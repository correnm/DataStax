package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 
 */

import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;

@Named("changePasscode")
@SessionScoped
public class ChangePasscode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private ApplicationUtils applicationUtils;
	@Inject private UserBean currentUser;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;

	//private Boolean meetsRequirements;
	private byte[] salt, encryptedPasscode;
	private Integer iterations;
	private String oldPasscode, newPasscode, newPasscode2, hashedPasscodeString, messageText, saltString, encryptedPasscodeString;

	//private Integer iterations = 20000;
	private String passcodeValueDelimiter = "***";

	// constructor
	public ChangePasscode() {

	}
	
	@PostConstruct
	public void init() {

		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get the hashed passcode from the DB
		rs = databaseQueryService.runQuery("select hashed_password from users where user_email = '" + currentUser.getEmail() + "'");
		row = rs.one();
		hashedPasscodeString = row.getString("hashed_password");

		String[] passCodeElementsArray = hashedPasscodeString.split("[*]{3}");
		iterations = Integer.parseInt(passCodeElementsArray[0]);
		try {
			salt = passCodeElementsArray[1].getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			encryptedPasscode = passCodeElementsArray[2].getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
    public String getOldPasscode() {
    	return "";
    }

    public String getNewPasscode() {
    	return "";
    }

    public String getNewPasscode2() {
    	return "";
    }

    public void setOldPasscode(String oldPasscode) {
    	this.oldPasscode = oldPasscode;
    }

    public void setNewPasscode(String newPasscode) {
    	this.newPasscode = newPasscode;
    }

    public void setNewPasscode2(String newPasscode2) {
    	this.newPasscode2 = newPasscode2;
    }

	public String changePasscodeActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		System.out.println("in changePasscodeActionControllerMethod - oldPasscode: " + oldPasscode);
		System.out.println("in changePasscodeActionControllerMethod - newPasscode: " + newPasscode);
		System.out.println("in changePasscodeActionControllerMethod - newPasscode2: " + newPasscode2);

		// does the new passcode meet all the requirements?
		//meetsRequirements = applicationUtils.meetsPasscodeRequirements(newPasscode);
		if (!applicationUtils.meetsPasscodeRequirements(newPasscode)) {
			messageText = "your New Passcode doesn't meet the minimum requirements!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("changePasscodeForm", errorMessage);
			return(null);			
		}

		// check that the two new passcode entries match
		if (!newPasscode.equals(newPasscode2)) {
			messageText = "your two New Passcode entries don't match!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("changePasscodeForm", errorMessage);
			return(null);
		}

		// check that the old passcode entry is valid
		if (!PasscodeEncryptionService.authenticate(oldPasscode, encryptedPasscode, salt, iterations)) {
			messageText = "the Current Passcode entered is invalid!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("changePasscodeForm", errorMessage);
			return(null);
		};

		// hash and save the new passcode
		encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(newPasscode, salt, iterations);

		try {
			saltString = new String(salt, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			encryptedPasscodeString = new String(encryptedPasscode, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String passcodeValueToStore = iterations.toString();
		passcodeValueToStore = passcodeValueToStore.concat(passcodeValueDelimiter);
		passcodeValueToStore = passcodeValueToStore.concat(saltString);
		passcodeValueToStore = passcodeValueToStore.concat(passcodeValueDelimiter);
		passcodeValueToStore = passcodeValueToStore.concat(encryptedPasscodeString);

		// execute the query to update the passcode in the database
		databaseQueryService.runUpdateQuery("update users set hashed_password = '" + passcodeValueToStore + "', password_last_reset = toDate(now()) where user_email = '" + currentUser.getEmail() + "'");

		// go back to the user's default dashboard?
		return("/" + currentUser.getDefaultLensView() + "?faces-redirect=true");

	}

	public String changePasscodeCancelControllerMethod() {
		
		// go back to the user's default dashboard
		return("/" + currentUser.getDefaultLensView() + "?faces-redirect=true");

	}

}
