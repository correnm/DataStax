package src.com.g2.types;

import java.util.List;

import com.datastax.driver.core.LocalDate;

public class Software {

	private String part;
	private String vendor;
	private String product;
	private String version;
	private String cpe_update;
	private String edition;
	private String language;
	private String sw_edition;
	private String target_hw;
	private String target_sw;
	private String other;
	private String cpe_id;
	private LocalDate end_of_life_date;
	private LocalDate end_of_sale;
	private LocalDate end_of_support;
	private List<Hardware_Relationships> installed_on_hardware;
	
	//>>>>>>>>>>>>>> part <<<<<<<<<<<<<<<<
	public void setPart(String part){
		this.part = part.replaceAll("/", "");
	}
	public String getPart(){
		return part;
	}
	//>>>>>>>>>>>>>> vendor <<<<<<<<<<<<<<<<
	public void setVendor(String vendor){
		this.vendor =vendor;
	}
	public String getVendor(){
		return vendor;
	}
	//>>>>>>>>>>>>>> product <<<<<<<<<<<<<<<<
	public void setProduct(String product){
		this.product =product;
	}
	public String getProduct(){
		return product;
	}
	//>>>>>>>>>>>>>> version <<<<<<<<<<<<<<<<
	public void setVersion(String version){
		this.version =version;
	}
	public String getVersion(){
		return version;
	}
	//>>>>>>>>>>>>>> cpe_update <<<<<<<<<<<<<<<<
	public void setCpe_update(String cpe_update){
		this.cpe_update =cpe_update;
	}
	public String getCpe_update(){
		return cpe_update;
	}
	//>>>>>>>>>>>>>> edition <<<<<<<<<<<<<<<<
	public void setEdition(String edition){
		this.edition =edition;
	}
	public String getEdition(){
		return edition;
	}
	//>>>>>>>>>>>>>> language <<<<<<<<<<<<<<<<
	public void setLanguage(String language){
		this.language =language;
	}
	public String getLanguage(){
		return language;
	}
	//>>>>>>>>>>>>>> sw_edition <<<<<<<<<<<<<<<<
	public void setSw_edition(String sw_edition){
		this.sw_edition =sw_edition;
	}
	public String getSw_edition(){
		return sw_edition;
	}
	//>>>>>>>>>>>>>> target_hw <<<<<<<<<<<<<<<<
	public void setTarget_hw(String target_hw){
		this.target_hw =target_hw;
	}
	public String getTarget_hw(){
		return target_hw;
	}
	//>>>>>>>>>>>>>> target_sw <<<<<<<<<<<<<<<<
	public void setTarget_sw(String target_sw){
		this.target_sw =target_sw;
	}
	public String getTarget_sw(){
		return target_sw;
	}
	//>>>>>>>>>>>>>> other <<<<<<<<<<<<<<<<
	public void setOther(String other){
		this.other =other;
	}
	public String getOther(){
		return other;
	}
	//>>>>>>>>>>>>>> cpe_id <<<<<<<<<<<<<<<<
	public void setCpe_id(String cpe_id){
		this.cpe_id =cpe_id;
	}
	public String getCpe_id(){
		return cpe_id;
	}
	//>>>>>>>>>>>>>> end_of_life_date <<<<<<<<<<<<<<<<
	public void setEnd_of_life_date(LocalDate end_of_life_date){
		this.end_of_life_date =end_of_life_date;
	}
	public LocalDate getEnd_of_life_date(){
		return end_of_life_date;
	}
	//>>>>>>>>>>>>>> end_of_sale <<<<<<<<<<<<<<<<
	public void setEnd_of_sale(LocalDate end_of_sale){
		this.end_of_sale =end_of_sale;
	}
	public LocalDate getEnd_of_sale(){
		return end_of_sale;
	}
	//>>>>>>>>>>>>>> end_of_support <<<<<<<<<<<<<<<<
	public void setEnd_of_support(LocalDate end_of_support){
		this.end_of_support =end_of_support;
	}
	public LocalDate getEnd_of_support(){
		return end_of_support;
	}
	//>>>>>>>>>>>>>> installed_on_hardware <<<<<<<<<<<<<<<<
	public void setInstalled_on_hardware(List<Hardware_Relationships> installed_on_hardware){
		this.installed_on_hardware =installed_on_hardware;
	}
	public List<Hardware_Relationships> getInstalled_on_hardware(){
		return installed_on_hardware;
	}
	
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append(part);
		buff.append(", ");
		buff.append(vendor);
		buff.append(", ");
		buff.append(product);
		buff.append(", ");
		buff.append(version);
		buff.append(", ");
		buff.append(cpe_update);
		buff.append(", ");
		buff.append(edition);
		buff.append(", ");
		buff.append(language);
		buff.append(", ");
		buff.append(sw_edition);
		buff.append(", ");
		buff.append(target_hw);
		buff.append(", ");
		buff.append(target_sw);
		buff.append(", ");
		buff.append(other);
		buff.append(", ");
		buff.append(end_of_life_date);
		buff.append(", ");
		buff.append(end_of_sale);
		buff.append(", ");
		buff.append(end_of_support);
		
		return buff.toString();
	}
	
	
}
