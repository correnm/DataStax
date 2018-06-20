package src.com.g2.jobs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AuthenticationException;
import com.datastax.driver.mapping.MappingManager;

import src.com.g2.types.Audit_Upsert;
import src.com.g2.types.Connected_Elements;
import src.com.g2.types.Cvss_Scores;
import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Relationships;
import src.com.g2.types.Software_UDT;
import src.com.g2.utils.Connect2Db;
import src.com.g2.utils.TableData;
import src.com.g2.utils.VulnUtils;

 
public class CvssScoresUpdate {
	/**Connect to Database */
	private static Connect2Db connection = new Connect2Db();
	private static Session session;
	//maping classes
	private static MappingManager manager;
	/**Valid Keyspaces*/
	static List<String> keyspaces = new ArrayList<String>(Arrays.asList("dod", "g2", "vmasc", "appl_auth"));
	//logging
	private final static Logger slf4jLogger = LoggerFactory.getLogger(CvssScoresUpdate.class);
	private static BoundStatement bound;
	private static PreparedStatement prepared_update_record;
	private static PreparedStatement prepared_select_cve;
	private static PreparedStatement prepared_select_cve_true;
	private static PreparedStatement prepared_select_cve_false;
	private static PreparedStatement prepared_select_cve_null;

	
	/**@purpose to update the organization's hardware table with newly updated cvss_scores from the common_vulnerabilities table.*/
	public static void main(String args[]){
		try{
		//check if the keyspace is valid
		if (!validKeyspace(args[0]))
			return;
		String keyspace = args[0];
		if (!(args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("false"))){
			slf4jLogger.error("Please specify 'true' if updating after a scan, or 'false' for scheduling 2hr updates.");
			connection.getSession().close();
			connection.getSession().getCluster().close();
			return;
		}
		boolean afterScan = Boolean.valueOf(args[1].toLowerCase());  
		session = connection.connectToKeyspace(keyspace);
		prepareQueries();
		//get the current list of the organization's hardware
		List<Hardware> hardwareList = TableData.collectHwTable(keyspace); //To Do:check for when list is null
		Iterator<Hardware> it = hardwareList.iterator();
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		while(it.hasNext()){
			Hardware hw = it.next();
//			System.out.println("*****************Host: "+hw.getIp_address()+"*****************");
			//get installed_software, run_datetime, and cvss_scores from hardware
			List<Software_UDT> installed_software = hw.getInstalled_software();
			List<Cvss_Scores> cvss_scores = hw.getCvss_scores();
			Date run_datetime = hw.getRun_datetime();
//			System.out.println("run_datetime: "+ run_datetime);
//			System.out.println("installed_software: "+ installed_software.size());
			if (run_datetime != null){
				//update current scores in the hardware table
				cvss_scores = updateScoring(cvss_scores);
			}
			//get any new scores that apply to any of the installed_software 
			Audit_Upsert audit = new Audit_Upsert();
			audit.setChangedbyusername("sara.bergman");
			audit.setDatechanged(new Date());
			Date run_datetime_new = new Date();
			cvss_scores = getNewScores_hardware(installed_software, cvss_scores, run_datetime, afterScan);
			bound = prepared_update_record.bind(cvss_scores, cvss_scores.size(), run_datetime_new, audit, hw.getSite_id(), hw.getIp_subnet_or_building(), hw.getInternal_system_id());
//			System.out.println(cvss_scores.size()+", "+hw.getSite_id()+", "+hw.getIp_subnet_or_building()+", "+hw.getInternal_system_id()+", "+run_datetime_new);
			Iterator<Cvss_Scores> it_scores = cvss_scores.iterator();
//			while (it_scores.hasNext()){
//				System.out.println(it_scores.next().toString());
//			}
			session.execute(bound);
		}
		slf4jLogger.info("cvss_scores are updated in "+session.getLoggedKeyspace()+".hardware with new run_datetime: "+ new Date());
		session.close();
		session.getCluster().close();
		slf4jLogger.info("Session closed: "+Boolean.toString(session.isClosed()));
		}catch(NullPointerException e){
			slf4jLogger.error(e.toString());
			e.printStackTrace();
			connection.getSession().close();
			connection.getSession().getCluster().close();
			return;
		}catch(ArrayIndexOutOfBoundsException e1){
			slf4jLogger.error(e1.toString());
			slf4jLogger.error("Please spcify the orginization keyspace and true or false if the update is running immediately after a scan.");
			connection.getSession().close();
			connection.getSession().getCluster().close();
			return;
		}
	}
	
