package com.g2ops.washington.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.g2ops.washington.utils.DatabaseConnectionManager;

@WebListener
public class AppContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {

		// get the servlet's context
		ServletContext ctx = servletContextEvent.getServletContext();

		// read the init parameters from the web.xml file
		String uName = ctx.getInitParameter("db_USERNAME");
		String pWord = ctx.getInitParameter("db_PASSWORD");
		String cPoint1 = ctx.getInitParameter("db_CONTACT_POINT_1");
		String cPoint2 = ctx.getInitParameter("db_CONTACT_POINT_2");
		String cPoint3 = ctx.getInitParameter("db_CONTACT_POINT_3");
		String cPoint4 = ctx.getInitParameter("db_CONTACT_POINT_4");
		String[] cPoints = {cPoint1, cPoint2, cPoint3, cPoint4};
		String port = ctx.getInitParameter("db_PORT");

		// create database connection from init parameters
		DatabaseConnectionManager dbManager = new DatabaseConnectionManager(uName, pWord, cPoints, port);
    	
		// put the database connection into the servlet's context
		ctx.setAttribute("DBManager", dbManager);

		// print statement for troubleshooting
		System.out.println("Database connection initialized for Application.");

	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		// get the Servlet's Context
		ServletContext ctx = servletContextEvent.getServletContext();

		// get the database connection from the context
		DatabaseConnectionManager dbManager = (DatabaseConnectionManager) ctx.getAttribute("DBManager");
		
		// close the database connection
		dbManager.closeConnection();

		// print statement for troubleshooting
		System.out.println("Database connection closed for Application.");

	}

}
