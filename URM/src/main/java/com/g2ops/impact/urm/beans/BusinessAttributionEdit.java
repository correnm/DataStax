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

	private String bvaToEditName, bit, busCrit, infClass, dBType;
	private BigDecimal riskAppetite, annRev, recordCount;

	private int annRevYear;
	private UUID siteID, busProcID;
	private List<String> BITs, bCRITs, iClass, bTypes;
	 
	// constructor
	public BusinessAttributionEdit() {
		System.out.println("*** in BusinessAttributionEdit constructor ***");
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
				this.BITs = new ArrayList<String>(bitMap.values());
				break;
			case "business_criticality" :
				Map<String, String> critMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.bCRITs = new ArrayList<String>(critMap.values());
				break;
			case "breach_type" :
				Map<String, String> typeMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.bTypes = new ArrayList<String>(typeMap.values());
				break;				
			case "information_classification" :
				Map<String, String> iclassMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.iClass = new ArrayList<String>(iclassMap.values());							
				break;
			}
		}
	}
	
	// method for getting the data for the user being edited
	public void retrievebvaData() {
		System.out.println("siteID in retrievebvaData: "+siteID);
		System.out.println("busProcID in retrievebvaData: "+busProcID);

		// select the business attributes
		if (siteID != null){
			String query = "select business_process_name, business_interruption_threshold, business_criticality, information_classification, " + 
				"default_breach_type, annual_revenue, annual_revenue_year, runs_on_hosts, risk_appetite, record_count, resistance_strength " + 
				"from business_value_attribution where site_id="+ siteID +" and business_process_id=" + busProcID;	
	
			rs = databaseQueryService.runQuery(query);
			// get the result record
			row = rs.one();
		
			// set values to what was returned by the query
			bvaToEditName = row.getString("business_process_name");
			bit = row.getString("business_interruption_threshold");
			busCrit = row.getString("business_criticality");
			infClass = row.getString("information_classification");
			riskAppetite = row.getDecimal("risk_appetite");
			dBType = row.getString("default_breach_type");
			annRev = row.getDecimal("annual_revenue");
			annRevYear = row.getInt("annual_revenue_year");
			recordCount  = row.getDecimal("record_count");
		} else {
			System.out.println("could not retreive data b/c siteID is null - BusinessAttributionEdit.java ln 127");
		}
	}

		public String editBVAControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {

		// get the Database Query Service object for this Organization
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

	//UPDATE business_value_attribution
		if (siteID != null){
			databaseQueryService.runUpdateQuery("update business_value_attribution set business_process_name='" + this.bvaToEditName +"', business_interruption_threshold='" + this.bit +
					"',business_criticality='" + this.busCrit +"',information_classification='', risk_appetite=" + this.riskAppetite +", default_breach_type='" + this.dBType + 
					"',annual_revenue=" + this.annRev + " , annual_revenue_year=" + this.annRevYear + ", record_count= " + this.recordCount + 
					" , audit_upsert = { datechanged : dateof(now()), changedbyusername : '" + currentUser.getEmail() + 
					"' } where site_id = " + siteID + " and business_process_id= " + busProcID);
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

	public String getBusCrit() {
		return busCrit;
	}

	public void setBusCrit(String busCrit) {
		this.busCrit = busCrit;
	}

	public String getInfClass() {
		return infClass;
	}

	public void setInfClass(String infClass) {
		this.infClass = infClass;
	}

	public String getdBType() {
		return dBType;
	}

	public void setdBType(String dBType) {
		this.dBType = dBType;
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

	public UUID getSiteID() {
		return siteID;
	}

	public void setSiteID(UUID msiteID) {
		this.siteID = msiteID;
	}

	public UUID getBusProcID() {
		return busProcID;
	}

	public void setBusProcID(UUID busProcID) {
		this.busProcID = busProcID;
	}

	public List<String> getBITs() {
		return BITs;
	}

	public void setBITs(List<String> bITs) {
		BITs = bITs;
	}

	public List<String> getbCRITs() {
		return bCRITs;
	}

	public void setbCRITs(List<String> bCRITs) {
		this.bCRITs = bCRITs;
	}

	public List<String> getiClass() {
		return iClass;
	}

	public void setiClass(List<String> iClass) {
		this.iClass = iClass;
	}

	public List<String> getbTypes() {
		return bTypes;
	}

	public void setbTypes(List<String> bTypes) {
		this.bTypes = bTypes;
	}

}

