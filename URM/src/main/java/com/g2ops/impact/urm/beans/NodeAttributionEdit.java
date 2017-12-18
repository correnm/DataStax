package com.g2ops.impact.urm.beans;

/**
 * @author 		Sara Prokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * 
 * This class is a bean that is utilized by the business-attribution-edit.xhtml, which is used to edit the 
 * edit the Business Attribution Table.
 * 
 * Date				Author				Revision Description
 * 7-27-2017		sara.prokop			Creator
 * 14-Sept-2017		sara.prokop			Added database entry capability 
 * 6-Oct-2017		sara.bergman		Changed class name from BusinessAttributionEdit to NodeAttributionEdit
 * 
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.context.FacesContext;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.filters.NodeAttributionFilter;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("nodeAttributionEdit")
@RequestScoped
public class NodeAttributionEdit {
	
	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private String ip, osType, sysType, assetType, assetVis, vendor;
	private boolean reportable;
	private String siteORouName,ipSubOrBuilding;
	private UUID internalSysID;
	private String site, sub;
	private UUID UUid;
	private Iterator<Row> iterator;
	private String colValue;
	private List<String> ddOptions = new ArrayList<String>();
	
	public NodeAttributionEdit() {
	
		//getting the parameters (keys) when the edit icon is pressed
		FacesContext fc = FacesContext.getCurrentInstance();
		this.UUid = getIdParam(fc);
		this.site = getSiteParam(fc);
		this.sub = getSubParam(fc);
		
		//queries the specific entry
		String query = "select vendor, ip_address, os_general, system_type, asset_type, asset_visibility, reportable_flag from hardware"
				+ " where internal_system_id = ? and site_or_ou_name = ? and ip_subnet_or_building = ?"; //site_or_ou_name = '96a3378e-2f67-4b9d-ab67-9ee9d533b88f' and ip_subnet_or_building = '10.106'";

		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(UUid, site, sub);
		rs = dbSession.execute(bound);
		
		// get the result values
		row = rs.one();

		// set values to what was returned by the query
		vendor= row.getString("vendor");
		ip = row.getString("ip_address");
		osType = row.getString("os_general");
		sysType = row.getString("system_type");
		assetType = row.getString("asset_type");
		assetVis = row.getString("asset_visibility");
		reportable = row.getBool("reportable_flag");
//		siteORouName = row.getString("site_or_ou_name");
//		ipSubOrBuilding = row.getString("ip_subnet_or_building");
//		internalSysID = row.getUUID("internal_system_id");
		
	}
	//methods utilize a map that maps the name of the parameter to its value 
	public UUID getIdParam(FacesContext fc){
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String id = params.get("id");
		UUID UUid = UUID.fromString(id);
		return UUid;
	}
	
	public String getSiteParam(FacesContext fc){
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String site = params.get("site");
		return site;
	}

	public String getSubParam(FacesContext fc){
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		String sub = params.get("sub");
		return sub;
	}
	public void setVendor(String vendor){
		this.vendor = vendor;
	}
	public String getVendor(){
		return vendor;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	public void setOsType(String type){
		this.osType = type;
	}
	public String getOsType() {
		return osType;
	}
	public void setSysType(String type){
		this.sysType = type;
	}
	public String getSysType() {
		return sysType;
	}
	public void setAssetType(String type){
		this.assetType = type;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetVis(String vis){
		this.assetVis = vis;
	}
	public String getAssetVis() {
		return assetVis;
	}
	public void setReportable(boolean flag){
		this.reportable = flag;
	}
	public boolean getReportable(){
		return reportable;
	}
	
	
	//get keys for editing unique rows
	public String getSiteORouName(){
		return siteORouName;
	}
	
	public String getIpSubOrBuilding(){
		return ipSubOrBuilding;
	}

	public UUID getInternalSysID(){
		return internalSysID;
	}
	
	//methods called by the business-attribution-edit.xhtml to set category value
	public List<String> ddAssetTypes(){
		ddOptions.clear();
		populateDropDowns("Asset Type");
		return ddOptions;
	}
	
	public List<String> ddAssetVisibility(){
		ddOptions.clear();
		populateDropDowns("Asset Visibility");
		return ddOptions;
	}
	
	public List<String> ddVendors(){
		ddOptions.clear();
		ddOptions = NodeAttributionFilter.ddVendors();;
		return ddOptions;
	}
	
	public List<String> ddOS(){
		ddOptions.clear();
		ddOptions = NodeAttributionFilter.ddOS();
		return ddOptions;
	}
	
	public List<String> ddSysTypes(){
		ddOptions.clear();
		ddOptions = NodeAttributionFilter.ddSysTypes();
		return ddOptions;
	}
	
	//populates the options for the dropdowns
	public void populateDropDowns(String col){
		// execute query to get drop down options for each column in the table
		String query = "select business_value from business_practice where category = ?";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(col);
		rs = dbSession.execute(bound);
//		rs = dbSession.execute("select business_value from business_practice where category =" + col);
		
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
		 row = iterator.next();
		 colValue = row.getString("business_value");
		 System.out.println("colValue: " + colValue);
		 ddOptions.add(colValue);
		}
	}
	
	public void setNull(String value){
		if (value == "null")
			value = null;
	}
	
	//saves the new entry into the Cassandra database
	public void editNodeAttributionActionControllerMethod() {

		
		String query = "UPDATE hardware SET vendor = ?, ip_address = ?, os_general = ? , system_type = ?, asset_type = ?, asset_visibility = ?, reportable_flag = ?"
				+ " where internal_system_id = ? and site_or_ou_name = ? and ip_subnet_or_building = ?" ;
		PreparedStatement prepared = dbSession.prepare(query);
		
		
		if(vendor == "")
			vendor = null;
		if(osType =="")
			osType = null;
		if(sysType == "")
			sysType = null;
		if(assetType == "")
			assetType = null;
		if(assetVis =="")
			assetVis = null;
		
		
		BoundStatement bound = prepared.bind(vendor, ip, osType, sysType, assetType, assetVis, reportable, UUid, site, sub);
		
		dbSession.execute(bound);

	}
	
}
