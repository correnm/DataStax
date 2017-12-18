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

@Named("patchesExceedingThresholdPieChart")
@RequestScoped
public class PatchesExceedingThresholdPieChart {

	private String data;
	private String chartData;
	private FusionCharts patchesExceedingThresholdPieChart;

	public PatchesExceedingThresholdPieChart() {
		
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
		
		patchesExceedingThresholdPieChart = new FusionCharts(
                "pie2d",// chartType
                "patchesExceedingThresholdPieChartID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "patchesExceedingThresholdPieChartContainer",// chartContainer
                "xml",// dataFormat
                chartData
		);
    	data = patchesExceedingThresholdPieChart.render();
	}

	public String getPatchesExceedingThresholdPieChart() {
		return data;
    }

}
