package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import com.g2ops.washington.utils.FusionCharts;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@ManagedBean
public class PatchesExceedingThresholdPieChartXML {

	private String data;
	private String chartData;
	private FusionCharts patchesExceedingThresholdPieChartXML;

	public PatchesExceedingThresholdPieChartXML() {
		
		try {
			
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			
			//xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("chart");
			xMLStreamWriter.writeAttribute("theme", "g2ops");
			xMLStreamWriter.writeAttribute("caption", "Patches Exceeding Threshold");
			xMLStreamWriter.writeAttribute("pieRadius", "60");
			xMLStreamWriter.writeAttribute("valueFontColor", "#231f20");
			//xMLStreamWriter.writeAttribute("paletteColors", "#CC2A1F,#FFC80D,#2b8118");
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "120 days");
			xMLStreamWriter.writeAttribute("value", "12.6");
			xMLStreamWriter.writeAttribute("color", "#df382c");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "90 days");
			xMLStreamWriter.writeAttribute("value", "21.3");
			xMLStreamWriter.writeAttribute("color", "#e95420");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "60 days");
			xMLStreamWriter.writeAttribute("value", "26.4");
			xMLStreamWriter.writeAttribute("color", "#efb73e");
			xMLStreamWriter.writeEndElement();
			
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("label", "30 days");
			xMLStreamWriter.writeAttribute("value", "39.7");
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
		
		patchesExceedingThresholdPieChartXML = new FusionCharts(
                "pie2d",// chartType
                "patchesExceedingThresholdPieChartXMLID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "patchesExceedingThresholdPieChartXMLContainer",// chartContainer
                "xml",// dataFormat
                chartData
		);
    	data = patchesExceedingThresholdPieChartXML.render();
	}

	public String getPatchesExceedingThresholdPieChartXML() {
		return data;
    }

}
