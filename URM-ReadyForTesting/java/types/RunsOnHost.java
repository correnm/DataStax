package com.g2ops.impact.urm.types;

/**
 * @author Tammy Bogart

 * @date 03.30.2018
 * 
 * @purpose This is a utility class stores the runs_on_hosts column in the business_value_attribution table that is a User-Defined type
 * 
 * 
 * Modification History
 * Date 				Author				Description
 *
 */

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.mapping.annotations.Field;

import java.util.UUID;


@UDT(name = "hardware_relationships", keyspace="")

public class RunsOnHost {

	@Field(name = "site_id")
	private UUID siteID;
	
	@Field(name="ip_subnet_or_building")
	private String ipSubnet;
	
	@Field(name="internal_system_id")
	private UUID internalSystemID;

		
	//>>>>>>>>>>>>>> getters/setters <<<<<<<<<<<<<<<<
	
	public UUID getSiteID() {
		return siteID;
	}

	public void setSiteID(UUID siteID) {
		this.siteID = siteID;
	}

	public String getIpSubnet() {
		return ipSubnet;
	}

	public void setIpSubnet(String ipSubnet) {
		this.ipSubnet = ipSubnet;
	}

	public UUID getInternalSystemID() {
		return internalSystemID;
	}

	public void setInternalSystemID(UUID internalSystemID) {
		this.internalSystemID = internalSystemID;
	}	
}