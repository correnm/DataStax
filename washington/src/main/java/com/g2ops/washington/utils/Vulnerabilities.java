package com.g2ops.washington.utils;

import java.util.*;
import com.g2ops.washington.types.Vulnerability;

public class Vulnerabilities {

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
