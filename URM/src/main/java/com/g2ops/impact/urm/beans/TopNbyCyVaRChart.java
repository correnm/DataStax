package com.g2ops.impact.urm.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.g2ops.impact.urm.types.BusinessProcessTopByCyVar;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.FusionCharts;
import com.g2ops.impact.urm.utils.SessionUtils;

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
 * 12-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 14-Aug-2017		corren.mccoy		Change position of caption alignment
 */
@Named("topNbyCyVaRChart")
@RequestScoped
	public class TopNbyCyVaRChart {
	   
		private String data;
		private String chartData;
		private FusionCharts topNbyCyVaRChart;
		private DatabaseQueryService databaseQueryService;
		private Iterator<Row> iterator;

		public TopNbyCyVaRChart() {

			// get the user's session
			HttpSession userSession = SessionUtils.getSession();

			// get the Database Query Service instance from the user's session
			//databaseQueryService = (DatabaseQueryService)userSession.getAttribute("databaseQueryService");
			// get the Database Query Service object for this Org
			databaseQueryService = SessionUtils.getOrgDBQueryService();
			
			// get the currently active Site from the user's session
			String selectedSite = (String)userSession.getAttribute("currentSite");

			// do DB query
			ResultSet rs = databaseQueryService.runQuery("select business_process_name, cyber_value_at_risk from business_value_attribution where site_id = " + selectedSite);

			//iterate over the results. 
			iterator = rs.iterator();
			List<BusinessProcessTopByCyVar> businessProcessTopByCyVarArrayList = new ArrayList<BusinessProcessTopByCyVar>();
			BusinessProcessTopByCyVar businessProcessTopByCyVarInstance;
			while (iterator.hasNext()) {
				Row row = iterator.next();
				businessProcessTopByCyVarInstance = new BusinessProcessTopByCyVar(row.getString("business_process_name"), row.getDecimal("cyber_value_at_risk"));
				businessProcessTopByCyVarArrayList.add(businessProcessTopByCyVarInstance);
			}
			
			// create array for storing the business process names and cyber at risk values
			BusinessProcessTopByCyVar[] businessProcessTopByCyVarArray = (BusinessProcessTopByCyVar[]) businessProcessTopByCyVarArrayList.toArray(new BusinessProcessTopByCyVar[businessProcessTopByCyVarArrayList.size()]);
			
			// sort the Array by CyVar value descending
			Arrays.sort(businessProcessTopByCyVarArray);
			
			chartData = "{\"chart\": {";
			chartData = chartData.concat("\"theme\": \"g2ops\",");
			chartData = chartData.concat("\"caption\": \"Top Business Processes by CyVar\",");
			
			// 14-Aug-2017 corren.mccoy: changed alignment and orientation of caption to entire chart area
			chartData = chartData.concat("\"captionAlignment\": \"center\",");
			chartData = chartData.concat("\"alignCaptionWithCanvas\": \"0\",");
			// 14-Aug 2017
			
			chartData = chartData.concat("\"numberPrefix\": \"$\",");
			chartData = chartData.concat("\"paletteColors\": \"#595a5c\",");
			chartData = chartData.concat("\"showShadow\": \"0\",");
			chartData = chartData.concat("\"showAlternateVGridColor\": \"0\",");
			chartData = chartData.concat("\"numVDivLines\": \"0\",");
			chartData = chartData.concat("\"divLineIsDashed\": \"1\",");
			chartData = chartData.concat("\"divLineDashLen\": \"1\",");
			chartData = chartData.concat("\"divLineGapLen\": \"2\",");
			chartData = chartData.concat("\"usePlotGradientColor\": \"0\",");
			chartData = chartData.concat("\"showplotborder\": \"0\",");
			chartData = chartData.concat("\"showcanvasborder\": \"0\",");
			chartData = chartData.concat("\"chartLeftMargin\": \"25\",");
			chartData = chartData.concat("\"chartRightMargin\": \"25\",");
			chartData = chartData.concat("\"valuePadding\": \"10\",");
			chartData = chartData.concat("\"labeldisplay\": \"none\",");
			chartData = chartData.concat("\"showTickMarks\": \"0\",");
			chartData = chartData.concat("\"valueFontColor\": \"#ffffff\",");
			chartData = chartData.concat("\"placeValuesInside\": \"1\",");
			chartData = chartData.concat("\"rotateValues\": \"1\",");
			chartData = chartData.concat("\"showAxisLines\": \"0\",");
			chartData = chartData.concat("\"showAlternateHGridColor\": \"0\",");
			chartData = chartData.concat("},"); // end chart
			chartData = chartData.concat("\"data\": [");

			// loop through the array
			for(int i=0; i<businessProcessTopByCyVarArray.length; i++) {
			    //System.out.println(businessProcessTopByCyVarArray[i].getBusinessProcessName());
				// only display the top 8
				if (i < 8) {
					chartData = chartData.concat("{\"label\": \"" + businessProcessTopByCyVarArray[i].getBusinessProcessName() + "\", \"value\": \"" + businessProcessTopByCyVarArray[i].getCyVarValue().toString() + "\"},");
				}
			}			
			
			/*
			chartData = chartData.concat("{\"label\": \"Admissions\", \"value\": \"32305337\"},");
			chartData = chartData.concat("{\"label\": \"Emergency Room\", \"value\": \"27949576\"},");
			chartData = chartData.concat("{\"label\": \"Lab1\", \"value\": \"24957000\"},");
			chartData = chartData.concat("{\"label\": \"Finance\", \"value\": \"21402250\"},");
			chartData = chartData.concat("{\"label\": \"Accounts Payable\", \"value\": \"5542309\"},");
			chartData = chartData.concat("{\"label\": \"Obstetrics\", \"value\": \"801792\"},");
			chartData = chartData.concat("{\"label\": \"Normal Newborns\", \"value\": \"748156\"},");
			chartData = chartData.concat("{\"label\": \"Infectious Diseases\", \"value\": \"281880\"},");
			*/
			chartData = chartData.concat("]"); // end data
			chartData = chartData.concat("}");

			topNbyCyVaRChart = new FusionCharts(
                    "bar2d",// chartType
                    "topNbyCyVaRChartID",// chartId
                    "100%","100%",// chartWidth, chartHeight
                    "topNbyCyVaRChartContainer",// chartContainer
                    "json",// dataFormat
                    chartData
    		);
	    	data = topNbyCyVaRChart.render();
		}
		
	    public String getTopNbyCyVaRChart() {
	    	return data;
	    }

	}
