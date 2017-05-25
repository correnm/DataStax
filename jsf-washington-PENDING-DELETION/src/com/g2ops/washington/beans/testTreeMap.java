package com.g2ops.washington.beans;

import java.io.FileReader;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



@ManagedBean
public class testTreeMap {

	private String data;
	private String chartData;
	private FusionCharts testTreeMap;

	public testTreeMap() {

		JSONParser parser = new JSONParser();
		 
        try {
 
        	
        	Object obj = parser.parse(new FileReader(
                    "/Users/DangL/Documents/workspace/washington/target/washington/resources/json/topology.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            chartData = jsonObject.toJSONString();

    		System.out.println(chartData);
 


 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		testTreeMap = new FusionCharts(
                "dragnode",// chartType
                "testTreeMapID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "testTreeMapContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = testTreeMap.render();
	}
	
    public String getTestTreeMap() {
    	return data;
    }

}
