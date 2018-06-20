FusionCharts.ready(function () {
    var riskGauge = new FusionCharts({
        type: 'hlineargauge',
        renderAt: 'chart-container',
        id: 'riskGauge',
        width: '550',
        height: '190',
        dataFormat: 'json',
        dataSource: {
            "chart": {
            	"showBorder": "0",
                "theme": "g2ops",
                "caption": "Resistance Strength",
                "subcaption": "",
                "baseFont": "Arial",
                "baseFontSize": "11",
                "baseFontColor": "#231f20",
                "baseFontBold": "1",
                "lowerLimit": "0",
                "upperLimit": "10",
                "numberSuffix": "%",
                "chartBottomMargin": "40",  
                "valueFontSize": "12",  
                "valueFontBold": "1",
                //"gaugeFillRatio": "40,30,40",
                "showGaugeBorder": "1",
                "gaugeBorderColor": "{light-50}",
                "gaugeBorderThickness": "4",
                "gaugeBorderAlpha": "100",
                "tickValueStep": "4",
                "tickValueDecimals": "1",
                "forceTickValueDecimals": "1"                
            },		//end chart
            "colorRange": {
                "color": [{
                		"FontSize": "12",
                		"FontBold": "1",
                        "minValue": "0",
                        "maxValue": ".99",
                        "label": "Very Low",
                        "code": "#e44a00"
            		}, 
            		{
            			"minValue": ".99",
            			"maxValue": "3.9",
            			"label": "Low",
            			"code": "#e46a00"
            		}, 
            		{
            			"minValue": "3.9",
            			"maxValue": "6.9",
            			"label": "Medium",
            			"code": "#f8bd19"
            		}, 
            		{
            			"minValue": "6.9",
            			"maxValue": "8.9",
            			"label": "High",
            			"code": "#a4a901"
            		}, 
            		{
            			"minValue": "8.9",
            			"maxValue": "10",
            			"label": "Very High",
            			"code": "#6baa01"            			
            		}]
            },		//end colorRange
	        "pointers": {
	        	"pointer": [{
	                 "borderColor": "#28637e",
	                 "borderThickness": "2",
	                 "borderAlpha": "60",
	                 "bgColor": "#28637e",
	                 "bgAlpha": "75",
	                 "radius": "6",
	                 "sides": "4",
	                 "id": "riskValue",
	                 "value": "0",
	                 "toolText": "the resistence strength is $value%",
	                 "valueAbovePointer": "1"
	              }]		//end pointer
	       }		//end pointers	       
        }		//end datasource    
  
    });
   	riskGauge.render();
    });		// end function

