package src.com.g2.utils;
import java.util.UUID;

import com.datastax.driver.core.LocalDate;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.ZoneId;

/**
 * @author Sara Bergman
 * @date 10-Jan-2018
 * @purpose The purpose of the class is to randomize the current nessus file to "create" 2 years (730 days) worth of data 
 * in the hardware_archive table of the database. 
 * 
 * 
 *  */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.AuthenticationException;
import com.datastax.driver.mapping.MappingManager;

import src.com.g2.types.Audit_Upsert;
import src.com.g2.types.Connected_Elements;
import src.com.g2.types.Cvss_Scores;
import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Archive;
import src.com.g2.types.Hardware_Summary;
import src.com.g2.types.Software;
import src.com.g2.types.UniqueList;


public class Batches {
//	private static Connect2Db connection = new Connect2Db();
//	private static Session session = Connect2Db.getSession(); 
	MappingManager manager;
	
	private static List<Hardware> hardwareList = new ArrayList<Hardware>();
	private static List<Software> softwareList = new ArrayList<Software>();
	
	private static String USERNAME = "sara_bergman";//sara_prokop//washington 
	private static String PASSWORD = "password"; //password//7579659330

	private static String[] CONTACT_POINTS = {"52.44.86.234"}; 
	private static int PORT = 9042;
	private static AuthProvider authProvider = new PlainTextAuthProvider(USERNAME, PASSWORD);
	/*connecting to database*/
	private static Cluster cluster = Cluster.builder().addContactPoints(CONTACT_POINTS).withPort(PORT).withAuthProvider(authProvider).build();
	private static Session session;
	private static UUID site = UUID.fromString("96a3378e-2f67-4b9d-ab67-9ee9d533b88f");
	private static String[] subnetArray = new String[]{"10.101","10.106","10.120", "corporate"};
	
//	private static List<Hardware_Archive> hardwareArchiveList;
//	public static Map<UUID, List<Cvss_Scores>> deviceVulnMap = new HashMap<UUID, List<Cvss_Scores>>();
	private static List<Cvss_Scores> criticalList;
	private static int patch_count_critical;
	private static List<Cvss_Scores> highList;
	private static int patch_count_high;
	private static List<Cvss_Scores> mediumList;
	private static int patch_count_medium;
	private static List<Cvss_Scores> lowList;
	private static int patch_count_low;
	
	private String part;
	private String vendor;
	private String product;
	private String version;
	private String update;
	private String edition;
	private String language;
	private String sw_edition;
	private String target_sw;
	private String target_hw;
	private String other;
	
