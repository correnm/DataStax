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
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.types.NavMenuItem;
import com.g2ops.impact.urm.types.OUSite;
import com.g2ops.impact.urm.types.User;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.MailService;
import com.g2ops.impact.urm.utils.SessionUtils;


@Named("usersBean")
@ViewScoped
public class UsersBean implements Serializable {


	private static final long serialVersionUID = 1L;

	// get actual class name to be logged
	static Logger logger = Logger.getLogger(UsersBean.class.getName());

	@Inject private ApplicationUtils applicationUtils;
	@Inject private UserBean currentUser;
	@Inject private OUSiteBean OUSites;
	@Inject private NavMenu analyticsNavMenu;
	
	private DatabaseQueryService databaseQueryService;
	private ResultSet rs, rs2, rs3;
	private Row row, row2, row3;
	private Session appAuthDBSession;

	private String applicationURL, currentUserEmail, userToAddEmail, userToEditEmail, cellCarrier, cellPhone, firstName, lastName, role, selectedOUSite, selectedDashboard, defaultDashboardDisplayName, lastSuccessfulLoginDateTime, queryString;
	private Boolean activeUserInd;

	private List<String> roles;
	private List<OUSite> OUSiteArrayList = new ArrayList<OUSite>();
	private List<NavMenuItem> analyticsList = new ArrayList<NavMenuItem>();
	private List<User> userList = new ArrayList<User>();

	private Boolean systemAdministratorInd = false;
	private Boolean sendNotificationEmail = true;

	private ResourceBundle messages;


	// constructor
	public UsersBean() {

	}


	// post construct method for initialization
	@PostConstruct
	public void init() {

		// get the base URL of the application
		applicationURL = applicationUtils.getApplicationBaseURL();

		// get the database connection session
		appAuthDBSession = applicationUtils.getApplAuthDBSession();

		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get the Email of the current user
		currentUserEmail = currentUser.getEmail();

		// get List of all possible roles
		queryString = "select option_values from lov_references where database_column = 'application_role_name'";
		rs = databaseQueryService.runQuery(queryString);
		row = rs.one();
		Map<String, String> roleMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);
		this.roles = new ArrayList<String>(roleMap.values());

		// get List of all possible Sites
		OUSiteArrayList = OUSites.getOUSiteArray();

		// get List of all possible Default Pages
		analyticsList = analyticsNavMenu.getAnalyticssNavMenuItemList();

		// populate the userList
		retrieveUsersData();
		
