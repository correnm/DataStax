package com.g2ops.impact.urm.types;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, March 2018
 * @see			<a href="URL#value">label</a>
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 30-March-2018	tammy.bogart		Created this Java class
 * 
 */

public class BusinessHosts {
	
	//declare variables to display hosts data
	
	private String hostSubnet, hostName, hostIP, hostAType, hostAVisibility, hostOS, hostVendor, siteID;
	private UUID hostSiteID, hostID, busProcID; 
	// constructor for variable initialization. 
	
	public BusinessHosts (UUID hostSiteID, String hostSubnet, UUID hostID, UUID busProcID, String hostName, String hostIP, String hostAType, String hostAVisibility, String hostOS, String hostVendor) {
		this.hostSiteID = hostSiteID;
		this.hostSubnet = hostSubnet;
		this.hostID = hostID;
		this.busProcID = busProcID;
		this.hostName = hostName;
		this.hostIP = hostIP;
		this.hostAType = hostAType;
		this.hostAVisibility = hostAVisibility;
		this.hostOS = hostOS;
		this.hostVendor = hostVendor;		
	}
	
/*	private void updateBVAtable() {
		//iterate through runs on hosts till find one to update
		//run hosts through hardware table to get details
		String queryHosts = "select runs_on_hosts from business_value_attribution where site_id="+ this.getSiteID() +" and business_process_id=" + this.getSelBusProcID();
		
		//re-build list of hosts 
		rs = databaseQueryService.runQuery(queryHosts);
		row = rs.one();	
		if (row.isNull(0)) {
			System.out.println("no data returned");
		} else {
			Set<RunsOnHost> setRunsOnHost = row.getSet("runs_on_hosts", RunsOnHost.class);

			Iterator<RunsOnHost> itROH = setRunsOnHost.iterator();
			RunsOnHost hostNode = new RunsOnHost();
			while(itROH.hasNext()) {
				hostNode = itROH.next();
				if (hostNode.getInternalSystemID().equals(hostID)) {
					//update values	
					hostNode.setIpSubnet(hostSubnet);
					hostNode.setSiteID(this.getHostSiteID());
				}
			}	//end while
			
			String qHosts = "UPDATE business_value_attribution "
					+	"set audit_upsert= {datechanged: toUnixTimestamp(now()), changedbyusername: '" + auditUpsert.getChangedbyusername() + "'}, " 
					+	"runs_on_hosts= ? "
					+	"where site_id=? and business_process_id=? ";
			
			PreparedStatement prepared = session.prepare(qHosts);
			BoundStatement bound = prepared.bind(setRunsOnHost, this.getSiteID(), this.getSelBusProcID());
			session.execute(bound);
		}	//end if		
	}	// end updateBVATable
*/
	
	///////////////getters/setters///////////////////////////////
	
	
	public String getHostName() {
		return hostName;
	}

	public UUID getHostSiteID() {
		return hostSiteID;
	}

	public void setHostSiteID(UUID hostSiteID) {
		this.hostSiteID = hostSiteID;
	}

	public String getHostSubnet() {
		return hostSubnet;
	}

	public void setHostSubnet(String hostSubnet) {
		this.hostSubnet = hostSubnet;
	}

	public UUID getHostID() {
		return hostID;
	}

	public void setHostID(UUID hostID) {
		this.hostID = hostID;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public UUID getBusProcID() {
		return busProcID;
	}

	public void setBusProcID(UUID busProcID) {
		this.busProcID = busProcID;
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

	public String getHostAType() {
		return hostAType;
	}

	public void setHostAType(String hostAType) {
		this.hostAType = hostAType;
	}

	public String getHostAVisibility() {
		return hostAVisibility;
	}

	public void setHostAVisibility(String hostAVisibility) {
		this.hostAVisibility = hostAVisibility;
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
