package com.g2ops.impact.urm.utils;

import java.io.Serializable;
import java.util.*;
import com.g2ops.impact.urm.types.BusinessProcessDoD;

public class BusinessProcessesByCyvarDoD implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dataTitle;
	private List<BusinessProcessDoD> businessProcesses;
	
	public BusinessProcessesByCyvarDoD (String dataTitle, BusinessProcessDoD... businessProcesses) {
		this.dataTitle = dataTitle;
		this.businessProcesses = Arrays.asList(businessProcesses);
	}

	public String getDataTitle() {
		return(dataTitle);
	}

	public List<BusinessProcessDoD> getBusinessProcesses() {
		return(businessProcesses);
	}

}
