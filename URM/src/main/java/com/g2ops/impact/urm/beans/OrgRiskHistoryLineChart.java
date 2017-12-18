package com.g2ops.impact.urm.beans;

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
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.g2ops.impact.urm.utils.FusionCharts;

@Named("orgRiskHistoryLineChart")
@RequestScoped
public class OrgRiskHistoryLineChart {

	private String data;
	private String chartData;
	private FusionCharts orgRiskHistoryLineChart;

    public OrgRiskHistoryLineChart() {

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"Historical Cyber Value-at-Risk (CyVaR)\",");
		chartData = chartData.concat("\"xAxisName\": \"\",");
		chartData = chartData.concat("\"yAxisName\": \"\",");
		chartData = chartData.concat("\"lineThickness\": \"2\",");
		chartData = chartData.concat("\"showCanvasBorder\": \"0\",");
		chartData = chartData.concat("\"numberPrefix\": \"$\",");
		chartData = chartData.concat("\"paletteColors\": \"#28637e\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"10/15\",\"value\": \"151000000\"},");
		chartData = chartData.concat("{\"label\": \"11/15\",\"value\": \"165000000\"},");
		chartData = chartData.concat("{\"label\": \"12/15\",\"value\": \"155000000\"},");
		chartData = chartData.concat("{\"label\": \"1/16\",\"value\": \"150000000\"},");
		chartData = chartData.concat("{\"label\": \"2/16\",\"value\": \"155000000\"},");
		chartData = chartData.concat("{\"label\": \"3/16\",\"value\": \"145000000\"},");
		chartData = chartData.concat("{\"label\": \"4/16\",\"value\": \"150000000\"},");
		chartData = chartData.concat("{\"label\": \"5/16\",\"value\": \"140000000\"},");
		chartData = chartData.concat("{\"label\": \"6/16\",\"value\": \"135000000\"},");
		chartData = chartData.concat("{\"label\": \"7/16\",\"value\": \"130000000\"},");
		chartData = chartData.concat("{\"label\": \"8/16\",\"value\": \"145000000\"},");
		chartData = chartData.concat("{\"label\": \"9/16\",\"value\": \"172000000\",\"toolText\": \"Global Security Event: Petya\"},");
		chartData = chartData.concat("{\"label\": \"10/16\",\"value\":\"140000000\",\"toolText\": \"Petya Mitigation\"},");
		chartData = chartData.concat("{\"label\": \"11/16\",\"value\": \"135000000\"},");
		chartData = chartData.concat("{\"label\": \"12/16\",\"value\": \"125000000\"},");
		chartData = chartData.concat("{\"label\": \"1/17\",\"value\": \"120000000\"},");
		chartData = chartData.concat("{\"label\": \"2/17\",\"value\": \"115000000\"},");
		chartData = chartData.concat("{\"label\": \"3/17\",\"value\": \"175000000\",\"toolText\": \"Global Security Event: WannaCry\"},");
		chartData = chartData.concat("{\"label\": \"4/17\",\"value\": \"130000000\",\"toolText\": \"WannaCry Mitigation\"},");
		chartData = chartData.concat("{\"label\": \"5/17\",\"value\": \"125000000\"},");
		chartData = chartData.concat("{\"label\": \"6/17\",\"value\": \"122000000\"},");
		chartData = chartData.concat("{\"label\": \"7/17\",\"value\": \"120000000\"},");
		chartData = chartData.concat("{\"label\": \"8/17\",\"value\": \"120000000\"},");
		chartData = chartData.concat("{\"label\": \"9/17\",\"value\": \"117000000\"},");
		chartData = chartData.concat("{\"label\": \"10/17\",\"value\": \"110000000\"}");
		chartData = chartData.concat("],");
		chartData = chartData.concat("\"trendlines\": [{");
		chartData = chartData.concat("\"line\": [{");
		chartData = chartData.concat("\"startvalue\": \"100000000\",");
		chartData = chartData.concat("\"endvalue\": \"150000000\",");
		chartData = chartData.concat("\"color\": \"#595a5c\",");
		chartData = chartData.concat("\"displayvalue\": \"Risk{br}Tolerance{br}$150M\",");
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
