package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.BusinessProcess;
import com.g2ops.washington.utils.BusinessProcessesByCyvar;

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
 * 07-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 
 */
@ManagedBean
@SessionScoped
public class BusinessProcessesByCyvarTable extends BusinessProcessesByCyvar {
	private static final long serialVersionUID = 1L;
	public BusinessProcessesByCyvarTable () {
		super("Business Process CyVaR Details",
				new BusinessProcess("Airlift Request",	"PHI","160",	"$388","$3.50","3.7","FLASH","11.9%"),
				new BusinessProcess("Flight Control Information",	"PHI","56",		"$388","$3.5","4.8", "IMMEDIATE","7.1%"),   
				new BusinessProcess("Human Remains Search and Recovery",				"PHI","2",		"$388","$3.50","6.3", "ROUTINE","6.7%"), 
				new BusinessProcess("Intelligence Report",			"PHI","140,308",		"$388","$3.50","7.2", "ROUTINE","15.1%"),
				new BusinessProcess("Logistics Resupply Request",	"PHI","21,585",	"$388","$3.50","9.3","IMMEDIATE","26.4%"),
				new BusinessProcess("Mail Distribution Scheme Change",		"PHI","388",	"$388","$3.50","4.9","ROUTINE","28.9%"),
				new BusinessProcess("Media Contact Report",		"PHI","502",	"$388","$3.50","3.3","ROUTINE","37.4%"),
				new BusinessProcess("Message Correction",		"PII","8,157",	"$388","$0.75","7.7","ROUTINE","5.8%"),
				new BusinessProcess("Meteorological-Fallout",			"PHI","15",		"$388","$3.50","9","ROUTINE","8.4%"),
				new BusinessProcess("Military Postal Facility",			"PHI","114",	"$388","$3.50","1.4","ROUTINE","11.2%"),
				new BusinessProcess("Personnel Status Report",	"PHI","7,209",	"$388","$3.50","5.3","ROUTINE","53.6%"),
				new BusinessProcess("Operations Summary",			"PHI","221",	"$388","$3.50","8.7","ROUTINE","16.4%"),
				new BusinessProcess("Radar Status Report",		"PHI","1,911",	"$388","$3.50","6.8","ROUTINE","14.25%"),
				new BusinessProcess("Rear Area Security",	"PHI","2,048",	"$388","$3.50","3.9","ROUTINE","15.4%"),
				new BusinessProcess("Chemical Downwind",				"PHI","125",	"$388","$3.50","9.2","FLASH","9.2%"),
				new BusinessProcess("Decontamination Request",		"PHI","5",		"$388","$3.50","1.1","ROUTINE","40.4%"),
				new BusinessProcess("Electronic Warfare",			"PHI","4",		"$388","$3.50","1.2","ROUTINE","32.3%"),
				new BusinessProcess("Explosive Ordinance",	"PHI","490",	"$388","$3.50","6.1","FLASH","65.4%"),
				new BusinessProcess("Fire Support",		"PHI","874",		"$388","$3.50","6.5", "FLASH","14.9%"),   
				new BusinessProcess("Friendly Nuclear Strike",			"PHI","0",		"$388","$3.50","5.8","FLASH","32.3%"),
				new BusinessProcess("Rehabilitation",		"PHI","0",		"$388","$3.50","8.2", "ROUTINE","5.5%"),   
				new BusinessProcess("Preliminary Technical Report",	"PHI","0",		"$388","$3.50","3.8", "ROUTINE","7.8%"),   
				new BusinessProcess("General Administrative",		"PHI","71,391",		"$388","$3.50","1.9","ROUTINE","29.9%"),
				new BusinessProcess("Medical Spot Report",				"PHI","169",	"$388","$3.50","1.9","ROUTINE","34.7%"),
				new BusinessProcess("Medical Evacuation",			"PII","17",	"$388","$0.75","2.7","FLASH","19.6%"),
				new BusinessProcess("Reconnaissance Exploitation",		"PHI","101",	"$388","$3.50","3.8","FLASH","23.5%"),
				new BusinessProcess("Psychological Operations",			"PCI","116",	"$388","$1.50","5.9","IMMEDIATE","39.2%"),
				new BusinessProcess("Decontamination Request",				"PII","45",	"$388","$3.50","5.7","PRIORITY","11.8%"),
				new BusinessProcess("Route Report",		"PHI","71",	"$388","$3.50","7.3","ROUTINE","13.1%"),
				new BusinessProcess("Facility Maintenance",	"PII","53",		"$388","$0.75","1.1","ROUTINE","8.4%"),	
				new BusinessProcess("Message Correction", 	"PCI","27,087", "$388", "$1.50", "4.8", "ROUTINE","18.3%"),
				new BusinessProcess("Patrol Report", 		"PHI","28,642", "$388", "$3.50", "4.7", "ROUTINE","56.9%")
				);
	}
}
