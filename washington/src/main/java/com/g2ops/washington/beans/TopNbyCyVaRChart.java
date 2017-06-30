package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

	@ManagedBean
	public class TopNbyCyVaRChart {
	   
		private String data;
		private String chartData;
		private FusionCharts topNbyCyVaRChart;

		public TopNbyCyVaRChart() {

			chartData = "{\"chart\": {";
			chartData = chartData.concat("\"theme\": \"g2ops\",");
			chartData = chartData.concat("\"caption\": \"Top 10 by CyVaR\",");
			chartData = chartData.concat("\"numberPrefix\": \"$\",");
			chartData = chartData.concat("\"paletteColors\": \"#666666\",");
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
			chartData = chartData.concat("{\"label\": \"FIN\", \"value\": \"32500000\"},");
			chartData = chartData.concat("{\"label\": \"Payroll\", \"value\": \"25000000\"},");
			chartData = chartData.concat("{\"label\": \"AP2\", \"value\": \"22500000\"},");
			chartData = chartData.concat("{\"label\": \"EMR\", \"value\": \"22000000\"},");
			chartData = chartData.concat("{\"label\": \"AP\", \"value\": \"19850000\"},");
			chartData = chartData.concat("{\"label\": \"LAB1\", \"value\": \"19800000\"},");
			chartData = chartData.concat("{\"label\": \"LAB2\", \"value\": \"17000000\"},");
			chartData = chartData.concat("{\"label\": \"CMS\", \"value\": \"12000000\"},");
			chartData = chartData.concat("{\"label\": \"HR\", \"value\": \"8000000\"},");
			chartData = chartData.concat("{\"label\": \"AR\", \"value\": \"5000000\"},");
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

