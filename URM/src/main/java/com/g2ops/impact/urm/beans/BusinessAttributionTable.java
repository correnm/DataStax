package com.g2ops.impact.urm.beans;

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

import com.g2ops.impact.urm.types.BusinessAttribution;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("businessAttributionTable")
@RequestScoped
public class BusinessAttributionTable {

	private ResultSet rs;
	private Iterator<Row> iterator;
	private BusinessAttribution att;
	private List<BusinessAttribution> attList = new ArrayList<BusinessAttribution>();
		
	public List<BusinessAttribution> getBusinessAttData() {

		// clear out any old content before retrieving new database info
		attList.clear();
			
		// execute the query against the table
		populateBusinessAtt();

		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();

			att = new BusinessAttribution(row.getString("ip_address"), 
					row.getString("os_general"), 
					row.getString("system_type"), 
					row.getString("asset_type"), 
					row.getString("asset_visibility"));
			// Save each database record to the list.
			attList.add(att);
		}
			
		return attList;

	}

	private void populateBusinessAtt() {

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService();
			
		// execute the query
		rs = databaseQueryService.runQuery("select ip_address, os_general, system_type, asset_type, asset_visibility from hardware");

	}

}
