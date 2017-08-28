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
				new BusinessProcess("Airlift Request",					"PHI","11",		"$388","$3.50","3.7","FLASH (Z)",		"11.9%"),
				new BusinessProcess("Flight Control Information",		"PHI","56",		"$388","$3.50","4.8","IMMEDIATE (O)",	"7.1%"),   
				new BusinessProcess("Human Remains Search and Recovery","PHI","9",		"$388","$3.50","6.3","ROUTINE (R)",		"6.7%"), 
				new BusinessProcess("Intelligence Report",				"PHI","1,408",	"$388","$3.50","7.2","IMMEDIATE (O)",	"15.1%"),
				new BusinessProcess("Logistics Resupply Request",		"PHI","519",	"$388","$3.50","9.3","IMMEDIATE (O)",	"26.4%"),
				new BusinessProcess("Mail Distribution Scheme Change",	"PHI","88",		"$388","$3.50","4.9","ROUTINE (R)",		"28.9%"),
				new BusinessProcess("Media Contact Report",				"PHI","52",		"$388","$3.50","3.3","ROUTINE (R)",		"37.4%"),
				new BusinessProcess("Message Correction",				"PII","1,157",	"$388","$0.75","7.7","ROUTINE (R)",		"5.8%"),
				new BusinessProcess("Meteorological-Fallout",			"PHI","15",		"$388","$3.50","9.0","ROUTINE (R)",		"8.4%"),
				new BusinessProcess("Military Postal Facility",			"PHI","114",	"$388","$3.50","1.4","ROUTINE (R)",		"11.2%"),
				new BusinessProcess("Personnel Status Report",			"PHI","2,209",	"$388","$3.50","5.3","ROUTINE (R)",		"53.6%"),
				new BusinessProcess("Operations Summary",				"PHI","221",	"$388","$3.50","8.7","ROUTINE (R)",		"16.4%"),
				new BusinessProcess("Radar Status Report",				"PHI","1,911",	"$388","$3.50","6.8","ROUTINE (R)",		"14.25%"),
				new BusinessProcess("Rear Area Security",				"PHI","2,048",	"$388","$3.50","3.9","ROUTINE (R)",		"15.4%"),
				new BusinessProcess("Chemical Downwind",				"PHI","3",		"$388","$3.50","9.2","FLASH (Z)",		"9.2%"),
				new BusinessProcess("Decontamination Request",			"PHI","5",		"$388","$3.50","1.1","IMMEDIATE (O)",	"40.4%"),
				new BusinessProcess("Electronic Warfare",				"PHI","4",		"$388","$3.50","1.2","IMMEDIATE (O)",	"32.3%"),
				new BusinessProcess("Explosive Ordinance",				"PHI","49",		"$388","$3.50","6.1","IMMEDIATE (O)",	"65.4%"),
				new BusinessProcess("Fire Support",						"PHI","14",		"$388","$3.50","6.5","IMMEDIATE (O)",	"14.9%"),   
				new BusinessProcess("Friendly Nuclear Strike",			"PHI","0",		"$388","$3.50","5.8","IMMEDIATE (O)",	"32.3%"),
				new BusinessProcess("Rehabilitation",					"PHI","0",		"$388","$3.50","8.2","ROUTINE (R)",		"5.5%"),   
				new BusinessProcess("Preliminary Technical Report",		"PHI","468",	"$388","$3.50","3.8","ROUTINE (R)",		"7.8%"),   
				new BusinessProcess("General Administrative",			"PHI","10,391",	"$388","$3.50","1.9","ROUTINE (R)",		"29.9%"),
				new BusinessProcess("Medical Spot Report",				"PHI","169",	"$388","$3.50","1.9","ROUTINE (R)",		"34.7%"),
				new BusinessProcess("Medical Evacuation",				"PII","17",		"$388","$0.75","2.7","IMMEDIATE (O)",	"19.6%"),
				new BusinessProcess("Reconnaissance Exploitation",		"PHI","11",		"$388","$3.50","3.8","FLASH (Z)",		"23.5%"),
				new BusinessProcess("Psychological Operations",			"PCI","16",		"$388","$1.50","5.9","IMMEDIATE (O)",	"39.2%"),
				new BusinessProcess("Decontamination Request",			"PII","4",		"$388","$3.50","5.7","PRIORITY (P)",	"11.8%"),
				new BusinessProcess("Route Report",						"PHI","71",		"$388","$3.50","7.3","ROUTINE (R)",		"13.1%"),
				new BusinessProcess("Facility Maintenance",				"PII","53",		"$388","$0.75","1.1","ROUTINE (R)",		"8.4%"),	
				new BusinessProcess("Message Correction", 				"PCI","7,087", 	"$388","$1.50", "4.8","ROUTINE (R)",	"18.3%"),
				new BusinessProcess("Patrol Report", 					"PHI","2,862", 	"$388","$3.50", "4.7","ROUTINE (R)",	"56.9%")
				);
	}
}
