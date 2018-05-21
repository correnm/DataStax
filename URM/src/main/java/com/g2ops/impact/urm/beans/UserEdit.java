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
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
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
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Mapper;
import com.g2ops.impact.urm.types.Audit_Upsert;
import com.g2ops.impact.urm.types.NavMenuItem;
import com.g2ops.impact.urm.types.OUSite;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
//import com.g2ops.impact.urm.utils.PasscodeEncryptionService;

@Named("userEdit")
@SessionScoped
public class UserEdit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private ApplicationUtils applicationUtils;
	@Inject private UserBean currentUser;
	@Inject private OUSiteBean OUSites;
	@Inject private NavMenu dashboardsNavMenu;
	
	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;

	private String userToEditEmail, firstName, lastName, role, selectedOUSite, selectedDashboard;
	private Boolean activeUserInd;

	//private String newPasscode;
	//private Integer iterations = 20000;
	//private String passcodeValueDelimiter = "***";

	private List<String> roles;
	private List<OUSite> OUSiteArrayList = new ArrayList<OUSite>();
	private List<NavMenuItem> analyticsList = new ArrayList<NavMenuItem>();

	// constructor
	public UserEdit() {

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

		FacesContext context = FacesContext.getCurrentInstance();
		userToEditEmail = context.getExternalContext().getRequestParameterMap().get("email");
		System.out.println("in UserEdit init method - userToEditEmail: " + userToEditEmail);

		// select the user's data
		//rs = databaseQueryService.runQuery("select user_email, application_role_name, org_unit_id, site_id, default_lens_view_r, first_name, last_name, active_user_ind from users where user_email = '" + userToEditEmail + "'");

		// get the result record
		//row = rs.one();
		
		// set values to what was returned by the query
		//firstName = row.getString("first_name");
		//lastName = row.getString("last_name");
		//role = row.getString("application_role_name");
		//selectedOUSite = row.getUUID("org_unit_id").toString() + "|" + row.getUUID("site_id").toString();
		//selectedDashboard = row.getString("default_lens_view_r");
		//activeUserInd = row.getBool("active_user_ind");

	}

	// method for getting the data for the user being edited
	public void retrieveUserData() {

		System.out.println("in UserEdit retrieveUserData method - userToEditEmail: " + userToEditEmail);

		// select the user's data
		rs = databaseQueryService.runQuery("select user_email, application_role_name, org_unit_id, site_id, default_lens_view_r, first_name, last_name, active_user_ind from users where user_email = '" + userToEditEmail + "'");

		// get the result record
		row = rs.one();
		
		// set values to what was returned by the query
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		role = row.getString("application_role_name");
		selectedOUSite = row.getUUID("org_unit_id").toString() + "|" + row.getUUID("site_id").toString();
		selectedDashboard = row.getString("default_lens_view_r");
		activeUserInd = row.getBool("active_user_ind");
		
	}

    public String getUserToEditEmail() {
    	return userToEditEmail;
    }

    //public String getNewPasscode() {
    	//return "";
    //}

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

	public void setUserToEditEmail(String userToEditEmail) {
		System.out.println("in UserEdit setUserToEditEmail method");
    	this.userToEditEmail = userToEditEmail;
    }

    //public void setNewPasscode(String newPasscode) {
    	//this.newPasscode = newPasscode;
    //}

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

	public String editUserActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// get the Database Query Service object for this Organization
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		
		Session session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		Mapper<Audit_Upsert> mapper = new MappingManager(session).mapper(Audit_Upsert.class);
		
		// if setting a new password
		//if (!newPasscode.equals("")) {

			//byte[] salt = PasscodeEncryptionService.generateSalt();
			//byte[] encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(newPasscode, salt, iterations);
			//String saltString = null;
			//try {
				//saltString = new String(salt, "UTF-8");
			//} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			//}
			//String encryptedPasscodeString = null;
			//try {
				//encryptedPasscodeString = new String(encryptedPasscode, "UTF-8");
			// catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			//}
			//String passcodeValueToStore = iterations.toString();
			//passcodeValueToStore = passcodeValueToStore.concat(passcodeValueDelimiter);
			//passcodeValueToStore = passcodeValueToStore.concat(saltString);
			//passcodeValueToStore = passcodeValueToStore.concat(passcodeValueDelimiter);
			//passcodeValueToStore = passcodeValueToStore.concat(encryptedPasscodeString);
    	
			// execute the query to update the passcode in the database
			//databaseQueryService.runUpdateQuery("update users set hashed_password = '" + passcodeValueToStore + "' where user_email = '" + userToEditEmail + "'");

		//}

		// split the OU and Site UUIDs into an array
		String[] newOUSiteArray = selectedOUSite.split("[|]{1}");

		// update the user's info in the Users table for the user that was edited
		//databaseQueryService.runUpdateQuery("update users set first_name = '" + this.firstName + "', last_name = '" + this.lastName + "', application_role_name = '" + this.role + "', org_unit_id = " + UUID.fromString(newOUSiteArray[0]) + ", site_id = " + UUID.fromString(newOUSiteArray[1]) + ", default_lens_view_r = '" + selectedDashboard + "', active_user_ind = " + activeUserInd + ", audit_upsert = { datechanged : toUnixTimestamp(now()), changedbyusername : '" + currentUser.getEmail() + "' } where user_email = '" + userToEditEmail + "'");
		databaseQueryService.runUpdateQuery("update users set first_name = '" + this.firstName + "', last_name = '" + this.lastName + "', application_role_name = '" + this.role + "', org_unit_id = " + UUID.fromString(newOUSiteArray[0]) + ", site_id = " + UUID.fromString(newOUSiteArray[1]) + ", default_lens_view_r = '" + selectedDashboard + "', active_user_ind = " + activeUserInd + ", audit_upsert = " + applicationUtils.getAuditUpsertDBString(currentUser.getEmail()) + " where user_email = '" + userToEditEmail + "'");

		//this.conversation.end();
		
		// go back to the Manage Users page
		return "users-table?faces-redirect=true";

	}
	
}
