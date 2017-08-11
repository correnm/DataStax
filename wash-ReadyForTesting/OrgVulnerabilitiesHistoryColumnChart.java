package com.g2ops.washington.beans;

import javax.faces.bean.*;
import com.g2ops.washington.utils.FusionCharts;
/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 10-Aug-2017		corren.mccoy		Changed visual to a stacked column with a break down by severity
 * 11-Aug-2017		corren.mccoy		The height of the associated container where this chart displays
 * 										can impact whether x-axis labels are displayed or not.
 */
@ManagedBean
public class OrgVulnerabilitiesHistoryColumnChart {

	private String data;
	private String chartData;
	private FusionCharts orgVulnerabilitiesHistoryColumnChart;

    public OrgVulnerabilitiesHistoryColumnChart() {

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"Overall Vulnerabilities by Severity\",");
		chartData = chartData.concat("\"subCaption\": \"Two-Year Historical View\",");
		chartData = chartData.concat("\"xAxisName\": \"\",");
		chartData = chartData.concat("\"yAxisName\": \"\",");
		chartData = chartData.concat("\"lineThickness\": \"2\",");
		chartData = chartData.concat("\"showCanvasBorder\": \"0\",");
		chartData = chartData.concat("\"showplotborder\": \"0\",");
		chartData = chartData.concat("\"usePlotGradientColor\": \"0\",");
		// 11-aug-2017 corren.mccoy: make sure the container on the dashboard is sufficient height.
		chartData = chartData.concat("\"showLabels\": \"1\",");
		chartData = chartData.concat("\"showSum\": \"0\",");
		chartData = chartData.concat("\"valueFontColor\": \"#bbbdbf\"");
		chartData = chartData.concat("},");
		// add the categories which will render as the bars
		chartData = chartData.concat("\"categories\": [");
			// start: initial category tag
			chartData = chartData.concat("{"); 
			// list the individual entries
			chartData = chartData.concat("\"category\": [");
				chartData = chartData.concat("{\"label\": \"8/15\"},");
				chartData = chartData.concat("{\"label\": \"9/15\"},");				
				chartData = chartData.concat("{\"label\": \"10/15\"},");				
				chartData = chartData.concat("{\"label\": \"11/15\"},");
				chartData = chartData.concat("{\"label\": \"12/15\"},");
				chartData = chartData.concat("{\"label\": \"1/16\"},");				
				chartData = chartData.concat("{\"label\": \"2/16\"},");
				chartData = chartData.concat("{\"label\": \"3/16\"},");
				chartData = chartData.concat("{\"label\": \"4/16\"},");
				chartData = chartData.concat("{\"label\": \"5/16\"},");
				chartData = chartData.concat("{\"label\": \"6/16\"},");
				chartData = chartData.concat("{\"label\": \"7/16\"},");
				chartData = chartData.concat("{\"label\": \"8/16\"},");
				chartData = chartData.concat("{\"label\": \"9/16\"},");
				chartData = chartData.concat("{\"label\": \"10/16\"},");
				chartData = chartData.concat("{\"label\": \"11/16\"},");
				chartData = chartData.concat("{\"label\": \"12/16\"},");
				chartData = chartData.concat("{\"label\": \"1/17\"},");
				chartData = chartData.concat("{\"label\": \"2/17\"},");
				chartData = chartData.concat("{\"label\": \"3/17\"},");
				chartData = chartData.concat("{\"label\": \"4/17\"},");
				chartData = chartData.concat("{\"label\": \"5/17\"},");
				chartData = chartData.concat("{\"label\": \"6/17\"},");
				chartData = chartData.concat("{\"label\": \"7/17\"}");		
			chartData = chartData.concat("]"); 
			// end: initial category tag
			chartData = chartData.concat("}"); 			
		// end of categories	
		chartData = chartData.concat("],"); 
		// add the dataset based on the defined categories
		chartData = chartData.concat("\"dataset\": [");
		
