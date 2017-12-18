package com.g2ops.impact.urm.utils;

import java.io.Serializable;
import java.util.*;
import com.g2ops.impact.urm.types.Vulnerability;

public class Vulnerabilities implements Serializable {

	private static final long serialVersionUID = 1L;

	private String dataTitle;
	private List<Vulnerability> vulnerabilities;
	
	public Vulnerabilities (String dataTitle, Vulnerability... vulnerabilities) {
		this.dataTitle = dataTitle;
		this.vulnerabilities = Arrays.asList(vulnerabilities);
	}

	public String getDataTitle() {
		return(dataTitle);
	}

	public List<Vulnerability> getVulnerabilities() {
		return(vulnerabilities);
	}

}
