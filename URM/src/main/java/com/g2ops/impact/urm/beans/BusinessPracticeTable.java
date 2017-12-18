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

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.g2ops.impact.urm.types.BusinessPractice;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("businessPracticeTable")
@RequestScoped
public class BusinessPracticeTable {

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Iterator<Row> iterator;
	private BusinessPractice biz;
	private List<BusinessPractice> bizList = new ArrayList<BusinessPractice>();
	
	public List<BusinessPractice> getBusinessData() {
		// clear out any old content before retrieving new database info
		bizList.clear();
		
		// execute the query against the table
		populateBusinessData();

		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();

			biz = new BusinessPractice(row.getString("category"), 
					row.getString("business_value"), 
					row.getDecimal("collateral_damage_current"), 
					row.getDecimal("target_distribution_current"), 
					row.getDecimal("confidentiality_req_current"), 
					row.getDecimal("integrity_req_current"), 
					row.getDecimal("availability_req_current"));
			// Save each database record to the list.
			bizList.add(biz);
		}
		
		return bizList;

	}

	private void populateBusinessData () {

		// get the Database Query Service object for this Org
		databaseQueryService = SessionUtils.getOrgDBQueryService();
		
		// execute the query
		rs = databaseQueryService.runQuery("select category, business_value, collateral_damage_current, target_distribution_current, confidentiality_req_current, integrity_req_current, availability_req_current from business_practice");

	}

}
