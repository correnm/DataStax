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
 * 14-Aug-2017		corren.mccoy		Moved timeline ahead by one month for demo purposes
 * 
 */

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
		chartData = chartData.concat("\"paletteColors\": \"#28637e\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"8/15\",\"value\": \"45000000\"},");
		chartData = chartData.concat("{\"label\": \"9/15\",\"value\": \"60000000\"},");
		chartData = chartData.concat("{\"label\": \"10/15\",\"value\": \"51000000\"},");
		chartData = chartData.concat("{\"label\": \"11/15\",\"value\": \"65000000\"},");
		chartData = chartData.concat("{\"label\": \"12/15\",\"value\": \"55000000\"},");
		chartData = chartData.concat("{\"label\": \"1/16\",\"value\": \"50000000\"},");
		chartData = chartData.concat("{\"label\": \"2/16\",\"value\": \"55000000\"},");
		chartData = chartData.concat("{\"label\": \"3/16\",\"value\": \"45000000\"},");
		chartData = chartData.concat("{\"label\": \"4/16\",\"value\": \"50000000\"},");
		chartData = chartData.concat("{\"label\": \"5/16\",\"value\": \"40000000\"},");
		chartData = chartData.concat("{\"label\": \"6/16\",\"value\": \"35000000\"},");
		chartData = chartData.concat("{\"label\": \"7/16\",\"value\": \"30000000\"},");
		chartData = chartData.concat("{\"label\": \"8/16\",\"value\": \"45000000\"},");
		chartData = chartData.concat("{\"label\": \"9/16\",\"value\": \"72000000\",\"toolText\": \"Global Security Event: Petya\"},");
		chartData = chartData.concat("{\"label\": \"10/16\",\"value\":\"40000000\",\"toolText\": \"Petya Mitigation\"},");
		chartData = chartData.concat("{\"label\": \"11/16\",\"value\": \"35000000\"},");
		chartData = chartData.concat("{\"label\": \"12/16\",\"value\": \"25000000\"},");
		chartData = chartData.concat("{\"label\": \"1/17\",\"value\": \"20000000\"},");
		chartData = chartData.concat("{\"label\": \"2/17\",\"value\": \"15000000\"},");
		chartData = chartData.concat("{\"label\": \"3/17\",\"value\": \"75000000\",\"toolText\": \"Global Security Event: WannaCry\"},");
		chartData = chartData.concat("{\"label\": \"4/17\",\"value\": \"30000000\",\"toolText\": \"WannaCry Mitigation\"},");
		chartData = chartData.concat("{\"label\": \"5/17\",\"value\": \"25000000\"},");
		chartData = chartData.concat("{\"label\": \"6/17\",\"value\": \"22000000\"},");
		chartData = chartData.concat("{\"label\": \"7/17\",\"value\": \"20000000\"}");
		chartData = chartData.concat("],");
		chartData = chartData.concat("\"trendlines\": [{");
		chartData = chartData.concat("\"line\": [{");
		chartData = chartData.concat("\"startvalue\": \"42000000\",");
		chartData = chartData.concat("\"endvalue\": \"50000000\",");
		chartData = chartData.concat("\"color\": \"#595a5c\",");
		chartData = chartData.concat("\"displayvalue\": \"Risk{br}Tolerance{br}$50M\",");
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
