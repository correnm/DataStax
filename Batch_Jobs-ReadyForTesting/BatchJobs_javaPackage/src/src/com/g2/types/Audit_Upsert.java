package src.com.g2.types;

import java.util.Date;
import java.util.List;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;


@UDT(name = "audit_upsert", keyspace = "")
public class Audit_Upsert {
	
	@Field(name = "datechanged")
	private Date datechanged;// = toUnixTimestamp(now());
	
	@Field(name = "changedbyusername")
	private String changedbyusername;
	
	//>>>>>>>>>>>>>> destination_id <<<<<<<<<<<<<<<<
	public void setDatechanged(Date datechanged){
		this.datechanged = datechanged;
	}
	public Date getDatechanged(){
		return datechanged;
	}
	//>>>>>>>>>>>>>> mission_support <<<<<<<<<<<<<<<<
	public void setChangedbyusername(String changedbyusername){
		this.changedbyusername = changedbyusername;
	}
	public String getChangedbyusername(){
		return changedbyusername;
	}
	
	
}

//
//package com.g2ops.impact.urm.types;
//
///**
// * @author Tammy Bogart
//
// * @date 04.11.2018
// * 
// * @purpose This is a utility class stores the audit_insert column in the tables that is a User-Defined type
// * 
// * 
// * Modification History
// * Date 				Author				Description
// *
// */
//
//import com.datastax.driver.mapping.annotations.UDT;
//import com.datastax.driver.mapping.annotations.Field;
//
//import java.util.Date;
//
//
//@UDT(name = "audit_upsert", keyspace="dod") 
//
//public class Audit_Upsert {
//
//	
//	@Field(name = "datechanged")
//	private Date datechanged;
//
//	@Field(name = "changedbyusername")
//	private String changedbyusername;
//
//
//	//>>>>>>>>>>>>>>>>>>>>>>>>getters/setters<<<<<<<<<<<<<<<<<<<<<<//
//	public Date getDatechanged() {		
//		return datechanged;
//	}
//
//	public void setDatechanged(Date datechanged) {
//		this.datechanged = datechanged;
//	}
//
//	public String getChangedbyusername() {
//		return changedbyusername;
//	}
//
//	public void setChangedbyusername(String changedbyusername) {
//		this.changedbyusername = changedbyusername;
//	}
//}
