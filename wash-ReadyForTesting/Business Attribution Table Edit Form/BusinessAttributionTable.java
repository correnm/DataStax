package com.g2ops.washington.beans;
/**
 * @author 		 Sara Prokop G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * 
 * This class is a bean that is utilized by the business-attribution-table.xhtml, which displays
 * the Business Attribution Table.
 * 
 * Date				Author				Revision Description
 * 7-13-2017		Sara Prokop			Creator
 * 7-27-2017		Sara Prokop			Added the primary key fields
 * 
 */
	import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.washington.types.BusinessAttribution;
import com.g2ops.washington.utils.DatabaseQueryService;

	@ManagedBean
	@ViewScoped
	public class BusinessAttributionTable {

		private DatabaseQueryService dqs = new DatabaseQueryService();
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
						row.getString("asset_visibility"), 
						row.getString("business_interruption_threshold"), 
						row.getString("business_criticality"),
						row.getString("information_classification_value"),
						//added these primary key columns to retrieve the values of a single row
						row.getString("site_or_ou_name"),
						row.getString("ip_subnet_or_building"),
						row.getUUID("internal_system_id"));
				// Save each database record to the list.
				attList.add(att);
			}
			
			return attList;

		}

		private void populateBusinessAtt() {

			rs = dqs.RunQuery("select ip_address, os_general, system_type, asset_type, asset_visibility, business_interruption_threshold, business_criticality, "
					+ "information_classification_value, site_or_ou_name, ip_subnet_or_building, internal_system_id from hardware");
		}
}
