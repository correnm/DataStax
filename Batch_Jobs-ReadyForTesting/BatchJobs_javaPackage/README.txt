Developer: Sara Bergman, G2 Ops Virginia Beach
Created April 20, 2018
Purpose: BatchJobs.jar stores batch jobs needed to maintain URM Cassandra database consistency 
Java Version: 1.8

Jobs
1. Import CPE Dictionary
	-Class.Method(Param): 	CPE_Dictionary_Import.ArchiveUpdatedCpeDictionary(String localFileLocation)
	-Run: 					daily
	-Description: 			Importing official cpe dictionary (.xml) from NIST into appl_auth.common_platforms table. The localFileLocation parameter is the directory where the official 
2. Update CVSS Scores with NVD vulnerability data
	-Class.Method(Param): 	CVSS_Scores_Update.UpdateCvssScores(String keyspace, boolean afterScan)
	-Run: 					every 2 hrs
	-Description: 			Collects installed software from the hardware table of a given keyspace, collects new vulnerabilities from appl_auth.common_vulnerabilities that apply to installed software, and updates current vulnerabilities. afterScan: true if job is run immediately after importing scan data, false if scores have been updated since scan.
3. Archive Today's hardware
	-Class: 				Archive_Hardware.archiveTodaysData(String keyspace)
	-Run: 					daily
	-Description: 			Collects all records within the hardware table and archives data within the hardware_archive table. Does not clear the current hardware table.  
4. Rollup today's hardware into hardware_daily_summary
	-Class.Method(Param):	Hardware_Sumary_Rollups.rollupHardwareDaily(String keyspace)
	-Run: 					daily
	-Description: 			specific keyspace and counts the number of vulnerabilities and patches for each vulnerability rating critical,high, medium, and low and inserts data into the hardware_daily_summary table
5. Rollup this week's hardware into hardware_weekly_summary
	-Class.Method(Param):	Hardware_Sumary_Rollups.rollupHardwareWeekly(String keyspace)
	-Run: 					daily(for up-to-date rollup of partial cur week) or weekly (for no partial weeks)
	-Description: 			Collects the current week's data from the hardware_archive table from a specific keyspace and counts the number of vulnerabilities and patches for each vulnerability rating critical, high, medium, and low and 	inserts data into the hardware_weekly_summary table
6. Rollup this month's hardware into hardware_monthly_summary
	-Class.Method(Param):	Hardware_Sumary_Rollups.rollupHardwareMonthly(String keyspace)
	-Run: 					daily(for up-to-date rollup of partial cur month) or weekly (for no partial months)
	-Description: 			Collects the current week's data from the hardware_archive table from a specific keyspace and counts the number of vulnerabilities and patches for each vulnerability rating critical, high, medium, and low and 	inserts data into the hardware_weekly_summary table.
7. Rollup all data from hardware_archive into hardware_daily_summary
	-Class.Method(Param):	Hardware_Summary_Rollups.rollupHardwareDaily_allHardwareArchive(String keyspace)
	-Run: 					once. if needed for demo purposes
	-Description: 			Collects all data from the hardware_archive table from a specific keyspace and rolls up vulnerability and patch counts into the hardware_daily_summary table. Queries data by the sites and subnets in the current hardware table. 
8.Rollup all data from hardware_archive into hardware_weekly_summary
	-Class.Method(Param):	Hardware_Summary_Rollups.rollupHardwareWeekly_allHardwareArchive(String keyspace)
	-Run: 					once. if needed for demo purposes
	-Description: 			Collects all data from the hardware_archive table and rolls up vulnerability and patch counts into the hardware_weekly_summary table. Queries data by the sites and subnets in the current hardware table.
9. Rollup all data from hardware_archive into hardware_monthly_summary
	-Class.Method(Param):	Hardware_Summary_Rollups.rollupHardwareMonthly_allHardwareArchive(String keyspace)
	-Run: 					once. if needed for demo purposes
	-Description: 			Collects all data from the hardware_archive table and rolls up vulnerability and patch counts into the hardware_monthly_summary table. Queries data by the sites and subnets in the current hardware table. 
10. Fill hardware_archive with randomized hardware data
	-Class.Method(Param):	Archive_Hardware.HardwaretoHardwareArchive_phoData(String keyspace, int days)
	-Run: 					for demo purposes only
	-Description: 			Collects the current list of hardware data and fills the hardware_archive table with a history of a specified number of days. Current hardware is collected once and randomized once for each day of history that is 	created.   
	