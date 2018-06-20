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

public class HardwareDailyRollup {
	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(HardwareDailyRollup.class);
	
	public static void main(String args[]){
		try{
			String keyspace = args[0];
			if (!TableData.validKeyspace(keyspace))
				return;
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
			
			Connect2Db.getSession().close();
			Connect2Db.getSession().getCluster().close();
			slf4jLogger.info("Session closed: "+Boolean.toString(Connect2Db.getSession().isClosed()));
			
		}catch(NullPointerException e){
			slf4jLogger.error("Please enter keyspace of the organization.");
			slf4jLogger.error(e.toString());
				e.printStackTrace();
			return;
		}catch(ArrayIndexOutOfBoundsException e1){
			slf4jLogger.error("Please enter keyspace of the organization.");
			slf4jLogger.error(e1.toString());
			return;
		}
	}
}
