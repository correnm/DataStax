package com.g2ops.washington.types;

public class Site {

	private String siteCode, siteName;
	
	public Site (String siteCode, String siteName) {
		this.siteCode = siteCode;
		this.siteName = siteName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

}
