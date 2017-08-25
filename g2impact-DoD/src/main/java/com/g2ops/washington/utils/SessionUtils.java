package com.g2ops.washington.utils;

import com.datastax.driver.core.Session;
import com.g2ops.washington.utils.DatabaseConnectionManager;
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

	public static Session getOrgDBSession() {

		// get the servlet's context
		ServletContext ctx = getRequest().getServletContext();

		// get the user's session
		HttpSession userSession = getSession();

		// get the orgKeyspace from the session
		String orgKeyspace = (String)userSession.getAttribute("orgKeyspace");

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

	/*
	public static String getOrgKeyspace() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("orgKeyspace").toString();
	}

	public static String getUserEmail() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("userEmail").toString();
	}

	public static String getUserRole() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		return session.getAttribute("userRole").toString();
	}
	*/
	
}
