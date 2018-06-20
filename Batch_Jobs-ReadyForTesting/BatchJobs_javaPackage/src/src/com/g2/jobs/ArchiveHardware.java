package src.com.g2.jobs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import src.com.g2.types.Hardware;
import src.com.g2.utils.Connect2Db;
import src.com.g2.utils.SummaryUtils;
import src.com.g2.utils.TableData;

public class ArchiveHardware {
	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(ArchiveHardware.class);
	/**
	 * @purpose top-level method that archives the current hardware records into the  hardware_archive 
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
			TableData.insertIntoHardwareArchive(hardwareList, 0);
			
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
}
