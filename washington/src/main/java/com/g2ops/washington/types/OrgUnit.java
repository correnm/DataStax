package com.g2ops.washington.types;

public class OrgUnit {

	private String orgUnitCode, orgUnitName;
	
	public OrgUnit (String orgUnitCode, String orgUnitName) {
		this.orgUnitCode = orgUnitCode;
		this.orgUnitName = orgUnitName;
	}

	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}
	
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

}
