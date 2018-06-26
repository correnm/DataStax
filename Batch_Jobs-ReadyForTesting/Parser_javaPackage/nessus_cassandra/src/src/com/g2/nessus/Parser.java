 //parseNessus_has no tracehops showing
 package src.com.g2.nessus;

import java.awt.Component;
//import java.awt.Cursor;
//import java.io.BufferedReader;
/**
 * 
 * @author Corren McCoy and Sara bergman, G2 East, Virginia Beach, VA
 * Advantage of SAX parser in Java:
 * It is faster than DOM parser because it will not load the XML document into memory.
 * It's an event based handler.
 *
 * Dependencies:
 * http://opencsv.sourceforge.net/				-- simple csv parser library for Java
 * https://sourceforge.net/projects/opencsv/	-- opencsv-3.8.jar
 * 
 * Interface:
 * -- no registration or API key is required for up to 10,000 requests per day.
 * https://macvendors.com/
 * -- API is free and without limits
 * https://macvendors.co/api		
 * -- File download directly from IEEE. Will need to be refreshed periodically to get latest entries on the oui.csv
 * https://regauth.standards.ieee.org/standards-ra-web/pub/view.html#registries
 * 
 * Coding References:
 * http://javarevisited.blogspot.com/2011/12/parse-read-xml-file-java-sax-parser.html
 * https://www.tutorialspoint.com/java_xml/java_sax_parse_document.htm
 * http://www.journaldev.com/1198/java-sax-parser-example
 * https://rosettacode.org/wiki/MAC_Vendor_Lookup
 * https://sourceforge.net/projects/opencsv/?source=typ_redirect
 * 
 * Modification History:
 * 01/06/2017		cmccoy		Created this package
 * 02/01/2017		cmccoy		No internet connection in the lab.
 * 05/30/2017		sprokop		Changed mac address api calls to use an OUI file lookup to find the vendor. 
 * 06/29/2017		sprokop		Created outputs for connecctor-ends and hop-ports with service Name and protocol per host
 * 09/18/2017		sprokop		Updated the vendor and mac address matching and the Qualified Name output, creating version 2. 
 * 
 */
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.opencsv.CSVWriter;

import src.com.g2.types.Connection;
import src.com.g2.types.ReportItem;
import src.com.g2.types.Software_UDT;
import src.com.g2.types.ReportHost;
import src.com.g2.types.UniqueList;

public class Parser  extends DefaultHandler {
	/**Determines the type of parsing, which is set by the user in the GUI*/
	private static String type;
	
	//below 3 are currently unused, but might want to enhance the site and version features in the future. 
	public static String site = "SUBCOMM";
	public static final String NESSUS_VERSION = "NessusClientData_v2";  // this version must be in the header tag
	public static final String VENDOR_FILE    = "parser_resources" +File.separatorChar +"vendors.csv";          // table export from MagicDraw


	/**loaded with vendors and Qualified Names from the vendors.csv */
	public static HashMap<String, String> magicDrawVendors = new HashMap<String, String>();
	/**resource file used for vendor references */
	public static String defaultVendorFile = "parser_resources" +File.separatorChar + "vendors.csv";
	public static Scanner scanner = null;
	public static boolean loadStatus = false;

	//below URL is not used because the matching mac->vendor->qualifiedname process must occur apart from the web
	/** Base URL for API. The API from www.macvendors.com was chosen. */
	private static final String baseURL = "http://api.macvendors.com/";	
	//will replace the above lookup vendor location
	public static String defaultMacFile = "parser_resources" +File.separatorChar +"oui.csv"; 
	/**loaded with Mac Address and Organization names from oui.csv*/
	private static HashMap<String, String> ouiMacLookUp = new HashMap<String,String>();
	
	/** holds the data retrieved from the XML file for each host */
	private static ArrayList<ReportHost> hostList  = new ArrayList<ReportHost>();
	/**holds the list of the conversations within the lightning files */
	private static List<Connection> connList  = new ArrayList<Connection>();
	
	/**holds entries to be written on the connector-ends.csv */
	private static ArrayList<String> connectorEndsList = new ArrayList<String>();

	/**maps the host with its trace-route hops */
	static Map <String, List<String>> hostTraceRmap = new HashMap<String, List<String>>();
	/** stores a list of every source with each of its destinations. */
	static Map<String, List<String>> connectedElementsMap = new HashMap<String, List<String>>();
	
	/**Associates each host's ip with its name that is not an ip address */
	private static HashMap<String, String> uniqueNames = new HashMap<String,String>();
	
	/**sets a pattern for detecting word names appart from IP's */
	private static final String IPADDRESS_PATTERN = 
	"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + 
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + 
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + 
	"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$"; 
	//declaring "i" which keeps track of the unique names
	static int i;
	private String curFileName;
		
	private String temp;
	private String tagName;
	private static ReportHost host;
	private static Connection conn;
	private static ReportItem item;

	private static File file;
	
	// indicator for attributes in <tag name=____ >
	boolean bHost				= false;
	boolean bHost_end 			= false;
	boolean bMacAddress 		= false;
	boolean bIp 				= false;
	boolean bHostStart			= false;
	boolean bOs 				= false;
	boolean bOperatingSystem	= false;
	boolean bSystemType			= false;
	boolean bHostFqdn			= false;
	boolean bInstalledSoftware	= false;
	boolean bTraceRouteHops		= false;
	boolean bCvssBaseScore		= false;
	boolean bCvssTemporalScore	= false;
	boolean bPort				= false;
	boolean bProtocol			= false;
	boolean bSrvName			= false;
	boolean bReportHost			= false;
	boolean bCve				= false;
	boolean bBaseScore			= false;
	boolean bTemporalScore		= false;
	boolean bVector				= false;
	boolean bTempVector			= false;
	boolean bDescription		= false;
	boolean bVulnPub			= false;
	boolean bPatchPub			= false;
	boolean bSolution			= false;
	boolean	bVendor				= false;
	boolean bSource 			= false;
	boolean bTarget				= false;
	boolean bServer				= false;
	boolean bArp 				= false;
	boolean bConnection			= false;
	boolean bPluginModDate		= false;
	boolean bPluginPubDate 		= false;
	boolean bPluginType			= false;
	boolean bRiskFactor 		= false;
	boolean bScriptVersion 		= false;
	boolean bSynopsis			= false;
	boolean bFName				= false;
	
