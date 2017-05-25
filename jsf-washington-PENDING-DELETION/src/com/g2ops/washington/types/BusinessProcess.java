package com.g2ops.washington.types;

public class BusinessProcess {
		
	private String processName;
	private String recordType;
	private String numRecords;
	private String breachCost;
	private String darkWebCost;
	private String nivScore;
	private String cyvarValue;

	public BusinessProcess (String processName, String recordType, String numRecords, String breachCost, String darkWebCost, String nivScore, String cyvarValue) {
		this.processName = processName;
		this.recordType = recordType;
		this.numRecords = numRecords;
		this.breachCost = breachCost;
		this.darkWebCost = darkWebCost;
		this.nivScore = nivScore;
		this.cyvarValue = cyvarValue;
	}
	
	public String getProcessName() {
		return processName;
	}
		
	public String getRecordType() {
		return recordType;
	}
		
	public String getNumRecords() {
		return numRecords;
	}
		
	public String getBreachCost() {
		return breachCost;
	}
		
	public String getDarkWebCost() {
		return darkWebCost;
	}
		
	public String getNivScore() {
		return nivScore;
	}
		
	public String getCyvarValue() {
		return cyvarValue;
	}
		
}

