package com.g2ops.washington.beans;

import java.util.Iterator;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.washington.utils.DatabaseQueryService;
import com.g2ops.washington.utils.FusionCharts;
import com.g2ops.washington.utils.SessionUtils;

/**
 * @author 		Dang Le, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 12-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 14-Aug-2017		corren.mccoy		Removed sublabel on treemap
 */
@ManagedBean
public class BusinessProcessTreeMap {

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs;
	private Iterator<Row> iterator;

	private String selectedSite, ip_address, node_impact_value, vulnerability_count;
	private Integer vulnerability_count_sum = 0;

	private String data;
	private String chartData;
	private String nodeData = "";
	private FusionCharts businessProcessTreeMap;

	public BusinessProcessTreeMap() {

		HttpSession userSession = SessionUtils.getSession();
		selectedSite = (String)userSession.getAttribute("currentSite");

		queryHardwareNodes();
		
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();
			ip_address = row.getString("ip_address");
			node_impact_value = Float.toString(row.getFloat("node_impact_value"));
			vulnerability_count = Integer.toString(row.getInt("vulnerability_count"));
			vulnerability_count_sum += row.getInt("vulnerability_count");
			nodeData = nodeData.concat("{\"label\": \"" + ip_address + "\", \"value\": \"" + vulnerability_count + "\", \"svalue\": \"" + node_impact_value + "\"},");
		}
			
		chartData = "{";

			chartData = chartData.concat("\"chart\": {");

				chartData = chartData.concat("\"theme\": \"g2ops\",");
				chartData = chartData.concat("\"animation\": \"0\",");
				chartData = chartData.concat("\"hideTitle\": \"1\",");
				chartData = chartData.concat("\"horizontalPadding\": \"0\",");
				chartData = chartData.concat("\"verticalPadding\": \"0\",");

				chartData = chartData.concat("\"algorithm\": \"squarified\",");
				chartData = chartData.concat("\"caption\": \"Organizational Risk View\",");

				chartData = chartData.concat("\"plotToolText\": \"<div><b>$label</b><br/> <b>Vulnerabilities: </b>$value<br/><b>Severity: </b>$svalue</div>\",");
				chartData = chartData.concat("\"plotborderthickness\": \".5\",");
				chartData = chartData.concat("\"plotbordercolor\": \"595a5c\",");

				chartData = chartData.concat("\"labelGlow\": \"0\",");
				chartData = chartData.concat("\"labelFontColor\": \"ffffff\",");
				chartData = chartData.concat("\"labelFontSize\": \"8\",");
				chartData = chartData.concat("\"labelFontBold\": \"1\",");

				chartData = chartData.concat("\"showLegend\": \"1\",");
				chartData = chartData.concat("\"legendpadding\": \"0\",");
				chartData = chartData.concat("\"legendItemFontSize\": \"10\",");
				chartData = chartData.concat("\"legendItemFontBold\": \"1\",");
				chartData = chartData.concat("\"legenditemfontcolor\": \"595a5c\",");
				chartData = chartData.concat("\"legendScaleLineThickness\": \"0\",");
				chartData = chartData.concat("\"legendCaptionFontSize\": \"10\",");
				chartData = chartData.concat("\"legendaxisbordercolor\": \"bfbfbf\",");
				// chartData = chartData.concat("\"legendCaption\": \"Growth in sales - Compared to previous year\"");

			chartData = chartData.concat("},");  // chart

			chartData = chartData.concat("\"data\": [{"); // data
				chartData = chartData.concat("\"label\": \"\", \"fillcolor\": \"595a5c\", \"value\": \"" + Integer.toString(vulnerability_count_sum) + "\", ");
				chartData = chartData.concat("\"data\": ["); // subnets
				chartData = chartData.concat(nodeData); // node data

