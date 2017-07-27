package com.g2ops.washington.beans;

/**
 * @author 		Sara Prokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * 
 * This class is a bean that is utilized by the business-attribution-edit.xhtml, which is used to edit the 
 * edit the Business Attribution Table.
 * 
 * Date				Author				Revision Description
 * 7-27-2017		Sara Prokop			Creator
 * 
 * 
 */

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.washington.utils.PasscodeEncryptionService;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@ViewScoped
public class BusinessAttributionEdit {
	
	private static final long serialVersionUID = 1L;

	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private String ip, osType, sysType, assetType, assetVis, busIntThreshold, businessCrit, infoClass ;
	private String siteORouName,ipSubOrBuilding;
	private UUID internalSysID;
	private Iterator<Row> iterator;
	private String colValue;
	private List<String> ddOptions = new ArrayList<String>();
	

	public BusinessAttributionEdit() {

		// execute sample query
		rs = dbSession.execute("select ip_address, os_general, system_type, asset_type, asset_visibility, business_interruption_threshold,"
				+ "business_criticality, information_classification_value, site_or_ou_name, ip_subnet_or_building, internal_system_id from hardware "
				+ "where site_or_ou_name = '96a3378e-2f67-4b9d-ab67-9ee9d533b88f' and ip_subnet_or_building = '10.106' and internal_system_id = 012878d1-eff0-49b7-9a43-95c1bae4a508");

		// get the result values
		row = rs.one();

		// set values to what was returned by the query
		ip = row.getString("ip_address");
		osType = row.getString("os_general");
		sysType = row.getString("system_type");
		assetType = row.getString("asset_type");
		assetVis = row.getString("asset_visibility");
		busIntThreshold = row.getString("business_interruption_threshold");
		businessCrit = row.getString("business_criticality");
		infoClass = row.getString("information_classification_value");
		siteORouName = row.getString("site_or_ou_name");
		ipSubOrBuilding = row.getString("ip_subnet_or_building");
		internalSysID = row.getUUID("internal_system_id");
		
	}

	public String getIp() {
		return ip;
	}

	public String getOsType() {
		return osType;
	}

	public String getSysType() {
		return sysType;
	}
	public String getAssetType() {
		return assetType;
	}

	public String getAssetVis() {
		return assetVis;
	}
	
	public String getBusIntThreshold() {
		return busIntThreshold;
	}
	
	public String getBusinessCrit() {
		return businessCrit;
	}

	public String getInfoClass() {
		return infoClass;
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
		//ddOptions.clear();
		populateDropDowns("'Asset Type'");
		return ddOptions;
	}
	
	public List<String> ddAssetVisibility(){
		ddOptions.clear();
		populateDropDowns("'Asset Visibility'");
		return ddOptions;
	}
	
	public List<String> ddBusIntThreshold(){
		ddOptions.clear();
		populateDropDowns("'Business Interruption Threshold'");
		return ddOptions;
	}
	
	public List<String> ddBusCrit(){
		ddOptions.clear();
		populateDropDowns("'Business Criticality'");
		return ddOptions;
	}
	
	public List<String> ddInfoClassValue(){
		ddOptions.clear();
		populateDropDowns("'Information Classification Value'");
		return ddOptions;
	}
	
	//populates the options for the dropdowns
	public void populateDropDowns(String col){
		// execute query to get drop down options for each column in the table
		rs = dbSession.execute("select business_value from business_practice where category = " + col);
		
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
		 row = iterator.next();
		 colValue = row.getString("business_value");
		 System.out.println("colValue: " + colValue);
		 ddOptions.add(colValue);
		}
	}

	public String editBusinessAttributionActionControllerMethod() {

		//will be used to edit contents of the hardware table in the database

		
		return "edit table";
		
	}
	
}
