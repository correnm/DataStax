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

import java.util.Iterator;
import java.util.UUID;

import javax.annotation.PostConstruct;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.MailService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("passcodeForgot")
@RequestScoped
public class PasscodeForgot {

	@Inject private ApplicationUtils applicationUtils;
	
	private ServletContext ctx;
	
	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;
	private Session appAuthDBSession;

	private String applicationURL, orgName, userEmail, orgKeyspace, queryString;
	private Boolean activeUserInd;

	
	// constructor
	public PasscodeForgot() {

	}
	
	
	// method that executes after injections have occurred
	@PostConstruct
	public void init() {

		// get the servlet's context
		ctx = SessionUtils.getRequest().getServletContext();

		// get the base URL of the application
		applicationURL = ctx.getInitParameter("application_URL");

		// get the database connection session
		appAuthDBSession = applicationUtils.getApplAuthDBSession();

	}

	
	// getters
	public String getOrgName() {
		return orgName;
	}

    public String getUserEmail() {
    	return userEmail;
    }

    
    // setters
	public void setOrgName(String orgName) {
		this.orgName = orgName.toLowerCase();
	}
  
	public void setUserEmail(String userEmail) {
    	this.userEmail = userEmail.toLowerCase();
    }

	
	// validate Organization name entered on form
	public void validateOrganizationName(FacesContext context, UIComponent comp, Object obj) {

		// cast the form field value to a string type
		orgName = (String) obj;
		orgName = orgName.trim().toLowerCase();

		// get the prepared statement for selecting organization info from the servlet's context
		PreparedStatement preparedStatement = (PreparedStatement)ctx.getAttribute("PSOrgInfo");
	
		// create bound statement
		BoundStatement boundStatement = preparedStatement.bind(orgName);
	
		// execute the query
		ResultSet rs = appAuthDBSession.execute(boundStatement);

		// get the result values
		Row row = rs.one();

		// if row is null, the orgName value is invalid
		if (row == null) {

			// return to login form with error message
            FacesMessage msg = new FacesMessage("Organization Name is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

		} else {

			// save values retrieved by query
			orgKeyspace = row.getString("keyspace_name");

		}

	}
	
	
	// forgot passcode form was submitted
	public String forgotPasscodeActionControllerMethod() {

		System.out.println("in PasscodeForgot forgotPasscodeActionControllerMethod - orgName: " + this.orgName + " userEmail: " + this.userEmail);

		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(orgKeyspace);

		// execute the query for selecting the user's info
		rs = databaseQueryService.runQuery("select active_user_ind from users where user_email = '" + this.userEmail + "'");

		// get the result values
		row = rs.one();

		// if row is null, the userEmail value is invalid
		if (row == null) {

			// return to form with error message
            FacesMessage msg = new FacesMessage("Email Address is invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return(null);

		} else {

			// save value retrieved by query
			activeUserInd = row.getBool("active_user_ind");

			// if user account is inactive, return to form with error message
			if (!activeUserInd) {
				// return to form with error message
	            FacesMessage msg = new FacesMessage("User Account is inactive");
	            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, msg);
				return(null);
			}
			
		}

		// delete any existing passcode reset requests
		rs = appAuthDBSession.execute("select organization_name, email_token, email_address from passcode_reset");
		
		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (passcode reset record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// is it for the current requester?
			if ((this.userEmail.equals(row.getObject("email_address"))) && (this.orgName.equals(row.getObject("organization_name")))) {
				// if so, delete the record
				queryString = "delete from passcode_reset where organization_name = '" + this.orgName + "' and email_token = " + row.getObject("email_token");
				appAuthDBSession.execute(queryString);
			}

		}

		// *****
		// send email to user
			// insert record into appl_auth table
			// send email with link to new user page (including key parameter)
		// *****
		// generate token (a new UUID)
		UUID uuid = applicationUtils.generateRandomUUID();
		
		// get the client's IP address
		String ipAddress = applicationUtils.getUserIPAddress();
		
		System.out.println("in PasscodeForgot forgotPasscodeActionControllerMethod - uuid: " + uuid.toString());
		System.out.println("in PasscodeForgot forgotPasscodeActionControllerMethod - IP: " + ipAddress);
		
		// insert a record into the password_reset table
		queryString = "insert into passcode_reset (organization_name, email_token, email_address, ip_address, reset_attempts, reset_complete, reset_request_datetime)";
		queryString = queryString.concat(" values ('" + this.orgName + "', " + uuid + ", '" + this.userEmail + "', '" + ipAddress + "', 0, false, toTimestamp(now()))");
		System.out.println("in PasscodeForgot forgotPasscodeActionControllerMethod - queryString: " + queryString);
		
		appAuthDBSession.execute(queryString);

		// *** need expiry date to be one day in the future ???
		
		//send email with link to passcode reset
		String emailSubject = "Your URM Passcode Reset Request";
		String emailBody = "To reset your URM passcode, click this link: <a href='" + applicationURL + "/public/passcode-reset-step1.jsf?requestID=" + uuid + "'>Reset Passcode</a>";
        try {
        	MailService.sendMessage(this.userEmail, emailSubject, emailBody);
        }
        catch(MessagingException ex) {
            //statusMessage = ex.getMessage();
            ex.printStackTrace();
        }
		
		// go back to same page, but with a success message
        FacesMessage msg = new FacesMessage("Passcode reset request successfully submitted - check your email!");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return(null);

	}

	
	// cancel button was clicked, go to login page
	public String forgotPasscodeCancelControllerMethod() {

		// go to the Login page
		return "/login?faces-redirect=true";
		
	}

	
}
