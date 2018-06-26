package src.com.g2.utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVWriter;

import src.com.g2.nessus.Warnings;
import src.com.g2.types.Connection;
import src.com.g2.types.ReportHost;
import src.com.g2.types.ReportItem;
import src.com.g2.types.Software_UDT;

public class WritetoCSV {
	//indicator for writing .csv files 
		private static Boolean importSpreadSheet = false;
		private static Boolean connectorEnds = false;
		private static Boolean hostPorts = false;
		private static Boolean hostVulns = false;

	 //writes connector-ends.csv, which includes all the source-target connections
    public static void writeConnectorEndsCsvFile(String saveTo, List<Connection> connectorsList, String site) throws FileNotFoundException {
    	//Delimiter used in CSV file
    	final String COMMA_DELIMITER = ",";
    	final String NEW_LINE_SEPARATOR = "\n";
    	List<String> connectorEndsList = addtoConnectorEndsList(connectorsList, site);
    	//CSV file header
    	final String[] FILE_HEADER = "source,source_name,block_name,target,target_name,owner,diagram".split(",");
		
		FileWriter fileWriter = null;
		CSVWriter writer = null;		
			
		try {
			fileWriter = new FileWriter(saveTo);
			writer = new CSVWriter(fileWriter, ',', '"');
			
			writer.writeNext(FILE_HEADER);
			if (!(connectorEndsList.size()>0))
				return;
			//Write a new report object list to the CSV file
	        Iterator<String> it = connectorEndsList.iterator();
	        String curConnection = new String();
	        while (it.hasNext()) {
	        	curConnection = it.next();
	        	String[] entries = curConnection.split("><");
	        	writer.writeNext(entries);
	        }
	        connectorEnds = true;
	        System.out.println("connectorEndsCSV file was created successfully.");
	        
		}catch (FileNotFoundException e){
			System.out.println("saveto: " + saveTo);
			Warnings.showWarning(saveTo);
			return;
		} catch (Exception e) {
			System.out.println("Error in connectorEndsCSVFile.");
			e.printStackTrace();
		} finally {
			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
			}catch (NullPointerException e){
				 e.printStackTrace();
			}
			 System.out.println("Save as file: " + saveTo);
			
		} // end of try-catch
	} // end method writeMagicDrawConnectorEnds
    
    public static List<String> addtoConnectorEndsList(List<Connection> connList, String site){
    	List<String> connectorEndsList = new ArrayList<String>();
    	List<String> values;
    	String source = new String();
    	String target;
    	String blockName;
    	String sourceName;
    	String targetName;
    	String owner = site;
    	String diagram = owner+"::"+"Diagram";
    	String delimiter = "><";
    	//int i = 0; //index for the connectionsList
    	Connection connection;

    	boolean first = true;
    	int index = 0;
    	
    	//create a collection that maps ip to the host name for a lookup for the ip's
    	Iterator<Connection> it = connList.iterator();
    	while (it.hasNext()){
    		connection= it.next();
    		source = connection.getSource().toString();
    		target = connection.getTarget().toString();
    		
    		if (((source.contains("[0-9a-zA-Z]"))&&(target.contains("[0-9a-zA-Z]")))){
    			continue;
    		}

    		StringBuffer connectorInfo = new StringBuffer();	
    		
    		blockName = connection.getSourceName();	
    		sourceName = owner+"::"+blockName;
    		targetName = owner+"::"+ connection.getTargetName();
    		
    		connectorInfo.append(source.trim());
    		connectorInfo.append(delimiter);
    		connectorInfo.append(sourceName.trim());
    		connectorInfo.append(delimiter);
    		connectorInfo.append(blockName.trim());
    		connectorInfo.append(delimiter);
    		connectorInfo.append(target.trim());
    		connectorInfo.append(delimiter);
    		connectorInfo.append(targetName.trim());
    		connectorInfo.append(delimiter);
    		connectorInfo.append(owner.trim());
    		connectorInfo.append(delimiter);
    		connectorInfo.append(diagram.trim());
    			
    		connectorEndsList.add(connectorInfo.toString());

    		source = target;
    	}
    	return connectorEndsList;
    }
    
    //writes host-ports.csv, which includes all the protocols for all the ports for all the hosts
    public static void writeMagicDrawHostPorts(String saveTo, ArrayList<ReportHost> hostList) throws FileNotFoundException {
    	//Delimiter used in CSV file
    	final String COMMA_DELIMITER = ",";
    	final String NEW_LINE_SEPARATOR = "\n";
    	String portsEntry = new String();
    	
    	//CSV file header
    	final String[] FILE_HEADER = "host,ports,service_name,protocol".split(",");
		
		FileWriter fileWriter = null;
		CSVWriter writer = null;		
		if (!(hostList.size()>0))
			return;
		
		try {
			fileWriter = new FileWriter(saveTo);
			writer = new CSVWriter(fileWriter, ',', '"');
			writer.writeNext(FILE_HEADER);
			
			//Write a new report object list to the CSV file
			Iterator<ReportHost> it = hostList.iterator();
			ReportHost curHost = new ReportHost();
			while(it.hasNext()){
				curHost = it.next();
				List<String> hostPortsInfo = curHost.getHostPortsInfo();
				//returns a unique list of ports,services, and protocols for each host.
				Iterator<String> it2 = hostPortsInfo.iterator();
				while(it2.hasNext()){
					String curInfo = it2.next();
					String host = curHost.getHost();
					String row = curHost.getHost()+curInfo;
					String[] rowEntry = row.split("><");
					writer.writeNext(rowEntry);
				}
			}

		    hostPorts = true;
		    System.out.println("ports CSV file was created successfully.");
		}catch (FileNotFoundException e){
			Warnings.showWarning(saveTo);
			return;
		} catch (Exception e) {
			System.out.println("Error in writeportsCSVFile.");
			e.printStackTrace();
		} finally {
			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			 System.out.println("Save as file: " + saveTo);		
		} // end of try-catch
	} // end method writeMagicDrawHostPorts
    
    
    //writes host-vulnerabilities.csv, which includes all vulnerabilities on each of the hosts, gathered by each of the report items
    public static void writeHostVulnerabilities(String saveTo, ArrayList<ReportHost> hostList) throws FileNotFoundException {
    	//Delimiter used in CSV file
    	final String COMMA_DELIMITER = ",";
    	final String NEW_LINE_SEPARATOR = "\n";
    	String portsEntry = new String();
    	
    	//CSV file header
    	final String[] FILE_HEADER = "host,cvss_base_score,cvss_temporal_score".split(",");
		
		FileWriter fileWriter = null;
		CSVWriter writer = null;		
		if (!(hostList.size()>0))
			return;
		
		try {
			fileWriter = new FileWriter(saveTo);
			writer = new CSVWriter(fileWriter, ',', '"');
			writer.writeNext(FILE_HEADER);
			
			//Write a new report object list to the CSV file
			Iterator<ReportHost> it = hostList.iterator();
			ReportHost curHost = new ReportHost();
			while(it.hasNext()){
				curHost = it.next();
				List<ReportItem> items = curHost.getReportItems();
//				System.out.println("Items with vulnerabilities: " + items.size());
				if (!(items.size()>0)){
					return;
				}else{
					Iterator<ReportItem> itr = items.iterator();
					while(itr.hasNext()){
						ReportItem item = itr.next();
						if(item.getBaseScore() == 0 && item.getTempScore() ==0)
							continue;
						String row = curHost.getHost()+"><"+item.getItemVuln();
//						System.out.println(row);
						String[] rowEntry = row.split("><");
						writer.writeNext(rowEntry);
					}
				}
			}
		    hostVulns = true;
		    System.out.println("vulnerabilities CSV file was created successfully.");
		}catch (FileNotFoundException e){
			Warnings.showWarning(saveTo);
			return;
		} catch (Exception e) {
			System.out.println("Error in writeVulnsCSVFile.");
			e.printStackTrace();
		} finally {
			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			 System.out.println("Save as file: " + saveTo);		
		} // end of try-catch
	} // end method writeMagicDrawHostPorts
    
    
    /**Creates a csv of all Report Items that do not have a cveID*/
    public static void writeNoCveIds(String saveTo, ArrayList<ReportHost> hostList) throws FileNotFoundException {

    	//CSV file header
    	final String[] FILE_HEADER = "host,ip_address,port,srv_name,protocol,severity,plugin_id,plugin_name,plugin_family,cve_id,cvss_base_score,cvss_vector,description,fname,plugin_modification_date,plugin_publication_date,plugin_type,risk_factor,script_version,solution,synopsis".split(",");
		int vulnCount = 0;
		int hostwVuln = 0;
		int hostwVulnNoCve = 0;
		boolean hostVulnNoCve;
		boolean hostVuln;
		FileWriter fileWriter = null;
		CSVWriter writer = null;		
		if (!(hostList.size()>0))
			return;
		
		try {
			fileWriter = new FileWriter(saveTo);
			writer = new CSVWriter(fileWriter, ',', '"');
			writer.writeNext(FILE_HEADER);
			
			//Write a new report object list to the CSV file
			Iterator<ReportHost> it = hostList.iterator();
			ReportHost curHost = new ReportHost();
			while(it.hasNext()){
				hostVulnNoCve = false;
				hostVuln = false;
				curHost = it.next();
				List<ReportItem> reportItems = curHost.getReportItems();
				Iterator<ReportItem> it2 = reportItems.iterator();
				while(it2.hasNext()){
					ReportItem item = it2.next();
					if (item.getCveId()== null && item.getBaseScore()>0){
						String info = curHost.getHost() + "><"+ curHost.getIpAddress() + "><"+ item.getNoCveInfo();
						String[] row = info.split("><");
						writer.writeNext(row);
						vulnCount++;
						hostVulnNoCve = true;
						hostVuln = true;
					}else if (item.getCveId()!= null){
						vulnCount++;
						hostVuln = true;
						continue;
					}else{
						continue;
					}
				}
				if (hostVuln)
					hostwVuln++;
				if (hostVulnNoCve)
					hostwVulnNoCve++;
			}
		    System.out.println("No Cve Id's written successfully.");
		    System.out.println("Number of Vulnerabilities: " + vulnCount);
		    System.out.println("Number of Hosts with vulnerabilities: " + hostwVuln);
		    System.out.println("Number of Hosts with vulneranilities with out a CVE-ID: "+ hostwVulnNoCve);
		}catch (FileNotFoundException e){
			Warnings.showWarning(saveTo);
			return;
		} catch (Exception e) {
			System.out.println("Error in NoCveInfo.");
			e.printStackTrace();
		} finally {
			
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter");
                e.printStackTrace();
			}catch (NullPointerException e){
				e.printStackTrace();
			}
			 System.out.println("Save as file: " + saveTo);		
		} // end of try-catch
	} // end method writeMagicDrawHostPorts
    
    //writes importSpreadsheet.csv, which includes all the general information collected from the parsed files
    //needs renovation
    public static void writeMagicDrawCsvFile(String saveTo, ArrayList<ReportHost> hostList) throws FileNotFoundException {
        	//Delimiter used in CSV file
        	final String COMMA_DELIMITER = ",";
        	final String NEW_LINE_SEPARATOR = "\n";
        	String columns = "host,vendor,mac_address,qualified_name,ip_address,o_s,operating_system,system_type,fqdn,scan_date,"
        			+ "installed_software,traceroute_hops,cvss_base_score,cvss_temporal_score,cpe_part,cpe_vendor,cpe_product,"
        			+ "cpe_version,cpe_update,cpe_edition,cpe_language,cpe_sw_edition,cpe_target_sw,cpe_target_hw,cpe_other";
        	//CSV file header
        	//need to add: part,vendor,product,version,update,edition,language,sw_edition,target_sw,target_hw,other
        	final String[] FILE_HEADER = columns.split(",");
    		
    		FileWriter fileWriter = null;
    		CSVWriter writer = null;		
    		try {
    			fileWriter = new FileWriter(saveTo);
    		
    			writer = new CSVWriter(fileWriter, ',', '"');
    			writer.writeNext(FILE_HEADER);
    			
    			if (!(hostList.size()>0))
        			return;
    			
    			//Write a new report object list to the CSV file
    	        Iterator<ReportHost> it = hostList.iterator();
    	        ReportHost curHost = new ReportHost();
    	        while (it.hasNext()) {
    	        	curHost = it.next();
    	        	List<Software_UDT> cpeList = curHost.getInstalledSoftwareList();
    	        	if(cpeList.size()>0){
//    	        		System.out.println("InstalledSoftware size: " + cpeList.size());
    	        		Iterator<Software_UDT> itr = cpeList.iterator();
    	        		int i = 0;
    	        		while(itr.hasNext()){
    	        			String row = curHost.toOutputString(i);
    	        			if (row == "EXIT")
    	        				break;
    	        			String[] entriesWcpes = row.split("><");
    	        			writer.writeNext(entriesWcpes);
    	        			i++;
    	        		}
    	        	}else{
    	        		String[] entries = curHost.toString().split("><");
        	        	writer.writeNext(entries);
    	        	}
    			}
    	        importSpreadSheet = true;
    	        System.out.println("CSV file was created successfully.");
    		}catch (FileNotFoundException e){
    			Warnings.showWarning(saveTo);
    			return;
    		} catch (Exception e) {
    			System.out.println("Error in writeMagicDrawCSVFile.");
    			e.printStackTrace();
    		}finally {
    			try {
    				writer.flush();
    				writer.close();
    			} catch (IOException e) {
    				System.out.println("Error while flushing/closing fileWriter");
                    e.printStackTrace();
    			}catch (NullPointerException e){
    				e.printStackTrace();
    			}
    			 System.out.println("Save as file: " + saveTo);	
    		} // end of try-catch
    	} // end method writeMagicDrawCSVFile
    
	 /**@return true/false if importSpreadSheet saved successfully */
	 public static boolean getIsSaveStatus(){
		 return importSpreadSheet;
	 }
	 
	 /**@return true/false if connector-ends saved successfully */
	 public static boolean getCeSaveStatus(){
		 return connectorEnds;
	 }
	 
	 /**@return true/false if host-ports saved successfully */
	 public static boolean getHpSaveStatus(){
		 return hostPorts;
	 }
	 /**@return true/false if host-vulnerabilities saved successfully */
	 public static boolean getHvSaveStatus(){
		 return hostVulns;
	 }
}
