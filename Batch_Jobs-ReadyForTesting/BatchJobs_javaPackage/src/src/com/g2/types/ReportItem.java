package src.com.g2.types;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.datastax.driver.core.LocalDate;

/**
 * @author Sara Prokop, G2 Ops, Virginia Beach, VA
 * @date September 18,2017 
 * 
 * @purpose This ReportItem class captures information about each item within each Report Host, 
 * Each ReportItem contains information about cvss scores, which is imported into the database, 
 * as well as a vulnerabilities output file.
 * It also stores information about ports, services, and protocols, which is used for the host-ports.csv output file. 
 * 
 */

public class ReportItem {
/** class variables **/
	
	//port per item- port
	//<ReportItem port="23" svc_name="telnet" protocol="tcp" severity="2" pluginID="42263" pluginName="Unencrypted Telnet Server" pluginFamily="Misc.">
	private String port;
	
	//service name per item - svc_name
	private String service;
	
	//protocol per item- protocol
	private String protocol;
	
	//severity per item- severity
	private String severity;
	
	//pluginID per item- pluginID
	private String pluginID;
	
	//pluginName per item- pluginName
	private String pluginName;
	
	//pluginFamily per item- pluginFamily
	private String pluginFamily;
	
	//effected software. usually one cpe per Report Item, but there could be multiple
	//<cpe>cpe:/o:microsoft:windows
	//cpe:/a:microsoft:ie</cpe>
	//some also have extra suffixes and prefixes that need to be discarded.
	//x-cpe:/a:git:msysgit
	private List<String> cpeList;
	
	//vulnerability identification
	//<cve>CVE-1999-0524</cve>
	private String cveID;
	
	//cvss base score for a vulnerability
	//<cvss_base_score>5.8</cvss_base_score>
	private double baseScore = 0;
	
	//cvss temporal score for a vulnerability
	//<cvss_temporal_score>7.1</cvss_temporal_score>
	private double tempScore = 0;
		
	//description of the Report Item
	//<description>Makes a traceroute to the remote host.</description>
	private String description;
	
	//vulnerability publication date
	//<vuln_publication_date>2008/11/24</vuln_publication_date>
	private LocalDate vulnPub;
	
	//patch publication date
	//<patch_publication_date>2016/12/13</patch_publication_date>
	private String patchPub;
	
	//solution for vulnerability
	//<solution>Microsoft has released a set of patches for Internet Explorer 9, 10, and 11.</solution>
	private String solution;
	
	//cvss vector
	//<cvss_vector>CVSS2#AV:L/AC:L/Au:N/C:C/I:C/A:C</cvss_vector>
	private String cvssVector;
	
	//cvss temporal vector
	//<cvss_temporal_vector>CVSS2#E:F/RL:OF/RC:ND</cvss_temporal_vector>
	private String cvssTempVector; 
	
	//<fname>ssh_weak_hmac_enabled.nasl</fname>
	private String fName;
	
	//<plugin_modification_date>
	private String pluginModDate;
	
	//<plugin_publication_date>2009/10/27</plugin_publication_date>
	private String pluginPubDate;
	
	//<plugin_type>remote</plugin_type>
	private String pluginType;
	
	//<risk_factor>Medium</risk_factor>
	private String riskFactor;
	
	//<script_version>$Revision: 1.10 $</script_version>
	private String scriptVersion;
	
	//<synopsis>The remote Telnet server transmits traffic in cleartext.</synopsis>
	private String synopsis;
	
	//acquired from the appl_auth.common_vulnerabilities table
	private int patch_count;
	
	private SimpleDateFormat dateParser;
	private SimpleDateFormat newFormat;

	
	
	public ReportItem(){
		
	}

/** Tools **/
	//replace null strings with the empty string so "null" doesn't print
	public static String replaceNull(String input) {
		return input == null ? "" : input;
	}
	public static String keepNull(String input){
		if (input == null){
			return input;
		}else{
			return "'"+input+"'";
		}
	}
	
