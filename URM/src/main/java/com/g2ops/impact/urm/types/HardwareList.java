package com.g2ops.impact.urm.types;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, April 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 17-April-2018	tammy.bogart		Created this Java class
 * 
 */

public class HardwareList {
	
	//declare variables to display hosts data
	
	private UUID hostSiteID, hostID; 
	private String hostSubnet, hostName, hostIP, hostAssetType, hostAssetVisibility, hostOS, hostVendor; 

	// constructor for variable initialization. 
	
	public HardwareList (UUID siteID, String subnet, UUID internalSystemID, String hostName, String ipAddress, String assetType, String assetVisibility, String OS, String Vendor) {

		this.hostSiteID = siteID;
		this.hostSubnet = subnet;
		this.hostID = internalSystemID;
		this.hostName = hostName;
		this.hostIP = ipAddress;
		this.hostAssetType = assetType;
		this.hostAssetVisibility = assetVisibility;
		this.hostOS = OS;
		this.hostVendor = Vendor;
	}

	///////////////getters/setters///////////////////////////////

	
	public UUID getHostSiteID() {
		return hostSiteID;
	}

	public void setHostSiteID(UUID hostSiteID) {
		this.hostSiteID = hostSiteID;
	}

	public UUID getHostID() {
		return hostID;
	}

	public void setHostID(UUID hostID) {
		this.hostID = hostID;
	}

	public String getHostSubnet() {
		return hostSubnet;
	}

	public void setHostSubnet(String hostSubnet) {
		this.hostSubnet = hostSubnet;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getHostAssetType() {
		return hostAssetType;
	}

	public void setHostAssetType(String hostAssetType) {
		this.hostAssetType = hostAssetType;
	}

	public String getHostAssetVisibility() {
		return hostAssetVisibility;
	}

	public void setHostAssetVisibility(String hostAssetVisibility) {
		this.hostAssetVisibility = hostAssetVisibility;
	}

	public String getHostOS() {
		return hostOS;
	}

	public void setHostOS(String hostOS) {
		this.hostOS = hostOS;
	}

	public String getHostVendor() {
		return hostVendor;
	}

	public void setHostVendor(String hostVendor) {
		this.hostVendor = hostVendor;
	}
}
