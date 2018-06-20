package src.com.g2.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AuthenticationException;
import com.datastax.driver.mapping.MappingManager;

import src.com.g2.types.Audit_Upsert;
import src.com.g2.types.Connected_Elements;
import src.com.g2.types.Cvss_Scores;
import src.com.g2.types.DictEntry;
import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Relationships;
import src.com.g2.types.Hardware_Summary;
import src.com.g2.types.Software_UDT;
import src.com.g2.types.UniqueList;

public class TableData {
	/**Connect to Database */
	private static Connect2Db connection = new Connect2Db();
	private static Session session;
	//maping classes
	private static MappingManager manager;
	private static BoundStatement bound;
	static PreparedStatement prepared_insert_archive;
	static PreparedStatement prepared_insert_daily;
	static PreparedStatement prepared_insert_weekly;
	static PreparedStatement prepared_insert_monthly;
	static PreparedStatement prepared_select_archived;
	static PreparedStatement prepared_date;
	
	//logging
	private final static Logger slf4jLogger = LoggerFactory.getLogger(TableData.class);
		
	private static UniqueList siteSet = new UniqueList();
	private static UniqueList subnetSet = new UniqueList();
	
	private static Audit_Upsert audit = new Audit_Upsert();
	
	/**Valid Keyspaces*/
	static List<String> keyspaces = new ArrayList<String>(Arrays.asList("dod", "g2", "vmasc", "appl_auth"));
	
	public static boolean validKeyspace(String keyspace){
		boolean result = false;
		if (!(keyspaces.contains(keyspace))){
			slf4jLogger.error(keyspace+" is not a valid keyspace. Cannot connect.");
			connection.getSession().close();
			connection.getSession().getCluster().close();
			return false;
		}else {
			result = true;
		}	
		//validating connection
		try{
			session = connection.connectToKeyspace(keyspace);
			slf4jLogger.info("session is connected to keyspace: "+session.getLoggedKeyspace());
			if (keyspace == "appl_auth"){
			}else{
				audit.setChangedbyusername("sara.bergman");
				audit.setDatechanged(new Date());
				manager = new MappingManager(session);
				manager.udtCodec(Connected_Elements.class);
				manager.udtCodec(Cvss_Scores.class);
				manager.udtCodec(Audit_Upsert.class);
				manager.udtCodec(Hardware_Relationships.class);
				manager.udtCodec(Software_UDT.class);
				prepareQueries();
			}
		}catch(AuthenticationException e){
			slf4jLogger.error(e.toString());
			slf4jLogger.error("Cannot connect to "+keyspace+" keyspace");
			e.printStackTrace();
			connection.getSession().close();
			connection.getSession().getCluster().close();
			return false;
		}catch(java.lang.IllegalArgumentException e1){
			slf4jLogger.error(e1.toString());
			e1.printStackTrace();
			connection.getSession().close();
			connection.getSession().getCluster().close();
			return false;
		}
		return result;
	}
	
	public static void prepareQueries(){
//		System.out.println("keyspace: " + session.getLoggedKeyspace());
		if (session.getLoggedKeyspace() !="appl_auth"){
			String query_insert_archive = "Insert into hardware_archive (site_id, ip_subnet_or_building, internal_system_id, asset_type, asset_visibility, audit_upsert, business_process_ids, "
					+ "connected_elements, cpe_id, crown_jewel, cvss_scores, end_of_life_date, end_of_sale, end_of_support_date, host_name, installed_software,"
					+ "ip_address, mac_address, node_impact_value, operating_system, os_general, part, product, reportable_flag, "
					+ "run_datetime, system_type, vendor, vendor_stencil_icon, version, version_update, vulnerability_count,archive_date)"
					+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			prepared_insert_archive = session.prepare(query_insert_archive);
			
			String query_insert_daily = "INSERT INTO hardware_daily_summary (site_id, ip_subnet_or_building, week_start_date, day_of_week, critical_count, high_count, low_count, medium_count, patches_count, patches_critical_count, patches_high_count, patches_low_count, patches_medium_count, vulnerability_count) "
					+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			prepared_insert_daily = session.prepare(query_insert_daily);
			
			String query_insert_weekly = "INSERT INTO hardware_weekly_summary (site_id, ip_subnet_or_building, month_end_date, week_start_date, critical_count, high_count, low_count, medium_count, "
					+ "patches_count, patches_critical_count, patches_high_count, patches_low_count, patches_medium_count, vulnerability_count) "
					+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			prepared_insert_weekly = session.prepare(query_insert_weekly);
			
			String query_insert_monthly = "INSERT INTO hardware_monthly_summary (site_id, ip_subnet_or_building, month_end_date,critical_count, high_count, low_count, medium_count, "
					+ "patches_count, patches_critical_count, patches_high_count, patches_low_count, patches_medium_count, vulnerability_count) "
					+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
			prepared_insert_monthly = session.prepare(query_insert_monthly);
			
			String query_select_archived = "select site_id, ip_subnet_or_building, internal_system_id, archive_date, cvss_scores, vulnerability_count from hardware_archive where site_id = ? and ip_subnet_or_building = ? and archive_date = ?;";
			prepared_select_archived = session.prepare(query_select_archived);
			
			String query_date = "Select archive_date from hardware_archive Limit 1";
			prepared_date = session.prepare(query_date);
		}
	}
	
