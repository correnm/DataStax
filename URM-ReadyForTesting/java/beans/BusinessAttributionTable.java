package com.g2ops.impact.urm.beans;

import javax.annotation.PostConstruct;

/**
 * @author 		Sara Prokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date			Author			Revision Description
 * 3/14/2018	Tammy Bogart	 Changed the form to pull the Business Value Attribution table data vs the hardware table data
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.types.BusinessAttribution;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("businessAttributionTable")
@RequestScoped
public class BusinessAttributionTable {

	@Inject private UserBean currentUser;

	private Session dbSession;
	private String siteID;
	private ResultSet rs;
	private Iterator<Row> iterator;
	private BusinessAttribution att;
	private List<BusinessAttribution> attList = new ArrayList<BusinessAttribution>();
		
	public BusinessAttributionTable() {

		System.out.println("*** in BusinessAttributionTable constructor ***");

	}
	
	@PostConstruct
	public void init() {

		// execute the query against the table
		dbSession = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
			
		// execute the query
		String query = "select site_id, business_process_id, business_process_name, business_interruption_threshold, business_criticality, information_classification, "
				+ "default_breach_type, annual_revenue, annual_revenue_year, runs_on_hosts, risk_appetite, record_count, resistance_strength from business_value_attribution";
		rs = databaseQueryService.runQuery(query);
		
		// clear out any old content before retrieving new database info
		attList.clear();
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();
			siteID = row.getUUID("site_id").toString();
			this.setSiteID(siteID);
			UUID busProcID = row.getUUID("business_process_id");
			String busName = row.getString("business_process_name");
			String BIT = row.getString("business_interruption_threshold");
			String busCrit = row.getString("business_criticality");
			String infoClass = row.getString("information_classification");
			String breachType = row.getString("default_breach_type");
			BigDecimal annRev = row.getDecimal("annual_revenue");
			int annRevYear = row.getInt("annual_revenue_year");
			BigDecimal recCount = row.getDecimal("record_count");
			BigDecimal resStrength =  row.getDecimal("resistance_strength");
			BigDecimal riskAppetite = row.getDecimal("risk_appetite");

				
				att = new BusinessAttribution(UUID.fromString(siteID), busProcID, busName, BIT, busCrit, infoClass, breachType, annRev, annRevYear, recCount, resStrength, riskAppetite);
				// Save each database record to the list.
				attList.add(att);
			}	//end of while
		}	//end of init


	public List<BusinessAttribution> getBusinessAttData() {

		return attList;

	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}
	
	

}
