package com.g2ops.impact.urm.beans;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, April 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 4.23.2018		tammy.bogart		created 
 */
 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.g2ops.impact.urm.types.BreachTypes;

@Named("breachTypesBean")
@SessionScoped	//required for pulling in currentUser info --Client-specific data      


public class BreachTypesBean  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;
	
	private Session session;
	private ResultSet resultSet; 
	private DatabaseQueryService databaseQueryService;
	private String query, breach_type, country_name, origBTName, origCountry;
	private double publication_year, distribution_pct, per_capita_cost, origPubYear;
	private List<BreachTypes> btList = new ArrayList<BreachTypes>();
	private BreachTypes bt;
	private Iterator<Row> iterator;
	
	// constructor
	public BreachTypesBean() {
		System.out.println("*** in BreachTypesBean constructor ***");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("*** in BreachTypes init ***");
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		
		//load table data
		LoadBreachTypes();
	} // end of init()

	//functions to load the table, add, edit pages	
	public void LoadBreachTypes() {
		//called on initialization of breach-types-table to load table data
		System.out.println("in LoadBreachTypes");	
		
		query = "SELECT "
			+	"publication_year, "
			+	"country_name, "
			+	"breach_type, "
			+	"distribution_pct, "
			+	"per_capita_cost "
			+	"from appl_auth.breach_types";
		resultSet = databaseQueryService.runQuery(query);	
		// clear out any old content before retrieving new database info
		btList.clear();
		iterator = resultSet.iterator();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			publication_year = row.getDouble("publication_year");
			country_name = row.getString("country_name");
			breach_type = row.getString("breach_type");
			distribution_pct = row.getDouble("distribution_pct");
			per_capita_cost = row.getDouble("per_capita_cost");

			bt = new BreachTypes(publication_year, country_name, breach_type, distribution_pct, per_capita_cost);
			btList.add(bt);	
		}  	//end of while
	}

	public String LoadBTAddFormData()  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadAddFormData");
		
		this.publication_year = 2017;		//set default value
		this.country_name = "United States";	//set default value
		this.breach_type = "";
		this.distribution_pct = 0;
		this.per_capita_cost = 0;

		//goto Add form
		return "/superadmin/breach-types-add.jsf";
		//return null;
	}	//end LoadBTAddFormData()
	
	public String LoadBTEditFormData(BreachTypes btEdit) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadBTEditFormData");	
	
		this.publication_year = btEdit.getPublication_year();
		this.country_name = btEdit.getCountry_name();
		this.breach_type = btEdit.getBreach_type();
		this.distribution_pct = btEdit.getDistribution_pct();
		this.per_capita_cost = btEdit.getPer_capita_cost();
		//set original primary values in case they change
		this.origBTName = btEdit.getBreach_type();
		this.origCountry = 	btEdit.getCountry_name();
		this.origPubYear = btEdit.getPublication_year();
		
		//goto Edit form
		return "/superadmin/breach-types-edit.jsf";
	}	//end of LoadBTEditFormData()

	//Functions to push changes to the database table
	public String editBTControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {		
		System.out.println("in editBTControllerMethod");		
		PreparedStatement prepared;
		BoundStatement bound;

		if (this.origBTName.equals(this.breach_type) && this.origCountry.equals(this.country_name) && this.origPubYear==this.publication_year)  {
			//if primary key does not change, update existing data	
			System.out.println("primary key unchanged, query: " + query);

			query = "UPDATE appl_auth.breach_types "
				+	"SET distribution_pct=?, "
				+	"per_capita_cost=? "
				+	"WHERE publication_year=? and country_name=? and breach_type=?";

			prepared = session.prepare(query);
			bound = prepared.bind(this.getDistribution_pct(), this.getPer_capita_cost(), this.publication_year, this.country_name, this.breach_type);
			session.execute(bound);
		} else {
			System.out.println("primary key changed, query: " + query);
			
			//if primary key does change, insert new record
			query = "INSERT INTO appl_auth.breach_types ("
				+ 	"publication_year, "
				+ 	"country_name, "
				+ 	"breach_type, "
				+ 	"distribution_pct, "
				+ 	"per_capita_cost) VALUES " 
				+ 	"(?,?,?,?,?)"; 

			prepared = session.prepare(query);
			bound = prepared.bind(this.publication_year, this.country_name, this.breach_type, this.distribution_pct, this.per_capita_cost);
			session.execute(bound);

			//now delete old record
			query = "DELETE FROM appl_auth.breach_types "
				+	"WHERE publication_year=? and country_name=? and breach_type=?";

			prepared = session.prepare(query);
			bound = prepared.bind(this.origPubYear, this.origCountry, this.origBTName);
			session.execute(bound);
		}	
		//re-load table
		LoadBreachTypes();
		return "/superadmin/breach-types-table.jsf";
	}	//end deleteBVAControllerMethod

	public String addBTControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in addBTControllerMethod");
		
		query = "INSERT into appl_auth.breach_types " 
				+	"(publication_year, "
				+	"country_name, "
				+	"breach_type, "
				+	"distribution_pct, "
				+	"per_capita_cost) "
				+	"VALUES (?,?,?,?,?)";
			
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind(this.getPublication_year(), this.getCountry_name(), this.getBreach_type(), this.getDistribution_pct(), this.getPer_capita_cost());
		session.execute(bound);
		
		//re-load table
		LoadBreachTypes();
		return "/superadmin/breach-types-table.jsf";
	}
	
	public String deleteBTControllerMethod(BreachTypes bt) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in deleteBVAControllerMethod");
			
		query = "DELETE FROM appl_auth.breach_types "
				+	"WHERE publication_year=? and country_name=? and breach_type=?";

			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound = prepared.bind(bt.getPublication_year(), bt.getCountry_name(), bt.getBreach_type());
			session.execute(bound);
			//update table
			this.btList.remove(bt);
			System.out.println("Breach type " + bt.getBreach_type() +" was deleted.");		
		return null;
	}	//end deleteBVAControllerMethod


	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Getters/Setters<<<<<<<<<<<<<<<<<<<<<<<<//
	public List<BreachTypes> getBreachTypeData() {
		return btList;
	}
	
	public BreachTypes getBt() {
		return bt;
	}

	public void setBt(BreachTypes bt) {
		this.bt = bt;
	}

	public String getOrigBTName() {
		return origBTName;
	}

	public void setOrigBTName(String origBTName) {
		this.origBTName = origBTName;
	}

	public String getOrigCountry() {
		return origCountry;
	}

	public void setOrigCountry(String origCountry) {
		this.origCountry = origCountry;
	}

	public double getOrigPubYear() {
		return origPubYear;
	}

	public void setOrigPubYear(double origPubYear) {
		this.origPubYear = origPubYear;
	}

	public UserBean getCurrentUser() {
		return currentUser;
	}

	public String getBreach_type() {
		return breach_type;
	}

	public void setBreach_type(String breach_type) {
		this.breach_type = breach_type;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public double getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(double publication_year) {
		this.publication_year = publication_year;
	}

	public double getDistribution_pct() {
		return distribution_pct;
	}

	public void setDistribution_pct(double distribution_pct) {
		this.distribution_pct = distribution_pct;
	}

	public double getPer_capita_cost() {
		return per_capita_cost;
	}

	public void setPer_capita_cost(double per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}
}
                                                                                      
 