				/*	
				chartData = chartData.concat("{\"label\": \"10.106.253.80\", \"value\": \"13\", \"svalue\":  	\"9.59\"},");
				chartData = chartData.concat("{\"label\": \"10.106.75.198\", \"value\": \"4\",  \"svalue\":  	\"5.26\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.242\", \"value\": \"1\",  \"svalue\":  	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.60.131\", \"value\": \"38\",  \"svalue\": 	\"7.42\"},");
				chartData = chartData.concat("{\"label\": \"10.106.74.135\", \"value\": \"1\",  \"svalue\":  	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.248.101\", \"value\": \"15\",  \"svalue\": 	\"6.63\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.252\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.244\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.56.102\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.75.221\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.248.100\", \"value\": \"26\",  \"svalue\": 	\"6.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.248.108\", \"value\": \"13\",  \"svalue\": 	\"6.42\"},");
				chartData = chartData.concat("{\"label\": \"10.106.23.41\", \"value\": \"43\",  \"svalue\": 	\"7.59\"},");
				chartData = chartData.concat("{\"label\": \"10.106.57.225\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.74.12\", \"value\": \"40\",  \"svalue\": 	\"7.5\"},");
				chartData = chartData.concat("{\"label\": \"10.106.23.39\", \"value\": \"3\",  \"svalue\": 		\"6.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.57.230\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.60.87\", \"value\": \"38\",  \"svalue\": 	\"7.42\"},");
				chartData = chartData.concat("{\"label\": \"10.106.23.34\", \"value\": \"1\",  \"svalue\": 		\"6.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.62.244\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.55.234\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.246.76\", \"value\": \"3\",  \"svalue\": 	\"4.05\"},");
				chartData = chartData.concat("{\"label\": \"10.106.62.247\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.248\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.57.217\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.245.17\", \"value\": \"14\",  \"svalue\": 	\"6.44\"},");
				chartData = chartData.concat("{\"label\": \"10.106.57.239\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.246.86\", \"value\": \"1\",  \"svalue\": 	\"9.03\"},");
				chartData = chartData.concat("{\"label\": \"10.106.57.218\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.248.127\", \"value\": \"14\",  \"svalue\": 	\"6.63\"},");
				chartData = chartData.concat("{\"label\": \"10.106.246.78\", \"value\": \"3\",  \"svalue\": 	\"4.05\"},");
				chartData = chartData.concat("{\"label\": \"10.106.75.242\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.74.29\", \"value\": \"1\",  \"svalue\": 		\"7.98\"},");
				chartData = chartData.concat("{\"label\": \"10.106.23.35\", \"value\": \"1\",  \"svalue\": 		\"6.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.245\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");				
				chartData = chartData.concat("{\"label\": \"10.106.61.251\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.74.10\", \"value\": \"7\",  \"svalue\": 		\"6.78\"},");
				chartData = chartData.concat("{\"label\": \"10.106.75.250\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.246.132\", \"value\": \"4\",  \"svalue\": 	\"5.48\"},");
				chartData = chartData.concat("{\"label\": \"10.106.74.52\", \"value\": \"8\",  \"svalue\": 		\"6.42\"},");				
				chartData = chartData.concat("{\"label\": \"10.106.253.68\", \"value\": \"14\",  \"svalue\": 	\"6.59\"},");
				chartData = chartData.concat("{\"label\": \"10.106.70.199\", \"value\": \"24\",  \"svalue\":	\"6.84\"},");
				chartData = chartData.concat("{\"label\": \"10.106.252.220\", \"value\": \"11\",  \"svalue\": 	\"7.07\"},");
				chartData = chartData.concat("{\"label\": \"10.106.245.12\", \"value\": \"4\",  \"svalue\": 	\"6.44\"},");
				chartData = chartData.concat("{\"label\": \"10.106.55.238\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.221\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.60.88\", \"value\": \"38\",  \"svalue\": 	\"6.94\"},");
				chartData = chartData.concat("{\"label\": \"10.106.61.239\", \"value\": \"1\",  \"svalue\": 	\"7.96\"},");
				chartData = chartData.concat("{\"label\": \"10.106.246.161\", \"value\": \"2\",  \"svalue\": 	\"6.44\"},");
				chartData = chartData.concat("{\"label\": \"10.106.246.163\", \"value\": \"3\",  \"svalue\": 	\"4.05\"},");
				chartData = chartData.concat("{\"label\": \"10.106.248.132\", \"value\": \"15\",  \"svalue\": 	\"8.02\"},");
				chartData = chartData.concat("{\"label\": \"10.106.60.90\", \"value\": \"39\",  \"svalue\": 	\"6.94\"},");
				*/
				
