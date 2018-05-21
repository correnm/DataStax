package com.g2ops.impact.urm.utils;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, March 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 */

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.datastax.driver.core.Session;

import java.io.Serializable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Named("applicationUtils")
@ApplicationScoped
public class ApplicationUtils implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pattern singleNumberPattern, singleUpperCasePattern, singleLowerCasePattern, singleSpecialCharacterPattern;
	private Matcher passcodeMatcher;

	FacesContext facesContext = FacesContext.getCurrentInstance();

	private final String applicationBaseURL = facesContext.getExternalContext().getInitParameter("application_URL");


	// constructor
	public ApplicationUtils() {

		// compile regular expressions used to validate user passcodes
		singleNumberPattern = Pattern.compile("[0-9]+");
		singleUpperCasePattern = Pattern.compile("[A-Z]+");
		singleLowerCasePattern = Pattern.compile("[a-z]+");
		singleSpecialCharacterPattern = Pattern.compile("[!@#$&*]+");

	}


	// method to return the application's base URL
	public String getApplicationBaseURL() {
		return applicationBaseURL;
	}


	// method to validate a new user passcode
	public Boolean meetsPasscodeRequirements(String passcodeToCheck) {

		// check for at least one number
		passcodeMatcher = singleNumberPattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing a number ***");
			return false;
		}

		// check for at least one upper case letter
		passcodeMatcher = singleUpperCasePattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing an upper case letter ***");
			return false;
		}

		// check for at least one lower case letter
		passcodeMatcher = singleLowerCasePattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing a lower case letter ***");
			return false;
		}

		// check for at least one special character
		passcodeMatcher = singleSpecialCharacterPattern.matcher(passcodeToCheck);
		if (!passcodeMatcher.find()) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is missing a special character ***");
			return false;
		}

		// check that the new passcode is at least eight characters in length and doesn't contain disallowed characters
		if (!Pattern.matches("[0-9A-Za-z!@#$&*]{8,}", passcodeToCheck)) {
			System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " is not at least 8 characters long or contains disallowed characters ***");
			return false;
		}

		// if got this far, then passcode must meet requirements
		System.out.println("*** in ApplicationUtils meetsPasscodeRequirements - passcode " + passcodeToCheck + " meets all requirements ***");
		return true;

	}


	// method that generates a random UUID
	public UUID generateRandomUUID() {
		
		return UUID.randomUUID();
		
	}


	// method that returns the Servlet's Context
	public ServletContext getServletContext() {

		return SessionUtils.getRequest().getServletContext();

	}


	// method that returns the user's IP Address
	public String getUserIPAddress() {

		HttpServletRequest request = SessionUtils.getRequest();
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		return ipAddress;

	}


	// method that returns the DB Session for the appl_auth keyspace
	public Session getApplAuthDBSession() {

		// get the servlet's context
		ServletContext ctx = getServletContext();

		// get the database connection to the application authorization keyspace from the servlet's context 
		DatabaseConnectionManager appAuthDBConnection = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// get the database connection session
		return appAuthDBConnection.getSession();

	}

	
	// method that returns the audit upsert string
	public String getAuditUpsertDBString(String userName) {

		return "{ datechanged : toUnixTimestamp(now()), changedbyusername : '" + userName + "' }";

	}


}