	private final Logger slf4jLogger = LoggerFactory.getLogger(Batches.class);
	
	
	public Batches(){}
	
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_monthly_sumary table
	 * @param keyspace containing the hardware_archive and hardware_monthly_summary tables
	 * @return none; inserts data into the database */
	public void rollupHardwareMonthly(String keyspace){
		if (!validKeyspace(keyspace))
			return;
		List<Hardware_Summary> monthlyRollups = getHardwareMonthlyRollup();
		insertMonthlySummaries(monthlyRollups);
		System.out.println("Done with monthly summary");
	}
/////////////////////////////////////////-------------Hardware_Monthly_Summary-------------/////////////////////////////////////////
	/**
	 * @purpose queries the hardware_archive table to create vulnerability roll-up data for the hardware_monthly_summary table.
	 * @param none, queries database for unique vulnerabilities within a month time spans
	 * @return a list of Hardware_Monthly_Summary objects to prepare for insert */
	public List<Hardware_Summary> getHardwareMonthlyRollup(){
		
		List<Hardware_Summary> monthlySummaryList = new ArrayList<Hardware_Summary>();
		LocalDate first_archive_date = getEarliestDate();
		LocalDate first_month_end = getMonth_end(first_archive_date);
		LocalDate first_month_start = LocalDate.fromYearMonthDay(first_archive_date.getYear(),first_archive_date.getMonth(),1);

		//today's date as a LocalDate
		Date today_date = new Date();
		LocalDate today_localDate = LocalDate.fromMillisSinceEpoch(today_date.getTime()); 
		int first_month_int =first_month_start.getDaysSinceEpoch();
		int today_int = today_localDate.getDaysSinceEpoch();
		int month = -1; //initialize it to somthing it will never be
		String query = "select internal_system_id,cvss_scores from hardware_archive "
				+ "where site_id = ? and ip_subnet_or_building = ? and archive_date = ? ";
		PreparedStatement prepared = session.prepare(query);
		String query_device = "select internal_system_id, cvss_scores from hardware_archive where site_id = ? and ip_subnet_or_building = ? and archive_date = ? and internal_system_id = ?";
		PreparedStatement prepared_device = session.prepare(query_device);
		BoundStatement bound;
		ResultSet rs;
		Row row;
		for (int i = first_month_int;i<=today_int;i++){
			
			LocalDate this_date = LocalDate.fromDaysSinceEpoch(i);
			if (this_date.getMonth() == month)
				continue;
			month = this_date.getMonth();
			System.out.println("Month: " + month);
			
			LocalDate this_month_start = LocalDate.fromYearMonthDay(this_date.getYear(), this_date.getMonth(), 1);
			LocalDate next_month_start = this_month_start.add(Calendar.MONTH, 1);
			int days_to_increment = next_month_start.getDaysSinceEpoch() - this_month_start.getDaysSinceEpoch();
			System.out.println("Number of days within each month: " + days_to_increment);
			
			//get a roll-up for each summary
			for (String sub: subnetArray){
				int vulnCount = 0;
				criticalList= new ArrayList<Cvss_Scores>();
				highList= new ArrayList<Cvss_Scores>();
				mediumList= new ArrayList<Cvss_Scores>();
				lowList= new ArrayList<Cvss_Scores>();
				patch_count_critical= 0;
				patch_count_high= 0;
				patch_count_medium= 0;
				patch_count_low= 0;
				boolean recordedFirst = false;
//				System.out.println("=============Subnet: "+sub+"===============");
				
				//compare each day to each other for the roll-up
				for (int d=0;d<days_to_increment-1;d++){ //exclude the last day
					
					System.out.println("---days: "+ this_month_start.add(Calendar.HOUR,d*24).toString()+ " to "+ this_month_start.add(Calendar.HOUR,(d+1)*24).toString()+"---");
					System.out.println("|vulnerabilities before comparison: " + vulnCount);
					System.out.println("|querying to fill pri_HardwareMap "+ site+" "+ sub+" "+ this_month_start.add(Calendar.HOUR,d*24));
					
					//populate primary day's hardware
					bound = prepared.bind(site, sub, this_month_start.add(Calendar.HOUR,d*24));
					rs = session.execute(bound);
					HashMap<UUID,List<Cvss_Scores>> pri_HardwareMap = getHardwareVulnMap(rs);
					
//					System.out.println("|pri_HardwareMap size: " + pri_HardwareMap.size());
					
					//capturing the primary vulnerabilities
					if ((!recordedFirst) && pri_HardwareMap.size()>0){
						for(UUID cur_internal_sys_id: pri_HardwareMap.keySet()){
							Iterator<Cvss_Scores> it = pri_HardwareMap.get(cur_internal_sys_id).iterator();
							while(it.hasNext()){
								Cvss_Scores score = it.next();
								sortVulnerability(score);
							}
//							sortVulnerabilities(pri_HardwareMap.get(cur_internal_sys_id));
							vulnCount = vulnCount + pri_HardwareMap.get(cur_internal_sys_id).size();
//							System.out.println("|vulnCount: "+ vulnCount);
						}
						recordedFirst = true;
						System.out.println("|got the primary vulnerabilities on day "+ d + " vulnCount: "+ vulnCount);
					}
					System.out.println("|querying to fill sec_HardwareMap "+ site+" "+ sub+" "+ this_month_start.add(Calendar.HOUR,(d+1)*24).toString());
					
					//capturing the secondary vulnerabilities
					bound = prepared.bind(site, sub, this_month_start.add(Calendar.HOUR,(d+1)*24));
					rs = session.execute(bound);
					HashMap<UUID,List<Cvss_Scores>> sec_HardwareMap = getHardwareVulnMap(rs);

//					System.out.println("|sec_HardwareMap size: " + sec_HardwareMap.size());
					
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
									sortVulnerability(score);
									vulnCount++;
//									System.out.println("|new vulnerability/ vulnCount: " + vulnCount);
								}
						}else{
							vulnCount = vulnCount+ sec_HardwareMap.get(internal_sys_id).size();
							Iterator<Cvss_Scores> it = sec_HardwareMap.get(internal_sys_id).iterator();
							while (it.hasNext()){
								Cvss_Scores score = it.next();
								sortVulnerability(score);
							}
//							System.out.println("|added "+internal_sys_id+" "+sec_HardwareMap.get(internal_sys_id).size()+" vulnCount: " + vulnCount);
						}
					}
					System.out.println("|vulnerabilities for day "+d+": " + vulnCount);
				}
//				
				LocalDate month_end_date = getMonth_end(this_month_start);
				Hardware_Summary monthly = new Hardware_Summary();
				
				monthly.setSite_id(site);
				monthly.setIp_subnet_or_building(sub);
				monthly.setMonth_end_date(month_end_date);
				monthly.setCritical_count(criticalList.size());
				monthly.setHigh_count(highList.size());
				monthly.setMeduim_count(mediumList.size());
				monthly.setLow_count(lowList.size());
				monthly.setPatches_count(patch_count_critical + patch_count_high + patch_count_medium + patch_count_low);
				monthly.setPatches_critical_count(patch_count_critical);
				monthly.setPatches_high_count(patch_count_high);
				monthly.setPatches_medium_count(patch_count_medium);
				monthly.setPatches_low_count(patch_count_low);
				monthly.setVulnerability_count(criticalList.size() + highList.size() + mediumList.size() + lowList.size());
				System.out.println(monthly.toString());
				monthlySummaryList.add(monthly);
			}
		}
		return monthlySummaryList;
	}
	/**
	 * @purpose inserts hardware_archive roll-up data into hardware_monthly_summary
	 * @param weeklySummaryList of roll-up data for the hardware_monthly_summary table
	 * @return inserts data into hardware_monthly_summary table*/
	public void insertMonthlySummaries(List<Hardware_Summary> monthlySummaries){
		String query = "INSERT INTO hardware_monthly_summary (site_id, ip_subnet_or_building, month_end_date,critical_count, high_count, low_count, medium_count, "
				+ "patches_count, patches_critical_count, patches_high_count, patches_low_count, patches_medium_count, vulnerability_count) "
				+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		Iterator<Hardware_Summary> it = monthlySummaries.iterator();
		while(it.hasNext()){
			Hardware_Summary monthly = it.next();
			bound = prepared.bind(monthly.getSite_id(), monthly.getIp_subnet_or_building(), monthly.getMonth_end_date(), monthly.getCritical_count(), monthly.getHigh_count(), monthly.getLow_count(),monthly.getMedium_count(), monthly.getPatches_count(), monthly.getPatches_critical_count(), monthly.getPatches_high_count(), monthly.getPatches_low_count(), monthly.getPatches_medium_count(), monthly.getVulnerability_count());
			session.execute(bound);
		}
	}
	
