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
