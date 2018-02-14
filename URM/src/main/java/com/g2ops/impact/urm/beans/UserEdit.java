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

import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.g2ops.impact.urm.types.OrgUnit;
import com.g2ops.impact.urm.types.Site;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.PasscodeEncryptionService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("userEdit")
@SessionScoped
public class UserEdit implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private UserBean currentUser;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Iterator<Row> iterator;
	private Row row;
	private String userToEditEmail, newPasscode, firstName, lastName, role, selectedOrgUnit, selectedSite, org_id;
	//private String orgUnitID, siteID, defaultLensView;
	//private Boolean systemAdministratorInd;
	private Integer iterations = 20000;
	private String passcodeValueDelimiter = "***";
	private List<String> roles;
	private List<OrgUnit> orgUnits;
	private List<Site> sites;

	// constructor
	public UserEdit() {

	}
	
	@PostConstruct
	public void init() {

		System.out.println("in UserEdit init method");

		// get the Database Query Service object for this Org
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

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
			this.orgUnits.add(new OrgUnit(row.getUUID("org_unit_id").toString(), row.getString("org_unit_name")));
			System.out.println("OU List: " + this.orgUnits);
		}

		// get List of all possible Sites
		this.sites = new ArrayList<Site>();

		// clear out the current list
		this.sites.clear();
		
		// execute the query
		//rs = databaseQueryService.runQuery("select site_id, site_name from sites where org_unit_id = " + UUID.fromString(selectedOrgUnit));

		//iterator = rs.iterator();
		
		//while (iterator.hasNext()) {
			//row = iterator.next();
			//System.out.println("Site Query Row: " + row);
			//this.sites.add(new Site(row.getUUID("site_id").toString(), row.getString("site_name")));
		//}


		// get List of all possible Default Pages

	}
	
	public void retrieveUserData() {

		System.out.println("userToEditEmail: " + userToEditEmail);

		// select the user's data
		rs = databaseQueryService.runQuery("select user_email, application_role_name, org_unit_id, site_id, default_lens_view_r, first_name, last_name, system_administrator_ind from users where user_email = '" + userToEditEmail + "'");

		// get the result record
		row = rs.one();
		
		// set values to what was returned by the query
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		role = row.getString("application_role_name");
		//defaultLensView = row.getString("default_lens_view_r");
		//orgUnitID = row.getUUID("org_unit_id").toString();
		//siteID = row.getUUID("site_id").toString();
		//systemAdministratorInd = row.getBool("system_administrator_ind");
		
	}

    public String getUserToEditEmail() {
    	return userToEditEmail;
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

    public void setUserToEditEmail(String userToEditEmail) {
		System.out.println("in UserEdit setUserToEditEmail method");
    	this.userToEditEmail = userToEditEmail;
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

		System.out.println("*** BEGIN editUserActionControllerMethod ***");

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		
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
			databaseQueryService.runUpdateQuery("update users set hashed_password = '" + passcodeValueToStore + "' where user_email = '" + userToEditEmail + "'");

		}

		// update the user's info in the Users table for the user that was edited
		databaseQueryService.runUpdateQuery("update users set first_name = '" + this.firstName + "', last_name = '" + this.lastName + "', application_role_name = '" + this.role + "', audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } where user_email = '" + userToEditEmail + "'");

		System.out.println("*** END editUserActionControllerMethod ***");

		return "users-table";

	}
	
}
