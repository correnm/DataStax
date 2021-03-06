package src.com.g2.types;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Frozen;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

@Table(name="hardware")
public class Hardware {

	private UUID site_id;

	private String ip_subnet_or_building;

	private UUID internal_system_id;
	
	private LocalDate archive_date;
	String asset_type;
	String asset_visibility;
	Audit_Upsert audit_upsert;
	List<UUID> business_process_ids;
	List<Connected_Elements> connected_elements;
	String cpe_id;
	Boolean crown_jewel;
	List<Cvss_Scores> cvss_scores;
	LocalDate end_of_life_date;
	LocalDate end_of_sale;
	LocalDate end_of_support_date;
	String host_name;
	LocalDate import_date;
	List<Software_UDT> installed_software;
	String ip_address;
	String mac_address;
	BigDecimal node_impact_value;
	String operating_system;
	String os_general;
	String part;
	String product;
	Boolean reportable_flag = true;
	Date run_datetime;
	String system_type;
	String vendor;
	String vendor_stencil_icon;
	String version;
	String version_update;
	int vulnerability_count;
	
	public Hardware(){
		
	}
	
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
	//>>>>>>>>>>>>>> asset_type <<<<<<<<<<<<<<<<
	public void setAsset_type(String asset_type){
		this.asset_type = asset_type;	
	}
	public String getAsset_type(){
		return asset_type;
	}
	//>>>>>>>>>>>>>> asset_type <<<<<<<<<<<<<<<<
	public void setAsset_visibility(String asset_visibility){
		this.asset_visibility = asset_visibility;	
	}
	public String getAsset_visibility(){
		return asset_visibility;
	}
	//>>>>>>>>>>>>>> audit_upsert <<<<<<<<<<<<<<<<
//	public void setAudit_upsert(Date datechanged, String changedbyusername){
//		this.datechanged = datechanged;
//		this.changedbyusername = changedbyusername;
//	}
	
	public void setAudit_upsert(Audit_Upsert audit_upsert){
		this.audit_upsert = audit_upsert;
	}

