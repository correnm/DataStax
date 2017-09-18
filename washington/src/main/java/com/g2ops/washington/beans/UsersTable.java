package com.g2ops.washington.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.washington.types.User;
import com.g2ops.washington.utils.DatabaseQueryService;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@ViewScoped
public class UsersTable implements Serializable {

	private static final long serialVersionUID = 1L;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Iterator<Row> iterator;
	private User user;
	private List<User> userList = new ArrayList<User>();

	public List<User> getUsersData() {

		populateUsersData();

		iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			user = new User(row.getString("user_email"), row.getString("first_name"), row.getString("last_name"), row.getString("application_role_name"), row.getString("default_lens_view_r"), row.getBool("system_administrator_ind"));
			userList.add(user);
		}
		
		return userList;

	}

	private void populateUsersData () {

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();
		
		// get the Database Query Service instance from the user's session
		databaseQueryService = (DatabaseQueryService)userSession.getAttribute("databaseQueryService");
		
		// execute the query
		rs = databaseQueryService.runQuery("select user_email, application_role_name, default_lens_view_r, first_name, last_name, system_administrator_ind from users");

	}

}
