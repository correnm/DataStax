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
import com.g2ops.impact.urm.types.NavMenuItem;
import com.g2ops.impact.urm.types.OUSite;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;

@Named("userAdd")
@RequestScoped
public class UserAdd {

	//private static final long serialVersionUID = 1L;

	@Inject private UserBean currentUser;
	@Inject private OUSiteBean OUSites;
	@Inject private NavMenu dashboardsNavMenu;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;

	private String userEmail, passcode, firstName, lastName, role, selectedOUSite, selectedDashboard;
	private Boolean activeUserInd;

	private Integer iterations = 20000;
	private String passcodeValueDelimiter = "***";
	private Boolean systemAdministratorInd = false;
	private Boolean sendNotificationEmail = true;

	private List<String> roles;
	private List<OUSite> OUSiteArrayList = new ArrayList<OUSite>();
	private List<NavMenuItem> dashboardsList = new ArrayList<NavMenuItem>();

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
		dashboardsList = dashboardsNavMenu.getDashboardsNavMenuItemList();

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
	
	public List<NavMenuItem> getDashboardArray() {
		return dashboardsList;
	}

    public Boolean getActiveUserInd() {
		return activeUserInd;
	}

	public void setUserEmail(String userEmail) {
		System.out.println("in UserAdd setUserEmail method");
    	this.userEmail = userEmail;
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

		/*
		System.out.println("in UserAdd addUserActionControllerMethod - userEmail: " + userEmail);
		System.out.println("in UserAdd addUserActionControllerMethod - passcode: " + passcode);
		System.out.println("in UserAdd addUserActionControllerMethod - firstName: " + firstName);
		System.out.println("in UserAdd addUserActionControllerMethod - lastName: " + lastName);
		System.out.println("in UserAdd addUserActionControllerMethod - role: " + role);
		System.out.println("in UserAdd addUserActionControllerMethod - selectedOUSite: " + selectedOUSite);
		System.out.println("in UserAdd addUserActionControllerMethod - selectedDashboard: " + selectedDashboard);
		System.out.println("in UserAdd addUserActionControllerMethod - activeUserInd: " + activeUserInd);
		System.out.println("in UserAdd addUserActionControllerMethod - passcodeValueToStore: " + passcodeValueToStore);
		*/

		// insert the user's info in the Users table
		String queryString = "insert into users (user_email, hashed_password, first_name, last_name, application_role_name, org_unit_id, site_id, default_lens_view_r, active_user_ind, send_notification_email, system_administrator_ind, audit_upsert)";
		queryString = queryString.concat(" values ('" + this.userEmail + "', '" + passcodeValueToStore + "', '" + this.firstName + "', '" + this.lastName + "', '" + this.role + "', " + UUID.fromString(newOUSiteArray[0]) + ", " + UUID.fromString(newOUSiteArray[1]) + ", '" + this.selectedDashboard + "', " + activeUserInd + ", " + sendNotificationEmail + ", " + systemAdministratorInd + ", { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' })");
		//System.out.println("in UserAdd addUserActionControllerMethod - queryString: " + queryString);
		databaseQueryService.runUpdateQuery(queryString);

		// go back to the Manage Users page
		return "users-table";

	}

}
