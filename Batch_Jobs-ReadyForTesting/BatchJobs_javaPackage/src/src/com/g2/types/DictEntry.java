package src.com.g2.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sara Bergman G2 Ops, Virginia Beach, VA
 * 
 * @purpose This class stores information about each cpe within the Dictionary. This is a the java
 * domain object which represents the dat we want to parse and extract. 
 * 
 * References:
 * http://javarevisited.blogspot.com/2011/12/parse-read-xml-file-java-sax-parser.html
 * https://www.tutorialspoint.com/java_xml/java_sax_parse_document.htm
 * http://www.journaldev.com/1198/java-sax-parser-example
 * https://cpe.mitre.org/specification/
 * NIST Interagency Report 7696: http://nvlpubs.nist.gov/nistpubs/Legacy/IR/nistir7696.pdf (pages, 9, 12-13, 31)
 * CPE Specification 2.2 https://cpe.mitre.org/files/cpe-specification_2.2.pdf (8, 10-13)
 * 
 * Notes:
 * What happens after a vulnerability is identified?
 * CVE identifiers are assigned by CVE and other CVE Numbering Authorities (CNAs). The NVD receives data feeds from the 
 * CVE website and in turn performs analysis to determine impact metrics (CVSS), vulnerability types (CWE), and applicability 
 * statements (CPE), as well as other pertinent metadata. The NVD does not actively perform vulnerability testing, relying on
 * vendors and third party security researchers to provide information that is then used assign these attributes. We then 
 * perform additional research to confirm that CPEs comply with CPE specifications and include them in the official CPE dictionary. 
 * As additional information becomes available CVSS scores and configurations are subject to change.
 * From: https://nvd.nist.gov/general/faq#eeabbb01-eb9f-488d-ac31-40a8b92c1473
 * 
 * 
 * Modification History
 * Date 			Author			Description
 * 25-Oct-2017		sara.bergman	housed cpe2.2, cpe2.3, and title
 * 26-Oct-2017 		sara.bergman	parsed the cpe 2.3 into its seperate elements
 * 31-Oct-2017		sara.bergman	added the references. 	
 */ 

public class DictEntry {
/** class variables*/
	//version 2.2 naming convention 
	// cpe:/{part}:{vendor}:{product}:{version}:{update}:{edition}:{language}
	//edition can be a single value or look like: {~(edition)~(sw_edition)~(target_sw)~(target_hw)~(other)}
	// eg. "cpe:/a:acquia:mollom:6.x-2.7::~~~drupal~~"
	//<cpe-item name="cpe:/a:1024cms:1024_cms:0.7">
	private String cpe;
	
	//Human-readable title of the name. 
	//<title xml:lang="en-US">12net Login Rebuilder (login_rebuilder) for WordPress 1.0.2</title>
	private String title;
	
	//version 2.3 naming convention
	// cpe:2.3:{part}:{vendor}:{product}:{version}:{update}:{edition}:{language}:{sw_edition}:{target_sw}:{target_hw}:{other} 
	//<cpe-23:cpe23-item name="cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*"/>
	private String cpe23;
	
	//part is either:
	//h = hardware 
	//o = operating system part
	//a = application part
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > a
	private String part; 
	
	//vendor name is the most widely known vendor name
	//spaces are represented by "_"
	//if a vendor name does not exist, the developer's name is used. 
	//if vendor names are the same, the later vendor added uses a suffix to the vendor ("cpe:2.3:a:acm.org:er_assistant:2.0:*:*:*:*:*:*:*")
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 12net 
	private String vendor;
	
	//product name is the most common/recognizable name or an official abreviation if applicable
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > login_rebuilder
	private String product; 
	
	//version name is exactly like (special characters and all) the vendor formats
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 1.0.2
	private String version;
	/** cpe must have all known information for "part" through "version" to qualify for a cpe dictionary entry */
	
	//update aka service pack, or point release, or minor version
	//a "-" is used when there is no term available to be used and represents NA.  [Also "-" in 2.2]
	//a "*" indicates a wild card that represents ANY. [Also "" in 2.2]
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 1st "*"
	private String update;
	
	//Edition in 2.3 differs from 2.2
	//2.2 {edition} can be a single value, excluding sw_edition, target_sw, target_hw, other, or {~(edition)~(sw_edition)~(target_sw)~(target_hw)~(other)}
	//2.3 values are always defined: ...":{edition}:{language}:{sw_edition}:{target_sw}:{target_hw}:{other}"
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 2nd "*"
	private String edition;
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 3rd "*"
	
	//language name is represented by a valid language tag defined by IETF RFC 4646
	//tags include only language and region
	private String language;
	
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 4th "*"
	private String sw_edition;
	
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > wordpress
	private String target_sw;
	
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 5th "*"
	private String target_hw;
	
