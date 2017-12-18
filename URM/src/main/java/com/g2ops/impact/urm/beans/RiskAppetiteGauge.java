package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, August 2017
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

@Named("riskAppetiteGauge")
@RequestScoped
public class RiskAppetiteGauge {

	private String data;
	private String chartData;
	private FusionCharts riskAppetiteGauge;

	public RiskAppetiteGauge() {
		
		try {
			
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			
			xMLStreamWriter.writeStartElement("chart");

				xMLStreamWriter.writeAttribute("theme", "g2ops");
				xMLStreamWriter.writeAttribute("caption", "Current Cyber Value-at-Risk (CyVaR)");
				xMLStreamWriter.writeAttribute("subcaption", "$110,162,017");
				xMLStreamWriter.writeAttribute("lowerLimit", "0");
				xMLStreamWriter.writeAttribute("upperLimit", "150000000");
				xMLStreamWriter.writeAttribute("gaugeOuterRadius", "100");
				xMLStreamWriter.writeAttribute("gaugeInnerRadius", "40%");
				xMLStreamWriter.writeAttribute("gaugeFillMix", "");

				xMLStreamWriter.writeStartElement("colorrange");
					xMLStreamWriter.writeStartElement("color");
						xMLStreamWriter.writeAttribute("minvalue", "0");
						xMLStreamWriter.writeAttribute("maxvalue", "60000000");
						xMLStreamWriter.writeAttribute("code", "#38b44a");
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("color");
						xMLStreamWriter.writeAttribute("minvalue", "60000000");
						xMLStreamWriter.writeAttribute("maxvalue", "105000000");
						xMLStreamWriter.writeAttribute("code", "#efb73e");
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("color");
						xMLStreamWriter.writeAttribute("minvalue", "105000000");
						xMLStreamWriter.writeAttribute("maxvalue", "135000000");
						xMLStreamWriter.writeAttribute("code", "#e95420");
					xMLStreamWriter.writeEndElement();
					xMLStreamWriter.writeStartElement("color");
						xMLStreamWriter.writeAttribute("minvalue", "135000000");
						xMLStreamWriter.writeAttribute("maxvalue", "150000000");
						xMLStreamWriter.writeAttribute("code", "#df382c");
					xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeEndElement();

				xMLStreamWriter.writeStartElement("dials");
					xMLStreamWriter.writeStartElement("dial");
						xMLStreamWriter.writeAttribute("value", "110162017.32");
						xMLStreamWriter.writeAttribute("bgColor", "#28637e");
						xMLStreamWriter.writeAttribute("borderThickness", "0");
						xMLStreamWriter.writeAttribute("radius", "80");
					xMLStreamWriter.writeEndElement();
				xMLStreamWriter.writeEndElement();

			xMLStreamWriter.writeEndElement();

			xMLStreamWriter.flush();
			xMLStreamWriter.close();
			
			chartData = stringWriter.getBuffer().toString();
			
			stringWriter.close();
			
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		riskAppetiteGauge = new FusionCharts(
                "angulargauge",// chartType
                "riskAppetiteGaugeID",// chartId
                "100%","100%",// chartWidth, chartHeight
                "riskAppetiteGaugeContainer",// chartContainer
                "xml",// dataFormat
                chartData
		);
    	data = riskAppetiteGauge.render();
	}
	
    public String getRiskAppetiteGauge() {
    	return data;
    }

}
