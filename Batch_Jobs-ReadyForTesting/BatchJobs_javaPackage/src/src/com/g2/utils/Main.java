package src.com.g2.utils;

import src.com.g2.jobs.ArchiveHardware;
import src.com.g2.jobs.Demo_Archive_Rollups;
import src.com.g2.jobs.CpeDictionaryImport;
import src.com.g2.jobs.CvssScoresUpdate;
import src.com.g2.jobs.Hardware_Summary_Rollups;

public class Main {

	public static void main(String[] args) {
		
//		TableData.deleteNA_commonVulnerabilities();
		
		
//		CPE_Dictionary_Import.ArchiveUpdatedCpeDictionary("C:\\Users\\SaraProkop\\Documents\\URM\\Parsing\\temp");
//		Demo_Archive_Rollups.HardwaretoHardwareArchive_phoData("dod", 10);
//		System.out.println("*************************************************HARDWARE ARCHIVE DONE.*************************************************");
//		Hardware_Summary_Rollups.rollupHardwareDaily_allHardwareArchive("g2");
//		System.out.println("*************************************************HARDWARE DAILY DONE.*************************************************");
//		Hardware_Summary_Rollups.rollupHardwareWeekly_allHardwareArchive("g2");
//		System.out.println("*************************************************HARDWARE WEEKLY DONE.*************************************************");
//		Hardware_Summary_Rollups.rollupHardwareMonthly_allHardwareArchive("g2");
//		System.out.println("*************************************************HARDWARE MONTHLY DONE.*************************************************");
//		System.out.println("Done.");
//		Demo_Archive_Rollups.main(new String[]{"g2"});
		CvssScoresUpdate.main(new String[] {"g2", "false"});
	}
	
}
 