	//lists for Lightning scans
	/**holds all the ip addresses in lightning scan */
	private static  UniqueList ipBlockEntries = new UniqueList();
	/**holds a list of servers in the lightning file */
	private static ArrayList<ReportHost> serverList = new ArrayList <ReportHost>();
	/**holds a list of arp records in the lightning file */
	private static List<ReportHost> arpList = new ArrayList <ReportHost>();
	/**holds a list of all information gathered from the Lightning scans */
	private static HashMap<String, ReportHost> ipReportHost = new HashMap<String, ReportHost>();
	/**Keeps track of the number of hosts without an ip*/
	static int numIp;
	private static HashMap<String, List<ReportHost>> softwareHostsMap = new HashMap<String, List<ReportHost>>();

	
	/**Constructor for Parser
	 * @param type determines the type of file it is parsing */
	public Parser(String fileType){
		this.type = fileType;
	}
	
	/** Starts parsing the files parsing tag-by-tag
	 * @param inputFiles nessus file(s) selected for parsing*/
	public void startParser(File[] inputFoldersFiles) throws IOException, ParserConfigurationException,
	SAXException {
		Scanner keyboard = new Scanner(System.in);
		 String statusMessage = null;
		 
		 //Shows you what you selected at the top level
		 for (int i = 0; i < inputFoldersFiles.length; i++) {
				if (inputFoldersFiles[i].isFile()) {
					System.out.println("File " + inputFoldersFiles[i].getName());				
				} else if (inputFoldersFiles[i].isDirectory()) {
					System.out.println("Directory " + inputFoldersFiles[i].getName());
				}
		 
		 //clears the list in the case of a second  (non-simultaneous) parsing
		 hostList.clear();
		 connectorEndsList.clear();
		 hostTraceRmap.clear();
		 serverList.clear();
		 arpList.clear();
		 ipReportHost.clear();
		 ipBlockEntries.clear();
		 connList = new ArrayList<Connection>();
		 numIp = 0;
		 i=0;
		
		try {
			// read all the files in the import directory into an array
				loadStatus = vendorMap();
				if (loadStatus)
					statusMessage = "Vendor file loaded successfully.";

				statusMessage = "Parser completed successfully.";
					
				File[] listOfFiles = getFiles(inputFoldersFiles);
		
				if (listOfFiles.length == 0){
					Warnings.noFile();
					return;
				}
				
				//shows what the parsed files are
				System.out.println("-----Files to Parse-----");
				for (File file : listOfFiles)
					System.out.println(file.getName());
				System.out.println("------------------------");
				
				for (int f = 0; f < listOfFiles.length; f++) {
					//The File.getAbsolutePath() will give you the full complete path name (filepath + saveTo) of a file
					File scanFile = new File(listOfFiles[f].getAbsolutePath());
					curFileName = scanFile.getName();
					System.out.println("--CURRENT FILE--"+curFileName);
		
					// Read: http://javarevisited.blogspot.com/2011/12/parse-read-xml-file-java-sax-parser.html
		            //Create a "parser factory" for creating SAX parsers
		            SAXParserFactory spfac = SAXParserFactory.newInstance();
		
		            //Now use the parser factory to create a SAXParser object
		            SAXParser sp = spfac.newSAXParser();
		
		            //Create an instance of this class; it defines all the handler methods
		            Parser handler = new Parser(type);
		
		            //Finally, tell the parser to parse the input and notify the handler
		            try{
		            	sp.parse(scanFile, handler);
		            	
		            	if (type == "lightning"){
		            		handler.sortLightningData();
		            	}else if (type == "nessus"){
		            		handler.createConnectedElements();
		            	}
		            	
		            	System.out.println("hostList size: " + hostList.size());
		            
		            }catch (SAXParseException e){
		            	e.printStackTrace();
		            	Warnings.showNonCompatibleFile();
		            }
	        } // end for (list all files in directory)
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("********* Parser Status **********");
			System.out.println(statusMessage);
		}
    	// close all open resources to avoid compiler warning
		if (keyboard != null)
	    	keyboard.close();
		} 
	}
	
	/**@purpose to list out files within any directorys that may be selected and filter out any files that are not Nessus or XML files
	 * @return An array of .nessus or .xml files */
	public File[] getFiles(File[] filesFolders){
		List<File> fileList = new ArrayList<>();
		for (File file : filesFolders){
			if (file.isDirectory()){
				File[] dirFiles = file.listFiles();
				for (File dirFile : dirFiles){
					if (FilenameUtils.getExtension(dirFile.getAbsolutePath()).equals("nessus") || FilenameUtils.getExtension(dirFile.getAbsolutePath()).equals("xml")){
//						System.out.println("added: "+dirFile.getAbsolutePath());
						fileList.add(dirFile);
					}else{
						File[] subDir = getFiles(new File[]{dirFile});
						fileList.addAll(Arrays.asList(subDir));
					}
				}
			}else{
				if (FilenameUtils.getExtension(file.getAbsolutePath()).equals("nessus") ||FilenameUtils.getExtension(file.getAbsolutePath()).equals("xml")){
//					System.out.println("added: "+file.getAbsolutePath());
					fileList.add(file);
				}
			}
		}
		File[] files = fileList.toArray(new File[fileList.size()]);
		return files;
	}
	
