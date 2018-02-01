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
			new BusinessProcess("Accounts Payable",		"PCI, PII",	"14,245",	"$0",			"7.7",	"$1,828,962",	"$4,766,386",	"$5,265,194.26",	"0.95", "$5,542,309.75"),
			new BusinessProcess("Admissions",			"PCI, PII",	"83,078",	"$0",			"2.7",	"$10,660,761",	"$27,782,590",	"$30,690,070.86",	"0.95",	"$32,305,337.75"),
			new BusinessProcess("Dental",				"PHI",		"2",		"$6,728",		"6.3",	"$258", 		"$673",			"$743.85",			"0.95",	"$783.00"),
			new BusinessProcess("Dermatology",			"PHI",		"14",		"$151,304",		"7.2",	"$1,809",		"$4,714",		"$4,055.94",		"0.74",	"$5,481.00"),
			new BusinessProcess("Ear, Nose and Throat",	"PHI",		"215",		"$264,509",		"9.3",	"$26,935",		"$40,403",		"$72,388.35",		"0.86",	"$84,172.50"),
			new BusinessProcess("Emergency Room",		"PHI",		"71,391",	"$131,800,839",	"7.3",	"$5,310,420",	"$24,036,636",	"$27,390,584.97",	"0.98",	"$27,949,576.50"),
			new BusinessProcess("Facility Maintenance",	"PII",		"53",		"$0",			"1.1",	"$9,684",		"$17,719",		"$11,744.14",		"0.57",	"$20,603.75"),
			new BusinessProcess("Finance", 				"PII",		"42,000",	"$0",			"4.8",	"$7,062,743",	"$18,405,935",	"$19,690,070.00",	"0.92",	"$21,402,250.00"),
			new BusinessProcess("General Medicine",		"PHI",		"388",		"$28,949,405",	"4.9",	"$50,128",		"$116,965",		"$132,154.74",		"0.87",	"$151,902.00"),
			new BusinessProcess("General Surgery",		"PHI",		"502",		"$37,440,152",	"3.3",	"$64,856",		"$169,018",		"$188,671.68",		"0.96",	"$196,533.00"),
			new BusinessProcess("Gynecology",			"PHI",		"15",		"$8,490,747",	"9.0",	"$1,938",		"$5,050",		"$5,343.98",		"0.91",	"$5,872.50"),
			new BusinessProcess("Hematology",			"PHI",		"114",		"$11,204,333",	"1.4",	"$20,977",		"$38,383",		"$39,721.59",		"0.89",	"$44,631.00"),
			new BusinessProcess("Infectious Diseases",	"PHI",		"720",		"$53,693,869",	"5.3",	"$95,839",		"$242,417",		"$250,873.20",		"0.89",	"$281,880.00"),
			new BusinessProcess("Invasive Cardiology",	"PHI, PII",	"320",		"$11,967,910",	"3.7",	"$41,197",		"$107,362",		"$113,604.40",		"0.91",	"$124,840.00"),
			new BusinessProcess("Lab1", 				"PHI",		"64,000",	"$5,698,145",	"4.7",	"$7,986,240",	"$19,216,890",	"$24,208,290.00",	"0.97",	"$24,957,000.00"),
			new BusinessProcess("Neonatology",			"PHI",		"221",		"$16,496,309",	"8.7",	"$27,687",		"$66,622",		"$85,656.29",		"0.99",	"$86,521.50"),
			new BusinessProcess("Normal Newborns",		"PHI",		"1,911",	"$142,563,691",	"6.8",	"$142,150",		"$643,415",		"$740,674.94",		"0.99",	"$748,156.50"),
			new BusinessProcess("Obstetrics/Delivery",	"PHI",		"2,048",	"$154,693,330",	"3.9",	"$152,340",		"$617,380",		"$793,774.08",		"0.99",	"$801,792.00"),
			new BusinessProcess("Oncology",				"PHI",		"125",		"$9,299,389",	"9.2",	"$16,639",		"$37,682",		"$43,065.00",		"0.88",	"$48,937.50"),
			new BusinessProcess("Oncology Surgery",		"PHI",		"5",		"$404,297",		"1.1",	"$666",			"$1,683",		"$1,937.93",		"0.99",	"$1,957.50"),
			new BusinessProcess("Open Heart Surgery",	"PHI",		"0",		"$0",			"4.8",	"$0",			"$0",			"$0.00",			"0.99",	"$0.00"),
			new BusinessProcess("Opthamology",			"PHI",		"4",		"$323,437",		"1.2",	"$501",			"$908",			"$1,315.44",		"0.84",	"$1,566.00"),
			new BusinessProcess("Orthopedic Surgery",	"PHI",		"490",		"$36,548,453",	"6.1",	"$36,449",		"$147,713",		"$164,978.10",		"0.86",	"$191,835.00"),
			new BusinessProcess("Payroll",				"PII",		"451",		"$0",			"5.7",	"$57,858",		"$150,781",		"$142,014.26",		"0.81",	"$175,326.25"),
			new BusinessProcess("Plastic Surgery",		"PHI",		"0",		"$0",			"6.5", 	"$0",			"$0",			"$0.00",			"0.81",	"$0.00"),
			new BusinessProcess("Psychiatry",			"PHI",		"43",		"$3,234,376",	"5.8",	"$7,865",		"$9,503",		"$13,926.83",		"0.85",	"$16,384.50"),
			new BusinessProcess("Rehabilitation",		"PHI",		"0",		"$0",			"8.2",	"$0",			"$0",			"$0.00",			"0.84",	"$0.00"),
			new BusinessProcess("Transplant Surgery",	"PHI",		"0",		"$0",			"3.8",	"$0",			"$0",			"$0.00",			"0.84",	"$0.00"),
			new BusinessProcess("Trauma, Multiple",		"PHI",		"40",		"$2,991,798",	"1.9",	"$2,975",		"$13,468",		"$15,503.40",		"0.99",	"$15,660.00"),
			new BusinessProcess("Urology",				"PHI",		"169",		"$3,409,009",	"1.9",	"$21,834",		"$38,375",		"$58,885.52",		"0.89",	"$66,163.50"),
			new BusinessProcess("Vascular Surgery",		"PHI",		"101",		"$2,354,444",	"3.8",	"$7,513",		"$30,447",		"$36,773.60",		"0.93",	"$39,541.50")
		);
	}
}
