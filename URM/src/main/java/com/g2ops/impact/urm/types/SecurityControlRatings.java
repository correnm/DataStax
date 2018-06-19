package com.g2ops.impact.urm.types;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 10-May-2018		tammy.bogart		created
 * 
 */

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.mapping.annotations.Field;
import java.io.Serializable;
import java.math.BigDecimal;


@UDT(name = "security_control_ratings", keyspace="appl_auth")

public class SecurityControlRatings implements Serializable {

	private static final long serialVersionUID = 1L;

	@Field(name = "score") 
		private BigDecimal score;
	
	@Field(name = "category") 
		private String category;
	
	@Field(name = "description") 
		private String description;
	
	@Field(name = "apply_impact_cost") 
		private boolean apply_impact_cost;
	
	@Field(name = "cost_type") 
		private String cost_type;
	
	@Field(name = "impacts_resistance_strength") 
		private boolean impacts_resistance_strength;
  	
	
/////////////////////Getters-Setters/////////////////////////////////////

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

	public boolean isApply_impact_cost() {
		return apply_impact_cost;
	}

	public void setApply_impact_cost(boolean apply_impact_cost) {
		this.apply_impact_cost = apply_impact_cost;
	}

	public String getCost_type() {
		return cost_type;
	}

	public void setCost_type(String cost_type) {
		this.cost_type = cost_type;
	}

	public boolean isImpacts_resistance_strength() {
		return impacts_resistance_strength;
	}

	public void setImpacts_resistance_strength(boolean impacts_resistance_strength) {
		this.impacts_resistance_strength = impacts_resistance_strength;
	}
		
		
}