package com.g2ops.washington.beans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.washington.types.OrgUnit;
import com.g2ops.washington.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class OrgUnitSelectOneListboxBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<OrgUnit> orgUnits;
	public String selectedOrgUnit;
	
	public OrgUnitSelectOneListboxBean() {
		
		Session dbSession = SessionUtils.getOrgDBSession();
		ResultSet rs;
		Row row;
		Iterator<Row> iterator;
		this.orgUnits = new ArrayList<OrgUnit>();

		// execute the query
		rs = dbSession.execute("select org_unit_id, org_unit_name from organizational_units");

		iterator = rs.iterator();
		
		while (iterator.hasNext()) {
			row = iterator.next();
			this.orgUnits.add(new OrgUnit(row.getUUID("org_unit_id").toString(), row.getString("org_unit_name")));
			System.out.println("org_unit_name: " + row.getString("org_unit_name"));
		}

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
