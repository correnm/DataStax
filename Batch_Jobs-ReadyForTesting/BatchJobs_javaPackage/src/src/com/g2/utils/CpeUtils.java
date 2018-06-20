package src.com.g2.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import src.com.g2.jobs.CpeDictionaryImport;


public class CpeUtils {

	private final static Logger slf4jLogger = LoggerFactory.getLogger(CpeUtils.class);
	private static final String CONTENT_URL="https://static.nvd.nist.gov/feeds/xml/cpe/dictionary/official-cpe-dictionary_v2.3.xml.gz"; //"http://static.nvd.nist.gov/feeds/xml/cpe/dictionary/official-cpe-dictionary_v2.3.xml"; 
	private static File destFile;
	private static boolean noDownload = false;
	
	public static boolean validFileLocation(String localFileLocation){
		destFile = new File(localFileLocation);
		if (destFile.exists()){
			return true;
		}else{
			slf4jLogger.error(destFile.getAbsolutePath()+ " (The system cannot find the file specified)");
			return false;
		}
	}

	/** checks if a copy of the official is already downloaded and if it is the most updated version. 
	 * If it does not exist or is not the most updated version, the method downloads the latest version of the cpe dictionary v2.3.
	 * @return the file location of the downloaded official cpe dictionary */
	public static boolean getDictionary(String localCopy) throws Exception{	

		String dictLocation = CONTENT_URL;		
		//check to see if input directory exists
		File folder = new File(localCopy);

		//gets the name of the dictionary from its absolute path
		String dictName = dictLocation.substring(dictLocation.lastIndexOf("/") + 1);
		destFile = new File(localCopy + File.separator + dictName);
		//checks the modification date of the NIST site where the cpe is located. 
		URL url = new URL(dictLocation);
		URLConnection conn = url.openConnection();
		try{
			conn.connect();
		}catch(UnknownHostException e){
			slf4jLogger.warn(e.toString());
		}
		long lmodifiedRemote = conn.getLastModified();		

		//checks the last modification of the last cpe dictionary download
		boolean needToDownload = checkModDates(destFile, lmodifiedRemote);
		
		if(needToDownload){
			// the file on the server is newer, download the update file
			download(conn);
		}
		destFile = uncompress(destFile);
		
		return needToDownload;
	}
	
	public static boolean checkModDates(File file, long lmodifiedRemote){
		boolean needToDownload = false;
		
		if(file.exists()){
			slf4jLogger.info(file.getAbsolutePath() + " exists, check modification time");
			long lmodifiedLocal = file.lastModified();
			
			//compares the modification times of the local file and the file on NIST site
			if(lmodifiedRemote > lmodifiedLocal){
				slf4jLogger.info("Server file is newer, need to download");
				needToDownload = true;
			}else{
				noDownload = true;
				needToDownload = false;
				slf4jLogger.info("Local version is newer, no need to download");
			}
		}else{
			slf4jLogger.info("Local version doesn't exist, need to download");
			needToDownload = true;
		}
		return needToDownload;
	}
	
	public static void download(URLConnection conn){
		// the file on the server is newer, download the update file
		try {
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buff = new byte[8192];
			int read = 0;
			
			while((read = is.read(buff)) > 0){
				fos.write(buff, 0, read);	
			}
			slf4jLogger.info("Downloaded");
			
			fos.flush();
			fos.close();
			is.close();	
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	
	public static DictionaryParser parseDictionary(){
		try {
			DictionaryParser parser = new DictionaryParser();
			parser.startParser(destFile);
			return parser;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**uncompresses the file obtained from the NIST website 
	 * @return uncompressed file */
	 public static File uncompress(File file) {
	        byte[] buffer = new byte[1024]; //sets the buffer capacity to 1024 bytes
	        GZIPInputStream gzis = null; //initialize variables
	        FileOutputStream out = null;
	        File newFile = new File(file.getAbsolutePath().replaceAll(".gz", ""));
	        try{
	        	slf4jLogger.info("Uncompressing " + file.getName()); // displays which file is being uncompressed
	            gzis = new GZIPInputStream(new FileInputStream(file)); // stream filter for reading compressed data in the GZIP file format
	            out = new FileOutputStream(newFile); // gets rid of .gz at the end of each url
	            int len; 
	            while ((len = gzis.read(buffer)) > 0) { //doesn't need to be 1024, could be anything
	                out.write(buffer, 0, len);  // writes out what is in the buffer from 0 to length
	            }
	        }catch(IOException ex){
	        	slf4jLogger.warn(ex.toString());
	            ex.printStackTrace();
	        } finally {
	            close(gzis); // runs the close function
	            close(out);
	        }
	        return newFile;
	 }
	 
	 /**defines the close function */ 
	 private static void close (Closeable object) { 
	        if (object != null) { //if there is an object, then we close the object
	            try {
	                object.close();
	            } catch (IOException e) { 
	            	slf4jLogger.warn(e.toString());
	                e.printStackTrace(); 
	            }
	        }
	    }

	/**parses the Strings for the reference links and reference descriptions and puts them into a map data type for the data table */
	public static Map<String, String> getRefMap(String descriptions, String links, String cpe22){

		Map<String, String> refMap = new HashMap<String,String>();
		String[] descriptArray = descriptions.split(",");
		String[] linksArray = links.split(",");
		int i = 0;
		try{
			for (String ref : descriptArray){
				String script = descriptArray[i];
				String link = linksArray[i];
				i++;
				refMap.put(script, link);
			}
		}catch(ArrayIndexOutOfBoundsException e){
			slf4jLogger.warn(e.toString());		
		}
		return refMap;
	}
	
	
	/**@param Results Set, name of column that is being searched
	 * @return string of multiple results */
	public static String getText(ResultSet rs, String col){
		List<Row> rows = rs.all();
		Iterator<Row> iterator = rows.iterator();
		String results = "<html>";
		//if there are no keys for the ip_address one is inserted
		if (rows.size()<1){
			return "No results found that match this criteria";
		}else{
			while(iterator.hasNext()){
				Row result = iterator.next();
				results = results + result.getString(col)+"<br>";
			}
			return results+"</html>";
		}
	}
}
