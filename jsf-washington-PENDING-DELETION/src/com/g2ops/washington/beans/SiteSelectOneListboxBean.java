package com.g2ops.washington.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.Site;

@ManagedBean
@SessionScoped
public class SiteSelectOneListboxBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Site> sites;
	public String selectedSite;
	
	public SiteSelectOneListboxBean() {
		
		this.sites = new ArrayList<Site>();
		this.sites.add(new Site("1", "Virginia Beach Office"));
		this.sites.add(new Site("2", "San Diego Office"));
		this.sites.add(new Site("3", "Tampa Bay Office in Florida"));
		
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