	public Audit_Upsert getAudit_upsert(){
		return audit_upsert;
	}
	//>>>>>>>>>>>>>> business_processes <<<<<<<<<<<<<<<<
	public void setBusiness_processes(List<UUID> business_process_ids){
		this.business_process_ids = business_process_ids;	
	}
	public List<UUID> getBusiness_processes(){
		return business_process_ids;
	}
	//>>>>>>>>>>>>>> connected_elements <<<<<<<<<<<<<<<<
	public void setConnected_elements(List<Connected_Elements> connected_elements){
		this.connected_elements = connected_elements ;
	}
	public List<Connected_Elements> getConnected_Elements(){
		return connected_elements;
	}
	//>>>>>>>>>>>>>> cpe_id <<<<<<<<<<<<<<<<
	public void setCpe_id(String cpe_id){
		this.cpe_id = cpe_id;	
	}
	public String getCpe_id(){
		return cpe_id;
	}
	//>>>>>>>>>>>>>> crown_jewel <<<<<<<<<<<<<<<<
	public void setCrown_jewel(boolean crown_jewel){
		this.crown_jewel = crown_jewel;	
	}
	public boolean getCrown_jewel(){
		return crown_jewel;
	}
	//>>>>>>>>>>>>>> cvss_scores <<<<<<<<<<<<<<<<
	public void setCvss_scores(List<Cvss_Scores> cvss_scores){
		this.cvss_scores = cvss_scores;	
	}
	public List<Cvss_Scores> getCvss_scores(){
		return cvss_scores;
	}
	//>>>>>>>>>>>>>> end_of_life_date <<<<<<<<<<<<<<<<
	public void setEnd_of_life_date(LocalDate end_of_life_date){
		this.end_of_life_date = end_of_life_date;	
	}
	public LocalDate getEnd_of_life_date(){
		return end_of_life_date;
	}
	//>>>>>>>>>>>>>> end_of_sale <<<<<<<<<<<<<<<<
	public void setEnd_of_sale(LocalDate end_of_sale){
		this.end_of_sale = end_of_sale;	
	}
	public LocalDate getEnd_of_sale(){
		return end_of_sale;
	}
	//>>>>>>>>>>>>>> end_of_support_date <<<<<<<<<<<<<<<<
	public void setEnd_of_support_date(LocalDate end_of_support_date){
		this.end_of_support_date = end_of_support_date;	
	}
	public LocalDate getEnd_of_support_date(){
		return end_of_support_date;
	}
	//>>>>>>>>>>>>>> host_name <<<<<<<<<<<<<<<<
	public void setHost_name(String host_name){
		this.host_name = host_name;	
	}
	public String getHost_name(){
		return host_name;
	}
	//>>>>>>>>>>>>>> import_date <<<<<<<<<<<<<<<<
	public void setImport_date(LocalDate import_date){
		this.import_date = import_date;	
	}
	public LocalDate getImport_date(){
		return import_date;
	}
	//>>>>>>>>>>>>>> installed_software <<<<<<<<<<<<<<<<
	public void setInstalled_software(List<Software_UDT> installed_software){
		this.installed_software = installed_software;	
	}
	public List<Software_UDT> getInstalled_software(){
		return installed_software;
	}	
	//>>>>>>>>>>>>>> ip_address <<<<<<<<<<<<<<<<
	public void setIp_address(String ip_address){
		this.ip_address = ip_address;	
	}
	public String getIp_address(){
		return ip_address;
	}
	//>>>>>>>>>>>>>> mac_address <<<<<<<<<<<<<<<<
	public void setMac_address(String mac_address){
		this.mac_address = mac_address;	
	}
	public String getMac_address(){
		return mac_address;
	}
	//>>>>>>>>>>>>>> neighbor_impact_value <<<<<<<<<<<<<<<<
	public void setNode_impact_value(BigDecimal node_impact_value){
		this.node_impact_value = node_impact_value;	
	}
	public BigDecimal getNode_impact_value(){
		return node_impact_value;
	}
	//>>>>>>>>>>>>>> operating_system <<<<<<<<<<<<<<<<
	public void setOperating_system(String operating_system){
		this.operating_system = operating_system;	
	}
	public String getOperating_system(){
		return operating_system;
	}
	//>>>>>>>>>>>>>> os_general <<<<<<<<<<<<<<<<
	public void setOs_general(String os_general){
		this.os_general = os_general;	
	}
	public String getOs_general(){
		return os_general;
	}
	//>>>>>>>>>>>>>> part <<<<<<<<<<<<<<<<
	public void setPart(String part){
		this.part = part;	
	}
	public String getPart(){
		return part;
	}
	//>>>>>>>>>>>>>> product <<<<<<<<<<<<<<<<
	public void setProduct(String product){
		this.product = product;	
	}
	public String getProduct(){
		return product;
	}
	//>>>>>>>>>>>>>> reportable_flag <<<<<<<<<<<<<<<<
	public void setReportable_flag(boolean reportable_flag){
		this.reportable_flag = reportable_flag;	
	}
	public boolean getReportable_flag(){
		return reportable_flag;
	}
	//>>>>>>>>>>>>>> run_date <<<<<<<<<<<<<<<<
	public void setRun_datetime(Date run_datetime){
		this.run_datetime = run_datetime;	
	}
	public Date getRun_datetime(){
		return run_datetime;
	}
	//>>>>>>>>>>>>>> system_type <<<<<<<<<<<<<<<<
	public void setSystem_type(String system_type){
		this.system_type = system_type;	
	}
	public String getSystem_type(){
		return system_type;
	}
	//>>>>>>>>>>>>>> vendor <<<<<<<<<<<<<<<<
	public void setVendor(String vendor){
		this.vendor = vendor;
	}
	public String getVendor(){
		return vendor;
	}
	//>>>>>>>>>>>>>> vendor_stencil_icon <<<<<<<<<<<<<<<<
	public void setVendor_stencil_icon(String vendor_stencil_icon){
		this.vendor_stencil_icon = vendor_stencil_icon;
	}
	public String getVendor_stencil_icon(){
		return vendor_stencil_icon;
	}
	//>>>>>>>>>>>>>> version_update <<<<<<<<<<<<<<<<
	public void setVersion_update(String version_update){
		this.version_update = version_update;
	}
	public String getVersion_update(){
		return version_update;
	}
	//>>>>>>>>>>>>>> version <<<<<<<<<<<<<<<<
	public void setVersion(String version){
		this.version = version;
	}
	public String getVersion(){
		return version;
	}
	//>>>>>>>>>>>>>> vulnerability_count <<<<<<<<<<<<<<<<
	public void setVulnerability_count(int vulnerability_count){
		this.vulnerability_count = vulnerability_count;
	}
	public int getVulnerability_count(){
		return vulnerability_count;
	}

	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append("site_id: "+site_id);
		buff.append(", ip_subnet_or_building: "+ip_subnet_or_building);
		buff.append(", internal_system_id: "+internal_system_id);
		buff.append(", asset_type: "+asset_type);
		buff.append(", asset_visibility: "+asset_visibility);
		buff.append(", business_process_ids: "+business_process_ids.size());
		buff.append(", connected_elements: "+connected_elements.size());
		buff.append(", cpe_id: "+cpe_id);
		buff.append(", crown_jewel: "+Boolean.toString(crown_jewel));
		buff.append(", cvss_scores: "+cvss_scores.size());
		buff.append(", end_of_life_date: "+end_of_life_date);
		buff.append(", end_of_support_date: "+end_of_support_date);
		buff.append(", host_name: "+host_name);
		buff.append(", import_date: "+import_date);
		buff.append(", installed_software: "+installed_software);
		buff.append(", ip_address: "+ip_address);
		buff.append(", mac_address: "+mac_address);
		buff.append(", node_impact_value: "+node_impact_value);
		buff.append(", operating_system: "+operating_system);
		buff.append(", os_general: "+os_general);
		buff.append(", part: "+part);
		buff.append(", product: "+product);
		buff.append(", reportable_flag: "+Boolean.toString(reportable_flag));
		buff.append(", run_datetime: "+run_datetime);
		buff.append(", system_type: "+system_type);
		buff.append(", vendor: "+vendor);
		buff.append(", vendor_stencil_icon: "+vendor_stencil_icon);
		buff.append(", version: "+version);
		buff.append(", version_update: "+version_update);
		buff.append(", vulnerability_count: "+vulnerability_count);
		
		return buff.toString();
		
		
		
	}
}
