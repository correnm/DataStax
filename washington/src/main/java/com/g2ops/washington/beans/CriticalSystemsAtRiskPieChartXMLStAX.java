package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@ManagedBean
public class CriticalSystemsAtRiskPieChartXMLStAX {

	private String data;
	private String chartData;
	private FusionCharts criticalSystemsAtRiskPieChartXMLStAX;

	public CriticalSystemsAtRiskPieChartXMLStAX() {
		
		try {
			
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("chart");
			xMLStreamWriter.writeAttribute("theme", "g2ops");
			xMLStreamWriter.writeAttribute("caption", "% Critical Systems at Risk");
			xMLStreamWriter.writeAttribute("pieRadius", "60");
			xMLStreamWriter.writeAttribute("paletteColors", "#CC2A1F,#FFC80D,#2b8118");
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "High");
			xMLStreamWriter.writeAttribute("value", "36.6");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "Medium");
			xMLStreamWriter.writeAttribute("value", "47.6");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "Low");
			xMLStreamWriter.writeAttribute("value", "15.8");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndDocument();
			xMLStreamWriter.flush();
			xMLStreamWriter.close();
			
			chartData = stringWriter.getBuffer().toString();
			
			stringWriter.close();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		criticalSystemsAtRiskPieChartXMLStAX = new FusionCharts(
                "pie2d",// chartType
                "criticalSystemsAtRiskPieChartXMLID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "criticalSystemsAtRiskPieChartXMLContainer",// chartContainer
                "xml",// dataFormat
                chartData
		);
    	data = criticalSystemsAtRiskPieChartXMLStAX.render();
	}
	
    public String getCriticalSystemsAtRiskPieChartXMLStAX() {
    	return data;
    }

}
