Developer: Sara Bergman, G2 Ops Virginia Beach
Created April 20, 2018
Java Version: 1.8

Rollup today's hardware into hardware_daily_summary
----------------------------------------------
Paramaters: 	args[0] = Organization Keyspace

Run: 		Daily

Description: 	Collects all records within the hardware table, counts the number of vulnerabilities and patches for each vulnerability rating critical,high, medium, and low, and inserts data into the hardware_daily_summary table
