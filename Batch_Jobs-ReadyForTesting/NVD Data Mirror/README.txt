NIST NVD Data Mirror

jar file: CVE_Batch_Job.jar

This code allows users to download xml files from https://nvd.nist.gov/vuln/data-feeds to obtain yearly Common Vulnerabilities 
and Exposures (CVE) information and the list of recently modified or exposed CVE IDs. Files are parsed and saved to a .csv file 
or to Cassandra. 

File Manifest:
CVE_Batch_Job.NistDataMirror.java
Utils.Connect2Db.java
Utils.cve2Cassandra.java
Utils.CVErecord.java
Utils.MyHandler.java
Utils.XMLParserSAX.java

Parameters:
REQUIRED - Argument 1: "file directory path"
REQUIRED - Argument 2: csv or database
REQUIRED - Argument 3: mod OR mirror
REQUIRED - Argument 4: xml OR json
REQUIRED IF MIRROR - Argument 5: start year OR all
REQUIRED IF MIRROR AND ARGUMENT 5 DOES NOT EQUAL ALL - Argument 6: end year

Batch Job to update common_vulneratbilites in Cassandra:
This job will run every 2 hours to capture the modified and updated CVE IDs by downloading, parsing, and saving the CVE-Modified file to Cassandra. 
Class/method/param: NistDataMirror.main("", database, mod, xml)

Code Dependencies: 
cassandra-driver-core-3.1.4.jar located at https://mvnrepository.com/artifact/com.datastax.cassandra/cassandra-driver-core/3.1.4 
jsoup-1.11.2.jar located at https://mvnrepository.com/artifact/org.jsoup/jsoup/1.11.2
slf4j-api-1.5.6.jar located at https://mvnrepository.com/artifact/org.slf4j/slf4j-api/1.5.6
javax.mail-1.5.0.jar located at https://mvnrepository.com/artifact/com.sun.mail/javax.mail/1.5.0
commons-io-2.4.jar located at https://mvnrepository.com/artifact/commons-io/commons-io/2.4
opencsv-3.8.jar located at https://mvnrepository.com/artifact/com.opencsv/opencsv/3.8