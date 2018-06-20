package src.com.g2.utils;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;

import src.com.g2.types.Cvss_Scores;
import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Summary;
import src.com.g2.types.UniqueList;

public class SummaryUtils {
	/**Connect to Database */
	private static Connect2Db connection = new Connect2Db();
	private static Session session;

	//logging
	private final static Logger slf4jLogger = LoggerFactory.getLogger(SummaryUtils.class);
	
	private static UniqueList siteSet = new UniqueList();
	private static UniqueList subnetSet = new UniqueList();
	
	private static List<Cvss_Scores> criticalList = new ArrayList<Cvss_Scores>();
	private static List<Cvss_Scores> highList= new ArrayList<Cvss_Scores>();
	private static List<Cvss_Scores> mediumList= new ArrayList<Cvss_Scores>();
	private static List<Cvss_Scores> lowList= new ArrayList<Cvss_Scores>();
	private static int patch_count_critical= 0;
	private static int patch_count_high= 0;
	private static int patch_count_medium= 0;
	private static int patch_count_low= 0;
	
	public static void clearSummaryCounts(){
		criticalList = new ArrayList<Cvss_Scores>();
		highList= new ArrayList<Cvss_Scores>();
		mediumList= new ArrayList<Cvss_Scores>();
		lowList= new ArrayList<Cvss_Scores>();
		patch_count_critical= 0;
		patch_count_high= 0;
		patch_count_medium= 0;
		patch_count_low= 0;
	}
	
	/**gets the days since epock for today*/
	public static LocalDate getTodaysDate(){
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		Date today_date = new Date();
		LocalDate today_localDate = LocalDate.fromMillisSinceEpoch(today_date.getTime()); 
//		System.out.println("Today's Date: " + today_date);
		return today_localDate;
	}
	
	/**@purpose gets the list of Hardware Summaries for one subnet and summarizes for hardware_daily_summary_import*/
	public static List<Hardware_Summary> createSummaryRows(List<Hardware> hardwareList,UUID site, String subnet, LocalDate archived, String summaryPeriod){
//		System.out.println("createSummaryRows: size of hardwareList: " + hardwareList.size()+" site: " + site+ " subnet: "+ subnet+ " archived: " + archived+ " summaryPeriod: " + summaryPeriod);
		List<Hardware_Summary> summaryList = new ArrayList<Hardware_Summary>();

		clearSummaryCounts();
		
		Iterator<Hardware> it = hardwareList.iterator();
		while (it.hasNext()){
			Hardware hardware = it.next();
			if (!(hardware.getCvss_scores().size()>0))
				continue;
			//check to make sure the hardware list matches the expected site and subnet summary 
			if (site.equals(hardware.getSite_id()) && subnet.equals(hardware.getIp_subnet_or_building())){
				List<Cvss_Scores> scores = hardware.getCvss_scores();
				sortScores(scores);
			}
		}
		//create summaries over the time period
		Hardware_Summary summary = createSummary(site, subnet,archived, summaryPeriod);
		summaryList.add(summary);

		return summaryList;
	}
	
	/**@purpose gets the Hardware Summary for hardware_summary jobs*/
	public static Hardware_Summary createSummary(UUID site, String subnet, LocalDate archived, String summaryPeriod){
		Hardware_Summary summary= new Hardware_Summary();
		LocalDate weekStart = getWeek_start_date(archived, "sunday");
		LocalDate monthEnd;
		if (summaryPeriod == "monthly"){
			monthEnd = getMonth_end(archived);
		}else{
			monthEnd = getMonth_end(weekStart);
		}
		int lows = lowList.size();
		int mediums = mediumList.size();
		int highs = highList.size();
		int criticals = criticalList.size();
		
		summary.setSite_id(site);
		summary.setIp_subnet_or_building(subnet);
		if (summaryPeriod == "daily")
			summary.setDay_of_week(archived);
		if (summaryPeriod == "daily" || summaryPeriod == "weekly")
			summary.setWeek_start_date(weekStart);
		if (summaryPeriod == "weekly" ||summaryPeriod == "monthly")
			summary.setMonth_end_date(monthEnd);
		summary.setCritical_count(criticals);
		summary.setHigh_count(highs);
		summary.setMeduim_count(mediums);
		summary.setLow_count(lows);
		summary.setPatches_count(patch_count_critical+patch_count_high+patch_count_medium+patch_count_low);
		summary.setPatches_critical_count(patch_count_critical);
		summary.setPatches_high_count(patch_count_high);
		summary.setPatches_medium_count(patch_count_medium);
		summary.setPatches_low_count(patch_count_low);
		summary.setVulnerability_count(lows+mediums+highs+criticals);
//		System.out.println(summary.toString());
		
		return summary;
	}
	
