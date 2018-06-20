package src.com.g2.types;

import java.util.UUID;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

@UDT(name = "hardware_relationships", keyspace = "")
public class Hardware_Relationships {
	
	@Field(name = "site_id")
	private UUID site_id;
	
	@Field(name = "ip_subnet_or_building")
	private String ip_subnet_or_building;
	
	@Field(name = "internal_system_id")
	private UUID internal_system_id;
	
	//>>>>>>>>>>>>>> site_id <<<<<<<<<<<<<<<<
	public void setSite_id(UUID site_id){
		this.site_id = site_id;
	}
	public UUID getSite_id(){
		return site_id;
	}
	//>>>>>>>>>>>>>> ip_subnet_or_building <<<<<<<<<<<<<<<<
	public void setIp_subnet_or_building(String ip_subnet_or_building){
		this.ip_subnet_or_building = ip_subnet_or_building;
	}
	public String getIp_subnet_or_building(){
		return ip_subnet_or_building;
	}
	//>>>>>>>>>>>>>> internal_system_id <<<<<<<<<<<<<<<<
	public void setInternal_system_id(UUID internal_system_id){
		this.internal_system_id = internal_system_id;
	}
	public UUID getInternal_system_id(){
		return internal_system_id;
	}
	
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append(site_id.toString());
		buff.append(", ");
		buff.append(ip_subnet_or_building);
		buff.append(", ");
		buff.append(internal_system_id.toString());
		
		return buff.toString();
	}
}
