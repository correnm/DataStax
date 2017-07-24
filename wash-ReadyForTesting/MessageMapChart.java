package com.g2ops.washington.beans;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.g2ops.washington.utils.FusionCharts;

/**
 * @author 		Dang Le, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			https://stackoverflow.com/questions/32251251/java-classloader-getresource-with-special-characters-in-path
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 24-Jul-2017		corren.mccoy		Changed file access pattern to properly handle getResource with special characters in the path
 * 
 * 
 * 
 */

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
        	//File file = new File(classLoader.getResource("json/messagemap.json").getFile());
        	URL resource = classLoader.getResource("json/messagemap.json");
        	// 07-24-2017: handle special characters which might be contained in the path.
        	// Without special handling, can result in java.io.FileNotFoundException at runtime
        	// which can cause any screen where this chart is embedded not to display.
        	String configPath = URLDecoder.decode(resource.getFile(), "UTF-8");
        	File file = new File(configPath);
        	Scanner scanner = new Scanner(file);

        	while (scanner.hasNextLine()) {
    			String line = scanner.nextLine();
    			result.append(line).append("\n");
    		}

        	scanner.close();

    		Object obj = parser.parse(result.toString());
        	
            JSONObject jsonObject = (JSONObject) obj;
            chartData = jsonObject.toJSONString();

            // DEBUG: print chart data to console
    		//System.out.println(chartData);
  
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		messageMap = new FusionCharts(
                "dragnode",				// chartType
                "messageMapID",			// chartId
                "100%","100%",			// chartWidth, chartHeight
                "MessageMapContainer",	// chartContainer
                "json",					// dataFormat
                chartData
		);
    	data = messageMap.render();
	}
	
    public String getMessageMap() {
    	return data;
    }

}