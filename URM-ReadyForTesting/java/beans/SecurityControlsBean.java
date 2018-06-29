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
 * 6.25.2018		tammy.bogart		created 
 */
 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.g2ops.impact.urm.types.AuditUpsert;
import com.g2ops.impact.urm.types.BreachTypes;
import com.g2ops.impact.urm.types.BusinessHosts;
import com.g2ops.impact.urm.types.RunsOnHost;
import com.g2ops.impact.urm.types.SecurityControlRatings;
import com.g2ops.impact.urm.types.SecurityControls;
import com.g2ops.impact.urm.types.SecurityControlRatingsList;

@Named("securityControlsBean")
@SessionScoped	//required for pulling in currentUser info --Client-specific data      


public class SecurityControlsBean  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;

	static AuditUpsert auditUpsert = new AuditUpsert();
	static SecurityControlRatings secControlRatings = new SecurityControlRatings();

	private Session session;
	private ResultSet resultSet; 
	private Iterator<Row> iterator;
	private PreparedStatement prepared;
	private BoundStatement bound;
	private Row row;

	private String documentTitle, action, query, scGroup, scID, scName, selectedscID, selectedscGroup, origSCGroup, origSCID;

	private List<SecurityControls>scList = new ArrayList<SecurityControls>();
	private SecurityControls sc;
	private SecurityControlRatings scr, selectedRatings;
	private List<SecurityControlRatings>scRatingsList = new ArrayList<SecurityControlRatings>();
	private SecurityControlRatingsList scRatings;
	private List<SecurityControlRatingsList>scRList = new ArrayList<SecurityControlRatingsList>();

	private BigDecimal score;
	private String category, description, costType, expandedPanels, actionRatings;
	private boolean applyIC, impactsRS;
	
	// constructor
	public SecurityControlsBean() {
		System.out.println("*** in SecurityControlsBean constructor ***");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("*** in SecurityControlsBean init ***");
		
		//DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());

		//TODO: NOT USING auditUpsert class now, should we??
		MappingManager manager = new MappingManager(session);
		manager.udtCodec(AuditUpsert.class);
		auditUpsert.setChangedbyusername(currentUser.getFirstName().toLowerCase()+"."+currentUser.getLastName().toLowerCase());				
		manager.udtCodec(SecurityControlRatings.class);

		//load table data
		LoadSecurityControls();
	} // end of init()
	
	public void LoadSecurityControls() {
		//called on initialization the Table
		System.out.println("in LoadSecurityControlsBean");
		
		//first grab all data to define: ID, security control question, drop-down answers
		query = "SELECT security_control_group, security_control_id, rating, security_control FROM appl_auth.security_controls";
		
		prepared = session.prepare(query);
		bound = prepared.bind();
		resultSet = session.execute(bound);
		
		scList.clear();
		iterator = resultSet.iterator();			
		while (iterator.hasNext()) {
				row = iterator.next();
				scGroup = row.getString("security_control_group");
				scID = row.getString("security_control_id");
				scName = row.getString("security_control");				
				scRatingsList = row.getList("rating", SecurityControlRatings.class);
				sc = new SecurityControls(scGroup, scID, scName, scRatingsList);
				scList.add(sc);	
//				System.out.println("scID: " + scID);
		}	//end of while	 	
	}		//end LoadSecurityControls

	public String LoadSCEditFormData(SecurityControls scEdit) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadSCEditFormData");	
		this.action="edit";
		this.actionRatings = "";
		System.out.println("loadSCEdit: " + scEdit.getScRatings().size());
		this.scRList.clear();

		this.documentTitle="Edit Security Control";
		this.selectedscID = scEdit.getScID();
		this.selectedscGroup = scEdit.getScGroup();
		this.scID = scEdit.getScID();
		this.scGroup = scEdit.getScGroup();
		this.scName = scEdit.getScName();
		this.origSCGroup = scEdit.getScGroup();
		this.origSCID = scEdit.getScID();

		//Load Ratings Table
		this.scRatingsList = scEdit.getScRatings();
		this.expandedPanels = "";
		//goto Edit form
		return "/superadmin/security-controls.jsf";
	}	//end of LoadSCEditFormData()

	public String LoadSCAddFormData() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in loadSCAddFormData");	
		this.action="add";

		this.documentTitle="Add Security Control";
		this.selectedscID = " ";
		this.selectedscGroup = " ";
		this.scID = " ";
		this.scGroup = " ";
		this.scName = " ";
		this.scRatingsList.clear();		
		this.scRList.clear();
		
		//goto Add form
		return "/superadmin/security-controls.jsf";
	}	//end of LoadSCAddFormData()
	
	//Functions to push changes to the database table
	public String actionSCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
			System.out.println("action in controller: " + action);   //good

			String returnVar = " ";
			if (action == "add") {
				returnVar = addSCControllerMethod();
			} else {
				if (action == "edit") {
					returnVar = editSCControllerMethod();
				}
			}
			return returnVar;
		}
	
	public String addSCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {		
			insertSC();
			LoadSecurityControls();
			return "/superadmin/security-controls-table.jsf";
	}
	
	public void insertSC() {
		if (this.getScGroup().isEmpty()) {
			//do nothing
		} else {
				query = "INSERT into appl_auth.security_controls " 
						+	"(security_control_group, "
						+	"security_control_id, "
						+	"security_control, "
						+	"rating) "
						+	"VALUES (?,?,?,?)";
					
				PreparedStatement prepared = session.prepare(query);
				BoundStatement bound = prepared.bind(this.getScGroup(), this.getScID(), this.getScName(), this.getScRatingsList());
				session.execute(bound);		
		}		
	}
	
	public String editSCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {		
		System.out.println("in editSCControllerMethod");

		PreparedStatement prepared;
		BoundStatement bound;
		query="";
		
		if (this.origSCGroup.equals(this.scGroup) && this.origSCID.equals(this.scID)) {	
			//if primary key does not change, update existing data	
			System.out.println("primary key unchanged");
			query = "UPDATE appl_auth.security_controls "
				+	"SET security_control=?, "
				+	"rating=? "
				+	"WHERE security_control_group=? and security_control_id=?";

			prepared = session.prepare(query);
			bound = prepared.bind(this.getScName(), this.getScRatingsList(), this.scGroup, this.scID);
			session.execute(bound);
		} else {
			System.out.println("primary key changed");
			//if primary key does change, insert new record
			query = "INSERT into appl_auth.security_controls " 
					+	"(security_control_group, "
					+	"security_control_id, "
					+	"security_control, "
					+	"rating) "
					+	"VALUES (?,?,?,?)";
				
			PreparedStatement prepared2 = session.prepare(query);
			BoundStatement bound2 = prepared2.bind(this.getScGroup(), this.getScID(), this.getScName(), this.getScRatingsList());
			session.execute(bound2);

			//now delete old record
			query = "DELETE FROM appl_auth.security_controls "
					+	"WHERE security_control_group=? and security_control_id=?";
			
			PreparedStatement prepared3 = session.prepare(query);
			BoundStatement bound3 = prepared3.bind(this.getScGroup(), this.getScID());
			session.execute(bound3);
		}
		
		//re-load table
		LoadSecurityControls();
		return "/superadmin/security-controls-table.jsf";
	}

	public void deleteSCControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in deleteSSCControllerMethod");
			
		query = "DELETE FROM appl_auth.security_controls "
				+	"WHERE security_control_group=? and security_control_id=?";
 
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind(selectedscGroup, selectedscID);
		session.execute(bound);
		//update table
		this.scList.remove(sc);
			System.out.println("Security Control " + selectedscGroup + "/" + selectedscID +" was deleted.");		
	}

	public void actionSCRatingsControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		this.expandedPanels = "frmMain:pnlRatings";
 
		secControlRatings.setScore(this.getScore());
		secControlRatings.setCategory(this.getCategory());
		secControlRatings.setDescription(this.getDescription());
		secControlRatings.setApply_impact_cost(this.isApplyIC());
		secControlRatings.setCost_type(this.getCostType());
		secControlRatings.setImpacts_resistance_strength(this.isImpactsRS());
		this.scRatingsList.add(secControlRatings);
		
		if (actionRatings == "edit") {
			this.scRatingsList.remove(selectedRatings);

			query="UPDATE appl_auth.security_controls " 
					+	"SET rating=?"  
					+	"WHERE security_control_group=? and security_control_id=?";
			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound = prepared.bind(this.scRatingsList, selectedscGroup, selectedscID);
			session.execute(bound);
			
		} else {
			insertSC();
		}
	}

	public void deleteSCRatingsControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in deleteSCRatingsControllerMethod");
		this.scRatingsList.remove(selectedRatings);

		//update table
		query = "UPDATE appl_auth.security_controls "
				+ "SET rating=? "
				+	"WHERE security_control_group=? and security_control_id=?";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind(this.scRatingsList, selectedscGroup, selectedscID);
		session.execute(bound);
	}
	
	public void setSelected(SecurityControls selSC)  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		//set selected values
		System.out.println("selSC.id: " + selSC.getScID());
		this.sc = selSC;
		this.scID = selSC.getScID();
		this.scGroup = selSC.getScGroup();
		this.scName = selSC.getScName();
		this.scRatingsList = selSC.getScRatings();
		this.selectedscGroup = selSC.getScGroup();
		this.selectedscID = selSC.getScID();	
	}

	public void setSelectedRating(SecurityControlRatings selSCR)  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		//set selected values
		this.score = selSCR.getScore();
		this.category = selSCR.getCategory();
		this.description = selSCR.getDescription();
		this.costType = selSCR.getCost_type();
		this.applyIC = selSCR.isApply_impact_cost();
		this.impactsRS = selSCR.isImpacts_resistance_strength();
		this.selectedRatings = selSCR;
		this.actionRatings = "edit";
	}

	public void setAddRating(SecurityControlRatings selSCR)  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		//set selected values
		this.score =  new BigDecimal("0");
		this.category =  "";
		this.description =  "";
		this.costType =  "";
		this.applyIC =  false;
		this.impactsRS =  false;
		this.selectedRatings = selSCR;
		this.actionRatings = "add";
		System.out.println("in setAddRatings: " + this.getScGroup() + "/"  + this.scGroup + "/" + this.selectedscGroup);
	}
	
