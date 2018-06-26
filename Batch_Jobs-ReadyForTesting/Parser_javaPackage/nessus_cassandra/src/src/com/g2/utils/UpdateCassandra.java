package src.com.g2.utils;
/**
 * @author Sara Prokop
 * @date 08/18/2017
 * 
 * The purpose of this class is to connect to the Cassandra database and to update hardware table with the data gathered
 * from the nessus file. 
 * 
 * This class is called when the user selects FILE> SAVE RESULTS> CASSANDRA DATABASE
 * 
 * Modification History
 * Date 				Author				Description
 * 9/6/2017				sara.prokop			Added to connected_elements, cvss_scores, updated host information from nessus file. 
 * 
 */


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import src.com.g2.nessus.Parser;
import src.com.g2.nessus.Warnings;
import src.com.g2.types.Audit_Upsert;
import src.com.g2.types.Connected_Elements;
import src.com.g2.types.Cpe;
import src.com.g2.types.Cvss_Scores;
import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Relationships;
import src.com.g2.types.ReportHost;
import src.com.g2.types.ReportItem;
import src.com.g2.types.Software_UDT;
import src.com.g2.utils.Connect2Db;

public class UpdateCassandra {

	/**class variables**/
	
	private static Connect2Db connection = new Connect2Db();
	private static Session session; 
	private final Logger slf4jLogger = LoggerFactory.getLogger(UpdateCassandra.class);
	
	/*parsed data used to populate database*/
	//maps source to a list of targets
	static Map<String, List<String>> connectedElementsMap = new HashMap<String, List<String>>();
	static List<ReportHost> hostList = new ArrayList<ReportHost>();
	
	private MappingManager manager;
	
	//used to assign the site to the item
	private static UUID site;
	
	private static List<String> insertedIps = new ArrayList<String>();
	
//	private static List<Hardware> hardwareList = new ArrayList<Hardware>();

	public UpdateCassandra(){
		session = connection.getSession();
		manager = new MappingManager(session);
		slf4jLogger.info("session is connected to keyspace: "+session.getLoggedKeyspace());
		manager = new MappingManager(session);
		manager.udtCodec(Connected_Elements.class);
		manager.udtCodec(Cvss_Scores.class);
		manager.udtCodec(Audit_Upsert.class);
		manager.udtCodec(Software_UDT.class);
		manager.udtCodec(Hardware_Relationships.class);
    }
	
	public void save(Parser data){
		//updates lists to be referred to when inserting information into the database
		try{
		connectedElementsMap = data.getConnectedElements();
		hostList = data.getHosts();
//		readSoftware();
		getCvssScores_InstalledSoftware();	
		System.out.println("Done Sorting");
		readSoftware();
		update();
		updateSoftware(data.getSoftwareHostsMap());
		
		System.out.println("Scores Saved");
//		wipeOutConnectedElements(data);
//		System.out.println("connected_elements wiped");
		updateConnectedElements();
		session.close();
		session.getCluster().close();
		System.out.println("Data saved to Cassandra");
		System.out.println("end.");
    	}catch(ExceptionInInitializerError e){
    		Warnings.showAuthenticationError();
    		e.printStackTrace();
    	}catch(NullPointerException e){
    		Warnings.noFile();
    		e.printStackTrace();
    	}
	}
	public void readSoftware(){
		Iterator<ReportHost>  it = hostList.iterator();
		while (it.hasNext()){
			ReportHost host = it.next();
			System.out.println("Host: "+ host.getIpAddress());
			List<Software_UDT> softwareList = host.getInstalledSoftwareList();
			Iterator<Software_UDT> it_soft = softwareList.iterator();
			while (it_soft.hasNext()){
				Software_UDT software = it_soft.next();
				System.out.println("      "+software.getCpe()+ " Title: "+software.getTitle());
			}
		}
	}

