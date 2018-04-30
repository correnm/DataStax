package com.g2ops.impact.urm.types;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @author 		Sara Prokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * @see			<a href="URL#value">label</a>
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 12-July-2017		sara.prokop		Created this Java class
 * 3/14/2018		Tammy.Bogart	Changed the data to pull the values from the Business_Value_Attribution table vs the Hardware table
 * 4/13/2018		Tammy.Bogart	Changed the name 	
 * 
 */

public class BusinessAttributionTypes {

	// declare the variables which coincide with fields in the business_practice table
	
	private String busName, busIntThreshold, busCriticality, infoClass, defBreachType;
	private BigDecimal annRevenue, recordCount, resistanceStrength, riskAppetite;
	private int annRevenueYear;
	private UUID siteID, busProcID;
	
		// constructor for variable initialization. BigDecimal is the Java equivalent to the decimal data type in Cassandra.
	
	public BusinessAttributionTypes (UUID siteID, UUID busProcID, String busName, String busIntThreshold, String busCriticality, String InfoClass, 
			String defBreachType, BigDecimal annRevenue, int annRevenueYear, BigDecimal recCount, BigDecimal resStength, BigDecimal riskAppetite) {
		
		this.siteID							=siteID;
		this.busProcID 						=busProcID;
		this.busName						= busName;
		this.busIntThreshold 			  	= busIntThreshold;
		this.busCriticality					=busCriticality;
		this.infoClass						=InfoClass;
		this.defBreachType					=defBreachType;
		this.annRevenue						=annRevenue;
		this.annRevenueYear					=annRevenueYear;
		this.recordCount					=recCount;
		this.resistanceStrength				=resStength;
		this.riskAppetite					=riskAppetite;

	}

	// Setters and getters for each of the class variables that will referenced in xhtml
	
	public UUID getsiteID() {
		return siteID;
	}
	
	public UUID getbusProcID() {
		return busProcID;
	}
	
	public String getBusName() {
		return busName;
	}
	
	public String getBusIntThreshold() {
		return busIntThreshold;
	}
	
	public String getBusCriticality() {
		return busCriticality;
	}

	public String getInfoClass() {
		return infoClass;
	}
	
	public String getDefBreachType() {
		return defBreachType;
	}

	public BigDecimal getAnnRevenue() {
		return annRevenue;
	}

	public int getAnnRevenueYear() {
		return annRevenueYear;
	}

	public BigDecimal getRecordCount() {
		return recordCount;
	}

	public BigDecimal getResistanceStrength() {
		return resistanceStrength;
	}

	public BigDecimal getRiskAppetite() {
		return riskAppetite;
	}

	/////////////////////////////setters///////////////////////////////////////

	public void setsiteID(UUID siteID) {
		this.siteID = siteID;
	}

	public void setbusProcID(UUID busProcID) {
		this.busProcID = busProcID;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}
	
	public void setBusIntThreshold(String busIntThreshold) {
		this.busIntThreshold = busIntThreshold;
	}

	public void setBusCriticality(String busCriticality) {
		this.busCriticality = busCriticality;
	}
	
	public void setInfoClass(String infoClass) {
		this.infoClass = infoClass;
	}

	public void setDefBreachType(String defBreachType) {
		this.defBreachType = defBreachType;
	}

	public void setAnnRevenue(BigDecimal annRevenue) {
		this.annRevenue = annRevenue;
	}

	public void setAnnRevenueYear(int annRevenueYear) {
		this.annRevenueYear = annRevenueYear;
	}
	
	public void setRecordCount(BigDecimal recordCount) {
		this.recordCount = recordCount;
	}

	public void setResistenceStrength(BigDecimal resistanceStrength) {
		this.resistanceStrength = resistanceStrength;
	}

	public void setRiskAppetite(BigDecimal riskAppetite) {
		this.riskAppetite = riskAppetite;
	}
}