		// get the "messages" resource bundle
		messages = applicationUtils.getMessagesResourceBundle();

	}


	// method for populating a ListArray with all the users for the users table
	private void retrieveUsersData() {

		// log begin of method
		logger.warn("begin retrieveUsersData method");

		// empty the existing list to start fresh
		userList.clear();
		
		// execute the query
		queryString = "select user_email, application_role_name, default_lens_view_r, org_unit_id, site_id, first_name, last_name, active_user_ind, locked, deleted, last_successful_login_date_time "
						+ "from users";
		rs = databaseQueryService.runQuery(queryString);

		// create an Iterator from the Result Set
		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (user record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// if deleted, ignore
			if (row.getBool("deleted")) {
				continue;
			}
			
			// obtain the display name of the user's default dashboard
			for (NavMenuItem dashboardNavItem : analyticsList) {
				String userDefaultDashboard = row.getString("default_lens_view_r");
				if (dashboardNavItem.getFileName().equals(userDefaultDashboard)) {
					defaultDashboardDisplayName = dashboardNavItem.getDisplayName();
				}
			}

			queryString = "select org_unit_name from organizational_units where org_unit_id = " + row.getUUID("org_unit_id");
			rs2 = databaseQueryService.runQuery(queryString);
			row2 = rs2.one();
			
			queryString = "select site_name from sites where org_unit_id = " + row.getUUID("org_unit_id") + " and site_id = " + row.getUUID("site_id");
			rs3 = databaseQueryService.runQuery(queryString);
			row3 = rs3.one();
			
			if (row.getTimestamp("last_successful_login_date_time") != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("h:mm a 'on' MMMM d, yyyy");
				lastSuccessfulLoginDateTime = sdf.format(row.getTimestamp("last_successful_login_date_time"));
			} else {
				lastSuccessfulLoginDateTime = "no successful logins";
			}
			
			// create a user object
			User user = new User(row.getString("user_email"), row.getString("first_name"), row.getString("last_name"), row.getString("application_role_name"), defaultDashboardDisplayName, row2.getString("org_unit_name"), row3.getString("site_name"), row.getBool("active_user_ind"), row.getBool("locked"), lastSuccessfulLoginDateTime);

			// add user object to list
			userList.add(user);

		}

		logger.warn("end retrieveUsersData method");

	}

	
	// method for re-setting the data for the add user form
	public void resetUserData() {

		// log begin of method
		logger.warn("begin resetUserData method");

		// set values to their defaults
		this.userToAddEmail = "";
		this.userToEditEmail = "";
		this.firstName = "";
		this.lastName = "";
		cellCarrier = "";
		cellPhone = "";
		role = "user";
		selectedOUSite = "";
		selectedDashboard = "";
		activeUserInd = true;

		// log end of method
		logger.warn("end resetUserData method");

		//return "users-table";
		
	}


	// method for getting the data for the user being edited
	//public String retrieveUserData(String userToEditEmail) {
	public void retrieveUserData(String userToEditEmail) {

		// set bean property to value passed in
		this.userToEditEmail = userToEditEmail;

		// log begin of method
		logger.warn("begin retrieveUserData method -> this.userToEditEmail: " + this.userToEditEmail);

		// select the user's data
		queryString = "select user_email, active_user_ind, application_role_name, cell_carrier, cell_phone_number, "
						+ "default_lens_view_r, first_name, last_name, last_successful_login_date_time, org_unit_id, site_id  "
						+ "from users "
						+ "where user_email = '" + this.userToEditEmail + "'";
		rs = databaseQueryService.runQuery(queryString);

		// get the result record
		row = rs.one();
		
		// set values to what was returned by the query
		activeUserInd = row.getBool("active_user_ind");
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		cellCarrier = row.getString("cell_carrier");
		cellPhone = row.getString("cell_phone_number");
		role = row.getString("application_role_name");
		selectedOUSite = row.getUUID("org_unit_id").toString() + "|" + row.getUUID("site_id").toString();
		selectedDashboard = row.getString("default_lens_view_r");
		//lastSuccessfulLoginDateTime = row.getTimestamp("last_successful_login_date_time");

		// log end of method
		logger.warn("end retrieveUserData method");

		// navigate to the user edit page
		//return "user-edit?faces-redirect=true";
		
	}


	// method that add's the new user's data
	public void addUserActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		logger.warn("begin addUserActionControllerMethod method");

		// get the Database Query Service object for this Organization
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		
		// check that the user email is available
		rs = databaseQueryService.runQuery("select user_email from users where user_email = '" + this.userToAddEmail + "'");
		Boolean userEmailFound = false;
		for (@SuppressWarnings("unused") Row row : rs) {
			userEmailFound = true;
		}
		// if already in use, generate error message
		if (userEmailFound) {
			String messageText = "The user email " + this.userToAddEmail + " already exists!";
			FacesMessage errorMessage = new FacesMessage(messageText);
			errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage("userAddForm", errorMessage);
			//return(null);
		}

		// split the OU and Site UUIDs into an array
		String[] newOUSiteArray = selectedOUSite.split("[|]{1}");

		// insert the user's info in the Users table
		String queryString = "insert into users (user_email, cell_carrier, cell_phone_number, first_name, last_name, application_role_name, org_unit_id, site_id, default_lens_view_r, active_user_ind, send_notification_email, system_administrator_ind, audit_upsert)";
		queryString = queryString.concat(" values ('" + this.userToAddEmail
														+ "', '" + this.cellCarrier
														+ "', '" + this.cellPhone
														+ "', '" + this.firstName
														+ "', '" + this.lastName
														+ "', '" + this.role
														+ "', " + UUID.fromString(newOUSiteArray[0])
														+ ", " + UUID.fromString(newOUSiteArray[1])
														+ ", '" + this.selectedDashboard
														+ "', " + activeUserInd
														+ ", " + sendNotificationEmail
														+ ", " + systemAdministratorInd
														+ ", { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' })");
		databaseQueryService.runUpdateQuery(queryString);
		//System.out.println("in UserAdd addUserActionControllerMethod - queryString: " + queryString);

		// generate key
		UUID uuid = applicationUtils.generateRandomUUID();
		System.out.println("in UserAdd addUserActionControllerMethod - uuid: " + uuid.toString());
		
		// get the client's IP address
		String ipAddress = applicationUtils.getUserIPAddress();
		
		// insert a record into the password_reset table
		queryString = "insert into passcode_reset (organization_name, email_token, email_address, ip_address, reset_attempts, reset_complete, reset_request_datetime)";
		queryString = queryString.concat(" values ('" + currentUser.getOrgName() + "', " + uuid + ", '" + this.userToAddEmail + "', '" + ipAddress + "', 0, false, toTimestamp(now()))");
		appAuthDBSession.execute(queryString);

		//send email with link to passcode reset
		String emailSubject = messages.getString("userAddEmailSubject");
		String linkURL = "<a href='" + applicationURL + "/public/passcode-set-step1.jsf?requestID=" + uuid + "'>Set Passcode</a>";
		Object[] messageArguments = {linkURL};
		MessageFormat formatter = new MessageFormat("");
		//formatter.setLocale(currentLocale);
		formatter.applyPattern(messages.getString("userAddEmailBody"));
		String emailBody = formatter.format(messageArguments);
        try {
        	MailService.sendMessage(this.userToAddEmail, emailSubject, emailBody);
        }
        catch(MessagingException ex) {
            //statusMessage = ex.getMessage();
            ex.printStackTrace();
        }
		
		// populate the userList
		retrieveUsersData();
		
		logger.warn("end addUserActionControllerMethod method");

		// go back to the Manage Users page
		//return "users-table?faces-redirect=true";

	}


	// method that update's the edited user's data
	//public String editUserActionControllerMethod() {
	public void editUserActionControllerMethod() {

		logger.warn("begin editUserActionControllerMethod method");

		// split the OU and Site UUIDs into an array
		String[] newOUSiteArray = selectedOUSite.split("[|]{1}");

		// update the user's info in the Users table for the user that was edited
		queryString = "update users set first_name = '" + this.firstName
									+ "', last_name = '" + this.lastName
									+ "', application_role_name = '" + this.role
									// + "', cell_carrier = '" + this.cellCarrier
									+ "', cell_phone_number = '" + this.cellPhone
									+ "', org_unit_id = " + UUID.fromString(newOUSiteArray[0])
									+ ", site_id = " + UUID.fromString(newOUSiteArray[1])
									+ ", default_lens_view_r = '" + selectedDashboard
									+ "', active_user_ind = " + activeUserInd
									+ ", audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } "
									+ "where user_email = '" + userToEditEmail + "'";
		databaseQueryService.runUpdateQuery(queryString);

		// populate the userList
		retrieveUsersData();
		
		logger.warn("end editUserActionControllerMethod method");

		// go back to the Manage Users page
		//return "users-table?faces-redirect=true";

	}

	
	// method that locks the selected user's account to force a passcode reset
	public void lockAccountActionControllerMethod() {
		
		logger.warn("begin lockAccountActionControllerMethod method -> this.userToEditEmail: " + this.userToEditEmail);

		// lock the account
		queryString = "update users set locked = true, audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } where user_email = '" + userToEditEmail + "'";
		databaseQueryService.runUpdateQuery(queryString);
		
		// delete any existing passcode reset requests
		rs = appAuthDBSession.execute("select organization_name, email_token, email_address from passcode_reset");
		
		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (passcode reset record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// is it for the current requester?
			if ((this.userToEditEmail.equals(row.getObject("email_address"))) && (currentUser.getOrgName().equals(row.getObject("organization_name")))) {
				// if so, delete the record
				queryString = "delete from passcode_reset where organization_name = '" + currentUser.getOrgName() + "' and email_token = " + row.getObject("email_token");
				appAuthDBSession.execute(queryString);
			}

		}

		// create passcode reset request
		// generate token (a new UUID)
		UUID uuid = applicationUtils.generateRandomUUID();
		
		// get the client's IP address
		String ipAddress = applicationUtils.getUserIPAddress();
		
		// insert a record into the password_reset table
		queryString = "insert into passcode_reset (organization_name, email_token, email_address, ip_address, reset_attempts, reset_complete, reset_request_datetime)";
		queryString = queryString.concat(" values ('" + currentUser.getOrgName() + "', " + uuid + ", '" + this.userToEditEmail + "', '" + ipAddress + "', 0, false, toTimestamp(now()))");
		appAuthDBSession.execute(queryString);

		//send email with link to passcode reset
		String emailSubject = messages.getString("lockAccountEmailSubject");
		String linkURL = "<a href='" + applicationURL + "/public/passcode-reset-step1.jsf?requestID=" + uuid + "'>Reset Passcode</a>";
		Object[] messageArguments = {linkURL};
		MessageFormat formatter = new MessageFormat("");
		//formatter.setLocale(currentLocale);
		formatter.applyPattern(messages.getString("lockAccountEmailBody"));
		String emailBody = formatter.format(messageArguments);
        try {
        	//MailService.sendMessage(this.userEmail, emailSubject, emailBody);
        	MailService.sendMessage(this.userToEditEmail, emailSubject, emailBody);
        }
        catch(MessagingException ex) {
            //statusMessage = ex.getMessage();
            ex.printStackTrace();
        }
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// populate the userList
		retrieveUsersData();
		
		logger.warn("end lockAccountActionControllerMethod method -> this.userToEditEmail: " + this.userToEditEmail);

	}


	// method that locks the selected user's account to force a passcode reset
	public void unlockAccountActionControllerMethod() {
		
		System.out.println("begin unlockAccountActionControllerMethod -> this.userToEditEmail: " + this.userToEditEmail);

		// lock the account
		queryString = "update users set locked = false, audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } where user_email = '" + userToEditEmail + "'";
		databaseQueryService.runUpdateQuery(queryString);
		
		// populate the userList
		retrieveUsersData();
		
		System.out.println("end unlockAccountActionControllerMethod -> this.userToEditEmail: " + this.userToEditEmail);

	}


	// method that marks a user account for deletion
	public void deleteUserActionControllerMethod() {
		
		System.out.println("begin deleteUserActionControllerMethod -> this.userToEditEmail: " + this.userToEditEmail);

		// mark the account as deleted
		queryString = "update users set deleted = true, audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } where user_email = '" + userToEditEmail + "'";
		databaseQueryService.runUpdateQuery(queryString);
		
		// populate the userList
		retrieveUsersData();
		
		System.out.println("end deleteUserActionControllerMethod -> this.userToEditEmail: " + this.userToEditEmail);

	}


	// getters
	public List<User> getUsersData() {
		// return the list of users
		return userList;
	}

    public String getCurrentUserEmail() {
    	return currentUserEmail;
    }

    public String getUserToAddEmail() {
    	return this.userToAddEmail;
    }

    public String getUserToEditEmail() {
    	return this.userToEditEmail;
    }

    public String getCellCarrier() {
    	return cellCarrier;
    }

    public String getCellPhone() {
    	return cellPhone;
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


    // setters
	public void setUserToAddEmail(String userToAddEmail) {
    	this.userToAddEmail = userToAddEmail;
    }

	public void setUserToEditEmail(String userToEditEmail) {
    	this.userToEditEmail = userToEditEmail;
    }

    public void setCellCarrier(String cellCarrier) {
    	this.cellCarrier = cellCarrier;
    }

    public void setCellPhone(String cellPhone) {
    	this.cellPhone = cellPhone;
    	this.cellPhone = this.cellPhone.replaceAll("[^0-9]", "");
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
