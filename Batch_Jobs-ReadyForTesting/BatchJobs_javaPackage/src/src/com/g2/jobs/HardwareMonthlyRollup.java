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

public class HardwareMonthlyRollup {
	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(HardwareMonthlyRollup.class);
	
	public static void main(String args[]){
		try{
			if (!TableData.validKeyspace(args[0]))
				return;
			String keyspace = args[0];
			slf4jLogger.info("Insert this month's Monthly Rollup");
			TableData.collectHwTable(keyspace);
			UUID[] sites = TableData.getSites();
			String[] subnets = TableData.getSubnets();
			
			LocalDate archive_date = SummaryUtils.getTodaysDate(); //LocalDate.fromYearMonthDay(2018, 4, 30); //SummaryUtils.getTodaysDate();
//			System.out.println("1. Get all the records within the month that contains "+archive_date);
			List<Hardware_Summary> monthlyRollups = TableData.getMonthlyRollUp(sites, subnets, archive_date);
			TableData.insertMonthlySummaries(monthlyRollups);
			slf4jLogger.info("Done inserting this month's Monthly Rollup");
			
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