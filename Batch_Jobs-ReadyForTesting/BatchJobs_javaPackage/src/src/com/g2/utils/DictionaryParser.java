package src.com.g2.utils;

/**
* 
* @author Sara Bergman, G2 East, Virginia Beach, VA
* @purpose This class parses the Official CPE Dictionary xml file version 2.3 into desired 
* attributes used for product data storage
* 
* 
* Advantage of SAX parser in Java:
* It is faster than DOM parser because it will not load the XML document into memory.
* It's an event based handler.
*
* Dependencies:
* http://opencsv.sourceforge.net/				-- simple csv parser library for Java
* https://sourceforge.net/projects/opencsv/	-- opencsv-3.8.jar
* 
* Interface:	
* -- Official CPE Dictionary v2.3 file download directly from NIST NVD and saved to local file 
* directory. Will need to be refreshed daily to get latest entries. Dictionary is updated nightly when 
* modifications or new names are added.
* 
* File References
* http://static.nvd.nist.gov/feeds/xml/cpe/dictionary/official-cpe-dictionary_v2.3.xml.gz
* 
* Coding References:
* http://javarevisited.blogspot.com/2011/12/parse-read-xml-file-java-sax-parser.html
* https://www.tutorialspoint.com/java_xml/java_sax_parse_document.htm
* http://www.journaldev.com/1198/java-sax-parser-example
* https://sourceforge.net/projects/opencsv/?source=typ_redirect
* http://www.mkyong.com/java/how-to-read-utf-8-xml-file-in-java-sax-parser/
* 
* Modification History:
* Date				Author			Description
* 25-Oct-2017		sara.bergman	Extracts the title and cpe's (versions 2.2 and 2.3) of each cpe in dictionary
* 
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import src.com.g2.types.DictEntry;



public class DictionaryParser extends DefaultHandler {
/**class variables*/
	
	//the attribute currently being evaluated by the parser
	private final static Logger slf4jLogger = LoggerFactory.getLogger(DictionaryParser.class);
	private String temp;
	private static src.com.g2.types.DictEntry cpe;
	//allows the file name to be passed to Window.java and displayed above the data table
	private static File parseFile; 
	
	//a list of all the cpe's 
	private static ArrayList<DictEntry> cpeList  = new ArrayList<DictEntry>();
	
	//indicator for attributes 
	Boolean bTitle = false;
	Boolean bCpe23 = false; 
	Boolean bRef = false;
	
	/**begins to parse the file */
	public void startParser(File inputFile) throws IOException, ParserConfigurationException,
	SAXException {
		Scanner keyboard = new Scanner(System.in);
		 String statusMessage = null;
		
		try {
			// read all the files in the import directory into an array
			statusMessage = "Parser completed successfully.";
			parseFile = inputFile;

				// Read: http://javarevisited.blogspot.com/2011/12/parse-read-xml-file-java-sax-parser.html
	            //Create a "parser factory" for creating SAX parsers
	            SAXParserFactory spfac = SAXParserFactory.newInstance();
	
	            //Now use the parser factory to create a SAXParser object
	            SAXParser sp = spfac.newSAXParser();
	
	            //Create an instance of this class; it defines all the handler methods
	            DictionaryParser handler = new DictionaryParser();
	
	            //Finally, tell the parser to parse the input and notify the handler
	            try{
	            	//added this in case the parser encounters foreign characters. 
	            	//Read: http://www.mkyong.com/java/how-to-read-utf-8-xml-file-in-java-sax-parser/
	            	InputStream inputStream = new FileInputStream(inputFile);
	            	Reader reader = new InputStreamReader(inputStream, "UTF-8");
	            	InputSource is = new InputSource(reader);
	            	sp.parse(is,handler);
//	            sp.parse(inputFile, handler);
	            if (cpeList.size()<1){
	            	slf4jLogger.warn("The dictionary seems to be empty. Confirm that you are processing the correct file.");
	            }
	            
//	            handler.readMe();
	            }catch (SAXParseException e){
	            	slf4jLogger.error(e.toString());
	            	statusMessage = "Parser Error";
	            	return;
	            } catch(FileNotFoundException e ){
	            	slf4jLogger.error(e.toString());
	            	statusMessage = "Parser Error";
	            }

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			slf4jLogger.info("********* Parser Status **********");
			slf4jLogger.info(statusMessage);
		}
    	// close all open resources to avoid compiler warning
	    if (keyboard != null)
	    	keyboard.close();
	} // end startParser
	
	//identifying elements within the xml file. 
	/** Every time the parser encounters the beginning of a new element,
     * it calls this method, which resets the string buffer*/ 
    public void startElement(String uri, String localName,
                  String qName, Attributes attributes) throws SAXException {
    	//System.out.println("qName: " + qName); -- wanted to see what the options for qName were
           temp = "";
           if (qName.equalsIgnoreCase("cpe-item")) {
        	   cpe = new DictEntry();
               cpe.setEntry(attributes.getValue("name"));
           } else if (qName.equalsIgnoreCase("reference")){
        	   cpe.setRefLink(attributes.getValue("href"));
        	   bRef = true;
        	   
           } else if (qName.equalsIgnoreCase("title")) {
        	   bTitle = true;
           }else if (qName.equalsIgnoreCase("cpe-23:cpe23-item")){
        	   cpe.setCpe23(attributes.getValue("name"));
           }
    } // end StartElement
	
    /** When the parser encounters plain text (not XML elements),
     * it calls this method which accumulates them in a string buffer*/
    public void characters(char[] buffer, int start, int length) {
    	// remove leading and trailing whitespace
    	temp = new String(buffer, start, length).trim();
    	
        if(temp.length() == 0) return; // ignore white space
    }
   
    /** When the parser encounters the end of an element, it calls this method*/
    public void endElement(String uri, String localName, String qName)
                  throws SAXException {

           if (qName.equalsIgnoreCase("cpe-Item")) {
        	   // add new host to the list
               cpeList.add(cpe);
               
           } else if (bTitle) { 
               cpe.setTitle(temp);
               bTitle = false;
           } else if (bRef){
        	   cpe.setRefDescription(temp);
        	   bRef = false;
           }
           
    } //end endElement
    
    /**testing for valid info */
    public void readMe(){
    	for (DictEntry cpe : cpeList){
    		System.out.println(cpe.toString());
    	}
    }
    
    /**"get" methods to make parsed collections accessible*/
    public ArrayList<DictEntry> getCpeList(){
    	return cpeList;
    }
	 
	 public static File getParseFile(){
		 return parseFile;
	 }
}
