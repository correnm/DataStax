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

import javax.enterprise.context.SessionScoped;

import javax.inject.Named;
import javax.servlet.ServletContext;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.DatabaseConnectionManager;
import com.g2ops.impact.urm.utils.EncryptionDecryptionService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("organizationEdit")
@SessionScoped
public class OrganizationEdit implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session appAuthDBSession;
	private PreparedStatement psSelectOrgInfo, psUpdateOrgInfo;
	private BoundStatement bsSelectOrgInfo, bsUpdateOrgInfo;
	private ResultSet rs;
	private Row row;

	private String orgToEditName, keyspaceName, username, password, countryName, industry;

	// constructor
	public OrganizationEdit() {

	}
	
	@PostConstruct
	public void init() {

		// get the servlet's context
		ServletContext ctx = SessionUtils.getRequest().getServletContext();

		// get the database connection to the application authorization keyspace from the servlet's context 
		DatabaseConnectionManager appAuthDBConnection = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");

		// get the database connection session
		appAuthDBSession = appAuthDBConnection.getSession();

		// create the prepared statement for selecting the organization info
		psSelectOrgInfo = appAuthDBSession.prepare("select keyspace_name, username, encrypted_password, country_name, industry from organizations where organization_name = ?");

		// create the prepared statement for updating the organization info
		psUpdateOrgInfo = appAuthDBSession.prepare("update organizations set keyspace_name = ?, username = ?, encrypted_password = ?, country_name = ?, industry = ? where organization_name = ? and webapp_name = 'URM'");

	}

	// method for getting the data for the user being edited
	public void retrieveOrganizationData() {

		// create bound statement for selecting the organization info
		bsSelectOrgInfo = psSelectOrgInfo.bind(orgToEditName);
	
		// execute the query
		rs = appAuthDBSession.execute(bsSelectOrgInfo);

		// get the result record
		row = rs.one();

		// set values to what was returned by the query
		keyspaceName = row.getString("keyspace_name");
		username = row.getString("username");
		password = row.getString("encrypted_password");
		countryName = row.getString("country_name");
		industry = row.getString("industry");

		// decrypt the password
		try {
			password = EncryptionDecryptionService.decrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String getOrgToEditName() {
    	return orgToEditName;
    }

    public String getKeyspaceName() {
    	return keyspaceName;
    }

    public String getUsername() {
    	return username;
    }

    public String getPassword() {
    	return password;
    }

    public String getCountryName() {
    	return countryName;
    }

    public String getIndustry() {
    	return industry;
    }

	public void setOrgToEditName(String orgToEditName) {
    	this.orgToEditName = orgToEditName;
    }

    public void setKeyspaceName(String keyspaceName) {
    	this.keyspaceName = keyspaceName;
    }

    public void setUsername(String username) {
    	this.username = username;
    }

    public void setPassword(String password) {
    	this.password = password;
    }

    public void setCountryName(String countryName) {
    	this.countryName = countryName;
    }

    public void setIndustry(String industry) {
    	this.industry = industry;
    }

	public String editOrganizationActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		try {
			this.password = EncryptionDecryptionService.encrypt(this.password);
			System.out.println("in editOrganizationActionControllerMethod method the encrypted password: " + this.password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create bound statement for updating the organization info
		bsUpdateOrgInfo = psUpdateOrgInfo.bind(this.keyspaceName, this.username, this.password, this.countryName, this.industry, orgToEditName);
	
		// execute the query
		rs = appAuthDBSession.execute(bsUpdateOrgInfo);

		// go back to the Manage Organizations page
		return "organizations-table";

	}
	
}
