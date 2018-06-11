package com.g2ops.impact.urm.beans;

/**
 * @author 		{name}, G2 Ops, Virginia Beach, VA
 * @version 	1.00, Month Year
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
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.datastax.driver.core.ResultSet;				//function used for database queries
import com.datastax.driver.core.Row;					//function used for database queries
import com.g2ops.impact.urm.utils.DatabaseQueryService;	//function used for database queries
import com.datastax.driver.core.BoundStatement;			//function used for database queries
import com.datastax.driver.core.PreparedStatement;		//function used for database queries
import com.datastax.driver.core.Session;				//function used for session data
import com.g2ops.impact.urm.utils.SessionUtils;			//function used for session data
import com.g2ops.impact.urm.beans.LovReferences;		//function to pull: countries, Business Criticality, Business Interruption Threshold, Asset Types, Information Classification, Industry, Asset Visiblity
import com.g2ops.impact.urm.types.BreachTypes;			//call any classes in types that you need for your bean

@Named("breachTypeBean")		//name that will reference this bean on the front-end, ex: breachTypeBean  -- same as class name but with lower-case 1st letter
@SessionScoped	//required for pulling in currentUser info --Client-specific data      


public class BreachTypesBean  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;
	
	//Define variables used throughout the bean
	private Session session;
	private ResultSet resultSet; 
	private DatabaseQueryService databaseQueryService;
	private Iterator<Row> iterator;
	private String query, country;
	private ArrayList<String> countries;
	private List<BreachTypes> btList = new ArrayList<BreachTypes>();
	private BreachTypes bt;

	// constructor
	public BreachTypesBean() {
		System.out.println("*** in BreachTypesBean constructor ***");
	}
	
	@PostConstruct
	public void init() {
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());

		//get country list for dropdown on front-end		** Example of pulling the LovReferences
		LovReferences LovReferences = new LovReferences();
		this.countries = new ArrayList<String>(LovReferences.getSelectItems("country_name",currentUser).values());
		
		//load form/table
		LoadBreachTypes();
	} // end of init()
	
	//functions to load the table or form pages	
	public void LoadBreachTypes() {

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
		country = row.getString("country_name");
		breach_type = row.getString("breach_type");
		distribution_pct = row.getDouble("distribution_pct");
		per_capita_cost = row.getDouble("per_capita_cost");

		bt = new BreachTypes(publication_year, country, breach_type, distribution_pct, per_capita_cost);
		btList.add(bt);	
	}  	//end of while
	
}		//end of LoadBreachTypes()
	
	
//The following functions are called from the commandButtons:  Add, Edit, Delete
	
	public String editBTControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {		
		PreparedStatement prepared;
		BoundStatement bound;
			query="";
			query = "UPDATE appl_auth.breach_types "
				+	"SET distribution_pct=?, "
				+	"per_capita_cost=? "
				+	"WHERE publication_year=? and country_name=? and breach_type=?";

			prepared = session.prepare(query);
			bound = prepared.bind(this.getDistribution_pct(), this.getPer_capita_cost(), this.publication_year, this.country, this.breach_type);
			session.execute(bound);
			
		//re-load table
		LoadBreachTypes();
		return "/superadmin/breach-types-table.jsf";		//return to main table data 
	}	//end deleteBVAControllerMethod

	public String addBTControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		query = "INSERT into appl_auth.breach_types " 
				+	"(publication_year, "
				+	"country_name, "
				+	"breach_type, "
				+	"distribution_pct, "
				+	"per_capita_cost) "
				+	"VALUES (?,?,?,?,?)";
			
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind(this.getPublication_year(), this.getCountry(), this.getBreach_type(), this.getDistribution_pct(), this.getPer_capita_cost());
		session.execute(bound);
		
		//re-load table
		LoadBreachTypes();
		return "/superadmin/breach-types-table.jsf";		//return to main table data
	}
	
	public void deleteBTControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		query = "DELETE FROM appl_auth.breach_types "
				+	"WHERE publication_year=? and country_name=? and breach_type=?";

			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound = prepared.bind(bt.getPublication_year(), bt.getCountry_name(), bt.getBreach_type());
			session.execute(bound);
	
			//update table
			this.btList.remove(bt);
	}	//end deleteBVAControllerMethod
	
//define the getters aned setters for the variables that are used on the front end forms	** examples below
	public UserBean getCurrentUser() {
		return currentUser;
	}
	
	public List<BreachTypes> getBreachTypeData() {
		return btList;
	}
	
	public BreachTypes getBt() {
		return bt;
	}

	public void setBt(BreachTypes bt) {
		this.bt = bt;
	}

} 		//end class BreachTypesBean	