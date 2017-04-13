package com.g2ops.washington.utils;

import java.util.*;

import com.g2ops.washington.types.BusinessApplication;

public class Dashboard {

	private String dataTitle;
	private List<BusinessApplication> businessApplications;
	
	public Dashboard (String dataTitle, BusinessApplication... businessApplications) {
		this.dataTitle = dataTitle;
		this.businessApplications = Arrays.asList(businessApplications);
	}

	public String getDataTitle() {
		return(dataTitle);
	}

	public List<BusinessApplication> getBusinessApplications() {
		return(businessApplications);
	}

}
