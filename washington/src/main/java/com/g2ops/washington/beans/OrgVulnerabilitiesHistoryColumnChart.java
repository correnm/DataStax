package com.g2ops.washington.beans;

import javax.faces.bean.*;
import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class OrgVulnerabilitiesHistoryColumnChart {

	private String data;
	private String chartData;
	private FusionCharts orgVulnerabilitiesHistoryColumnChart;

    public OrgVulnerabilitiesHistoryColumnChart() {

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"Overall Vulnerabilities\",");
		chartData = chartData.concat("\"xAxisName\": \"\",");
		chartData = chartData.concat("\"yAxisName\": \"\",");
		chartData = chartData.concat("\"lineThickness\": \"2\",");
		chartData = chartData.concat("\"showCanvasBorder\": \"0\",");
		chartData = chartData.concat("\"usePlotGradientColor\": \"0\",");
		chartData = chartData.concat("\"paletteColors\": \"#3e658e,#4b79aa,#638dbb\",");
		chartData = chartData.concat("\"placevaluesInside\": \"1\",");
		chartData = chartData.concat("\"valueFontColor\": \"#f7f7f7\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"7/15\",\"value\": \"5000\"},");
		chartData = chartData.concat("{\"label\": \"8/15\",\"value\": \"4500\"},");
		chartData = chartData.concat("{\"label\": \"9/15\",\"value\": \"6000\"},");
		chartData = chartData.concat("{\"label\": \"10/15\",\"value\": \"7500\"},");
		chartData = chartData.concat("{\"label\": \"11/15\",\"value\": \"6500\"},");
		chartData = chartData.concat("{\"label\": \"12/15\",\"value\": \"5500\"},");
		chartData = chartData.concat("{\"label\": \"1/16\",\"value\": \"5000\"},");
		chartData = chartData.concat("{\"label\": \"2/16\",\"value\": \"5500\"},");
		chartData = chartData.concat("{\"label\": \"3/16\",\"value\": \"4500\"},");
		chartData = chartData.concat("{\"label\": \"4/16\",\"value\": \"5000\"},");
		chartData = chartData.concat("{\"label\": \"5/16\",\"value\": \"4000\"},");
		chartData = chartData.concat("{\"label\": \"6/16\",\"value\": \"3500\"},");
		chartData = chartData.concat("{\"label\": \"7/16\",\"value\": \"3000\"},");
		chartData = chartData.concat("{\"label\": \"8/16\",\"value\": \"4500\"},");
		chartData = chartData.concat("{\"label\": \"9/16\",\"value\": \"5000\"},");
		chartData = chartData.concat("{\"label\": \"10/16\",\"value\": \"4000\"},");
		chartData = chartData.concat("{\"label\": \"11/16\",\"value\": \"3500\"},");
		chartData = chartData.concat("{\"label\": \"12/16\",\"value\": \"3000\"},");
		chartData = chartData.concat("{\"label\": \"1/17\",\"value\": \"4000\"},");
		chartData = chartData.concat("{\"label\": \"2/17\",\"value\": \"3500\"},");
		chartData = chartData.concat("{\"label\": \"3/17\",\"value\": \"4500\"},");
		chartData = chartData.concat("{\"label\": \"4/17\",\"value\": \"3500\"},");
		chartData = chartData.concat("{\"label\": \"5/17\",\"value\": \"3000\"},");
		chartData = chartData.concat("{\"label\": \"6/17\",\"value\": \"3500\"}");
		chartData = chartData.concat("]");
		chartData = chartData.concat("}");

		orgVulnerabilitiesHistoryColumnChart = new FusionCharts(
                "column2d",// chartType
                "orgVulnerabilitiesHistoryColumnChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "orgVulnerabilitiesHistoryColumnChartContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = orgVulnerabilitiesHistoryColumnChart.render();
	}
	
    public String getOrgVulnerabilitiesHistoryColumnChart() {
    	return data;
    }

}
