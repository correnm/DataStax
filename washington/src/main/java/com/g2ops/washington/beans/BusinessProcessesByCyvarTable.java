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
				new BusinessProcess("Invasive Cardiology",	"PHI","160",	"$402","$0.94","3.7"," $64,470.40"),
				new BusinessProcess("Invasive Cardiology",	"PII","160",	"$402","$0.94","3.7"," $64,470.40"),
				new BusinessProcess("Open Heart Surgery",	"PHI","0",		"$402","$0.94","4.8", ""),   
				new BusinessProcess("Dental",				"PHI","2",		"$402","$0.94","6.3", "$805.88"), 
				new BusinessProcess("Dermatology",			"PHI","14",		"$402","$0.94","7.2", "$5,641.16"),
				new BusinessProcess("Ear, Nose and Throat",	"PHI","215",	"$402","$0.94","9.3","$86,632.10"),
				new BusinessProcess("General Medicine",		"PHI","388",	"$402","$0.94","4.9","$156,340.72"),
				new BusinessProcess("General Surgery",		"PHI","502",	"$402","$0.94","3.3","$202,275.88"),
				new BusinessProcess("Accounts Payable",		"PII","8,157",	"$402","$0.94","7.7","$3,286,781.58"),
				new BusinessProcess("Accounts Payable",		"PCI","6,088",	"$402","$0.94","2.3","$2,453,098.72"),
				new BusinessProcess("Gynecology",			"PHI","15",		"$402","$0.94","9","$6,044.10"),
				new BusinessProcess("Hematology",			"PHI","114",	"$402","$0.94","1.4","$45,935.16"),
				new BusinessProcess("Infectious Diseases",	"PHI","720",	"$402","$0.94","5.3","$290,116.80"),
				new BusinessProcess("Neonatology",			"PHI","221",	"$402","$0.94","8.7","$89,049.74"),
				new BusinessProcess("Normal Newborns",		"PHI","1,911",	"$402","$0.94","6.8","$770,018.34"),
				new BusinessProcess("Obstetrics/Delivery",	"PHI","2,048",	"$402","$0.94","3.9","$825,221.12"),
				new BusinessProcess("Oncology",				"PHI","125",	"$402","$0.94","9.2","$50,367.50"),
				new BusinessProcess("Oncology Surgery",		"PHI","5",		"$402","$0.94","1.1","$2,014.70"),
				new BusinessProcess("Opthamology",			"PHI","4",		"$402","$0.94","1.2","$1,611.76"),
				new BusinessProcess("Orthopedic Surgery",	"PHI","490",	"$402","$0.94","6.1","$197,440.60"),
				new BusinessProcess("Plastic Surgery",		"PHI","0",		"$402","$0.94","6.5", ""),   
				new BusinessProcess("Psychiatry",			"PHI","43",		"$402","$0.94","5.8","$17,326.42"),
				new BusinessProcess("Rehabilitation",		"PHI","0",		"$402","$0.94","8.2", ""),   
				new BusinessProcess("Transplant Surgery",	"PHI","0",		"$402","$0.94","3.8", ""),   
				new BusinessProcess("Trauma, Multiple",		"PHI","40",		"$402","$0.94","1.9","$16,117.60"),
				new BusinessProcess("Urology",				"PHI","169",	"$402","$0.94","1.9","$68,096.86"),
				new BusinessProcess("Admissions",			"PII","71,391",	"$402","$0.94","2.7","$28,766,289.54"),
				new BusinessProcess("Vascular Surgery",		"PHI","101",	"$402","$0.94","3.8","$40,696.94"),
				new BusinessProcess("Admissions",			"PCI","11,687",	"$402","$0.94","5.9","$4,709,159.78"),
				new BusinessProcess("Payroll",				"PII","451",	"$402","$0.94","5.7","$181,725.94"),
				new BusinessProcess("Emergency Room",		"PHI","71,391",	"$402","$0.94","7.3","$28,766,289.54"),
				new BusinessProcess("Facility Maintenance",	"PII","53",		"$402","$0.94","1.1","$21,355.82"),	
				new BusinessProcess("EMR", 					"PHI","71,391", "$209", "$58", "8.2", "$19,061,397"),
				new BusinessProcess("Finance", 				"PII","15,000", "$197", "$12", "6.1", "$3,313,000"),
				new BusinessProcess("Finance", 				"PCI","27,000", "$148", "$6", "6.1", "$4,158,000"),
				new BusinessProcess("LAB1", 				"PHI","28,000", "$209", "$58", "5.4", "$19,800,000"),
				new BusinessProcess("LAB1", 				"PII","36,000", "$197", "$12", "5.4", "$7,524,000")
				);
	}
}
