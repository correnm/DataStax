package com.g2ops.washington.beans;

import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import com.g2ops.washington.utils.FusionCharts;

@ManagedBean
public class messageMap {

	private String data;
	private String chartData;
	private FusionCharts messageMap;

	public messageMap() {

		JSONParser parser = new JSONParser();
		 
        try {
 
        	String basePath = new File("").getAbsolutePath();
            System.out.println(basePath);

            basePath.concat("/src/main/webapp/resources/json/tata2.json");
            System.out.println(basePath);
        	//filePath.concat("path to the property file");
            
            PrintWriter out = new PrintWriter("/Users/DangL/Desktop/PWdata/danny.txt");

            out.println(basePath);
            
            out.close ();
        	
        	Object obj = parser.parse(new FileReader(
        			"/Users/DangL/Documents/workspace/washington/target/washington/resources/json/messagemap.json"));
 
            JSONObject jsonObject = (JSONObject) obj;
            chartData = jsonObject.toJSONString();

    		System.out.println(chartData);
 


 
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        messageMap = new FusionCharts(
                "dragnode",// chartType
                "testTreeMapID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "messageMapContainer",// chartContainer
                "json",// dataFormat
                chartData
		);
    	data = messageMap.render();
	}
	
    public String getMessageMap() {
    	return data;
    }

}
