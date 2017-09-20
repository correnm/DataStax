package com.g2ops.washington.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.washington.types.BusinessPractice;
import com.g2ops.washington.utils.DatabaseQueryService;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@ViewScoped
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

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();
		
		// get the Database Query Service instance from the user's session
		databaseQueryService = (DatabaseQueryService)userSession.getAttribute("databaseQueryService");
		
		// execute the query
		rs = databaseQueryService.runQuery("select category, business_value,collateral_damage_current, target_distribution_current, confidentiality_req_current, integrity_req_current, availability_req_current from business_practice");

	}

}
