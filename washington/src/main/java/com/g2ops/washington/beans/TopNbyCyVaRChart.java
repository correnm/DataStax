package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
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
 * 12-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 
 */
	@ManagedBean
	public class TopNbyCyVaRChart {
	   
		private String data;
		private String chartData;
		private FusionCharts topNbyCyVaRChart;

		public TopNbyCyVaRChart() {

			chartData = "{\"chart\": {";
			chartData = chartData.concat("\"theme\": \"g2ops\",");
			chartData = chartData.concat("\"caption\": \"Top N by CyVaR\",");
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
			chartData = chartData.concat("{\"label\": \"Admissions\", \"value\": \"32305337\"},");
			chartData = chartData.concat("{\"label\": \"Emergency Room\", \"value\": \"27949576\"},");
			chartData = chartData.concat("{\"label\": \"Lab1\", \"value\": \"24957000\"},");
			chartData = chartData.concat("{\"label\": \"Finance\", \"value\": \"21402250\"},");
			chartData = chartData.concat("{\"label\": \"Accounts Payable\", \"value\": \"5542309\"},");
			chartData = chartData.concat("{\"label\": \"Obstetrics\", \"value\": \"801792\"},");
			chartData = chartData.concat("{\"label\": \"Normal Newborns\", \"value\": \"748156\"},");
			chartData = chartData.concat("{\"label\": \"Infectious Diseases\", \"value\": \"281880\"},");
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

