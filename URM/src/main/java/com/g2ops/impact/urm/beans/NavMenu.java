package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, December 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 */

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.g2ops.impact.urm.types.NavMenuItem;

import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

@Named("navMenu")
@SessionScoped
public class NavMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private UserBean currentUser;

	private ResultSet rs;
	private Iterator<Row> iterator;
	private NavMenuItem navMenuItem;

	private List<NavMenuItem> administratorNavMenuItemList = new ArrayList<NavMenuItem>();
	private List<NavMenuItem> dashboardsNavMenuItemList = new ArrayList<NavMenuItem>();
	private List<NavMenuItem> userNavMenuItemList = new ArrayList<NavMenuItem>();

	public NavMenu() {

		System.out.println("*** in NavMenu constructor ***");
		
	}
	
	@PostConstruct
	public void init() {

		System.out.println("*** in PostConstruct init method ***");
		System.out.println("orgKeyspace is: " + currentUser.getOrgKeyspace());
		
		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get the menu items

		// execute the query
		rs = databaseQueryService.runQuery("select menu_name, module_name, file_name, menu_module_order from application_modules");

		// create an iterator out of the query results
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {

			Row row = iterator.next();

			// create navigation menu item object
			navMenuItem = new NavMenuItem(row.getString("module_name"), 
					row.getString("file_name"),
					row.getInt("menu_module_order"));

			// save each navigation menu item object to the appropriate list
			switch (row.getString("menu_name")) {
				case "Administrator":
					administratorNavMenuItemList.add(navMenuItem);
					break;
				case "Dashboards":
					dashboardsNavMenuItemList.add(navMenuItem);
					break;
				case "User":
					userNavMenuItemList.add(navMenuItem);
					break;
			}

			// sort the lists
			administratorNavMenuItemList.sort((NavMenuItem i1, NavMenuItem i2)->i1.getDisplayOrder()-i2.getDisplayOrder());
			dashboardsNavMenuItemList.sort((NavMenuItem i1, NavMenuItem i2)->i1.getDisplayOrder()-i2.getDisplayOrder());
			userNavMenuItemList.sort((NavMenuItem i1, NavMenuItem i2)->i1.getDisplayOrder()-i2.getDisplayOrder());

		}
			
		System.out.println("*** end PostConstruct init method ***");
		
	}

	public List<NavMenuItem> getAdministratorNavMenuItemList() {
		return administratorNavMenuItemList;
	}
	
	public List<NavMenuItem> getDashboardsNavMenuItemList() {
		return dashboardsNavMenuItemList;
	}
	
	public List<NavMenuItem> getUserNavMenuItemList() {
		return userNavMenuItemList;
	}

}
