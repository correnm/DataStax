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

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.g2ops.impact.urm.types.OrgUnit;
import com.g2ops.impact.urm.types.Site;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("viewFilterBean")
@SessionScoped
public class ViewFilterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	//private String org_id;
	private String selectedOrgUnit;
	private String selectedSite;
	private String selectedOrgUnitName;
	private String selectedSiteName;
	
	private List<OrgUnit> orgUnits;
	private List<Site> sites;

	public ViewFilterBean() {
		
		this.orgUnits = new ArrayList<OrgUnit>();
		this.sites = new ArrayList<Site>();

		HttpSession userSession = SessionUtils.getSession();

		// get the default OU and Site from the user's session
		selectedOrgUnit = (String)userSession.getAttribute("currentOU");
		selectedSite = (String)userSession.getAttribute("currentSite");
		//String orgKeyspace = (String)userSession.getAttribute("orgKeyspace");
		//if (orgKeyspace.equals("g2")) {
			//org_id = "df72e1dd-a385-4ab9-ba30-70b9df8d539b";
		//}
		//else if (orgKeyspace.equals("vmasc")) {
			//org_id = "2cd3bc78-350f-47ce-bd53-7525b93f0640";
		//}
		
		createOrgUnitList();
		createSiteList(selectedOrgUnit);
		
	}
	
	private void createOrgUnitList() {
		
		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService();

		// execute the query
		ResultSet rs = databaseQueryService.runQuery("select org_unit_id, org_unit_name from organizational_units");

		Iterator<Row> iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			this.orgUnits.add(new OrgUnit(row.getUUID("org_unit_id").toString(), row.getString("org_unit_name")));
		}
		
	}

	private void createSiteList(String orgUnit) {

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService();

		// clear out the current list
		this.sites.clear();
		
		// execute the query
		//ResultSet rs = databaseQueryService.runQuery("select site_id, site_name from sites where org_id = " + UUID.fromString(org_id) + " and org_unit_id = " + UUID.fromString(orgUnit));
		ResultSet rs = databaseQueryService.runQuery("select site_id, site_name from sites where org_unit_id = " + UUID.fromString(orgUnit));

		Iterator<Row> iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			Row row = iterator.next();
			this.sites.add(new Site(row.getUUID("site_id").toString(), row.getString("site_name")));
			if (this.sites.size() == 1){
				this.selectedSite = row.getUUID("site_id").toString();
				System.out.println("in createSiteList method - setting this.selectedSite = " + this.selectedSite);
			}
		}
		
	}

	public String getSelectedOrgUnit() {
		return selectedOrgUnit;
	}

	public String getSelectedOrgUnitName() {

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService();

		// execute the query
		//ResultSet rs = databaseQueryService.runQuery("select org_unit_name from organizational_units where org_id = " + UUID.fromString(org_id) + " and org_unit_id = " + UUID.fromString(selectedOrgUnit));
		ResultSet rs = databaseQueryService.runQuery("select org_unit_name from organizational_units where org_unit_id = " + UUID.fromString(selectedOrgUnit));

		// get the result value
		this.selectedOrgUnitName = rs.one().getString("org_unit_name");

		return selectedOrgUnitName;

	}

	public void setSelectedOrgUnit(String selectedOrgUnit) {
		this.selectedOrgUnit = selectedOrgUnit;
		System.out.println("in setSelectedOrgUnit method - set this.selectedOrgUnit = " + this.selectedOrgUnit);
		createSiteList(selectedOrgUnit);
	}

	public List<OrgUnit> getOrgUnits() {
		return orgUnits;
	}

	public String getSelectedSite() {
		return selectedSite;
	}

	public String getSelectedSiteName() {

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService();

		// execute the query
		//ResultSet rs = databaseQueryService.runQuery("select site_name from sites where org_id = " + UUID.fromString(org_id) + " and org_unit_id = " + UUID.fromString(selectedOrgUnit) + " and site_id = " + UUID.fromString(selectedSite));
		ResultSet rs = databaseQueryService.runQuery("select site_name from sites where org_unit_id = " + UUID.fromString(selectedOrgUnit) + " and site_id = " + UUID.fromString(selectedSite));

		// get the result value
		this.selectedSiteName = rs.one().getString("site_name");
		
		return selectedSiteName;

	}

	public void setSelectedSite(String selectedSite) {
		this.selectedSite = selectedSite;
		System.out.println("in setSelectedSite method - set this.selectedSite = " + this.selectedSite);
	}

	public List<Site> getSites() {
		System.out.println("in getSites method");
		return sites;
	}

	public String refreshView() {

		System.out.println("refreshView - selectedOrgUnit: " + selectedOrgUnit);
		System.out.println("refreshView - selectedSite: " + selectedSite);

		HttpSession userSession = SessionUtils.getSession();

		userSession.setAttribute("currentOU", selectedOrgUnit);
		userSession.setAttribute("currentSite", selectedSite);

		return(null);

	}

}
