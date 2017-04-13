package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class CriticalSystemsAtRiskPieChart {

	private String data;
	private String chartData;
	private FusionCharts criticalSystemsAtRiskPieChart;

	public CriticalSystemsAtRiskPieChart() {

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"% Critical Systems at Risk\",");
		chartData = chartData.concat("\"decimals\": \"1\",");
		chartData = chartData.concat("\"enableSmartLabels\": \"1\",");
		chartData = chartData.concat("\"showPercentValues\": \"1\",");
		chartData = chartData.concat("\"showShadow\": \"0\",");
		chartData = chartData.concat("\"showplotborder\": \"0\",");
		chartData = chartData.concat("\"pieRadius\": \"60\",");
		chartData = chartData.concat("\"use3DLighting\": \"0\",");
		chartData = chartData.concat("\"paletteColors\": \"#CC2A1F,#FFC80D,#2b8118\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"High\",\"value\": \"24.6\"},");
		chartData = chartData.concat("{\"label\": \"Medium\",\"value\": \"59.6\"},");
		chartData = chartData.concat("{\"label\": \"Low\",\"value\": \"15.8\"},");
		chartData = chartData.concat("]");
		chartData = chartData.concat("}");

		criticalSystemsAtRiskPieChart = new FusionCharts(
                "pie2d",// chartType
                "criticalSystemsAtRiskPieChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "criticalSystemsAtRiskPieChartContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = criticalSystemsAtRiskPieChart.render();
	}
	
    public String getCriticalSystemsAtRiskPieChart() {
    	return data;
    }

}
