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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.g2ops.impact.urm.types.IndustrySecurityIncidents;

@Named("industrySecurityIncidentsBean")
@SessionScoped	//required for pulling in currentUser info --Client-specific data      


public class IndustrySecurityIncidentsBean  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;
	
	private Session session;
	private ResultSet resultSet; 
	private DatabaseQueryService databaseQueryService;
	PreparedStatement prepared;
	BoundStatement bound;
	private List<IndustrySecurityIncidents> isiList = new ArrayList<IndustrySecurityIncidents>();
	private IndustrySecurityIncidents isi;
	private Iterator<Row> iterator;
	private String query, selectedISI;

	private double publication_year, origPubYear, breaches_large, breaches_small, breaches_total, breaches_unk, incidents_large, incidents_small, incidents_total, incidents_unk, probability_of_attack, sample_size; 
	private String verizon_dbir_industry_name, origVerIndName;
	
	// constructor
	public IndustrySecurityIncidentsBean() {
		System.out.println("*** in IndustrySecurityIncidents constructor ***");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("*** in IndustrySecurityIncidents init ***");
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		
		//load table data
		LoadIndustrySecurityIncidents();
	} // end of init()

	//functions to load the table, add, edit pages	
	public void LoadIndustrySecurityIncidents() {
		//called on initialization of breach-types-table to load table data
		System.out.println("in LoadIndustrySecurityIncidents");	
		this.selectedISI ="";
		query = "SELECT publication_year, " 
				+	"verizon_dbir_industry_name, " 
				+	"breaches_large, " 
				+	"breaches_small, " 
				+	"breaches_total, " 
				+	"breaches_unk, " 
				+	"incidents_large, " 
				+	"incidents_small, " 
				+	"incidents_total, " 
				+	"incidents_unk, " 
				+	"probability_of_attack, " 
				+	"sample_size " 
				+	"FROM appl_auth.industry_security_incidents";
		resultSet = databaseQueryService.runQuery(query);	
		// clear out any old content before retrieving new database info
		isiList.clear();
		iterator = resultSet.iterator();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			this.publication_year = row.getDouble("publication_year");    	    
		    this.verizon_dbir_industry_name= row.getString("verizon_dbir_industry_name");
		    this.breaches_large = row.getDouble("breaches_large");
		    this.breaches_small = row.getDouble("breaches_small");
		    this.breaches_total = row.getDouble("breaches_total");
		    this.breaches_unk = row.getDouble("breaches_unk");
		    this.incidents_large = row.getDouble("incidents_large");
		    this.incidents_small = row.getDouble("incidents_small");
		    this.incidents_total = row.getDouble("incidents_total");
		    this.incidents_unk = row.getDouble("incidents_unk");
		    this.probability_of_attack = row.getDouble("probability_of_attack");
		    this.sample_size = row.getDouble("sample_size");

			isi = new IndustrySecurityIncidents(this.publication_year, this.verizon_dbir_industry_name, this.breaches_large, this.breaches_small, this.breaches_total, this.breaches_unk,
												this.incidents_large, this.incidents_small, this.incidents_total, this.incidents_unk, this.probability_of_attack, this.sample_size);
			isiList.add(isi);	
		}  	//end of while
		
		//sort list in desc order by Publication Year
		List<IndustrySecurityIncidents> sortedList = new ArrayList<IndustrySecurityIncidents>();
		Collections.sort(isiList, IndustrySecurityIncidents.PubYearComparator);
		for(IndustrySecurityIncidents s: isiList) {
			sortedList.add(s);
		}
		System.out.println("isiList b/4 sort: " + isiList.get(0));

		isiList.clear();
		isiList = sortedList;
		System.out.println("isiList: " + isiList.get(0));
		System.out.println("sortedList: " + sortedList.get(0));
	}	//end LoadIndustrySecurityIncidents

	public String LoadISIAddFormData()  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadAddISIFormData");
		this.publication_year = 2017;    	    
	    this.verizon_dbir_industry_name= "";
	    this.breaches_large = 0;
	    this.breaches_small = 0;
	    this.breaches_total = 0;
	    this.breaches_unk = 0;
	    this.incidents_large = 0;
	    this.incidents_small = 0;
	    this.incidents_total = 0;
	    this.incidents_unk = 0;
	    this.probability_of_attack = 0;
	    this.sample_size = 0;
		//goto Add form
		return "/superadmin/industry-security-incidents-add.jsf";
	}	//end LoadISIAddFormData()
	
	public String LoadISIEditFormData(IndustrySecurityIncidents isiEdit) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadISIEditFormData");	
		this.publication_year = isiEdit.getPublication_year();    	    
	    this.verizon_dbir_industry_name= isiEdit.getVerizon_dbir_industry_name();
	    this.breaches_large = isiEdit.getBreaches_large();
	    this.breaches_small = isiEdit.getBreaches_small();
	    this.breaches_total = isiEdit.getBreaches_total();
	    this.breaches_unk = isiEdit.getBreaches_unk();
	    this.incidents_large = isiEdit.getIncidents_large();
	    this.incidents_small = isiEdit.getIncidents_small();
	    this.incidents_total = isiEdit.getIncidents_total();
	    this.incidents_unk = isiEdit.getIncidents_unk();
	    this.probability_of_attack = isiEdit.getProbability_of_attack();
	    this.sample_size = isiEdit.getSample_size();

	    origPubYear = isiEdit.getPublication_year();
	    origVerIndName = isiEdit.getVerizon_dbir_industry_name();
		//System.out.println("orgPubYear/getPubYear : "+ this.origPubYear + "/" + this.publication_year);
		//System.out.println("orgVerIndName/getVerIndName : "+ this.origVerIndName + "/" + this.verizon_dbir_industry_name);
		//goto Edit form
		return "/superadmin/industry-security-incidents-edit.jsf";
	}	//end of LoadISIEditFormData()

	//Functions to push changes to the database table
	public String editISIControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {		
		System.out.println("in editBTControllerMethod");
		//Check to see if primary key changed, if so, insert new record and delete old one
		//System.out.println("orgPubYear/getPubYear : "+ this.origPubYear + "/" + this.getPublication_year());
		//System.out.println("orgVerIndName/getVerIndName : "+ this.origVerIndName + "/" + this.getVerizon_dbir_industry_name());
		
		if(this.origPubYear == this.getPublication_year() && this.origVerIndName.equals(this.getVerizon_dbir_industry_name())) {
			System.out.println("primary key did not changed");
			//only update table
			query = "UPDATE appl_auth.industry_security_incidents "
				+	"SET "
				+	"breaches_large=?, "
				+	"breaches_small=?, "
				+	"breaches_total=?, "
				+	"breaches_unk=?, "
				+	"incidents_large=?, "
				+	"incidents_small=?, "
				+	"incidents_total=?, "
				+	"incidents_unk=?, "
				+	"probability_of_attack=?, "
				+	"sample_size=? "
				+	"WHERE publication_year=? AND verizon_dbir_industry_name=? ";
			
			prepared = session.prepare(query);
			bound = prepared.bind(this.getBreaches_large(), this.getBreaches_small(), this.getBreaches_total(), this.getBreaches_unk(),
					  this.getIncidents_large(), this.getIncidents_small(), this.getIncidents_total(), this.getIncidents_unk(), this.getProbability_of_attack(), this.getSample_size(), 
					  this.publication_year, this.verizon_dbir_industry_name);
			session.execute(bound);
				
		} else {
			System.out.println("primary key changed");
			//insert and delete	
			query="INSERT INTO appl_auth.industry_security_incidents ( "
					+	"publication_year, " 
					+	"verizon_dbir_industry_name, " 
					+	"breaches_large, "
					+	"breaches_small, " 
					+	"breaches_total, " 
					+	"breaches_unk, " 
					+	"incidents_large, " 
					+	"incidents_small, " 
					+	"incidents_total, " 
					+	"incidents_unk, " 
					+	"probability_of_attack, " 
					+	"sample_size) VALUES "
					+ 	"(?,?,?,?,?,?,?,?,?,?,?,?)"; 

			prepared = session.prepare(query);
			bound = prepared.bind(this.getPublication_year(), this.getVerizon_dbir_industry_name(), this.getBreaches_large(), this.getBreaches_small(), this.getBreaches_total(), this.getBreaches_unk(),
								  this.getIncidents_large(), this.getIncidents_small(), this.getIncidents_total(), this.getIncidents_unk(), this.getProbability_of_attack(), this.getSample_size());
			session.execute(bound);

			query = "DELETE FROM appl_auth.industry_security_incidents WHERE "
					+ "publication_year = ? and verizon_dbir_industry_name=? ";
			
			prepared = session.prepare(query);
			bound = prepared.bind(this.origPubYear, this.origVerIndName);
			session.execute(bound);

		}
		//reload table data
		LoadIndustrySecurityIncidents();
		//goto Table form
		return "/superadmin/industry-security-incidents-table.jsf";
}	//end editISIControllerMethod

	public String addISIControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in addISIControllerMethod, pubYear: " + this.getPublication_year());
		
		query="INSERT INTO appl_auth.industry_security_incidents ( "
			+	"publication_year, " 
			+	"verizon_dbir_industry_name, " 
			+	"breaches_large, "
			+	"breaches_small, " 
			+	"breaches_total, " 
			+	"breaches_unk, " 
			+	"incidents_large, " 
			+	"incidents_small, " 
			+	"incidents_total, " 
			+	"incidents_unk, " 
			+	"probability_of_attack, " 
			+	"sample_size) VALUES "
			+ 	"(?,?,?,?,?,?,?,?,?,?,?,?)"; 

	prepared = session.prepare(query);
	bound = prepared.bind(this.getPublication_year(), this.getVerizon_dbir_industry_name(), this.getBreaches_large(), this.getBreaches_small(), this.getBreaches_total(), this.getBreaches_unk(),
						  this.getIncidents_large(), this.getIncidents_small(), this.getIncidents_total(), this.getIncidents_unk(), this.getProbability_of_attack(), this.getSample_size());
	session.execute(bound);


		//reload table data
		LoadIndustrySecurityIncidents();
		//goto Table form
		return "/superadmin/industry-security-incidents-table.jsf";
}   //end addBTControllerMethod
	
	public void deleteISIControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in deleteISIControllerMethod");
		query = "DELETE FROM appl_auth.industry_security_incidents WHERE "
				+ "publication_year = ? and verizon_dbir_industry_name=? ";
		
		prepared = session.prepare(query);
		bound = prepared.bind(isi.getPublication_year(), isi.getVerizon_dbir_industry_name());
		session.execute(bound);

		//reload table data
		this.isiList.remove(isi);
		//LoadIndustrySecurityIncidents();
		//return null;
	}	//end deleteIBCControllerMethod

	public void setSelected(IndustrySecurityIncidents selISI) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		this.isi = selISI;	
		this.selectedISI = selISI.getVerizon_dbir_industry_name();
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Getters/Setters<<<<<<<<<<<<<<<<<<<<<<<<//
	public List<IndustrySecurityIncidents> getIndustrySecurityIncidentData() {
		return isiList;
	}
	
	public List<IndustrySecurityIncidents> getIsiList() {
		return isiList;
	}

	public void setIsiList(List<IndustrySecurityIncidents> isiList) {
		this.isiList = isiList;
	}

	public String getSelectedISI() {
		return selectedISI;
	}

	public void setSelectedISI(String selectedISI) {
		this.selectedISI = selectedISI;
	}

	public IndustrySecurityIncidents getIsi() {
		return isi;
	}

	public void setIsi(IndustrySecurityIncidents isi) {
		this.isi = isi;
	}

	public double getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(double publication_year) {
		this.publication_year = publication_year;
	}

	public double getBreaches_large() {
		return breaches_large;
	}

	public void setBreaches_large(double breaches_large) {
		this.breaches_large = breaches_large;
	}

	public double getBreaches_small() {
		return breaches_small;
	}

	public void setBreaches_small(double breaches_small) {
		this.breaches_small = breaches_small;
	}

	public double getBreaches_total() {
		return breaches_total;
	}

	public void setBreaches_total(double breaches_total) {
		this.breaches_total = breaches_total;
	}

	public double getBreaches_unk() {
		return breaches_unk;
	}

	public void setBreaches_unk(double breaches_unk) {
		this.breaches_unk = breaches_unk;
	}

	public double getIncidents_large() {
		return incidents_large;
	}

	public void setIncidents_large(double incidents_large) {
		this.incidents_large = incidents_large;
	}

	public double getIncidents_small() {
		return incidents_small;
	}

	public void setIncidents_small(double incidents_small) {
		this.incidents_small = incidents_small;
	}

	public double getIncidents_total() {
		return incidents_total;
	}

	public void setIncidents_total(double incidents_total) {
		this.incidents_total = incidents_total;
	}

	public double getIncidents_unk() {
		return incidents_unk;
	}

	public void setIncidents_unk(double incidents_unk) {
		this.incidents_unk = incidents_unk;
	}

	public double getProbability_of_attack() {
		return probability_of_attack;
	}

	public void setProbability_of_attack(double probability_of_attack) {
		this.probability_of_attack = probability_of_attack;
	}

	public double getSample_size() {
		return sample_size;
	}

	public void setSample_size(double sample_size) {
		this.sample_size = sample_size;
	}

	public String getVerizon_dbir_industry_name() {
		return verizon_dbir_industry_name;
	}

	public void setVerizon_dbir_industry_name(String verizon_dbir_industry_name) {
		this.verizon_dbir_industry_name = verizon_dbir_industry_name;
	}

	public double getOrigPubYear() {
		return origPubYear;
	}

	public void setOrigPubYear(double origPubYear) {
		this.origPubYear = origPubYear;
	}

	public String getOrigVerIndName() {
		return origVerIndName;
	}

	public void setOrigVerIndName(String origVerIndName) {
		this.origVerIndName = origVerIndName;
	}	
}
                                                                                      
 
