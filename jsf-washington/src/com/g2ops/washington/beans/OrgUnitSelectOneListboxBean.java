package com.g2ops.washington.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.OrgUnit;

@ManagedBean
@SessionScoped
public class OrgUnitSelectOneListboxBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<OrgUnit> orgUnits;
	public String selectedOrgUnit;
	
	public OrgUnitSelectOneListboxBean() {
		
		this.orgUnits = new ArrayList<OrgUnit>();
		this.orgUnits.add(new OrgUnit("1", "Eastern Division"));
		this.orgUnits.add(new OrgUnit("2", "Southern Division"));
		this.orgUnits.add(new OrgUnit("3", "Western Division with Long Name"));
		this.orgUnits.add(new OrgUnit("4", "Northern Division"));
		
	}
	
	public List<OrgUnit> getOrgUnits() {
		return orgUnits;
	}

	public void setOrgUnits(List<OrgUnit> orgUnits) {
		this.orgUnits = orgUnits;
	}
	
	public String getSelectedOrgUnit() {
		return selectedOrgUnit;
	}

	public void setSelectedOrgUnit(String selectedOrgUnit) {
		this.selectedOrgUnit = selectedOrgUnit;
	}

}
