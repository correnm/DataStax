package com.g2ops.impact.urm.types;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.g2ops.impact.urm.types.SecurityControlRatings;



/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 10-May-2018		tammy.bogart		created
 * 
 */

/**
 * @author TammyBogart
 *
 */
public class SecurityControls implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ID, question, secText;
	private String category, description;
	private LinkedHashMap<String,String> answers = new LinkedHashMap<String, String>();
	private LinkedHashMap<String, String> mapAnswers = new LinkedHashMap<String, String>();
 
	private List<SecurityControlRatings>lstSCRatings;
	//	private Map<String,List<SecurityControlRatings>> IDSecRatings = new HashMap<String, List<SecurityControlRatings>>();

	public SecurityControls (String questionID, List<SecurityControlRatings>lstRatings, String secContext) {
		this.ID = questionID;
		this.lstSCRatings = lstRatings;
		this.question =secContext;
		this.answers = getAnswers(questionID, lstRatings);		
		this.secText = secContext;
	}

	
	public String getQuestion(String questionID) {
		String question = " ";
		switch (questionID) {
			case "ACX": 
				question = "What is your level of access control or system authorization?";				
				break;
			case "BCM":
				question = "What is the posture of your Business Continuity Management?";
				break;
			case "CLD":
				question = "What percentage of restricted data do you have stored in the cloud?";
				break;
			case "CLS":
				question = "Do you use Data Classification?";
				break;
			case "CPM":
				question = "How do you implement Configuration Management (patching)?";
				break;
			case "CPO":
				question = "Chief Privacy Officer  ?";
				break;
			case "CSO":
				question = "Chief Information Security Officer / Chief Security Officer  ?";
				break;
			case "DLP":
				question = "Data Loss Prevention (DLP)  ?";
				break;
			case "DOS":
				question = "DDoS Defense  ?";
				break;
			case "ECR":
				question = "Encryption/privacy/data protection ?";
				break;
			case "EMP":
				question = "Endpoint Malware Protection  ?";
				break;
			case "EMS":
				question = "Email/messaging Security  ?";
				break;
			case "EXS":
				question = "Executive Sponsorship  ?";
				break;
			case "FW1":
				question = "Firewall; Security Architecture  ?";
				break;
			case "IAM":
				question = "Identity & Access Management (IAM)  ?";
				break;
			case "IDS":
				question = "Intrusion Detection / Prevention  ?";
				break;
			case "INS":
				question = "Cyber Insurance Policy  ?";
				break;
			case "IRT":
				question = "Incident Response Team  ?";
				break;
			case "MFA":
				question = "Multifactor Authentication (MFA)  ?";
				break;
			case "MOB":
				question = "Mobile Security  ?";
				break;
			case "PNT":
				question = "Penetration Testing  ?";
				break;
			case "SAT":
				question = "Security and Awareness Training  ?";
				break;
			case "SEG":
				question = "Segmentation / Security Zones  ?";
				break;
			case "SIM":
				question = "Security Information & Event Management (SIEM)  ?";
				break;
			case "STF":
				question = "InfoSec Staff  ?";
				break;
			case "TIP":
				question = "Threat Intelligence Consumption / Sharing  ?";
				break;
			case "VLN":
				question = "Vulnerability Scanning  ?";
				break;
			case "VPN":
				question = "Virtual Private Networking (VPN)  ?";
				break;
			case "WCF":
				question = "Web Content Filtering  ?";
				break;
			case "WLS":
				question = "Wireless Security Posture  ?";
				break;
		}
		return question;
	}

	public LinkedHashMap<String, String> getAnswers(String questionID, List<SecurityControlRatings>lstRatings) {			
		Iterator<SecurityControlRatings> it = lstRatings.iterator();
		SecurityControlRatings sec;	
		switch (questionID) {
		case "ACX": 
			//VL = none / L = Everyone enabled / M = RBAC, not federated / H = RBAC federated / VH = Strong Governance with matching implementation
			while(it.hasNext()) {
				sec = it.next();
					mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "BCM":
			//VL:None	L:Work in Progress	M:Plan in place, not tested	H:Plan in place, tested only once	VH:Plan in place, regularly tested/validated
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "CLD":
			//VL:>50%	L:<50%	M:<25%	h:<10%	VH:0
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "CLS":
			//VL:none	L:internal/external tags only	M:formal policy	H:Document Management System; formal definitions for data classes	VH:Strong governance with matching implementation
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "CPM":
			//VL:repeatable	L:documented (excel)	M:Change Mgmt Process	H:CCB	VH:Strong governance with matching implementation
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "CPO":
			//VL:none	L:Advisory Services Contractor (PT)	M:Advisory Services Contractor (FT)	H:FTE	VH:FTE Report to CEO/BOD			
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "CSO":
			//VL:			
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "DLP":
			//VL:			
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "DOS":
			//VL:
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "ECR":
			//VL:
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "EMP":
			//VL:
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "EMS":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "EXS":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "FW1":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "IAM":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "IDS":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "INS":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "IRT":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "MFA":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "MOB":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "PNT":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "SAT":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "SEG":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "SIM":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "STF":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "TIP":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "VLN":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "VPN":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "WCF":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		case "WLS":
			while(it.hasNext()) {
				sec = it.next();
				mapAnswers.put(sec.getCategory(),sec.getDescription());		
			}
			break;
		}  	//end of while		
		return mapAnswers;
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>GETTERS/SETTERS<<<<<<<<<<<<<<<<<<<<<<<<//
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public LinkedHashMap<String, String> getAnswers() {
		return answers;
	}

	public void setAnswers(LinkedHashMap<String, String> answers) {
		this.answers = answers;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecText() {
		return secText;
	}


	public void setSecText(String secText) {
		this.secText = secText;
	}


	public List<SecurityControlRatings> getLstSCRatings() {
		return lstSCRatings;
	}


	public void setLstSCRatings(List<SecurityControlRatings> lstSCRatings) {
		this.lstSCRatings = lstSCRatings;
	}
	
	
}
