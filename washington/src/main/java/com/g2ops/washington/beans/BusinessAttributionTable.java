package com.g2ops.washington.beans;

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
						row.getString("information_classification_value"));
				// Save each database record to the list.
				attList.add(att);
			}
			
			return attList;

		}

		private void populateBusinessAtt() {

			rs = dqs.RunQuery("select ip_address, os_general, system_type, asset_type, asset_visibility, business_interruption_threshold, business_criticality, information_classification_value from hardware");
		}
}
