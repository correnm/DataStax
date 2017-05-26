package com.g2ops.washington.types;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String orgKeyspace, userName, firstName, lastName, appRoleName, defaultLensView;
	private Boolean systemAdministratorInd;
	
	public User (String orgKeyspace, String userName, String firstName, String lastName, String appRoleName, String defaultLensView, Boolean systemAdministratorInd) {

		this.orgKeyspace = orgKeyspace;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.appRoleName = appRoleName;
		this.defaultLensView = defaultLensView;
		this.systemAdministratorInd = systemAdministratorInd;

	}

	public String getOrgKeyspace() {
		return orgKeyspace;
	}

	public String getUserName() {
		return userName;
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

	public Boolean getSystemAdministratorInd() {
		return systemAdministratorInd;
	}

	public void setOrgKeyspace(String orgKeyspace) {
		this.orgKeyspace = orgKeyspace;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public void setSystemAdministratorInd(Boolean systemAdministratorInd) {
		this.systemAdministratorInd = systemAdministratorInd;
	}

}