/////////////////////////////////////////-------------Hardware_Weekly_Summary-------------/////////////////////////////////////////
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_weekly_sumary table
	 * @param keyspace containing the hardware_archive and hardware_weekly_summary tables
	 * @return none; inserts data into the database */
	public void rollupHardwareWeekly(String keyspace){
		if (!validKeyspace(keyspace))
			return;
		List<Hardware_Summary> weeklyRollups = getWeeklyRollUp();
		insertWeeklySummaries(weeklyRollups);
		System.out.println("Done inserting.");
	}
	
	/**
	 * @purpose queries the hardware_archive table to create vulnerability roll-up data for the hardware_weekly_summary table.
	 * @param none, queries database for unique vulnerabilities within week time spans
	 * @return a list of Hardware_Weely_Summary objects to prepare for insert */
	public List<Hardware_Summary> getWeeklyRollUp(){
		List<Hardware_Summary> weeklySummaryList = new ArrayList<Hardware_Summary>();
		LocalDate first_archive_date = getEarliestDate();
		//first archive_date in hardware_archive
		LocalDate first_week = getWeek_start_date(first_archive_date, "sunday");
		//end of the week of the first archive_date
		LocalDate weekEnd = getWeek_end_date(first_week);
		//today's date as a LocalDate
		Date today_date = new Date();
		LocalDate today_localDate = LocalDate.fromMillisSinceEpoch(today_date.getTime()); 
		int first_week_int =first_week.getDaysSinceEpoch();
		int today_int = today_localDate.getDaysSinceEpoch();
		int diff = first_week_int%7;
//		boolean recordedFirst = false;
//		System.out.println("firs_week_int: "+first_week_int+" diff: "+diff);//correct
		String query = "select site_id, ip_subnet_or_building, internal_system_id, archive_date,cvss_scores, vulnerability_count from hardware_archive "
				+ "where site_id = ? and ip_subnet_or_building = ? and archive_date = ? ";
		PreparedStatement prepared = session.prepare(query);
		String query_device = "select internal_system_id, cvss_scores from hardware_archive where site_id = ? and ip_subnet_or_building = ? and archive_date = ? and internal_system_id = ?";
		PreparedStatement prepared_device = session.prepare(query_device);
		BoundStatement bound;
		ResultSet rs;
		Row row;
		for (int i = first_week_int;i<=today_int;i++){
			
			//get readable increments
			int day_increment = i-first_week.getDaysSinceEpoch();
			int hour_increment = day_increment * 24;
			
			//skips every 7 days (weekly roll-ups)
			if (i%7 !=diff){
				continue;
			}
			
			//gets start of every week
			LocalDate cur_week = first_week.add(Calendar.HOUR, hour_increment);
			//gets end of every week
			LocalDate cur_week_end = getWeek_end_date(cur_week);
			
			System.out.println("day_increment: " + day_increment);
			System.out.println("hour_increment: " + hour_increment);
			System.out.println("cur_week: " + cur_week.toString());
			System.out.println("cur_end: " + cur_week_end.toString());
			
			//get a roll-up for each summary
			for (String sub: subnetArray){
				int vulnCount = 0;
				criticalList= new ArrayList<Cvss_Scores>();
				highList= new ArrayList<Cvss_Scores>();
				mediumList= new ArrayList<Cvss_Scores>();
				lowList= new ArrayList<Cvss_Scores>();
				patch_count_critical= 0;
				patch_count_high= 0;
				patch_count_medium= 0;
				patch_count_low= 0;
				boolean recordedFirst = false;
//				System.out.println("=============Subnet: "+sub+"===============");
				
				//compare each day to each other for the roll-up
				for (int d=0;d<6;d++){
					
					System.out.println("---days: "+ cur_week.add(Calendar.HOUR,d*24).toString()+ " to "+ cur_week.add(Calendar.HOUR,(d+1)*24).toString()+"---");
//					System.out.println("|vulnerabilities before comparison: " + vulnCount);
//					System.out.println("|querying to fill pri_HardwareMap "+ site+" "+ sub+" "+ cur_week.add(Calendar.HOUR,d*24));
					
					//populate primary day's hardware
					bound = prepared.bind(site, sub, cur_week.add(Calendar.HOUR,d*24));
					rs = session.execute(bound);
					HashMap<UUID,List<Cvss_Scores>> pri_HardwareMap = getHardwareVulnMap(rs);
					
//					System.out.println("|pri_HardwareMap size: " + pri_HardwareMap.size());
					
					//capturing the primary vulnerabilities
					if ((!recordedFirst) && pri_HardwareMap.size()>0){
						for(UUID cur_internal_sys_id: pri_HardwareMap.keySet()){
							Iterator<Cvss_Scores> it = pri_HardwareMap.get(cur_internal_sys_id).iterator();
							while(it.hasNext()){
								Cvss_Scores score = it.next();
								sortVulnerability(score);
							}
//							sortVulnerabilities(pri_HardwareMap.get(cur_internal_sys_id));
							vulnCount = vulnCount + pri_HardwareMap.get(cur_internal_sys_id).size();
//							System.out.println("|vulnCount: "+ vulnCount);
						}
						recordedFirst = true;
						System.out.println("|got the primary vulnerabilities on day "+ d + " vulnCount: "+ vulnCount);
					}
					System.out.println("|querying to fill sec_HardwareMap "+ site+" "+ sub+" "+ cur_week.add(Calendar.HOUR,(d+1)*24).toString());
					
					//capturing the secondary vulnerabilities
					bound = prepared.bind(site, sub, cur_week.add(Calendar.HOUR,(d+1)*24));
					rs = session.execute(bound);
					HashMap<UUID,List<Cvss_Scores>> sec_HardwareMap = getHardwareVulnMap(rs);

//					System.out.println("|sec_HardwareMap size: " + sec_HardwareMap.size());
					
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
									sortVulnerability(score);
									vulnCount++;
//									System.out.println("|new vulnerability/ vulnCount: " + vulnCount);
								}
						}else{
							vulnCount = vulnCount+ sec_HardwareMap.get(internal_sys_id).size();
							Iterator<Cvss_Scores> it = sec_HardwareMap.get(internal_sys_id).iterator();
							while (it.hasNext()){
								Cvss_Scores score = it.next();
								sortVulnerability(score);
							}
//							System.out.println("|added "+internal_sys_id+" "+sec_HardwareMap.get(internal_sys_id).size()+" vulnCount: " + vulnCount);
						}
					}
					System.out.println("|vulnerabilities for day "+d+": " + vulnCount);
				}
				
				LocalDate month_end_date = getMonth_end(cur_week);
				Hardware_Summary weekly = new Hardware_Summary();
				
				weekly.setSite_id(site);
				weekly.setIp_subnet_or_building(sub);
				weekly.setMonth_end_date(month_end_date);
				weekly.setWeek_start_date(cur_week);
				weekly.setCritical_count(criticalList.size());
				weekly.setHigh_count(highList.size());
				weekly.setMeduim_count(mediumList.size());
				weekly.setLow_count(lowList.size());
				weekly.setPatches_count(patch_count_critical + patch_count_high + patch_count_medium + patch_count_low);
				weekly.setPatches_critical_count(patch_count_critical);
				weekly.setPatches_high_count(patch_count_high);
				weekly.setPatches_medium_count(patch_count_medium);
				weekly.setPatches_low_count(patch_count_low);
				weekly.setVulnerability_count(criticalList.size() + highList.size() + mediumList.size() + lowList.size());
				System.out.println("=="+weekly.toString());
				weeklySummaryList.add(weekly);