	//........................APPL_AUTH........................
	
	/**updates the cpe info into the common_platforms database */
	public static void importDictionary(DictionaryParser parser){
		
		if (!validKeyspace("appl_auth"))
			return;
		
		List<DictEntry> cpeList = parser.getCpeList();
		String query = "INSERT INTO common_platforms (cpe_id_22, part, vendor, product, cpe_id_23, "
				+ "edition, import_date, language, official, product_update, other, references, sw_edition, target_hw, target_sw, version, title)"
				+ "VALUES (?, ?, ?, ?, ?, ?, toDate(now()), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		PreparedStatement prepared =session.prepare(query);
		slf4jLogger.info("CPE List: "+ cpeList.size());
		for (DictEntry cpe: cpeList){
			String cpe22 = cpe.getCpe();
			String part = cpe.getPart();
			String vendor = cpe.getVendor();
			String product = cpe.getProduct();
			String cpe23 = cpe.getCpe23();
			String edition = cpe.getEdition();
			String language = cpe.getLanguage();
			//all inserts from xml dictionary are official
			boolean official = true;
			String other = cpe.getOther();
			String update = cpe.getUpdate();
			String swEdition = cpe.getSwEdition();
			String targetHw = cpe.getTargetHw();
			String targetSw = cpe.getTargetSw();
			String version = cpe.getVersion();
			String refDescriptions = cpe.getRefDescriptions();
			String refLinks = cpe.getRefLinks();
			Map<String, String> refMap = CpeUtils.getRefMap(refDescriptions, refLinks, cpe22);
			String title = cpe.getTitle();
			
			BoundStatement bound = prepared.bind(cpe22, part, vendor, product,cpe23, edition, language, official, update, other, refMap, swEdition, targetHw, targetSw, version, title);
//			System.out.println(cpe22+", "+part+", "+vendor+", "+product+", "+cpe23+", "+edition+", "+ language+", "+official+", "+update+", "+other+", "+refMap+", "+swEdition+", "+targetHw+", "+targetSw+", "+version+", "+title);
			session.execute(bound);
//			System.out.println("cpe id: " + cpe.getCpe());
		}
		
		slf4jLogger.info(session.getLoggedKeyspace() +".common_platforms tables has been updated with the most updated official dictionary.");
		session.close();
		session.getCluster().close();
		slf4jLogger.info("Session closed: "+Boolean.toString(session.isClosed()));
	}
	
	/**checks if the cpe is already in the dictionary.
	 * not needed when inputing a new dictionary, but is necessary when a user is searching for a part */
	public static String searchCpe(String part, String vendor, String product){

		String query_search = "Select cpe_id_22 from common_platforms_by_part_vendor_product where part =? and vendor =? product=?";
		PreparedStatement prepared = session.prepare(query_search);
		BoundStatement bound = prepared.bind(part, vendor, product);
		ResultSet rs = session.execute(bound);
		String results = CpeUtils.getText(rs, "cpe_id_22");
		
		return results;
		
	}
	
	/**This method is used to retrieve a unique list of results from a search query.
	 * @param col column being retrieved */
	public static String[] uniqueList(String col){
		String query = "Select ? from common_platforms";
		List<String> uniqueList = new ArrayList<String>();
		String[] uniqueArray;
		Row row;
		ResultSet rs;
		
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind(col);
		
		rs = session.execute(bound);
		Iterator<Row> iterator = rs.iterator();
		String item;
		
		while(iterator.hasNext()){
			row = iterator.next();
			item = row.getString(col);
			if (!(uniqueList.contains(item)))
				uniqueList.add(item);
		}
		Collections.sort(uniqueList);
		uniqueArray = uniqueList.toArray(new String[0]);
		return uniqueArray;
	}
	
