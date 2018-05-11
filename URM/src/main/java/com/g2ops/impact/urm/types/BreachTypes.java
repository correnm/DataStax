package com.g2ops.impact.urm.types;

import java.io.Serializable;
import java.util.Comparator;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, April 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 23-Apr-2018		tammy.bogart		created
 * 
 */

public class BreachTypes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Double publication_year, distribution_pct, per_capita_cost;
	private String country_name, breach_type;

	public BreachTypes (double pubYear, String cName, String bType, double distPct, double perCapita) {
		this.publication_year = pubYear;
		this.country_name = cName;
		this.breach_type = bType;
		this.distribution_pct = distPct;
		this.per_capita_cost = perCapita;
	}

   	public static Comparator<BreachTypes> PubYearComparator = new Comparator<BreachTypes>() {
		
		public int compare(BreachTypes c1, BreachTypes c2) {
			Double pubYear1 = c1.getPublication_year();
			Double pubYear2 = c2.getPublication_year();
			
			//descending order
			//return pubYear2.compareTo(pubYear1);
			return pubYear2.intValue() - pubYear1.intValue();
		}
	};

//>>>>>>>>>>>>>>>>>>>>>>>>GETTERS/SETTERS<<<<<<<<<<<<<<<<<<<<<<<<//

	public Double getPublication_year() {
		return publication_year;
	}

	public String getCountry_name() {
		return country_name;
	}

	public String getBreach_type() {
		return breach_type;
	}

	public Double getDistribution_pct() {
		return distribution_pct;
	}

	public Double getPer_capita_cost() {
		return per_capita_cost;
	}


	public void setPublication_year(Double publication_year) {
		this.publication_year = publication_year;
	}


	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}


	public void setBreach_type(String breach_type) {
		this.breach_type = breach_type;
	}


	public void setDistribution_pct(Double distribution_pct) {
		this.distribution_pct = distribution_pct;
	}


	public void setPer_capita_cost(Double per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}

	
}