	/**@purpose to update a specific score with the most recent information in the common_vulnerabilities table
	 * @param cvss_scores a hardware's list of vulnerabilities
	 * @return cvss_scores with updated scoring */
	public static List<Cvss_Scores> updateScoring(List<Cvss_Scores> cvss_scores){
//		session = connection.connectToKeyspace("appl_auth");
		//create query for getting updated scoring for current scores.
		Iterator<Cvss_Scores> it = cvss_scores.iterator();
		while (it.hasNext()){
			Cvss_Scores score = it.next();
			bound = prepared_select_cve.bind(score.getCve_id());
//			System.out.println("Select * from common_vulnerabilities where cve_id = "+score.getCve_id()+" limit 1 ");
			try{
				ResultSet rs = session.execute(bound);
				score = VulnUtils.updateScore(score, rs);
			}catch(NullPointerException e){
				//occurs if the CVEID is (for an unexpected reason) not in the common_vulnerabilities database
				slf4jLogger.warn(score.getCve_id()+" is not in the common_vulnerabilities table.");
			}
		}
		return cvss_scores;
	}
	
	/**@purpose to create a list of cve_id's from a list of Cvss_Scores to use for comparison. 
	 * @paparm List<Cvss_Scores> cvss_scores 
	 * @return List<String> of only the cve_ids from the cvss_scores */
	public static List<String> getCveIds(List<Cvss_Scores> cvss_scores){
		List<String> cve_ids = new ArrayList<String>();
		Iterator<Cvss_Scores> it = cvss_scores.iterator();
		while (it.hasNext()){
			Cvss_Scores cur_score = it.next();
			cve_ids.add(cur_score.getCve_id());
		}
		return cve_ids;
	}
	
	/**@purpose to get all the new cvss_scores that apply to a hardware's installed software past a specific date.
	 * @param hardware's installed_software
	 * @param hardware's current list of cvss_scores
	 * @param last run_datetime of scan
	 * @return an updated list of cvss_scores*/
	public static List<Cvss_Scores> getNewScores_hardware(List<Software_UDT> installed_software, List<Cvss_Scores> cur_cvss_scores, Date run_datetime, boolean afterScan){
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		List<Cvss_Scores> cvss_scores = new ArrayList<Cvss_Scores>();
		cvss_scores.addAll(cur_cvss_scores);
//		session = connection.connectToKeyspace("appl_auth");
		List<String> cve_ids = getCveIds(cvss_scores); //all cveids for 1 hardware
		LocalDate run_date = null;//LocalDate.fromMillisSinceEpoch(new Date().getTime());
		if (run_datetime != null)
			run_date = LocalDate.fromMillisSinceEpoch(run_datetime.getTime());
		//bid values to query
		Iterator<Software_UDT> it = installed_software.iterator();
		while(it.hasNext()){
			String cpe = it.next().getCpe();
			if (afterScan){
				bound = prepared_select_cve_true.bind(cpe,run_date);
			}else if (run_datetime == null && !afterScan){
				bound = prepared_select_cve_null.bind(cpe);
			}else{
				bound = prepared_select_cve_false.bind(cpe, run_datetime);
			}
			try{
				ResultSet rs = session.execute(bound);
				List<Cvss_Scores> cpe_cvss_scores = VulnUtils.getNewScores_software(rs,cve_ids);
				cvss_scores.addAll(cpe_cvss_scores);
				cve_ids = getCveIds(cvss_scores);
			}catch(NullPointerException e){
				//catches if there are no updated vulnerabilities for the cpe.
				slf4jLogger.warn("No new vulnerabilities were detected for "+cpe);
			}
		}
		return cvss_scores;
	}

	
	/**@purpose to check if the keyspace is valid and map the appropriate classes to UDT values.*/
	public static boolean validKeyspace(String keyspace){
		boolean result = false;
		if (!(keyspaces.contains(keyspace))){
			slf4jLogger.error(keyspace+" is not a valid keyspace.");
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
			manager = new MappingManager(session);
			manager.udtCodec(Connected_Elements.class);
			manager.udtCodec(Cvss_Scores.class);
			manager.udtCodec(Audit_Upsert.class);
			manager.udtCodec(Hardware_Relationships.class);
		}catch(AuthenticationException e){
			slf4jLogger.error(e.toString() +" connecting to keyspace "+ keyspace);
			return false;
		}
		return result;
	}
	
	/**@purpose to prepare all queries once to avoid anti-patterns when querying the database.*/
	public static void prepareQueries(){
		String query_update_record = "Update hardware Set cvss_scores = ?, vulnerability_count = ?, run_datetime = ?, audit_upsert = ? where site_id = ?and ip_subnet_or_building = ? and internal_system_id = ? ";
		prepared_update_record = session.prepare(query_update_record);
		
		String query_cve = "Select * from appl_auth.common_vulnerabilities where cve_id = ? limit 2 ";
		prepared_select_cve = session.prepare(query_cve);
		
		String query_true = "Select * from appl_auth.common_vuln_by_cpe_modified where cpe_affected = ? and modified_date >= ?";
		prepared_select_cve_true = session.prepare(query_true);
		
		String query_false = "Select * from appl_auth.common_vuln_by_cpe_update where cpe_affected = ? and update_datetime >= ?";
		prepared_select_cve_false = session.prepare(query_false);
		
		String query_null = "Select * from appl_auth.common_vuln_by_cpe_modified where cpe_affected = ?;";
		prepared_select_cve_null = session.prepare(query_null);
	}
}
