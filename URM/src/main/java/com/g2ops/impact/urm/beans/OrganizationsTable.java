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
import javax.inject.Named;
import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.types.Organization;
import com.g2ops.impact.urm.utils.DatabaseConnectionManager;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("organizationsTable")
@RequestScoped
public class OrganizationsTable {

	private ResultSet rs;
	private Row row;

	private List<Organization> organizationList = new ArrayList<Organization>();

	public OrganizationsTable() {
		
	}
	
	@PostConstruct
	public void init() {

		// get the servlet's context
		ServletContext ctx = SessionUtils.getRequest().getServletContext();

		// get the database connection to the application authorization keyspace from the servlet's context 
		DatabaseConnectionManager appAuthDBConnection = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// get the database connection session
		Session appAuthDBSession = appAuthDBConnection.getSession();

		// execute the query
		rs = appAuthDBSession.execute("select organization_name, keyspace_name, username, encrypted_password, country_name, industry from organizations");
		
		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (user record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// instantiate an Organization
			Organization organization = new Organization(row.getString("organization_name"), row.getString("keyspace_name"), row.getString("username"), row.getString("encrypted_password"), row.getString("country_name"), row.getString("industry"));

			// add Organization to list
			organizationList.add(organization);

		} // end while loop
		
	} // end init method

	public List<Organization> getOrganizationsData() {

		return organizationList;
		
	}

}
