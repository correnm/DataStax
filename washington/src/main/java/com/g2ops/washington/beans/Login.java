package com.g2ops.washington.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import com.g2ops.washington.types.User;
import com.g2ops.washington.utils.DatabaseConnectionManager;
import com.g2ops.washington.utils.PasscodeEncryptionService;
import com.g2ops.washington.utils.SessionUtils;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

	final long ONE_MINUTE_IN_MILLIS = 60000;
	final int NUM_LOCKOUT_MINUTES = 2;
	final int LOCKOUT_THRESHOLD = 5;
	final String LOCKOUT_ERROR_MESSAGE = "Your account is locked due to too many incorrect login attempts. Please try again in XX minutes.";

	// form field values
	private String userOrg = "G2 Ops"; // value set for testing purposes
	private String userEmail = ""; // value set for testing purposes "john.reddy@g2-ops.com"
	private String userPassCode = ""; // value set for testing purposes "password"

	// values retrieved from the application authorization keyspace
	private String orgKeyspace = "";
	//private String orgUsername = "";
	//private String orgPassword = "";

	// values retrieved from the user's organization keyspace
	private Boolean activeUserInd = false;
	private Boolean systemAdministratorInd = false;
	private String appRoleName = "";
	private Date loginTimeoutStartTime;
	private int numConsecutiveFailedAttempts = 0;
	private String defaultLensView = "";
	private String firstName = "";
	private String lastName = "";
	private String userName = "";
	private String hashedPassword = "";
	
	// used to return error messages to the login form
	private String messageText = "";
	
	public String getUserOrg() {
		return(userOrg);
	}

	public String getUserEmail() {
		return(userEmail);
	}

	public String getUserPassCode() {
		return(userPassCode);
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
  
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPassCode(String userPassCode) {
		this.userPassCode = userPassCode;
	}
  
	public String loginActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// trim and convert to lower case the userOrg value for use in query
		String userOrgLowerCase = userOrg.trim().toLowerCase();

		// trim and convert to lower case the userEmail value for use in query
		String userEmailLowerCase = userEmail.trim().toLowerCase();

		// make sure the userOrg value has a length of at least 3
		if (userOrg.length() < 3) {
			messageText = "Organization Name is at least 3 characters";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("loginForm:userOrg", errorMessage);
			return(null);
		}
		
		// get the servlet's context
		ServletContext ctx = SessionUtils.getRequest().getServletContext();

		// get the database connection to the application authorization keyspace from the servlet's context 
		DatabaseConnectionManager appAuthDBConnection = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// get the database connection session
		Session DBSession = appAuthDBConnection.getSession();

		// get the prepared statement for selecting organization info from the servlet's context
		PreparedStatement preparedStatement = (PreparedStatement)ctx.getAttribute("PSOrgInfo");
		
		// create bound statement
		BoundStatement boundStatement = preparedStatement.bind(userOrgLowerCase);
		
		// execute the query
		ResultSet rs = DBSession.execute(boundStatement);

		// get the result values
		Row row = rs.one();
		
		// if row is null, the userOrg value is invalid
		if (row == null) {

			// return to login form with error message
			messageText = "Organization Name is invalid";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		} else {

			// save values retrieved by query
			orgKeyspace = row.getString("keyspace_name");
			//orgUsername = row.getString("username");
			//orgPassword = row.getString("hashed_password");

		}

		// print statement for troubleshooting
		System.out.println("Organization Keyspace Name: " + orgKeyspace);
		//System.out.println("Organization DB Username: " + orgUsername);
		//System.out.println("Organization DB Password: " + orgPassword);

		// authenticate user in corresponding keyspace with userEmail and userPassCode and set user's preferences
		//String[] cPoints = ctx.getInitParameter("db_CONTACT_POINTS").split(",");
		//String port = ctx.getInitParameter("db_PORT");
		
		// create database connection to organization's keyspace
		//orgDBConnection = new DatabaseConnectionManager(orgUsername, orgPassword, cPoints, port, orgKeyspace);

		// put the database connection into the user's session
		//HttpSession userSession = SessionUtils.getSession();
		//userSession.setAttribute("orgDBManager", orgDBConnection);

		// get the Hash Map of the Organization database connections
		@SuppressWarnings("unchecked")
		Map<String, DatabaseConnectionManager> DBConnectionsHashMap = (Map<String, DatabaseConnectionManager>) ctx.getAttribute("OrgDBConnections");
		
		// get the database connection for this user's organization
		DatabaseConnectionManager orgDBConnection = DBConnectionsHashMap.get(orgKeyspace);
		
		// get the database connection session
		DBSession = orgDBConnection.getSession();

		// create the prepared statement for selecting the user's info
		preparedStatement = DBSession.prepare("select active_user_ind, application_role_name, default_lens_view_r, first_name, last_name, user_name, hashed_password, login_timeout_start_time, num_consecutive_failed_attempts, system_administrator_ind from users where user_email = ?");

		// create bound statement
		boundStatement = preparedStatement.bind(userEmailLowerCase);
		
		// execute the query
		rs = DBSession.execute(boundStatement);

		// get the result values
		row = rs.one();

		// if row is null, the userEmail value is invalid
		if (row == null) {

			// return to login form with error message
			messageText = "Login failed - userEmail is invalid";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		} else {
		
			// save values retrieved by query
			activeUserInd = row.getBool("active_user_ind");
			appRoleName = row.getString("application_role_name");
			defaultLensView = row.getString("default_lens_view_r");
			firstName = row.getString("first_name");
			lastName = row.getString("last_name");
			userName = row.getString("user_name");
			hashedPassword = row.getString("hashed_password");
			loginTimeoutStartTime = row.getTimestamp("login_timeout_start_time");
			numConsecutiveFailedAttempts = row.getInt("num_consecutive_failed_attempts");
			systemAdministratorInd = row.getBool("system_administrator_ind");

		}
		
		// print statements for troubleshooting
		System.out.println("Active User Ind: " + activeUserInd);
		System.out.println("Application Role Name: " + appRoleName);
		System.out.println("Default Lens View: " + defaultLensView);
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("User Name: " + userName);
		System.out.println("Hashed Password: " + hashedPassword);
		if (!(loginTimeoutStartTime == null)) {
			System.out.println("Login Timeout Start Time: " + loginTimeoutStartTime.toString());
		}
		System.out.println("Num Consecutive Failed Attempts: " + numConsecutiveFailedAttempts);
		System.out.println("System Administrator Ind: " + systemAdministratorInd);

		// hash the passcode entered by the user
		//userPassCode = userPassCode;
		
		// if not an active user, send back to login page
		if (!activeUserInd) {
			
			// return to login form with error message
			messageText = "Login failed - inactive user";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		}

		// if user is locked out, send back to login page
		if ((numConsecutiveFailedAttempts >= LOCKOUT_THRESHOLD) && (!systemAdministratorInd)) {

			// add 30 minutes to the timeout start time
			long loginTimeoutStartTimeInMs = loginTimeoutStartTime.getTime();
			Date loginTimeoutEndTime = new Date (loginTimeoutStartTimeInMs + (NUM_LOCKOUT_MINUTES * ONE_MINUTE_IN_MILLIS));

			// still locked out?
			Date currentDateTime = new Date();
			if (currentDateTime.before(loginTimeoutEndTime)) {
				
				// return to login form with error message
				long currentDateTimeInMs = currentDateTime.getTime();
				long loginTimeoutEndTimeInMs = loginTimeoutEndTime.getTime();
				int remainingLockoutMinutes = (int) ((loginTimeoutEndTimeInMs - currentDateTimeInMs) / ONE_MINUTE_IN_MILLIS);
				if (remainingLockoutMinutes < 2) {
					remainingLockoutMinutes = 2;
				}
				messageText = LOCKOUT_ERROR_MESSAGE;
				messageText = messageText.replaceAll("XX", Integer.toString(remainingLockoutMinutes));
				FacesMessage errorMessage = new FacesMessage(messageText);
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				return(null);

			}
			
		}
		
		// get the client IP address
		HttpServletRequest request = SessionUtils.getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		// if passcode is invalid, update users table and send back to login page
		/*
		String[] passCodeElementsArray = hashedPassword.split("***");
		int iterations = Integer.parseInt(passCodeElementsArray[0]);
		byte[] salt = passCodeElementsArray[1].getBytes();
		byte[] encryptedPasscode = passCodeElementsArray[2].getBytes();
		String saltString = new String(salt);
		String encryptedPasscodeString = new String(encryptedPasscode);

		//byte[] testencryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode("password", salt, iterations);
		//String testencryptedPasscodeString = new String(testencryptedPasscode);
		
		System.out.println("salt as a string: " + passCodeElementsArray[1]);
		System.out.println("passcode as a string: " + passCodeElementsArray[2]);
		System.out.println("salt as a string post conversions: " + saltString);
		System.out.println("passcode as a string post conversions: " + encryptedPasscodeString);
		System.out.println("iterations: " + iterations);
		System.out.println("salt: " + salt);
		System.out.println("encryptedPasscode: " + encryptedPasscode);
		//System.out.println("userPassCode: " + userPassCode);
		//System.out.println("testencryptedPasscodeString: " + testencryptedPasscodeString);
		
		boolean userPassCodeEncrypted = PasscodeEncryptionService.authenticate(userPassCode, encryptedPasscode, salt, iterations);
		*/
		
		if (!userPassCode.equals(hashedPassword)) {
		//if (!userPassCodeEncrypted) {

			// if was locked out, reset counter
			if (numConsecutiveFailedAttempts >= LOCKOUT_THRESHOLD) {
				numConsecutiveFailedAttempts = 0;
			}
			
			// increment count
			numConsecutiveFailedAttempts += 1;

			// create the prepared statement for updating the user's info
			preparedStatement = DBSession.prepare("update users set num_consecutive_failed_attempts = ?, login_timeout_start_time = toTimestamp(now()), ip_address = ? where user_email = ?");

			// create bound statement
			boundStatement = preparedStatement.bind(numConsecutiveFailedAttempts, ipAddress, userEmailLowerCase);
			
			// execute the query
			rs = DBSession.execute(boundStatement);

			// return to login form with error message
			if ((numConsecutiveFailedAttempts == LOCKOUT_THRESHOLD)  && (!systemAdministratorInd)) {
				messageText = LOCKOUT_ERROR_MESSAGE;
				messageText = messageText.replaceAll("XX", Integer.toString(NUM_LOCKOUT_MINUTES));
			} else {
				messageText = "Login failed - userPassCode is invalid";
			}
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		// login successful, update users table, save values in user's session, send to default lens view
		} else {
			
			// get the session ID
			//jsessionID = userSession.getId();
			
			// create the prepared statement for updating the user's info
			preparedStatement = DBSession.prepare("update users set num_consecutive_failed_attempts = 0, login_timeout_start_time = null, last_successful_login_date_time = toTimestamp(now()), ip_address = ? where user_email = ?");

			// create bound statement
			boundStatement = preparedStatement.bind(ipAddress, userEmailLowerCase);
			
			// execute the query
			rs = DBSession.execute(boundStatement);			
			
			// create the prepared statement for getting the Org ID
			preparedStatement = DBSession.prepare("select org_id from organizations");

			// create bound statement
			boundStatement = preparedStatement.bind();
			
			// execute the query
			rs = DBSession.execute(boundStatement);			
			
			// get the result values
			row = rs.one();

			String orgID = row.getUUID("org_id").toString();
			
			// create user object and store in user's session
			User user = new User(userEmailLowerCase, userName, firstName, lastName, appRoleName, defaultLensView, systemAdministratorInd);
			HttpSession userSession = SessionUtils.getSession();
			userSession.setAttribute("user", user);
			userSession.setAttribute("orgKeyspace", orgKeyspace);
			userSession.setAttribute("orgID", orgID);

			// send to default page for this user
			return(defaultLensView);

		}
				
	}

	// logout event, invalidate session
	public String logoutActionControllerMethod() {

		// get user's session
		HttpSession session = SessionUtils.getSession();
		
		// destroy user's session
		session.invalidate();
		
		// send user to login page
		return "login";

	}

	public String helpActionControllerMethod() {
		return "page-b";
	}
	
}
