package com.g2ops.washington.beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.g2ops.washington.types.OrgUnit;
import com.g2ops.washington.types.Site;
import com.g2ops.washington.types.User;
import com.g2ops.washington.utils.DatabaseQueryService;
import com.g2ops.washington.utils.PasscodeEncryptionService;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@ViewScoped
public class UserEdit implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;
	private Iterator<Row> iterator;
	private String userEmail, newPasscode, firstName, lastName, role, orgUnitID, siteID, defaultLensView, selectedOrgUnit, selectedSite, org_id;
	private Boolean systemAdministratorInd;
	private Integer iterations = 20000;
	private String passcodeValueDelimiter = "***";
	private List<String> roles;
	private List<OrgUnit> orgUnits;
	private List<Site> sites;

	// constructor
	public UserEdit() throws NoSuchAlgorithmException, InvalidKeySpecException {

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();
		
		// get the Database Query Service instance from the user's session
		databaseQueryService = (DatabaseQueryService)userSession.getAttribute("databaseQueryService");
		
		// get the default OU and Site from the user's session
		selectedOrgUnit = (String)userSession.getAttribute("currentOU");
		selectedSite = (String)userSession.getAttribute("currentSite");

		// get the org_id for using in queries (temporary thing)
		String orgKeyspace = (String)userSession.getAttribute("orgKeyspace");

		org_id = "df72e1dd-a385-4ab9-ba30-70b9df8d539b";

		// get List of all possible roles
		this.roles = new ArrayList<String>();
		this.roles.add("admin");
		this.roles.add("user");

		// get List of all possible OUs
		rs = databaseQueryService.runQuery("select org_unit_id, org_unit_name from organizational_units");
		iterator = rs.iterator();
		
		this.orgUnits = new ArrayList<OrgUnit>();
		while (iterator.hasNext()) {
			row = iterator.next();
			System.out.println("OU Query Row: " + row);
			this.orgUnits.add(new OrgUnit(row.getUUID("org_unit_id").toString(), row.getString("org_unit_name")));
			System.out.println("OU List: " + this.orgUnits);
		}

		// get List of all possible Sites
		this.sites = new ArrayList<Site>();

		// clear out the current list
		this.sites.clear();
		
		// execute the query
		rs = databaseQueryService.runQuery("select site_id, site_name from sites where org_id = " + UUID.fromString(org_id) + " and org_unit_id = " + UUID.fromString(selectedOrgUnit));

		iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			row = iterator.next();
			System.out.println("Site Query Row: " + row);
			this.sites.add(new Site(row.getUUID("site_id").toString(), row.getString("site_name")));
		}


		// get List of all possible Default Pages
		

		// get the email address of the user to edit from the URL parameter
		HttpServletRequest request = SessionUtils.getRequest();
		userEmail = request.getParameter("userEmail");
		
		// select the user's data
		rs = databaseQueryService.runQuery("select user_email, application_role_name, org_unit_id, site_id, default_lens_view_r, first_name, last_name, system_administrator_ind from users where user_email = '" + userEmail + "'");

		// get the result record
		row = rs.one();

		// set values to what was returned by the query
		userEmail = row.getString("user_email");
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		role = row.getString("application_role_name");
		defaultLensView = row.getString("default_lens_view_r");
		orgUnitID = row.getUUID("org_unit_id").toString();
		siteID = row.getUUID("site_id").toString();
		systemAdministratorInd = row.getBool("system_administrator_ind");
		
	}

    public String getUserEmail() {
    	return userEmail;
    }

    public String getNewPasscode() {
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

    public void setNewPasscode(String newPasscode) {
    	this.newPasscode = newPasscode;
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

	public String editUserActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		//System.out.println("*** editUserActionControllerMethod ***");

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();

		// get the User instance from the user's session
		User user = (User) userSession.getAttribute("user");
		
		// if setting a new password
		if (!newPasscode.equals("")) {

			byte[] salt = PasscodeEncryptionService.generateSalt();
			byte[] encryptedPasscode = PasscodeEncryptionService.getEncryptedPasscode(newPasscode, salt, iterations);
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
    	
			// execute the query to update the passcode in the database
			rs = databaseQueryService.runQuery("update users set hashed_password = '" + passcodeValueToStore + "' where user_email = '" + userEmail + "'");

			//System.out.println("*** editUserActionControllerMethod ***");

		}

		// update the user's info in the Users table for the user that was edited
		rs = databaseQueryService.runQuery("update users set first_name = '" + this.firstName + "', last_name = '" + this.lastName + "', application_role_name = '" + this.role + "', audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + user.getUserEmail() + "' } where user_email = '" + userEmail + "'");

		return "users-table";

	}
	
}
