package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@ViewScoped
public class UserEdit {

	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private String userEmail, userName, firstName, lastName, applicationRoleName, defaultLensView;
	private Boolean systemAdministratorInd;

	public UserEdit() {

		// execute the query
		rs = dbSession.execute("select user_email, application_role_name, default_lens_view_r, first_name, last_name, user_name, system_administrator_ind from users where user_email = 'john.reddy@g2-ops.com'");

		// get the result values
		row = rs.one();

		// set values to what was returned by the query
		userEmail = row.getString("user_email");
		userName = row.getString("user_name");
		firstName = row.getString("first_name");
		lastName = row.getString("last_name");
		applicationRoleName = row.getString("application_role_name");
		defaultLensView = row.getString("default_lens_view_r");
		systemAdministratorInd = row.getBool("system_administrator_ind");
		
	}

    public String getUserEmail() {
    	return userEmail;
    }

    public String getFirstName() {
    	return firstName;
    }

    public String getLastName() {
    	return lastName;
    }

}
