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
				new BusinessProcess("Invasive Cardiology",	"PHI","160",	"$388","$3.50","3.7","$62,640.00","$11,967,910"),
				new BusinessProcess("Invasive Cardiology",	"PII","160",	"$388","$0.75","3.7","$62,200.00", "11,967,910"),
				new BusinessProcess("Open Heart Surgery",	"PHI","0",		"$388","$3.50","4.8", "","$0"),   
				new BusinessProcess("Dental",				"PHI","2",		"$388","$3.50","6.3", "$783.00","$6,728"), 
				new BusinessProcess("Dermatology",			"PHI","14",		"$388","$3.50","7.2", "$5,481.00","$151,304"),
				new BusinessProcess("Ear, Nose and Throat",	"PHI","215",	"$388","$3.50","9.3","$84,172.50","$264,509"),
				new BusinessProcess("General Medicine",		"PHI","388",	"$388","$3.50","4.9","$151,902.00","$28,949,405"),
				new BusinessProcess("General Surgery",		"PHI","502",	"$388","$3.50","3.3","$196,533.00","$37,440,152"),
				new BusinessProcess("Accounts Payable",		"PII","8,157",	"$388","$0.75","7.7","$3,171,033.75","$0"),
				new BusinessProcess("Accounts Payable",		"PCI","6,088",	"$388","$1.50","7.7","$2,371,276.00","$0"),
				new BusinessProcess("Gynecology",			"PHI","15",		"$388","$3.50","9","$5,872.50","$8,490,747"),
				new BusinessProcess("Hematology",			"PHI","114",	"$388","$3.50","1.4","$44,631.00","$11,204,333"),
				new BusinessProcess("Infectious Diseases",	"PHI","720",	"$388","$3.50","5.3","$281,880.00","$53,693,869"),
				new BusinessProcess("Neonatology",			"PHI","221",	"$388","$3.50","8.7","$86,521.50","$16,496,309"),
				new BusinessProcess("Normal Newborns",		"PHI","1,911",	"$388","$3.50","6.8","$748,156.50","$142,563,691"),
				new BusinessProcess("Obstetrics/Delivery",	"PHI","2,048",	"$388","$3.50","3.9","$801,792.00","$154,693,330"),
				new BusinessProcess("Oncology",				"PHI","125",	"$388","$3.50","9.2","$48,937.50","$9,299,389"),
				new BusinessProcess("Oncology Surgery",		"PHI","5",		"$388","$3.50","1.1","$1,957.50","$404,297"),
				new BusinessProcess("Opthamology",			"PHI","4",		"$388","$3.50","1.2","$1,566.00","$323,437"),
				new BusinessProcess("Orthopedic Surgery",	"PHI","490",	"$388","$3.50","6.1","$191,835.00","$36,548,453"),
				new BusinessProcess("Plastic Surgery",		"PHI","0",		"$388","$3.50","6.5", "","$0"),   
				new BusinessProcess("Psychiatry",			"PHI","43",		"$388","$3.50","5.8","$16,384.50","$3,234,376"),
				new BusinessProcess("Rehabilitation",		"PHI","0",		"$388","$3.50","8.2", "","$0"),   
				new BusinessProcess("Transplant Surgery",	"PHI","0",		"$388","$3.50","3.8", "","$0"),   
				new BusinessProcess("Trauma, Multiple",		"PHI","40",		"$388","$3.50","1.9","$15,660.00","$2,991,798"),
				new BusinessProcess("Urology",				"PHI","169",	"$388","$3.50","1.9","$66,163.50","$3,409,009"),
				new BusinessProcess("Admissions",			"PII","71,391",	"$388","$0.75","2.7","$27,753,251.25","$0"),
				new BusinessProcess("Vascular Surgery",		"PHI","101",	"$388","$3.50","3.8","$39,541.50","2,354,444"),
				new BusinessProcess("Admissions",			"PCI","11,687",	"$388","$1.50","5.9","$4,552,086.50","$0"),
				new BusinessProcess("Payroll",				"PII","451",	"$388","$3.50","5.7","$175,326.25","$0"),
				new BusinessProcess("Emergency Room",		"PHI","71,391",	"$388","$3.50","7.3","$27,949,576.50","$131,800,839"),
				new BusinessProcess("Facility Maintenance",	"PII","53",		"$388","$0.75","1.1","$20,603.75","$0"),	
				new BusinessProcess("Finance", 				"PII","15,000", "$388","$0.75", "4.8", "$10,496,250.00","$0"),
				new BusinessProcess("Finance", 				"PCI","27,000", "$388", "$1.50", "4.8", "$10,906,000.00","$0"),
				new BusinessProcess("Lab1", 				"PHI","28,000", "$388", "$3.50", "4.7", "$10,962,000.00","$5,698,145"),
				new BusinessProcess("Lab1", 				"PII","36,000", "$388", "$0.75", "4.7", "$13,995,000.00","$5,698,145")
				);
	}
}