	public static void deleteNA_commonVulnerabilities(){
		if (!validKeyspace("appl_auth"))
			return;
		
		List<String> noCpeList = new ArrayList<String>(); 
		String query = "Select * from appl_auth.common_vuln_by_cpe_update where cpe_affected = 'N/A';";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind();
		ResultSet rs = session.execute(bound);
		Iterator<Row> it = rs.iterator();
		while (it.hasNext()){
			Row row = it.next();
			String cve_id = row.getString("cve_id");
			noCpeList.add(cve_id);
		}
		System.out.println("size of noCpeList: " + noCpeList.size());
		
		String query2 = "delete from appl_auth.common_vulnerabilities where cve_id = ? and cpe_affected = 'N/A';";
		PreparedStatement prepared2 = session.prepare(query2);
		Iterator<String> it2 = noCpeList.iterator();
		while (it2.hasNext()){
			String cve_id = it2.next();
			bound = prepared2.bind(cve_id);
			session.execute(bound);
		}
		System.out.println("no more N/A.");
		connection.getSession().close();
		connection.getSession().getCluster().close();
	}
	
	//........................DOD/ G2/ VMASC........................
	/**
	 * @purpose creates a list of all current hardware data
	 * @param none
	 * @return populates the hardwareList 
	 * */
	public static List<Hardware> collectHwTable(String keyspace){
		if (!validKeyspace(keyspace))
			return null;
		siteSet.clear();
		subnetSet.clear();
		List<Hardware> hardwareList = new ArrayList<Hardware>();
		String query = "SELECT * FROM hardware";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind();
		ResultSet rs = session.execute(bound);
		Iterator<Row> iterator = rs.iterator();
		Hardware hwRow;
		Row row;
		
		while (iterator.hasNext()){
			row = iterator.next();
			hwRow = new Hardware();
			UUID site_id = row.getUUID("site_id");
				hwRow.setSite_id(site_id);
				siteSet.add(site_id);
			String ip_subnet_or_building = row.getString("ip_subnet_or_building");
				hwRow.setIp_subnet_or_building(ip_subnet_or_building);
				subnetSet.add(ip_subnet_or_building);
			UUID internal_system_id = row.getUUID("internal_system_id");
				hwRow.setInternal_system_id(internal_system_id);
			String asset_type = row.getString("asset_type");
				hwRow.setAsset_type(asset_type);
			String asset_visibility = row.getString("asset_visibility");
				hwRow.setAsset_visibility(asset_visibility);
			Audit_Upsert audit_upsert =row.get("audit_upsert", Audit_Upsert.class);
				hwRow.setAudit_upsert(audit_upsert);
			List<UUID> business_process_ids = row.getList("business_process_ids", UUID.class);
				hwRow.setBusiness_processes(business_process_ids);
			List<Connected_Elements> connected_elements = row.getList("connected_elements", Connected_Elements.class);
				hwRow.setConnected_elements(connected_elements);
			String cpe_id = row.getString("cpe_id");
				hwRow.setCpe_id(cpe_id);
			boolean crown_jewel = row.getBool("crown_jewel");
				hwRow.setCrown_jewel(crown_jewel);
			List<Cvss_Scores> cvss_scores = row.getList("cvss_scores", Cvss_Scores.class);
				hwRow.setCvss_scores(cvss_scores);
			LocalDate end_of_life_date = row.getDate("end_of_life_date");
				hwRow.setEnd_of_life_date(end_of_life_date);
			LocalDate end_of_sale = row.getDate("end_of_sale");
				hwRow.setEnd_of_sale(end_of_sale);
			LocalDate end_of_support_date = row.getDate("end_of_support_date");
				hwRow.setEnd_of_support_date(end_of_support_date);
			String host_name = row.getString("host_name");
				hwRow.setHost_name(host_name);
			LocalDate import_date = row.getDate("import_date");
				hwRow.setImport_date(import_date);
			List<Software_UDT> installed_software = row.getList("installed_software", Software_UDT.class);
				hwRow.setInstalled_software(installed_software);
			String ip_address = row.getString("ip_address");
				hwRow.setIp_address(ip_address);
			String mac_address = row.getString("mac_address");
				hwRow.setMac_address(mac_address);
			BigDecimal node_impact_value = row.getDecimal("node_impact_value");
				hwRow.setNode_impact_value(node_impact_value);
			String operating_system = row.getString("operating_system");
				hwRow.setOperating_system(operating_system);
			String os_general = row.getString("os_general");
				hwRow.setOs_general(os_general);
			String part = row.getString("part");
				hwRow.setPart(part);
			String product = row.getString("product");
				hwRow.setProduct(product);
			boolean reportable_flag = row.getBool("reportable_flag");	
				hwRow.setReportable_flag(reportable_flag);
			Date run_datetime = row.getTimestamp("run_datetime");
				hwRow.setRun_datetime(run_datetime);
			String system_type = row.getString("system_type");
				hwRow.setSystem_type(system_type);
			String vendor = row.getString("vendor");
				hwRow.setVendor(vendor);
			String vendor_stencil_icon = row.getString("vendor_stencil_icon");
				hwRow.setVendor_stencil_icon(vendor_stencil_icon);
			String version = row.getString("version");
				hwRow.setVersion(version);
			String version_update = row.getString("version_update");
				hwRow.setVersion_update(version_update);
			int vulnerability_count = row.getInt("vulnerability_count");
				hwRow.setVulnerability_count(vulnerability_count);
			hardwareList.add(hwRow);
		}
		slf4jLogger.info(session.getLoggedKeyspace()+".hardware Collection Complete. number of Hardware: " + hardwareList.size());
		return hardwareList;
	}
	
