package com.g2ops.impact.urm.utils;

import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.DatabaseConnectionManager;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	public static Session getOrgDBSession(String orgKeyspace) {

		// get the servlet's context
		ServletContext ctx = getRequest().getServletContext();

		// get the Hash Map of the Organization database connections
		@SuppressWarnings("unchecked")
		Map<String, DatabaseConnectionManager> DBConnectionsHashMap = (Map<String, DatabaseConnectionManager>) ctx.getAttribute("OrgDBConnections");
		
		// get the database connection for this user's organization
		DatabaseConnectionManager orgDBConnection = DBConnectionsHashMap.get(orgKeyspace);
		
		// get the database connection session
		Session DBSession = orgDBConnection.getSession();
		
		// return the Org's DB Session
		return DBSession;

	}

	public static DatabaseQueryService getOrgDBQueryService(String orgKeyspace) {

		// get the servlet's context
		ServletContext ctx = getRequest().getServletContext();

		// get the Hash Map of the Organization database query services
		@SuppressWarnings("unchecked")
		Map<String, DatabaseQueryService> DBQueryServicesHashMap = (Map<String, DatabaseQueryService>) ctx.getAttribute("OrgDBQueryServices");
		
		// get the database query service for this user's organization
		DatabaseQueryService orgDBQueryService = DBQueryServicesHashMap.get(orgKeyspace);
		
		// return the Org's DB Query Service
		return orgDBQueryService;

	}

}
