package com.g2ops.washington.beans;

import java.io.File;

import java.util.Scanner;

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
 
        	StringBuilder result = new StringBuilder("");
        	ClassLoader classLoader = getClass().getClassLoader();
        	File file = new File(classLoader.getResource("json/messagemap.json").getFile());
        	Scanner scanner = new Scanner(file);

        	while (scanner.hasNextLine()) {
    			String line = scanner.nextLine();
    			result.append(line).append("\n");
    		}

        	scanner.close();

    		Object obj = parser.parse(result.toString());
        	
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