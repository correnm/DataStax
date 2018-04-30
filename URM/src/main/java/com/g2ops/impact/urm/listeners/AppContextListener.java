package com.g2ops.impact.urm.listeners;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.g2ops.impact.urm.utils.DatabaseConnectionManager;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.EncryptionDecryptionService;

import io.netty.util.internal.InternalThreadLocalMap;

public class AppContextListener implements ServletContextListener {

	// get actual class name to be logged
	static Logger logger = Logger.getLogger(AppContextListener.class.getName());


	// method that executes when the application is starting up
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// log start of method
		logger.info("start of contextInitialized method");

		// get the servlet's context
		ServletContext ctx = servletContextEvent.getServletContext();

		// read the database connection parameters from the web.xml file
		String uName = ctx.getInitParameter("db_USERNAME");
		String pWord = ctx.getInitParameter("db_PASSWORD");
		String[] cPoints = ctx.getInitParameter("db_CONTACT_POINTS").split(",");
		String port = ctx.getInitParameter("db_PORT");
		String keyspace = ctx.getInitParameter("db_KEYSPACE");

		logger.info("read DB related context init params");

		// decrypt the password
		try {
			pWord = EncryptionDecryptionService.decrypt(pWord);
		} catch (Exception e) {
			// log error and exit application
			logger.fatal("DB password could NOT be decrypted for Keyspace: " + keyspace);
			System.exit(1);
		}
		
		// database connection object
		DatabaseConnectionManager dbManager = null;
		
		// database query service object
		DatabaseQueryService dbQueryService = null;
		
		// create initial database connection using parameters from the web.xml file - make 3 attempts - exit application if all attempts fail
		try {
			dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port, keyspace);
		} catch (Exception exception1) {
			logger.warn("DB connection attempt #1 failed");
			try {
				dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port, keyspace);
			} catch (Exception exception2) {
				logger.error("DB connection attempt #2 failed");
				try {
					dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port, keyspace);
				} catch (Exception exception3) {
					logger.fatal("DB connection attempt #3 failed - exiting");
					System.exit(1);
				}
			}
		}
    	
		// put the database connection into the servlet's context
		ctx.setAttribute("appAuthDBManager", dbManager);
		
		// output statement for troubleshooting
		logger.info("appl auth database connection created and saved in servlet's context");

		// get the database connection session
		Session session = dbManager.getSession();

		// create the prepared statement for selecting the organization info
		PreparedStatement preparedStatement = session.prepare("select keyspace_name from organizations where organization_name = ?");

		// put the prepared statement into the servlet's context
		ctx.setAttribute("PSOrgInfo", preparedStatement);

		// print statement for troubleshooting
		logger.info("prepared statement for selecting organization's keyspace name saved in servlet's context");

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

			// attempt to decrypt the password for logging into the database
			String decrypted_password = "";
			try {

				// decrypt password
				decrypted_password = EncryptionDecryptionService.decrypt(row.getString("encrypted_password"));

			} catch (Exception e) {

				// log error
				logger.error("DB password could NOT be decrypted for Keyspace: " + row.getString("keyspace_name"));

				// go to the next organization
				continue;

			}
			
			// attempt to create a database connection and a query service for this organization/keyspace
			DatabaseConnectionManager orgDBConnection = null;
			try {

				// create database connection
				orgDBConnection = new DatabaseConnectionManager(row.getString("username"), decrypted_password, cPoints, port, row.getString("keyspace_name"));

			} catch (Exception e) {

				// log error
				logger.error("DB connection could NOT be created for: " + row.getString("keyspace_name"));

				// go to the next organization
				continue;

			}

			// create database query service object
			dbQueryService = new DatabaseQueryService(orgDBConnection);
			
			// add database connection to HashMap
			DBConnectionsHashMap.put(row.getString("keyspace_name"), orgDBConnection);

			// print statement for troubleshooting
			logger.info("DB connection created and put into HashMap for Keyspace: " + row.getString("keyspace_name"));

			// add database query service to HashMap
			DBQueryServicesHashMap.put(row.getString("keyspace_name"), dbQueryService);

			// print statement for troubleshooting
			logger.info("DatabaseQueryService object created and put into HashMap for Keyspace: " + row.getString("keyspace_name"));

		}

		// put hash map of organization database connections into the servlet's context
		ctx.setAttribute("OrgDBConnections", DBConnectionsHashMap);

		// put hash map of organization database query services into the servlet's context
		ctx.setAttribute("OrgDBQueryServices", DBQueryServicesHashMap);

		// log end of method
		logger.info("end of contextInitialized method");

	} // end of contextInitialized method


	// method that executes when the application is shutting down
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		// log start of method
		logger.info("start of contextDestroyed method");

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
			logger.info("DB session closed for Keyspace: " + orgKeySpace);

			// close the organization database connection
			dbManager.closeConnection();

			// print statement for troubleshooting
			logger.info("DB connection closed for Keyspace: " + orgKeySpace);

		}
		
		// get the initial database connection from the context
		dbManager = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// close the initial database connection session
		dbManager.closeSession();
		
		// print statement for troubleshooting
		logger.info("DB session closed for Keyspace: appl_auth");

		// close the initial database connection
		dbManager.closeConnection();

		// print statement for troubleshooting
		logger.info("DB connection closed for Keyspace: appl_auth");

		// attempt to destroy all threads
		InternalThreadLocalMap.remove();
		InternalThreadLocalMap.destroy();

		// sleep a bit to allow all threads to die
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// log end of method
		logger.info("end of contextDestroyed method");

	} // end of contextDestroyed method


}
