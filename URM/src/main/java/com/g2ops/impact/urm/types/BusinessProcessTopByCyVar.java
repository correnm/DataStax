package com.g2ops.impact.urm.types;

import java.math.BigDecimal;

public class BusinessProcessTopByCyVar implements Comparable<BusinessProcessTopByCyVar>{

		private String businessProcessName;
		private BigDecimal cyVarValue;

		public BusinessProcessTopByCyVar(String businessProcessName, BigDecimal cyVarValue) {
			super();
			this.businessProcessName = businessProcessName;
			this.cyVarValue = cyVarValue;
		}

		public String getBusinessProcessName() {
			return businessProcessName;
		}
		public void setBusinessProcessName(String businessProcessName) {
			this.businessProcessName = businessProcessName;
		}
		public BigDecimal getCyVarValue() {
			return cyVarValue;
		}
		public void setCyVarValue(BigDecimal cyVarValue) {
			this.cyVarValue = cyVarValue;
		}

		public int compareTo(BusinessProcessTopByCyVar compareBusinessProcessTopByCyVar) {

			BigDecimal compareCyVarValue = ((BusinessProcessTopByCyVar) compareBusinessProcessTopByCyVar).getCyVarValue();

			//ascending order
			//return this.cyVarValue.subtract(compareCyVarValue).signum();

			//descending order
			return compareCyVarValue.subtract(this.cyVarValue).signum();

		}
	}