//				System.out.println("===============Number of total vulnerabilities: " + vulnCount +"===============");
			}		
		}
		return weeklySummaryList;
	}

	public void sortVulnerability(Cvss_Scores score){
			BigDecimal bdBase = score.getCvss_base_score();
			double base = bdBase.doubleValue();
			int patch_count = score.getPatch_count();
			if (0<=base && base <=3.9){
				lowList.add(score);
				if(patch_count > 0)
					patch_count_low = patch_count_low + patch_count;
			}else if(4<= base && base<= 6.9){
				mediumList.add(score);
				if (patch_count > 0)
					patch_count_medium = patch_count_medium + patch_count;
			}else if(7<= base && base <=8.9){
				highList.add(score);
				if(patch_count > 0)
					patch_count_high = patch_count_high + patch_count;
			}else if(9.0<= base && base<=10){
				criticalList.add(score);
				if(patch_count > 0)
					patch_count_critical = patch_count_critical + patch_count;
			}
	}
	

	/**
	 * @purpose inserts hardware_archive roll-up data into hardware_weekly_summary
	 * @param weeklySummaryList of roll-up data for the hardware_weekly_summary table
	 * @return inserts data into hardware_weekly_summary table*/
	public void insertWeeklySummaries(List<Hardware_Summary> weeklySummaries){
		String query = "INSERT INTO hardware_weekly_summary (site_id, ip_subnet_or_building, month_end_date, week_start_date, critical_count, high_count, low_count, medium_count, "
				+ "patches_count, patches_critical_count, patches_high_count, patches_low_count, patches_medium_count, vulnerability_count) "
				+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		Iterator<Hardware_Summary> it = weeklySummaries.iterator();
		while(it.hasNext()){
			Hardware_Summary weekly = it.next();
			bound = prepared.bind(weekly.getSite_id(), weekly.getIp_subnet_or_building(), weekly.getMonth_end_date(), weekly.getWeek_start_date(), weekly.getCritical_count(), weekly.getHigh_count(), weekly.getLow_count(),weekly.getMedium_count(), weekly.getPatches_count(), weekly.getPatches_critical_count(), weekly.getPatches_high_count(), weekly.getPatches_low_count(), weekly.getPatches_medium_count(), weekly.getVulnerability_count());
			session.execute(bound);
		}
	}
	
