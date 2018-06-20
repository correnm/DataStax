package src.com.g2.jobs;
/**
 * Sara Bergman G2 Ops Inc, Virginia Beach
 * 
 * @purpose The purpose of this class is to download a copy of the most updated version of the cpe dictionary and save it to a local directory. 
 * 
 * refernces:
 * G2, Inc. https://github.com/vmware/vmware-scap-edit/blob/master/eSCAPeLib/src/main/java/com/g2inc/scap/util/OfficalCPEDictionaryGrabberTask.java
 * 
 * Date			Author			Description
 * 6-Nov-2017	sara.bergman	Created 
 * */



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import src.com.g2.utils.CpeUtils;
import src.com.g2.utils.DictionaryParser;
import src.com.g2.utils.TableData;



public class CpeDictionaryImport {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(CpeDictionaryImport.class);

	
	/**
	 * Attempt to grab the latest offical CPE dictionary file and store it locally
	 * @param args - the absolute path to a file to be parsed as the cpe dictionary
	 */
	
	public static void main(String args[]) throws NullPointerException{
		try{
			String localFileLocation = args[0];
			if (!CpeUtils.validFileLocation(localFileLocation))
				return;
			
			DictionaryParser parser = new DictionaryParser();
			try {
				boolean updateDb = CpeUtils.getDictionary(localFileLocation);
				if (updateDb){
						parser = CpeUtils.parseDictionary();
						TableData.importDictionary(parser);
				}
				slf4jLogger.info("Size of CpeList: " + parser.getCpeList().size());
			} catch (Exception e1) {
				slf4jLogger.error(e1.toString());
				e1.printStackTrace();
			}
		}catch(NullPointerException e){
			slf4jLogger.error("Specify the local file location to store/parse the offical cpe ditionary");
			slf4jLogger.error(e.toString());
			return;
		}catch(ArrayIndexOutOfBoundsException e1){
			slf4jLogger.error("Specify the local file location to store/parse the offical cpe ditionary");
			slf4jLogger.error(e1.toString());
			return;
		}
	}
	
}
