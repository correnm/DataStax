package com.g2ops.impact.urm.types;

/**
 * @author Tammy Bogart

 * @date 04.10.2018
 * 
 * @purpose This is a utility class stores the connected_elements column in the hardware table that is a User-Defined type
 * 
 * 
 * Modification History
 * Date 				Author				Description
 *
 */

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.mapping.annotations.Field;

import java.io.Serializable;
import java.util.List;

@UDT(name = "connected_elements", keyspace = "dod")

public class connectedElements implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Field(name = "destination_id")
	private String destination_id;
	
	@Field(name = "mission_support")
	private List<String> mission_support;
	
	//>>>>>>>>>>>>>> getters/setters <<<<<<<<<<<<<<<<
	public void setDestination_id(String destination_id){
		this.destination_id = destination_id;
	}
	public String getDestination_id(){
		return destination_id;
	}
	public void setMission_support(List<String> mission_support){
		this.mission_support = mission_support;
	}
	public List<String> getMission_support(){
		return mission_support;
	}
}