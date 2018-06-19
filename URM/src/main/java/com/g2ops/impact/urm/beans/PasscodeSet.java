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
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("passcodeSet")
@ConversationScoped
public class PasscodeSet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject Conversation conversation;
	
	@Inject private ApplicationUtils applicationUtils;
	
	private ServletContext ctx;
	
	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;
	private Session appAuthDBSession;

	private String requestID, orgName, cellPhone, cellPhoneDB, orgKeyspace, queryString, userEmail, passcode, passcode2, messageText, saltString, encryptedPasscodeString;
	private String securityQuestion1, securityQuestion2, securityQuestion3;
	private String userSecurityQuestion1, userSecurityQuestion2, userSecurityQuestion3, userSecurityAnswer1, userSecurityAnswer2, userSecurityAnswer3;
	private Integer resetAttempts, iterations;
	private Boolean resetComplete;
	private Date resetRequestDatetime;
	private byte[] salt, encryptedPasscode;
	private String passcodeValueDelimiter = "***";

    private final static String requestID_PATTERN = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    private final static Pattern requestID_COMPILED_PATTERN = Pattern.compile(requestID_PATTERN);


	// constructor
	public PasscodeSet() {

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

		// get the default passcode iterations
		iterations = applicationUtils.getPasscodeIterations();

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

    public String getUserSecurityQuestion1() {
    	return securityQuestion1;
    }
    
    public String getUserSecurityQuestion2() {
    	return securityQuestion2;
    }
    
    public String getUserSecurityQuestion3() {
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

    public String getPasscode() {
    	return "";
    }

    public String getPasscode2() {
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

	public void setUserSecurityQuestion1(String userSecurityQuestion1) {
		this.userSecurityQuestion1 = userSecurityQuestion1.toLowerCase().trim();
	}
	
	public void setUserSecurityQuestion2(String userSecurityQuestion2) {
		this.userSecurityQuestion2 = userSecurityQuestion2.toLowerCase().trim();
	}
	
	public void setUserSecurityQuestion3(String userSecurityQuestion3) {
		this.userSecurityQuestion3 = userSecurityQuestion3.toLowerCase().trim();
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

	public void setPasscode(String passcode) {
		this.passcode = passcode.trim();
	}
  
	public void setPasscode2(String passcode2) {
		this.passcode2 = passcode2.trim();
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
	
	
	// set passcode step 1 form was submitted
	public String setPasscodeStep1ActionControllerMethod() {

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
            FacesMessage msg = new FacesMessage("this Passcode Set Request is invalid");
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
	        FacesMessage msg = new FacesMessage("this Passcode Set Request has already been completed");
	        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
				
		}
			
		// check the request date/time to see if this request is expired
		Date currentDate = java.util.Calendar.getInstance().getTime(); // get the current date/time
		System.out.println("currentDate: " + currentDate);
		System.out.println("resetRequestDatetime: " + resetRequestDatetime);
		Calendar c = Calendar.getInstance(); // create an instance of Calendar
		c.setTime(resetRequestDatetime); // set the instance of Calendar to the reset request date/time
		c.add(Calendar.HOUR, 24); // add 24 hours to the reset request date/time to get the expiration date/time
		Date resetExpirationDate = c.getTime(); // convert the instance of Calendar to a Date object

		if (currentDate.compareTo(resetExpirationDate) > 0) { // compare the current date/time to the expiration date/time

			// return to form with error message
			FacesMessage msg = new FacesMessage("this Passcode Set Request has expired");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);
	        	
		}
			
		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(orgKeyspace);

		// execute the query for selecting the user's cell phone number
		queryString = "select cell_phone_number "
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

		// go to step 2
		return "passcode-set-step2?faces-redirect=true";
		
	}


	// set passcode step 2 form was submitted
	public String setPasscodeStep2ActionControllerMethod() {
		
		// increment passcode reset attempt counter in DB
		resetAttempts += 1;
		queryString = "update passcode_reset "
						+ "set reset_attempts = " + resetAttempts + " "
						+ "where organization_name = '" + this.orgName + "'"
							+ "and email_token = " + this.requestID;
		// execute the query
		rs = appAuthDBSession.execute(queryString);

		// security questions and answers submitted - go to step 3
		return "passcode-set-step3?faces-redirect=true";

	}


	// set passcode step 3 form was submitted
	public String setPasscodeStep3ActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		
		// does the passcode meet all the requirements?
		if (!applicationUtils.meetsPasscodeRequirements(passcode)) {
			messageText = "your Passcode doesn't meet the minimum requirements!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);			
		}

		// check that the two passcode entries match
		if (!passcode.equals(passcode2)) {
			messageText = "your two Passcode entries don't match!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);
		}

		// get a salt value
		salt = PasscodeEncryptionService.generateSalt();
		
		// hash and save the new passcode
		encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(passcode, salt, iterations);

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

		// execute the query to save the security questions and answers in the database
		databaseQueryService.runUpdateQuery("update users set security = [{question:'" + userSecurityQuestion1 + "',answer:'" + userSecurityAnswer1 + "'}, "
				+ "{question:'" + userSecurityQuestion2 + "',answer:'" + userSecurityAnswer2 + "'}, "
				+ "{question:'" + userSecurityQuestion3 + "',answer:'" + userSecurityAnswer3 + "'}] "
				+ "where user_email = '" + userEmail + "'");
		
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
	public String setPasscodeCancelControllerMethod() {

		// end the conversation - bean goes out of scope
		if (!conversation.isTransient()) {
			conversation.end();
		}

		// go to the Login page
		return "/login?faces-redirect=true";
		
	}

	
}
