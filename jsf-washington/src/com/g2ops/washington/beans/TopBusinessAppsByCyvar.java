package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.BusinessApplication;
import com.g2ops.washington.utils.BusinessAppsByCyvar;

@ManagedBean
@SessionScoped
public class TopBusinessAppsByCyvar extends BusinessAppsByCyvar {
	public TopBusinessAppsByCyvar () {
		super("POS Applications",
			new BusinessApplication("AppName1", "PCI", 500, 5, 2500, "20", 50000),
			new BusinessApplication("AppName2", "SSN", 1500, 15, 22500, "60", 120000),
			new BusinessApplication("AppName3", "PHR", 2000, 10, 20000, "50", 100000));
	}
}