	/** @purpose Updates the connected_elements in the hardware table.*/
	public void updateConnectedElements(){
		System.out.println("=============== Update Connected Elements ===============");

		List<String> destinations;
		Hardware_Relationships keys; //keys for the source
		Hardware_Relationships targetKeys; //keys for the target
		UUID targetUUID;
		String query = "Update hardware set connected_elements = ? where internal_system_id = ? AND  site_id = ? AND ip_subnet_or_building = ? ;";
		PreparedStatement prepared = session.prepare(query);

		//information gathered from select to be used in update
		UUID sourceId;
		UUID sourceSite;
		String sourceSub;
		//information gathered from Select to be used in the Update statement and populates the connected_elements column

  	
		BoundStatement bound;
		
		//populates each source with all its connections in the hardware table
		for (String source: connectedElementsMap.keySet()){
			List<Connected_Elements> connected_elements = new ArrayList<Connected_Elements>();
			destinations = connectedElementsMap.get(source);

			//keys for the source. If the source IP was not in the table, it was inserted and its keys are returned
			keys = getKeys(source);
			
			
			//double checks that the ip as a key
			//if there is not a first key, there will not be any keys (and all are needed)
			if (keys.getInternal_system_id() == null){
				insertIp(source);
				insertedIps.add(source);
				System.out.println("Not in Database: " + source);
				keys = getKeys(source);
			}
			//assigning keys for the source
			sourceId = keys.getInternal_system_id();
			sourceSite = keys.getSite_id();
			sourceSub = keys.getIp_subnet_or_building();
			
			//for each source's targets, the target's Id are retrieved for the connected_elements user-data type
			//and placed into the source's connected_elements column. 
			for(String target : destinations){
				Connected_Elements connected_element = new Connected_Elements();
				//get the UUID for the target
				targetKeys = getKeys(target);
				
				//double checks that the target as a key
				if (targetKeys.getInternal_system_id() == null){
					insertIp(target);
					targetKeys = getKeys(target);
				}
				//only the UUID is needed
				targetUUID = targetKeys.getInternal_system_id();
				connected_element.setDestination_id(targetUUID.toString());
				connected_elements.add(connected_element);
//				
//				//change to a String because data type in the database is a string		
//				destination_id = targetUUID.toString();
//				
//				//query and prepared statement are within the loop because Cassandra does not support binding list literals (which includes connected_elements)
//				String query_update = "UPDATE hardware SET "
//		  			+ "connected_elements = connected_elements + "
//		  			+" [{destination_id: '"+ destination_id +"', mission_support: []}]"
//		  			+ " WHERE internal_system_id = ? AND  site_id = ? AND ip_subnet_or_building = ?";
//				PreparedStatement prepared_update = session.prepare(query_update);
//				//updates the hardware table with another entry
//				bound_update = prepared_update.bind(sourceId, sourceSite, sourceSub);
//				session.execute(bound_update);
			}
			bound = prepared.bind(connected_elements, sourceId, sourceSite, sourceSub);
			session.execute(bound);
			System.out.println("updated "+ source );
		}
		System.out.println("Inserted connected_elements");
		Iterator<String> it = insertedIps.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
	//This method assigns the site to each new entry
	public void getSite(){
//		//currently gets only the first row of the table and uses its site_id
//		//This will need to be changed later. 
//		String query = "SELECT * FROM sites LIMIT 1";
//		PreparedStatement prepared = session.prepare(query);
//		BoundStatement bound = prepared.bind();
//		ResultSet rs = session.execute(bound);
//		
//		Row row =rs.one();
//		site = row.getUUID("site_id");
		site = UUID.fromString("96a3378e-2f67-4b9d-ab67-9ee9d533b88f");
		
	}
	
	//inserts a new ip into the hardware table with keys and importDate 
	public void insertIp(String ip){
		String statement_insertHopIp = "INSERT INTO hardware(ip_address, internal_system_id, site_id,ip_subnet_or_building, audit_upsert, reportable_flag) "
				+ "values (?, uuid(), ?, ?, {datechanged: toUnixTimestamp(now()), changedbyusername: 'sara.bergman'}, true)";
		PreparedStatement prepared_insertHopIp = session.prepare(statement_insertHopIp);
		BoundStatement bound_insert;
		ResultSet rs;
		int dot2;
		
		//gets the site from the sites table
		getSite();
		
		//finds the first 2 octets of the ip
		dot2 = StringUtils.ordinalIndexOf(ip, ".", 2);
		String subNet = ip.substring(0,dot2).trim(); 
		
		bound_insert = prepared_insertHopIp.bind(ip, site, subNet);
		//inserts the new ip with an import_date, internal_system_id, site_id, and ip_subnet_or_building
		rs = session.execute(bound_insert);
	}
	
	/**@purpose Given an ip_address, its keys are returned in an object array. 
	 * @return an array of keys
	 * */
	public Hardware_Relationships getKeys(String ip){
		Hardware_Relationships keys = new Hardware_Relationships();
		ResultSet rs;
		List<Row> row;
		 String statement_keys = "SELECT * FROM hardware_by_ip_address WHERE ip_address = ?";
		 PreparedStatement prepared_keys = session.prepare(statement_keys); 
		 BoundStatement bound_keys;
		bound_keys = prepared_keys.bind(ip);
		rs = session.execute(bound_keys);
		row = rs.all();
		//if there are no keys for the ip_address one is inserted
		if (row == null){
			System.out.println("Inserting: " + ip);
			insertIp(ip);
			bound_keys = prepared_keys.bind(ip);
			rs = session.execute(bound_keys);
			row = rs.all();
		}
		//captures all rows in a resultset but only one is expected. 
		Iterator<Row> it = row.iterator();
		while (it.hasNext()){
			Row result = it.next();
			//assigns a key to each index of an array
			keys.setSite_id(result.getUUID("site_id"));
			keys.setIp_subnet_or_building(result.getString("ip_subnet_or_building").trim());
			keys.setInternal_system_id(result.getUUID("internal_system_id"));
		}
	
		return keys;
	}
	
	/**@purpose updates the cvss_scores in the hardware table*/
	public void getCvssScores_InstalledSoftware(){
		String ip;
		List<ReportItem> items;
		//variables assigned to the keys for each host
		Object[] keys;
		UUID hostId;
		UUID hostSite;
		String hostSub;
		String query_cveLookup = "Select cve_description,cvss_base_score, modified_date, patch_count, cvss_vector, patch_references from appl_auth.common_vulnerabilities "
				+ "where cve_id = ? limit 1";
//		String query_cveLookup  = "Select * from appl_auth.breach_types where publication_year = ?; ";
		PreparedStatement prepared_cveLookup= session.prepare(query_cveLookup);
		BoundStatement bound;
		PreparedStatement prepared_addCvss;
		
		System.out.println("======== SORT CVSS SCORE ========");
		for (ReportHost host: hostList){
			//get titles for installed software
			getTitles(host);
			List<Cvss_Scores> cvss_scores = new ArrayList<Cvss_Scores>();
			int vulns = 0;
			
			//list of vulnerabilities
			items = host.getReportItems(); 
	
			//collects each vulnerability for each host 
			//items are ReportItems that contain a cveID 
			for (ReportItem item : items){
				if (item.getCveId() == null)
					continue;
				
				Cvss_Scores score = new Cvss_Scores();
				String cveID = item.getCveId().replaceAll("'", "");
				score.setCve_id(cveID);
//				System.out.println("CVE-ID: " + cveID);
				bound = prepared_cveLookup.bind(cveID);
			try{	
				Row nvd_row = session.execute(bound).one();
				BigDecimal baseScore = nvd_row.getDecimal("cvss_base_score");
					score.setCvss_base_score(baseScore);
				LocalDate vulnMod = nvd_row.getDate("modified_date");
					score.setVuln_modification_date(vulnMod);
				String description= nvd_row.getString("cve_description");
					score.setCve_description(description);
				String cvssVector = nvd_row.getString("cvss_vector");
					score.setCvss_vector(cvssVector);
				int patch_cnt = nvd_row.getInt("patch_count");
					score.setPatch_count(patch_cnt);
				if (patch_cnt>0){
					score.setPatch_references(nvd_row.getList("patch_references", String.class));
				}
			}catch(NullPointerException e){
//				System.out.println(cveID + " "+e.toString());
				score.setCve_description(item.getDescription());
				score.setCvss_base_score(new BigDecimal(item.getBaseScore()));
				score.setCvss_vector(item.getCvssVector());
				score.setVuln_modification_date(item.getVulPublicationDate());
				if (item.getSolution().contains("n/a")){
					score.setPatch_count(0);
				}else{
					score.setPatch_count(1);
				}
				List<String> solution = new ArrayList<String>();
				solution.add(item.getSolution());
				score.setPatch_references(solution);
//				System.out.println(score.toString());
			}
				score.setCvss_temporal_score(new BigDecimal(item.getTempScore()));
				score.setCvss_temporal_vector(item.getCvssTempVector());
				score.setIiv_score(new BigDecimal(0));

//				System.out.println(score.toString());
				
				cvss_scores.add(score);
				vulns++;
				
				}
				host.setCvss_Scores(cvss_scores);
				host.setVunerabilityCount(cvss_scores.size());
			}
		}
	
		public void getTitles(ReportHost host){
			List<Software_UDT> software_udts = new ArrayList<Software_UDT>();
			List<Software_UDT> softwareList = host.getInstalledSoftwareList();
			String query = "Select title from appl_auth.common_platforms where cpe_id_22 = ?";
			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound;
			Iterator<Software_UDT> it = softwareList.iterator();
			while(it.hasNext()){
				Software_UDT software = it.next();
				String cpe = software.getCpe();
				bound = prepared.bind(cpe);
				Row row = session.execute(bound).one();
				System.out.println(cpe);
				String title = getTitle(prepared, cpe);
				software.setTitle(title);
//				System.out.println("cpe: "+ software.getCpe() +" title: "+software.getTitle());
				software_udts.add(software);
			}
			host.setInstalledSoftwareList(software_udts);
		}
		
		public String getTitle(PreparedStatement prepared, String cpe){
			
			BoundStatement bound = prepared.bind(cpe);
			Row row = session.execute(bound).one();
			System.out.println(cpe);
			String title;
				try{
					title = row.getString("title");
					return title;
				}catch(NullPointerException e){
					Cpe cpe_type = new Cpe(cpe);
					String vendor = cpe_type.getVendor().replaceAll("_", " ");
					vendor = vendor.substring(0, 1).toUpperCase()+vendor.substring(1);
					String product = cpe_type.getProduct().replaceAll("_", " ");
					product = product.substring(0, 1).toUpperCase()+product.substring(1);
					String version = cpe_type.getVersion().replaceAll("_", " ");
					String update = cpe_type.getUpdate().replaceAll("_", " ");
					title = vendor +" "+product+" "+version+" "+update+"*";
					title = title.replaceAll("  ", " ");
					title = title.replace("ANY", "");
					title = title.replace("NA", "");
					title = title.replace(" *", "*");
					return title;
				}
		}

	
//	//The following method does not work for updating the cvss scores. the following error occurs:
	//Exception in thread "AWT-EventQueue-0" com.datastax.driver.core.exceptions.InvalidQueryException: Invalid list literal for cvss_scores: bind variables are not supported inside collection literals
	//at com.datastax.driver.core.exceptions.InvalidQueryException.copy(InvalidQueryException.java:50)
	//at com.datastax.driver.core.DriverThrowables.propagateCause(DriverThrowables.java:37)
	//at com.datastax.driver.core.AbstractSession.prepare(AbstractSession.java:104)
	//at src.com.g2.nessus.UpdateCassandra.updateCvssScores(UpdateCassandra.java:384)
//	public void updateCvssScores(){
//		//data elements that are captured per host and updated into the database.
//		String ip;
//		String cveID;
//		double baseScore;
//		double tempScore;
//		String description;
//		String vulnPub;
//		String patchPub;
//		String solution;
//		List<ReportItem> items;
//		//variables assigned to the keys for each host
//		Object[] keys;
//		UUID hostId;
//		UUID hostSite;
//		String hostSub;
//		manager.udtCodec(Cvss_Scores.class);
//		PreparedStatement prepared_addCvss;
//		BoundStatement bound_addCvss;
//		
//		System.out.println("======== UPDATE CVSS SCORE ========");
//		//updates the cvss_scores column with vulnerabilities for each host
//		for (ReportHost host: hostList){
//			//list of vulnerabilities
//			items = host.getReportItems(); 
//			//gets the keys for each host
//			ip = host.getIpAddress();
//			keys = getKeys(ip);
//			
//			//double checks that the ip as a key
//			//if there is not a first key, there will not be any keys (and all are needed)
//			if(keys[0] == null){
//				insertIp(ip);
//				keys = getKeys(ip);
//			}
//			hostId = (UUID) keys[0];
//			hostSite = (UUID) keys[1];
//			hostSub = (String) keys[2];
//			int vulns = 0;
//			
//			//inserts each vulnerability for each host 
//			//items are ReportItems that contain a cveID 
//			for (ReportItem item : items){
//				baseScore = item.getBaseScore();
//				if (baseScore == 0)
//					continue;
//				Cvss_Scores score = new Cvss_Scores();
//				score.setCve_id(item.getCveId());
//				score.setCvss_temporal_score(item.getTempScore());
//				score.setCve_description(item.getDescription());
//				score.setVuln_publication_date(item.getVulPublicationDate());
//				score.setPatch_publication_date(item.getPatchPublicationDate());
//				score.setSolution(item.getSolution());
//				score.setCvss_temporal_vector(item.getCvssTempVector());
//				score.setCvss_vector(item.getCvssVector());
//				vulns++;
//				
////				System.out.println("host with base score: "+ host.getHost()+ " <> "+ baseScore);
//				
//				//query and prepared statement are included in the for loop because Cassandra does not allow binding of list literals (which includes the user-type cvss_scores)
//				String query_addCvss = "UPDATE hardware SET vulnerability_count = ?, cvss_scores = cvss_scores + [?]"
//								+ " WHERE internal_system_id = ? and site_id = ? and ip_subnet_or_building = ? ;";
//				prepared_addCvss = session.prepare(query_addCvss);
//				//updates the cvss_scores column in the hardware table with another item.
//				bound_addCvss = prepared_addCvss.bind(vulns, score, hostId, hostSite, hostSub);
//				session.execute(bound_addCvss);
//				
//			}
//		}
//		System.out.println("Updated Cvss Scores.");		
//	}
	
	//this method is used to update the hardware table with information taken from each host. 
	public void update(){
		
		System.out.println("======== UPDATE GENERAL ========");
		
		String query_hardware = "UPDATE hardware "
				+ "SET host_name = ?, " //+ name
				+ " ip_address = ?, "// + ip
				+ " mac_address = ?, "// + mac
				+ " operating_system = ? , "// +operatingSystem
				+ " os_general = ?, "// + os
				+ " vendor = ?, " //+ vendor
				+ " system_type = ?, " //+ systemType 
				+ " run_datetime = ?, "//+runDate
				+ " cvss_scores = ?, "
				+ " vulnerability_count = ?, "
				+ " installed_software = ?, "
				+ " reportable_flag = ?, "
				+ " import_date = toDate(now()), "
				+ " audit_upsert = {datechanged: toUnixTimestamp(now()), changedbyusername: 'sara.bergman'}"
				+ " WHERE internal_system_id = ? and site_id = ? and ip_subnet_or_building = ? "; //+ count;
		PreparedStatement prepared_hardware = session.prepare(query_hardware);
		
		String query_software = "INSERT INTO software(title, part, vendor, product, version, cpe_update, edition, language, sw_edition, target_hw, target_sw, other)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepared_software = session.prepare(query_software);
		
		for (ReportHost host : hostList){
			String name = host.getHost().trim();
			String ip = host.getIpAddress().trim();
			String mac = host.getMacAddress().trim();
			String operatingSystem = host.getOperatingSystem().trim();
			String os = host.getOs().trim();
			String vendor = host.getVendor().trim();
			String systemType = host.getSystemType().trim();
			Date runDate= host.getRunDate(); //LocalDate needed for the Cassandra date data type entry 
			List<Cvss_Scores> cvss_scores = host.getCvss_Scores();
			int count = cvss_scores.size();
			List<Software_UDT> installed_software = host.getInstalledSoftwareList();
			boolean reportable_flag = true;
			
			//getting the keys for each ip
			Hardware_Relationships keys = getKeys(ip);
			if (keys.getInternal_system_id() == null){
				insertIp(ip);
				keys = getKeys(ip);
			}
			UUID hostId = keys.getInternal_system_id();
			UUID hostSite = keys.getSite_id();
			String hostSub = keys.getIp_subnet_or_building();
			hostSub = hostSub.trim();
			
			System.out.println(name+", "+ip+", "+mac+", "+operatingSystem+", "+os+", "+vendor+", "+systemType+", "+runDate+", "+count+", "+ hostId+", "+hostSite+", "+hostSub);
//			for (Cvss_Scores score : cvss_scores){
//				System.out.println(score.toString());
//			}
			BoundStatement bound = prepared_hardware.bind(name, ip, mac, operatingSystem, os, vendor, systemType, runDate, cvss_scores, count,installed_software, reportable_flag, hostId, hostSite, hostSub); //count after vendor
			session.execute(bound);
			
			
		}//for host end
		System.out.println("Done with General Update");
	}//update() end
	
	public void updateSoftware(HashMap<String,List<ReportHost>> softwareHosts){
		String query_title = "Select title from appl_auth.common_platforms where cpe_id_22 = ?";
		PreparedStatement prepared_title = session.prepare(query_title);
		String query_ip = "Select * from hardware_by_ip_address where ip_address = ?";
		PreparedStatement prepared_ip = session.prepare(query_ip);
		String query_software = "INSERT INTO software(title, cpe_id, part, vendor, product, version, cpe_update, edition, language, sw_edition, target_hw, target_sw, other,installed_on_hardware )"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prepared_software = session.prepare(query_software);
		BoundStatement bound;
		
		for (String cpe : softwareHosts.keySet()){
			List<Hardware_Relationships> hostids = new ArrayList<Hardware_Relationships>();
			List<ReportHost> hosts = softwareHosts.get(cpe);
			Iterator<ReportHost> it = hosts.iterator();
			while (it.hasNext()){
				String ip = it.next().getIpAddress();
				bound = prepared_ip.bind(ip);
				Row row = session.execute(bound).one();
				Hardware_Relationships hr = new Hardware_Relationships();
				UUID site = row.getUUID("site_id");
				hr.setSite_id(site);
				String subnet = row.getString("ip_subnet_or_building");
				hr.setIp_subnet_or_building(subnet);
				UUID id = row.getUUID("internal_system_id");
				hr.setInternal_system_id(id);
				hostids.add(hr);
			}
			Cpe cpe_type = new Cpe(cpe);
			String title = getTitle(prepared_title, cpe);
			bound = prepared_software.bind(title, cpe, cpe_type.getPart(), cpe_type.getVendor(), cpe_type.getProduct(), cpe_type.getVersion(), cpe_type.getUpdate(), cpe_type.getEdition(), cpe_type.getLanguage(), cpe_type.getSw_edition(), cpe_type.getTarget_hw(), cpe_type.getTarget_sw(), cpe_type.getOther(), hostids);
			System.out.println(title+", "+cpe+", "+cpe_type.getPart()+", "+cpe_type.getVendor()+", "+cpe_type.getProduct()+", "+cpe_type.getVersion()+", "+cpe_type.getUpdate()+", "+cpe_type.getEdition()+", "+cpe_type.getLanguage()+", "+cpe_type.getSw_edition()+", "+cpe_type.getTarget_hw()+", "+cpe_type.getTarget_sw()+", "+cpe_type.getOther()+", "+hostids.size());
			session.execute(bound);
		}
	}
	
//	//if this method is ran separately (without update() or adding the cvss scores or connected elements, it gives the right count. 
//	//if it runs after these methods, then the vulnerability count will be a multiple of what it should be. 
//	//currently it is not included when the information is saved to the database. 
//	public void vulnerabilityCount(){
//		
//		String vulnerability_query = "UPDATE hardware SET vulnerability_count = ? WHERE internal_system_id = ? and site_id = ? and ip_subnet_or_building = ?";
//		PreparedStatement prepared = session.prepare(vulnerability_query);
//		for (ReportHost host: hostList){
//			String ip = host.getIpAddress();
//			int count = host.getVulnerabilityCount();
//			
//			Object[] keys = getKeys(ip);
//			if (keys[0] == null){
//				insertIp(ip);
//				keys = getKeys(ip);
//			}
//			UUID hostId = (UUID) keys[0];
//			UUID hostSite = (UUID) keys[1];
//			String hostSub = (String) keys[2];
//			hostSub = hostSub.trim();
//			
//		System.out.println("Vulnerability Count: " + count+ " for IP Address: " + ip + " Internal_system_id: " + hostId + " site_id: " + hostSite + " Sub: " + hostSub);
//		BoundStatement bound = prepared.bind(count, hostId, hostSite, hostSub);
//		session.execute(bound);
//		}
//	}
	
	public void wipeoutCvssScores(Parser parser){
		hostList = parser.getHosts();
		Iterator<ReportHost> it = hostList.iterator();
		String query = "update hardware set cvss_scores = null, vulnerability_count = 0 where internal_system_id = ? and site_id = ? and ip_subnet_or_building = ?";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		
		while (it.hasNext()){
			ReportHost host = it.next();
			Hardware_Relationships keys = getKeys(host.getIpAddress());
			
			UUID hostId = keys.getInternal_system_id();
			UUID hostSite = keys.getSite_id();
			String hostSub = keys.getIp_subnet_or_building();
			hostSub = hostSub.trim();
			
			bound = prepared.bind(hostId, hostSite, hostSub);
			session.execute(bound);
		}
	}
		
		public void wipeOutConnectedElements(Parser parser){
			hostList = parser.getHosts();
//			Iterator<ReportHost> it = hostList.iterator();
//			String query = "update hardware set connected_elements = null where internal_system_id = ? and site_id = ? and ip_subnet_or_building = ?";
//			PreparedStatement prepared = session.prepare(query);
//			BoundStatement bound;
//			
//			while (it.hasNext()){
//				ReportHost host = it.next();
//				Object[] keys = getKeys(host.getIpAddress());
//				
//				UUID hostId = (UUID) keys[0];
//				UUID hostSite = (UUID) keys[1];
//				String hostSub = (String) keys[2];
//				hostSub = hostSub.trim();
//				
//				bound = prepared.bind(hostId, hostSite, hostSub);
//				session.execute(bound);
//			}
		
	}
	
	
}
