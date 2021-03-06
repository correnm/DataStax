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

@Named("vulnerabilitiesByBusinessProcessChart")
@RequestScoped
public class VulnerabilitiesByBusinessProcessChart {
	   
	private String data;
	private String chartData;
	private FusionCharts vulnerabilitiesByBusinessProcessChart;

	public VulnerabilitiesByBusinessProcessChart() {

		try {
				
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
				
			//xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("chart");
			xMLStreamWriter.writeAttribute("theme", "g2ops");
			xMLStreamWriter.writeAttribute("caption", "Vulnerabilities by Business Process");
			//xMLStreamWriter.writeAttribute("paletteColors", "#cc2a1f,#ff6600,#ffc80d,#2b8118");
			xMLStreamWriter.writeAttribute("paletteColors", "#df382c,#e95420,#efb73e,#38b44a");
			xMLStreamWriter.writeAttribute("usePlotGradientColor", "0");
			xMLStreamWriter.writeAttribute("showSum", "1");
			xMLStreamWriter.writeAttribute("valueFontColor", "#231f20");
			xMLStreamWriter.writeAttribute("seriesNameInToolTip", "0");
			xMLStreamWriter.writeAttribute("showplotborder", "0");
			xMLStreamWriter.writeAttribute("showcanvasborder", "0");
			xMLStreamWriter.writeAttribute("showAlternateVGridColor", "0");
			xMLStreamWriter.writeAttribute("showAlternateHGridColor", "0");
			xMLStreamWriter.writeAttribute("showAxisLines", "0");
			xMLStreamWriter.writeAttribute("showTickMarks", "0");
			xMLStreamWriter.writeAttribute("divLineIsDashed", "1");
			xMLStreamWriter.writeAttribute("divLineDashLen", "1");
			xMLStreamWriter.writeAttribute("divLineGapLen", "2");
			xMLStreamWriter.writeAttribute("legendShadow", "0");

			xMLStreamWriter.writeStartElement("categories");
			xMLStreamWriter.writeStartElement("category");
			xMLStreamWriter.writeAttribute("label", "EMR");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("category");
			xMLStreamWriter.writeAttribute("label", "FIN");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("category");
			xMLStreamWriter.writeAttribute("label", "AP");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("category");
			xMLStreamWriter.writeAttribute("label", "Payroll");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("category");
			xMLStreamWriter.writeAttribute("label", "CMS");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("dataset");
			xMLStreamWriter.writeAttribute("seriesname", "Critical");
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "15");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "10");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "12");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "5");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "3");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("dataset");
			xMLStreamWriter.writeAttribute("seriesname", "High");
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "16");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "8");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "10");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "6");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "4");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("dataset");
			xMLStreamWriter.writeAttribute("seriesname", "Medium");
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "17");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "18");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "10");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "4");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "6");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeStartElement("dataset");
			xMLStreamWriter.writeAttribute("seriesname", "Low");
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "25");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "20");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "8");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "6");
			xMLStreamWriter.writeEndElement();
			xMLStreamWriter.writeStartElement("set");
			xMLStreamWriter.writeAttribute("value", "5");
			xMLStreamWriter.writeEndElement();
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

		vulnerabilitiesByBusinessProcessChart = new FusionCharts(
				"stackedbar2d",// chartType
				"vulnerabilitiesByBusinessProcessChartID",// chartId
				"100%","100%",// chartWidth, chartHeight
				"vulnerabilitiesByBusinessProcessChartContainer",// chartContainer
				"xml",// dataFormat
				chartData
		);
	    data = vulnerabilitiesByBusinessProcessChart.render();
	}
		
	public String getVulnerabilitiesByBusinessProcessChart() {
		return data;
	}

}

