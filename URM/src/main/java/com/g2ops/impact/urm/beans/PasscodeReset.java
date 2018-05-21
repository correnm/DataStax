package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, April 2018
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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("passcodeReset")
@ConversationScoped
public class PasscodeReset implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject Conversation conversation;
	
	@Inject private ApplicationUtils applicationUtils;
	
	private ServletContext ctx;
	
	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;
	private Session appAuthDBSession;

	private String requestID, orgName, cellPhone, cellPhoneDB, orgKeyspace, queryString, userEmail, newPasscode, newPasscode2, messageText, saltString, encryptedPasscodeString;
	private String securityQuestion1, securityQuestion2, securityQuestion3;
	private String securityAnswer1, securityAnswer2, securityAnswer3;
	private String userSecurityAnswer1, userSecurityAnswer2, userSecurityAnswer3;
	private Integer resetAttempts, iterations;
	private Boolean resetComplete;
	private Date resetRequestDatetime;
	private byte[] salt, encryptedPasscode;
	private String passcodeValueDelimiter = "***";

    private final static String requestID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private final static Pattern requestID_COMPILED_PATTERN = Pattern.compile(requestID_PATTERN);

    private final static Integer MAX_RESET_ATTEMPTS = 3;
    

	// constructor
	public PasscodeReset() {

	}
	
	
	// method that executes after injections have occurred
	@PostConstruct
	public void init() {

		if (conversation.isTransient()) {
			conversation.begin();
		}
		
		// get the servlet's context
		ctx = SessionUtils.getRequest().getServletContext();

		// get the database connection session
		appAuthDBSession = applicationUtils.getApplAuthDBSession();

	}

	
	// getters
	public String getRequestID() {
		return requestID;
	}

	public String getOrgName() {
		return orgName;
	}

    public String getCellPhone() {
    	return cellPhone;
    }

    public String getSecurityQuestion1() {
    	return securityQuestion1;
    }
    
    public String getSecurityQuestion2() {
    	return securityQuestion2;
    }
    
    public String getSecurityQuestion3() {
    	return securityQuestion3;
    }
    
    public String getUserSecurityAnswer1() {
    	return "";
    }

    public String getUserSecurityAnswer2() {
    	return "";
    }

    public String getUserSecurityAnswer3() {
    	return "";
    }

    public String getNewPasscode() {
    	return "";
    }

    public String getNewPasscode2() {
    	return "";
    }

    // setters
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
  
	public void setOrgName(String orgName) {
		this.orgName = orgName.toLowerCase();
	}
  
	public void setCellPhone(String cellPhone) {
    	this.cellPhone = cellPhone;
    	this.cellPhone = this.cellPhone.replaceAll("[^0-9]", "");
    }

	public void setUserSecurityAnswer1(String userSecurityAnswer1) {
		this.userSecurityAnswer1 = userSecurityAnswer1.toLowerCase().trim();
	}
	
	public void setUserSecurityAnswer2(String userSecurityAnswer2) {
		this.userSecurityAnswer2 = userSecurityAnswer2.toLowerCase().trim();
	}
	
	public void setUserSecurityAnswer3(String userSecurityAnswer3) {
		this.userSecurityAnswer3 = userSecurityAnswer3.toLowerCase().trim();
	}

	public void setNewPasscode(String newPasscode) {
		this.newPasscode = newPasscode.trim();
	}
  
	public void setNewPasscode2(String newPasscode2) {
		this.newPasscode2 = newPasscode2.trim();
	}
  

	// validate Organization name entered on form
	public void validateOrganizationName(FacesContext context, UIComponent comp, Object obj) {

		// cast the form field value to a string type
		orgName = (String) obj;
		orgName = orgName.trim().toLowerCase();

		// get the prepared statement for selecting organization info from the servlet's context
		PreparedStatement preparedStatement = (PreparedStatement)ctx.getAttribute("PSOrgInfo");
	
		// create bound statement
		BoundStatement boundStatement = preparedStatement.bind(orgName);
	
		// execute the query
		rs = appAuthDBSession.execute(boundStatement);

		// get the result values
		row = rs.one();

		// if row is null, the orgName value is invalid
		if (row == null) {

			// return to login form with error message
            FacesMessage msg = new FacesMessage("Organization Name is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

		} else {

			// save values retrieved by query
			orgKeyspace = row.getString("keyspace_name");

		}

	}
	
	
	// validate Cell phone entered on form
	public void validateCellPhone(FacesContext context, UIComponent comp, Object obj) {

		// cast the form field value to a string type
		cellPhone = (String) obj;
		cellPhone = cellPhone.trim();

		if (cellPhone.equals("")) {
			// return to form with error message
            FacesMessage msg = new FacesMessage("Cell Phone is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
		}

	}
	
	
	// reset passcode step 1 form was submitted
	public String resetPasscodeStep1ActionControllerMethod() {

		// validate that the requestID is a valid UUID
        Matcher matcher = requestID_COMPILED_PATTERN.matcher(requestID);

        // if not a match, then the requestID is not a valid UUID
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("this Passcode Reset Request is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
        }

		// execute the query for selecting the info for this reset request
		queryString = "select email_address, "
							+ "reset_attempts, "
							+ "reset_complete, "
							+ "reset_request_datetime "
						+ "from passcode_reset "
						+ "where organization_name = '" + this.orgName + "' "
							+ "and email_token = " + this.requestID;
		rs = appAuthDBSession.execute(queryString);

		// get the result values
		row = rs.one();

		// if row is null, the requestID value is invalid
		if (row == null) {

			// return to form with error message
            FacesMessage msg = new FacesMessage("this Passcode Reset Request is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);

		}

		// save values retrieved by query
		userEmail = row.getString("email_address");
		resetAttempts = row.getInt("reset_attempts");
		resetComplete = row.getBool("reset_complete");
		resetRequestDatetime = row.getTimestamp("reset_request_datetime");

		// check that this request isn't already completed
		if (resetComplete) {
				
			// return to form with error message
	        FacesMessage msg = new FacesMessage("this Passcode Reset Request has already been completed");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
				
		}
			
		// check the reset attempts
		if (resetAttempts >= MAX_RESET_ATTEMPTS) {
				
			// return to form with error message
	        FacesMessage msg = new FacesMessage("this Passcode Reset Request has exceeded the maximum allowed reset attempts, please submit a new Passcode Reset Request");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
	        	
		}

		// check the reset request date/time to see if this request is expired
		Date currentDate = java.util.Calendar.getInstance().getTime(); // get the current date/time
		System.out.println("currentDate: " + currentDate);
		System.out.println("resetRequestDatetime: " + resetRequestDatetime);
		Calendar c = Calendar.getInstance(); // create an instance of Calendar
		c.setTime(resetRequestDatetime); // set the instance of Calendar to the reset request date/time
		c.add(Calendar.HOUR, 24); // add 24 hours to the reset request date/time to get the expiration date/time
		Date resetExpirationDate = c.getTime(); // convert the instance of Calendar to a Date object

		if (currentDate.compareTo(resetExpirationDate) > 0) { // compare the current date/time to the expiration date/time

			// return to form with error message
			FacesMessage msg = new FacesMessage("this Passcode Reset Request has expired");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
	        	
		}
			
		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(orgKeyspace);

		// execute the query for selecting the user's cell phone number and security questions
		queryString = "select cell_phone_number, security "
						+ "from users "
						+ "where user_email = '" + this.userEmail + "'";
		rs = databaseQueryService.runQuery(queryString);

		// get the result values
		row = rs.one();

		// validate the cell phone number
		cellPhoneDB = row.getString("cell_phone_number");
		if (!cellPhone.equals(cellPhoneDB)) {
			
			// return to form with error message
			FacesMessage msg = new FacesMessage("the cell phone number entered is incorrect");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
	        	
		}

		// all good, so get the security questions

		// save value retrieved by query
		List<UDTValue> qaList = row.getList("security", UDTValue.class);
		System.out.println("qaList: " + qaList);
		
		UDTValue qa = qaList.get(0);
		securityQuestion1 = qa.getString("question");
		securityAnswer1 = qa.getString("answer");
		qa = qaList.get(1);
		securityQuestion2 = qa.getString("question");
		securityAnswer2 = qa.getString("answer");
		qa = qaList.get(2);
		securityQuestion3 = qa.getString("question");
		securityAnswer3 = qa.getString("answer");
		System.out.println("securityQuestion1: " + securityQuestion1);
		System.out.println("securityAnswer1: " + securityAnswer1);
		System.out.println("securityQuestion2: " + securityQuestion2);
		System.out.println("securityAnswer2: " + securityAnswer2);
		System.out.println("securityQuestion3: " + securityQuestion3);
		System.out.println("securityAnswer3: " + securityAnswer3);
		
		// go to step 2
		return "passcode-reset-step2?faces-redirect=true";
		
	}


	// reset passcode step 2 form was submitted
	public String resetPasscodeStep2ActionControllerMethod() {
		
		// increment passcode reset attempt counter in DB
		resetAttempts += 1;
		queryString = "update passcode_reset "
						+ "set reset_attempts = " + resetAttempts + " "
						+ "where organization_name = '" + this.orgName + "'"
							+ "and email_token = " + this.requestID;
		// execute the query
		rs = appAuthDBSession.execute(queryString);

		// validate security question answers
		if (!userSecurityAnswer1.equals(securityAnswer1) || !userSecurityAnswer2.equals(securityAnswer2) || !userSecurityAnswer3.equals(securityAnswer3)) {

			// return to form with error message
            FacesMessage msg = new FacesMessage("one or more of your answers are not correct");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
        	
		}

		// security questions answered correctly - go to step 3
		return "passcode-reset-step3?faces-redirect=true";

	}


	// reset passcode step 3 form was submitted
	public String resetPasscodeStep3ActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		
		// does the new passcode meet all the requirements?
		if (!applicationUtils.meetsPasscodeRequirements(newPasscode)) {
			messageText = "your New Passcode doesn't meet the minimum requirements!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);			
		}

		// check that the two new passcode entries match
		if (!newPasscode.equals(newPasscode2)) {
			messageText = "your two New Passcode entries don't match!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);
		}

		// get the hashed passcode from the DB so we can use the salt and iterations
		rs = databaseQueryService.runQuery("select hashed_password from users where user_email = '" + userEmail + "'");
		row = rs.one();
		String hashedPasscodeString = row.getString("hashed_password");

		String[] passCodeElementsArray = hashedPasscodeString.split("[*]{3}");
		iterations = Integer.parseInt(passCodeElementsArray[0]);
		try {
			salt = passCodeElementsArray[1].getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		databaseQueryService.runUpdateQuery("update users set hashed_password = '" + passcodeValueToStore + "', password_last_reset = toDate(now()) where user_email = '" + userEmail + "'");

		// mark as completed in DB
		queryString = "update passcode_reset "
				+ "set reset_complete = true "
				+ "where organization_name = '" + this.orgName + "'"
					+ "and email_token = " + this.requestID;
		rs = appAuthDBSession.execute(queryString);
		
		// end the conversation - bean goes out of scope
		if (!conversation.isTransient()) {
			conversation.end();
		}

		// go to the login page
		return "/login?faces-redirect=true";

	}


	// cancel button was clicked, go to login page
	public String resetPasscodeCancelControllerMethod() {

		// end the conversation - bean goes out of scope
		if (!conversation.isTransient()) {
			conversation.end();
		}

		// go to the Login page
		return "/login?faces-redirect=true";
		
	}

	
}
