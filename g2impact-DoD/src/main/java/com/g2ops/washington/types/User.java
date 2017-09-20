package com.g2ops.washington.types;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userEmail, firstName, lastName, appRoleName, defaultLensView;
	private Boolean systemAdministratorInd;
	
	public User (String userEmail, String firstName, String lastName, String appRoleName, String defaultLensView, Boolean systemAdministratorInd) {

		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.appRoleName = appRoleName;
		this.defaultLensView = defaultLensView;
		this.systemAdministratorInd = systemAdministratorInd;

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

	public Boolean getSystemAdministratorInd() {
		return systemAdministratorInd;
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

	public void setSystemAdministratorInd(Boolean systemAdministratorInd) {
		this.systemAdministratorInd = systemAdministratorInd;
	}

}