	public static UUID[] getSites(){
		UUID[] sites =  Arrays.copyOf(siteSet.toObjectArray(), siteSet.toObjectArray().length, UUID[].class);
		return sites;
	}
	public static String[] getSubnets(){
		String[] subnets =  Arrays.copyOf(subnetSet.toObjectArray(), subnetSet.toObjectArray().length, String[].class);
		return subnets;
	}
	
	/**@purpose gets the list of Hardware by each site, subnet, and archive_date*/
	public static List<Hardware> getHardwarebySiteSubDate(ResultSet rs){
		List<Hardware> hardwareArchiveList = new ArrayList<Hardware>();
		Iterator<Row>it = rs.iterator();
		//put all the results from the hardware_archive table into a list. 
		while (it.hasNext()){
			Row row = it.next();
			Hardware hardware_archive = new Hardware();
			UUID site_id = row.getUUID("site_id");
				hardware_archive.setSite_id(site_id);
			String ip_subnet_or_building = row.getString("ip_subnet_or_building");
				hardware_archive.setIp_subnet_or_building(ip_subnet_or_building);
			LocalDate archive_date = row.get("archive_date",LocalDate.class);
				hardware_archive.setArchive_date(archive_date);
			List<Cvss_Scores> cvss_scores = row.getList("cvss_scores", Cvss_Scores.class);
				hardware_archive.setCvss_scores(cvss_scores);
			int vulnerability_count = row.getInt("vulnerability_count");
				hardware_archive.setVulnerability_count(vulnerability_count);
			
				hardwareArchiveList.add(hardware_archive);
		}
		return hardwareArchiveList;
	}
	
	/**
	 * @purpose inserts hardware_archive roll-up data into hardware_daily_summary
	 * @param dailySummaryList of roll-up data for the hardware_daily_summary table
	 * @return inserts data into hardware_daily_summary table*/
	public static void insertDailySummaries(List<Hardware_Summary> dailySummaryList){
		Iterator<Hardware_Summary> it = dailySummaryList.iterator();
		LocalDate day = LocalDate.fromMillisSinceEpoch(new Date().getTime());
		while (it.hasNext()){
			Hardware_Summary daily = it.next();
			day = daily.getDay_of_week();
			bound = prepared_insert_daily.bind(daily.getSite_id(), daily.getIp_subnet_or_building(), daily.getWeek_start_date(), daily.getDay_of_week(), daily.getCritical_count(), daily.getHigh_count(), daily.getLow_count(), daily.getMedium_count(), daily.getPatches_count(), daily.getPatches_critical_count(), daily.getPatches_high_count(), daily.getPatches_low_count(), daily.getPatches_medium_count(), daily.getVulnerability_count());
//			System.out.println(daily.getSite_id()+", "+daily.getIp_subnet_or_building()+", "+daily.getWeek_start_date()+", "+daily.getDay_of_week()+", "+daily.getCritical_count()+", "+ daily.getHigh_count()+", "+daily.getLow_count()+", "+ daily.getMedium_count()+", "+ daily.getPatches_count()+", "+ daily.getPatches_critical_count()+", "+ daily.getPatches_high_count()+", "+ daily.getPatches_low_count()+", "+ daily.getPatches_medium_count()+", "+ daily.getVulnerability_count());
			session.execute(bound);
		}
		slf4jLogger.info("Done inserting today's Daily Rollup (day_of_week: "+ day+")");
	}
	
