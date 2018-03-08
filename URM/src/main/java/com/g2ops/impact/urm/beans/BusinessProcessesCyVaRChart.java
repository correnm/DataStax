package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, September 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 */
	
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.g2ops.impact.urm.types.BusinessProcessCyVar;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.FusionCharts;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("businessProcessesCyVaRChart")
@RequestScoped
public class BusinessProcessesCyVaRChart {

	@Inject private UserBean currentUser;
	
	private String data;
	private String chartData;
	private FusionCharts businessProcessesCyVaRChart;
	private Iterator<Row> iterator;
	private BigDecimal BPIV, tempCyVaR, tempAnnualRevenue, tempBPIV;
	private BigDecimal lowCutoffValue, mediumCutoffValue, highCutoffValue, nullAnnualRevenueValue, nullCyVaRValue, nullBPIVValue;
	private String columnColor;
	private DecimalFormat myFormatter = new DecimalFormat("###,###,###.##");

	public BusinessProcessesCyVaRChart() {

		lowCutoffValue = new BigDecimal("4.0");
		mediumCutoffValue = new BigDecimal("7.0");
		highCutoffValue = new BigDecimal("9.0");
		
		nullAnnualRevenueValue = new BigDecimal("0.0");
		nullCyVaRValue = new BigDecimal("0.0");
		nullBPIVValue = new BigDecimal("0.0");
		
	}

	@PostConstruct
	public void init() {

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// get the currently active Site from the user's session
		//String selectedSite = (String)userSession.getAttribute("currentSite");
		String selectedSite = currentUser.getSiteID();

		// do DB query
		ResultSet rs = databaseQueryService.runQuery("select business_process_name, annual_revenue, cyber_value_at_risk, business_process_impact_value from business_value_attribution where site_id = " + selectedSite);

		//iterate over the results. 
		iterator = rs.iterator();
		List<BusinessProcessCyVar> businessProcessCyVarArrayList = new ArrayList<BusinessProcessCyVar>();
		BusinessProcessCyVar businessProcessCyVarInstance;

		while (iterator.hasNext()) {

			Row row = iterator.next();

			if (row.isNull("annual_revenue")) {
				tempAnnualRevenue = nullAnnualRevenueValue;
			} else {
				tempAnnualRevenue = row.getDecimal("annual_revenue");
			}

			if (row.isNull("cyber_value_at_risk")) {
				tempCyVaR = nullCyVaRValue;
			} else {
				tempCyVaR = row.getDecimal("cyber_value_at_risk");
			}

			if (row.isNull("business_process_impact_value")) {
				tempBPIV = nullBPIVValue;
			} else {
				tempBPIV = row.getDecimal("business_process_impact_value");
			}

			businessProcessCyVarInstance = new BusinessProcessCyVar(row.getString("business_process_name"), tempAnnualRevenue, tempCyVaR, tempBPIV);
			businessProcessCyVarArrayList.add(businessProcessCyVarInstance);

		}
			
		// create array for storing the business process names and cyber at risk values
		BusinessProcessCyVar[] businessProcessCyVarArray = (BusinessProcessCyVar[]) businessProcessCyVarArrayList.toArray(new BusinessProcessCyVar[businessProcessCyVarArrayList.size()]);
			
		// sort the Array by CyVar value descending
		Arrays.sort(businessProcessCyVarArray);
			
		// construct the JSON object for creating the chart
		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"Business Processes\",");			
		chartData = chartData.concat("\"subCaption\": \"by CyVaR\",");
		chartData = chartData.concat("\"captionAlignment\": \"center\",");
		chartData = chartData.concat("\"alignCaptionWithCanvas\": \"0\",");
		chartData = chartData.concat("\"numberPrefix\": \"$\",");
		chartData = chartData.concat("\"paletteColors\": \"#28637e,#226d2d\",");
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
		chartData = chartData.concat("\"labeldisplay\": \"rotate\",");
		chartData = chartData.concat("\"slantLabels\": \"1\",");
		chartData = chartData.concat("\"showLegend\": \"0\",");
		chartData = chartData.concat("\"numVisiblePlot\": \"15\",");
		chartData = chartData.concat("\"showTickMarks\": \"0\",");
		chartData = chartData.concat("\"valueFontColor\": \"#ffffff\",");
		chartData = chartData.concat("\"placeValuesInside\": \"1\",");
		chartData = chartData.concat("\"rotateValues\": \"1\",");
		chartData = chartData.concat("\"showAxisLines\": \"0\",");
		chartData = chartData.concat("\"showAlternateHGridColor\": \"0\",");
		chartData = chartData.concat("\"scrollPadding\": \"10\",");
		chartData = chartData.concat("\"scrollHeight\": \"5\",");
		chartData = chartData.concat("\"flatscrollbars\": \"1\",");
		chartData = chartData.concat("\"scrollshowbuttons\": \"1\",");
		chartData = chartData.concat("\"toolTipBgColor\": \"#ffffff\",");
		chartData = chartData.concat("\"toolTipColor\": \"#000000\",");
		chartData = chartData.concat("\"clickURL\": \"Javascript:businessProcessesByCyVaRChartReset()\",");
		chartData = chartData.concat("},"); // end chart

