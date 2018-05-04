package com.g2ops.impact.urm.types;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

@UDT(name = "software", keyspace = "dod")
public class Software {

	@Field(name = "cpe")
	public static String cpe;
	@Field(name = "title")
	public static String title;
	
	//>>>>>>>>>>>>>>CPE<<<<<<<<<<<<<<<<<<
	public void setCpe(String cpe){
		this.cpe = cpe;
	}
	public String getCpe(){
		return cpe;
	}
	//>>>>>>>>>>>>>>Title<<<<<<<<<<<<<<<<<<
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
}
