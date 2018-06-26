package src.com.g2.types;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

@UDT(name = "software", keyspace = "")
public class Software_UDT {

	@Field(name = "cpe")
	public String cpe = new String();
	@Field(name = "title")
	public String title = new String();
	
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
