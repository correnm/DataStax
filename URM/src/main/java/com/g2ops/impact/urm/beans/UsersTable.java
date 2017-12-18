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

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.impact.urm.types.User;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("usersTable")
@RequestScoped
public class UsersTable {

	public UsersTable() {
		
	}
	
	public List<User> getUsersData() {

		ResultSet rs = populateUsersData();

		Iterator<Row> iterator = rs.iterator();
		List<User> userList = new ArrayList<User>();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			User user = new User(row.getString("user_email"), row.getString("first_name"), row.getString("last_name"), row.getString("application_role_name"), row.getString("default_lens_view_r"), row.getBool("system_administrator_ind"));
			userList.add(user);
		}
		
		return userList;

	}

	private ResultSet populateUsersData () {

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService();
		
		// execute the query
		ResultSet rs = databaseQueryService.runQuery("select user_email, application_role_name, default_lens_view_r, first_name, last_name, system_administrator_ind from users");

		return rs;
		
	}

}
