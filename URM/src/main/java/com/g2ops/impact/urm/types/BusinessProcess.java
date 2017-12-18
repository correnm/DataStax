package com.g2ops.impact.urm.types;

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
	private String annualRevenue;
	private String BPIV;
	private String minELV;
	private String maxELV;
	private String CYVAR;
	private String riskAppetite;
	private String maxBreachLoss;

	public BusinessProcess (String processName, String recordType, String numRecords, 
			String annualRevenue, String BPIV, String minELV, String maxELV, String CYVAR,
			String riskAppetite, String maxBreachLoss )
	{
		this.processName = processName;
		this.recordType = recordType;
		this.numRecords = numRecords;
		this.annualRevenue = annualRevenue;
		this.BPIV = BPIV;
		this.minELV = minELV;
		this.maxELV = maxELV;
		this.CYVAR = CYVAR;
		this.riskAppetite = riskAppetite;
		this.maxBreachLoss = maxBreachLoss;
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
		
	public String getAnnualRevenue() {
		return annualRevenue;
	}	

	public String getBPIV() {
		return BPIV;
	}
		
	public String getMinELV() {
		return minELV;
	}
		
	public String getMaxELV() {
		return maxELV;
	}
		
	public String getCYVAR() {
		return CYVAR;
	}

	public String getRiskAppetite() {
		return riskAppetite;
	}

	public String getMaxBreachLoss() {
		return maxBreachLoss;
	}
		
}