	/**
	 * @purpose inserts hardware_archive roll-up data into hardware_weekly_summary
	 * @param weeklySummaryList of roll-up data for the hardware_weekly_summary table
	 * @return inserts data into hardware_weekly_summary table*/
	public static void insertWeeklySummaries(List<Hardware_Summary> weeklySummaries){
		Iterator<Hardware_Summary> it = weeklySummaries.iterator();
		LocalDate weekStartDate = null;
		while(it.hasNext()){
			Hardware_Summary weekly = it.next();
			weekStartDate = weekly.getWeek_start_date();
			bound = prepared_insert_weekly.bind(weekly.getSite_id(), weekly.getIp_subnet_or_building(), weekly.getMonth_end_date(), weekly.getWeek_start_date(), weekly.getCritical_count(), weekly.getHigh_count(), weekly.getLow_count(),weekly.getMedium_count(), weekly.getPatches_count(), weekly.getPatches_critical_count(), weekly.getPatches_high_count(), weekly.getPatches_low_count(), weekly.getPatches_medium_count(), weekly.getVulnerability_count());
//			System.out.println(weekly.getSite_id()+", "+weekly.getIp_subnet_or_building()+", "+weekly.getMonth_end_date()+", "+weekly.getWeek_start_date()+", "+weekly.getCritical_count()+", "+ weekly.getHigh_count()+", "+weekly.getLow_count()+", "+ weekly.getMedium_count()+", "+ weekly.getPatches_count()+", "+ weekly.getPatches_critical_count()+", "+ weekly.getPatches_high_count()+", "+ weekly.getPatches_low_count()+", "+ weekly.getPatches_medium_count()+", "+ weekly.getVulnerability_count());
			session.execute(bound);
		}
		slf4jLogger.info("Done inserting this week's Weekly Rollup (week_start_date: "+ weekStartDate+")");
	}
	
	/**
	 * @purpose inserts hardware_archive roll-up data into hardware_monthly_summary
	 * @param weeklySummaryList of roll-up data for the hardware_monthly_summary table
	 * @return inserts data into hardware_monthly_summary table*/
	public static void insertMonthlySummaries(List<Hardware_Summary> monthlySummaries){
		Iterator<Hardware_Summary> it = monthlySummaries.iterator();
		LocalDate monthEndDate = null;
		while(it.hasNext()){
			Hardware_Summary monthly = it.next();
			monthEndDate = monthly.getMonth_end_date();
			bound = prepared_insert_monthly.bind(monthly.getSite_id(), monthly.getIp_subnet_or_building(), monthly.getMonth_end_date(), monthly.getCritical_count(), monthly.getHigh_count(), monthly.getLow_count(),monthly.getMedium_count(), monthly.getPatches_count(), monthly.getPatches_critical_count(), monthly.getPatches_high_count(), monthly.getPatches_low_count(), monthly.getPatches_medium_count(), monthly.getVulnerability_count());
//			System.out.println(monthly.getSite_id()+", "+monthly.getIp_subnet_or_building()+", "+monthly.getMonth_end_date()+", "+monthly.getCritical_count()+", "+ monthly.getHigh_count()+", "+monthly.getLow_count()+", "+ monthly.getMedium_count()+", "+ monthly.getPatches_count()+", "+ monthly.getPatches_critical_count()+", "+ monthly.getPatches_high_count()+", "+ monthly.getPatches_low_count()+", "+ monthly.getPatches_medium_count()+", "+ monthly.getVulnerability_count());
			session.execute(bound);
		}
		slf4jLogger.info("Done inserting this month's Monthlyly Rollup (month_end_date: "+ monthEndDate+")");
	}
	