		chartData = chartData.concat("\"categories\": [");
		chartData = chartData.concat("{");
		chartData = chartData.concat("\"category\": [");
			
		// loop through the array
		for(int i=0; i<businessProcessCyVarArray.length; i++) {
			chartData = chartData.concat("{\"label\": \"" + businessProcessCyVarArray[i].getBusinessProcessName() + "\"},");
		}			

		chartData = chartData.concat("]");
		chartData = chartData.concat("}");
		chartData = chartData.concat("],");

		chartData = chartData.concat("\"dataset\": [");
		chartData = chartData.concat("{");
		chartData = chartData.concat("\"seriesname\": \"CyVar Value\",");
		chartData = chartData.concat("\"data\": [");

		// loop through the array
		for(int i=0; i<businessProcessCyVarArray.length; i++) {
			BPIV = businessProcessCyVarArray[i].getBPIV();
			if (BPIV.compareTo(lowCutoffValue) == -1) {
				columnColor = "#38b44a";
			} else if (BPIV.compareTo(mediumCutoffValue) == -1) {
				columnColor = "#efb73e";
			} else if (BPIV.compareTo(highCutoffValue) == -1) {
				columnColor = "#e95420";
			} else {
				columnColor = "#df382c";
			}
			chartData = chartData.concat("{\"value\": \"" + businessProcessCyVarArray[i].getCyVarValue().toString() + "\", \"color\": \"" + columnColor + "\", \"toolText\": \"CyVaR: $" + myFormatter.format(businessProcessCyVarArray[i].getCyVarValue()) + "<br><br>Annual Revenue: $" + myFormatter.format(businessProcessCyVarArray[i].getAnnualRevenue()) + "\", \"link\": \"Javascript:businessProcessesByCyVaRChartClicked('" + businessProcessCyVarArray[i].getBusinessProcessName() + "')\"},");
		}			

		chartData = chartData.concat("]");
		chartData = chartData.concat("},");
		/*
		chartData = chartData.concat("{");
		chartData = chartData.concat("\"seriesname\": \"Annual Revenue\",");
		chartData = chartData.concat("\"renderAs\": \"line\",");
		chartData = chartData.concat("\"showValues\": \"0\",");
		chartData = chartData.concat("\"data\": [");
			
		// loop through the array
		for(int i=0; i<businessProcessCyVarArray.length; i++) {
		    //System.out.println(businessProcessCyVarArray[i].getBusinessProcessName());
			chartData = chartData.concat("{\"value\": \"" + businessProcessCyVarArray[i].getAnnualRevenue().toString() + "\", \"link\": \"Javascript:businessProcessesByCyVaRChartClicked('" + businessProcessCyVarArray[i].getBusinessProcessName() + "')\"},");
		}			

		chartData = chartData.concat("]");
		chartData = chartData.concat("}");
		*/
		chartData = chartData.concat("]");

		chartData = chartData.concat("}");

		businessProcessesCyVaRChart = new FusionCharts(
			"scrollColumn2d",// chartType
			"businessProcessesCyVaRChartID",// chartId
			"100%","100%",// chartWidth, chartHeight
			"businessProcessesCyVaRChartContainer",// chartContainer
			"json",// dataFormat
			chartData
		);
		data = businessProcessesCyVaRChart.render();
	}

	public String getBusinessProcessesCyVaRChart() {
		return data;
	}

}
