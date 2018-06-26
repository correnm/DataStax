package com.g2ops.impact.urm.types;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.mapping.annotations.Field;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, June 2018
 * @see			<a href="URL#value">label</a>
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 25-June-2018	tammy.bogart		Created this Java class
 * 
 */

public class SecurityControlRatingsList {
	
	//declare variables to display hosts data
	
	private BigDecimal score;
	private String category, description, costType;	
	private boolean applyIC, impactsRS, editable;	
	
	// constructor for variable initialization. 
	
	public SecurityControlRatingsList (BigDecimal score, String category, String description, String costType, Boolean applyIC, Boolean impactsRS) {
		this.score = score;
		this.category = category;
		this.description = description;
		this.costType = costType;
		this.applyIC = applyIC;
		this.impactsRS = impactsRS;
		this.editable = false;
				
	}

	public boolean edit() {
		System.out.println("editable: " + this.editable);
		if (this.isEditable()) {
			this.editable = false;
		} else {
			this.editable = true;
		}
		
		return this.editable;
	}
///////////////getters/setters///////////////////////////////

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public boolean isApplyIC() {
		return applyIC;
	}

	public void setApplyIC(boolean applyIC) {
		this.applyIC = applyIC;
	}

	public boolean isImpactsRS() {
		return impactsRS;
	}

	public void setImpactsRS(boolean impactsRS) {
		this.impactsRS = impactsRS;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
