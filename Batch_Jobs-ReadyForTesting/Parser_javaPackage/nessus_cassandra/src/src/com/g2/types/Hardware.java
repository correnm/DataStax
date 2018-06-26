package src.com.g2.types;

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
	String asset_type;
	String asset_visibility;
	Audit_Upsert audit_upsert;
	List<UUID> business_process_ids;
	List<Connected_Elements> connected_elements;
	String cpe_id;
	Boolean crown_jewel;
	List<Cvss_Scores> cvss_scores;
	Float cyvar;
	LocalDate end_of_life_date;
	LocalDate end_of_sale;
	LocalDate end_of_support_date;
	String host_name;
	LocalDate import_date;
	List<String> installed_software;
	String ip_address;
	String ip_gateway;
	String ip_netmask;
	String mac_address;
	Float neighbor_coefficient_value;
	Float node_impact_value;
	String operating_system;
	String os_general;
	String part;
	String patch_availability;
	String product;
	Boolean reportable_flag = true;
	Date run_datetime;
	String system_type;
	String vendor;
	String vendor_stencil_icon;
	String version;
	String version_update;
	int vulnerability_count;

	@Frozen
	private List<String> mission_support;
	
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
	//>>>>>>>>>>>>>> cyvar <<<<<<<<<<<<<<<<
	public void setCyvar(Float cyvar){
		this.cyvar = cyvar;	
	}
	public Float getCyvar(){
		return cyvar;
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
	public void setInstalled_software(List<String> installed_software){
		this.installed_software = installed_software;	
	}
	public List<String> getInstalled_software(){
		return installed_software;
	}	
	//>>>>>>>>>>>>>> ip_address <<<<<<<<<<<<<<<<
	public void setIp_address(String ip_address){
		this.ip_address = ip_address;	
	}
	public String getIp_address(){
		return ip_address;
	}
	//>>>>>>>>>>>>>> ip_gateway <<<<<<<<<<<<<<<<
	public void setIp_gateway(String ip_gateway){
		this.ip_gateway = ip_gateway;	
	}
	public String getIp_gateway(){
		return ip_gateway;
	}
	//>>>>>>>>>>>>>> ip_netmask <<<<<<<<<<<<<<<<
	public void setIp_netmask(String ip_netmask){
		this.ip_netmask = ip_netmask;	
	}
	public String getIp_netmask(){
		return ip_netmask;
	}
	//>>>>>>>>>>>>>> mac_address <<<<<<<<<<<<<<<<
	public void setMac_address(String mac_address){
		this.mac_address = mac_address;	
	}
	public String getMac_address(){
		return mac_address;
	}
	//>>>>>>>>>>>>>> neighbor_coefficient_value <<<<<<<<<<<<<<<<
	public void setNeighbor_coefficient_value(Float neighbor_coefficient_value){
		this.neighbor_coefficient_value = neighbor_coefficient_value;	
	}
	public Float getNeighbor_coefficient_value(){
		return neighbor_coefficient_value;
	}
	//>>>>>>>>>>>>>> neighbor_coefficient_value <<<<<<<<<<<<<<<<
	public void setNode_impact_value(Float node_impact_value){
		this.node_impact_value = node_impact_value;	
	}
	public Float getNode_impact_value(){
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
	//>>>>>>>>>>>>>> patch_availability <<<<<<<<<<<<<<<<
	public void setPatch_availability(String patch_availability){
		this.patch_availability = patch_availability;	
	}
	public String getPatch_availability(){
		return patch_availability;
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
	public void setRun_date(Date run_datetime){
		this.run_datetime = run_datetime;	
	}
	public Date getRun_date(){
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

}