		// begin series
		chartData = chartData.concat("{"); 	
				chartData = chartData.concat("\"seriesname\": \"Critical\",");
				chartData = chartData.concat("\"color\": \"#df382c\",");
				chartData = chartData.concat("\"showValues\": \"0\",");
				chartData = chartData.concat("\"data\":"); 	
				chartData = chartData.concat("["); 
				chartData = chartData.concat("{\"value\": \"29\"},");
				chartData = chartData.concat("{\"value\": \"28\"},");	
				chartData = chartData.concat("{\"value\": \"29\"},");	
				chartData = chartData.concat("{\"value\": \"37\"},");
				chartData = chartData.concat("{\"value\": \"36\"},");
				chartData = chartData.concat("{\"value\": \"33\"},");
				chartData = chartData.concat("{\"value\": \"28\"},");	
				chartData = chartData.concat("{\"value\": \"39\"},");
				chartData = chartData.concat("{\"value\": \"48\"},");	
				chartData = chartData.concat("{\"value\": \"49\"},");
				chartData = chartData.concat("{\"value\": \"37\"},");	
				chartData = chartData.concat("{\"value\": \"39\"},");
				chartData = chartData.concat("{\"value\": \"27\"},");	
				chartData = chartData.concat("{\"value\": \"29\"},");
				chartData = chartData.concat("{\"value\": \"41\"},");	
				chartData = chartData.concat("{\"value\": \"37\"},");
				chartData = chartData.concat("{\"value\": \"34\"},");	
				chartData = chartData.concat("{\"value\": \"29\"},");
				chartData = chartData.concat("{\"value\": \"28\"},");	
				chartData = chartData.concat("{\"value\": \"51\"},");
				chartData = chartData.concat("{\"value\": \"48\"},");	
				chartData = chartData.concat("{\"value\": \"19\"},");
				chartData = chartData.concat("{\"value\": \"14\"},");	
				chartData = chartData.concat("{\"value\": \"50\"}");
				chartData = chartData.concat("]"); 
		chartData = chartData.concat("},"); 	
		// end series
		
		// begin series
		chartData = chartData.concat("{"); 	
				chartData = chartData.concat("\"seriesname\": \"High\",");
				chartData = chartData.concat("\"color\": \"#e95420\",");
				chartData = chartData.concat("\"showValues\": \"0\",");
				chartData = chartData.concat("\"data\":"); 	
				chartData = chartData.concat("[");
				chartData = chartData.concat("{\"value\": \"267\"},");
				chartData = chartData.concat("{\"value\": \"241\"},");	
				chartData = chartData.concat("{\"value\": \"309\"},");	
				chartData = chartData.concat("{\"value\": \"415\"},");
				chartData = chartData.concat("{\"value\": \"203\"},");
				chartData = chartData.concat("{\"value\": \"261\"},");
				chartData = chartData.concat("{\"value\": \"241\"},");	
				chartData = chartData.concat("{\"value\": \"306\"},");
				chartData = chartData.concat("{\"value\": \"241\"},");	
				chartData = chartData.concat("{\"value\": \"267\"},");
				chartData = chartData.concat("{\"value\": \"241\"},");	
				chartData = chartData.concat("{\"value\": \"247\"},");
				chartData = chartData.concat("{\"value\": \"241\"},");	
				chartData = chartData.concat("{\"value\": \"287\"},");
				chartData = chartData.concat("{\"value\": \"211\"},");	
				chartData = chartData.concat("{\"value\": \"207\"},");
				chartData = chartData.concat("{\"value\": \"241\"},");	
				chartData = chartData.concat("{\"value\": \"167\"},");
				chartData = chartData.concat("{\"value\": \"141\"},");	
				chartData = chartData.concat("{\"value\": \"296\"},");
				chartData = chartData.concat("{\"value\": \"209\"},");	
				chartData = chartData.concat("{\"value\": \"184\"},");
				chartData = chartData.concat("{\"value\": \"179\"},");	
				chartData = chartData.concat("{\"value\": \"147\"}");
				chartData = chartData.concat("]"); 
		chartData = chartData.concat("},"); 	
		// end series	
		
