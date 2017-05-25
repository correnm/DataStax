package com.g2ops.washington.beans;

import java.io.FileReader;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class MessageMapChart {

	private String data;
	private String chartData;
	private FusionCharts messageMap;

	public MessageMapChart() {

		JSONParser parser = new JSONParser();
		 
        try {
 
        	
        	Object obj = parser.parse(new FileReader(
                    "/Users/DangL/Desktop/tata2.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            chartData = jsonObject.toJSONString();

    		System.out.println(chartData);
 


 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		messageMap = new FusionCharts(
                "dragnode",// chartType
                "messageMapID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "MessageMapContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = messageMap.render();
	}
	
    public String getMessageMap() {
    	return data;
    }

}