	//creates output for the host-ports.csv
	public String getItemPortsInfo(){
		StringBuffer attributes = new StringBuffer();
		String delimiter = "><";
		
		attributes.append(delimiter);
		attributes.append(getPort());
		attributes.append(delimiter);
		attributes.append(getService());
		attributes.append(delimiter);
		attributes.append(getProtocol());
		
		return attributes.toString();
	}

	//creates output for the host-vulnerabilities.csv
	public String getItemVuln(){
		StringBuffer vulnerabilities = new StringBuffer();
		String delimiter = "><";

		vulnerabilities.append(getBaseScore());
		vulnerabilities.append(delimiter);
		vulnerabilities.append(getTempScore());
//		vulnerabilities.append(delimiter);
//		vulnerabilities.append(getCvssTempVector());
//		System.out.println("TempVector: " +getCvssTempVector());
//		vulnerabilities.append(delimiter);
//		vulnerabilities.append(getCvssVector());
//		vulnerabilities.append(delimiter);
//		vulnerabilities.append(getDescription());
//		vulnerabilities.append(delimiter);
//		vulnerabilities.append(getSolution());

	
		
		return vulnerabilities.toString();
	}
	
	public String getNoCveInfo(){
		StringBuffer info = new StringBuffer();
		String delimiter = "><";
		
		info.append(getPort());
		info.append(delimiter);
		info.append(getService());
		info.append(delimiter);
		info.append(getProtocol());
		info.append(delimiter);
		info.append(getSeverity());
		info.append(delimiter);
		info.append(getPluginID());
		info.append(delimiter);
		info.append(getPluginName());
		info.append(delimiter);
		info.append(getPluginFamily());
		info.append(delimiter);
		info.append(getCveId());
		info.append(delimiter);
		info.append(getBaseScore());
		info.append(delimiter);
		info.append(getCvssVector());
		info.append(delimiter);
		info.append(getDescription());
		info.append(delimiter);
		info.append(getFName());
		info.append(delimiter);
		info.append(getPluginModDate());
		info.append(delimiter);
		info.append(getPluginPubDate());
		info.append(delimiter);
		info.append(getPluginType());
		info.append(delimiter);
		info.append(getRiskFactor());
		info.append(delimiter);
		info.append(getScriptVersion());
		info.append(delimiter);
		info.append(getSolution());
		info.append(delimiter);
		info.append(getSynopsis());
		
		return info.toString();
		
	}

/** Setting and Getting Attributes and Tags **/
	//***********Protocol******************/
	public void setProtocol(String protocol){
		this.protocol = protocol;
	}
	public String getProtocol(){
		return replaceNull(protocol);
	}
	
	//***********Service******************/
	public void setService(String service){
		this.service = service;
	}
	public String getService(){
		return replaceNull(service);
	}
	
	//***********Service******************/
	public void setPort(String port){
		this.port = port;
	}
	public String getPort(){
		return replaceNull(port);
	}

