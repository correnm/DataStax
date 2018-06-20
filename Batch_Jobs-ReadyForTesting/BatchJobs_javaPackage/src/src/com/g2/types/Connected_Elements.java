package src.com.g2.types;


import com.datastax.driver.mapping.annotations.UDT;

import java.util.List;

import com.datastax.driver.mapping.annotations.Field;

@UDT(name = "connected_elements", keyspace = "")
public class Connected_Elements {
	
	@Field(name = "destination_id")
	private String destination_id;
	
	@Field(name = "mission_support")
	private List<String> mission_support;
	
	//>>>>>>>>>>>>>> destination_id <<<<<<<<<<<<<<<<
	public void setDestination_id(String destination_id){
		this.destination_id = destination_id;
	}
	public String getDestination_id(){
		return destination_id;
	}
	//>>>>>>>>>>>>>> mission_support <<<<<<<<<<<<<<<<
	public void setMission_support(List<String> mission_support){
		this.mission_support = mission_support;
	}
	public List<String> getMission_support(){
		return mission_support;
	}
	
	
}
