package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, January 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 */

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseConnectionManager;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;
import com.g2ops.impact.urm.utils.SessionUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;

@Named("userBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private ApplicationUtils applicationUtils;
	
	final long ONE_MINUTE_IN_MILLIS = 60000;
	final int NUM_LOCKOUT_MINUTES = 2;
	final int LOCKOUT_THRESHOLD = 5;
	final String LOCKOUT_ERROR_MESSAGE = "Your account is locked due to too many incorrect login attempts. Please try again in XX minutes.";

	// form field values
	private String orgName = "";
	private String email = "";
	private String passCode = "";

	// values retrieved from the application authorization keyspace
	private String orgKeyspace = "";

	// values retrieved from the user's organization keyspace
	private Boolean activeUserInd = false;
	private Boolean systemAdministratorInd = false;
	private String appRoleName = "";
	private Date loginTimeoutStartTime;
	private int numConsecutiveFailedAttempts = 0;
	private String defaultLensView = "";
	private String orgUnitID = "";
	private String siteID = "";
	private String firstName = "";
	private String lastName = "";
	private String hashedPassword = "";
	
	// used to return error messages to the login form
	private String messageText = "";
	
	public String getOrgName() {
		return(orgName);
	}

	public String getEmail() {
		return(email);
	}

	public String getPassCode() {
		return(passCode);
	}

	public String getOrgKeyspace() {
		return(orgKeyspace);
	}

	public Boolean getSystemAdministratorInd() {
		return(systemAdministratorInd);
	}

	public String getAppRoleName() {
		return(appRoleName);
	}

	public String getDefaultLensView() {
		return(defaultLensView);
	}

	public String getOrgUnitID() {
		return(orgUnitID);
	}

	public String getSiteID() {
		return(siteID);
	}

	public String getFirstName() {
		return(firstName);
	}

	public String getLastName() {
		return(lastName);
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
  
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public void setAppRoleName(String appRoleName) {
		this.appRoleName = appRoleName;
	}

	public void setDefaultLensView(String defaultLensView) {
		this.defaultLensView = defaultLensView;
	}

	public void setOrgUnitID(String orgUnitID) {
		this.orgUnitID = orgUnitID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	// user login method
	public String login() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// trim and convert to lower case the submitted orgName value for use in query
		orgName = orgName.trim().toLowerCase();

		// trim and convert to lower case the submitted userEmail value for use in query
		email = email.trim().toLowerCase();

		// make sure the orgName value has a length of at least 3
		if (orgName.length() < 3) {
			messageText = "Organization Name is at least 3 characters";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("loginForm:orgName", errorMessage);
			return(null);
		}
		
		// get the servlet's context
		ServletContext ctx = SessionUtils.getRequest().getServletContext();

		// get the database connection session
		Session appAuthDBSession = applicationUtils.getApplAuthDBSession();

		// get the database connection to the application authorization keyspace from the servlet's context 
		//DatabaseConnectionManager appAuthDBConnection = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// get the database connection session
		//Session DBSession = appAuthDBConnection.getSession();

		// get the prepared statement for selecting organization info from the servlet's context
		PreparedStatement preparedStatement = (PreparedStatement)ctx.getAttribute("PSOrgInfo");
	
		// create bound statement
		BoundStatement boundStatement = preparedStatement.bind(orgName);
	
		// execute the query
		ResultSet rs = appAuthDBSession.execute(boundStatement);

		// get the result values
		Row row = rs.one();

		// if row is null, the orgName value is invalid
		if (row == null) {

			// return to login form with error message
			messageText = "the submitted Organization Name is invalid";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		} else {

			// save values retrieved by query
			orgKeyspace = row.getString("keyspace_name");

		}

		// get the Hash Map of the Organization database connections
		@SuppressWarnings("unchecked")
		Map<String, DatabaseConnectionManager> DBConnectionsHashMap = (Map<String, DatabaseConnectionManager>)ctx.getAttribute("OrgDBConnections");
		
		// get the database connection for this user's organization
		DatabaseConnectionManager orgDBConnection = DBConnectionsHashMap.get(orgKeyspace);
		
		// get the database connection session
		Session DBSession = orgDBConnection.getSession();

		// create the prepared statement for selecting the user's info
		preparedStatement = DBSession.prepare("select active_user_ind, application_role_name, default_lens_view_r, org_unit_id, site_id, first_name, last_name, hashed_password, login_timeout_start_time, num_consecutive_failed_attempts, system_administrator_ind from users where user_email = ?");

		// create bound statement
		boundStatement = preparedStatement.bind(email);
		
		// execute the query
		rs = DBSession.execute(boundStatement);

		// get the result values
		row = rs.one();

		// if row is null, the email value is invalid
		if (row == null) {

			// return to login form with error message
			messageText = "the submitted Email Address is invalid";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		} else {
		
			// save values retrieved by query
			activeUserInd = row.getBool("active_user_ind");
			appRoleName = row.getString("application_role_name");
			defaultLensView = row.getString("default_lens_view_r");
			orgUnitID = row.getUUID("org_unit_id").toString();
			siteID = row.getUUID("site_id").toString();
			firstName = row.getString("first_name");
			lastName = row.getString("last_name");
			hashedPassword = row.getString("hashed_password");
			loginTimeoutStartTime = row.getTimestamp("login_timeout_start_time");
			numConsecutiveFailedAttempts = row.getInt("num_consecutive_failed_attempts");
			systemAdministratorInd = row.getBool("system_administrator_ind");

		}
		
		// if not an active user, send back to login page
		if (!activeUserInd) {
			
			// return to login form with error message
			messageText = "this account is Inactive";
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
				int remainingLockoutMinutes = (int)((loginTimeoutEndTimeInMs - currentDateTimeInMs) / ONE_MINUTE_IN_MILLIS);
				if (remainingLockoutMinutes < 2) {
					remainingLockoutMinutes = 2;
				}
				messageText = LOCKOUT_ERROR_MESSAGE;
				messageText = messageText.replaceAll("XX", Integer.toString(remainingLockoutMinutes));
				FacesMessage errorMessage = new FacesMessage(messageText);
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, errorMessage);
				return(null);

			} // if still locked out
			
		} // if user is locked out
		
		// get the client IP address
		String ipAddress = applicationUtils.getUserIPAddress();

		// if passCode is invalid, update users table and send back to login page
		String[] passCodeElementsArray = hashedPassword.split("[*]{3}");
		int iterations = Integer.parseInt(passCodeElementsArray[0]);
		byte[] salt = passCodeElementsArray[1].getBytes("UTF-8");
		byte[] encryptedPasscode = passCodeElementsArray[2].getBytes("UTF-8");
		boolean userPassCodeEncrypted = PasscodeEncryptionService.authenticate(passCode, encryptedPasscode, salt, iterations);
		
		if (!userPassCodeEncrypted) {

			// if was locked out, reset counter
			if (numConsecutiveFailedAttempts >= LOCKOUT_THRESHOLD) {
				numConsecutiveFailedAttempts = 0;
			}
			
			// increment count
			numConsecutiveFailedAttempts += 1;

			// create the prepared statement for updating the user's info
			preparedStatement = DBSession.prepare("update users set num_consecutive_failed_attempts = ?, login_timeout_start_time = toTimestamp(now()), ip_address = ? where user_email = ?");

			// create bound statement
			boundStatement = preparedStatement.bind(numConsecutiveFailedAttempts, ipAddress, email);
			
			// execute the query
			rs = DBSession.execute(boundStatement);

			// return to login form with error message
			if ((numConsecutiveFailedAttempts == LOCKOUT_THRESHOLD)  && (!systemAdministratorInd)) {
				messageText = LOCKOUT_ERROR_MESSAGE;
				messageText = messageText.replaceAll("XX", Integer.toString(NUM_LOCKOUT_MINUTES));
			} else {
				messageText = "the submitted PassCode is invalid";
			}
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, errorMessage);
			return(null);

		// login successful, update users table, save values in user's session, send to default lens view
		} else {
			
			// create the prepared statement for updating the user's info
			preparedStatement = DBSession.prepare("update users set num_consecutive_failed_attempts = 0, login_timeout_start_time = null, last_successful_login_date_time = toTimestamp(now()), ip_address = ? where user_email = ?");

			// create bound statement
			boundStatement = preparedStatement.bind(ipAddress, email);
			
			// execute the query
			rs = DBSession.execute(boundStatement);			
			
			// create user object and store in user's session
			//User user = new User(email, firstName, lastName, appRoleName, defaultLensView, systemAdministratorInd);
			HttpSession userSession = SessionUtils.getSession();
			userSession.setAttribute("loggedIn", true);
			
			// store Organization, OU and Site data in user's session
			//userSession.setAttribute("orgKeyspace", orgKeyspace);
			//userSession.setAttribute("orgID", orgID);
			//userSession.setAttribute("currentOU", orgUnitID);
			//userSession.setAttribute("currentSite", siteID);

			// send to default page for this user
			return("/" + defaultLensView + "?faces-redirect=true");

		} // if login was successful or not
		
	}

	// logout method
	public String logout() {

		// get user's session
		HttpSession userSession = SessionUtils.getSession();
		
		// destroy user's session
		userSession.invalidate();
		
		// send user to login page and use URL parameter to ensure page URL changes
		return "/login?faces-redirect=true";

	}

}
