package com.g2ops.impact.urm.types;

import java.io.Serializable;

public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;

	private String organizationName, keyspaceName, userName, encryptedPassword, countryName, industry;
	
	public Organization (String organizationName, String keyspaceName, String userName, String encryptedPassword, String countryName, String industry) {
		this.organizationName = organizationName;
		this.keyspaceName = keyspaceName;
		this.userName = userName;
		this.encryptedPassword = encryptedPassword;
		this.countryName = countryName;
		this.industry = industry;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public String getKeyspaceName() {
		return keyspaceName;
	}

	public String getUserName() {
		return userName;
	}

	public String getEncryptedPassword() {
		return "";
	}

	public String getCountryName() {
		return countryName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setKeyspaceName(String keyspaceName) {
		this.keyspaceName = keyspaceName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}
