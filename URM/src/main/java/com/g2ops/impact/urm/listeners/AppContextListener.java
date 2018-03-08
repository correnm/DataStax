package com.g2ops.impact.urm.listeners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.g2ops.impact.urm.utils.DatabaseConnectionManager;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.EncryptionDecryptionService;

public class AppContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// get the servlet's context
		ServletContext ctx = servletContextEvent.getServletContext();

		// read the database parameters from the web.xml file
		String uName = ctx.getInitParameter("db_USERNAME");
		String pWord = ctx.getInitParameter("db_PASSWORD");
		String[] cPoints = ctx.getInitParameter("db_CONTACT_POINTS").split(",");
		String port = ctx.getInitParameter("db_PORT");
		String keyspace = ctx.getInitParameter("db_KEYSPACE");

		// database connection object
		DatabaseConnectionManager dbManager = null;
		
		// database query service object
		DatabaseQueryService dbQueryService = null;
		
		// create appl auth database connection using parameters from the web.xml file - make 3 attempts - exit application if all attempts fail
		try {
			dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port, keyspace);
		} catch (Exception exception1) {
			System.out.println("appl auth database connection attempt #1 - caught exception: " + exception1);
			try {
				dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port, keyspace);
			} catch (Exception exception2) {
				System.out.println("appl auth database connection attempt #2 - caught exception: " + exception2);
				try {
					dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port, keyspace);
				} catch (Exception exception3) {
					System.out.println("appl auth database connection attempt #3 - caught exception: " + exception3);
					System.exit(1);
				}
			}
		}
    	
		// put the app auth database connection into the servlet's context
		ctx.setAttribute("appAuthDBManager", dbManager);

		// print statement for troubleshooting
		System.out.println("appl auth database connection created and saved in servlet's context");

		// get the app auth database connection session
		Session session = dbManager.getSession();

		// create the prepared statement for selecting the organization info
		PreparedStatement preparedStatement = session.prepare("select keyspace_name from organizations where organization_name = ?");

		// put the prepared statement into the servlet's context
		ctx.setAttribute("PSOrgInfo", preparedStatement);

		// print statement for troubleshooting
		System.out.println("prepared statement for selecting organization keyspace name saved in servlet's context");

		// create hash map for storing the organization specific database connections (key is keyspace name)
		Map<String, DatabaseConnectionManager> DBConnectionsHashMap = new HashMap<String, DatabaseConnectionManager>();

		// create hash map for storing the organization specific database query services (key is keyspace name)
		Map<String, DatabaseQueryService> DBQueryServicesHashMap = new HashMap<String, DatabaseQueryService>();

		// create the prepared statement for selecting the organizations database connection info
		preparedStatement = session.prepare("select keyspace_name, username, encrypted_password from organizations");

		// create bound statement
		BoundStatement boundStatement = preparedStatement.bind();
		
		// execute the query
		ResultSet rs = session.execute(boundStatement);

		// get the query results as an iterator
		Iterator<Row> rows = rs.iterator();

		// loop through results and create connections
		while (rows.hasNext()) {

			// get each organization's data
			Row row = rows.next();

			
			// *** for testing ***
			String decrypted_password = "";
			System.out.println("keyspace_name: " + row.getString("keyspace_name") + " - password: " + row.getString("encrypted_password"));
			/*
			try {
				encrypted_password = EncryptionDecryptionService.encrypt(row.getString("encrypted_password"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("encrypted_password: " + encrypted_password);
			*/
			try {
				decrypted_password = EncryptionDecryptionService.decrypt(row.getString("encrypted_password"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("decrypted_password: " + decrypted_password);
			// *** end for testing ***
			
			
			// create database connection
			//DatabaseConnectionManager orgDBConnection = new DatabaseConnectionManager(row.getString("username"), row.getString("encrypted_password"), cPoints, port, row.getString("keyspace_name"));
			DatabaseConnectionManager orgDBConnection = new DatabaseConnectionManager(row.getString("username"), decrypted_password, cPoints, port, row.getString("keyspace_name"));

			// create database query service object
			dbQueryService = new DatabaseQueryService(orgDBConnection);
			
			// add database connection to HashMap
			DBConnectionsHashMap.put(row.getString("keyspace_name"), orgDBConnection);

			// print statement for troubleshooting
			System.out.println("organization Database connection created and put into HashMap for: " + row.getString("keyspace_name"));
			
			// add database query service to HashMap
			DBQueryServicesHashMap.put(row.getString("keyspace_name"), dbQueryService);

			// print statement for troubleshooting
			System.out.println("organization Database Query Service created and put into HashMap for: " + row.getString("keyspace_name"));
			
		}

		// put hash map of organization database connections into the servlet's context
		ctx.setAttribute("OrgDBConnections", DBConnectionsHashMap);

		// put hash map of organization database query services into the servlet's context
		ctx.setAttribute("OrgDBQueryServices", DBQueryServicesHashMap);

		// print statement for troubleshooting
		System.out.println("organization Database connections created, put into a HashMap and saved in servlet's context");
		
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		// get the Servlet's Context
		ServletContext ctx = servletContextEvent.getServletContext();

		// local object
		DatabaseConnectionManager dbManager = null;
		
		// get the hash map of organization database connections
		@SuppressWarnings("unchecked")
		Map<String, DatabaseConnectionManager> DBConnectionsHashMap = (Map<String, DatabaseConnectionManager>)ctx.getAttribute("OrgDBConnections");

		// iterate through hash map keys
		for (String orgKeySpace : DBConnectionsHashMap.keySet()) {

			// get the organization database connection
			dbManager = DBConnectionsHashMap.get(orgKeySpace);

			// close the organization database connection session
			dbManager.closeSession();
			
			// print statement for troubleshooting
			System.out.println("closed DB session for org: " + orgKeySpace);

			// close the organization database connection
			dbManager.closeConnection();

			// print statement for troubleshooting
			System.out.println("closed DB connection for org: " + orgKeySpace);

		}
		
		// get the app auth database connection from the context
		dbManager = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// close the app auth database connection session
		dbManager.closeSession();
		
		// print statement for troubleshooting
		System.out.println("closed DB session for appl_auth");

		// close the app auth database connection
		dbManager.closeConnection();

		// print statement for troubleshooting
		System.out.println("closed DB connection for appl_auth");

	}

}
