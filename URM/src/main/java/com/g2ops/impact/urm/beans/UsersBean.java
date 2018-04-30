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

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.impact.urm.types.NavMenuItem;
import com.g2ops.impact.urm.types.OUSite;
import com.g2ops.impact.urm.types.User;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("usersBean")
@SessionScoped
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// get actual class name to be logged
	static Logger logger = Logger.getLogger(UsersBean.class.getName());

	@Inject private UserBean currentUser;
	@Inject private OUSiteBean OUSites;
	@Inject private NavMenu dashboardsNavMenu;
	
	private DatabaseQueryService databaseQueryService;
	private ResultSet rs, rs2, rs3;
	private Row row, row2, row3;

	private String userToEditEmail, firstName, lastName, role, selectedOUSite, selectedDashboard, defaultDashboardDisplayName;
	private Boolean activeUserInd;

	private List<String> roles;
	private List<OUSite> OUSiteArrayList = new ArrayList<OUSite>();
	private List<NavMenuItem> dashboardsList = new ArrayList<NavMenuItem>();
	private List<User> userList = new ArrayList<User>();

	// constructor
	public UsersBean() {

	}

	// post construct method for initialization
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

	
	// method for populating a ListArray with all the users for the users table
	public List<User> getUsersData() {

		// empty the existing list to start fresh
		userList.clear();
		
		// execute the query
		rs = databaseQueryService.runQuery("select user_email, application_role_name, default_lens_view_r, org_unit_id, site_id, first_name, last_name, active_user_ind from users");

		// create an Iterator from the Result Set
		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (user record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// obtain the display name of the user's default dashboard
			for (NavMenuItem dashboardNavItem : dashboardsList) {
				String userDefaultDashboard = row.getString("default_lens_view_r");
				if (dashboardNavItem.getFileName().equals(userDefaultDashboard)) {
					defaultDashboardDisplayName = dashboardNavItem.getDisplayName();
				}
			}

			rs2 = databaseQueryService.runQuery("select org_unit_name from organizational_units where org_unit_id = " + row.getUUID("org_unit_id"));
			row2 = rs2.one();
			
			rs3 = databaseQueryService.runQuery("select site_name from sites where org_unit_id = " + row.getUUID("org_unit_id") + " and site_id = " + row.getUUID("site_id"));
			row3 = rs3.one();
			
			// create a user object
			User user = new User(row.getString("user_email"), row.getString("first_name"), row.getString("last_name"), row.getString("application_role_name"), defaultDashboardDisplayName, row2.getString("org_unit_name"), row3.getString("site_name"), row.getBool("active_user_ind"));

			// add user object to list
			userList.add(user);

		}

		// return the list of users
		return userList;
		
	}

	
	// method for getting the data for the user being edited
	public String retrieveUserData(String userToEditEmail) {

		// log start of method
		logger.info("start of retrieveUserData method");

		// set bean property to value passed in
		this.userToEditEmail = userToEditEmail;

		// select the user's data
		rs = databaseQueryService.runQuery("select user_email, application_role_name, org_unit_id, site_id, default_lens_view_r, first_name, last_name, active_user_ind from users where user_email = '" + this.userToEditEmail + "'");

		// get the result record
		row = rs.one();
		
		// set values to what was returned by the query
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		role = row.getString("application_role_name");
		selectedOUSite = row.getUUID("org_unit_id").toString() + "|" + row.getUUID("site_id").toString();
		selectedDashboard = row.getString("default_lens_view_r");
		activeUserInd = row.getBool("active_user_ind");

		// log end of method
		logger.info("end of retrieveUserData method");

		// navigate to the user edit page
		return "user-edit?faces-redirect=true";
		
	}

	
	// method that update's the edited user's data
	public String editUserActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// split the OU and Site UUIDs into an array
		String[] newOUSiteArray = selectedOUSite.split("[|]{1}");

		// update the user's info in the Users table for the user that was edited
		databaseQueryService.runUpdateQuery("update users set first_name = '" + this.firstName + "', last_name = '" + this.lastName + "', application_role_name = '" + this.role + "', org_unit_id = " + UUID.fromString(newOUSiteArray[0]) + ", site_id = " + UUID.fromString(newOUSiteArray[1]) + ", default_lens_view_r = '" + selectedDashboard + "', active_user_ind = " + activeUserInd + ", audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } where user_email = '" + userToEditEmail + "'");

		// go back to the Manage Users page
		return "users-table?faces-redirect=true";

	}
	

	// getters
    public String getUserToEditEmail() {
    	return userToEditEmail;
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


    // setters
	public void setUserToEditEmail(String userToEditEmail) {
    	this.userToEditEmail = userToEditEmail;
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

}
