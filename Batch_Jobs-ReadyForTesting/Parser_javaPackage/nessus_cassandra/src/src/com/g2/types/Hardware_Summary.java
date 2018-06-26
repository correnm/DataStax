package src.com.g2.types;

import java.util.UUID;

import com.datastax.driver.core.LocalDate;


public class Hardware_Summary {
	private UUID site_id;
	private String ip_subnet_or_building;
	private LocalDate month_end_date;
	private LocalDate week_start_date;
	private LocalDate day_of_week;
	private int critical_count;
	private int high_count;
	private int low_count;
	private int medium_count;
	private int patches_count;
	private int patches_critical_count;
	private int patches_high_count;
	private int patches_low_count;
	private int patches_medium_count;
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
	//>>>>>>>>>>>>>> month_end_date <<<<<<<<<<<<<<<<
	public void setMonth_end_date(LocalDate month_end_date){
		this.month_end_date = month_end_date;	
	}
	public LocalDate getMonth_end_date(){
		return (month_end_date);
	}
	//>>>>>>>>>>>>>> week_start_date <<<<<<<<<<<<<<<<
	public void setWeek_start_date(LocalDate week_start_date){
		this.week_start_date = week_start_date;	
	}
	public LocalDate getWeek_start_date(){
		return week_start_date;
	}
	//>>>>>>>>>>>>>> day_of_week <<<<<<<<<<<<<<<<
	public void setDay_of_week(LocalDate day_of_week){
		this.day_of_week = day_of_week;	
	}
	public LocalDate getDay_of_week(){
		return day_of_week;
	}
	//>>>>>>>>>>>>>> critical_count <<<<<<<<<<<<<<<<
	public void setCritical_count(int critical_count){
		this.critical_count = critical_count;	
	}
	public int getCritical_count(){
		return critical_count;
	}
	//>>>>>>>>>>>>>> high_count <<<<<<<<<<<<<<<<
	public void setHigh_count(int high_count){
		this.high_count = high_count;	
	}
	public int getHigh_count(){
		return high_count;
	}
	//>>>>>>>>>>>>>> low_count <<<<<<<<<<<<<<<<
	public void setLow_count(int low_count){
		this.low_count = low_count;	
	}
	public int getLow_count(){
		return low_count;
	}
	//>>>>>>>>>>>>>> medium_count <<<<<<<<<<<<<<<<
	public void setMeduim_count(int medium_count){
		this.medium_count = medium_count;	
	}
	public int getMedium_count(){
		return medium_count;
	}
	//>>>>>>>>>>>>>> patches_count <<<<<<<<<<<<<<<<
	public void setPatches_count(int patches_count){
		this.patches_count = patches_count;	
	}
	public int getPatches_count(){
		return patches_count;
	}
	//>>>>>>>>>>>>>> patches_critical_count <<<<<<<<<<<<<<<<
	public void setPatches_critical_count(int patches_critical_count){
		this.patches_critical_count = patches_critical_count;	
	}
	public int getPatches_critical_count(){
		return patches_critical_count;
	}
	//>>>>>>>>>>>>>> patches_high_count <<<<<<<<<<<<<<<<
	public void setPatches_high_count(int patches_high_count){
		this.patches_high_count = patches_high_count;	
	}
	public int getPatches_high_count(){
		return patches_high_count;
	}
	//>>>>>>>>>>>>>> patches_low_count <<<<<<<<<<<<<<<<
	public void setPatches_low_count(int patches_low_count){
		this.patches_low_count = patches_low_count;	
	}
	public int getPatches_low_count(){
		return patches_low_count;
	}
	//>>>>>>>>>>>>>> patches_medium_count <<<<<<<<<<<<<<<<
	public void setPatches_medium_count(int patches_medium_count){
		this.patches_medium_count = patches_medium_count;	
	}
	public int getPatches_medium_count(){
		return patches_medium_count;
	}
	//>>>>>>>>>>>>>> vulnerability_count <<<<<<<<<<<<<<<<
	public void setVulnerability_count(int vulnerability_count){
		this.vulnerability_count = vulnerability_count;
	}
	public int getVulnerability_count(){
		return vulnerability_count;
	}
	
	//****************************************************
	public static String replaceNull(String input) {
		String in;
		if (input != null)
			input.trim();
		return input == null ? "" : input;
	}
	
	public String toString(){
		StringBuffer summaryRow = new StringBuffer();
		summaryRow.append("site_id: "+ getSite_id());
		summaryRow.append(", ip_subnet_or_building: "+ getIp_subnet_or_building());
//		summaryRow.append(", day_of_week: "+ getDay_of_week());
		summaryRow.append(", month_end_date: "+ getMonth_end_date().toString());
//		summaryRow.append(", week_start_date: "+ getWeek_start_date().toString());
		summaryRow.append(", critical_count: "+ getCritical_count());
		summaryRow.append(", high_count: "+ getHigh_count());
		summaryRow.append(", mediuml_count: "+ getMedium_count());
		summaryRow.append(", low_count: "+ getLow_count());
		summaryRow.append(", patches_count: "+ getPatches_count());
		summaryRow.append(", patches_critical_count: "+ getPatches_critical_count());
		summaryRow.append(", patches_high_count: "+ getPatches_high_count());
		summaryRow.append(", patches_medium_count: "+ getPatches_medium_count());
		summaryRow.append(", patches_low_count: "+ getPatches_low_count());
		summaryRow.append(", vulnerability_count: "+ getVulnerability_count());
		
		return summaryRow.toString(); 
	}
	
}
