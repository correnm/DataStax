package com.g2ops.washington.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.BusinessProcess;
import com.g2ops.washington.utils.BusinessProcessesByCyvar;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 07-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 
 */
@ManagedBean
@SessionScoped
public class BusinessProcessesByCyvarTable extends BusinessProcessesByCyvar {
	private static final long serialVersionUID = 1L;
	public BusinessProcessesByCyvarTable () {
		super("Business Process CyVaR Details",
				new BusinessProcess("Critical Mission 6",				"PHI","11",		"$388","$3.50","3.3","Administrative",		"11.9%"),
				new BusinessProcess("Critical Mission 5",				"PHI","9",		"$388","$3.50","3.7","Mission Essential",	"6.7%"), 
				new BusinessProcess("Critical Mission 1",	 	 		"PHI","519",	"$388","$3.50","9.3","Mission Critical",	"14.8%"),
				new BusinessProcess("Critical Mission 4",				"PHI","88",		"$388","$3.50","4.8","Mission Essential",	"21.9%"),
				new BusinessProcess("Critical Mission 2",				"PHI","52",		"$388","$3.50","6.3","Mission Essential",	"17.4%"),
				new BusinessProcess("Critical Mission 7",				"PHI","52",		"$388","$3.50","2.3","Administrative",		"12.1%"),
				new BusinessProcess("Critical Mission 8",				"PHI","52",		"$388","$3.50","2.3","Administrative",		"11.4%"),
				new BusinessProcess("Critical Mission 3",				"PII","1,157",	"$388","$0.75","4.9","Mission Essential",	"5.8%")
				);
	}
}
