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
 * 24-Apr-2018		tammy.bogart		created
 * 
 */

public class IndustryBreachCosts implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private double publication_year, direct_cost_pct, direct_per_capita_cost, indirect_cost_pct, indirect_per_capita_cost, per_capita_cost;
    private String country_name, industry_name, verizon_dbir_industry_name;


	public IndustryBreachCosts (double pubYr, double dirCostPCT, double dirPerCapCost, double indirCostPCT, double indirPerCapCost, double perCapCost, 
								String cName, String indName, String verDBIRIndName) {
	
		this.publication_year = pubYr;
		this.direct_cost_pct = dirCostPCT;
		this.direct_per_capita_cost = dirPerCapCost;
		this.indirect_cost_pct = indirCostPCT;
		this.indirect_per_capita_cost = indirPerCapCost;
		this.per_capita_cost = perCapCost;
		this.country_name = cName;
		this.industry_name = indName;
		this.verizon_dbir_industry_name = verDBIRIndName;
	}
	
	public static Comparator<IndustryBreachCosts> PubYearComparator = new Comparator<IndustryBreachCosts>() {
		
		public int compare(IndustryBreachCosts c1, IndustryBreachCosts c2) {
			Double pubYear1 = c1.getPublication_year();
			Double pubYear2 = c2.getPublication_year();
			
			//descending order
			//return pubYear2.compareTo(pubYear1);
			return pubYear2.intValue() - pubYear1.intValue();
		}
	};
	
//>>>>>>>>>>>>>>>>>>>>>>>>GETTERS/SETTERS<<<<<<<<<<<<<<<<<<<<<<<<//

	public double getPublication_year() {
		return publication_year;
	}


	public void setPublication_year(double publication_year) {
		this.publication_year = publication_year;
	}


	public double getDirect_cost_pct() {
		return direct_cost_pct;
	}


	public void setDirect_cost_pct(double direct_cost_pct) {
		this.direct_cost_pct = direct_cost_pct;
	}


	public double getDirect_per_capita_cost() {
		return direct_per_capita_cost;
	}


	public void setDirect_per_capita_cost(double direct_per_capita_cost) {
		this.direct_per_capita_cost = direct_per_capita_cost;
	}


	public double getIndirect_cost_pct() {
		return indirect_cost_pct;
	}


	public void setIndirect_cost_pct(double indirect_cost_pct) {
		this.indirect_cost_pct = indirect_cost_pct;
	}


	public double getIndirect_per_capita_cost() {
		return indirect_per_capita_cost;
	}


	public void setIndirect_per_capita_cost(double indirect_per_capita_cost) {
		this.indirect_per_capita_cost = indirect_per_capita_cost;
	}


	public double getPer_capita_cost() {
		return per_capita_cost;
	}


	public void setPer_capita_cost(double per_capita_cost) {
		this.per_capita_cost = per_capita_cost;
	}


	public String getCountry_name() {
		return country_name;
	}


	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}


	public String getIndustry_name() {
		return industry_name;
	}


	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}


	public String getVerizon_dbir_industry_name() {
		return verizon_dbir_industry_name;
	}


	public void setVerizon_dbir_industry_name(String verizon_dbir_industry_name) {
		this.verizon_dbir_industry_name = verizon_dbir_industry_name;
	}
}

