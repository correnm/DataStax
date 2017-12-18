package com.g2ops.impact.urm.types;

import java.io.Serializable;

public class NavMenuItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String displayName, fileName;
	private Integer displayOrder;
	//private Boolean userRole, adminRole;
	
	public NavMenuItem (String displayName, String fileName, Integer displayOrder) {
		this.displayName = displayName;
		this.fileName = fileName;
		this.displayOrder = displayOrder;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getFileName() {
		return fileName;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

}
