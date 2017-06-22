package com.g2ops.washington.types;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 		Corren McCoy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, June 2017
 * @see			http://tutorialspointexamples.com/jsf-datatable-update-row-edit-program-code-eclipse-download/
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 20-Jun-2017		corren.mccoy		Created this Java class
 * 
 */

public class BusinessPractice implements Serializable {

	private static final long serialVersionUID = 1L;

	// declare the variables which coincide with fields in the business_practice table
	private String category, businessValue;
	private BigDecimal availabilityReqCurrent, collateralDamageCurrent, confidentialityReqCurrent, integrityReqCurrent, targetDistributionCurrent;
	private boolean editable;
	
	// constructor for variable initialization. BigDecimal is the Java equivalent to the
	// decimal data type in Cassandra.
	public BusinessPractice (String category, 		String businessValue, 
			BigDecimal availabilityReqCurrent, 		BigDecimal collateralDamageCurrent, 
			BigDecimal confidentialityReqCurrent, 	BigDecimal integrityReqCurrent, 
			BigDecimal targetDistributionCurrent) {

		this.category 							= category;
		this.businessValue 						= businessValue;
		this.availabilityReqCurrent 			= availabilityReqCurrent;
		this.collateralDamageCurrent 			= collateralDamageCurrent;
		this.confidentialityReqCurrent 		  	= confidentialityReqCurrent;
		this.integrityReqCurrent 				= integrityReqCurrent;
		this.targetDistributionCurrent 			= targetDistributionCurrent;

	}

	// Setters and getters for each of the class variables that will referenced in xhtml
	public String getCategory() {
		return category;
	}

	public String getBusinessValue() {
		return businessValue;
	}

	
	public BigDecimal getAvailabilityReqCurrent() {
		return availabilityReqCurrent;
	}
	public BigDecimal getcollateralDamageCurrent() {
		return collateralDamageCurrent;
	}

	public BigDecimal getConfidentialityReqCurrent() {
		return confidentialityReqCurrent;
	}
	
	public BigDecimal getIntegrityReqCurrent() {
		return integrityReqCurrent;
	}

	public BigDecimal getTargetDistributionCurrent() {
		return targetDistributionCurrent;
	}

	public void setCategory(String category) {
		this.category= category;
	}

	public void setBusinessValue(String businessValue) {
		this.businessValue = businessValue;
	}

	public void setcollateralDamageCurrent(BigDecimal collateralDamageCurrent) {
		this.collateralDamageCurrent = collateralDamageCurrent;
	}
	
	public void setAvailabilityReqCurrent(BigDecimal availabilityReqCurrent) {
		this.availabilityReqCurrent = availabilityReqCurrent;
	}

	public void setConfidentialityReqCurrent(BigDecimal confidentialityReqCurrent) {
		this.confidentialityReqCurrent = confidentialityReqCurrent;
	}

	public void setIntegrityReqCurrent(BigDecimal integrityReqCurrent) {
		this.integrityReqCurrent = integrityReqCurrent;
	}

	public void setTargetDistributionCurrent(BigDecimal targetDistributionCurrent) {
		this.targetDistributionCurrent = targetDistributionCurrent;
	}

	public boolean isEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

} // end of class