	/**@purpose Iterates through the given list and inserts data into the hardware_archive table
	 * @param A list of rows of hardware
	 * @param the number of days to subtract from the current date to generate a pho-historic archive_date
	 * @return inserts data into the hardware_archive table*/
	public static void insertIntoHardwareArchive(List<Hardware> architecture, int daysSubtracted){
		
		Iterator<Hardware> it = architecture.iterator();
		Date start = new Date();
		Date dnewDate = SummaryUtils.subtractDays(start, daysSubtracted);
		LocalDate lnewDate = LocalDate.fromMillisSinceEpoch(dnewDate.getTime());
		slf4jLogger.info("Archive Date: " + lnewDate);
		while (it.hasNext()){
			Hardware hardware = it.next();
//			starts with today's date and then subtract days
			bound = prepared_insert_archive.bind(hardware.getSite_id(), hardware.getIp_subnet_or_building(), hardware.getInternal_system_id(), hardware.getAsset_type(), hardware.getAsset_visibility(), hardware.getAudit_upsert(),hardware.getBusiness_processes(),
					hardware.getConnected_Elements(), hardware.getCpe_id(), hardware.getCrown_jewel(), hardware.getCvss_scores(), hardware.getEnd_of_life_date(), hardware.getEnd_of_sale(),
					hardware.getEnd_of_support_date(), hardware.getHost_name(), hardware.getInstalled_software(), hardware.getIp_address(),
					hardware.getMac_address(), hardware.getNode_impact_value(), hardware.getOperating_system(), hardware.getOs_general(), hardware.getPart(),
					hardware.getProduct(), hardware.getReportable_flag(), hardware.getRun_datetime(), hardware.getSystem_type(), hardware.getVendor(),hardware.getVendor_stencil_icon(), hardware.getVersion(), hardware.getVersion_update(), 
					hardware.getCvss_scores().size(), lnewDate);
//			System.out.println("archive_date: "+lnewDate+" "+ hardware.toString());
			session.execute(bound);
		}
		slf4jLogger.info("Done inserting hardware into hardware_archive for archive_date " + lnewDate);
	}
	
	/**
	 * @purpose queries the hardware_archive table to create vulnerability roll-up data for the hardware_daily_summary table.
	 * @param sites an array of site UUIDs used to query the hardware_archive table 
	 * @param subnets an array of ip_subnet_or_building Strings used to query the hardware_archive table
	 * @return a list of Hardware_Summary objects to prepare for insert */
	public static List<Hardware_Summary> getDailyRollUp(UUID[] sites, String subnets[]){
		List<Hardware_Summary> dailySummaryList = new ArrayList<Hardware_Summary>();
		
		LocalDate first_archived_date = TableData.getEarliestDate();
		Date today_date = new Date();
		LocalDate today_localDate = LocalDate.fromMillisSinceEpoch(today_date.getTime()); 
		int first_archived_int =first_archived_date.getDaysSinceEpoch();
		int today_int = today_localDate.getDaysSinceEpoch(); 

		//for each archive_date for each subnet at each site(currently only 1 site), consolidate the data into daily summaries. 
		for (int i = first_archived_int;i<=today_int;i++){
			
			//get readable increments
			int day_increment = i-first_archived_int;
			int hour_increment = day_increment * 24;
			LocalDate archived = first_archived_date.add(Calendar.HOUR, hour_increment);

			//gathering each set of data 
			for (UUID site: sites){
				for (String subnet: subnets){
					bound = prepared_select_archived.bind(site,subnet,archived);
					slf4jLogger.info("getDailyRollup querying hardware_archive site:"+site+" ip_subnet_or_building:"+ subnet+" archived:" + archived);
					ResultSet rs = session.execute(bound);
					List<Hardware> hardwareArchiveList = TableData.getHardwarebySiteSubDate(rs);
					List<Hardware_Summary> summaries_by_site_sub_date= SummaryUtils.createSummaryRows(hardwareArchiveList, site, subnet, archived, "daily");
					dailySummaryList.addAll(summaries_by_site_sub_date);
				}
			}
		}
		return dailySummaryList;
	}

