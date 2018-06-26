package src.com.g2.types;

public class Cpe {

	String part;
	String vendor;
	String product;
	String version;
	String update;
	String edition;
	String language;
	String sw_edition;
	String target_sw;
	String target_hw;
	String other;
	
	public Cpe(String cpe) {
		breakdownCPE(cpe);
	}
	
	private void breakdownCPE(String cpe){
    	String[] elements = cpe.split(":");
    	try{
			setPart(elements[1].substring(elements[1].indexOf('/') + 1));
			setVendor(elements[2]);
			setProduct(elements[3]);
			setVersion(elements[4]);
			setUpdate(elements[5]);
			if (elements[6].contains("~")){
				unpackEdition(elements[6]);
			}else{
				setEdition(elements[6]);
			}
			setLanguage(elements[7]);
    	}catch(IndexOutOfBoundsException e){
    		return;
    	}
    }
    
    private void unpackEdition(String edition){
    	String[] editionElements = edition.split("~");
    	try{
	    	setEdition(editionElements[1]);
	    	setSw_edition(editionElements[2]);
	    	setTarget_sw(editionElements[3]);
	    	setTarget_hw(editionElements[4]);
	    	setOther(editionElements[5]);
    	}catch(IndexOutOfBoundsException e){
    		return;
    	}
    }
    
    private String checkSymbol(String input){

    	if (input == "-")
    		return "NA";
    	if (input == "*"|| input ==null || input == " ")
    		return "ANY";
    	if (input.length()==0)
    		return "ANY";
    	return input;
    }
    
    //>>>>>>>>>>>>>>>>>>>> part <<<<<<<<<<<<<<<<<<<<//
    public void  setPart(String part){
    	this.part = part;
    }
    public String getPart(){
    	return checkSymbol(part);
    }
    //>>>>>>>>>>>>>>>>>>>> vendor <<<<<<<<<<<<<<<<<<<<//
    public void  setVendor(String vendor){
    	this.vendor = vendor;
    }
    public String getVendor(){
    	return checkSymbol(vendor);
    }
    //>>>>>>>>>>>>>>>>>>>> product <<<<<<<<<<<<<<<<<<<<//
    public void  setProduct(String product){
    	this.product =product;
    }
    public String getProduct(){
    	return checkSymbol(product);
    }
    //>>>>>>>>>>>>>>>>>>>> version <<<<<<<<<<<<<<<<<<<<//
    public void  setVersion(String version){
    	this.version =version;
    }
    public String getVersion(){
    	return checkSymbol(version);
    }
    //>>>>>>>>>>>>>>>>>>>> update <<<<<<<<<<<<<<<<<<<<//
    public void  setUpdate(String update){
    	this.update =update;
    }
    public String getUpdate(){
    	return checkSymbol(update);
    }
    //>>>>>>>>>>>>>>>>>>>> edition <<<<<<<<<<<<<<<<<<<<//
    public void  setEdition(String edition){
    	this.edition=edition;
    }
    public String getEdition(){
    	return checkSymbol(edition);
    }
    //>>>>>>>>>>>>>>>>>>>> language <<<<<<<<<<<<<<<<<<<<//
    public void  setLanguage(String language){
    	this.language=language;
    }
    public String getLanguage(){
    	return checkSymbol(language);
    }
    //>>>>>>>>>>>>>>>>>>>> sw_edition <<<<<<<<<<<<<<<<<<<<//
    public void  setSw_edition(String sw_edition){
    	this.sw_edition=sw_edition;
    }
    public String getSw_edition(){
    	return checkSymbol(sw_edition);
    }
    //>>>>>>>>>>>>>>>>>>>> target_sw <<<<<<<<<<<<<<<<<<<<//
    public void  setTarget_sw(String target_sw){
    	this.target_sw=target_sw;
    }
    public String getTarget_sw(){
    	return checkSymbol(target_sw);
    }
    //>>>>>>>>>>>>>>>>>>>> target_hw <<<<<<<<<<<<<<<<<<<<//
    public void  setTarget_hw(String target_hw){
    	this.target_hw=target_hw;
    }
    public String getTarget_hw(){
    	return checkSymbol(target_hw);
    }
    //>>>>>>>>>>>>>>>>>>>> other <<<<<<<<<<<<<<<<<<<<//
    public void  setOther(String other){
    	this.other= other;
    }
    public String getOther(){
    	return checkSymbol(other);
    }
}
