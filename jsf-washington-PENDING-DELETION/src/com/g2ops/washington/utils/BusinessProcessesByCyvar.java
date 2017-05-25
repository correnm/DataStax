package com.g2ops.washington.utils;

import java.util.*;
import com.g2ops.washington.types.BusinessProcess;

public class BusinessProcessesByCyvar {

	private String dataTitle;
	private List<BusinessProcess> businessProcesses;
	
	public BusinessProcessesByCyvar (String dataTitle, BusinessProcess... businessProcesses) {
		this.dataTitle = dataTitle;
		this.businessProcesses = Arrays.asList(businessProcesses);
	}

	public String getDataTitle() {
		return(dataTitle);
	}

	public List<BusinessProcess> getBusinessProcesses() {
		return(businessProcesses);
	}

}
