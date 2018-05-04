package com.g2ops.impact.urm.beans;

import javax.annotation.PostConstruct;

/**
 * @author 		Sara PRokop, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * @see			<a href="URL#value">label</a>
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 12-July-2017		sara.prokop			Created this Java class
 * 11-Sept-2017		sara.prokop			took out business interruption threshold, business criticality, and classification
 * 										used prepared statements for db query
 * 14-Sept-2017		sara.prokop			added reportable_flag to Business Attribution
 * 6-Oct-2017 		sara.bergman		changed class name from BusinessAttributionTable to NodeAttributionTable
 * 
 */

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.g2ops.impact.urm.types.Hardware;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

import com.g2ops.impact.urm.types.Hardware_Relationships;

import com.g2ops.impact.urm.types.Audit_Upsert;
import com.g2ops.impact.urm.types.Connected_Elements;
import com.g2ops.impact.urm.types.Cvss_Scores;

@Named("nodeAttributionTable")
@RequestScoped
public class NodeAttributionTable implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject private UserBean currentUser;
	private MappingManager manager;

	private Session dbSession;
	private Audit_Upsert auditUpsert = new Audit_Upsert();
	private Iterator<Row> iterator;
	private Hardware editHw;
	private List<Hardware> hwList = new ArrayList<Hardware>();
	private List<String> ddOptions = new ArrayList<String>();
	private HashMap<UUID, String> sitesMap = new HashMap<UUID, String>();
	String site_name;
	
	//Hardware Attributes:
	UUID site_id, internal_system_id;
	String ip_subnet_or_building, asset_type, asset_visibility, cpe_id, host_name, ip_address, ip_gateway, ip_netmask, mac_address, operating_system, os_general, part, vendor, product, version, version_update,system_type, vendor_stencil_icon;
	LocalDate archive_date, end_of_life_date, end_of_sale, end_of_support_date, import_date;
	List<UUID> business_process_ids;
	List<Connected_Elements> connected_elements;
	Boolean crown_jewel, reportable_flag;
	List<Cvss_Scores> cvss_scores;
	Float cyvar;
	List<String> installed_software = Arrays.asList("No installed software available");
	Float neighbor_coefficient_value, node_impact_value;
	Date run_datetime;
	int vulnerability_count;
	
		
	public NodeAttributionTable() {
		System.out.println("*** in NodeAttributionTable constructor ***");
	}
	
	/**
	 * @purpose to map the java classes with UDTs
	 */
	public void mapDataTypes(){
		manager = new MappingManager(dbSession);
		manager.udtCodec(Connected_Elements.class);
		manager.udtCodec(Cvss_Scores.class);
		manager.udtCodec(Audit_Upsert.class);
		manager.udtCodec(Hardware_Relationships.class);
	}
	
	@PostConstruct
	/**
	 * @purpose to populate the node attribution form with all hardware
	 */
	public void init() {
		System.out.println("*** in NodeAttributionTable init ***");
		dbSession = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		mapDataTypes();
		auditUpsert.setChangedbyusername(currentUser.getFirstName().toLowerCase()+"."+currentUser.getLastName().toLowerCase());

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// gets all entries with no filter
		String query = "select * from hardware where site_id= ?";
		PreparedStatement prepared = dbSession.prepare(query);
		site_id = getDefaultSite(currentUser.getEmail());
		site_name = sitesMap.get(site_id);
		BoundStatement bound = prepared.bind(site_id);
		ResultSet rs = dbSession.execute(bound);
//		ResultSet rs = databaseQueryService.runQuery(query);
		hwList.clear();
		hwList =setRows(rs);

	}
	
	public UUID getDefaultSite(String email){
		String query = "Select site_id from users where user_email = ?";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(email);
		Row row = dbSession.execute(bound).one();
		UUID site_id = row.getUUID("site_id");
		return site_id;
	}
	
	public String editNodeAtt(Hardware hw){
		System.out.println("edit: " + hw.getIp_address());
		editHw = hw;
		host_name = editHw.getHost_name();
		ip_address = editHw.getIp_address();
		mac_address = editHw.getMac_address();
		vendor = editHw.getVendor();
		product = editHw.getProduct();
		os_general = editHw.getOs_general();
		operating_system = editHw.getOperating_system();
		system_type = editHw.getSystem_type();
		asset_visibility = editHw.getAsset_visibility();
		asset_type = editHw.getAsset_type();
		installed_software = editHw.getInstalled_software();
		connected_elements = editHw.getConnected_Elements();
		business_process_ids = editHw.getBusiness_processes();
		reportable_flag = editHw.getReportable_flag();
		cvss_scores = editHw.getCvss_scores();
		vulnerability_count = editHw.getVulnerability_count();
		run_datetime = editHw.getRun_datetime();
		node_impact_value = editHw.getNode_impact_value();
		end_of_life_date = editHw.getEnd_of_life_date();
		end_of_sale = editHw.getEnd_of_sale();
		end_of_support_date = editHw.getEnd_of_support_date();
		
		return "/administrator/node-attribution-edit.jsf";
	}
		
	/**
	 * @return 
	 * @purpose to update the form with desired hardware 
	 */
	public List<Hardware> setRows(ResultSet rs){
		List<Hardware> hardwareList = new ArrayList<Hardware>();
		iterator = rs.iterator();
		
		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();
			Hardware hw = new Hardware();
			
			UUID site_id = row.getUUID("site_id");
				hw.setSite_id(site_id);
			String ip_subnet_or_building = row.getString("ip_subnet_or_building");
				hw.setIp_subnet_or_building(ip_subnet_or_building);
			UUID internal_system_id = row.getUUID("internal_system_id");
				hw.setInternal_system_id(internal_system_id);
			String asset_type = row.getString("asset_type");
				hw.setAsset_type(asset_type);
			String asset_visibility = row.getString("asset_visibility");
				hw.setAsset_visibility(asset_visibility);
			Audit_Upsert audit_upsert =row.get("audit_upsert", Audit_Upsert.class);
				hw.setAudit_upsert(audit_upsert);
			List<UUID> business_process_ids = row.getList("business_process_ids", UUID.class);
				hw.setBusiness_processes(business_process_ids);
			List<Connected_Elements> connected_elements = row.getList("connected_elements", Connected_Elements.class);
				hw.setConnected_elements(connected_elements);
			String cpe_id = row.getString("cpe_id");
				hw.setCpe_id(cpe_id);
			boolean crown_jewel = row.getBool("crown_jewel");
				hw.setCrown_jewel(crown_jewel);
			List<Cvss_Scores> cvss_scores = row.getList("cvss_scores", Cvss_Scores.class);
				hw.setCvss_scores(cvss_scores);
			Float cyvar = row.getFloat("cyvar");
				hw.setCyvar(cyvar);
			LocalDate end_of_life_date = row.getDate("end_of_life_date");
				hw.setEnd_of_life_date(end_of_life_date);
			LocalDate end_of_sale = row.getDate("end_of_sale");
				hw.setEnd_of_sale(end_of_sale);
			LocalDate end_of_support_date = row.getDate("end_of_support_date");
				hw.setEnd_of_support_date(end_of_support_date);
			String host_name = row.getString("host_name");
				hw.setHost_name(host_name);
			LocalDate import_date = row.getDate("import_date");
				hw.setImport_date(import_date);
			List<String> installed_software = row.getList("installed_software", String.class);
				hw.setInstalled_software(installed_software);
			String ip_address = row.getString("ip_address");
				hw.setIp_address(ip_address);
			String ip_gateway = row.getString("ip_gateway");
				hw.setIp_gateway(ip_gateway);
			String ip_netmask = row.getString("ip_netmask");
				hw.setIp_netmask(ip_netmask);
			String mac_address = row.getString("mac_address");
				hw.setMac_address(mac_address);
			Float neighbor_coefficient_value = row.getFloat("neighbor_coefficient_value");
				hw.setNeighbor_coefficient_value(neighbor_coefficient_value);
			Float node_impact_value = row.getFloat("node_impact_value");
				hw.setNode_impact_value(node_impact_value);
			String operating_system = row.getString("operating_system");
				hw.setOperating_system(operating_system);
			String os_general = row.getString("os_general");
				hw.setOs_general(os_general);
			String part = row.getString("part");
				hw.setPart(part);
			String product = row.getString("product");
				hw.setProduct(product);
			boolean reportable_flag = row.getBool("reportable_flag");	
				hw.setReportable_flag(reportable_flag);
			Date run_datetime = row.getTimestamp("run_datetime");
				hw.setRun_datetime(run_datetime);
			String system_type = row.getString("system_type");
				hw.setSystem_type(system_type);
			String vendor = row.getString("vendor");
				hw.setVendor(vendor);
			String vendor_stencil_icon = row.getString("vendor_stencil_icon");
				hw.setVendor_stencil_icon(vendor_stencil_icon);
			String version = row.getString("version");
				hw.setVersion(version);
			String version_update = row.getString("version_update");
				hw.setVersion_update(version_update);
			int vulnerability_count = row.getInt("vulnerability_count");
				hw.setVulnerability_count(vulnerability_count);

			// Save each database record to the list.
			hardwareList.add(hw);
		}
		return hardwareList;
	}
	
	public void saveChanges(){
		String query = "UPDATE hardware SET host_name = ?, ip_address = ?, mac_address = ?, vendor= ?, "
				+ "product = ?,os_general = ? , system_type = ?, asset_type = ?, asset_visibility = ?, reportable_flag = ?"
				+ " where internal_system_id = ? and site_or_ou_name = ? and ip_subnet_or_building = ?" ;
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound;
		
		ip_subnet_or_building = replaceNull_String(ip_subnet_or_building);
		asset_type = replaceNull_String(asset_type);
		asset_visibility = replaceNull_String(asset_visibility);
		host_name = replaceNull_String(host_name);
		ip_address= replaceNull_String(ip_address);
		mac_address= replaceNull_String(mac_address);
		operating_system= replaceNull_String(operating_system);
		os_general= replaceNull_String(os_general);
		vendor= replaceNull_String(vendor);
		product= replaceNull_String(product);
		system_type= replaceNull_String(system_type);
		
		bound = prepared.bind(host_name, ip_address,mac_address,vendor,product,os_general,system_type,asset_type,asset_visibility,reportable_flag,site_id, ip_subnet_or_building,internal_system_id);
		System.out.println("executed");
		
	}
	
	//replace null strings with the empty string so "null" doesn't print
	public static String replaceNull_String(String input) {
		if (input != null)
			input.trim();
		return input == null ? "" : input;
	}


	public List<Hardware> getConnected_Hardware(){
		List<Hardware> connected_ips = new ArrayList<Hardware>();
		String query_keys = "Select * from hardware_by_internal_system_id where internal_system_id = ?;";
		String query_hw = "Select * from hardware where site_id = ? and ip_subnet_or_building = ? and internal_system_id = ?";
		PreparedStatement prepared_keys = dbSession.prepare(query_keys);
		PreparedStatement prepared_hw = dbSession.prepare(query_hw);
		BoundStatement bound;
		Iterator<Connected_Elements> it = connected_elements.iterator();
		while(it.hasNext()){
			Connected_Elements element = it.next();
			bound = prepared_keys.bind(UUID.fromString(element.getDestination_id()));
			Row row = dbSession.execute(bound).one();
			if (row ==null) //this would be due to bad data. need to update connected_elements in hardware
				continue;
			UUID site_id = row.getUUID("site_id");
			String ip_subnet_or_building = row.getString("ip_subnet_or_building");
			UUID internal_system_id = row.getUUID("internal_system_id");
			
			bound = prepared_hw.bind(site_id, ip_subnet_or_building, internal_system_id);
			ResultSet rs = dbSession.execute(bound);
			connected_ips.addAll(setRows(rs));
		}
		return connected_ips;
	}
	
	public List<String> getBusiness_process_names(){
		List<String> process_names = new ArrayList<String>();
		String query = "Select business_process_name from business_value_attribution where site_id = ? and business_process_id = ?";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound;
		Iterator<UUID> it = business_process_ids.iterator();
		while(it.hasNext()){
			UUID process_id = it.next();
			bound = prepared.bind(UUID.fromString("96a3378e-2f67-4b9d-ab67-9ee9d533b88f"),process_id);
			Row name = dbSession.execute(bound).one();
			process_names.add(name.getString("business_process_name"));
		}
		return process_names;
	}
	
	public void populateSites(){
		sitesMap.clear();
		String query = "Select site_id, site_name from sites where org_unit_id = ?";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(UUID.fromString("703161c5-eca3-48a0-8ad9-99f2a6b8d5e7"));
		ResultSet rs = dbSession.execute(bound);
		Iterator<Row> it = rs.iterator();
		while (it.hasNext()){
			Row row = it.next();
			UUID id = row.getUUID("site_id");
			String name = row.getString("site_name");
			sitesMap.put(id, name);
			ddOptions.add(name);
		}
	}
	
	public void siteListener(ValueChangeEvent event) {
		String site_name = event.getNewValue().toString();
			System.out.println("site_name: "+ site_name);
	}
	
	//populates the options for the dropdowns
	public void populateDropDowns(String col){
		// execute query to get drop down options for each column in the table
		String query = "select business_value from business_practice where category = ?";
		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(col);
		ResultSet rs = dbSession.execute(bound);
		
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
		 Row row = iterator.next();
		 String colValue = row.getString("business_value");
		 System.out.println("colValue: " + colValue);
		 ddOptions.add(colValue);
		}
	}
	
	public List<String> ddSites(){
		ddOptions.clear();
		populateSites();
		return ddOptions;
	}
	
	public List<String> ddAssetTypes(){
		ddOptions.clear();
		populateDropDowns("Asset Type");
		return ddOptions;
	}
	
	public List<String> ddAssetVisibility(){
		ddOptions.clear();
		populateDropDowns("Asset Visibility");
		return ddOptions;
	}
	
	public List<String> ddVendors(){
		ddOptions.clear();
		return ddOptions;
	}
	
	public List<String> ddOS(){
		ddOptions.clear();
//		ddOptions = NodeAttributionFilter.ddOS();
		return ddOptions;
	}
	
	public List<String> ddSysTypes(){
		ddOptions.clear();
//		ddOptions = NodeAttributionFilter.ddSysTypes();
		return ddOptions;
	}
	

	public List<Hardware> getNodeAttData() {

		return hwList;

	}
	
	public Hardware getEditHw(){
		return editHw;
	}
	
	//getters for edit form
	//>>>>>>>>>>>>>> site_id <<<<<<<<<<<<<<<<
	public String getSite_name(){
		return site_name;
	}
	public UUID getSite_id(){
		return site_id;
	}
	//>>>>>>>>>>>>>> ip_subnet_or_building <<<<<<<<<<<<<<<<
	public String getIp_subnet_or_building(){
		return ip_subnet_or_building;
	}
	//>>>>>>>>>>>>>> internal_system_id <<<<<<<<<<<<<<<<
	public UUID getInternal_system_id(){
		return internal_system_id;
	}
	//>>>>>>>>>>>>>> archive_date <<<<<<<<<<<<<<<<
	public LocalDate getArchive_date(){
		return archive_date;
	}
	//>>>>>>>>>>>>>> asset_type <<<<<<<<<<<<<<<<
	public String getAsset_type(){
		return asset_type;
	}
	//>>>>>>>>>>>>>> asset_type <<<<<<<<<<<<<<<<
	public String getAsset_visibility(){
		return asset_visibility;
	}

	//>>>>>>>>>>>>>> business_processes <<<<<<<<<<<<<<<<
	public List<String> getBusiness_process_ids(){
		if(business_process_ids ==null)
			return null;
		return getBusiness_process_names();
	}
	//>>>>>>>>>>>>>> connected_elements <<<<<<<<<<<<<<<<
	public List<Hardware> getConnected_Elements(){
		System.out.println("connected_elemets: "+ connected_elements.size());
		if (connected_elements.size()==0){
//			System.out.println("connected_elemets: "+ connected_elements.size());
			List<Hardware> noList = new ArrayList<Hardware>();
			Hardware dummy = new Hardware();
			dummy.setIp_address("No Connections Found.");
			noList.add(dummy);
			return noList;
		}
		return getConnected_Hardware();
	}
	//>>>>>>>>>>>>>> cpe_id <<<<<<<<<<<<<<<<
	public String getCpe_id(){
		return cpe_id;
	}
	//>>>>>>>>>>>>>> crown_jewel <<<<<<<<<<<<<<<<
	public boolean getCrown_jewel(){
		return crown_jewel;
	}
	//>>>>>>>>>>>>>> cvss_scores <<<<<<<<<<<<<<<<
	public List<Cvss_Scores> getCvss_scores(){
//			List<String> cveList = new ArrayList<String>();
//			Iterator<Cvss_Scores> it = cvss_scores.iterator();
//			while(it.hasNext()){
//				Cvss_Scores score = it.next();
//				cveList.add(score.getCveID());
//			}
//			Collections.sort(cveList);
////			cveList.add(0, "View Current Vulnerabilities");
//			return cveList;
		return cvss_scores;
	}
	//>>>>>>>>>>>>>> cyvar <<<<<<<<<<<<<<<<
	public Float getCyvar(){
		return cyvar;
	}
	//>>>>>>>>>>>>>> end_of_life_date <<<<<<<<<<<<<<<<
	public LocalDate getEnd_of_life_date(){
		return end_of_life_date;
	}
	//>>>>>>>>>>>>>> end_of_sale <<<<<<<<<<<<<<<<
	public LocalDate getEnd_of_sale(){
		return end_of_sale;
	}
	//>>>>>>>>>>>>>> end_of_support_date <<<<<<<<<<<<<<<<
	public LocalDate getEnd_of_support_date(){
		return end_of_support_date;
	}
	//>>>>>>>>>>>>>> host_name <<<<<<<<<<<<<<<<
	public String getHost_name(){
		return host_name;
	}
	//>>>>>>>>>>>>>> import_date <<<<<<<<<<<<<<<<
	public LocalDate getImport_date(){
		return import_date;
	}
	//>>>>>>>>>>>>>> installed_software <<<<<<<<<<<<<<<<
	public List<String> getInstalled_software(){
		return installed_software;
	}	
	//>>>>>>>>>>>>>> ip_address <<<<<<<<<<<<<<<<
	public String getIp_address(){
		return ip_address;
	}
	//>>>>>>>>>>>>>> ip_gateway <<<<<<<<<<<<<<<<
	public String getIp_gateway(){
		return ip_gateway;
	}
	//>>>>>>>>>>>>>> ip_netmask <<<<<<<<<<<<<<<<
	public String getIp_netmask(){
		return ip_netmask;
	}
	//>>>>>>>>>>>>>> mac_address <<<<<<<<<<<<<<<<
	public String getMac_address(){
		return mac_address;
	}
	//>>>>>>>>>>>>>> neighbor_coefficient_value <<<<<<<<<<<<<<<<
	public Float getNeighbor_coefficient_value(){
		return neighbor_coefficient_value;
	}
	//>>>>>>>>>>>>>> neighbor_coefficient_value <<<<<<<<<<<<<<<<
	public Float getNode_impact_value(){
		return node_impact_value;
	}
	//>>>>>>>>>>>>>> operating_system <<<<<<<<<<<<<<<<
	public String getOperating_system(){
		return operating_system;
	}
	//>>>>>>>>>>>>>> os_general <<<<<<<<<<<<<<<<
	public String getOs_general(){
		return os_general;
	}
	//>>>>>>>>>>>>>> part <<<<<<<<<<<<<<<<
	public String getPart(){
		return part;
	}
	//>>>>>>>>>>>>>> product <<<<<<<<<<<<<<<<
	public String getProduct(){
		return product;	
	}
	//>>>>>>>>>>>>>> reportable_flag <<<<<<<<<<<<<<<<
	public boolean getReportable_flag(){
		if (reportable_flag == null)
			reportable_flag = false;
		return reportable_flag;
	}
	//>>>>>>>>>>>>>> run_date <<<<<<<<<<<<<<<<
	public Date getRun_datetime(){
		return run_datetime;
	}
	//>>>>>>>>>>>>>> system_type <<<<<<<<<<<<<<<<
	public String getSystem_type(){
		return system_type;
	}
	//>>>>>>>>>>>>>> vendor <<<<<<<<<<<<<<<<
	public String getVendor(){
		return vendor;
	}
	//>>>>>>>>>>>>>> vendor_stencil_icon <<<<<<<<<<<<<<<<
	public String getVendor_stencil_icon(){
		return vendor_stencil_icon;
	}
	//>>>>>>>>>>>>>> version_update <<<<<<<<<<<<<<<<
	public String getVersion_update(){
		return version_update;
	}
	//>>>>>>>>>>>>>> version <<<<<<<<<<<<<<<<
	public String getVersion(){
		return version;
	}
	//>>>>>>>>>>>>>> vulnerability_count <<<<<<<<<<<<<<<<
	public int getVulnerability_count(){
		return vulnerability_count;
	}

}
