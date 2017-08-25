package com.g2ops.washington.utils;

import java.io.Serializable;
import java.util.*;
import com.g2ops.washington.types.BusinessProcess;

public class BusinessProcessesByCyvar implements Serializable {

	private static final long serialVersionUID = 1L;

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
