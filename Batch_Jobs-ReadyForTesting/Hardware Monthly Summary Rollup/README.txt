Developer: Sara Bergman, G2 Ops Virginia Beach
Created April 20, 2018
Java Version: 1.8

Rollup this month's hardware into hardware_monthly_summary
----------------------------------------------
Paramaters: 	args[0] = Organization Keyspace

Run: 		Daily

Description: 	Collects all records within the hardware_archive table for this month, counts the number of vulnerabilities and patches for each vulnerability rating critical, high, medium, and low, and inserts data into the hardware_monthly_summary table.