	/**Sorts the */
	public static void sortScores(List<Cvss_Scores> scores){
		Iterator<Cvss_Scores> it = scores.iterator();
		while (it.hasNext()){
			Cvss_Scores score = it.next();
			sortScore(score);
		}
	}
	
	public static void sortScore(Cvss_Scores score){
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
	 * @purpose gets the week start date given any date
	 * @param archived_date LocalDate of taken from hardware_archive table
	 * @param FirstDay acceptable variables are "monday" or "sunday"
	 * @return week_start_date LocalDate*/
	public static LocalDate getWeek_start_date(LocalDate archived_date, String FirstDay){
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
//		System.out.println("get start of week for: " + archived_date);
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
//		System.out.println("start of week: " + week_start_date);
		return week_start_date;
	}
	
	/**
	 * @purpose gets the end of the week end date given a week start date
	 * @param week_start_date LocalDate
	 * @return week_end_date LocalDate*/
	public static LocalDate getWeek_end_date(LocalDate week_start_date){
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		LocalDate week_end_date;
		
		week_end_date = week_start_date.add(Calendar.HOUR, 144);
//		System.out.println(week_end_date);// correct
		return week_end_date;
	}
	
	/**@purpose to return the last day of the month to use for database import
	 * @param week_start_date as a com.datastax.driver.core.LocalDate
	 * @return month_end_date of the month the week_start_date resides as a com.datastax.driver.core.LocalDate*/
	public static LocalDate getMonth_end(LocalDate week_start_date){
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
//		System.out.println("week_start_date: " + week_start_date);
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
	
	public static HashMap<UUID,List<Cvss_Scores>> getHardwareVulnMap(ResultSet rs){
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
	
	/**@purpose to create a sublist of the given list. The method subtracts 0 to x percent of the data 
	 * to create the sublist and randomizes the order of the data.
	 * @param curArchitecture is a master list form which a sublist will be created
	 * @param percent is the percent of variance that will be subtracted out of the master list
	 * (ex. 30% would be written as 30)
	 * @return a randomized sublist of the master list 
	 * */
	public static List<Hardware> getRandomizedList(List<Hardware> curArchitecture, int percent){
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
//		String query = "Select cve_description, cvss_base_score, patch_count, patch_references from appl_auth.common_vulnerabilities where cve_id = ?";
//		PreparedStatement prepared = session.prepare(query);
//		BoundStatement bound;
//		ResultSet rs;
		
		for (Hardware host: curArchitecture.subList(gen_int, curArchitecture.size())){
//			for (Cvss_Scores score: host.getCvss_scores()){
//				if (score.getCve_id()!= null){
//					
//				}
//			}
			siteSet.add(host.getSite_id());
			subnetSet.add(host.getInternal_system_id());
			phoArchitecture.add(host);
		}
		System.out.println("New List size: " +phoArchitecture.size() +" Old List: " +curArchitecture.size());
		return phoArchitecture;
	}
	public static UUID[] getSites_fromRandom(){
		UUID[] sites =  Arrays.copyOf(siteSet.toObjectArray(), siteSet.toObjectArray().length, UUID[].class);
		return sites;
	}
	public static String[] getSubnets_fromRandom(){
		String[] subnets =  Arrays.copyOf(subnetSet.toObjectArray(), subnetSet.toObjectArray().length, String[].class);
		return subnets;
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

	public static boolean validDays(int days){
		if (days <= 0){
			slf4jLogger.error("Invalid number of days");
			return false;
		}else{
			return true;
		}
	}
	
	
}