	/**
	 * @purpose queries the hardware_archive table to create vulnerability roll-up data for the hardware_weekly_summary table.
	 * @param sites an array of site UUIDs used to query the hardware_archive table 
	 * @param subnets an array of ip_subnet_or_building Strings used to query the hardware_archive table 
	 * @param the starting date used to query the hardware_archive table for unique vulnerabilities within week time spans
	 * @return a list of Hardware_Summary objects to prepare for insert */
	public static List<Hardware_Summary> getWeeklyRollUp(UUID[] sites, String[] subnets, LocalDate archive_date){
		List<Hardware_Summary> weeklySummaryList = new ArrayList<Hardware_Summary>();
		
		LocalDate first_week = SummaryUtils.getWeek_start_date(archive_date, "sunday");
		int first_week_int =first_week.getDaysSinceEpoch();
		int todays_int = SummaryUtils.getTodaysDate().getDaysSinceEpoch();
		int diff = first_week_int%7;

		for (int i = first_week_int;i<=todays_int;i++){
			//skip every 7 days but capture every day to increment
			int day_increment = i-first_week.getDaysSinceEpoch();
			int hour_increment = day_increment * 24;
			if (i%7 !=diff){
				continue;
			}
			//gets start of every week
			LocalDate cur_week = first_week.add(Calendar.HOUR, hour_increment);

			//get a roll-up for each summary
			for (UUID site: sites){
				for (String subnet: subnets){
					aggregateVulns(site, subnet,cur_week,6);
					Hardware_Summary weekly = SummaryUtils.createSummary(site, subnet, cur_week, "weekly");
					weeklySummaryList.add(weekly);
				}		
			}
		}
		return weeklySummaryList;
	}
	
	/**
	 * @purpose queries the hardware_archive table to create vulnerability roll-up data for the hardware_monthly_summary table.
	 * @param sites an array of site UUIDs used to query the hardware_archive table 
	 * @param subnets an array of ip_subnet_or_building Strings used to query the hardware_archive table 
	 * @param the starting date used to query the hardware_archive table for unique vulnerabilities within month time spans
	 * @return a list of Hardware_Summary objects to prepare for insert */
	public static List<Hardware_Summary> getMonthlyRollUp(UUID[] sites, String[] subnets, LocalDate archive_date){
		List<Hardware_Summary> monthlySummaryList = new ArrayList<Hardware_Summary>();
		
		String query = "select site_id, ip_subnet_or_building, internal_system_id, archive_date,cvss_scores, vulnerability_count from hardware_archive "
				+ "where site_id = ? and ip_subnet_or_building = ? and archive_date = ? ";
		PreparedStatement prepared = session.prepare(query);
		LocalDate first_month_start = LocalDate.fromYearMonthDay(archive_date.getYear(),archive_date.getMonth(),1);
		int first_month_int =first_month_start.getDaysSinceEpoch();
		int todays_int = SummaryUtils.getTodaysDate().getDaysSinceEpoch();
		int month = -1; //initialize it to somthing it will never be
//		int april_int = LocalDate.fromYearMonthDay(2018, 4, 30).getDaysSinceEpoch();
		for (int i = first_month_int;i<=todays_int;i++){
//		for (int i = first_month_int;i<=april_int;i++){
			LocalDate this_date = LocalDate.fromDaysSinceEpoch(i);
			if (this_date.getMonth() == month)
				continue;
			month = this_date.getMonth();
			LocalDate this_month_start = LocalDate.fromYearMonthDay(this_date.getYear(), this_date.getMonth(), 1);
			LocalDate next_month_start = this_month_start.add(Calendar.MONTH, 1);
			int days_to_increment = next_month_start.getDaysSinceEpoch() - this_month_start.getDaysSinceEpoch();
//			System.out.println("	agregate the information between "+first_month_start+" and "+next_month_start.add(Calendar.HOUR, -24));
//			System.out.println("		number of days =  "+(todays_int-first_month_int));
			
			LocalDate dateInMonth = next_month_start.add(Calendar.HOUR, -24);
			//get a roll-up for each site and subnet
			for (UUID site: sites){
				for (String subnet: subnets){
					aggregateVulns(site, subnet,this_month_start,days_to_increment-1);
					Hardware_Summary monthly = SummaryUtils.createSummary(site, subnet, this_month_start, "monthly");
//					aggregateVulns(site, subnet,dateInMonth,days_to_increment-1);
//					Hardware_Summary monthly = SummaryUtils.createSummary(site, subnet, dateInMonth, "monthly");
					monthlySummaryList.add(monthly);
				}		
			}
		}
		return monthlySummaryList;
	}
	
