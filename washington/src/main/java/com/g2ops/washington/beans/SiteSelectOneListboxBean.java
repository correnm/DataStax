package com.g2ops.washington.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.washington.types.OrgUnit;
import com.g2ops.washington.types.Site;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class SiteSelectOneListboxBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Site> sites;
	public String selectedSite;
	
	public SiteSelectOneListboxBean() {
		
		Session dbSession = SessionUtils.getOrgDBSession();
		ResultSet rs;
		Row row;
		Iterator<Row> iterator;
		this.sites = new ArrayList<Site>();

		// execute the query
		rs = dbSession.execute("select site_id, site_name from sites");

		iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			row = iterator.next();
			this.sites.add(new Site(row.getUUID("site_id").toString(), row.getString("site_name")));
			System.out.println("site_name: " + row.getString("site_name"));
		}

	}
	
	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	public String getSelectedSite() {
		return selectedSite;
	}

	public void setSelectedSite(String selectedSite) {
		this.selectedSite = selectedSite;
	}

}
