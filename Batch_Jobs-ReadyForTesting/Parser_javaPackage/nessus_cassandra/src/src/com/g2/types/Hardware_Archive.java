package src.com.g2.types;

import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Table;

@Table(name="hardware_archive")
public class Hardware_Archive {
	
	private UUID site_id;
	private String ip_subnet_or_building;
	private UUID internal_system_id;
	private LocalDate archive_date;
	private List<Cvss_Scores> cvss_scores;
	private int vulnerability_count;
	
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
	//>>>>>>>>>>>>>> archive_date <<<<<<<<<<<<<<<<
	public void setArchive_date(LocalDate archive_date){
		this.archive_date = archive_date;	
	}
	public LocalDate getArchive_date(){
		return archive_date;
	}
	//>>>>>>>>>>>>>> cvss_scores <<<<<<<<<<<<<<<<
	public void setCvss_scores(List<Cvss_Scores> cvss_scores){
		this.cvss_scores = cvss_scores;	
	}
	public List<Cvss_Scores> getCvss_scores(){
		return cvss_scores;
	}
	//>>>>>>>>>>>>>> vulnerability_count <<<<<<<<<<<<<<<<
	public void setVulnerability_count(int vulnerability_count){
		this.vulnerability_count = vulnerability_count;
	}
	public int getVulnerability_count(){
		return vulnerability_count;
	}
}
