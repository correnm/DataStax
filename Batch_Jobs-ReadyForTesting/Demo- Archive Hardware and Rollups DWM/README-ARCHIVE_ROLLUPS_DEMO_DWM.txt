Developer: Sara Bergman, G2 Ops Virginia Beach
Created April 20, 2018
Java Version: 1.8

Archive Today's hardware USED FOR DEMO PURPOSES ONLY
----------------------------------------------
Paramaters: 	args[0] = Organization Keyspace

Run: 		Daily

Description: 	Collects all records within the hardware table, randomizes the list by removing about between 0-10% of the current architecture  and archives data within the hardware_archive table. Does not clear the current hardware table. It then creates a daily, weekly, and monthly summary of the randonized list for each ip_subnet_or_building and inserts the records into the hardware_daily_summary, hardware_weekly_summary, and hardware_monthly_summary tables.





