package com.g2ops.washington.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.g2ops.washington.utils.SessionUtils;


/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, September 2017
 * @exception
 * 
 * Known Bugs: (a list of bugs and other problems)
 *
 *
 * Revision History:
 * Date				Author				Revision Description
 * 
 * 
 * 
 */


@ManagedBean
@RequestScoped
public class TopologyNodeChartDB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private Iterator<Row> iterator;

	private UUID internalSystemID;
	private String currentSite, jsCode, jsCode2, ipAddress, destinationID;
	private Float nodeImpactValue;
	private Integer edgeCounter = 0;

	public TopologyNodeChartDB() throws NoSuchFieldException, SecurityException {

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();

		// get the Site from the user's session
		currentSite = (String)userSession.getAttribute("currentSite");

		// execute the query to get the nodes and edges
		rs = dbSession.execute("select internal_system_id, host_name, ip_address, node_impact_value, connected_elements from hardware where site_or_ou_name =  '" + currentSite + "'");

		// use an iterator to loop through the query results
		iterator = rs.iterator();
		
		jsCode2 = "";
		
		startTopologyNodeChartJS();
		
		while (iterator.hasNext()) {
			row = iterator.next();
			internalSystemID = row.getUUID("internal_system_id");
			ipAddress = row.getString("ip_address");
			nodeImpactValue = row.getFloat("node_impact_value");
			List<UDTValue> udtDataList = row.getList("connected_elements", UDTValue.class);
			//connectionsList = row.getList("connected_elements", TopologyConnectedNode.class);
	    	jsCode += "{ data: { id: '" + internalSystemID.toString() + "', ip: '" + ipAddress + "', niv: '" + nodeImpactValue + "' } },";
    	    //System.out.println("internalSystemID: " + internalSystemID);
	    	for(UDTValue connectedElement : udtDataList) {
	    		edgeCounter += 1;
	    	    //System.out.println("connectedElement: " + connectedElement);
	    	    destinationID = connectedElement.getString("destination_id");
	    	    //System.out.println("destinationID: " + destinationID);
	    	    jsCode2 += "{ data: { id: '" + edgeCounter + "', source: '" + internalSystemID.toString() + "', target: '" + destinationID + "' } },";
	    	}			
		}

		jsCode += jsCode2;
		
		endTopologyNodeChartJS();
		
	}
	
    public String getTopologyNodeChartJS() {
    	return jsCode;
    }

    private void startTopologyNodeChartJS() {

    	jsCode = "var cy = cytoscape({";
    	jsCode += "container: document.getElementById('topologyCoSeMapContainer'),";
    	jsCode += "elements: [";

    }

    private void endTopologyNodeChartJS() {
    	
    	jsCode += "],";
    	jsCode += "style: [";
    	jsCode += "{ selector: 'node', style: { 'background-color': '#595a5c', 'label': '' } },";
    	jsCode += "{ selector: '.critical', style: { 'background-color': '#df382c', } },";
    	jsCode += "{ selector: '.high', style: { 'background-color': '#e95420', } },";
    	jsCode += "{ selector: '.medium', style: { 'background-color': '#efb73e', } },";
    	jsCode += "{ selector: '.low', style: { 'background-color': '#38b44a', } },";
    	jsCode += "],";
    	jsCode += "layout: {";
    	jsCode += "name: 'cose',";
    	jsCode += "}";
    	jsCode += "});";
    	
    }

}
