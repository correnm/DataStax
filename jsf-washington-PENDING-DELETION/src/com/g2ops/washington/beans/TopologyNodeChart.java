package com.g2ops.washington.beans;

import java.io.FileReader;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@ManagedBean
public class TopologyNodeChart {

	private String data;
	private String chartData;
	private FusionCharts TopologyNodeChart;

	public TopologyNodeChart() {

		JSONParser parser = new JSONParser();
		 
        try {
 
        	
        	Object obj = parser.parse(new FileReader(
                    "/Users/DangL/Documents/workspace/washington/target/washington/resources/json/messagemap.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            chartData = jsonObject.toJSONString();

    		System.out.println(chartData);
 


 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		TopologyNodeChart = new FusionCharts(
                "dragnode",// chartType
                "TopologyNodeChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "TopologyNodeChartContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = TopologyNodeChart.render();
	}
	
    public String getTopologyNodeChart() {
    	return data;
    }

}
