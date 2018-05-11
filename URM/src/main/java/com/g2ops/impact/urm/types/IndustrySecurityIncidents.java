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
 * 26-Apr-2018		tammy.bogart		created
 * 
 */

public class IndustrySecurityIncidents implements Serializable {
	private static final long serialVersionUID = 1L;
	

	public IndustrySecurityIncidents () {
	}

    	private double publication_year, breaches_large, breaches_small, breaches_total, breaches_unk, incidents_large, incidents_small, incidents_total, incidents_unk, probability_of_attack, sample_size; 
    	private String verizon_dbir_industry_name;
    	
    	public IndustrySecurityIncidents(double pubYear, String verDBIRIndName, double breachesLarge, double breachesSmall, double breachesTotal, double breachesUNK, double incidentsLarge, double incidentsSmall, 
    										double incidentsTotal, double incidentsUNK, double probabilityAttack, double sampleSize) {
    		
    		this.publication_year = pubYear;    	    
    	    this.verizon_dbir_industry_name= verDBIRIndName;
    	    this.breaches_large = breachesLarge;
    	    this.breaches_small = breachesSmall;
    	    this.breaches_total = breachesTotal;
    	    this.breaches_unk = breachesUNK;
    	    this.incidents_large = incidentsLarge;
    	    this.incidents_small = incidentsSmall;
    	    this.incidents_total = incidentsTotal;
    	    this.incidents_unk = incidentsUNK;
    	    this.probability_of_attack = probabilityAttack;
    	    this.sample_size = sampleSize;
    	}

    	
    	public static Comparator<IndustrySecurityIncidents> PubYearComparator = new Comparator<IndustrySecurityIncidents>() {
    		
    		public int compare(IndustrySecurityIncidents c1, IndustrySecurityIncidents c2) {
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

		public double getBreaches_large() {
			return breaches_large;
		}

		public void setBreaches_large(double breaches_large) {
			this.breaches_large = breaches_large;
		}

		public double getBreaches_small() {
			return breaches_small;
		}

		public void setBreaches_small(double breaches_small) {
			this.breaches_small = breaches_small;
		}

		public double getBreaches_total() {
			return breaches_total;
		}

		public void setBreaches_total(double breaches_total) {
			this.breaches_total = breaches_total;
		}

		public double getBreaches_unk() {
			return breaches_unk;
		}

		public void setBreaches_unk(double breaches_unk) {
			this.breaches_unk = breaches_unk;
		}

		public double getIncidents_large() {
			return incidents_large;
		}

		public void setIncidents_large(double incidents_large) {
			this.incidents_large = incidents_large;
		}

		public double getIncidents_small() {
			return incidents_small;
		}

		public void setIncidents_small(double incidents_small) {
			this.incidents_small = incidents_small;
		}

		public double getIncidents_total() {
			return incidents_total;
		}

		public void setIncidents_total(double incidents_total) {
			this.incidents_total = incidents_total;
		}

		public double getIncidents_unk() {
			return incidents_unk;
		}

		public void setIncidents_unk(double incidents_unk) {
			this.incidents_unk = incidents_unk;
		}

		public double getProbability_of_attack() {
			return probability_of_attack;
		}

		public void setProbability_of_attack(double probability_of_attack) {
			this.probability_of_attack = probability_of_attack;
		}

		public double getSample_size() {
			return sample_size;
		}

		public void setSample_size(double sample_size) {
			this.sample_size = sample_size;
		}

		public String getVerizon_dbir_industry_name() {
			return verizon_dbir_industry_name;
		}

		public void setVerizon_dbir_industry_name(String verizon_dbir_industry_name) {
			this.verizon_dbir_industry_name = verizon_dbir_industry_name;
		}
}

