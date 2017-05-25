package com.g2ops.washington.utils;

import java.util.*;

import com.g2ops.washington.types.BusinessApplication;

public class BusinessAppsByCyvar {

	private String dataTitle;
	private List<BusinessApplication> businessApplications;
	
	public BusinessAppsByCyvar (String dataTitle, BusinessApplication... businessApplications) {
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
