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
 * 4.25.2018		tammy.bogart		created 
 */
 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.g2ops.impact.urm.types.IndustryBreachCosts;

@Named("industryBreachCostBean")
@SessionScoped	//required for pulling in currentUser info --Client-specific data      


public class IndustryBreachCostBean  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;
	
	private Session session;
	private ResultSet resultSet; 
	private DatabaseQueryService databaseQueryService;
	private PreparedStatement prepared;
	private BoundStatement bound;
	private List<IndustryBreachCosts> ibcList = new ArrayList<IndustryBreachCosts>();
	private IndustryBreachCosts ibc;
	private Iterator<Row> iterator;
	private String query, selectedBC;
	
    private double publication_year, origPubYear, direct_cost_pct, direct_per_capita_cost, indirect_cost_pct, indirect_per_capita_cost, per_capita_cost;
    private String origCName, industry_name, origIndName, verizon_dbir_industry_name;
	
    private String country;
	private List<String> countries;

	// constructor
	public IndustryBreachCostBean() {
		System.out.println("*** in IndustryBreachCostBean constructor ***");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("*** in IndustryBreachCostBean init ***");
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
	
		getCountryList();
		//load table data
		LoadIndustryBreachCostBean();
	} // end of init()

	//get list of countries for page
	public void getCountryList() {
		query="";
		query = "select database_column, option_values from lov_references where database_column ='country_name'";
		resultSet = databaseQueryService.runQuery(query);
		Row row = resultSet.one();
				
		Map<String, String> cntryMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
		this.countries = new ArrayList<String>(cntryMap.values());
	}
	
	//functions to load the table, add, edit pages	
	public void LoadIndustryBreachCostBean() {
		//called on initialization of breach-types-table to load table data
		System.out.println("in LoadIndustryBreachCostBean");	
		this.selectedBC="";
		
		query = "SELECT publication_year, " 
				+	"country_name, " 
				+	"industry_name, " 
				+	"direct_cost_pct, " 
				+	"direct_per_capita_cost, " 
				+	"indirect_cost_pct, " 
				+	"indirect_per_capita_cost, " 
				+	"per_capita_cost, " 
				+	"verizon_dbir_industry_name "
				+	"FROM appl_auth.industry_breach_costs";
		
		resultSet = databaseQueryService.runQuery(query);	
		
		// clear out any old content before retrieving new database info
		ibcList.clear();
		iterator = resultSet.iterator();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			publication_year = row.getDouble("publication_year");
			country = row.getString("country_name");
			industry_name = row.getString("industry_name");
			direct_cost_pct = row.getDouble("direct_cost_pct"); 
			direct_per_capita_cost = row.getDouble("direct_per_capita_cost"); 
			indirect_cost_pct = row.getDouble("indirect_cost_pct");
			indirect_per_capita_cost = row.getDouble("indirect_per_capita_cost"); 
			per_capita_cost = row.getDouble("per_capita_cost");
			verizon_dbir_industry_name = row.getString("verizon_dbir_industry_name");

			ibc = new IndustryBreachCosts(publication_year, direct_cost_pct, direct_per_capita_cost, indirect_cost_pct, indirect_per_capita_cost, per_capita_cost, country, industry_name,  verizon_dbir_industry_name);
			ibcList.add(ibc);	
		}  	//end of while
		
		//sort list in desc order by Publication Year
		List<IndustryBreachCosts> sortedList = new ArrayList<IndustryBreachCosts>();
		Collections.sort(ibcList, IndustryBreachCosts.PubYearComparator);
		for(IndustryBreachCosts s: ibcList) {
			sortedList.add(s);
		}
		ibcList.clear();
		ibcList = sortedList;
		
	}	//end LoadIndustryBreachCostBean


	public String LoadIBCAddFormData()  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadAddIBCFormData");
		publication_year = 2017;
		country = "United States";
		industry_name="";
		direct_cost_pct= 0; 
		direct_per_capita_cost = 0; 
		indirect_cost_pct = 0;
		indirect_per_capita_cost = 0; 
		per_capita_cost = 0;
		verizon_dbir_industry_name = "";
		//goto Add form
		return "/superadmin/industry-breach-costs-add.jsf";
	}	//end LoadIBCAddFormData()
	
	public String LoadIBCEditFormData(IndustryBreachCosts ibcEdit) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadIBCEditFormData");	
		publication_year = ibcEdit.getPublication_year();
		country = ibcEdit.getCountry_name();
		industry_name= ibcEdit.getIndustry_name();
		direct_cost_pct= ibcEdit.getDirect_cost_pct(); 
		direct_per_capita_cost = ibcEdit.getDirect_per_capita_cost(); 
		indirect_cost_pct = ibcEdit.getIndirect_cost_pct();
		indirect_per_capita_cost = ibcEdit.getIndirect_per_capita_cost(); 
		per_capita_cost = ibcEdit.getPer_capita_cost();
		verizon_dbir_industry_name = ibcEdit.getVerizon_dbir_industry_name();

		//load original values of primary key in case primary key values change
		this.origPubYear = publication_year;
		this.origCName = country;
		this.origIndName = industry_name;
		
		//goto Edit form
		return "/superadmin/industry-breach-costs-edit.jsf";
	}	//end of LoadIBCEditFormData()

	//Functions to push changes to the database table
	public String editIBCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {		
		System.out.println("in editBTControllerMethod");

		//check if primary key changed, if so insert new record, delete old record
		if (this.origPubYear==this.publication_year && this.origCName.equals(this.country) && this.origIndName.equals(this.industry_name)) {
				System.out.println("primary key unchanged, query: " + query);
				query = "UPDATE appl_auth.industry_breach_costs "
					+	"SET direct_cost_pct=?, " 
					+	"direct_per_capita_cost=?, " 
					+	"indirect_cost_pct=?, "
					+	"indirect_per_capita_cost=?, " 
					+	"per_capita_cost=?, "
					+	"verizon_dbir_industry_name=? "
					+	"WHERE publication_year=? and country_name=? and industry_name=?";

				prepared = session.prepare(query);
				bound = prepared.bind(this.getDirect_cost_pct(), this.getDirect_per_capita_cost(), this.getIndirect_cost_pct(), this.getIndirect_per_capita_cost(), 
									  this.getPer_capita_cost(), this.getVerizon_dbir_industry_name(), this.publication_year, this.country, this.industry_name);
				session.execute(bound);
			} else {
				System.out.println("primary key changed, query: " + query);
				
				//if primary key does change, insert new record
				query = "INSERT INTO appl_auth.industry_breach_costs ("
					+ 	"publication_year, "
					+ 	"country_name, "
					+ 	"industry_name, "
					+ 	"direct_cost_pct, "
					+	"direct_per_capita_cost, "
					+   "indirect_cost_pct, "
					+	"indirect_per_capita_cost, "
					+	"per_capita_cost, "
					+ 	"verizon_dbir_industry_name) VALUES " 
					+ 	"(?,?,?,?,?,?,?,?,?)"; 

				prepared = session.prepare(query);
				bound = prepared.bind(this.getPublication_year(), this.getCountry(), this.getIndustry_name(), this.getDirect_cost_pct(), this.getDirect_per_capita_cost(), 
									   this.getIndirect_cost_pct(), this.getIndirect_per_capita_cost(), this.getPer_capita_cost(), this.getVerizon_dbir_industry_name());
				session.execute(bound);

				//now delete old record
				query = "DELETE FROM appl_auth.industry_breach_costs "
					+	"WHERE publication_year=? and country_name=? and industry_name=?";

				prepared = session.prepare(query);
				bound = prepared.bind(this.origPubYear, this.origCName, this.origIndName);
				session.execute(bound);
			}	

		//reload table data
		LoadIndustryBreachCostBean();
		//goto Table form
		return "/superadmin/industry-breach-costs-table.jsf";
}	//end editIBCControllerMethod

	public String addIBCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in addIBCControllerMethod");
		query = "INSERT INTO appl_auth.industry_breach_costs ("
				+ 	"publication_year, "
				+ 	"country_name, "
				+ 	"industry_name, "
				+ 	"direct_cost_pct, "
				+	"direct_per_capita_cost, "
				+   "indirect_cost_pct, "
				+	"indirect_per_capita_cost, "
				+	"per_capita_cost, "
				+ 	"verizon_dbir_industry_name) VALUES " 
				+ 	"(?,?,?,?,?,?,?,?,?)"; 

			prepared = session.prepare(query);
			bound = prepared.bind(this.getPublication_year(), this.getCountry(), this.getIndustry_name(), this.getDirect_cost_pct(), this.getDirect_per_capita_cost(), 
								   this.getIndirect_cost_pct(), this.getIndirect_per_capita_cost(), this.getPer_capita_cost(), this.getVerizon_dbir_industry_name());
			session.execute(bound);

		//reload table data
		LoadIndustryBreachCostBean();
		//goto Table form
		return "/superadmin/industry-breach-costs-table.jsf";
}   //end addBTControllerMethod
	
	public void deleteIBCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in deleteIBCControllerMethod");
		query = "DELETE FROM appl_auth.industry_breach_costs "
				+	"WHERE publication_year=? and country_name=? and industry_name=?";

			prepared = session.prepare(query);
			bound = prepared.bind(ibc.getPublication_year(), ibc.getCountry_name(), ibc.getIndustry_name());
			session.execute(bound);
			
		//reload table data
			this.ibcList.remove(ibc);
		//LoadIndustryBreachCostBean();
		//return null;
	}	//end deleteIBCControllerMethod

	public void setSelected(IndustryBreachCosts selIBC) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		this.ibc = selIBC;
		this.selectedBC = selIBC.getCountry_name() + "/" + selIBC.getIndustry_name();
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Getters/Setters<<<<<<<<<<<<<<<<<<<<<<<<//
	public List<IndustryBreachCosts> getIndustryBreachCostData() {
		return ibcList;
	}

	public List<IndustryBreachCosts> getIbcList() {
		return ibcList;
	}

	public void setIbcList(List<IndustryBreachCosts> ibcList) {
		this.ibcList = ibcList;
	}

	public IndustryBreachCosts getIbc() {
		return ibc;
	}

	public void setIbc(IndustryBreachCosts ibc) {
		this.ibc = ibc;
	}

	public String getSelectedBC() {
		return selectedBC;
	}

	public void setSelectedBC(String selectedBC) {
		this.selectedBC = selectedBC;
	}

	public double getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(double publication_year) {
		this.publication_year = publication_year;
	}

	public double getDirect_cost_pct() {
		return direct_cost_pct;
	}

	public void setDirect_cost_pct(double direct_cost_pct) {
		this.direct_cost_pct = direct_cost_pct;
	}

	public double getDirect_per_capita_cost() {
		return direct_per_capita_cost;
	}

	public void setDirect_per_capita_cost(double direct_per_capita_cost) {
		this.direct_per_capita_cost = direct_per_capita_cost;
	}

	public double getIndirect_cost_pct() {
		return indirect_cost_pct;
	}

	public void setIndirect_cost_pct(double indirect_cost_pct) {
		this.indirect_cost_pct = indirect_cost_pct;
	}

	public double getIndirect_per_capita_cost() {
		return indirect_per_capita_cost;
	}

	public void setIndirect_per_capita_cost(double indirect_per_capita_cost) {
		this.indirect_per_capita_cost = indirect_per_capita_cost;
	}

	public double getPer_capita_cost() {
		return per_capita_cost;
	}

	public void setPer_capita_cost(double per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}

	public String getIndustry_name() {
		return industry_name;
	}

	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}

	public String getVerizon_dbir_industry_name() {
		return verizon_dbir_industry_name;
	}

	public void setVerizon_dbir_industry_name(String verizon_dbir_industry_name) {
		this.verizon_dbir_industry_name = verizon_dbir_industry_name;
	}
}
                                                                                      
 