//////////Getters and Setters

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public UserBean getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserBean currentUser) {
		this.currentUser = currentUser;
	}

	public List<SecurityControls> getSecurityControlsData() {
		return scList;
	}
	
/*	public List<SecurityControlRatingsList> getSecurityControlRatingData() {
		//return scRatingsList;
		return scRList;
	}
*/	
	public List<SecurityControlRatings> getSecurityControlRatingData() {
		return scRatingsList;
	}
	
	public SecurityControls getSC() {
		return sc;
	}
	
	public void setSc(SecurityControls sc) {
		this.sc = sc;
	}
	
	public List<SecurityControlRatingsList> getScRList() {
		return scRList;
	}

	public void setScRList(List<SecurityControlRatingsList> scRating) {
		this.scRList = scRating;
	}

	public String getScGroup() {
		return scGroup;
	}

	public void setScGroup(String scGroup) {
		this.scGroup = scGroup;
	}

	public String getScID() {
		return scID;
	}

	public void setScID(String scID) {
		this.scID = scID;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public String getSelectedscID() {
		return selectedscID;
	}

	public void setSelectedscID(String selectedscID) {
		this.selectedscID = selectedscID;
	}

	public String getSelectedscGroup() {
		return selectedscGroup;
	}

	public void setSelectedscGroup(String selectedscGroup) {
		this.selectedscGroup = selectedscGroup;
	}

	public String getOrigSCGroup() {
		return origSCGroup;
	}

	public void setOrigSCGroup(String origSCGroup) {
		this.origSCGroup = origSCGroup;
	}

	public String getOrigSCID() {
		return origSCID;
	}

	public void setOrigSCID(String origSCID) {
		this.origSCID = origSCID;
	}

	public List<SecurityControls> getScList() {
		return scList;
	}

	public void setScList(List<SecurityControls> scList) {
		this.scList = scList;
	}

	public SecurityControls getSc() {
		return sc;
	}
	
	public SecurityControlRatingsList getScRatings() {
		return scRatings;
	}

	public void setScRatings(SecurityControlRatingsList scRatings) {
		this.scRatings = scRatings;
	}

	public List<SecurityControlRatings> getScRatingsList() {
		return scRatingsList;
	}

	public void setScRatingsList(List<SecurityControlRatings> scRatingsList) {
		this.scRatingsList = scRatingsList;
	}

	public SecurityControlRatings getScr() {
		return scr;
	}

	public void setScr(SecurityControlRatings scr) {
		this.scr = scr;
	}

	public BigDecimal getScore() {
		return score;
	}

	public SecurityControlRatings getSelectedRatings() {
		return selectedRatings;
	}

	public void setSelectedRatings(SecurityControlRatings selectedRatings) {
		this.selectedRatings = selectedRatings;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
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

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public boolean isApplyIC() {
		return applyIC;
	}

	public void setApplyIC(boolean applyIC) {
		this.applyIC = applyIC;
	}

	public boolean isImpactsRS() {
		return impactsRS;
	}

	public void setImpactsRS(boolean impactsRS) {
		this.impactsRS = impactsRS;
	}

	public String getExpandedPanels() {
		return expandedPanels;
	}

	public void setExpandedPanels(String expandedPanels) {
		this.expandedPanels = expandedPanels;
	}

	public String getActionRatings() {
		return actionRatings;
	}

	public void setActionRatings(String actionRatings) {
		this.actionRatings = actionRatings;
	}
	
	
}

	