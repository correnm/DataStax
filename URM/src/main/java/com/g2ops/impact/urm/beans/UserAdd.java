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

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

//import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.types.NavMenuItem;
import com.g2ops.impact.urm.types.OUSite;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;

@Named("userAdd")
@RequestScoped
public class UserAdd {

	//private static final long serialVersionUID = 1L;

	@Inject private ApplicationUtils applicationUtils;
	@Inject private UserBean currentUser;
	@Inject private OUSiteBean OUSites;
	@Inject private NavMenu dashboardsNavMenu;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;
	private Session appAuthDBSession;

	private String userEmail, passcode, firstName, lastName, role, selectedOUSite, selectedDashboard;
	private Boolean activeUserInd;

	private Integer iterations = 20000;
	private String passcodeValueDelimiter = "***";
	private Boolean systemAdministratorInd = false;
	private Boolean sendNotificationEmail = true;

	private List<String> roles;
	private List<OUSite> OUSiteArrayList = new ArrayList<OUSite>();
	private List<NavMenuItem> analyticsList = new ArrayList<NavMenuItem>();

	// constructor
	public UserAdd() {

	}
	
	@PostConstruct
	public void init() {

		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get List of all possible roles
		rs = databaseQueryService.runQuery("select option_values from lov_references where database_column = 'application_role_name'");
		row = rs.one();
		Map<String, String> roleMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);
		this.roles = new ArrayList<String>(roleMap.values());

		// get List of all possible Sites
		OUSiteArrayList = OUSites.getOUSiteArray();

		// get List of all possible Dashboards
		analyticsList = dashboardsNavMenu.getAnalyticssNavMenuItemList();

	}
	
    public String getUserEmail() {
    	return userEmail;
    }

    public String getPasscode() {
    	return "";
    }

    public String getFirstName() {
    	return firstName;
    }

    public String getLastName() {
    	return lastName;
    }

    public String getRole() {
    	return role;
    }

	public List<String> getRoles() {
		return roles;
	}

	public String getSelectedOUSite() {
		return selectedOUSite;
	}
	
	public List<OUSite> getOUSiteArray() {
		return OUSiteArrayList;
	}

	public String getSelectedDashboard() {
		return selectedDashboard;
	}
	
	public List<NavMenuItem> getAnalyticsArray() {
		return analyticsList;
	}

    public Boolean getActiveUserInd() {
		return activeUserInd;
	}

	public void setUserEmail(String userEmail) {
		System.out.println("in UserAdd setUserEmail method");
    	this.userEmail = userEmail.toLowerCase();
    }

    public void setPasscode(String passcode) {
    	this.passcode = passcode;
    }

    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }

    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

    public void setRole(String role) {
    	this.role = role;
    }

	public void setSelectedOUSite(String selectedOUSite) {
		this.selectedOUSite = selectedOUSite;
	}
	
	public void setSelectedDashboard(String selectedDashboard) {
		this.selectedDashboard = selectedDashboard;
	}
	
	public void setActiveUserInd(Boolean activeUserInd) {
		this.activeUserInd = activeUserInd;
	}

	public String addUserActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// get the Database Query Service object for this Organization
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		
		// check that the user email is available
		rs = databaseQueryService.runQuery("select user_email from users where user_email = '" + this.userEmail + "'");
		Boolean userEmailFound = false;
		for (@SuppressWarnings("unused") Row row : rs) {
			userEmailFound = true;
		}
		// if already in use, generate error message
		if (userEmailFound) {
			String messageText = "The user email " + this.userEmail + " already exists!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("userAddForm", errorMessage);
			return(null);
		}
		
		// *****
		// check that the user email matches the organization's top level domain?
		// *****
		
		// hash the password
		byte[] salt = PasscodeEncryptionService.generateSalt();
		byte[] encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(passcode, salt, iterations);
		String saltString = null;
		try {
			saltString = new String(salt, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String encryptedPasscodeString = null;
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
    	
		// split the OU and Site UUIDs into an array
		String[] newOUSiteArray = selectedOUSite.split("[|]{1}");

		// insert the user's info in the Users table
		String queryString = "insert into users (user_email, hashed_password, first_name, last_name, application_role_name, org_unit_id, site_id, default_lens_view_r, active_user_ind, send_notification_email, system_administrator_ind, audit_upsert)";
		queryString = queryString.concat(" values ('" + this.userEmail + "', '" + passcodeValueToStore + "', '" + this.firstName + "', '" + this.lastName + "', '" + this.role + "', " + UUID.fromString(newOUSiteArray[0]) + ", " + UUID.fromString(newOUSiteArray[1]) + ", '" + this.selectedDashboard + "', " + activeUserInd + ", " + sendNotificationEmail + ", " + systemAdministratorInd + ", { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' })");
		//System.out.println("in UserAdd addUserActionControllerMethod - queryString: " + queryString);
		//databaseQueryService.runUpdateQuery(queryString);

		// *****
		// send email to new user
			// generate key
			// insert record into appl_auth table
			// send email with link to new user page (including key parameter)
		// *****
		UUID uuid = applicationUtils.generateRandomUUID();
		System.out.println("in UserAdd addUserActionControllerMethod - uuid: " + uuid.toString());
		
		// get the database connection session
		appAuthDBSession = applicationUtils.getApplAuthDBSession();

		// insert the token record in the password_reset table
		queryString = "insert into password_reset (organization_name, email_token, email_address, reset_attempts, token_expiry)";
		queryString = queryString.concat(" values ('" + currentUser.getOrgName() + "', " + uuid + ", '" + this.userEmail + "', 0, todate(now()))");
		System.out.println("in UserAdd addUserActionControllerMethod - queryString: " + queryString);
		
		// *** need expiry date to be one day in the future
		
		appAuthDBSession.execute(queryString);

		// go back to the Manage Users page
		return "users-table";

	}

}