	/**Loads a hash map, connecting vendors with qualified names */
	public boolean vendorMap() throws Exception {
		//trying to help match the web vendor to the vendor in the .csv
		Soundex soundex = new Soundex();
		String vendor;
		String vendorFile;

		try{
			
			//looks for vendor file located in the same path location of the parser. Open the file for reading. Throw an exception if not found.
			File vendors = new File(Parser.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			String path = vendors.getAbsolutePath();
			String file = vendors.getName();
			//shows the name of the vendor file on the console

			vendorFile = path.replace(file, "") + defaultVendorFile;
			System.out.println("Vendor File: " + vendorFile);
			
			scanner = new Scanner(new FileReader(vendorFile));
		
			while (scanner.hasNextLine()) {
				// comma separator is expected delimiter
				//2	3Com	Communications Profile::Vendor Hardware & Software::Vendor Folder::3Com
				String[] columns = scanner.nextLine().split(",");
    		
				//saves the soundex code for the vendor to match with the information from the oui.csv
				// column 1 = Vendor name, column 2 = MagicDraw Qualified Name
				// discards the "Inc." and other endings from the vendor name found in the vendor.csv to allow for more accurate matching. 
				// "Inc." exists in the vendor.csv but not in the oui.csv. 
				vendor = stripName(columns[1]);
				magicDrawVendors.put(soundex.soundex(vendor), columns[2]);
			} // end while
    	
			// close all open resources to avoid compiler warning
			if (scanner != null)
				scanner.close();
	    
			// Got here without an exception. All good.
			loadStatus = true;
	    
		}catch (FileNotFoundException e){
			e.printStackTrace();
			Warnings.showFileNotFound("vendors.csv", "\nResult: 'qualifiedName' will not be accurate.");
		}
	    
	    return loadStatus;
	} // end vendorMap

    /**Every time the parser encounters the beginning of a new element,
     * it calls this method, which resets the string buffer*/ 
    public void startElement(String uri, String localName,
                  String qName, Attributes attributes) throws SAXException {
    	//System.out.println("qName: " + qName); -- wanted to see what the options for qName were
    	temp = "";
    	//the parser parses the files based on the type of files that are selected
    	switch(type){
    	case "nessus":
    	
    		if (qName.equalsIgnoreCase("ReportHost")) {
    			host = new ReportHost();
    			host.setHost(attributes.getValue("name"));
//    			setUniqueNames(host);
    		} else if (qName.equalsIgnoreCase("tag")) {
    			// save the name of the attribute name=____ so we determine which value to update
    			tagName = attributes.getValue("name").toLowerCase();        	   
        	   
    			switch (tagName){
    				case "host_end": 			bHost_end = true; 			break;
    				case "host_start":			bHostStart = true;			break;
    				case "mac-address": 		bMacAddress = true; 		break;
    				case "host-ip":				bIp = true; 				break;
    				case "os":					bOs = true; 				break;
    				case "operating-system":	bOperatingSystem = true; 	break;
    				case "system-type":			bSystemType = true;			break;
    				case "host-fqdn":			bHostFqdn = true;			break;
    			}
    		
    			if (tagName.contains("cpe-")) {
    				bInstalledSoftware = true;
    			} else if (tagName.contains("traceroute-hop")) {//yes
    				bTraceRouteHops = true;
    			} 
    		} else if (qName.equalsIgnoreCase("cvss_base_score")) {
    			bCvssBaseScore = true;
    		} else if (qName.equalsIgnoreCase("cvss_temporal_score")) {
    			bCvssTemporalScore = true;
    		}else if (qName.equalsIgnoreCase("ReportItem")){
    			String port = attributes.getValue("port");
    			String service = attributes.getValue("svc_name");
    			String protocol = attributes.getValue("protocol");
    			String severity = attributes.getValue("severity");
    			String pluginID = attributes.getValue("pluginID");
    			String pluginName = attributes.getValue("pluginName");
    			String pluginFamily = attributes.getValue("pluginFamily");
    			host.setReportItem(port,service, protocol, severity, pluginID, pluginName, pluginFamily);
    			item = host.getItem();
    		}else if (qName.equalsIgnoreCase("cve")){
    			bCve = true;
    		}else if(qName.equalsIgnoreCase("cvss_base_score")){
    			bBaseScore= true;
    		}else if (qName.equalsIgnoreCase("cvss_temporal_score")){
    			bTemporalScore = true;
    		}else if (qName.equalsIgnoreCase("description")){
    			bDescription = true;
    		}else if (qName.equalsIgnoreCase("vuln_publication_date")){
    			bVulnPub = true;
    		} else if (qName.equalsIgnoreCase("patch_publication_date")){
    			bPatchPub = true;
    		}else if (qName.equalsIgnoreCase("solution")){
    			bSolution = true;
    		}else if (qName.equalsIgnoreCase("cvss_vector")){
    			bVector = true;
    		}else if (qName.equalsIgnoreCase("cvss_temporal_vector")){
    			bTempVector = true;
    		}else if (qName.equalsIgnoreCase("plugin_modification_date")){
    			bPluginModDate = true;
    		}else if (qName.equalsIgnoreCase("plugin_publication_date")){
    			bPluginPubDate = true;
    		}else if (qName.equalsIgnoreCase("plugin_type")){
    			bPluginType = true;
    		}else if (qName.equalsIgnoreCase("risk_factor")){
    			bRiskFactor = true;
    		}else if (qName.equalsIgnoreCase("script_version")){
    			bScriptVersion = true;
    		}else if (qName.equalsIgnoreCase("synopsis")){
    			bSynopsis = true;
    		}else if (qName.equalsIgnoreCase("fname")){
    			bFName = true;
    		}
    	
    	case "lightning":
    		switch(qName){
    		
    		//creates a new Host when a Host tag appears. Gives the host its given name. 
    		case "Host"			:		bHost = true;
    									host = new ReportHost(); 	break;
        	//Appears under the Host name, so it does not need to start a new Host
    		case "IpAddress"	:		bIp = true;					break;
        	//needs to create a new host, check if there already exists a host with that IP Address
    		case "IP"			:		bIp = true;					break;
        	//needs to start a new host to capture host in order to capture the port with the ip, that may or may not already have a host name.
    		case "Server"		:		bServer = true;				
    									host = new ReportHost();	break;
        	case "Port"			:		bPort = true;				break;
        	//needs to start a new host in order to capture MAC, IP, and Vendor
        	case "Arp"			:		bArp = true;				
        								host = new ReportHost(); 	break; //Address Resolution Protocol
        	case "MAC"			: 		bMacAddress = true;			break;
        	case "Vendor"		:		bVendor = true;				break;
        	//starts a new connection
        	case "Conversation" :		bConnection = true;
        								conn = new Connection();	break;
        	// host started and added to 
        	case "Node1"		:		bSource = true;				break;
        	//may start a new host?
        	case "Node2"		: 		bTarget = true;				break;
        	}
    	}//end switch
    } // end StartElement

   
    /**When the parser encounters the end of an element, it calls this method*/
    public void endElement(String uri, String localName, String qName)
                  throws SAXException {
//    	System.out.println("Temp: "+ temp);
    	switch(type){
    	case "nessus":
    		
    		if (qName.equalsIgnoreCase("ReportHost")) {
    			// add new host to the list
    			hostList.add(host);
    		} else if (bHost_end) { 
    			host.setScanDate(temp);
    			bHost_end = false;
    		} else if (bMacAddress) {
    			host.setMacAddress(temp);
    			// MacAddress can be 1:M in the Nessus file separated by newline.
    			// All will be for the same vendor so just use the first one in the list
    			String[] macList = temp.split("\n");
    			// use the IEEE lookup
    			String vendor;
    			try {
    				vendor = lookupVendor(macList[0]).replaceAll("^\"|\"$", "");
    			} catch (IOException e) {
    				vendor = "N/A";
    			}catch(NullPointerException e){
    				vendor = "N/A";
    			}
    			host.setVendor(vendor);
    			host.setQualifiedName(lookupMagicDrawVendor(vendor));
    			bMacAddress = false;
    		} else if (bIp) {
    			host.setIpAddress(temp.trim());
    			setUniqueNames(host);
    			ipReportHost.put(temp, host);
    			bIp = false;
    		}else if (bHostStart){
    			host.setRunDate(temp);
    			bHostStart = false;
    		}else if (bOs) {
    			host.setOs(temp);
    			bOs = false;
    		} else if (bOperatingSystem) {
    			host.setOperatingSystem(temp);
    			bOperatingSystem = false;
    		} else if (bSystemType) {
    			host.setSystemType(temp);
    			bSystemType = false;
    		} else if (bHostFqdn) {
    			host.setFqdn(temp);
    			bHostFqdn = false;
    		} else if (bInstalledSoftware) {
    			if (temp.contains("cpe:/")){
	    			if (softwareHostsMap.containsKey(temp)){
	    				List<ReportHost> hosts = softwareHostsMap.get(temp);
	    				hosts.add(host);
	    				softwareHostsMap.put(temp, hosts);
	    			}else{
	    				List<ReportHost> hosts = new ArrayList<ReportHost>();
	    				hosts.add(host);
	    				softwareHostsMap.put(temp,hosts);
	    			}
	    			host.setInstalledSoftware(temp);
    			}
    			bInstalledSoftware = false;
    		} else if (bTraceRouteHops) {
    			host.setTraceRouteHops(temp);
    			bTraceRouteHops = false;
    		} else if (bCvssBaseScore) {
    			host.setCVSSBaseScore(temp);
    			item.setBaseScore(temp);
    			bCvssBaseScore = false;
    		} else if (bCvssTemporalScore) {
    			host.setCVSSTemporalScore(temp);
    			item.setTempScore(temp);
    			bCvssTemporalScore = false;
    		} else if (bCve){
	    		item.setCveId(temp);
	    		bCve = false;
	    	}else if (bDescription){
	    		item.setDescription(temp);
	    		bDescription = false;
	    	}else if (bVulnPub){
	    		item.setVulPublicationDate(temp);
	    		bVulnPub = false;
	    	}else if (bPatchPub){
	    		item.setPatchPublicationDate(temp);
	    		bPatchPub = false;
	    	}else if (bSolution){
	    		item.setSolution(temp);
	    		bSolution = false;
	    	}else if (bVector){
	    		item.setCvssVector(temp);
	    		bVector = false;
	    	}else if (bTempVector){
	    		item.setCvssTempVector(temp);
	    		bTempVector = false;
	    	}else if (bPluginModDate){
	    		item.setPluginModDate(temp);
	    		bPluginModDate = false;
	    	}else if (bPluginPubDate){
	    		item.setPluginPubDate(temp);
	    		bPluginPubDate = false;
	    	}else if (bPluginType){
	    		item.setPluginType(temp);
	    		bPluginType = false;
	    	}else if (bRiskFactor){
	    		item.setRistFactor(temp);
	    		bRiskFactor = false;
	    	}else if (bScriptVersion){
	    		item.setScriptVersion(temp);
	    		bScriptVersion = false;
	    	}else if (bSynopsis){
	    		item.setSynopsis(temp);
	    		bSynopsis = false;
	    	}else if (bFName){
	    		item.setFName(temp);
	    		bFName = false;
	    	}
    	case "lightning":

//    		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//    		System.out.println("bWindowsWorkgroup: "+ Boolean.toString(bWindowsWorkgroup));
//    		System.out.println("bHost: 			   "+ Boolean.toString(bHost));
//    		System.out.println("bIp: 			   "+ Boolean.toString(bIp));
//    		System.out.println("bWorkgroupHost:    "+ Boolean.toString(bWorkgroupHost));
//    		System.out.println("bServer:		   "+ Boolean.toString(bServer));
//    		System.out.println("bPort:			   "+ Boolean.toString(bPort));
//    		System.out.println("bArp:			   "+ Boolean.toString(bArp));
//    		System.out.println("bVendor:		   "+ Boolean.toString(bVendor));
//    		System.out.println("bMacAddress: 	   "+ Boolean.toString(bMacAddress));
//    		System.out.println("bConnection: 	   "+ Boolean.toString(bConnection));
//    		System.out.println("bSource:		   "+ Boolean.toString(bSource));
//    		System.out.println("bTarget:		   "+ Boolean.toString(bTarget));
    		

        	if (qName.equalsIgnoreCase("Host")) { 
        		host.setHost(temp);
        		hostList.add(host);
        		bHost = false;
        	} else if (qName.equalsIgnoreCase("IP")||qName.equalsIgnoreCase("IPAddress")) {
        		host.setIpAddress(temp);
        		ipBlockEntries.add(temp);
        		bIp = false;
        	} else if (qName.equalsIgnoreCase("Server")){
        		serverList.add(host);
        		bServer = false;
        	}else if (qName.equalsIgnoreCase("Port")){
        		host.setReportItem(temp,"", "", "","", "", "");
        		host.setPort(temp);
        		bPort = false;
        	}else if (qName.equalsIgnoreCase("Arp")) {
        		arpList.add(host);
        		bArp = false;
        	} else if (qName.equalsIgnoreCase("MAC")) {
        		host.setMacAddress(temp);
        		bMacAddress = false;
        	} else if (qName.equalsIgnoreCase("Vendor")) {
        		String quaName = lookupMagicDrawVendor(temp);
//        		System.out.println(quaName);
        		String vendor = getVendorfromQuaName(quaName, temp);
        		host.setVendor(vendor);
        		host.setQualifiedName(quaName);
        		bVendor = false;
        	} else if (qName.equalsIgnoreCase("Conversation")) {
        		connList.add(conn);
        		bConnection = false;
        	} else if (qName.equalsIgnoreCase("Node1")) {
        		conn.setSource(temp);
        		if (isIP(temp)){
        			ipBlockEntries.add(temp);
        		}else{
        			host= new ReportHost();
        			host.setHost(temp);
        			hostList.add(host);
        		}
        		bSource = false;
        	} else if (qName.equalsIgnoreCase("Node2")) {
        		conn.setTarget(temp);
        		if (isIP(temp)){
        			ipBlockEntries.add(temp);
        		}else{
        			host= new ReportHost();
        			host.setHost(temp);
        			hostList.add(host);
        		}
        		bTarget = false;
        	}
    	}
    } 
    

    /** When the parser encounters plain text (not XML elements),
     * it calls this method which accumulates them in a string buffer*/
    public void characters(char[] buffer, int start, int length) {
    	// remove leading and trailing whitespace
    	temp = new String(buffer, start, length).trim();
    	
        if(temp.length() == 0) return; // ignore white space
    }
    
    /**@purpose to match the vendor name from the xml input report to the vendor name we have in Magic Draw
     * @param Qualified name and vendor name from the xml report
     * @return magicDraw vendor name if exists, if not the method returns the vendor name with a * to mark that it is new.
     * */
    private String getVendorfromQuaName(String quaName, String xmlVendor){
    	if (!(quaName.equals("N/A"))){
    		int colon = quaName.lastIndexOf(":");
    		String vendor = quaName.substring(colon+1);
    		return vendor;
    	}else{
    		return xmlVendor+"*";
    	}
    }
    
    public void sortLightningData(){
    	Iterator<ReportHost> serverIt = serverList.iterator();
    	Iterator<ReportHost> arpIt = arpList.iterator();
    	
    	Iterator<Connection> connIt = connList.iterator();

    	List<ReportHost> noIp= new ArrayList<ReportHost>();
    	ReportHost host;
    	String IP;
    	boolean found;
    	
    	String[] ipBlocks =  Arrays.copyOf(ipBlockEntries.toObjectArray(), ipBlockEntries.toObjectArray().length, String[].class);
//    	System.out.println("Iterating through ipBlockEntries: ");
//    	System.out.println("size of list: " + ipBlocks.length);
    	for (String ip : ipBlocks){
    		host = new ReportHost();
    		host.setIpAddress(ip);
    		ipReportHost.put(ip, host);
    	}
    	
//    	System.out.println("Iterating through hostList: ");
//    	System.out.println("size of ipHost: " + hostList.size());
    	Iterator<ReportHost> hostIt = hostList.iterator();
    	while(hostIt.hasNext()){
    		ReportHost ahost = hostIt.next();
//    		System.out.println(ahost.toString());
    		if (ahost.getIpAddress()==""){
    			//creating a list of hosts that do not have ip's
    			noIp.add(ahost);
    		}else{
    			host = ipReportHost.get(ahost.getIpAddress());
    			host.setHost(ahost.getHost());
//    			System.out.println("Host name: " + ahost.getHost());
//    			setUniqueNames(host);
    			uniqueNames.put(host.getIpAddress(), host.getHost());
    		}
    	}
    	Iterator<ReportHost> noipIt = noIp.iterator();
    	//for all hosts with no ip's check to see if 
    	while(noipIt.hasNext()){
    		found = false;
    		ReportHost name = noipIt.next();
    		for(ReportHost checkHost : ipReportHost.values()){
    			//if the host with no ip (just a name) matches a host aready collected, break and do nothing  
    			if (name.getHost().equals(checkHost.getHost())){
    				found = true;
    				break;
    			}
    		}
    		//if it doesn't then give it an ip address
    		if (!found){
    			host = new ReportHost();
    			host.setHost(name.getHost());
    			host.setIpAddress("ip_" + numIp);
    			ipReportHost.put(host.getIpAddress(), host);
    			uniqueNames.put(host.getIpAddress(), host.getHost());
    			numIp++;
    		}	
    	}

    	
//        System.out.println("Iterating through arpList: ");
//        System.out.println("size of list: " + arpList.size());
    	while(arpIt.hasNext()){
    		ReportHost arp = arpIt.next();
    		host = ipReportHost.get(arp.getIpAddress());
    		host.setMacAddress(arp.getMacAddress());
    		host.setVendor(arp.getVendor());
    		host.setQualifiedName(arp.getQualifiedName());
//    		System.out.println("MAC: " + arp.getMacAddress() + " IP: " + arp.getIpAddress() + " Vendor: " + arp.getVendor());
    		//I need to check if this vendor is in the MagicDrawVendorList. if not, insert * before it, if it is, get the qualified name.
    	}
    		
    	
//    	System.out.println("Iterating through serverList: ");
//    	System.out.println("size of list: " + serverList.size());
    	while (serverIt.hasNext()){
    		ReportHost server = serverIt.next();
    		host = ipReportHost.get(server.getIpAddress());
    		host.setReportItems(server.getReportItems());
    		setUniqueNames(host);
    		server.setHost(host.getHost());
    		uniqueNames.put(host.getIpAddress(), host.getHost());
//    		setUniqueNames(server);
//    		System.out.println("IPAddress: " + server.getIpAddress()+ " PortsList: " + server.getPorts().size());
    	}
    	
      	ArrayList<ReportHost> reportHosts = new ArrayList<ReportHost>(ipReportHost.values());
    	Iterator<ReportHost> rhIt = reportHosts.iterator();
    	while (rhIt.hasNext()){
    		ReportHost curHost = rhIt.next();
//    		System.out.println(curHost.toString());
    		setUniqueNames(curHost);
    	}

    	while (connIt.hasNext()){
    		Connection conn = connIt.next();
    		
    		String source = (String) conn.getSource();
    		//source is already captured as ip in ipReportHost
    		if (ipReportHost.containsKey(source)){
    			ReportHost match= ipReportHost.get(source);
    			conn.setSourceName(match.getHost());
    		//source is captured as host name in ipReportHost or a name not captured in ipReportHost
    		}else if (!(isIP(source))){
    			conn.setSourceName(source);
    		//source is an ip that is not captured
    		}else{
    			//This should not occur
    			System.out.println("I am am ip and not captured by ReportHost: " + source);
    		}
    		String target = (String) conn.getTarget();
    		
    		if (ipReportHost.containsKey(target)){
    			ReportHost match= ipReportHost.get(target);
    			conn.setTargetName(match.getHost());
    		//source is captured as host name in ipReportHost or a name not captured in ipReportHost
    		}else if (!(isIP(target))){
    			conn.setTargetName(target);
    		//source is an ip that is not captured
    		}else{
    			//This should no occur
    			System.out.println("I am an ip and not captured by ReportHost: " + target);
    		}
    	}
    }
    
    
    /**creates a hash map of host names and assigns each host a unique name
     * @purpose to set unique names for each host in the connector ends output csv
     * @parm host each report host found in nessus file */
    public static void setUniqueNames(ReportHost host){
    	//if there is no ip, then give it a default
    	if (host.getIpAddress()==null || host.getIpAddress()==""){
    		host.setIpAddress("ip_" + numIp);
    		numIp++;
    	}

    	//if it doesn't have a host name, give it a default one and set it as host
    	if (host.getHost() == null || host.getHost()==""){
    		host.setHost("Host_"+ i); 
    		uniqueNames.put(host.getIpAddress(), host.getHost());
    		i++;
    	}
    	//if the host name is an ip, give it a non-ip host default name and set it as host
    	if (isIP(host.getHost())){ //sHost is an ip address
    		host.setHost("Host_"+ i);
    		uniqueNames.put(host.getIpAddress().trim(), "Host_"+ i );
    		i++;
    	}else{
    		uniqueNames.put(host.getIpAddress().trim(), host.getHost());
    	} 
    }
    
    public static boolean isIP(String input){
    	Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
    	Matcher matcher = pattern.matcher(input);
    	if (matcher.matches()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**@purpose to strip the input vendor name of any excess suffix to enable accurate soundex matching
     * @param vendor name
     * @return a stripped vendor name of "Inc.", "Co.", "Ltd.", "Corp."
     * */
    private String stripName(String name){
    	String stripped = name.replaceAll("Inc.", "");
    	stripped = stripped.replaceAll("Co.", "");
    	stripped = stripped.replaceAll("Ltd.", "");
    	stripped = stripped.replaceAll("Corp.","");
    	return stripped;
    }
    
    /**@purpose Performs lookup on supplied MAC address. It uses the first 3 paris of hexadecimal digits of the mac address to match against the oui.csv.
	 * @param macAddress MAC address to lookup.
	 * @return ManufaC address. */
	private  String lookupVendor(String macAddress) throws IOException {
		StringBuilder result = new StringBuilder();
		Soundex soundex = new Soundex();
		String firstSix = macAddress.replace(":", "").toUpperCase().substring(0,6);

		try{
			scanner = new Scanner(new FileReader(defaultMacFile));
			while (scanner.hasNextLine()){
			String[] columns = scanner.nextLine().split(",");
			String mac = columns[1]; 
			//took off the vendor name endings so that the soundex would match with the vendor name, which has also been stripped of various vendor name endings. 
			String vendor = stripName(columns[2]);
			//the oui.csv cuts off the zeros at the front of a mac address, which creates a mismatch for the lookup. 
			//putting back the zeros from the mac address columns allows look up form actual mac address to the first six in the csv
			while (mac.length()<6){
				mac = "0"+mac;
			}
			ouiMacLookUp.put(mac, vendor);
			//System.out.println("VENDOR INFORMATION: "+ columns[1] + " " + columns[2]);
			}
			if (scanner != null)
		    	scanner.close();
		}catch (FileNotFoundException a){
			a.printStackTrace();
			String addMessage = "\nA new oui.csv can be uploaded at: \nhttp://regauth.standards.ieee.org/standards-ra-web/pub/view.html#registries.";
			Warnings.showFileNotFound("oui.csv", addMessage);	
			System.exit(0);
		}
		 
		 String vendor = ouiMacLookUp.get(firstSix);
		 return vendor;
	}
	
	/**Uses mac address from parser to look up vendor in oui.csv and uses 
	 * the vendor to get the qualified name from the  pre-loaded vendors .
	 * If vendor is not found, returns "N/A"
	 * @parm vendorName taken from mac address look-up results
	 * @return Qualified name for specific vendor*/
	private String lookupMagicDrawVendor(String vendorName) {
		try {
			Soundex soundex = new Soundex();
			String result = new String();
			// Is this a vendor we already have in the master list?
			String vendor = stripName(vendorName);
			vendor = soundex.encode(vendor);
			result = magicDrawVendors.get(vendor);
			

			////////////////////////////The below is used only when the internet is able to be used. ///////////
			//private static final String baseURL = "http://api.macvendors.com/";	
			//StringBuilder result = new StringBuilder();
			//URL url = new URL(baseURL + macAddress);
			//System.out.println(url);
			//HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//conn.setRequestMethod("GET");
			//BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//String line;
			//while ((line = rd.readLine()) != null) {
			//	result.append(line);
			//}
			////webVendor
			//rd.close();
			//System.out.println(result);
			//return result.toString();
			////////////////////////////////////////////////////////////////////////////////////////////////////		
			

			return result == null ? "N/A" : result;
		} catch (Exception e) {
			e.printStackTrace();
			return "N/A";
		}
	}
	
	/** High-level method that gathers the traceRouteHops and creates a connector-ends list for the 
	 *  connector-ends output spreadsheet as well as the connector_ends in the database*/
    public void createConnectedElements(){
    	
    	//creates a map from host to a list of its trace route hops (hostTraceRmap)
//    	isolateTraceRouteHops();
//    	readHostTraceRmap();
    	
    	//creates a list of one-one connections (connectionsList)
    	buildConnectionsList();
//    	readConnList();
//    	readuniqueNames();
    	
    	//creates a map, connecting each source to its list of targets (connectedElementsMap)
    	buildConnectedElementsMap();
//    	readConnectedElementsMap();
    }
    
    /**@purpose Extracts the trace route hops from the hosts and creates a map of their host to trace route hops.*/
    public void isolateTraceRouteHops(){
    	    	
    	Iterator<ReportHost> it = hostList.iterator();
    	ReportHost curHost = new ReportHost();
    	while (it.hasNext()) {
    		curHost = it.next();
    		String routeHops = curHost.getTraceRouteHops();
    		String host = curHost.getHost();
    		String[] routeArray = routeHops.split("->");
    		List<String> routeList = Arrays.asList(routeArray);
    		hostTraceRmap.put(host, routeList);
//    		int i = 0;
    	}
    }
    
    /**@purpose Builds a list of one-to-one connections from the trace route Hops map
     * -enough data for the connector-ends output */
    public void buildConnectionsList(){
    	boolean first= true;
    	List<String> values;
    	String source = "";
    	String target = "";
    	Connection connection;
    	UniqueList uniqueConnections = new UniqueList();
	 
    	for (ReportHost host: hostList){
    		String hops = host.getTraceRouteHops();
    		String[] hopsList = hops.split("-> ");
//    		Iterator<String> itr = hopsList.iterator();
//    		System.out.println(host.getHost() +"           "+host.getIpAddress()+" hopsList.size(): " +hopsList.length);
    		int i = 0;
    		for (String hop : hopsList){
    			Connection conn = new Connection();
    			try{
    				//set source 
    				source = hopsList[i].trim();
    				conn.setSource(source);
    				if (uniqueNames.containsKey(source)){
    					conn.setSourceName(uniqueNames.get(source));
    				}else{
    					conn.setSourceName(getUniqueName(source));
    				}
    				//set target
    				target = hopsList[i+1].trim();
    				conn.setTarget(target);
    				if (uniqueNames.containsKey(target)){
    					conn.setTargetName(uniqueNames.get(target));
    				}else{
    					conn.setTargetName(getUniqueName(target));
    				}
    				uniqueConnections.add(conn);
//    				System.out.println("Source: " + source +"        Target: "+target);
    				i++;
    			}catch(ArrayIndexOutOfBoundsException e){
//    				System.out.println("index: " +i+"  ArrayIndexOutOfBoundsException: "+e.getMessage());
    				continue;
    			}catch(NullPointerException e){
//    				System.out.println("index: " +i+"  NullPointerException: "+e.getMessage());
    				continue;
    			}
    		}
    		Connection[] connArray = Arrays.copyOf(uniqueConnections.toObjectArray(), uniqueConnections.toObjectArray().length, Connection[].class);
    		connList = Arrays.asList(connArray);
    	}	
    }

    /**@purpose Builds a map from the list of connections that maps the source to a list of destinations
     * -specifically for the connected_elements column in the db data table */
    public void buildConnectedElementsMap(){
    	String source;
    	String target;
    	Connection connection;
    	boolean first = true;
    	List<String> destinations;
    	
    	Iterator<Connection> it = connList.iterator();
    	
    	while (it.hasNext()){
    		connection = it.next();
    		source = connection.getSource().toString();
    		target = connection.getTarget().toString();
    		
    		if (connectedElementsMap.containsKey(source)){
    			destinations = connectedElementsMap.get(source);
    			if (!(destinations.contains(target))&& (target.length()>1))
    				destinations.add(target);
    				connectedElementsMap.put(source,  destinations);
    		}else{
    			destinations = new ArrayList<String>();
    			if ((target.length()>1) && (source.length()>1)){
    				destinations.add(target);
    				connectedElementsMap.put(source,  destinations);
    			}
    		}		 
    	}
    }
    
    /**@purpose Goes through current hash map of hosts and their names and creates 
    * a new name for any ip not yet given a unique name 
    * @parm iP host IP address (String)
    * @return name of the host (String)*/
    public static String getUniqueName(String iP){
    	String ip= iP.trim();
    	String name;
    	ReportHost host;
    	if (!(uniqueNames.keySet().contains(ip))){
    		if (ipReportHost.containsKey(ip)){
    			host = ipReportHost.get(iP);
    		}else{
    			host = new ReportHost();
    			host.setIpAddress(ip);
    		}
    		setUniqueNames(host);
    	}else{
    		
    	}
    	name = uniqueNames.get(ip);
    	return name;
    }

    
    /**prints the contents of the collection mapping the source to its one or more targets to the console*/
    public void readConnectedElementsMap(){
   	 System.out.println("========== Connected Elements Map ==========");
    	List<String> values = new ArrayList<String>();
    	Iterator<String> it =connectedElementsMap.keySet().iterator();
    	for (String key: connectedElementsMap.keySet()){
    		StringBuffer connElements = new StringBuffer();
			String delimiter = "><";
    		values = connectedElementsMap.get(key);
    		connElements.append("Source: " + key + "      Target: ");
    		for (String value: values){
    			connElements.append(value);
    			connElements.append(delimiter);
             }	
    		String output = connElements.toString().substring(0,connElements.toString().length()-2);
    		System.out.println(output);
        }	
    }

    /**prints the contents of a map of all the hosts with a list of their trace routes to the console. */
    public void readHostTraceRmap() {
    	System.out.println("========== HostTraceRoute List ==========");
    	List<String> values = new ArrayList<String>();
    	Iterator<String> it =hostTraceRmap.keySet().iterator();
    	for (String key: hostTraceRmap.keySet()){
    		StringBuffer traceRoutes = new StringBuffer();
			String delimiter = "]      [";
    		values = hostTraceRmap.get(key);
    		traceRoutes.append("Host: " + key + " TraceRoute: ");
    		for (String value: values){
    			traceRoutes.append(value);
    			traceRoutes.append(delimiter);
             }	
    		String output = traceRoutes.toString().substring(0,traceRoutes.toString().length()-2);
    		System.out.println(output);
        }	
    }
   
    /**prints the given host names with their unique host name to the console. 
     * One was given if the host name was an IP address.*/  
    public void readuniqueNames(){
    	System.out.println("========== uniqueNames=========");
    	for (String host: uniqueNames.keySet())
    		System.out.println("Name: " + host + " Unique Name: " + uniqueNames.get(host));
    	System.out.println("Size of uniqueNames: " + uniqueNames.size());
    	
    }
    
    /**prints the contents of the list that will populate the connector-ends.csv to the console.*/
    public void readConnectorEndsList(){
    	Iterator<String> it = connectorEndsList.iterator();
    	while (it.hasNext()){
    		String row = it.next();
    		System.out.println(row);
    	}
    }
    
    public void readHostList(){
    	Iterator<ReportHost> it = hostList.iterator();
    	while(it.hasNext()){
    		ReportHost cur = it.next();
    		}
    }
    public void readConnList(){
    	System.out.println("============ConnList============");
    	Iterator<Connection> it = connList.iterator();
    	while(it.hasNext()){
    	Connection cur = it.next();
		System.out.println(cur.toString());
    	}
    }
    
//    public void readSoftware(){
//    	System.out.println("============Software============");
//    	Iterator<ReportHost> it = hostList.iterator();
//    	while(it.hasNext()){
//    		ReportHost host = it.next();
//    		System.out.println("Host: "+host.getIpAddress());
//    		List<String> softwareList = host.getInstalledSoftwareList();
//    		Iterator<String> it_soft = softwareList.iterator();
//    		while(it_soft.hasNext()){
//    			String software = it_soft.next();
//    			System.out.println(software);
//    		}
//    	}
//    }
   
	/**@return list of hosts. all inclusive if parsing a nessus file*/
	 public static ArrayList<ReportHost> getHostList() {
	        return hostList;
	   }
	 
	 /**@return list of all hosts to be reported on within an MBSE import spreadsheet.*/
	 public static ArrayList<ReportHost> getHosts(){
		ArrayList<ReportHost> hosts = new ArrayList<ReportHost>();
		switch(type){
		 
			case "nessus"		:		hosts = hostList;												break;
			case "lightning" 	:		hosts = new ArrayList<ReportHost>(ipReportHost.values());		break;
		 }
		 
		return hosts;
	 }
	 
	 public void setLightningHosts(HashMap<String, ReportHost> hostMap){
		 this.ipReportHost = hostMap;
	 }
	 
	 
	 public static ArrayList<ReportHost> getPortsInfo(){
		 ArrayList<ReportHost> ports = new ArrayList<ReportHost>();
			switch(type){
			 
				case "nessus"		:		ports = hostList;		break;
				case "lightning" 	:		ports = serverList;	
				
				
				break;
			 }
			 
			return ports;
	 }
	 
	 /**@return list of entries for the connector-ends list output csv*/
	 public static List<Connection> getConnectionsList() {
	        return connList;
	    }
	 
	 
	 /**@return Map of hosts with unique name  */
	 public HashMap<String, String> getUniqueHostList() {
	        return uniqueNames;
	    }
	 
	 /**@return Map of source with its targets */
	 public Map<String, List<String>> getConnectedElements(){
		 return connectedElementsMap;
	 }
	 
	 /**@return Map of software with all hosts it is installed on */
	 public HashMap<String, List<ReportHost>> getSoftwareHostsMap(){
		 return softwareHostsMap;
	 }
	 
	 /**@return type of file being parsed */
	 public String getType(){
		 return type;
	 }
	 
	 /**@return returns the site */
	 public String getSite(){
		 return site;
	 }
	
	 /**@return application version */
	 public String getVersion(){
		 return NESSUS_VERSION;
	 }

} // end parseNessus
