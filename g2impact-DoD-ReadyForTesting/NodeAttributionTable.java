package com.g2ops.washington.beans;

/**
 * @author 		Sara PRokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * @see			<a href="URL#value">label</a>
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 12-July-2017		sara.prokop			Created this Java class
 * 11-Sept-2017		sara.prokop			took out business interruption threshold, business criticality, and classification
 * 										used prepared statements for db query
 * 14-Sept-2017		sara.prokop			added reportable_flag to Business Attribution
 * 6-Oct-2017 		sara.bergman		changed class name from BusinessAttributionTable to NodeAttributionTable
 * 
 */

	import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.washington.types.BusinessAttribution;
import com.g2ops.washington.types.NodeAttribution;
import com.g2ops.washington.utils.DatabaseQueryService;
import com.g2ops.washington.utils.SessionUtils;

	@ManagedBean
	@ViewScoped
	public class NodeAttributionTable {

		private DatabaseQueryService dqs = new DatabaseQueryService();
		private Session dbSession = SessionUtils.getOrgDBSession();
		private ResultSet rs;
		private Iterator<Row> iterator;
		private NodeAttribution att;
		private List<NodeAttribution> attList = new ArrayList<NodeAttribution>();
	
		//table column fields
		private String vendor;
		private String ip;
		private String os;
		private String sysType;
		private String assetType;
		private String assetVis;
		private boolean flag;
		

		
		public List<NodeAttribution> getNodeAttData() {
			 // gets all entries with no filter
			String query = "select vendor, ip_address, os_general, system_type, asset_type, asset_visibility, reportable_flag, "
					+ "internal_system_id, site_or_ou_name, ip_subnet_or_building from hardware";
			rs = dqs.runQuery(query);
			
			setRows();
			return attList;
		}
		//set as seperate method to allow for filtering
		public void setRows(){
			// clear out any old content before retrieving new database info
			attList.clear();
			iterator = rs.iterator();
			
			//iterate over the results. 
			while (iterator.hasNext()) {
				Row row = iterator.next();
				String vendor = row.getString("vendor");
				String ip = row.getString("ip_address");
				String os = row.getString("os_general");
				String sysType = row.getString("system_type");
				String assetType = row.getString("asset_type");
				String assetVis = row.getString("asset_visibility");
				boolean flag = row.getBool("reportable_flag");
				UUID UUid = row.getUUID("internal_system_id");
				String site = row.getString("site_or_ou_name");
				String sub = row.getString("ip_subnet_or_building");
				
				att = new NodeAttribution(vendor, ip, os, sysType, assetType, assetVis,
						flag, UUid, site, sub);
				// Save each database record to the list.
				attList.add(att);
			}
		}
		
		public List<NodeAttribution> filter(String vendor, String ip, String os, String sysType, String assetType, String assetVis){

			//a list of all the variables that need to be bound into the query
			List<String> searchBy = new ArrayList<String>();
			
			//query is dynamic depending on what values are selected for the query. 
			//can work without a materialized view, but less efficient
			String query = "SELECT * FROM hardware WHERE vendor = ? and ip_address = ? and os_general = ? and system_type = ? and asset_type = ? and asset_visibility = ? ALLOW FILTERING";
//			String query = "SELECT * FROM hardware_by_vendor_ip_address_os_general_system_type_asset_type_asset_visibility WHERE vendor = ? and ip_address = ? and os_general = ? and system_type = ? and asset_type = ? and asset_visibility = ? ";
			
			//determines what the query looks. if a column has a value, it is used as a filter. if it is null, it is excluded from the query
			if (vendor == null){
				query = query.replace("vendor = ? and", "");
			}else if(vendor != null){
				searchBy.add(vendor);
			}
			if (ip == null){
				query = query.replace("ip_address = ? and", "");
			}else if(ip != null){
				searchBy.add(ip);
			}
			if (os == null){
				query = query.replace("os_general = ? and", "");
			}else if (os != null){
				searchBy.add(os);
			}
			if (sysType == null){
				query = query.replace("system_type = ? and", "");
			}else if(sysType != null){
				searchBy.add(sysType);
			}
			if (assetType == null){
				query = query.replace("asset_type = ? and", "");
			}else if(assetType != null){
				searchBy.add(assetType);
			}
			if (assetVis == null){
				query = query.replace("asset_visibility = ?", "");
			}else if(assetType != null){
				searchBy.add(assetType);
			}
			
			if(searchBy.size()>=1){
				PreparedStatement prepared = dbSession.prepare(query);
				BoundStatement bound = prepared.bind(searchBy);
				
				rs = dbSession.execute(bound);
				setRows();	
//				return attList;
//			}else{
//			getBusinessAttData();
//			return attList;
			}
			return attList;
		}
}
