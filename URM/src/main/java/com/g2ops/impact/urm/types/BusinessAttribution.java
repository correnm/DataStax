package com.g2ops.impact.urm.types;

/**
 * @author 		Sara Prokop, G2 Ops, Virginia Beach, VA
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
 * 
 */

public class BusinessAttribution {

	// declare the variables which coincide with fields in the business_practice table
	private String ip, osType, sysType, assetType, assetVis;
	
	
	// constructor for variable initialization. BigDecimal is the Java equivalent to the
	// decimal data type in Cassandra.
	public BusinessAttribution (String ip, 		String osType, 
			String sysType, 		String assetType, 
			String assetVis) {

		this.ip 							= ip;
		this.osType 						= osType;
		this.sysType 						= sysType;
		this.assetType						= assetType;
		this.assetVis 						= assetVis;
		//this.busIntThreshold 			  	= busIntThreshold;

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
	
	//public String getBusIntThreshold() {
		//return busIntThreshold;
	//}
	
	//public String getBusinessCrit() {
		//return businessCrit;
	//}

	//public String getInfoClass() {
		//return infoClass;
	//}

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

	//public void setBusIntThreshold(String busIntThreshold) {
		//this.busIntThreshold = busIntThreshold;
	//}

	//public void setBusinessCrit(String businessCrit) {
		//this.businessCrit = businessCrit;
	//}
	
	//public void setInfoClass(String infoClass) {
		//this.infoClass = infoClass;
	//}

}
