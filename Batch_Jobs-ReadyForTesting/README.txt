Created May 9, 2018

Purpose: This folder is for URM batch jobs ready to be tested on the server. 

For each job, create a folder that includes:
1. A README.txt, speficying:
	- Title
	- Paramaters: Specify the parameters needed
	- Run: how frequently the job needs to run (time period and periodicity).
		-ex. every 2 hours on the half hour 
	- Description: what data the job uses, what is meant to be accomplished, and any extra clarification of paramaters and parameter options if necessary.
	- Java Version: what version of java was used to create/run the job 
2. The executable jar file.
 


Double Check:
=> Required paramaters are presented first for job to run.
	-incorrect multi-parameter input: "optional parameter" "optional parameter" "required parameter"
	-correct multi-parameter input: "required parameter" "optional parameter" "optional parameter"
=> The session and cluster closes when done.
=> The session and cluster closes if a fatal error is encountered.
=> Job is logged using slf4j Logging.
=> Any print to console is removed.
-----------Congifurations Needed before Implementation-----------
=> Approporiate permissions are used. 
=> Severe errors are logged in application_logging
	-will need to print to an output file when fails to connect to keyspace