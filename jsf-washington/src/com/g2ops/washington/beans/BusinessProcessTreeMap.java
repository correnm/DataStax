package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class BusinessProcessTreeMap {

	private String data;
	private String chartData;
	private FusionCharts businessProcessTreeMap;

	public BusinessProcessTreeMap() {

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
				chartData = chartData.concat("\"label\": \"Eastern Division - Virginia Beach Office\", \"fillcolor\": \"666666\", \"value\": \"1000\", \"svalue\": \"50\", ");
				chartData = chartData.concat("\"data\": ["); // subnets
					chartData = chartData.concat("{\"label\": \"10.10.1.*\", \"value\": \"75\", \"svalue\": \"22\"},");
					chartData = chartData.concat("{\"label\": \"10.10.2.*\", \"value\": \"275\", \"svalue\": \"62\"},");
					chartData = chartData.concat("{\"label\": \"10.10.3.*\", \"value\": \"60\", \"svalue\": \"33\"},");
					chartData = chartData.concat("{\"label\": \"10.10.4.*\", \"value\": \"40\", \"svalue\": \"26\"},");
					chartData = chartData.concat("{\"label\": \"10.10.5.*\", \"value\": \"100\", \"svalue\": \"20\"},");
					chartData = chartData.concat("{\"label\": \"10.10.6.*\", \"value\": \"180\", \"svalue\": \"95\"},");
					chartData = chartData.concat("{\"label\": \"10.10.7.*\", \"value\": \"120\", \"svalue\": \"80\"},");
					chartData = chartData.concat("{\"label\": \"10.10.8.*\", \"value\": \"50\", \"svalue\": \"16\"},");
					chartData = chartData.concat("{\"label\": \"10.10.9.*\", \"value\": \"70\", \"svalue\": \"44\"},");
					chartData = chartData.concat("{\"label\": \"10.10.10.*\", \"value\": \"30\", \"svalue\": \"8\"}");
				chartData = chartData.concat("]"); // subnets
			chartData = chartData.concat("}],"); // data

			chartData = chartData.concat("\"colorrange\": {"); // colorrange
				chartData = chartData.concat("\"mapbypercent\": \"0\",");
				chartData = chartData.concat("\"gradient\": \"1\",");
				chartData = chartData.concat("\"minvalue\": \"0\",");
				chartData = chartData.concat("\"code\": \"2b8118\",");
				chartData = chartData.concat("\"startlabel\": \"Low\",");
				chartData = chartData.concat("\"endlabel\": \"High\",");
				chartData = chartData.concat("\"color\": [{"); // color
					chartData = chartData.concat("\"code\": \"CC2A1F\",");
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
