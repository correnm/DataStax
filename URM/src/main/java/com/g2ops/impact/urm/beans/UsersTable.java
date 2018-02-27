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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.impact.urm.types.NavMenuItem;
import com.g2ops.impact.urm.types.User;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("usersTable")
@RequestScoped
public class UsersTable {

	@Inject private UserBean currentUser;
	@Inject private NavMenu dashboardsNavMenu;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs, rs2, rs3;
	private Row row, row2, row3;

	String defaultDashboardDisplayName = "";

	private List<User> userList = new ArrayList<User>();
	private List<NavMenuItem> dashboardsList = new ArrayList<NavMenuItem>();

	public UsersTable() {
		
	}
	
	@PostConstruct
	public void init() {

		// get List of all Dashboards
		dashboardsList = dashboardsNavMenu.getDashboardsNavMenuItemList();

		// get the Database Query Service object for this Org
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		
		// execute the query
		rs = databaseQueryService.runQuery("select user_email, application_role_name, default_lens_view_r, org_unit_id, site_id, first_name, last_name, active_user_ind from users");

		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (user record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// obtain the display name of the user's default dashboard
			for (NavMenuItem dashboardNavItem : dashboardsList) {
				if (dashboardNavItem.getFileName().equals(row.getString("default_lens_view_r"))) {
					defaultDashboardDisplayName = dashboardNavItem.getDisplayName();
				}
			}

			rs2 = databaseQueryService.runQuery("select org_unit_name from organizational_units where org_unit_id = " + row.getUUID("org_unit_id"));
			row2 = rs2.one();
			
			rs3 = databaseQueryService.runQuery("select site_name from sites where org_unit_id = " + row.getUUID("org_unit_id") + " and site_id = " + row.getUUID("site_id"));
			row3 = rs3.one();
			
			User user = new User(row.getString("user_email"), row.getString("first_name"), row.getString("last_name"), row.getString("application_role_name"), defaultDashboardDisplayName, row2.getString("org_unit_name"), row3.getString("site_name"), row.getBool("active_user_ind"));
			userList.add(user);
		}
		
	}

	public List<User> getUsersData() {

		return userList;
		
	}

}