	//"cpe:2.3:a:12net:login_rebuilder:1.0.2:*:*:*:*:wordpress:*:*" > 6th "*"
	private String other;
	
	
	//<reference href="https://code.google.com/p/sexy-polling/downloads/list">version information</reference>
	//private List<String> refLinkList = new ArrayList<String>();
	private String refLinkList = "";
	
	//<reference href="https://code.google.com/p/sexy-polling/downloads/list">version information</reference>
	//private List<String> refDescriptionList = new ArrayList<String>();
	private String refDescriptionList = "";
	
	public void setEntry(String cpe22){
		this.cpe = cpe22;
	}
	public String getCpe(){
		return cpe;
	}
	
	public void setTitle (String title){
		this.title = title;
	} 
	public String getTitle(){
		return title;
	}
	
	public void setCpe23 (String cpe23){
		String[] elements = cpe23.split(":");
		setPart(elements[2]);
		setVendor(elements[3]);
		setProduct(elements[4]);
		setVersion(elements[5]);
		setUpdate(elements[6]);
		setEdition(elements[7]);
		setLanguage(elements[8]);
		setSwEdition(elements[9]);
		setTargetSw(elements[10]);
		setTargetHw(elements[11]);
		setOther(elements[12]);
		this.cpe23 = cpe23;
	}
	public String getCpe23(){
		return cpe23;
	}
	
	public void setPart(String part){
		part = specialChar(part);
		this.part = part;
	}
	public String getPart(){
		return part;
	}
	
	public void setVendor(String vendor){
		vendor = specialChar(vendor);
		this.vendor = vendor;
	}
	public String getVendor(){
		return vendor;
	}
	
	public void setProduct(String product){
		product = specialChar(product);
		this.product = product;
	}
	public String getProduct(){
		return product;
	}
	
	public void setVersion(String version){
		version = specialChar(version);
		this.version = version;
	}
	public String getVersion(){
		return version;
	}
	
	public void setUpdate(String update){
		update = specialChar(update);
		this.update = update;
	}
	public String getUpdate(){
		return update;
	}
	
	public void setEdition(String edition){
		edition = specialChar(edition);
		this.edition = edition;
	}
	public String getEdition(){
		return edition;
	}
	
	public void setLanguage(String lang){
		lang = specialChar(lang);
		this.language = lang;
	}
	public String getLanguage(){
		return language;
	}
	
	public void setSwEdition(String sw_ed){
		sw_ed = specialChar(sw_ed);
		this.sw_edition = sw_ed;
	}
	public String getSwEdition(){
		return sw_edition;
	}
	
	public void setTargetSw(String target_sw){
		target_sw = specialChar(target_sw);
		this.target_sw = target_sw;
	}
	public String getTargetSw(){
		return target_sw;
	}
	
	public void setTargetHw(String target_hw){
		target_hw = specialChar(target_hw);
		this.target_hw = target_hw;
	}
	public String getTargetHw(){
		return target_hw;
	}
	
	public void setOther(String other){
		other = specialChar(other);
		this.other = other;
	}
	public String getOther(){
		return other;
	}
	
	public void setRefLink(String ref){
		//refLinkList.add(ref + "\n");
		this.refLinkList = this.refLinkList + ", " + ref;
	}
	public String getRefLinks(){
		refLinkList.replace("null, ", "");
		return replaceNull(refLinkList);
	}
	
	public void setRefDescription(String descript){
		//refDescriptionList.add(descript + "\n");
		this.refDescriptionList = this.refDescriptionList + ", " + descript;
	}
	public String getRefDescriptions(){
		return replaceNull(refDescriptionList);
	}
	
	public String replaceNull (String element){
		
		if (element.contains("null, ")){
			element.replace("null, ", "");
		} else if (element.contains("null")){
			element.replace("null" , "");
		} else if (element.contains (", ")){
			element = element.substring(2, element.length());
		}
		return element;
	}

	//Interprets the special characters
	public String specialChar(String element){
		if (element.equals("*")){
			return "ANY";
		}else if (element.equals("-")){
			return "NA";
		}
		return element;
	}
	
	/**Returns the cpe info delimited by "#" 
	 * title#cpe22#cpe23#part#vendor#product#version#update#edition#language#sw_edition#target_sw#target_hw#other#ReferenceDescriptions#ReferenceLinks*/
	public String toString(){
		StringBuffer dictionaryInfo = new StringBuffer();
		String delimiter = "#";
		
		dictionaryInfo.append(getTitle());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getCpe());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getCpe23());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getPart());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getVendor());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getProduct());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getVersion());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getUpdate());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getEdition());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getLanguage());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getSwEdition());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getTargetSw());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getTargetHw());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getOther());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getRefDescriptions());
		dictionaryInfo.append(delimiter);
		dictionaryInfo.append(getRefLinks());	

		return dictionaryInfo.toString();
	}
	
}