/////////////////////////////////////////-------------Hardware_Daily_Summary(from hardware_archive)-------------/////////////////////////////////////////
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_daily_sumary table
	 * @param keyspace containing the hardware_archive and hardware_daily_summary tables
	 * @return none; inserts data into the database */
	public void rollupHardwareDaily_fromHardwareArchive(String keyspace){
		if (!validKeyspace(keyspace))
			return;
		List<Hardware_Summary> dailyArchivedSummaries = getDailyRollUp();
		insertDailySummaries(dailyArchivedSummaries); 
		System.out.println("Done with Daily Rollup");
	}
	/**
	 * @purpose queries the hardware_archive table to create vulnerability roll-up data for the hardware_daily_summary table.
	 * @param none, queries database
	 * @return a list of Hardware_Daily_Summary objects to prepare for insert */
	public List<Hardware_Summary> getDailyRollUp(){
		String query = "select site_id, ip_subnet_or_building, archive_date,cvss_scores, vulnerability_count from hardware_archive where site_id = ? and ip_subnet_or_building = ? and archive_date = ?;";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		ResultSet rs;
		Iterator<Row> it;
		Iterator<Hardware_Archive> it_archive;
		Row row;
		Hardware_Archive hardware_archive;
		Iterator<Cvss_Scores> it_scores;
		Hardware_Summary daily_summary;
		List<Hardware_Summary> dailySummaryList = new ArrayList<Hardware_Summary>();
		Date start = new Date();
		
		LocalDate first_archived_date = getEarliestDate();
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
			for (String subnet: subnetArray){
				List<Hardware_Archive> hardwareArchiveList = new ArrayList<Hardware_Archive>();
				bound = prepared.bind(site,subnet,archived);
				rs = session.execute(bound);
				it = rs.iterator();
				//put all the results from the hardware_archive table into a list. 
				while (it.hasNext()){
					row = it.next();
					hardware_archive = new Hardware_Archive();
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
				
				//summarize the rows of the result set into one row of the hardware_daily_summary_table
				criticalList = new ArrayList<Cvss_Scores>();
				highList= new ArrayList<Cvss_Scores>();
				mediumList= new ArrayList<Cvss_Scores>();
				lowList= new ArrayList<Cvss_Scores>();
				patch_count_critical= 0;
				patch_count_high= 0;
				patch_count_medium= 0;
				patch_count_low= 0;
				int vuln_count= 0;
				it_archive = hardwareArchiveList.iterator();
				while (it_archive.hasNext()){
					hardware_archive = it_archive.next();
					vuln_count= vuln_count+hardware_archive.getVulnerability_count();
					//if there are to cvss_scores in the row, then skip
					if (!(hardware_archive.getCvss_scores().size()>0)){
						continue;
					}
					List<Cvss_Scores> scores = hardware_archive.getCvss_scores();

					it_scores = scores.iterator();
					while(it_scores.hasNext()){
						Cvss_Scores score = it_scores.next();
						sortVulnerability(score);
					}
					int lows = lowList.size();
					int mediums = mediumList.size();
					int highs = highList.size();
					int criticals = criticalList.size();
					Date monday = new Date(archived.getMillisSinceEpoch());
					LocalDate weekStart = getWeek_start_date(archived, "sunday");
					
					daily_summary= new Hardware_Summary();
					daily_summary.setSite_id(site);
					daily_summary.setIp_subnet_or_building(subnet);
					daily_summary.setWeek_start_date(weekStart);
					daily_summary.setDay_of_week(archived);
					daily_summary.setCritical_count(criticals);
					daily_summary.setHigh_count(highs);
					daily_summary.setMeduim_count(mediums);
					daily_summary.setLow_count(lows);
					daily_summary.setPatches_count(patch_count_critical+patch_count_high+patch_count_medium+patch_count_low);
					daily_summary.setPatches_critical_count(patch_count_critical);
					daily_summary.setPatches_high_count(patch_count_high);
					daily_summary.setPatches_medium_count(patch_count_medium);
					daily_summary.setPatches_low_count(patch_count_low);
					daily_summary.setVulnerability_count(lows+mediums+highs+criticals);
					System.out.println(daily_summary.toString());
					dailySummaryList.add(daily_summary);
				}
			}
		}
		return dailySummaryList;
	}
	/**
	 * @purpose inserts hardware_archive roll-up data into hardware_daily_summary
	 * @param dailySummaryList of roll-up data for the hardware_daily_summary table
	 * @return inserts data into hardware_daily_summary table*/
	public void insertDailySummaries(List<Hardware_Summary> dailySummaryList){
		Iterator<Hardware_Summary> it = dailySummaryList.iterator();
		String query = "INSERT INTO hardware_daily_summary (site_id, ip_subnet_or_building, week_start_date, day_of_week, critical_count, high_count, low_count, medium_count, patches_count, patches_critical_count, patches_high_count, patches_low_count, patches_medium_count, vulnerability_count) "
				+ "Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		
		while (it.hasNext()){
			Hardware_Summary daily = it.next();
			bound = prepared.bind(daily.getSite_id(), daily.getIp_subnet_or_building(), daily.getWeek_start_date(), daily.getDay_of_week(), daily.getCritical_count(), daily.getHigh_count(), daily.getLow_count(), daily.getMedium_count(), daily.getPatches_count(), daily.getPatches_critical_count(), daily.getPatches_high_count(), daily.getPatches_low_count(), daily.getPatches_medium_count(), daily.getVulnerability_count());
			session.execute(bound);
		}
	}