				/*		
					chartData = chartData.concat("{\"label\": \"10.10.1.*\", \"value\": \"75\", \"svalue\": \"22\"},");
					chartData = chartData.concat("{\"label\": \"10.10.2.*\", \"value\": \"275\", \"svalue\": \"62\"},");
					chartData = chartData.concat("{\"label\": \"10.10.3.*\", \"value\": \"60\", \"svalue\": \"33\"},");
					chartData = chartData.concat("{\"label\": \"10.10.4.*\", \"value\": \"40\", \"svalue\": \"26\"},");
					chartData = chartData.concat("{\"label\": \"10.10.5.*\", \"value\": \"100\", \"svalue\": \"20\"},");
					chartData = chartData.concat("{\"label\": \"10.10.6.*\", \"value\": \"180\", \"svalue\": \"95\"},");
					chartData = chartData.concat("{\"label\": \"10.10.7.*\", \"value\": \"120\", \"svalue\": \"80\"},");
					chartData = chartData.concat("{\"label\": \"10.10.8.*\", \"value\": \"50\", \"svalue\": \"16\"},");
					chartData = chartData.concat("{\"label\": \"10.10.9.*\", \"value\": \"70\", \"svalue\": \"44\"},");
					chartData = chartData.concat("{\"label\": \"10.10.10.*\", \"value\": \"30\", \"svalue\": \"8\"}");
					*/
				chartData = chartData.concat("]"); // subnets
			chartData = chartData.concat("}],"); // data

			chartData = chartData.concat("\"colorrange\": {"); // colorrange
				chartData = chartData.concat("\"mapbypercent\": \"0\",");
				chartData = chartData.concat("\"gradient\": \"1\",");
				//chartData = chartData.concat("\"minvalue\": \"0\",");
				//chartData = chartData.concat("\"code\": \"2b8118\",");
				//chartData = chartData.concat("\"startlabel\": \"Low\",");
				//chartData = chartData.concat("\"endlabel\": \"Critical\",");

				// attributes of the color object are used to configure individual numeric ranges
				chartData = chartData.concat("\"color\": ["); // color
					chartData = chartData.concat("{");
					chartData = chartData.concat("\"code\": \"2b8118\","); // green		
					chartData = chartData.concat("\"minvalue\": \"0\","); 				
					chartData = chartData.concat("\"maxvalue\": \"3.99\","); 
					chartData = chartData.concat("\"label\": \"Low\"");
					chartData = chartData.concat("}");
					chartData = chartData.concat(","); // separate the colors
					
					chartData = chartData.concat("{");
					chartData = chartData.concat("\"code\": \"F4FA58\","); // yellow
					chartData = chartData.concat("\"minvalue\": \"4.00\","); 				
					chartData = chartData.concat("\"maxvalue\": \"6.99\","); 
					chartData = chartData.concat("\"label\": \"Medium\"");
					chartData = chartData.concat("}");	
					chartData = chartData.concat(","); // separate the colors
					
					chartData = chartData.concat("{");
					chartData = chartData.concat("\"code\": \"FE9A2E\","); // orange
					chartData = chartData.concat("\"minvalue\": \"7.00\","); 				
					chartData = chartData.concat("\"maxvalue\": \"8.99\","); 
					chartData = chartData.concat("\"label\": \"High\"");
					chartData = chartData.concat("}");	
					chartData = chartData.concat(","); // separate the colors
					
					chartData = chartData.concat("{");
					chartData = chartData.concat("\"code\": \"FF0404\","); // red
					chartData = chartData.concat("\"minvalue\": \"9.00\","); 				
					chartData = chartData.concat("\"maxvalue\": \"10\","); 
					chartData = chartData.concat("\"label\": \"Critical\"");
					chartData = chartData.concat("}");	
					chartData = chartData.concat("]"); // color				

					
				
				//original
/*				chartData = chartData.concat("\"color\": [{"); // color
					chartData = chartData.concat("\"code\": \"CC2A1F\","); //red
					chartData = chartData.concat("\"minvalue\": \"9\","); 						
					chartData = chartData.concat("\"maxvalue\": \"10\","); // severity is max NIV = 1 to 10
					chartData = chartData.concat("\"label\": \"Critical\"");
				chartData = chartData.concat("}]"); // color
*/				
			chartData = chartData.concat("}"); // colorrange

		chartData = chartData.concat("}");
		
		businessProcessTreeMap = new FusionCharts(
                "treemap",// chartType
                "businessProcessTreeMapID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "businessProcessTreeMapContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = businessProcessTreeMap.render();
	}
	
    public String getBusinessProcessTreeMap() {
    	return data;
    }

	private void queryHardwareNodes() {

		// get the user's session
		HttpSession userSession = SessionUtils.getSession();
		
		// get the Database Query Service instance from the user's session
		databaseQueryService = (DatabaseQueryService)userSession.getAttribute("databaseQueryService");
		
		// execute the query
		rs = databaseQueryService.runQuery("select ip_address, node_impact_value, vulnerability_count from hardware where site_or_ou_name = '" + selectedSite + "'");

	}

}
