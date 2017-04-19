package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.Vulnerability;
import com.g2ops.washington.utils.Vulnerabilities;

@ManagedBean
@SessionScoped
public class VulnerabilitiesTable extends Vulnerabilities {
	public VulnerabilitiesTable () {
		super("Vulnerabilities Detail",
			new Vulnerability(210787854, "Windows SMB Session Intercept", "9.3", "6.4 | 6.4", "EMR", "No", "PHI"),
			new Vulnerability(201789342, "SSL Man-in-the-Middle Attack", "8.3", "5.9 | 8.5", "FIN, Payroll", "http://link.com/topatch", "PII, PCI"),
			new Vulnerability(201683421, "HTTPD Buffer Overflow", "7.5", "4.3 | 5.1", "EMR, FIN, Payroll", "https://www.patch.com/13569", "PII, PHI, PCI"));
	}
}
