package com.g2ops.washington.types;

import java.util.UUID;

/**
 * @author 		 Sara Prokop G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * 
 * This class sets a type for populating the Business Value Attribution Table. 
 * It is initialized in the BusinessAttributionTable.java class 
 * 
 * Date				Author				Revision Description
 * 7-12-2017		Sara Prokop			Creator
 * 7-27-2017		Sara Prokop			Added the primary key fields
 * 
 */

public class BusinessAttribution {

	// declare the variables which coincide with fields in the business_practice table
	private String ip, osType, sysType, assetType, assetVis, busIntThreshold, businessCrit, infoClass ;
	private String siteORouName, build;// partition keys
	private UUID intSysID;//partition keys
	
	
	// constructor for variable initialization. 
	public BusinessAttribution (String ip, 		String osType, 
			String sysType, 		String assetType, 
			String assetVis, 	String busIntThreshold, 
			String businessCrit, String infoClass, String siteORouName, 
			String iPsubORbuilding, UUID intSysID) {

		this.ip 							= ip;
		this.osType 						= osType;
		this.sysType 						= sysType;
		this.assetType						= assetType;
		this.assetVis 						= assetVis;
		this.busIntThreshold 			  	= busIntThreshold;
		this.businessCrit 					= businessCrit;
		this.infoClass 						= infoClass;
		this.siteORouName					= siteORouName;
		this.build							= iPsubORbuilding;
		this.intSysID 						= intSysID;

	}

	// Setters and getters for each of the class variables that will referenced in xhtml
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

	public String getSiteORouName() {
		return siteORouName;
	}
	
	public String getIPsubORbuilding() {
		return build;
	}
	
	public UUID getIntSysID() {
		return intSysID;
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

	public void setBusIntThreshold(String busIntThreshold) {
		this.busIntThreshold = busIntThreshold;
	}

	public void setBusinessCrit(String businessCrit) {
		this.businessCrit = businessCrit;
	}
	
	public void setInfoClass(String infoClass) {
		this.infoClass = infoClass;
	}
	
	public void setSiteORouName(String siteORouName) {
		this.siteORouName = siteORouName;
	}
	
	public void setIPsubORbuilding(String iPsubORbuilding) {
		this.build = iPsubORbuilding;
	}
	
	public void setIntSysID(UUID intSysID) {
		this.intSysID = intSysID;
	}

}
