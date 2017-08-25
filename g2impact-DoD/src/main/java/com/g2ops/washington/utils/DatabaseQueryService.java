package com.g2ops.washington.utils;

import com.g2ops.washington.utils.DatabaseConnectionManager;
import com.g2ops.washington.utils.SessionUtils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class DatabaseQueryService {

	public ResultSet RunQuery(String query) {

		// get the servlet's context
		ServletContext ctx = SessionUtils.getRequest().getServletContext();

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();

		// get the orgKeyspace from the session
		String orgKeyspace = (String)userSession.getAttribute("orgKeyspace");

		// get the Hash Map of the Organization database connections
		@SuppressWarnings("unchecked")
		Map<String, DatabaseConnectionManager> DBConnectionsHashMap = (Map<String, DatabaseConnectionManager>) ctx.getAttribute("OrgDBConnections");
		
		// get the database connection for this user's organization
		DatabaseConnectionManager orgDBConnection = DBConnectionsHashMap.get(orgKeyspace);
		
		// get the database connection session
		Session DBSession = orgDBConnection.getSession();

		// execute the query
		ResultSet rs = DBSession.execute(query);

		// return the result set
		return rs;

	}
	
}
