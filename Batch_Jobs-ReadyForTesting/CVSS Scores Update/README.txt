Developer: Sara Bergman, G2 Ops Virginia Beach
Created April 20, 2018
Purpose: BatchJobs.jar stores batch jobs needed to maintain URM Cassandra database consistency 
Java Version: 1.8

Update CVSS Scores with NVD vulnerability data
----------------------------------------------
Paramaters: 	args[0] = Organization Keyspace
		args[1] = boolean: true if running after scan

Run: 		Once immediately after running a scan

Description: 	Updates the current vulnerabilities, collects installed software from the hardware table of a given keyspace, collects new vulnerabilities from appl_auth.common_vulnerabilities that apply to installed software, adds new vulnerabilities to current vulnerabilities, and updates the cvss_scores, vulnerability_count, and run_datetime for each record.
----------------------------------------------
Paramaters: 	args[0] = Organization Keyspace
		args[1] = boolean: false

Run: 		every 2 hrs

Description: 	Updates the current vulnerabilities, collects installed software from the hardware table of a given keyspace, collects new vulnerabilities from appl_auth.common_vulnerabilities that apply to installed software, adds new vulnerabilities to current vulnerabilities, and updates the cvss_scores, vulnerability_count, and run_datetime for each record.