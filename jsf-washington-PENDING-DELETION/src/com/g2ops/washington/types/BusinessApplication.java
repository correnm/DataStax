package com.g2ops.washington.types;

public class BusinessApplication {
		
	private String appName;
	private String recordType;
	private Integer numRecords;
	private Integer breachCost;
	private Integer dw;
	private String nivScore;
	private Integer cyvarValue;

	public BusinessApplication (String appName, String recordType, Integer numRecords, Integer breachCost, Integer dw, String nivScore, Integer cyvarValue) {
		this.appName = appName;
		this.recordType = recordType;
		this.numRecords = numRecords;
		this.breachCost = breachCost;
		this.dw = dw;
		this.nivScore = nivScore;
		this.cyvarValue = cyvarValue;
	}
	
	public String getAppName() {
		return appName;
	}
		
	public String getRecordType() {
		return recordType;
	}
		
	public Integer getNumRecords() {
		return numRecords;
	}
		
	public Integer getBreachCost() {
		return breachCost;
	}
		
	public Integer getDw() {
		return dw;
	}
		
	public String getNivScore() {
		return nivScore;
	}
		
	public Integer getCyvarValue() {
		return cyvarValue;
	}
		
}

