package com.g2ops.impact.urm.types;

import java.math.BigDecimal;

public class BusinessProcessCyVar implements Comparable<BusinessProcessCyVar>{

		private String businessProcessName;
		private BigDecimal annualRevenue;
		private BigDecimal cyVarValue;
		private BigDecimal BPIV;

		public BusinessProcessCyVar(String businessProcessName, BigDecimal annualRevenue, BigDecimal cyVarValue, BigDecimal BPIV) {
			super();
			this.businessProcessName = businessProcessName;
			this.annualRevenue = annualRevenue;
			this.cyVarValue = cyVarValue;
			this.BPIV = BPIV;
		}

		public String getBusinessProcessName() {
			return businessProcessName;
		}
		public void setBusinessProcessName(String businessProcessName) {
			this.businessProcessName = businessProcessName;
		}
		public BigDecimal getAnnualRevenue() {
			return annualRevenue;
		}
		public void setAnnualRevenue(BigDecimal annualRevenue) {
			this.annualRevenue = annualRevenue;
		}
		public BigDecimal getCyVarValue() {
			return cyVarValue;
		}
		public void setCyVarValue(BigDecimal cyVarValue) {
			this.cyVarValue = cyVarValue;
		}
		public BigDecimal getBPIV() {
			return BPIV;
		}
		public void setBPIV(BigDecimal BPIV) {
			this.BPIV = BPIV;
		}


		public int compareTo(BusinessProcessCyVar compareBusinessProcessCyVar) {

			BigDecimal compareCyVarValue = ((BusinessProcessCyVar) compareBusinessProcessCyVar).getCyVarValue();

			//ascending order
			//return this.cyVarValue.subtract(compareCyVarValue).signum();

			//descending order
			return compareCyVarValue.subtract(this.cyVarValue).signum();

		}
	}
