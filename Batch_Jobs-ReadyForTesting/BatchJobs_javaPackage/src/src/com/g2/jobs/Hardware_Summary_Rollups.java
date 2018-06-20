package src.com.g2.jobs;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.LocalDate;

import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Summary;
import src.com.g2.utils.TableData;
import src.com.g2.utils.SummaryUtils;

public class Hardware_Summary_Rollups {


	private final static Logger slf4jLogger = LoggerFactory.getLogger(Hardware_Summary_Rollups.class);
	
	/**
	 * @purpose top-level method that archives the current hardware table into the hardware_archve table 
	 * @param keyspace containing hardware and hardware_archive tables
	 * @return none  fills data in hardware_archive*/
	public static void rollupHardwareDaily(String keyspace){
		if (!TableData.validKeyspace(keyspace))
			return;
		slf4jLogger.info("Insert today's Daily Rollup");
		//collect current hardware
		List<Hardware> hardwareList = TableData.collectHwTable(keyspace);
		UUID[] sites = TableData.getSites();
		String [] subnets = TableData.getSubnets();
		//get archived date for summary prep
		LocalDate archived = SummaryUtils.getTodaysDate();
		//collect the summaries
		List<Hardware_Summary> dailySummaries = new ArrayList<Hardware_Summary>();
		for (UUID site: sites){
			for(String subnet :subnets){
				dailySummaries.addAll(SummaryUtils.createSummaryRows(hardwareList, site, subnet, archived, "daily"));
			}
		}
		//expecting one record per subnet for the day's roll-up
		TableData.insertDailySummaries(dailySummaries);
		slf4jLogger.info("Done inserting today's Daily Rollup");
	}
	
	
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_weekly_sumary table
	 * @param keyspace containing the hardware_archive and hardware_weekly_summary tables
	 * @return none; inserts data into the database */
	public static void rollupHardwareWeekly(String keyspace){
		if (!TableData.validKeyspace(keyspace))
			return;
		slf4jLogger.info("Instert this week's Weekly Rollup");
		TableData.collectHwTable(keyspace);
		UUID[] sites = TableData.getSites();
		String[] subnets = TableData.getSubnets();
		
		LocalDate archive_date = SummaryUtils.getTodaysDate();
		List<Hardware_Summary> weeklyRollups = TableData.getWeeklyRollUp(sites,subnets, archive_date);
		TableData.insertWeeklySummaries(weeklyRollups);
		slf4jLogger.info("Done inserting this week's Weekly Rollup");
	}
	
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_monthly_sumary table
	 * @param keyspace containing the hardware_archive and hardware_monthly_summary tables
	 * @return none; inserts data into the database */
	public static void rollupHardwareMonthly(String keyspace){
		if (!TableData.validKeyspace(keyspace))
			return;
		slf4jLogger.info("Insert this month's Monthly Rollup");
		TableData.collectHwTable(keyspace);
		UUID[] sites = TableData.getSites();
		String[] subnets = TableData.getSubnets();
		
		LocalDate archive_date = SummaryUtils.getTodaysDate();
		List<Hardware_Summary> monthlyRollups = TableData.getMonthlyRollUp(sites, subnets, archive_date);
		TableData.insertMonthlySummaries(monthlyRollups);
		slf4jLogger.info("Done inserting this month's Monthly Rollup");
	}
	
	
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_daily_sumary table
	 * @param keyspace containing the hardware_archive and hardware_daily_summary tables
	 * @return none; inserts data into the database */
	public static void rollupHardwareDaily_allHardwareArchive(String keyspace){
		if (!TableData.validKeyspace(keyspace))
			return;
		slf4jLogger.info("Instert Daily Rollup from all hardware_archive");
		TableData.collectHwTable(keyspace);
		UUID[] sites = TableData.getSites();
		String[] subnets = TableData.getSubnets();
		List<Hardware_Summary> dailyArchivedSummaries = TableData.getDailyRollUp(sites, subnets);
		TableData.insertDailySummaries(dailyArchivedSummaries); 
		slf4jLogger.info("Done inserting Daily Rollup from all hardware_archive");
	}
	
	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_weekly_sumary table
	 * @param keyspace containing the hardware_archive and hardware_weekly_summary tables
	 * @return none; inserts data into the database */
	public static void rollupHardwareWeekly_allHardwareArchive(String keyspace){
		if (!TableData.validKeyspace(keyspace))
			return;
		slf4jLogger.info("Insert Weekly Rollup from all hardware_archive");
		TableData.collectHwTable(keyspace);
		UUID[] sites = TableData.getSites();
		String[] subnets = TableData.getSubnets();
		
		LocalDate first_archive_date = TableData.getEarliestDate();
		List<Hardware_Summary> weeklyRollups = TableData.getWeeklyRollUp(sites,subnets, first_archive_date);
		TableData.insertWeeklySummaries(weeklyRollups);
		slf4jLogger.info("Done inserting Weekly Rollup from all hardware_archive");
	}

	/**
	 * @purpose top-level method that gathers roll-up data from hardware_archive table and inserts it into the hardware_monthly_sumary table
	 * @param keyspace containing the hardware_archive and hardware_monthly_summary tables
	 * @return none; inserts data into the database */
	public static void rollupHardwareMonthly_allHardwareArchive(String keyspace){
		if (!TableData.validKeyspace(keyspace))
			return;
		slf4jLogger.info("Insert Monthly Rollup from all hardware_archive");
		TableData.collectHwTable(keyspace);
		UUID[] sites = TableData.getSites();
		String[] subnets = TableData.getSubnets();
		
		LocalDate first_archive_date = TableData.getEarliestDate();
		List<Hardware_Summary> monthlyRollups = TableData.getMonthlyRollUp(sites, subnets, first_archive_date);
		TableData.insertMonthlySummaries(monthlyRollups);
		slf4jLogger.info("Done inserting Monthly Rollup from all hardware_archive");
	}
	
}
