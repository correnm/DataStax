package com.g2ops.impact.urm.beans;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, March 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 3.15.2018		tammy.bogart		created
 */

import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("BusinessAttributionEdit")
@SessionScoped
public class BusinessAttributionEdit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject private UserBean currentUser;

 DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Row row;
	private Iterator<Row> iterator;
	private String bvaToEditName, bit, busCrit, infClass, dbType, siteID, busProcID;
	private BigDecimal riskAppetite, annRev, recordCount;
	private int annRevYear;
	private List<String> bits;
	private List<String> bcrits;
	private List<String> iclass;
	private List<String> dbtypes;
	 
	// constructor
	public BusinessAttributionEdit() {
		System.out.println("*** in BusinessAttributionEdit constructor ***");
		System.out.print("siteID: " + siteID);

	}
	
	@PostConstruct
	public void init() {

		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get List of all possible BITs
		String query = "select database_column, option_values from lov_references where database_column in('business_interruption_threshold', 'business_criticality', 'breach_type', 'information_classification')";
		rs = databaseQueryService.runQuery(query);
		iterator = rs.iterator();
				
		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();
			String dbColumn = row.getString("database_column");
			switch(dbColumn) {
			case "business_interruption_threshold" :
				Map<String, String> bitMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.bits = new ArrayList<String>(bitMap.values());
				break;
			case "business_criticality" :
				Map<String, String> critMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.bcrits = new ArrayList<String>(critMap.values());
				break;
			case "breach_type" :
				Map<String, String> typeMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.dbtypes = new ArrayList<String>(typeMap.values());
				break;				
			case "information_classification" :
				Map<String, String> iclassMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.iclass = new ArrayList<String>(iclassMap.values());							
				break;
			}
		}
	}
	
	// method for getting the data for the user being edited
	public void retrievebvaData() {
		// select the business attributes
		if (siteID != null){
			String query = "select business_process_name, business_interruption_threshold, business_criticality, information_classification, " + 
				"default_breach_type, annual_revenue, annual_revenue_year, runs_on_hosts, risk_appetite, record_count, resistance_strength " + 
				"from business_value_attribution where site_id="+ UUID.fromString(siteID) +" and business_process_id=" + UUID.fromString(busProcID);	
	
			rs = databaseQueryService.runQuery(query);
			// get the result record
			row = rs.one();
		
			// set values to what was returned by the query
			bvaToEditName = row.getString("business_process_name");
			bit = row.getString("business_interruption_threshold");
			busCrit = row.getString("business_criticality");
			infClass = row.getString("information_classification");
			riskAppetite = row.getDecimal("risk_appetite");
			dbType = row.getString("default_breach_type");
			annRev = row.getDecimal("annual_revenue");
			annRevYear = row.getInt("annual_revenue_year");
			recordCount  = row.getDecimal("record_count");
		} else {
			System.out.println("could not retreive data b/c siteID is null - BusinessAttributionEdit.java ln 127");
		}
	}

	public String editBVAControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		if (siteID != null){
			
			// get the Database Query Service object for this Organization
			DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

			String queryUpdate = "update business_value_attribution set business_process_name='" + bvaToEditName +"', business_interruption_threshold='" +bit +
					"',business_criticality='" + busCrit +"',information_classification='" + infClass + "', risk_appetite=" + riskAppetite +", default_breach_type='" + dbType + 
					"',annual_revenue=" + annRev + " , annual_revenue_year=" + annRevYear + ", record_count= " + recordCount + 
					" , audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + "' } " +
					"where site_id = " + UUID.fromString(siteID) + " and business_process_id= " + UUID.fromString(busProcID);
			
			System.out.println("query: " +queryUpdate);
			
			databaseQueryService.runUpdateQuery(queryUpdate);
			
			
		} else {
			System.out.println("could not update db bc siteID is null - BusinessAttributionEdit.java ln 141");
		}
		// go back to the Business Attribution page
		return "business-attribution-table";
	}

///////////////////////Getters and Setters///////////////////////////////

	public UserBean getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserBean currentUser) {
		this.currentUser = currentUser;
	}

	public String getBvaToEditName() {
		return bvaToEditName;
	}

	public void setBvaToEditName(String bvaToEditName) {
		this.bvaToEditName = bvaToEditName;
	}

	public String getBit() {
		return bit;
	}
	
	public BigDecimal getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(BigDecimal recordCount) {
		this.recordCount = recordCount;
	}

	public void setBit(String bit) {
		this.bit = bit;
	}

	public List<String> getBits() {
		return bits;
	}

	public void setBits(List<String> bits) {
		this.bits = bits;
	}

	public String getBusCrit() {
		return busCrit;
	}

	public void setBusCrit(String busCrit) {
		this.busCrit = busCrit;
	}
	
	public List<String> getBcrits() {
		return bcrits;
	}

	public void setBcrits(List<String> bcrits) {
		this.bcrits = bcrits;
	}

	public String getInfClass() {
		return infClass;
	}

	public void setInfClass(String infClass) {
		this.infClass = infClass;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public BigDecimal getRiskAppetite() {
		return riskAppetite;
	}

	public void setRiskAppetite(BigDecimal riskAppetite) {
		this.riskAppetite = riskAppetite;
	}

	public BigDecimal getAnnRev() {
		return annRev;
	}

	public void setAnnRev(BigDecimal annRev) {
		this.annRev = annRev;
	}

	public int getAnnRevYear() {
		return annRevYear;
	}

	public void setAnnRevYear(int annRevYear) {
		this.annRevYear = annRevYear;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public String getBusProcID() {
		return busProcID;
	}

	public void setBusProcID(String busProcID) {
		this.busProcID = busProcID;
	}

	public List<String> getIclass() {
		return iclass;
	}

	public void setIclass(List<String> iclass) {
		this.iclass = iclass;
	}

	public List<String> getDbtypes() {
		return dbtypes;
	}

	public void setDbtypes(List<String> dbtypes) {
		this.dbtypes = dbtypes;
	}

}