/////////////////////////////////////////-------------Hardware_Daily_Summary(from hardware)-------------/////////////////////////////////////////
	/**
	 * @purpose toplevel method for hardware_daily_summary for today
	 * @param keyspace containing hardware and hardware_archive tables
	 * @return none  fills data in hardware_archive with today's hardware data*/
	public void hardwareIntoHardwareArchive(String keyspace){
		//validating input
		if (!validKeyspace(keyspace))
			return;
		collectHwTable();
		insertHardwareArchive(hardwareList, 0);
		slf4jLogger.info("Data from "+keyspace+".hardware archived into "+keyspace+".hardware_archive.");
	}

/////////////////////////////////////////-------------Hardware_Archive(for select number of days)-------------/////////////////////////////////////////
	/**
	 * @purpose top-level method that uses a list of all current hardware records and inserts 2 years worth (730 days) of hardware data
	 * @param keyspace containing hardware and hardware_archive tables
	 * @param days number of days to generate fake data
	 * @return none  fills data in hardware_archive*/
	public void insertRandomizedData(String keyspace, int days){
		//validating input
		if (!validKeyspace(keyspace))
			return;
		if (!validDays(days))
			return;
		int day = 0;
		//collecting data from current table
		collectHwTable();
		System.out.println("HardwareList Size: "+hardwareList.size());
		//inserting new data into the hardware_archive
		for (day = 0 ; day<days; day++){
			List<Hardware> randomizedList = getRandomizedList(hardwareList, 10);
			System.out.println("randomizedList Size: "+randomizedList.size());
			System.out.println("Day: "+ day);
			insertHardwareArchive(randomizedList, day);
		}
		slf4jLogger.info("Data from "+keyspace+".hardware has been used to create "+day+" days of archived data into "+keyspace+".hardware_archive.");
	}
	/**
	 * @purpose creates a list of all current hardware data
	 * @param none
	 * @return populates the hardwareList 
	 * */
	public void collectHwTable(){
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
			String ip_subnet_or_building = row.getString("ip_subnet_or_building");
				hwRow.setIp_subnet_or_building(ip_subnet_or_building);
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
			Float cyvar = row.getFloat("cyvar");
				hwRow.setCyvar(cyvar);
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
			List<String> installed_software = row.getList("installed_software", String.class);
				hwRow.setInstalled_software(installed_software);
			String ip_address = row.getString("ip_address");
				hwRow.setIp_address(ip_address);
			String ip_gateway = row.getString("ip_gateway");
				hwRow.setIp_gateway(ip_gateway);
			String ip_netmask = row.getString("ip_netmask");
				hwRow.setIp_netmask(ip_netmask);
			String mac_address = row.getString("mac_address");
				hwRow.setMac_address(mac_address);
			Float neighbor_coefficient_value = row.getFloat("neighbor_coefficient_value");
				hwRow.setNeighbor_coefficient_value(neighbor_coefficient_value);
			Float node_impact_value = row.getFloat("node_impact_value");
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
				hwRow.setRun_date(run_datetime);
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
		System.out.println("Collection Complete. number of Hardware: " + hardwareList.size());
	}
	
	/**@purpose to create a sublist of the given list. The method subtracts 0 to x percent of the data 
	 * to create the sublist and randomizes the order of the data.
	 * @param curArchitecture is a master list form which a sublist will be created
	 * @param percent is the percent of variance that will be subtracted out of the master list
	 * (ex. 30% would be written as 30)
	 * @return a randomized sublist of the master list 
	 * */
	public List<Hardware> getRandomizedList(List<Hardware> curArchitecture, int percent){
		
		List<Hardware> phoArchitecture = new ArrayList<Hardware>();
		int totalItemCount = curArchitecture.size();
		//number into integer - rounded. 
		int variant = (totalItemCount*percent/100)-((totalItemCount % 100)/100); //(7/5) - ((7 mod 5)/5)
		
		Collections.shuffle(curArchitecture);
		System.out.println("");
		System.out.println("Variant: " + variant);
		Random gen =new Random();
		int gen_int = gen.nextInt(variant);
		System.out.println("Sub List: " + gen_int + " from "+ curArchitecture.size());
		String query = "Select cve_description, cvss_base_score, patch_count, patch_references from appl_auth.common_vulnerabilities where cve_id = ?";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		ResultSet rs;
		
		for (Hardware host: curArchitecture.subList(gen_int, curArchitecture.size())){
//			for (Cvss_Scores score: host.getCvss_scores()){
//				if (score.getCve_id()!= null){
//					
//				}
//			}
			phoArchitecture.add(host);
		}
		System.out.println("New List size: " +phoArchitecture.size() +" Old List: " +curArchitecture.size());
		return phoArchitecture;
	}
	
	/**@purpose Iterates through the given list and inserts data into the hardware_archive table
	 * @param A list of rows of hardware
	 * @param the number of days to subtract from the current date to generate a pho-historic archive_date
	 * @return inserts data into the hardware_archive table*/
	public void insertHardwareArchive(List<Hardware> oldArchitecture, int daysSubtracted){
		String query = "Insert into hardware_archive (site_id, ip_subnet_or_building, internal_system_id, asset_type, asset_visibility, audit_upsert, business_process_ids, "
				+ "connected_elements, cpe_id, crown_jewel, cvss_scores, cyvar, end_of_life_date, end_of_sale, end_of_support_date, host_name, installed_software,"
				+ "ip_address, ip_gateway, ip_netmask, mac_address, neighbor_coefficient_value, node_impact_value, operating_system, os_general, part, patch_availability, product, reportable_flag, "
				+ "run_datetime, system_type, vendor, vendor_stencil_icon, version, version_update, vulnerability_count,archive_date)"
				+ "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound;
		
		Iterator<Hardware> it = oldArchitecture.iterator();
		Date start = new Date();
		Date dnewDate = subtractDays(start, daysSubtracted);
		LocalDate lnewDate = LocalDate.fromMillisSinceEpoch(dnewDate.getTime());
		System.out.println("Date: " + lnewDate);
		while (it.hasNext()){
			Hardware hardware = it.next();
//			starts with today's date and then subtract days
			bound = prepared.bind(hardware.getSite_id(), hardware.getIp_subnet_or_building(), hardware.getInternal_system_id(), hardware.getAsset_type(), hardware.getAsset_visibility(), hardware.getAudit_upsert(),hardware.getBusiness_processes(),
					hardware.getConnected_Elements(), hardware.getCpe_id(), hardware.getCrown_jewel(), hardware.getCvss_scores(), hardware.getCyvar(), hardware.getEnd_of_life_date(), hardware.getEnd_of_sale(),
					hardware.getEnd_of_support_date(), hardware.getHost_name(), hardware.getInstalled_software(), hardware.getIp_address(), hardware.getIp_gateway(), hardware.getIp_netmask(),
					hardware.getMac_address(), hardware.getNeighbor_coefficient_value(), hardware.getNode_impact_value(), hardware.getOperating_system(), hardware.getOs_general(), hardware.getPart(), hardware.getPatch_availability(),
					hardware.getProduct(), hardware.getReportable_flag(), hardware.getRun_date(), hardware.getSystem_type(), hardware.getVendor(),hardware.getVendor_stencil_icon(), hardware.getVersion(), hardware.getVersion_update(), 
					hardware.getCvss_scores().size(), lnewDate);
			
			session.execute(bound);
		}
	}
	/**
	 * @purpose subtracts the number of days from the given date according to a Gregorian calendar
	 * @param date is the starting date
	 * @param days is the number of days that will be subtracted from the starting date
	 * @return the date that results from the subtracted number of days.
	 * */
	public static Date subtractDays(Date date, int days) {
//		int plus252 = days+252;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}
	/**
	 * @purpose retrieves the earliest date in the hardware_archive table
	 * @param non, queries hardware_archive table
	 * @return earliest archive_date */
	public LocalDate getEarliestDate(){
		UniqueList UniqueList = new UniqueList();
		//recieves error- Bind variables cannot be used for keyspace names- on preparing a PreparedStatement with ?'s at col or table placement.
		String query = "Select archive_date from hardware_archive Limit 1";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind();
		ResultSet rs = session.execute(bound);
		Iterator<Row> it = rs.iterator();
		LocalDate startDay = null;
		while(it.hasNext()){
			Row row = it.next();
			startDay = row.getDate("archive_date");
		}
		return startDay;
	}
	
	/**@purpose to return the last day of the month to use for database import
	 * @param week_start_date as a com.datastax.driver.core.LocalDate
	 * @return month_end_date of the month the week_start_date resides as a com.datastax.driver.core.LocalDate*/
	public LocalDate getMonth_end(LocalDate week_start_date){
		//get YearMonth to return java.time.LocalDate
		int year = week_start_date.getYear();
		int month = week_start_date.getMonth();
		YearMonth yrMo = YearMonth.of(year, month);
		//use .atEndOfMonth to get the date of the last day of the month
		java.time.LocalDate endOfMonth = yrMo.atEndOfMonth();
		//change format of date to a com.datastax.driver.core.LocalDate for database import
		Date end = Date.from(endOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		LocalDate month_end_date = LocalDate.fromMillisSinceEpoch(end.getTime());
//		System.out.println("month_end_date: " + month_end_date.toString());
		return month_end_date;
	}
	
	/**
	 * @purpose gets the week start date given any date
	 * @param archived_date LocalDate of taken from hardware_archive table
	 * @param FirstDay acceptable variables are "monday" or "sunday"
	 * @return week_start_date LocalDate*/
	public LocalDate getWeek_start_date(LocalDate archived_date, String FirstDay){
		
		int dayOfWeek = 0;
		Calendar cal = Calendar.getInstance();
		Date archived = new Date(archived_date.getMillisSinceEpoch());
		cal.setTime(archived);
		switch(FirstDay){
			case "monday" : dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) + (cal.getFirstDayOfWeek());
			case "sunday" : dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) + (cal.getFirstDayOfWeek()-1);
		}
		//allows the same day to be the start of the week
		if (dayOfWeek == 7)
			dayOfWeek = 0;
		cal.add(Calendar.DAY_OF_MONTH, -dayOfWeek);
		Date start = cal.getTime();
		LocalDate week_start_date = LocalDate.fromMillisSinceEpoch(start.getTime());
		
		return week_start_date;
	}
	/**
	 * @purpose gets the end of the week end date given a week start date
	 * @param week_start_date LocalDate
	 * @return week_end_date LocalDate*/
	public LocalDate getWeek_end_date(LocalDate week_start_date){
		LocalDate week_end_date;
		
		week_end_date = week_start_date.add(Calendar.HOUR, 144);
//		System.out.println(week_end_date);// correct
		return week_end_date;
	}
	
	public HashMap<UUID,List<Cvss_Scores>> getHardwareVulnMap(ResultSet rs){
		HashMap<UUID,List<Cvss_Scores>> hardwareVulnMap = new HashMap<UUID,List<Cvss_Scores>>();
		Iterator<Row>it = rs.iterator();
		//put all the results from the hardware_archive table into a list. 
		while (it.hasNext()){
			Row row = it.next();
			UUID internal_system_id = row.getUUID("internal_system_id");
			List<Cvss_Scores> cvss_scores = row.getList("cvss_scores", Cvss_Scores.class);
			hardwareVulnMap.put(internal_system_id, cvss_scores);
		}
		return hardwareVulnMap;
	}
	
	public List<Hardware_Archive> populateHardwareArchiveList_Summaries(ResultSet rs){
		List<Hardware_Archive> hardwareArchiveList = new ArrayList<Hardware_Archive>();
		Iterator<Row>it = rs.iterator();
		//put all the results from the hardware_archive table into a list. 
		while (it.hasNext()){
			Row row = it.next();
			Hardware_Archive hardware_archive = new Hardware_Archive();
			UUID site_id = row.getUUID("site_id");
				hardware_archive.setSite_id(site_id);
			String ip_subnet_or_building = row.getString("ip_subnet_or_building");
				hardware_archive.setIp_subnet_or_building(ip_subnet_or_building);
			LocalDate archive_date = row.get("archive_date",LocalDate.class);
				hardware_archive.setArchive_date(archive_date);
			UUID internal_system_id = row.getUUID("internal_system_id");
				hardware_archive.setInternal_system_id(internal_system_id);
			List<Cvss_Scores> cvss_scores = row.getList("cvss_scores", Cvss_Scores.class);
				hardware_archive.setCvss_scores(cvss_scores);
			int vulnerability_count = row.getInt("vulnerability_count");
				hardware_archive.setVulnerability_count(vulnerability_count);
				
//				deviceVulnMap.put(internal_system_id, cvss_scores);
				hardwareArchiveList.add(hardware_archive);
		}
		return hardwareArchiveList;
	}
	
	public void updateSoftware(String keyspace){
		if (!validKeyspace(keyspace))
			return;
		String query = "INSERT INTO dod.software (part, vendor, product, version, cpe_update, edition, language, sw_edition, target_hw, target_sw, other, cpe_id, end_of_life_date, end_of_sale, end_of_support_date, installed_on_hardware)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		collectHwTable();
		createSoftwareInserts();
		
	}
	
	private void createSoftwareInserts(){
		Iterator<Hardware> it = hardwareList.iterator();
		while(it.hasNext()){
			Hardware device = it.next();
			List<String> software = device.getInstalled_software();
			Iterator<String> it_soft = software.iterator();
			while (it_soft.hasNext()){
				String cpe = it_soft.next();
				Software row = new Software();
				breakdownCPE(cpe);
				row.setCpe_id(cpe);
				row.setPart(part);
				row.setVendor(vendor);
				row.setProduct(product);
				row.setVersion(version);
				row.setCpe_update(update);
				row.setEdition(edition);
				row.setLanguage(language);
				row.setSw_edition(sw_edition);
				row.setTarget_hw(target_hw);
				row.setTarget_sw(target_sw);
				row.setOther(other);
				System.out.println(row.toString());
				softwareList.add(row);
			}
		}
	}
	
	 private void breakdownCPE(String cpe){
		 String[] elements = cpe.split(":");
		 try{
			 part =elements[1];
			 vendor=elements[2];
			 product=checkSymbol(elements[3]);
			 version=checkSymbol(elements[4]);
			 update=checkSymbol(elements[5]);
			 if (elements[6].contains("~")){
				 unpackEdition(elements[6]);
			 }else{
				 edition=checkSymbol(elements[6]);
			 }
			 language=checkSymbol(elements[7]);
		 }catch(IndexOutOfBoundsException e){
    		return;
    	}
	 }
	    
	    private void unpackEdition(String edition){
	    	String[] editionElements = edition.split("~");
	    	try{
		    	this.edition=checkSymbol(editionElements[1]);
		    	sw_edition=checkSymbol(editionElements[2]);
		    	target_sw=checkSymbol(editionElements[3]);
		    	target_hw =checkSymbol(editionElements[4]);
		    	other=checkSymbol(editionElements[5]);
	    	}catch(IndexOutOfBoundsException e){
	    		return;
	    	}
	    }
	    
	    private String checkSymbol(String input){
	    	if (input == "-")
	    		return "NA";
	    	if (input == "*")
	    		return "ANY";
	    	if(input==null)
	    		return "ANY";
	    	return input;
	    }
	    

	public boolean validKeyspace(String keyspace){
		boolean result = false;
		if (!(keyspace== "vmasc" || keyspace =="g2" || keyspace =="dod")){
			slf4jLogger.error("This keyspace does not contain the hardware_archive table.");
			return false;
		}else {
			result = true;
		}	
		//validating connection
		try{
			session = cluster.connect(keyspace);
			slf4jLogger.info("session is connected to keyspace: "+session.getLoggedKeyspace());
			manager = new MappingManager(session);
			manager.udtCodec(Connected_Elements.class);
			manager.udtCodec(Cvss_Scores.class);
			manager.udtCodec(Audit_Upsert.class);
		}catch(AuthenticationException e){
			slf4jLogger.error(e.toString());
			return false;
		}
		return result;
	}
	
	public boolean validDays(int days){
		if (days <= 0){
			slf4jLogger.error("Invalid number of days");
			return false;
		}else{
			return true;
		}
	}
}
