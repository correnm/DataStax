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

import com.g2ops.impact.urm.types.BusinessProcess;
import com.g2ops.impact.urm.utils.BusinessProcessesByCyvar;

@Named("businessProcessesByCyvarTable")
@RequestScoped
public class BusinessProcessesByCyvarTable extends BusinessProcessesByCyvar {

	private static final long serialVersionUID = 1L;

	public BusinessProcessesByCyvarTable () {
		super("Business Process CyVaR Details",
			new BusinessProcess("Accounts Payable",		"PCI, PII",	"14,245",	"$0",			"7.7",	"$1,828,962",	"$8,766,386",	"$5,265,194.26",	"0.95", "$5,542,309"),
			new BusinessProcess("Admissions",			"PCI, PII",	"83,078",	"$0",			"2.7",	"$10,660,761",	"$37,782,590",	"$30,690,070.86",	"0.95",	"$32,305,337"),
			new BusinessProcess("Dental",				"PHI",		"2",		"$6,728",		"6.3",	"$258", 		"$973",			"$743.85",			"0.95",	"$783"),
			new BusinessProcess("Dermatology",			"PHI",		"14",		"$151,304",		"7.2",	"$1,809",		"$6,714",		"$4,055.94",		"0.74",	"$5,481"),
			new BusinessProcess("Ear, Nose and Throat",	"PHI",		"215",		"$264,509",		"9.3",	"$26,935",		"$90,403",		"$72,388.35",		"0.86",	"$84,172"),
			new BusinessProcess("Emergency Room",		"PHI",		"71,391",	"$131,800,839",	"7.3",	"$5,310,420",	"$29,036,636",	"$27,390,584.97",	"0.98",	"$27,949,576"),
			new BusinessProcess("Facility Maintenance",	"PII",		"53",		"$0",			"1.1",	"$9,684",		"$27,719",		"$11,744.14",		"0.57",	"$20,603"),
			new BusinessProcess("Finance", 				"PII",		"42,000",	"$0",			"4.8",	"$7,062,743",	"$28,405,935",	"$19,690,070.00",	"0.92",	"$21,402,250"),
			new BusinessProcess("General Medicine",		"PHI",		"388",		"$28,949,405",	"4.9",	"$50,128",		"$166,965",		"$132,154.74",		"0.87",	"$151,902"),
			new BusinessProcess("General Surgery",		"PHI",		"502",		"$37,440,152",	"3.3",	"$64,856",		"$199,018",		"$188,671.68",		"0.96",	"$196,533"),
			new BusinessProcess("Gynecology",			"PHI",		"15",		"$8,490,747",	"9.0",	"$1,938",		"$7,050",		"$5,343.98",		"0.91",	"$5,872"),
			new BusinessProcess("Hematology",			"PHI",		"114",		"$11,204,333",	"1.4",	"$20,977",		"$48,383",		"$39,721.59",		"0.89",	"$44,631"),
			new BusinessProcess("Infectious Diseases",	"PHI",		"720",		"$53,693,869",	"5.3",	"$95,839",		"$292,417",		"$250,873.20",		"0.89",	"$281,880"),
			new BusinessProcess("Invasive Cardiology",	"PHI, PII",	"320",		"$11,967,910",	"3.7",	"$41,197",		"$137,362",		"$113,604.40",		"0.91",	"$124,840"),
			new BusinessProcess("Lab1", 				"PHI",		"64,000",	"$5,698,145",	"4.7",	"$7,986,240",	"$29,216,890",	"$24,208,290.00",	"0.97",	"$24,957,000"),
			new BusinessProcess("Neonatology",			"PHI",		"221",		"$16,496,309",	"8.7",	"$27,687",		"$96,622",		"$85,656.29",		"0.99",	"$86,521"),
			new BusinessProcess("Normal Newborns",		"PHI",		"1,911",	"$142,563,691",	"6.8",	"$142,150",		"$843,415",		"$740,674.94",		"0.99",	"$748,156"),
			new BusinessProcess("Obstetrics/Delivery",	"PHI",		"2,048",	"$154,693,330",	"3.9",	"$152,340",		"$917,380",		"$793,774.08",		"0.99",	"$801,792"),
			new BusinessProcess("Oncology",				"PHI",		"125",		"$9,299,389",	"9.2",	"$16,639",		"$57,682",		"$43,065.00",		"0.88",	"$48,937"),
			new BusinessProcess("Oncology Surgery",		"PHI",		"5",		"$404,297",		"1.1",	"$666",			"$3,683",		"$1,937.93",		"0.99",	"$1,957"),
			new BusinessProcess("Open Heart Surgery",	"PHI",		"0",		"$0",			"4.8",	"$0",			"$0",			"$0.00",			"0.99",	"$0"),
			new BusinessProcess("Opthamology",			"PHI",		"4",		"$323,437",		"1.2",	"$501",			"$1,908",		"$1,315.44",		"0.84",	"$1,566"),
			new BusinessProcess("Orthopedic Surgery",	"PHI",		"490",		"$36,548,453",	"6.1",	"$36,449",		"$197,713",		"$164,978.10",		"0.86",	"$191,835"),
			new BusinessProcess("Payroll",				"PII",		"451",		"$0",			"5.7",	"$57,858",		"$180,781",		"$142,014.26",		"0.81",	"$175,326"),
			new BusinessProcess("Plastic Surgery",		"PHI",		"0",		"$0",			"6.5", 	"$0",			"$0",			"$0.00",			"0.81",	"$0"),
			new BusinessProcess("Psychiatry",			"PHI",		"43",		"$3,234,376",	"5.8",	"$7,865",		"$19,503",		"$13,926.83",		"0.85",	"$16,384"),
			new BusinessProcess("Rehabilitation",		"PHI",		"0",		"$0",			"8.2",	"$0",			"$0",			"$0.00",			"0.84",	"$0"),
			new BusinessProcess("Transplant Surgery",	"PHI",		"0",		"$0",			"3.8",	"$0",			"$0",			"$0.00",			"0.84",	"$0"),
			new BusinessProcess("Trauma, Multiple",		"PHI",		"40",		"$2,991,798",	"1.9",	"$2,975",		"$17,468",		"$15,503.40",		"0.99",	"$15,660"),
			new BusinessProcess("Urology",				"PHI",		"169",		"$3,409,009",	"1.9",	"$21,834",		"$78,375",		"$58,885.52",		"0.89",	"$66,163"),
			new BusinessProcess("Vascular Surgery",		"PHI",		"101",		"$2,354,444",	"3.8",	"$7,513",		"$40,447",		"$36,773.60",		"0.93",	"$39,541")
		);
	}
}
