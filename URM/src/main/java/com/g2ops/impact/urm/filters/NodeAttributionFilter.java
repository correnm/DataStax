package com.g2ops.impact.urm.filters;

/**
 * @author 		Sara Prokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * 
 * This class is a filter bean (washington/filters) that is utilized by the business-attribution-edit.xhtml, which is used to edit the 
 * edit the Business Attribution Table.
 * 
 * Date				Author				Revision Description
 *19-Sept-2017		sara.prokop			Creator
 *26-Sept-2017		sara.prokop			temporarily hard-coded the drop down values
 *6-Oct-2017		sara.bergman		changed class name from BusinessAttributionFilter to NodeAttributionFilter
 * 
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.beans.NodeAttributionTable;
import com.g2ops.impact.urm.types.NodeAttribution;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("dtFilterView")
@RequestScoped
public class NodeAttributionFilter {
	
	
	private List<NodeAttribution> attList;
	//private List<NodeAttribution> filteredAttList;
	
	//default for filter is no filter
	private String vendor 		= null;
	private String ip 			= null;
	private String os			= null;
	private String sysType 		= null;
	private String assetType 	= null;
	private String assetVis		= null;
	
	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private Iterator<Row> iterator;
	private String colValue;
	private List<String> ddOptions = new ArrayList<String>();
	
	@Inject private NodeAttributionTable table;
	
	@PostConstruct
	public void init(){
		attList = filter();
	}
	
	//unnecessary since ip- look up will be a dropdown
	//	public List<String> getIPs(){
//		return table.getIPs();
//	}

	public List<NodeAttribution> getAtts(){
		return attList;
	}
//	public void setFilteredList(List<BusinessAttribution> filteredList){
//		this.filteredAttList = filteredList;
//	}
	public void setTable (NodeAttributionTable table ){
		this.table = table;
	}
	
	//populate the drop down columns for Asset Types and Asset Visibility
	public List<String> ddAssetTypes(){
		ddOptions.clear();
		populateBusinessOptions("Asset Type");
		return ddOptions;
	}
	public List<String> ddAssetVisibility(){
		ddOptions.clear();
		populateBusinessOptions("Asset Visibility");
		return ddOptions;
	}
	
	public static List<String> ddVendors(){
		//temp hard-coded drop-down list
		List<String> vendorList = new ArrayList<String>();
		vendorList.add("ADVANTECH CO.");
		vendorList.add("Dell Inc.");
		vendorList.add("Hewlett Packard");
		vendorList.add("VMware");
		return vendorList;
		
		//to be used when materialized views are created
//		ddOptions.clear();
//		populateHardwareOptions("vendor");
//		return ddOptions;
	}
	
	public static List<String> ddOS(){
		//temp hard-coded drop-down list
		List<String> osList = new ArrayList<String>();
		osList.add("other");
		osList.add("linux");
		osList.add("windows");
		return osList;
		
		//to be used when materialized views are created
//		ddOptions.clear();
//		populateHardwareOptions("os_general");
//		return ddOptions;
	}
	
	public static List<String> ddSysTypes(){
		//temp hard-coded drop-down list
		List<String> sysTypesList = new ArrayList<String>();
		sysTypesList.add("embedded");
		sysTypesList.add("general-purpose");
		sysTypesList.add("switch");
		return sysTypesList;
		
		//to be used when materialized views are created
//		ddOptions.clear();
//		populateHardwareOptions("system_type");
//		return ddOptions;
	}
	
	//getting drop down options from business_practive table
	public void populateBusinessOptions(String col){

		String query = "SELECT business_value from business_practice where category = ?";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(col);
		rs = dbSession.execute(bound);
		setOptions();
	}
	
	//getting drop down options from hardware_by_vendor_ip_address_os_general_system_type_asset_type_asset_visibility materialized view in hardware table
	public void populateHardwareOptions(String col){
		
		String query = "SELECT ? DISTINCT from hardware_by_vendor_ip_address_os_general_system_type_asset_type_asset_visibility";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(col);
		rs = dbSession.execute(bound);
		setOptions();
	}
	
	//populating the list used to populate drop-downs
	public void setOptions(){
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
		 row = iterator.next();
		 colValue = row.getString("business_value");
		 ddOptions.add(colValue);
		}
	}
	
	public void getData(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vendor = request.getParameter("vendor");
		String ip = request.getParameter("ip");
		String os = request.getParameter("os");
		String sysType = request.getParameter("sysType");
		String assetType = request.getParameter("assetType");
		String assetVis = request.getParameter("assetVis");
		
		setVendorFilter(vendor);
		setIpFilter(ip);
		setOsFilter(os);
		setSysTypeFilter(sysType);
		setAssetTypeFilter(assetType);
		setAssetVisFilter(assetVis);
		
	}
	//sets the specific column value and filters the table
	public void setVendorFilter(String vendor){
		this.vendor = vendor;
		filter();
	}
	public void setIpFilter(String ip){
		this.ip = ip;
		filter();
	}
	public void setOsFilter(String os){
		this.os = os;
		filter();
	}
	public void setSysTypeFilter(String sysType){
		this.sysType = sysType;
		filter();
	}
	public void setAssetTypeFilter (String assetType){
		this.assetType= assetType;
		filter();
	}
	public void setAssetVisFilter(String assetVis){
		this.assetVis = assetVis;
		filter();
	}
	
	public List<NodeAttribution> filter(){
//		filteredAttList.clear();
		attList = table.filter(vendor, ip, os, sysType, assetType, assetVis);
		
		return attList;
	}
	
	
}
