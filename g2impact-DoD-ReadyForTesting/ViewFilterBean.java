package com.g2ops.washington.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import com.g2ops.washington.types.OrgUnit;
import com.g2ops.washington.types.Site;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class ViewFilterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String org_id;
	private String selectedOrgUnit;
	private String selectedSite;
	private String selectedOrgUnitName;
	private String selectedSiteName;
	
	private List<OrgUnit> orgUnits;
	private List<Site> sites;

	private Session dbSession = SessionUtils.getOrgDBSession();
	private ResultSet rs;
	private Row row;
	private Iterator<Row> iterator;
	private PreparedStatement preparedStatement;
	private BoundStatement boundStatement;

	public ViewFilterBean() {
		
		this.orgUnits = new ArrayList<OrgUnit>();
		this.sites = new ArrayList<Site>();

		HttpSession userSession = SessionUtils.getSession();

		// get the default OU and Site from the user's session
		selectedOrgUnit = (String)userSession.getAttribute("currentOU");
		selectedSite = (String)userSession.getAttribute("currentSite");
		String orgKeyspace = (String)userSession.getAttribute("orgKeyspace");
		if (orgKeyspace.equals("g2")) {
			org_id = "df72e1dd-a385-4ab9-ba30-70b9df8d539b";
		}
		else if (orgKeyspace.equals("vmasc")) {
			org_id = "2cd3bc78-350f-47ce-bd53-7525b93f0640";
		}
		else if (orgKeyspace.equals("dod")) {
			org_id = "df72e1dd-a385-4ab9-ba30-70b9df8d539b";
		}
		
		createOrgUnitList();
		createSiteList();
		
	}
	
	private void createOrgUnitList() {
		
		// execute the query
		rs = dbSession.execute("select org_unit_id, org_unit_name from organizational_units");

		iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			row = iterator.next();
			this.orgUnits.add(new OrgUnit(row.getUUID("org_unit_id").toString(), row.getString("org_unit_name")));
		}

	}

	private void createSiteList() {

		// clear out the current list
		this.sites.clear();
		
		// create the prepared statement for updating the user's info
		preparedStatement = dbSession.prepare("select site_id, site_name from sites where org_id = ? and org_unit_id = ?");

		// create bound statement
		boundStatement = preparedStatement.bind(UUID.fromString(org_id), UUID.fromString(selectedOrgUnit));
		
		// execute the query
		rs = dbSession.execute(boundStatement);

		iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			row = iterator.next();
			this.sites.add(new Site(row.getUUID("site_id").toString(), row.getString("site_name")));
		}

	}

	public String getSelectedOrgUnit() {
		return selectedOrgUnit;
	}

	public String getSelectedOrgUnitName() {

		// create the prepared statement for selecting the user's info
		preparedStatement = dbSession.prepare("select org_unit_name from organizational_units where org_id = ? and org_unit_id = ?");

		// create bound statement
		boundStatement = preparedStatement.bind(UUID.fromString(org_id), UUID.fromString(selectedOrgUnit));
		
		// execute the query
		rs = dbSession.execute(boundStatement);

		// get the result values
		Row row = rs.one();
		
		this.selectedOrgUnitName = row.getString("org_unit_name");

		return selectedOrgUnitName;

	}

	public void setSelectedOrgUnit(String selectedOrgUnit) {
		this.selectedOrgUnit = selectedOrgUnit;
		createSiteList();
	}

	public List<OrgUnit> getOrgUnits() {
		return orgUnits;
	}

	public String getSelectedSite() {
		return selectedSite;
	}

	public String getSelectedSiteName() {

		// create the prepared statement for selecting the user's info
		preparedStatement = dbSession.prepare("select site_name from sites where org_id = ? and org_unit_id = ? and site_id = ?");

		// create bound statement
		boundStatement = preparedStatement.bind(UUID.fromString(org_id), UUID.fromString(selectedOrgUnit), UUID.fromString(selectedSite));
		
		// execute the query
		rs = dbSession.execute(boundStatement);

		// get the result values
		Row row = rs.one();
		
		this.selectedSiteName = row.getString("site_name");

		return selectedSiteName;

	}

	public void setSelectedSite(String selectedSite) {
		this.selectedSite = selectedSite;
	}

	public List<Site> getSites() {
		return sites;
	}

	public String refreshView() {

		HttpSession userSession = SessionUtils.getSession();

		userSession.setAttribute("currentOU", selectedOrgUnit);
		userSession.setAttribute("currentSite", selectedSite);

		return(null);

	}

}