		// begin series
		chartData = chartData.concat("{"); 	
				chartData = chartData.concat("\"seriesname\": \"Medium\",");
				chartData = chartData.concat("\"color\": \"#efb73e\",");
				chartData = chartData.concat("\"showValues\": \"0\",");
				chartData = chartData.concat("\"data\":"); 	
				chartData = chartData.concat("["); 
				chartData = chartData.concat("{\"value\": \"99\"},");
				chartData = chartData.concat("{\"value\": \"88\"},");	
				chartData = chartData.concat("{\"value\": \"74\"},");	
				chartData = chartData.concat("{\"value\": \"69\"},");
				chartData = chartData.concat("{\"value\": \"105\"},");
				chartData = chartData.concat("{\"value\": \"93\"},");
				chartData = chartData.concat("{\"value\": \"86\"},");	
				chartData = chartData.concat("{\"value\": \"74\"},");	
				chartData = chartData.concat("{\"value\": \"169\"},");
				chartData = chartData.concat("{\"value\": \"105\"},");
				chartData = chartData.concat("{\"value\": \"99\"},");
				chartData = chartData.concat("{\"value\": \"78\"},");	
				chartData = chartData.concat("{\"value\": \"94\"},");	
				chartData = chartData.concat("{\"value\": \"69\"},");
				chartData = chartData.concat("{\"value\": \"105\"},");
				chartData = chartData.concat("{\"value\": \"59\"},");
				chartData = chartData.concat("{\"value\": \"98\"},");	
				chartData = chartData.concat("{\"value\": \"79\"},");	
				chartData = chartData.concat("{\"value\": \"69\"},");
				chartData = chartData.concat("{\"value\": \"105\"},");
				chartData = chartData.concat("{\"value\": \"99\"},");
				chartData = chartData.concat("{\"value\": \"69\"},");
				chartData = chartData.concat("{\"value\": \"106\"},");
				chartData = chartData.concat("{\"value\": \"224\"}");
				chartData = chartData.concat("]"); 
		chartData = chartData.concat("},"); 	
		// end series
		
		// begin series
			chartData = chartData.concat("{"); 	
				chartData = chartData.concat("\"seriesname\": \"Low\",");
				chartData = chartData.concat("\"color\": \"#38b44a\",");
				chartData = chartData.concat("\"showValues\": \"0\",");
				chartData = chartData.concat("\"data\":"); 	
				chartData = chartData.concat("["); 
				chartData = chartData.concat("{\"value\": \"245\"},");
				chartData = chartData.concat("{\"value\": \"122\"},");	
				chartData = chartData.concat("{\"value\": \"165\"},");	
				chartData = chartData.concat("{\"value\": \"149\"},");
				chartData = chartData.concat("{\"value\": \"179\"},");
				chartData = chartData.concat("{\"value\": \"125\"},");
				chartData = chartData.concat("{\"value\": \"132\"},");	
				chartData = chartData.concat("{\"value\": \"155\"},");	
				chartData = chartData.concat("{\"value\": \"149\"},");
				chartData = chartData.concat("{\"value\": \"179\"},");
				chartData = chartData.concat("{\"value\": \"145\"},");
				chartData = chartData.concat("{\"value\": \"122\"},");	
				chartData = chartData.concat("{\"value\": \"165\"},");	
				chartData = chartData.concat("{\"value\": \"149\"},");
				chartData = chartData.concat("{\"value\": \"143\"},");
				chartData = chartData.concat("{\"value\": \"100\"},");
				chartData = chartData.concat("{\"value\": \"122\"},");	
				chartData = chartData.concat("{\"value\": \"165\"},");	
				chartData = chartData.concat("{\"value\": \"259\"},");
				chartData = chartData.concat("{\"value\": \"133\"},");
				chartData = chartData.concat("{\"value\": \"175\"},");
				chartData = chartData.concat("{\"value\": \"102\"},");	
				chartData = chartData.concat("{\"value\": \"129\"},");
				chartData = chartData.concat("{\"value\": \"155\"}");
				chartData = chartData.concat("]"); 
				chartData = chartData.concat("}"); 	
			// end series

			//end of data set
		chartData = chartData.concat("]"); 
		// end of chart
		chartData = chartData.concat("}");
		// debug: to validate JSON format
		System.out.println(chartData);	

		orgVulnerabilitiesHistoryColumnChart = new FusionCharts(
                "stackedcolumn2d",								// chartType
                "orgVulnerabilitiesHistoryColumnChartID",		// chartId
                "100%","100%",									// chartWidth, chartHeight
                "orgVulnerabilitiesHistoryColumnChartContainer",// chartContainer
                "json",											// dataFormat
                chartData
		);
    	data = orgVulnerabilitiesHistoryColumnChart.render();
	}
	
    public String getOrgVulnerabilitiesHistoryColumnChart() {
    	return data;
    }

}
