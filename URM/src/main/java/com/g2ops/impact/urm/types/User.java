package com.g2ops.impact.urm.types;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userEmail, firstName, lastName, appRoleName, defaultLensView, orgUnitName, siteName, userStatus;
	private Boolean activeUserInd;
	
	public User (String userEmail, String firstName, String lastName, String appRoleName, String defaultLensView, String orgUnitName, String siteName, Boolean activeUserInd) {

		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.appRoleName = appRoleName;
		this.defaultLensView = defaultLensView;
		this.orgUnitName = orgUnitName;
		this.siteName = siteName;
		this.activeUserInd = activeUserInd;
		if (this.activeUserInd) {
			userStatus = "active";
		} else {
			userStatus = "inactive";
		}

	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAppRoleName() {
		return appRoleName;
	}
	
	public String getDefaultLensView() {
		return defaultLensView;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public String getSiteName() {
		return siteName;
	}

	public Boolean getActiveUserInd() {
		return activeUserInd;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAppRoleName(String appRoleName) {
		this.appRoleName = appRoleName;
	}

	public void setDefaultLensView(String defaultLensView) {
		this.defaultLensView = defaultLensView;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public void setActiveUserInd(Boolean activeUserInd) {
		this.activeUserInd = activeUserInd;
	}

}
