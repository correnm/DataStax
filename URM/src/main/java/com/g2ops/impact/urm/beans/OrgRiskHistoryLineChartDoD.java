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
 * 14-Aug-2017		corren.mccoy		Moved timeline ahead by one month for demo purposes
 * 26-Aug-2017		corren.mccoy		Re-designed to be DoD/mission centric
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.g2ops.impact.urm.utils.FusionCharts;

@Named("orgRiskHistoryLineChartDoD")
@RequestScoped
public class OrgRiskHistoryLineChartDoD {

	private String data;
	private String chartData;
	private FusionCharts orgRiskHistoryLineChartDoD;

    public OrgRiskHistoryLineChartDoD() {

		chartData = "{\"chart\": {";
		chartData = chartData.concat("\"theme\": \"g2ops\",");
		chartData = chartData.concat("\"caption\": \"Aggregated Cybersecurity Posture\",");
		chartData = chartData.concat("\"xAxisName\": \"\",");
		chartData = chartData.concat("\"yAxisName\": \"\",");
		chartData = chartData.concat("\"lineThickness\": \"2\",");
		chartData = chartData.concat("\"showCanvasBorder\": \"0\",");
		chartData = chartData.concat("\"numberPrefix\": \"\",");
		chartData = chartData.concat("\"paletteColors\": \"#28637e\",");
		chartData = chartData.concat("},");
		chartData = chartData.concat("\"data\": [");
		chartData = chartData.concat("{\"label\": \"11/15\",\"value\": \"91.0\"},");
		chartData = chartData.concat("{\"label\": \"12/15\",\"value\": \"92.3\"},");
		chartData = chartData.concat("{\"label\": \"1/16\",\"value\": \"93.2\"},");
		chartData = chartData.concat("{\"label\": \"2/16\",\"value\": \"94.0\"},");
		chartData = chartData.concat("{\"label\": \"3/16\",\"value\": \"93.4\"},");
		chartData = chartData.concat("{\"label\": \"4/16\",\"value\": \"97.4\"},");
		chartData = chartData.concat("{\"label\": \"5/16\",\"value\": \"93.1\"},");
		chartData = chartData.concat("{\"label\": \"6/16\",\"value\": \"94.1\"},");
		chartData = chartData.concat("{\"label\": \"7/16\",\"value\": \"95.6\"},");
		chartData = chartData.concat("{\"label\": \"8/16\",\"value\": \"96.5\"},");
		chartData = chartData.concat("{\"label\": \"9/16\",\"value\": \"81.2\",\"toolText\": \"Global Security Event: Petya\"},");
		chartData = chartData.concat("{\"label\": \"10/16\",\"value\":\"89.6\",\"toolText\": \"Petya Mitigation\"},");
		chartData = chartData.concat("{\"label\": \"11/16\",\"value\": \"93.7\"},");
		chartData = chartData.concat("{\"label\": \"12/16\",\"value\": \"97.1\"},");
		chartData = chartData.concat("{\"label\": \"1/17\",\"value\": \"98.0\"},");
		chartData = chartData.concat("{\"label\": \"2/17\",\"value\": \"97.8\"},");
		chartData = chartData.concat("{\"label\": \"3/17\",\"value\": \"81.4\",\"displayValue\": \"Windows{br}Critical{br}Advisory{br}14 Mar 2017\"},");
		chartData = chartData.concat("{\"label\": \"4/17\",\"value\": \"94.3\",\"toolText\": \"WannaCry Mitigation\"},");
		chartData = chartData.concat("{\"label\": \"5/17\",\"value\": \"98.2\",\"displayValue\": \"Weaponization{br}12 May 2017\" },");
		chartData = chartData.concat("{\"label\": \"6/17\",\"value\": \"99.0\"},");
		chartData = chartData.concat("{\"label\": \"7/17\",\"value\": \"98.8\"},");
		chartData = chartData.concat("{\"label\": \"8/17\",\"value\": \"98.3\"},");
		chartData = chartData.concat("{\"label\": \"9/17\",\"value\": \"98.8\"},");
		chartData = chartData.concat("{\"label\": \"10/17\",\"value\": \"98.9\"}");
		chartData = chartData.concat("],");
		chartData = chartData.concat("\"trendlines\": [{");
		chartData = chartData.concat("\"line\": [{");
		chartData = chartData.concat("\"startvalue\": \"99.996\",");
		chartData = chartData.concat("\"endvalue\": \"99.996\",");
		chartData = chartData.concat("\"color\": \"#595a5c\",");
		chartData = chartData.concat("\"displayvalue\": \"Risk Assurance{br}Threshold{br}99.996%\",");
		chartData = chartData.concat("\"valueOnRight\": \"1\",");
		chartData = chartData.concat("\"thickness\": \"4\"");
		chartData = chartData.concat("}]");
		chartData = chartData.concat("}]}");
/****
		   "annotations": {
			        "width": "500",
			        "height": "300",
			        "autoScale": "1",
			        "groups": [
			            {
			                "id": "user-images",
			                "items": [
			                    {
			                        "id": "dyn-label-bg",
			                        "type": "rectangle",
			                        "showBorder": "1",
			                        "borderColor": "12345d",
			                        "fillcolor": "ffffff",
			                        "x": "$canvasEndY-245",
			                        "y": "$canvasEndY+45",
			                        "tox": "$canvasEndX+10",
			                        "toy": "$canvasEndY + 80"
			                    },
			                    {
			                        "id": "dyn-label",
			                        "type": "text",
			                        "fillcolor": "#000000",
			                        "fontsize": "11",
			                        "text": "Global Security Events: Petya and WannaCry",
			                        "bold": "1",
			                        "wrap": "1",
			                        "wrapWidth": "350",
			                        "x": "$canvasEndY-72",
			                        "y": "$canvasEndY + 60"
			                    }
			                ]
			            }
			        ]
			    },
					
*****/		
		
		
		
		
		
		
		
		
		
		
		orgRiskHistoryLineChartDoD = new FusionCharts(
                "line",// chartType
                "orgRiskHistoryLineChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "orgRiskHistoryLineChartContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = orgRiskHistoryLineChartDoD.render();
	}
	
    public String getOrgRiskHistoryLineChartDoD() {
    	return data;
    }

}
