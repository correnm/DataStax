package com.g2ops.impact.urm.beans;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, June 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 6.20.2018		tammy.bogart		created 
 */
 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DecimalFormat;
//import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
import java.util.Spliterator;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.MappingManager;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.g2ops.impact.urm.types.SecurityControlsQA;
import com.g2ops.impact.urm.types.AuditUpsert;
import com.g2ops.impact.urm.types.SecurityControlRatings;
@Named("securityControlBeanBusiness")
@ManagedBean
@SessionScoped	//required for pulling in currentUser info --Client-specific data      


public class SecurityControlBeanBusiness  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;
	
	static AuditUpsert auditUpsert = new AuditUpsert();
	
	private Session session;
	private ResultSet resultSet; 
	private Iterator<Row> iterator;
	private String query, userSecGroup, userCountry, securityControlText, impactCosts, siteName, businessName;
	private double rsValue = 0;
	private String sesQAList = "";
	private PreparedStatement prepared, preparedIC, preparedSC;
	private BoundStatement bound, boundIC, boundSC;
	private Row row;
	private UUID siteID, businessID;

	private String qACX, qBCM, qCLD, qCLS, qCPM, qCPO, qCSO, qDLP, qDOS, qECR, qEMP, qEMS, qEXS, qFW1, qIAM, qIDS, qINS, qIRT, qMFA, qMOB, qPNT, qSAT, qSEG, qSIM, qSTF, qTIP, qVLN, qVPN, qWCF, qWLS;
	private String selectedaACX, selectedaBCM, selectedaCLD, selectedaCLS, selectedaCPM, selectedaCPO, selectedaCSO, selectedaDLP, selectedaDOS, selectedaECR, selectedaEMP, selectedaEMS, selectedaEXS, 
				selectedaFW1, selectedaIAM, selectedaIDS, selectedaINS, selectedaIRT, selectedaMFA, selectedaMOB, selectedaPNT, selectedaSAT, selectedaSEG, selectedaSIM, 
				selectedaSTF, selectedaTIP, selectedaVLN, selectedaVPN, selectedaWCF, selectedaWLS;
	private Map<String,String> answersACX, answersBCM, answersCLD, answersCLS, answersCPM, answersCPO, answersCSO, answersDLP, answersDOS, answersECR, answersEMP, answersEMS, answersEXS, answersFW1, 
	answersIAM, answersIDS, answersINS, answersIRT, answersMFA, answersMOB, answersPNT, answersSAT, answersSEG, answersSIM, answersSTF, answersTIP, answersVLN, answersVPN, answersWCF, answersWLS;
	private LinkedHashMap<UUID, String> siteList = new LinkedHashMap<UUID, String>(); 
	private LinkedHashMap<UUID, String> businessList = new LinkedHashMap<UUID, String>();
	private String querySecurityControls, queryImpactCost, strRSValue;		//used for table updates
	private SecurityControlsQA secControl;	
	static SecurityControlRatings secControlRatings = new SecurityControlRatings();

	private List<SecurityControlRatings>lstSCR;
	
	private List<SecurityControlsQA> scList = new ArrayList<SecurityControlsQA>();	
	// constructor
	public SecurityControlBeanBusiness() {
		System.out.println("*** in SecurityControlsBeanBusiness constructor ***");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("*** in SecurityControlsBeanBusiness init ***");
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		//TODO: NOT USING auditUpsert class now, should we??
				MappingManager manager = new MappingManager(session);
				manager.udtCodec(AuditUpsert.class);
				auditUpsert.setChangedbyusername(currentUser.getFirstName().toLowerCase()+"."+currentUser.getLastName().toLowerCase());				
				manager.udtCodec(SecurityControlRatings.class);
				//get User's Industry
		query = "SELECT country_name, default_security_control_group FROM appl_auth.organizations where organization_name=?";
		prepared = session.prepare(query);
		bound = prepared.bind(currentUser.getOrgName());
		resultSet = session.execute(bound);
		row = resultSet.one();
		
		// set values to what was returned by the query
		userSecGroup = row.getString("default_security_control_group");		
		userCountry = row.getString("country_name");
		if (userCountry == "United States") {
			userCountry="US";
		}
		//load Questions Data
		LoadFormData();
		
	} // end of init()

	//functions to load the table, add, edit pages	
	public void LoadFormData() {
		//called on initialization the form
		System.out.println("in LoadFormData");
		
		//first grab all data to define: ID, security control question, drop-down answers
		query = "SELECT security_control_group, security_control_id, rating, security_control FROM appl_auth.security_controls " + 
				"WHERE security_control_group =?";

		prepared = session.prepare(query);
		bound = prepared.bind(userSecGroup);
		resultSet = session.execute(bound);
		
		// clear out any old content before retrieving new database info
		this.siteID = null;
		this.businessID = null;
		scList.clear();
		siteList.clear();
		businessList.clear();

		populateSiteList();		//populate the site drop-down list

		iterator = resultSet.iterator();			
		String questionID = "";
		while (iterator.hasNext()) {
				row = iterator.next();
				questionID =  row.getString("security_control_id").toUpperCase().trim() ;
				securityControlText = row.getString("security_control");				
				lstSCR = row.getList("rating", SecurityControlRatings.class);
				secControl = new SecurityControlsQA(questionID, lstSCR, securityControlText);
				scList.add(secControl);	
		}

		Iterator<SecurityControlsQA> itSEC = scList.iterator();
		while (itSEC.hasNext()) {
			secControl = itSEC.next();
			getQuestions_Answers(secControl.getID());
		}
		//TODO: FOr existing forms do we want to have them select the site and businessID then pull it up??
		//determine if survey already saved, if so, set Selected values

	}		//end LoadFormData()
	
	private void populateSelectedValues(String secID, String category) {
		switch (secID) {
		case "ACX": 
			this.selectedaACX = category;	
			break;
		case "BCM":
			this.selectedaBCM = category;	
			break;
		case "CLD":
			this.selectedaCLD  = category;
			break;
		case "CLS":
			this.selectedaCLS  = category;
			break;
		case "CPM":
			this.selectedaCPM  = category;
			break;
		case "CPO":
			this.selectedaCPO  = category;
			break;
		case "CSO":
			this.selectedaCSO  = category;
			break;
		case "DLP":
			this.selectedaDLP  = category;
			break;
		case "DOS":
			this.selectedaDOS  = category;
			break;
		case "ECR":
			this.selectedaECR  = category;
			break;
		case "EMP":
			this.selectedaEMP  = category;
			break;
		case "EMS":
			this.selectedaEMS  = category;
			break;
		case "EXS":
			this.selectedaEXS  = category;
			break;
		case "FW1":
			this.selectedaFW1  = category;
			break;
		case "IAM":
			this.selectedaIAM  = category;
			break;
		case "IDS":
			this.selectedaIDS  = category;
			break;
		case "INS":
			this.selectedaINS  = category;
			break;
		case "IRT":
			this.selectedaIRT  = category;
			break;
		case "MFA":
			this.selectedaMFA  = category;
			break;
		case "MOB":
			this.selectedaMOB  = category;
			break;
		case "PNT":
			this.selectedaPNT  = category;
			break;
		case "SAT":
			this.selectedaSAT  = category;
			break;
		case "SEG":
			this.selectedaSEG  = category;
			break;
		case "SIM":
			this.selectedaSIM  = category;
			break;
		case "STF":
			this.selectedaSTF  = category;
			break;
		case "TIP":
			this.selectedaTIP  = category;
			break;
		case "VLN":
			this.selectedaVLN  = category;
			break;
		case "VPN":
			this.selectedaVPN  = category;
			break;
		case "WCF":
			this.selectedaWCF  = category;
			break;
		case "WLS":
			this.selectedaWLS  = category;
			break;
		}	// end switch
	}

	private void resetAnswers() {
	System.out.println("in resetAnswers");
		this.selectedaACX = "";	
			this.selectedaBCM = "";	
			this.selectedaCLD  = "";
			this.selectedaCLS  = "";
			this.selectedaCPM  = "";
			this.selectedaCPO  = "";
			this.selectedaCSO  = "";
			this.selectedaDLP  = "";
			this.selectedaDOS  = "";
			this.selectedaECR  = "";
			this.selectedaEMP  = "";
			this.selectedaEMS  = "";
			this.selectedaEXS  = "";
			this.selectedaFW1  = "";
			this.selectedaIAM  = "";
			this.selectedaIDS  = "";
			this.selectedaINS  = "";
			this.selectedaIRT  = "";
			this.selectedaMFA  = "";
			this.selectedaMOB  = "";
			this.selectedaPNT  = "";
			this.selectedaSAT  = "";
			this.selectedaSEG  = "";
			this.selectedaSIM  = "";
			this.selectedaSTF  = "";
			this.selectedaTIP  = "";
			this.selectedaVLN  = "";
			this.selectedaVPN  = "";
			this.selectedaWCF  = "";
			this.selectedaWLS  = "";
	}

	private void calculateRS(String category, String secID) {
		//calculate the resistance strength 
		double newRSValue = 0.00;
		switch (category) {
		case "very_low" :
			newRSValue = 0.00;
			break;
		case "low" :
			newRSValue = 2.00;
			break;
		case "medium" :
			newRSValue = 5.45;
			break;
		case "high" :
			newRSValue = 7.95;
			break;
		case "very_high" :
			newRSValue = 9.50;
			break;
	}
		this.rsValue = this.rsValue + newRSValue;
		String newValue = secID + "_" + Double.toString(newRSValue);
		if (newValue.length() < 8) {
			newValue = newValue + "0";
		}
		
	//	System.out.println("newValue: " + newValue);
		this.sesQAList = this.sesQAList + newValue;
	}	//end calculateRS
	
	private long checkExistingSurveyResults() {
		long returnVal = (long) 0;
			// determine if the survey answers already exist if so, set the query to pull from the org.org_security_controls table
			query = "select count(*) from business_value_security_controls where site_id=? and business_process_id=? and security_control_group=?";
			prepared = session.prepare(query);
			bound = prepared.bind(siteID, businessID, userSecGroup);
			resultSet = session.execute(bound);
			row = resultSet.one();
			//Double count = row.getDecimal("count").doubleValue();
			long count = row.getLong("count");
			if (count > 0) {
				returnVal = count;
			}	
		return returnVal;
	}
	
	public void getQuestions_Answers(String ID) {
		
			switch (ID) {
			case "ACX": 
				qACX = secControl.getQuestion();
				answersACX = secControl.getAnswers();			
				break;
			case "BCM":
				qBCM = secControl.getQuestion();
				//answersBCM = new LinkedHashMap<String, String>();
				answersBCM = secControl.getAnswers();
				break;
			case "CLD":
				qCLD = secControl.getQuestion();
				answersCLD = secControl.getAnswers();
				break;
			case "CLS":
				qCLS = secControl.getQuestion();
				answersCLS = secControl.getAnswers();
				break;
			case "CPM":
				qCPM = secControl.getQuestion();
				answersCPM = secControl.getAnswers();
				break;
			case "CPO":
				qCPO = secControl.getQuestion();
				answersCPO = secControl.getAnswers();
				break;
			case "CSO":
				qCSO = secControl.getQuestion();
				answersCSO = secControl.getAnswers();
				break;
			case "DLP":
				qDLP = secControl.getQuestion();
				answersDLP = secControl.getAnswers();
				break;
			case "DOS":
				qDOS = secControl.getQuestion();
				answersDOS = secControl.getAnswers();
				break;
			case "ECR":
				qECR = secControl.getQuestion();
				answersECR = secControl.getAnswers();
				break;
			case "EMP":
				qEMP = secControl.getQuestion();
				answersEMP = secControl.getAnswers();
				break;
			case "EMS":
				qEMS = secControl.getQuestion();
				answersEMS = secControl.getAnswers();
				break;
			case "EXS":
				qEXS = secControl.getQuestion();
				answersEXS = secControl.getAnswers();
				break;
			case "FW1":
				qFW1 = secControl.getQuestion();
				answersFW1 = secControl.getAnswers();
				break;
			case "IAM":
				qIAM = secControl.getQuestion();
				answersIAM = secControl.getAnswers();
				break;
			case "IDS":
				qIDS = secControl.getQuestion();
				answersIDS = secControl.getAnswers();
				break;
			case "INS":
				qINS = secControl.getQuestion();
				answersINS = secControl.getAnswers();
				break;
			case "IRT":
				qIRT = secControl.getQuestion();
				answersIRT = secControl.getAnswers();
				break;
			case "MFA":
				qMFA = secControl.getQuestion();
				answersMFA = secControl.getAnswers();
				break;
			case "MOB":
				qMOB = secControl.getQuestion();
				answersMOB = secControl.getAnswers();
				break;
			case "PNT":
				qPNT = secControl.getQuestion();
				answersPNT = secControl.getAnswers();
				break;
			case "SAT":
				qSAT = secControl.getQuestion();
				answersSAT = secControl.getAnswers();
				break;
			case "SEG":
				qSEG = secControl.getQuestion();
				answersSEG = secControl.getAnswers();
				break;
			case "SIM":
				qSIM = secControl.getQuestion();
				answersSIM = secControl.getAnswers();
				break;
			case "STF":
				qSTF = secControl.getQuestion();
				answersSTF = secControl.getAnswers();
				break;
			case "TIP":
				qTIP = secControl.getQuestion();
				answersTIP = secControl.getAnswers();
				break;
			case "VLN":
				qVLN = secControl.getQuestion();
				answersVLN = secControl.getAnswers();
				break;
			case "VPN":
				qVPN = secControl.getQuestion();
				answersVPN = secControl.getAnswers();
				break;
			case "WCF":
				qWCF = secControl.getQuestion();
				answersWCF = secControl.getAnswers();
				break;
			case "WLS":
				qWLS = secControl.getQuestion();
				answersWLS = secControl.getAnswers();
				break;
			}	// end switch	
	}

    public BigDecimal getImpactCost(String secCID, int year) {
		BigDecimal ic = new BigDecimal(0);
	
		boundIC = preparedIC.bind(userCountry, year, secCID);
		ResultSet rs = session.execute(boundIC);
		
		Row r = rs.one();
		if (r == null || r.isNull(0)) {
			//System.out.println("r is null");
		} else {	
			//System.out.println("r not null: " + r.getObject(0));
		    ic = r.getDecimal("impact_cost");
		}	
			return ic;
	}

	public void listenSite(ValueChangeEvent event) {
		Object newValue = event.getNewValue();
		this.siteID = UUID.fromString(newValue.toString());
		populateBusinessProcesses(this.siteID);
	}
	
	public void listenBusiness(ValueChangeEvent event) {
		Object newValue = event.getNewValue();
		this.businessID = UUID.fromString(newValue.toString());
		System.out.println("in listenBusiness: " + siteID + "/" + businessID + "/" + userSecGroup);
		if (checkExistingSurveyResults() > 0) {
			query = "SELECT site_id, business_process_id, security_control_group, security_control_id, category, description, security_control " + 
					"FROM business_value_security_controls " + 
					"where site_id=? and  business_process_id=? and security_control_group=?" ;
			prepared = session.prepare(query);
			bound = prepared.bind(siteID, businessID, userSecGroup);
			resultSet = session.execute(bound);
			iterator = resultSet.iterator();
			String secID, category;
			while (iterator.hasNext()) {
					row = iterator.next();
					secID = row.getString("security_control_id");
					category = row.getString("category");
					populateSelectedValues(secID, category);
					calculateRS(category, secID);
					this.siteID = row.getUUID("site_id");
					populateBusinessProcesses(this.siteID);
					this.businessID=row.getUUID("business_process_id");
			}	
			this.rsValue = this.rsValue/30;
			System.out.println("rsval: " + rsValue);
		} else {
			//reset form controls
			resetAnswers();
			this.rsValue = 0.0;
		}	
	}
	
	public void populateSiteList() {
		System.out.println("in populateSiteList");
		this.siteList.clear();
		
		//load drop-down for site
		UUID dbSiteID = UUID.randomUUID();
		//SelectItem siSite = new SelectItem();
		String queryS = "select site_id, site_name FROM sites";
		PreparedStatement preparedS = session.prepare(queryS);
		BoundStatement boundS = preparedS.bind();			
		ResultSet rsS = session.execute(boundS);
		Iterator<Row> itSites = rsS.iterator();
		Row rowSites;	
		// Set the first entry to a user prompt
		this.siteList.put(UUID.randomUUID(), "Select a site name");
		while (itSites.hasNext()) {
			rowSites = itSites.next();			
			dbSiteID = rowSites.getUUID("site_id");
			siteName = rowSites.getString("site_name");
			this.siteList.put(dbSiteID, siteName);
		}	//end while
	}		//end populateSiteList()
	
	public void populateBusinessProcesses(UUID siteID) {
		this.businessList.clear();
		//SelectItem siBusiness = new SelectItem();
		UUID dbBusinessID = UUID.randomUUID();
		String queryBP = "select business_process_name, business_process_id from business_value_attribution where site_id=?"; 
		PreparedStatement preparedBP = session.prepare(queryBP);
		BoundStatement boundBP = preparedBP.bind(siteID);			
		ResultSet rsBP = session.execute(boundBP);
		Iterator<Row> itBPs = rsBP.iterator();
		Row rowBPs;
		// Set the first entry to a user prompt
		this.businessList.put(UUID.randomUUID(), "Select a business name");
		while (itBPs.hasNext()) {
			rowBPs = itBPs.next();
			dbBusinessID = rowBPs.getUUID("business_Process_id");
			businessName = rowBPs.getString("business_process_name");
			this.businessList.put(dbBusinessID, businessName);
		}	//end while
	}

	private String savetoTable(String selectedCategory, String selectedDescription, String securityControlID) {
		String result = "n/a";
		BigDecimal score = new BigDecimal(0);
		String costType = "na";
		Boolean impactsRS = false;
		Boolean applyIC = false;
		String securityControl = secControl.getSecText();
		int year = 2017;
		BigDecimal impactCost = new BigDecimal(0);
		getImpactCost(securityControlID, year);
		lstSCR.clear();

		lstSCR = secControl.getLstSCRatings();
		Iterator<SecurityControlRatings>itSCR = lstSCR.iterator();
		while (itSCR.hasNext()) {
			secControlRatings = itSCR.next();
			if (secControlRatings.getCategory().equals(selectedCategory)) {
				costType = secControlRatings.getCost_type();
				score = secControlRatings.getScore();
				applyIC = secControlRatings.isApply_impact_cost();
				impactsRS = secControlRatings.isImpacts_resistance_strength();

				boundSC = preparedSC.bind(userSecGroup, securityControlID, applyIC, selectedCategory, costType, selectedDescription, impactCost, impactsRS, score, securityControl);	
				session.execute(boundSC);
				result = securityControlID.toString() + ". " + selectedCategory + " was added to table.";
				//System.out.println("query: " + querySecurityControls);
				//System.out.println("values: " + userSecGroup+  "/" + securityControlID+  "/" + applyIC+  "/" + selectedCategory+  "/" + costType+  "/" + selectedDescription+  "/" + impactCost+  "/" + impactsRS+  "/" + score+  "/" + securityControl);
				break;					
			}		//end if desc			
		}	//end while itSCR		
		return result;
	}

	public String saveSurvey() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
	//Define and prepare query statements so they are only prepared once
		
		queryImpactCost = "SELECT impact_cost FROM appl_auth.data_breach_cost_controls WHERE country = ? AND year = ? AND  security_control_id = ? " ;
		preparedIC = session.prepare(queryImpactCost);
	
		querySecurityControls = "INSERT INTO business_value_security_controls (site_id, business_process_id, security_control_group, security_control_id, apply_impact_cost, category, cost_type, description, impact_cost, impacts_resistance_strength, score, security_control) " +
								"VALUES (" + this.siteID + ", " + this.businessID + ",?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
		
		preparedSC = session.prepare(querySecurityControls);
		Iterator<SecurityControlsQA> itSC = scList.iterator();
		String result;
		while (itSC.hasNext()) {
			secControl = itSC.next();
			switch (secControl.getID()) {
			case "ACX": 
				if (!this.getSelectedaACX().isEmpty()) {
					result = savetoTable(this.getSelectedaACX(), answersACX.get(this.getSelectedaACX()), secControl.getID());
					//System.out.println(result);
				}	
			case "BCM":
				if (!this.getSelectedaBCM().isEmpty()) {
					result = savetoTable(this.getSelectedaBCM(), answersBCM.get(this.getSelectedaBCM()), secControl.getID());
					//System.out.println(result);
				}	
			case "CLD":
				if (!this.getSelectedaCLD().isEmpty()) {
					result = savetoTable(this.getSelectedaCLD(), answersCLD.get(this.getSelectedaCLD()), secControl.getID());
					//System.out.println(result);
				}				
			case "CLS":
				if (!this.getSelectedaCLS().isEmpty()) {
					result = savetoTable(this.getSelectedaCLS(), answersCLS.get(this.getSelectedaCLS()), secControl.getID());
					//System.out.println(result);
				}				
			case "CPM":
				if (!this.getSelectedaCPM().isEmpty()) {
					result = savetoTable(this.getSelectedaCPM(), answersCPM.get(this.getSelectedaCPM()), secControl.getID());
					//System.out.println(result);
				}				
			case "CPO":
				if (!this.getSelectedaCPO().isEmpty()) {
					result = savetoTable(this.getSelectedaCPO(), answersCPO.get(this.getSelectedaCPO()), secControl.getID());
					//System.out.println(result);
				}				
			case "CSO":
				if (!this.getSelectedaCSO().isEmpty()) {
					result = savetoTable(this.getSelectedaCSO(), answersCSO.get(this.getSelectedaCSO()), secControl.getID());
					//System.out.println(result);
				}				 
			case "DLP":
				if (!this.getSelectedaDLP().isEmpty()) {
					result = savetoTable(this.getSelectedaDLP(), answersDLP.get(this.getSelectedaDLP()), secControl.getID());
					//System.out.println(result);
				}				
			case "DOS":
				if (!this.getSelectedaDOS().isEmpty()) {
					result = savetoTable(this.getSelectedaDOS(), answersDOS.get(this.getSelectedaDOS()), secControl.getID());
					//System.out.println(result);
				}				
			case "ECR":
				if (!this.getSelectedaECR().isEmpty()) {
					result = savetoTable(this.getSelectedaECR(), answersECR.get(this.getSelectedaECR()), secControl.getID());
					//System.out.println(result);
				}				
			case "EMP":
				if (!this.getSelectedaEMP().isEmpty()) {
					result = savetoTable(this.getSelectedaEMP(), answersEMP.get(this.getSelectedaEMP()), secControl.getID());
					//System.out.println(result);
				}							
			case "EMS":
				if (!this.getSelectedaEMS().isEmpty()) {
					result = savetoTable(this.getSelectedaEMS(), answersEMP.get(this.getSelectedaEMS()), secControl.getID());
					//System.out.println(result);
				}
			case "EXS":
				lstSCR.clear();
				if (!this.getSelectedaEXS().isEmpty()) {
					result = savetoTable(this.getSelectedaEXS(), answersEXS.get(this.getSelectedaEXS()), secControl.getID());
					//System.out.println(result);
				}			
			case "FW1":
				if (!this.getSelectedaFW1().isEmpty()) {
					result = savetoTable(this.getSelectedaFW1(), answersFW1.get(this.getSelectedaFW1()), secControl.getID());
					//System.out.println(result);
				}			
			case "IAM":
				if (!this.getSelectedaIAM().isEmpty()) {
					result = savetoTable(this.getSelectedaIAM(), answersIAM.get(this.getSelectedaIAM()), secControl.getID());
					//System.out.println(result);
				}			
			case "IDS":
				lstSCR.clear();
				if (!this.getSelectedaIDS().isEmpty()) {
					result = savetoTable(this.getSelectedaIDS(), answersIDS.get(this.getSelectedaIDS()), secControl.getID());
					//System.out.println(result);
				}			
			case "INS":
				if (!this.getSelectedaINS().isEmpty()) {
					result = savetoTable(this.getSelectedaINS(), answersINS.get(this.getSelectedaINS()), secControl.getID());
					//System.out.println(result);
				}			
			case "IRT":
				lstSCR.clear();
				if (!this.getSelectedaIRT().isEmpty()) {
					result = savetoTable(this.getSelectedaIRT(), answersIRT.get(this.getSelectedaIRT()), secControl.getID());
					//System.out.println(result);
				}			
			case "MFA":
				if (!this.getSelectedaMFA().isEmpty()) {
					result = savetoTable(this.getSelectedaMFA(), answersMFA.get(this.getSelectedaMFA()), secControl.getID());
					//System.out.println(result);
				}			
			case "MOB":
				if (!this.getSelectedaMOB().isEmpty()) {
					result = savetoTable(this.getSelectedaMOB(), answersMOB.get(this.getSelectedaMOB()), secControl.getID());
					//System.out.println(result);
				}			
			case "PNT":
				if (!this.getSelectedaPNT().isEmpty()) {
					result = savetoTable(this.getSelectedaPNT(), answersPNT.get(this.getSelectedaPNT()), secControl.getID());
					//System.out.println(result);
				}			
			case "SAT":
				if (!this.getSelectedaSAT().isEmpty()) {
					result = savetoTable(this.getSelectedaSAT(), answersSAT.get(this.getSelectedaSAT()), secControl.getID());
					//System.out.println(result);
				}			
			case "SEG":
				if (!this.getSelectedaSEG().isEmpty()) {
					result = savetoTable(this.getSelectedaSEG(), answersSEG.get(this.getSelectedaSEG()), secControl.getID());
					//System.out.println(result);
				}			
			case "SIM":
				if (!this.getSelectedaSIM().isEmpty()) {
					result = savetoTable(this.getSelectedaSIM(), answersSIM.get(this.getSelectedaSIM()), secControl.getID());
					//System.out.println(result);
				}						
			case "STF":
				if (!this.getSelectedaSTF().isEmpty()) {
					result = savetoTable(this.getSelectedaSTF(), answersSTF.get(this.getSelectedaSTF()), secControl.getID());
					//System.out.println(result);
				}			
			case "TIP":
				if (!this.getSelectedaTIP().isEmpty()) {
					result = savetoTable(this.getSelectedaTIP(), answersTIP.get(this.getSelectedaTIP()), secControl.getID());
					//System.out.println(result);
				}			
			case "VLN":
				if (!this.getSelectedaVLN().isEmpty()) {
					result = savetoTable(this.getSelectedaVLN(), answersVLN.get(this.getSelectedaVLN()), secControl.getID());
					//System.out.println(result);
				}			
			case "VPN":
				if (!this.getSelectedaVPN().isEmpty()) {
					result = savetoTable(this.getSelectedaVPN(), answersVPN.get(this.getSelectedaVPN()), secControl.getID());
					//System.out.println(result);
				}			
			case "WCF":
				if (!this.getSelectedaWCF().isEmpty()) {
					result = savetoTable(this.getSelectedaWCF(), answersWCF.get(this.getSelectedaWCF()), secControl.getID());
					//System.out.println(result);
				}			
			case "WLS":
				if (!this.getSelectedaWLS().isEmpty()) {
					result = savetoTable(this.getSelectedaWLS(), answersWLS.get(this.getSelectedaWLS()), secControl.getID());
					//System.out.println(result);
				}			
			}		//end switch
		}		//end while itSC

		if (currentUser.getOrgKeyspace().equals("dod")) {
			return "dashboard-mission-process-impact-dod.jsf";
		} else {
			return "dashboard-mission-process-impact.jsf";
		}
		
	}
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>GETTERS/SETTERS<<<<<<<<<<<<<<<<<<<<<<<<//
	
	public List<SecurityControlsQA> getScList() {
		return scList;
	}

	public UUID getSiteID() {
		return siteID;
	}

	public void setSiteID(UUID siteID) {
		this.siteID = siteID;
	}

	public UUID getBusinessID() {
		return businessID;
	}

	public void setBusinessID(UUID businessID) {
		this.businessID = businessID;
	}

	public void setScList(List<SecurityControlsQA> scList) {
		this.scList = scList;
	}

	public SecurityControlsQA getSecControl() {
		return secControl;
	}

	public void setSecControl(SecurityControlsQA secControl) {
		this.secControl = secControl;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getqACX() {
		return qACX;
	}

	public void setqACX(String qACX) {
		this.qACX = qACX;
	}

	public String getqBCM() {
		return qBCM;
	}

	public void setqBCM(String qBCM) {
		this.qBCM = qBCM;
	}

	public String getqCLD() {
		return qCLD;
	}

	public void setqCLD(String qCLD) {
		this.qCLD = qCLD;
	}

	public String getqCLS() {
		return qCLS;
	}

	public void setqCLS(String qCLS) {
		this.qCLS = qCLS;
	}

	public String getqCPM() {
		return qCPM;
	}

	public void setqCPM(String qCPM) {
		this.qCPM = qCPM;
	}

	public String getqCPO() {
		return qCPO;
	}

	public void setqCPO(String qCPO) {
		this.qCPO = qCPO;
	}

	public String getqCSO() {
		return qCSO;
	}

	public void setqCSO(String qCSO) {
		this.qCSO = qCSO;
	}

	public String getqDLP() {
		return qDLP;
	}

	public void setqDLP(String qDLP) {
		this.qDLP = qDLP;
	}

	public String getqDOS() {
		return qDOS;
	}

	public void setqDOS(String qDOS) {
		this.qDOS = qDOS;
	}

	public String getqECR() {
		return qECR;
	}

	public void setqECR(String qECR) {
		this.qECR = qECR;
	}

	public String getqEMP() {
		return qEMP;
	}

	public void setqEMP(String qEMP) {
		this.qEMP = qEMP;
	}

	public String getqEMS() {
		return qEMS;
	}

	public void setqEMS(String qEMS) {
		this.qEMS = qEMS;
	}

	public String getqEXS() {
		return qEXS;
	}

	public void setqEXS(String qEXS) {
		this.qEXS = qEXS;
	}

	public String getqFW1() {
		return qFW1;
	}

	public void setqFW1(String qFW1) {
		this.qFW1 = qFW1;
	}

	public String getqIAM() {
		return qIAM;
	}

	public void setqIAM(String qIAM) {
		this.qIAM = qIAM;
	}

	public String getqIDS() {
		return qIDS;
	}

	public void setqIDS(String qIDS) {
		this.qIDS = qIDS;
	}

	public String getqINS() {
		return qINS;
	}

	public void setqINS(String qINS) {
		this.qINS = qINS;
	}

	public String getqIRT() {
		return qIRT;
	}

	public void setqIRT(String qIRT) {
		this.qIRT = qIRT;
	}

	public String getqMFA() {
		return qMFA;
	}

	public void setqMFA(String qMFA) {
		this.qMFA = qMFA;
	}

	public String getqMOB() {
		return qMOB;
	}

	public void setqMOB(String qMOB) {
		this.qMOB = qMOB;
	}

	public String getqPNT() {
		return qPNT;
	}

	public void setqPNT(String qPNT) {
		this.qPNT = qPNT;
	}

	public String getqSAT() {
		return qSAT;
	}

	public void setqSAT(String qSAT) {
		this.qSAT = qSAT;
	}

	public String getqSEG() {
		return qSEG;
	}

	public void setqSEG(String qSEG) {
		this.qSEG = qSEG;
	}

	public String getqSIM() {
		return qSIM;
	}

	public void setqSIM(String qSIM) {
		this.qSIM = qSIM;
	}

	public String getqSTF() {
		return qSTF;
	}

	public void setqSTF(String qSTF) {
		this.qSTF = qSTF;
	}

	public String getqTIP() {
		return qTIP;
	}

	public void setqTIP(String qTIP) {
		this.qTIP = qTIP;
	}

	public String getqVLN() {
		return qVLN;
	}

	public void setqVLN(String qVLN) {
		this.qVLN = qVLN;
	}

	public String getqVPN() {
		return qVPN;
	}

	public void setqVPN(String qVPN) {
		this.qVPN = qVPN;
	}

	public String getqWCF() {
		return qWCF;
	}

	public void setqWCF(String qWCF) {
		this.qWCF = qWCF;
	}

	public String getqWLS() {
		return qWLS;
	}

	public void setqWLS(String qWLS) {
		this.qWLS = qWLS;
	}

	public Double getRsValue() {
		return rsValue;
	}

	public void setRsValue(Double rsValue) {
		this.rsValue = rsValue;
		this.strRSValue = Double.toString(rsValue);
	}

	public String getSesQAList() {
		return sesQAList;
	}

	public void setSesQAList(String sesQAList) {
		this.sesQAList = sesQAList;
	}

	public String getStrRSValue() {
		return strRSValue;
	}
	
	public void setStrRSValue(String strRSValue) {
		this.strRSValue = strRSValue;
	}
	public  String getSelectedaACX() {
		return this.selectedaACX;
	}

	public void setSelectedaACX(String  selectedaACX) {
		this.selectedaACX = selectedaACX;
	}

	public String getSelectedaBCM() {
		return selectedaBCM;
	}

	public void setSelectedaBCM(String selectedaBCM) {
		this.selectedaBCM = selectedaBCM;
	}

	public String getSelectedaCLD() {
		return selectedaCLD;
	}

	public void setSelectedaCLD(String selectedaCLD) {
		this.selectedaCLD = selectedaCLD;
	}

	public String getSelectedaCLS() {
		return selectedaCLS;
	}

	public void setSelectedaCLS(String selectedaCLS) {
		this.selectedaCLS = selectedaCLS;
	}

	public String getSelectedaCPM() {
		return selectedaCPM;
	}

	public void setSelectedaCPM(String selectedaCPM) {
		this.selectedaCPM = selectedaCPM;
	}

	public String getSelectedaCPO() {
		return selectedaCPO;
	}

	public void setSelectedaCPO(String selectedaCPO) {
		this.selectedaCPO = selectedaCPO;
	}

	public String getSelectedaCSO() {
		return selectedaCSO;
	}

	public void setSelectedaCSO(String selectedaCSO) {
		this.selectedaCSO = selectedaCSO;
	}

	public String getSelectedaDLP() {
		return selectedaDLP;
	}

	public void setSelectedaDLP(String selectedaDLP) {
		this.selectedaDLP = selectedaDLP;
	}

	public String getSelectedaDOS() {
		return selectedaDOS;
	}

	public void setSelectedaDOS(String selectedaDOS) {
		this.selectedaDOS = selectedaDOS;
	}

	public String getSelectedaECR() {
		return selectedaECR;
	}

	public void setSelectedaECR(String selectedaECR) {
		this.selectedaECR = selectedaECR;
	}

	public String getSelectedaEMP() {
		return selectedaEMP;
	}

	public void setSelectedaEMP(String selectedaEMP) {
		this.selectedaEMP = selectedaEMP;
	}

	public String getSelectedaEMS() {
		return selectedaEMS;
	}

	public void setSelectedaEMS(String selectedaEMS) {
		this.selectedaEMS = selectedaEMS;
	}

	public String getSelectedaEXS() {
		return selectedaEXS;
	}

	public void setSelectedaEXS(String selectedaEXS) {
		this.selectedaEXS = selectedaEXS;
	}

	public String getSelectedaFW1() {
		return selectedaFW1;
	}

	public void setSelectedaFW1(String selectedaFW1) {
		this.selectedaFW1 = selectedaFW1;
	}

	public String getSelectedaIAM() {
		return selectedaIAM;
	}

	public void setSelectedaIAM(String selectedaIAM) {
		this.selectedaIAM = selectedaIAM;
	}

	public String getSelectedaIDS() {
		return selectedaIDS;
	}

	public void setSelectedaIDS(String selectedaIDS) {
		this.selectedaIDS = selectedaIDS;
	}

	public String getSelectedaINS() {
		return selectedaINS;
	}

	public void setSelectedaINS(String selectedaINS) {
		this.selectedaINS = selectedaINS;
	}

	public String getSelectedaIRT() {
		return selectedaIRT;
	}

	public void setSelectedaIRT(String selectedaIRT) {
		this.selectedaIRT = selectedaIRT;
	}

	public String getSelectedaMFA() {
		return selectedaMFA;
	}

	public void setSelectedaMFA(String selectedaMFA) {
		this.selectedaMFA = selectedaMFA;
	}

	public String getSelectedaMOB() {
		return selectedaMOB;
	}

	public void setSelectedaMOB(String selectedaMOB) {
		this.selectedaMOB = selectedaMOB;
	}

	public String getSelectedaPNT() {
		return selectedaPNT;
	}

	public void setSelectedaPNT(String selectedaPNT) {
		this.selectedaPNT = selectedaPNT;
	}

	public String getSelectedaSAT() {
		return selectedaSAT;
	}

	public void setSelectedaSAT(String selectedaSAT) {
		this.selectedaSAT = selectedaSAT;
	}

	public String getSelectedaSEG() {
		return selectedaSEG;
	}

	public void setSelectedaSEG(String selectedaSEG) {
		this.selectedaSEG = selectedaSEG;
	}

	public String getSelectedaSIM() {
		return selectedaSIM;
	}

	public void setSelectedaSIM(String selectedaSIM) {
		this.selectedaSIM = selectedaSIM;
	}

	public String getSelectedaSTF() {
		return selectedaSTF;
	}

	public void setSelectedaSTF(String selectedaSTF) {
		this.selectedaSTF = selectedaSTF;
	}

	public String getSelectedaTIP() {
		return selectedaTIP;
	}

	public void setSelectedaTIP(String selectedaTIP) {
		this.selectedaTIP = selectedaTIP;
	}

	public String getSelectedaVLN() {
		return selectedaVLN;
	}

	public void setSelectedaVLN(String selectedaVLN) {
		this.selectedaVLN = selectedaVLN;
	}

	public String getSelectedaVPN() {
		return selectedaVPN;
	}

	public void setSelectedaVPN(String selectedaVPN) {
		this.selectedaVPN = selectedaVPN;
	}

	public String getSelectedaWCF() {
		return selectedaWCF;
	}

	public void setSelectedaWCF(String selectedaWCF) {
		this.selectedaWCF = selectedaWCF;
	}

	public String getSelectedaWLS() {
		return selectedaWLS;
	}

	public void setSelectedaWLS(String selectedaWLS) {
		this.selectedaWLS = selectedaWLS;
	}

	public Map<String, String> getAnswersACX() {
		return answersACX;
	}

	public void setAnswersACX(Map<String, String> answersACX) {
		this.answersACX = answersACX;
	}

	public Map<String, String> getAnswersBCM() {
		return answersBCM;
	}

	public void setAnswersBCM(Map<String, String> answersBCM) {
		this.answersBCM = answersBCM;
	}

	public Map<String, String> getAnswersCLD() {
		return answersCLD;
	}

	public void setAnswersACLD(Map<String, String> answersCLD) {
		this.answersCLD = answersCLD;
	}

	public Map<String, String> getAnswersCLS() {
		return answersCLS;
	}

	public void setAnswersACLS(Map<String, String> answersCLS) {
		this.answersCLS = answersCLS;
	}

	public Map<String, String> getAnswersCPM() {
		return answersCPM;
	}

	public void setAnswersCPM(Map<String, String> answersCPM) {
		this.answersCPM = answersCPM;
	}

	public Map<String, String> getAnswersCPO() {
		return answersCPO;
	}

	public void setAnswersCPO(Map<String, String> answersCPO) {
		this.answersCPO = answersCPO;
	}

	public Map<String, String> getAnswersCSO() {
		return answersCSO;
	}

	public void setAnswersCSO(Map<String, String> answersCSO) {
		this.answersCSO = answersCSO;
	}

	public Map<String, String> getAnswersDLP() {
		return answersDLP;
	}

	public void setAnswersDLP(Map<String, String> answersDLP) {
		this.answersDLP = answersDLP;
	}

	public Map<String, String> getAnswersDOS() {
		return answersDOS;
	}

	public void setAnswersDOS(Map<String, String> answersDOS) {
		this.answersDOS = answersDOS;
	}

	public Map<String, String> getAnswersECR() {
		return answersECR;
	}

	public void setAnswersECR(Map<String, String> answersECR) {
		this.answersECR = answersECR;
	}

	public Map<String, String> getAnswersEMP() {
		return answersEMP;
	}

	public void setAnswersEMP(Map<String, String> answersEMP) {
		this.answersEMP = answersEMP;
	}

	public Map<String, String> getAnswersEMS() {
		return answersEMS;
	}

	public void setAnswersEMS(Map<String, String> answersEMS) {
		this.answersEMS = answersEMS;
	}

	public Map<String, String> getAnswersEXS() {
		return answersEXS;
	}

	public void setAnswersEXS(Map<String, String> answersEXS) {
		this.answersEXS = answersEXS;
	}

	public Map<String, String> getAnswersFW1() {
		return answersFW1;
	}

	public void setAnswersFW1(Map<String, String> answersFW1) {
		this.answersFW1 = answersFW1;
	}

	public Map<String, String> getAnswersIAM() {
		return answersIAM;
	}

	public void setAnswersIAM(Map<String, String> answersIAM) {
		this.answersIAM = answersIAM;
	}

	public Map<String, String> getAnswersIDS() {
		return answersIDS;
	}

	public void setAnswersIDS(Map<String, String> answersIDS) {
		this.answersIDS = answersIDS;
	}

	public Map<String, String> getAnswersINS() {
		return answersINS;
	}

	public void setAnswersINS(Map<String, String> answersINS) {
		this.answersINS = answersINS;
	}

	public Map<String, String> getAnswersIRT() {
		return answersIRT;
	}

	public void setAnswersIRT(Map<String, String> answersIRT) {
		this.answersIRT = answersIRT;
	}

	public Map<String, String> getAnswersMFA() {
		return answersMFA;
	}

	public void setAnswersMFA(Map<String, String> answersMFA) {
		this.answersMFA = answersMFA;
	}

	public Map<String, String> getAnswersMOB() {
		return answersMOB;
	}

	public void setAnswersMOB(Map<String, String> answersMOB) {
		this.answersMOB = answersMOB;
	}

	public Map<String, String> getAnswersPNT() {
		return answersPNT;
	}

	public void setAnswersPNT(Map<String, String> answersPNT) {
		this.answersPNT = answersPNT;
	}

	public Map<String, String> getAnswersSAT() {
		return answersSAT;
	}

	public void setAnswersSAT(Map<String, String> answersSAT) {
		this.answersSAT = answersSAT;
	}

	public Map<String, String> getAnswersSEG() {
		return answersSEG;
	}

	public void setAnswersSEG(Map<String, String> answersSEG) {
		this.answersSEG = answersSEG;
	}

	public Map<String, String> getAnswersSIM() {
		return answersSIM;
	}

	public void setAnswersSIM(Map<String, String> answersSIM) {
		this.answersSIM = answersSIM;
	}

	public Map<String, String> getAnswersSTF() {
		return answersSTF;
	}

	public void setAnswersSTF(Map<String, String> answersSTF) {
		this.answersSTF = answersSTF;
	}

	public Map<String, String> getAnswersTIP() {
		return answersTIP;
	}

	public void setAnswersTIP(Map<String, String> answersTIP) {
		this.answersTIP = answersTIP;
	}

	public Map<String, String> getAnswersVLN() {
		return answersVLN;
	}

	public void setAnswersVLN(Map<String, String> answersVLN) {
		this.answersVLN = answersVLN;
	}

	public Map<String, String> getAnswersVPN() {
		return answersVPN;
	}

	public void setAnswersVPN(Map<String, String> answersVPN) {
		this.answersVPN = answersVPN;
	}

	public Map<String, String> getAnswersWCF() {
		return answersWCF;
	}

	public void setAnswersWCF(Map<String, String> answersWCF) {
		this.answersWCF = answersWCF;
	}

	public Map<String, String> getAnswersWLS() {
		return answersWLS;
	}

	public void setAnswersWLS(Map<String, String> answersWLS) {
		this.answersWLS = answersWLS;
	}

	public LinkedHashMap<UUID, String> getSiteList() {
		return siteList;
	}

	public void setSiteList(LinkedHashMap<UUID, String> siteList) {
		this.siteList = siteList;
	}

	public LinkedHashMap<UUID, String> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(LinkedHashMap<UUID, String> businessList) {
		this.businessList = businessList;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	
	
}		//end class SecurityControlsBeanBusiness