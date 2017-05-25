package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class CriticalSystemsAtRiskPieChartXML {

	private String data;
	private String chartData;
	private FusionCharts criticalSystemsAtRiskPieChartXML;

	public CriticalSystemsAtRiskPieChartXML() {

		chartData = "<chart theme=\"g2ops\" caption=\"% Critical Systems at Risk\" decimals=\"1\" enableSmartLabels=\"1\" showPercentValues=\"1\" showShadow=\"0\" showplotborder=\"0\" pieRadius=\"60\" use3DLighting=\"0\" paletteColors=\"#CC2A1F,#FFC80D,#2b8118\">";
		chartData = chartData.concat("<set label=\"High\" value=\"24.6\" />");
		chartData = chartData.concat("<set label=\"Medium\" value=\"59.6\" />");
		chartData = chartData.concat("<set label=\"Low\" value=\"15.8\" />");
		chartData = chartData.concat("</chart>");
		
		criticalSystemsAtRiskPieChartXML = new FusionCharts(
                "pie2d",// chartType
                "criticalSystemsAtRiskPieChartXMLID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "criticalSystemsAtRiskPieChartXMLContainer",// chartContainer
                "xml",// dataFormat
                chartData
		);
    	data = criticalSystemsAtRiskPieChartXML.render();
	}
	
    public String getCriticalSystemsAtRiskPieChartXML() {
    	return data;
    }

}
