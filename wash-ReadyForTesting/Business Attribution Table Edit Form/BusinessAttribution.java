package com.g2ops.washington.types;

import java.util.UUID;

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
 * 12-July-2017		sara.prokop		Created this Java class
 * 11-Sept-2017		sara.prokop		took out business interruption threshold, business criticality, and classification
 * 12-Sept-2017		sara.prokop		added keys into attributes
 * 14-Sept-2017 	sara.prokop		added reportable_flag and vendor 
 */

public class BusinessAttribution {
	

	// declare the variables which coincide with fields in the business_practice table
	private String ip, osType, sysType, assetType, assetVis, siteOrOuName, ipSubOrBuilding, vendor;
	boolean reportable;
	private UUID internalSysId;
	
	public BusinessAttribution(){
		
	}
	
	// constructor for variable initialization. BigDecimal is the Java equivalent to the
	// decimal data type in Cassandra.
	public BusinessAttribution (String vendor, String ip, 		String osType, 
			String sysType, String assetType,
			String assetVis, boolean reportable, UUID internalSysId, String siteOrOuName, String ipSubOrBuilding) {

		this.vendor 						= vendor; 
		this.ip 							= ip;
		this.osType 						= osType;
		this.sysType 						= sysType;
		this.assetType						= assetType;
		this.assetVis 						= assetVis;
		this.reportable						= reportable;
		this.internalSysId					= internalSysId;
		this.siteOrOuName					= siteOrOuName;
		this.ipSubOrBuilding 				= ipSubOrBuilding;

	}

	// Setters and getters for each of the class variables that will referenced in xhtml
	public String getVendor(){
		return vendor;
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
//	public String getReportable(){
//		return reportable;
//	}
	
	public void setVendor(String vendor){
		this.vendor = vendor;
	}
	
	public void setIp(String ip) {
		this.ip= ip;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public void setAssetVis(String assetVis) {
		this.assetVis = assetVis;
	}
//	public void setReportable(String flag){
//		this.reportable = flag;
//	}
	
	/**keys **/
	public UUID getInternalSysId(){
		return internalSysId;
	}
	public String getSiteOrOuName(){
		return siteOrOuName;
	}
	public String getIpSubOrBuilding(){
		return ipSubOrBuilding;
	}
	public void setInternalSysId(UUID id){
		this.internalSysId = id;
	}
	public void setSiteOrOuName(String site){
		this.siteOrOuName = site;
	}
	public void setIpSubOrBuilding(String sub){
		this.ipSubOrBuilding = sub;
	}

}
