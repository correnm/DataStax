package com.g2ops.washington.beans;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import com.g2ops.washington.utils.DatabaseConnectionManager;
import com.g2ops.washington.utils.FusionCharts;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

@ManagedBean
public class OrgRiskHistoryChart3 {

	private String data;
	private String chartData;
	private FusionCharts orgRiskHistoryChart;
    private String releaseVersion = "";
	private DatabaseConnectionManager dbConnection;

    public OrgRiskHistoryChart3() {

    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	ServletContext ctx = request.getServletContext();
    	dbConnection = (DatabaseConnectionManager)ctx.getAttribute("appAuthDBManager");
    	ResultSet rs = dbConnection.getSession().execute("select release_version from system.local");
    	Row row = rs.one();
    	releaseVersion = row.getString("release_version");

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"clickURL\": \"login.jsf\",");
		chartData = chartData.concat("\"caption\": \"Organization At-Risk History\",");
		//chartData = chartData.concat("\"subCaption\": \"By Quarter\",");
		chartData = chartData.concat("\"subCaption\": \"");
		chartData = chartData.concat(releaseVersion);
		chartData = chartData.concat("\",");
		chartData = chartData.concat("\"xAxisName\": \"\",");
		chartData = chartData.concat("\"yAxisName\": \"$$ At-Risk\",");
		chartData = chartData.concat("\"lineThickness\": \"2\",");
		chartData = chartData.concat("\"numberPrefix\": \"$\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"2016{br}Q1\",\"value\": \"500000\"},");
		chartData = chartData.concat("{\"label\": \"2016{br}Q2\",\"value\": \"450000\"},");
		chartData = chartData.concat("{\"label\": \"2016{br}Q3\",\"value\": \"600000\"},");
		chartData = chartData.concat("{\"label\": \"2016{br}Q4\",\"value\": \"400000\"},");
		chartData = chartData.concat("{\"label\": \"2017{br}Q1\",\"value\": \"250000\"}");
		chartData = chartData.concat("],");
		chartData = chartData.concat("\"trendlines\": [{");
		chartData = chartData.concat("\"line\": [{");
		chartData = chartData.concat("\"startvalue\": \"440000\",");
		chartData = chartData.concat("\"color\": \"#2b8118\",");
		chartData = chartData.concat("\"displayvalue\": \"Average{br}At-Risk\",");
		chartData = chartData.concat("\"valueOnRight\": \"1\",");
		chartData = chartData.concat("\"thickness\": \"2\"");
		chartData = chartData.concat("}]");
		chartData = chartData.concat("}]}");

		orgRiskHistoryChart = new FusionCharts(
                "line",// chartType
                "orgRiskHistoryChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "orgRiskHistoryChartContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = orgRiskHistoryChart.render();
	}
	
    public String getOrgRiskHistoryChart3() {
    	return data;
    }

}
