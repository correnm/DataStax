package src.com.g2.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.LocalDate;

import src.com.g2.types.Hardware;
import src.com.g2.types.Hardware_Summary;
import src.com.g2.utils.Connect2Db;
import src.com.g2.utils.SummaryUtils;
import src.com.g2.utils.TableData;

public class Demo_Archive_Rollups {
	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(ArchiveHardware.class);
	/**
	 * @purpose top-level method that archives  90-100% of the current hardware records into the  hardware_archive (used for demo purposes only)
	 * @param keyspace containing hardware and hardware_archive tables
	 * @return none  fills data in hardware_archive*/
	public static void main(String args[]){
		//validating input
		try{
			String keyspace = args[0];
			if (!TableData.validKeyspace(keyspace))
				return;
			slf4jLogger.info("Archive Today's Data from "+keyspace+".hardware into "+keyspace+".hardware_archive.");
			List<Hardware> hardwareList = TableData.collectHwTable(keyspace);
			List<Hardware> randomizedList = SummaryUtils.getRandomizedList(hardwareList, 10);
			TableData.insertIntoHardwareArchive(randomizedList, 0);
			//get archived date for summary prep
			LocalDate archive_date = SummaryUtils.getTodaysDate();
			UUID[] sites = TableData.getSites();
			String [] subnets = TableData.getSubnets();
			
			//collect the summaries
			List<Hardware_Summary> dailySummaries = new ArrayList<Hardware_Summary>();
			for (UUID site: sites){
				for(String subnet :subnets){
					dailySummaries.addAll(SummaryUtils.createSummaryRows(randomizedList, site, subnet, archive_date, "daily"));
				}
			}
			//expecting one record per subnet for the day's roll-up
			TableData.insertDailySummaries(dailySummaries);
			
			///////////////weekly and monthly added into one program\\\\\\\\\\\\\\\
			////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			//create weekly rollups
			List<Hardware_Summary> weeklyRollups = TableData.getWeeklyRollUp(sites,subnets, archive_date);
			TableData.insertWeeklySummaries(weeklyRollups);
			
			//create Monthly Summaries
			List<Hardware_Summary> monthlyRollups = TableData.getMonthlyRollUp(sites, subnets, archive_date);
			TableData.insertMonthlySummaries(monthlyRollups);
			////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			
			Connect2Db.getSession().close();
			Connect2Db.getSession().getCluster().close();
			slf4jLogger.info("Data from "+Connect2Db.getSession().getLoggedKeyspace()+".hardware has been archived into "+Connect2Db.getSession().getLoggedKeyspace()+".hardware_archive.");
			slf4jLogger.info("Session closed: "+Boolean.toString(Connect2Db.getSession().isClosed()));
		}catch(NullPointerException e){
			slf4jLogger.error("Please enter keyspace of the organization.");
			slf4jLogger.error(e.toString());
			Connect2Db.getSession().close();
			Connect2Db.getSession().getCluster().close();
			return;
		}catch(ArrayIndexOutOfBoundsException e1){
			slf4jLogger.error("Cannot connect to "+args[0]+" keyspace");
			slf4jLogger.error("Please enter keyspace of the organization.");
			slf4jLogger.error(e1.toString());
			e1.printStackTrace();
			Connect2Db.getSession().close();
			Connect2Db.getSession().getCluster().close();
			return;
		}
	}
	
	
	/**
	 * @purpose top-level method that uses a list of all current hardware records and inserts 2 years worth (730 days) of hardware data
	 * @param keyspace containing hardware and hardware_archive tables
	 * @param days number of days to generate fake data
	 * @return none  fills data in hardware_archive*/
	public static void HardwaretoHardwareArchive_phoData(String keyspace, int days){
		//validating input
		if (!TableData.validKeyspace(keyspace))
			return;
		if (!SummaryUtils.validDays(days))
			return;
		slf4jLogger.info("Archive "+days+" days of archived dataData from "+keyspace+".hardware into "+keyspace+".hardware_archive.");
		int day = 0;
		//collecting data from current table
		List<Hardware> hardwareList = TableData.collectHwTable(keyspace);
		System.out.println("HardwareList Size: "+hardwareList.size());
		//inserting new data into the hardware_archive
		for (day = 0 ; day<days; day++){
			List<Hardware> randomizedList = SummaryUtils.getRandomizedList(hardwareList, 10);
			System.out.println("randomizedList Size: "+randomizedList.size());
			System.out.println("Day: "+ day);
			TableData.insertIntoHardwareArchive(randomizedList, day);
		}
		slf4jLogger.info("Data from "+keyspace+".hardware has been used to create "+day+" days of archived data into "+keyspace+".hardware_archive.");
	}
	
}
