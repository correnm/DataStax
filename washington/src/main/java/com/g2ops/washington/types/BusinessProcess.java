package com.g2ops.washington.types;

import java.io.Serializable;

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
 * 14-Aug-2017		corren.mccoy		Added new column for annual revenue
 * 
 */

public class BusinessProcess implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String processName;
	private String recordType;
	private String numRecords;
	private String breachCost;
	private String darkWebCost;
	private String nivScore;
	private String cyvarValue;
	private String annualRevenue;

	public BusinessProcess (String processName, String recordType, String numRecords, 
			String breachCost, String darkWebCost, String nivScore, String cyvarValue, 
			String annualRevenue) 
	{
		this.processName = processName;
		this.recordType = recordType;
		this.numRecords = numRecords;
		this.breachCost = breachCost;
		this.darkWebCost = darkWebCost;
		this.nivScore = nivScore;
		this.cyvarValue = cyvarValue;
		this.annualRevenue = annualRevenue;
	}
	
	public String getProcessName() {
		return processName;
	}
		
	public String getRecordType() {
		return recordType;
	}
		
	public String getNumRecords() {
		return numRecords;
	}
		
	public String getBreachCost() {
		return breachCost;
	}
		
	public String getDarkWebCost() {
		return darkWebCost;
	}
		
	public String getNivScore() {
		return nivScore;
	}
		
	public String getCyvarValue() {
		return cyvarValue;
	}
	public String getAnnualRevenue() {
		return annualRevenue;
	}	
}

