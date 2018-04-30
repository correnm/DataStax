package com.g2ops.impact.urm.types;

/**
 * @author Tammy Bogart

 * @date 04.11.2018
 * 
 * @purpose This is a utility class stores the audit_insert column in the tables that is a User-Defined type
 * 
 * 
 * Modification History
 * Date 				Author				Description
 *
 */

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.mapping.annotations.Field;

import java.sql.Timestamp;


@UDT(name = "audit_upsert", keyspace="") 

public class audit_upsert {

	
	@Field(name = "datechanged")
	private Timestamp datechanged;

	@Field(name = "changedbyusername")
	private String changedbyusername;


	//>>>>>>>>>>>>>>>>>>>>>>>>getters/setters<<<<<<<<<<<<<<<<<<<<<<//
	public Timestamp getDatechanged() {		
		return datechanged;
	}

	public void setDatechanged(Timestamp datechanged) {
		this.datechanged = datechanged;
	}

	public String getChangedbyusername() {
		return changedbyusername;
	}

	public void setChangedbyusername(String changedbyusername) {
		this.changedbyusername = changedbyusername;
	}
}