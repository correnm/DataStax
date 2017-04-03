package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

	@ManagedBean
	public class OrgRiskToleranceChart {
	   
		private String data;
		private String chartData;
		private FusionCharts orgRiskToleranceChart;

		public OrgRiskToleranceChart() {

			chartData = "{\"chart\": {";
			chartData = chartData.concat("\"theme\": \"g2ops\",");
			chartData = chartData.concat("\"caption\": \"Organization Overall Risk\",");
			chartData = chartData.concat("\"numberPrefix\": \"$\",");
			chartData = chartData.concat("\"paletteColors\": \"#666666,#2b8118\",");
			chartData = chartData.concat("\"showAlternateHGridColor\": \"0\",");
			chartData = chartData.concat("\"showAlternateVGridColor\": \"0\",");
			chartData = chartData.concat("\"showYAxisValue\": \"0\",");
			chartData = chartData.concat("\"numVDivLines\": \"5\",");
			chartData = chartData.concat("\"divLineIsDashed\": \"1\",");
			chartData = chartData.concat("\"divLineDashLen\": \"2\",");
			chartData = chartData.concat("\"divLineGapLen\": \"2\",");
			chartData = chartData.concat("\"usePlotGradientColor\": \"0\",");
			chartData = chartData.concat("\"showcanvasborder\": \"0\",");
			chartData = chartData.concat("\"chartLeftMargin\": \"25\",");
			chartData = chartData.concat("\"chartRightMargin\": \"25\",");
			chartData = chartData.concat("\"valuePadding\": \"10\",");
			chartData = chartData.concat("\"labeldisplay\": \"none\",");
			chartData = chartData.concat("\"valueFontColor\": \"#ffffff\",");
			chartData = chartData.concat("\"placeValuesInside\": \"1\",");
			chartData = chartData.concat("\"rotateValues\": \"1\",");
			chartData = chartData.concat("\"showXAxisLine\": \"0\",");
			chartData = chartData.concat("\"showYAxisLine\": \"0\",");
			chartData = chartData.concat("\"legendBgAlpha\": \"0\",");
			chartData = chartData.concat("\"legendBorderAlpha\": \"0\",");
			chartData = chartData.concat("\"legendShadow\": \"0\",");
			chartData = chartData.concat("\"legendItemFontSize\": \"12\",");
			chartData = chartData.concat("\"legendItemFontColor\": \"#666666\",");
			chartData = chartData.concat("},");
			chartData = chartData.concat("\"categories\": [{\"category\": [{\"label\": \"\"}]}],");
			chartData = chartData.concat("\"dataset\": [");
			chartData = chartData.concat("{\"seriesname\": \"Threshold\", \"data\": [{\"value\": \"1000000\"}]},");
			chartData = chartData.concat("{\"seriesname\": \"Actual\",\"data\": [{\"value\": \"650000\"}]}");
			chartData = chartData.concat("]}");

			orgRiskToleranceChart = new FusionCharts(
                    "msbar2d",// chartType
                    "orgRiskToleranceChartID",// chartId
                    "100%","100%",// chartWidth, chartHeight
                    "orgRiskToleranceChartContainer",// chartContainer
                    "json",// dataFormat
                    chartData
    		);
	    	data = orgRiskToleranceChart.render();
		}
		
	    public String getOrgRiskToleranceChart() {
	    	return data;
	    }

	}

