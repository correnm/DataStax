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
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.g2ops.impact.urm.utils.FusionCharts;

@Named("criticalSystemsAtRiskPieChart")
@RequestScoped
public class CriticalSystemsAtRiskPieChart {

	private String data;
	private String chartData;
	private FusionCharts criticalSystemsAtRiskPieChart;

	public CriticalSystemsAtRiskPieChart() {

		try {
			
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			
			//xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("chart");
			xMLStreamWriter.writeAttribute("theme", "g2ops");
			xMLStreamWriter.writeAttribute("caption", "% Critical Processes at Risk (BPIV)");
			xMLStreamWriter.writeAttribute("showLabels", "0");
			xMLStreamWriter.writeAttribute("showValues", "0");
			//xMLStreamWriter.writeAttribute("placevaluesInside", "1");
			//xMLStreamWriter.writeAttribute("valueFontColor", "#ffffff");
			//xMLStreamWriter.writeAttribute("pieRadius", "100");
			//xMLStreamWriter.writeAttribute("paletteColors", "#CC2A1F,#FFC80D,#2b8118");
			//xMLStreamWriter.writeAttribute("paletteColors", "#1D495D,#28637E,#31799B");
			//xMLStreamWriter.writeAttribute("paletteColors", "#df382c,#da543a,#efb73e,#38b44a");

			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "Critical");
			xMLStreamWriter.writeAttribute("value", "10.0");
			xMLStreamWriter.writeAttribute("color", "#df382c");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "High");
			xMLStreamWriter.writeAttribute("value", "16.0");
			xMLStreamWriter.writeAttribute("color", "#e95420");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "Medium");
			xMLStreamWriter.writeAttribute("value", "35.0");
			xMLStreamWriter.writeAttribute("color", "#efb73e");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "Low");
			xMLStreamWriter.writeAttribute("value", "39.0");
			xMLStreamWriter.writeAttribute("color", "#38b44a");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeEndElement();
			//xMLStreamWriter.writeEndDocument();
			xMLStreamWriter.flush();
			xMLStreamWriter.close();
			
			chartData = stringWriter.getBuffer().toString();
			
			stringWriter.close();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		criticalSystemsAtRiskPieChart = new FusionCharts(
                "pie2d",// chartType
                "criticalSystemsAtRiskPieChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "criticalSystemsAtRiskPieChartContainer",// chartContainer
                "xml",// dataFormat
                chartData
		);
    	data = criticalSystemsAtRiskPieChart.render();
	}
	
    public String getCriticalSystemsAtRiskPieChart() {
    	return data;
    }

}