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
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.types.Organization;
import com.g2ops.impact.urm.utils.ApplicationUtils;
import com.g2ops.impact.urm.utils.EncryptionDecryptionService;

@Named("organizationsBean")
@SessionScoped
public class OrganizationsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private ApplicationUtils applicationUtils;
	
	private Session appAuthDBSession;
	private PreparedStatement psSelectOrgInfo, psSelectKeyspaceName, psSelectRole, psUpdateOrgInfo;
	private BoundStatement bsSelectOrgInfo, bsSelectKeyspaceName, bsSelectRole, bsUpdateOrgInfo;
	private ResultSet rs;
	private Row row;

	private String orgToEditName, keyspaceName, username, password, countryName, industryName;
	
	private List<String> countries;
	private List<String> industries;
	private List<Organization> organizations = new ArrayList<Organization>();


	// constructor
	public OrganizationsBean() {

	}
	
	// post construct method for initialization
	@PostConstruct
	public void init() {

		// get the database connection session
		appAuthDBSession = applicationUtils.getApplAuthDBSession();

		// create the prepared statement for selecting the organization info
		psSelectOrgInfo = appAuthDBSession.prepare("select keyspace_name, username, encrypted_password, country_name, industry from organizations where organization_name = ?");

		// create the prepared statement for validating the keyspace name
		psSelectKeyspaceName = appAuthDBSession.prepare("select count(*) as cnt from system_schema.keyspaces where keyspace_name = ?");

		// create the prepared statement for validating the DB username
		psSelectRole = appAuthDBSession.prepare("select count(*) as cnt from system_auth.roles where role = ?");

		// create the prepared statement for updating the organization info
		psUpdateOrgInfo = appAuthDBSession.prepare("update organizations set keyspace_name = ?, username = ?, encrypted_password = ?, country_name = ?, industry = ? where organization_name = ?");

		// select all the countries
		rs = appAuthDBSession.execute("select option_values from lov_references where database_column = 'country_name'");
		row = rs.one();
		Map<String, String> countriesMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);
		this.countries = new ArrayList<String>(countriesMap.values());

		// create array for holding the country names
		String[] countriesArray = (String[]) this.countries.toArray(new String[this.countries.size()]);
			
		// sort the array by country name
		Arrays.sort(countriesArray);

		// re-populate the ArrayList
		countries.clear();
		for(int i=0; i<countriesArray.length; i++) {
			countries.add(countriesArray[i]);
		}

		// select all the industries
		rs = appAuthDBSession.execute("select option_values from lov_references where database_column = 'industry'");
		row = rs.one();
		Map<String, String> industriesMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);
		this.industries = new ArrayList<String>(industriesMap.values());

		// create array for holding the industry names
		String[] industriesArray = (String[]) this.industries.toArray(new String[this.industries.size()]);
			
		// sort the array by industry name
		Arrays.sort(industriesArray);

		// re-populate the ArrayList
		industries.clear();
		for(int i=0; i<industriesArray.length; i++) {
			industries.add(industriesArray[i]);
		}

	}


	// method for populating a ListArray with all the organizations for the organizations table
	public List<Organization> getOrganizationsData() {

		// empty the existing list to start fresh
		organizations.clear();

		// execute the query
		rs = appAuthDBSession.execute("select organization_name, keyspace_name, username, encrypted_password, country_name, industry from organizations");
		
		Iterator<Row> iterator = rs.iterator();
		
		// iterate through each row (organization record)
		while (iterator.hasNext()) {

			// grab the next row
			row = iterator.next();

			// instantiate an Organization
			Organization organization = new Organization(row.getString("organization_name"), row.getString("keyspace_name"), row.getString("username"), row.getString("encrypted_password"), row.getString("country_name"), row.getString("industry"));

			// add Organization to list
			organizations.add(organization);

		} // end while loop
		
		return organizations;
		
	}


	// method for getting the data for the organization being edited
	public String retrieveOrganizationData(String orgToEditName) {

		// set bean property to value passed in
		this.orgToEditName = orgToEditName;

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
		industryName = row.getString("industry");

		// decrypt the password
		try {
			password = EncryptionDecryptionService.decrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// navigate to the organization edit page
		return "organization-edit?faces-redirect=true";
		
	}

	
	// method that validates the submitted keyspace name is a valid keyspace in the DB
	public void validateKeyspaceName(FacesContext context, UIComponent comp, Object obj) {

		// cast the form field value to a string type
		String keyspaceName = (String) obj;

		// create bound statement for selecting the count of keyspace names
		bsSelectKeyspaceName = psSelectKeyspaceName.bind(keyspaceName);
		
		// execute the query
		rs = appAuthDBSession.execute(bsSelectKeyspaceName);

		// get the result record
		row = rs.one();

		// set value to what was returned by the query
		long rsCount = row.getLong("cnt");

		// if the returned count is not 1, then there is a problem
        if (rsCount != 1) {
            FacesMessage msg = new FacesMessage("Keyspace name was not found in the DB");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
		
	}


	// method that validates the submitted username is a valid user in the DB
	public void validateUsername(FacesContext context, UIComponent comp, Object obj) {

		// cast the form field value to a string type
		String username = (String) obj;

		// create bound statement for selecting the count of keyspace names
		bsSelectRole = psSelectRole.bind(username);
		
		// execute the query
		rs = appAuthDBSession.execute(bsSelectRole);

		// get the result record
		row = rs.one();

		// set value to what was returned by the query
		long rsCount = row.getLong("cnt");

		// if the returned count is not 1, then there is a problem
        if (rsCount != 1) {
            FacesMessage msg = new FacesMessage("Username was not found in the DB");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
		
	}


	// method that update's the edited organization's data
	public String editOrganizationActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// encrypt the password
		String encryptedPassword = "";
		try {
			encryptedPassword = EncryptionDecryptionService.encrypt(this.password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create bound statement for updating the organization info
		bsUpdateOrgInfo = psUpdateOrgInfo.bind(this.keyspaceName, this.username, encryptedPassword, this.countryName, this.industryName, orgToEditName);
	
		// execute the query that updates the organization's data in the appl_auth.organizations table
		rs = appAuthDBSession.execute(bsUpdateOrgInfo);

		// execute the query that updates the DB role's password in the DB
		// (can't use a prepared statement because "bind variables cannot be used for role names")
		rs = appAuthDBSession.execute("alter role " + this.username + " with password = '" + this.password + "'");

		// go back to the Manage Organizations page
		return "organizations-table?faces-redirect=true";

	}


	// getters
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

    public String getIndustryName() {
    	return industryName;
    }
    
    public List<String> getCountries() {
    	return countries;
    }

    public List<String> getIndustries() {
    	return industries;
    }


	// setters
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

    public void setIndustryName(String industry) {
    	this.industryName = industry;
    }

}
