package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class BusinessProcessTreeMapBackup {

	private String data;
	private String chartData;
	private FusionCharts businessProcessTreeMap;

	public BusinessProcessTreeMapBackup() {

		chartData = "{";

			chartData = chartData.concat("\"chart\": {");

				chartData = chartData.concat("\"theme\": \"g2ops\",");
				chartData = chartData.concat("\"animation\": \"0\",");
				chartData = chartData.concat("\"hideTitle\": \"1\",");
				chartData = chartData.concat("\"horizontalPadding\": \"0\",");
				chartData = chartData.concat("\"verticalPadding\": \"0\",");

				chartData = chartData.concat("\"algorithm\": \"squarified\",");
				chartData = chartData.concat("\"caption\": \"Subnet Vulnerabilities\",");

				chartData = chartData.concat("\"plotToolText\": \"<div><b>$label</b><br/> <b>Vulnerabilities: </b>$value<br/><b>Severity: </b>$svalue</div>\",");
				chartData = chartData.concat("\"plotborderthickness\": \".5\",");
				chartData = chartData.concat("\"plotbordercolor\": \"666666\",");

				chartData = chartData.concat("\"labelGlow\": \"0\",");
				chartData = chartData.concat("\"labelFontColor\": \"ffffff\",");
				chartData = chartData.concat("\"labelFontSize\": \"8\",");
				chartData = chartData.concat("\"labelFontBold\": \"1\",");

				chartData = chartData.concat("\"showLegend\": \"1\",");
				chartData = chartData.concat("\"legendpadding\": \"0\",");
				chartData = chartData.concat("\"legendItemFontSize\": \"10\",");
				chartData = chartData.concat("\"legendItemFontBold\": \"1\",");
				chartData = chartData.concat("\"legenditemfontcolor\": \"ffffff\",");
				chartData = chartData.concat("\"legendScaleLineThickness\": \"0\",");
				chartData = chartData.concat("\"legendCaptionFontSize\": \"10\",");
				chartData = chartData.concat("\"legendaxisbordercolor\": \"bfbfbf\",");
				// chartData = chartData.concat("\"legendCaption\": \"Growth in sales - Compared to previous year\"");

			chartData = chartData.concat("},");  // chart

			chartData = chartData.concat("\"data\": [{"); // data
				chartData = chartData.concat("\"label\": \"Organization Name\", \"fillcolor\": \"333333\", \"value\": \"1000000\", \"svalue\": \"250\", ");
				chartData = chartData.concat("\"data\": ["); // business processes
					chartData = chartData.concat("{\"label\": \"EMR\", \"value\": \"200000\", \"data\": [{\"label\": \"Site 1\", \"value\": \"100000\", \"svalue\": \"30\"}, {\"label\": \"Site 2\", \"value\": \"100000\", \"svalue\": \"20\"}]},");
					chartData = chartData.concat("{\"label\": \"FIN\", \"value\": \"300000\", \"data\": [{\"label\": \"Site 3\", \"value\": \"100000\", \"svalue\": \"100\"}, {\"label\": \"Site 4\", \"value\": \"200000\", \"svalue\": \"50\"}]},");
					chartData = chartData.concat("{\"label\": \"AP\", \"value\": \"500000\", \"data\": [{\"label\": \"Site 5\", \"value\": \"200000\", \"svalue\": \"25\"}, {\"label\": \"Site 6\", \"value\": \"300000\", \"svalue\": \"50\"}]}");
				chartData = chartData.concat("]"); // business processes
			chartData = chartData.concat("}],"); // data

			chartData = chartData.concat("\"colorrange\": {"); // colorrange
				chartData = chartData.concat("\"mapbypercent\": \"1\",");
				chartData = chartData.concat("\"gradient\": \"1\",");
				chartData = chartData.concat("\"minvalue\": \"0\",");
				chartData = chartData.concat("\"code\": \"001900\",");
				chartData = chartData.concat("\"startlabel\": \"Low\",");
				chartData = chartData.concat("\"endlabel\": \"High\",");
				chartData = chartData.concat("\"color\": [{"); // color
					chartData = chartData.concat("\"code\": \"00cc00\",");
					chartData = chartData.concat("\"maxvalue\": \"100\",");
					chartData = chartData.concat("\"label\": \"High\"");
				chartData = chartData.concat("}]"); // color
			chartData = chartData.concat("}"); // colorrange

		chartData = chartData.concat("}");
		
		businessProcessTreeMap = new FusionCharts(
                "treemap",// chartType
                "businessProcessTreeMapID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "businessProcessTreeMapContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = businessProcessTreeMap.render();
	}
	
    public String getBusinessProcessTreeMap() {
    	return data;
    }

}
