package com.g2ops.impact.urm.types;

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

import java.io.Serializable;

public class BusinessProcessDoD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String missionName;
	private String capability;
	private String MIV;
	private String riskLikelyhood;

	public BusinessProcessDoD (String missionName, String capability, String MIV, String riskLikelyhood )
	{
		this.missionName = missionName;
		this.capability = capability;
		this.MIV = MIV;
		this.riskLikelyhood = riskLikelyhood;
	}
	
	public String getMissionName() {
		return missionName;
	}
		
	public String getCapability() {
		return capability;
	}
		
	public String getMIV() {
		return MIV;
	}
		
	public String getRiskLikelyhood() {
		return riskLikelyhood;
	}	

}

