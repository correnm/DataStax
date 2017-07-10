package com.g2ops.washington.beans;

import javax.faces.bean.*;
import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class OrgRiskHistoryLineChart {

	private String data;
	private String chartData;
	private FusionCharts orgRiskHistoryLineChart;

    public OrgRiskHistoryLineChart() {

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"Cyber Value-at-Risk (CyVaR)\",");
		chartData = chartData.concat("\"xAxisName\": \"\",");
		chartData = chartData.concat("\"yAxisName\": \"\",");
		chartData = chartData.concat("\"lineThickness\": \"2\",");
		chartData = chartData.concat("\"showCanvasBorder\": \"0\",");
		chartData = chartData.concat("\"numberPrefix\": \"$\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"7/15\",\"value\": \"500000\"},");
		chartData = chartData.concat("{\"label\": \"8/15\",\"value\": \"450000\"},");
		chartData = chartData.concat("{\"label\": \"9/15\",\"value\": \"600000\"},");
		chartData = chartData.concat("{\"label\": \"10/15\",\"value\": \"750000\"},");
		chartData = chartData.concat("{\"label\": \"11/15\",\"value\": \"650000\"},");
		chartData = chartData.concat("{\"label\": \"12/15\",\"value\": \"550000\"},");
		chartData = chartData.concat("{\"label\": \"1/16\",\"value\": \"500000\"},");
		chartData = chartData.concat("{\"label\": \"2/16\",\"value\": \"550000\"},");
		chartData = chartData.concat("{\"label\": \"3/16\",\"value\": \"450000\"},");
		chartData = chartData.concat("{\"label\": \"4/16\",\"value\": \"500000\"},");
		chartData = chartData.concat("{\"label\": \"5/16\",\"value\": \"400000\"},");
		chartData = chartData.concat("{\"label\": \"6/16\",\"value\": \"350000\"},");
		chartData = chartData.concat("{\"label\": \"7/16\",\"value\": \"300000\"},");
		chartData = chartData.concat("{\"label\": \"8/16\",\"value\": \"450000\"},");
		chartData = chartData.concat("{\"label\": \"9/16\",\"value\": \"500000\"},");
		chartData = chartData.concat("{\"label\": \"10/16\",\"value\": \"400000\"},");
		chartData = chartData.concat("{\"label\": \"11/16\",\"value\": \"350000\"},");
		chartData = chartData.concat("{\"label\": \"12/16\",\"value\": \"250000\"},");
		chartData = chartData.concat("{\"label\": \"1/17\",\"value\": \"200000\"},");
		chartData = chartData.concat("{\"label\": \"2/17\",\"value\": \"150000\"},");
		chartData = chartData.concat("{\"label\": \"3/17\",\"value\": \"250000\"},");
		chartData = chartData.concat("{\"label\": \"4/17\",\"value\": \"300000\"},");
		chartData = chartData.concat("{\"label\": \"5/17\",\"value\": \"250000\"},");
		chartData = chartData.concat("{\"label\": \"6/17\",\"value\": \"200000\"}");
		chartData = chartData.concat("],");
		chartData = chartData.concat("\"trendlines\": [{");
		chartData = chartData.concat("\"line\": [{");
		chartData = chartData.concat("\"startvalue\": \"500000\",");
		chartData = chartData.concat("\"color\": \"#2b8118\",");
		chartData = chartData.concat("\"displayvalue\": \"Risk{br}Tolerance{br}$500K\",");
		chartData = chartData.concat("\"valueOnRight\": \"1\",");
		chartData = chartData.concat("\"thickness\": \"2\"");
		chartData = chartData.concat("}]");
		chartData = chartData.concat("}]}");

		orgRiskHistoryLineChart = new FusionCharts(
                "line",// chartType
                "orgRiskHistoryLineChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "orgRiskHistoryLineChartContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = orgRiskHistoryLineChart.render();
	}
	
    public String getOrgRiskHistoryLineChart() {
    	return data;
    }

}