	/**
	 * @purpose sorts and counts unique vulnerabilities, comparing vulnerabilities from day to day
	 * @param prepared statement querying the hardware_archive table
	 * @param sites an array of site UUIDs used to query the hardware_archive table 
	 * @param subnets an array of ip_subnet_or_building Strings used to query the hardware_archive table 
	 * @param date to increment to query the hardware_archive table
	 * @period the period of time defined by days to capture and compare days
	 * @return earliest archive_date */
	public static void aggregateVulns(UUID site, String subnet, LocalDate date, int period){

		BoundStatement bound;
		SummaryUtils.clearSummaryCounts();
		boolean recordedFirst = false;
		//compare each day to each other for the roll-up
		for (int d=0;d<period;d++){
//			System.out.println("---------------------------"+date.add(Calendar.HOUR,d*24)+" to "+date.add(Calendar.HOUR,(d+1)*24)+"---------------------------");
			//populate primary day's hardware
			bound = prepared_select_archived.bind(site, subnet, date.add(Calendar.HOUR,d*24));
//			System.out.println("PRIMARY--site: "+site+"--subnet: "+subnet+"--archive_date: "+date.add(Calendar.HOUR,d*24)+"--");
			HashMap<UUID,List<Cvss_Scores>> pri_HardwareMap = SummaryUtils.getHardwareVulnMap(session.execute(bound));

			//capturing the primary vulnerabilities
			if ((!recordedFirst) && pri_HardwareMap.size()>0){
//				System.out.println("	sorting first day "+date.add(Calendar.HOUR,d*24));
				for(UUID cur_internal_sys_id: pri_HardwareMap.keySet())
					SummaryUtils.sortScores(pri_HardwareMap.get(cur_internal_sys_id));
				recordedFirst = true;
			}
			
			//capturing the secondary vulnerabilities
			bound = prepared_select_archived.bind(site, subnet, date.add(Calendar.HOUR,(d+1)*24));
//			System.out.println("SECONDARY--site: "+site+"--subnet: "+subnet+"--archive_date: "+date.add(Calendar.HOUR,(d+1)*24)+"--");
			HashMap<UUID,List<Cvss_Scores>> sec_HardwareMap = SummaryUtils.getHardwareVulnMap(session.execute(bound));

			//cycling through hardware of primary day to compare with secondary day
			for(UUID internal_sys_id: sec_HardwareMap.keySet()){
				if (pri_HardwareMap.containsKey(internal_sys_id)){
					//put primary vulnerabilities into a map
					List<Cvss_Scores> pri_scores = pri_HardwareMap.get(internal_sys_id);
					HashMap<String,BigDecimal> pri_vulnMap = new HashMap<String, BigDecimal>();
					Iterator<Cvss_Scores> pri_it = pri_scores.iterator();
					while (pri_it.hasNext()){
						Cvss_Scores score = pri_it.next();
						pri_vulnMap.put(score.getCve_id(), score.getCvss_base_score());
					}
					//cycle through secondary vulnerabilities and compare to primary
					List<Cvss_Scores> sec_scores = sec_HardwareMap.get(internal_sys_id);
					Iterator<Cvss_Scores> sec_it = sec_scores.iterator();
					while (sec_it.hasNext()){
						Cvss_Scores score = sec_it.next();
						if (pri_vulnMap.containsKey(score.getCve_id()))
							continue;
//						System.out.println("not in previous: "+ score.getCve_id()+", "+score.getCvss_base_score()); not here
						SummaryUtils.sortScore(score);
					}
				}else{
//					System.out.println("hardware not previously recorded "+ internal_sys_id);
					SummaryUtils.sortScores(sec_HardwareMap.get(internal_sys_id));
				}
			}
		}
	}
	
	/**
	 * @purpose retrieves the earliest date in the hardware_archive table
	 * @param non, queries hardware_archive table
	 * @return earliest archive_date */
	public static LocalDate getEarliestDate(){
		UniqueList UniqueList = new UniqueList();
		//recieves error- Bind variables cannot be used for keyspace names- on preparing a PreparedStatement with ?'s at col or table placement.
		bound = prepared_date.bind();
		ResultSet rs = session.execute(bound);
		Iterator<Row> it = rs.iterator();
		LocalDate startDay = null;
		while(it.hasNext()){
			Row row = it.next();
			startDay = row.getDate("archive_date");
		}
		return startDay;
	}
	
}
