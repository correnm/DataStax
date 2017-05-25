package com.g2ops.washington.beans;

import java.io.FileReader;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.g2ops.washington.utils.FusionCharts;

	@ManagedBean
	public class timelineManagementChart {
	   
		private String data;
		private String chartData;
		private FusionCharts timelineManagementChart;

		public timelineManagementChart() {

			JSONParser parser = new JSONParser();
			 
	        try {
	        	
	        	Object obj = parser.parse(new FileReader(
	        			"/Users/DangL/Desktop/PWdata/timeline.json"));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	            chartData = jsonObject.toJSONString();

	    		System.out.println(chartData);
	 


	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        timelineManagementChart = new FusionCharts(
                    "sparkcolumn",// chartType
                    "timelineChartID",// chartId
                    "100%","100%",// chartWidth, chartHeight
                    "timelineChartContainer",// chartContainer
                    "json",// dataFormat
                    chartData
    		);
	    	data = timelineManagementChart.render();
		}
		
	    public String getTimelineChart() {
	    	return data;
	    }

	}

