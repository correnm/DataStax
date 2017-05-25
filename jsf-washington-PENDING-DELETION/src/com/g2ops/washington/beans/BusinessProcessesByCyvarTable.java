package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.BusinessProcess;
import com.g2ops.washington.utils.BusinessProcessesByCyvar;

@ManagedBean
@SessionScoped
public class BusinessProcessesByCyvarTable extends BusinessProcessesByCyvar {
	public BusinessProcessesByCyvarTable () {
		super("Business Process CyVaR Details",
			new BusinessProcess("EMR", "PHI", "53,000,000", "$209", "$58", "8.2", "$22,000,000"),
			new BusinessProcess("FIN", "PII,PCI", "15,000 | 27,000,000", "$197 | $148", "$12 | $6", "6.1", "$41,000,000"),
			new BusinessProcess("LAB1", "PHI,PII", "28,000,000 | 36,000,000", "$209 | $197", "$58 | $12", "5.4", "$19,800,000"));
	}
}
