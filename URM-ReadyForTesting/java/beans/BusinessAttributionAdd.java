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
 * 3.21.2018		tammy.bogart		created
 */

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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

@Named("BusinessAttributionAdd")
@RequestScoped
public class BusinessAttributionAdd {

	private static final long serialVersionUID = 1L;

	@Inject private UserBean currentUser;

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Iterator<Row> iterator;

	private String busName, bit, busCrit, infClass, dbType;
	private BigDecimal riskAppetite, annRev, recordCount;
	private int annRevYear;
	private List<String> bits;
	private List<String> bcrits;
	private List<String> iclass;
	private List<String> dbtypes;

	

	// constructor
	public BusinessAttributionAdd() {
		System.out.println("*** in BusinessAttributionAdd constructor ***");
	}
	
	@PostConstruct
	public void init() {

		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get List of all possible drop-down values
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
			}	//end switch
		}	//end while
	}	//end init
	
 
	public String addBusinessAttributionActionControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
			String queryString = "INSERT INTO vmasc.business_value_attribution\r\n" + 
				"(site_id, business_process_id, annual_revenue, annual_revenue_year, business_criticality, business_interruption_threshold, business_process_name, default_breach_type, " +
				"information_classification, record_count, risk_appetite, audit_upsert)" + 
				" VALUES(" + currentUser.getSiteID() + ", uuid() ," + annRev + "," + annRevYear + ", '" + busCrit + "', '" + bit + "', '" + busName + "', '" + dbType + "', '" + infClass + "', " + recordCount + ", " + riskAppetite + 
				" , {datechanged:dateof(now()), changedbyusername:'" + currentUser.getEmail() + "'})";    

				System.out.println("Query: " +queryString);
				
			// get the Database Query Service object for this Organization
			DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
						
			databaseQueryService.runUpdateQuery(queryString);
		
			// go back to the Business Attribution page
			return "business-attribution-table";
		
	}	//end AddBAACM

///////////////////////Getters and Setters///////////////////////////////

	public UserBean getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserBean currentUser) {
		this.currentUser = currentUser;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBit() {
		return bit;
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

	public BigDecimal getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(BigDecimal recordCount) {
		this.recordCount = recordCount;
	}

	public int getAnnRevYear() {
		return annRevYear;
	}

	public void setAnnRevYear(int annRevYear) {
		this.annRevYear = annRevYear;
	}

	public List<String> getBits() {
		return bits;
	}

	public void setBits(List<String> bits) {
		this.bits = bits;
	}

	public List<String> getBcrits() {
		return bcrits;
	}

	public void setBcrits(List<String> bcrits) {
		this.bcrits = bcrits;
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