	//***********ID******************//		
	public void setCveId(String id){
//		System.out.println("CVE ID: " + id);
		this.cveID = id;
	}
	public String getCveId(){
		return keepNull(cveID);
	}
	//***********Base Score******************//	
	public void setBaseScore(String base){
//		System.out.println("Base: " + base);
		this.baseScore = Double.parseDouble(base);
	}
	public Double getBaseScore(){
		String score = Double.toString(baseScore);
		return baseScore;
	}
	//***********Temp Score******************//	
	public void setTempScore(String temp){
//		System.out.println("Temp: " + temp);
		this.tempScore = Double.parseDouble(temp);
	}
	public Double getTempScore(){
		String score = Double.toString(baseScore);
		return tempScore;
	}
	//***********Description******************//	
	public void setDescription(String descript){
		int dot = descript.indexOf(".") + 1;//the +1 to include the period
		this.description = descript.substring(0, dot);
	}
	public String getDescription(){
		return keepNull(description);
	}
	//***********Vulnerability Publication Date******************//	
	public void setVulPublicationDate(String vdate){
		String date = vdate.replaceAll("/", "-");
//		this.vulnPub = date;
		//the following will not wrk for updating cvss_scores.
		newFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dVulnDate = newFormat.parse(date);
			LocalDate lVulnDate = LocalDate.fromMillisSinceEpoch(dVulnDate.getTime());
			this.vulnPub = lVulnDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public LocalDate getVulPublicationDate(){
		return vulnPub;
//		return keepNull(vulnPub);
	}
	//***********Patch Publication Date******************//	
	public void setPatchPublicationDate(String pdate){
		String date = pdate.replaceAll("/", "-");
		this.patchPub = date;
		//the following will not work for updating the cvss scores.
//		newFormat = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date dPatchDate = newFormat.parse(date);
//			LocalDate lPatchDate = LocalDate.fromMillisSinceEpoch(dPatchDate.getTime());
//			this.patchPub = lPatchDate;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public String getPatchPublicationDate(){
		return keepNull(patchPub);
	}
	//***********Solution******************//	
	public void setSolution(String sol){
//		int dot = sol.indexOf(".") + 1;//the +1 to include the period
//		this.solution = sol.substring(0, dot);
		this.solution = sol;
	}
	public String getSolution(){
		return keepNull(solution);
	}
	//***********Cvss Vector******************//	
	public void setCvssVector(String vector){
		this.cvssVector = vector;
	}
	public String getCvssVector(){
		return keepNull(cvssVector);
	}
	//***********Cvss Temporal Vector******************//	
	public void setCvssTempVector(String vector){
		this.cvssTempVector = vector;
	}
	public String getCvssTempVector(){
		return keepNull(cvssTempVector);
	}
	//***********Severity******************//	
	public void setSeverity(String severity) {
		this.severity = severity;
		
	}
	public String getSeverity(){
		return severity;
	}
	//***********PluginID******************//
	public void setPluginID(String pluginID) {
		this.pluginID = pluginID;	
	}
	public String getPluginID(){
		return pluginID;
	}
	//***********PluginName******************//
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public String getPluginName(){
		return pluginName;
	}
	//***********PluginFamily******************//
	public void setPluginFamily(String pluginFamily) {
		this.pluginFamily = pluginFamily;
	}
	public String getPluginFamily(){
		return pluginFamily;
	}
	//***********Plugin Modification Date******************//
	public void setPluginModDate(String pluginModDate) {
		this.pluginModDate = pluginModDate;
	}
	public String getPluginModDate(){
		return pluginModDate;
	}
	//***********Plugin Publication Date******************//
	public void setPluginPubDate(String pluginPubDate) {
		this.pluginPubDate = pluginPubDate;
	}
	public String getPluginPubDate(){
		return pluginPubDate;
	}
	//***********Plugin Type******************//
	public void setPluginType(String pluginType) {
		this.pluginType = pluginType;
	}
	public String getPluginType(){
		return pluginType;
	}
	//***********Risk Factor******************//
	public void setRistFactor(String riskFactor) {
		this.riskFactor = riskFactor;
	}
	public String getRiskFactor(){
		return riskFactor;
	}
	//***********Script Version******************//
	public void setScriptVersion(String scriptVersion) {
		this.scriptVersion = scriptVersion;
	}
	public String getScriptVersion(){
		return scriptVersion;
	}
	//***********Synopsis******************//
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getSynopsis(){
		return synopsis;
	}
	//***********FName******************//
	public void setFName(String fName) {
		this.fName = fName;
	}
	public String getFName(){
		return fName;
	}
	//***********Patch Count******************//
	public void setPatchCount(int patchCount) {
		this.patch_count = patchCount;
	}
	public int getPatchCount(){
		return patch_count;
	}
}
