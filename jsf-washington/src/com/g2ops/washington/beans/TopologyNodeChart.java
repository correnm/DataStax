package com.g2ops.washington.beans;

import java.io.FileReader;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class TopologyNodeChart {

	private String data;
	private String chartData;
	private FusionCharts topologyMap;

	public TopologyNodeChart() {

		JSONParser parser = new JSONParser();
		 
        try {
 
        	
        	Object obj = parser.parse(new FileReader(
                    "/Users/DangL/Desktop/tata.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            chartData = jsonObject.toJSONString();

    		System.out.println(chartData);
 


 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		topologyMap = new FusionCharts(
                "dragnode",// chartType
                "topologyMapID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "topologyMapContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = topologyMap.render();
	}
	
    public String getTopologyMap() {
    	return data;
    }

}
