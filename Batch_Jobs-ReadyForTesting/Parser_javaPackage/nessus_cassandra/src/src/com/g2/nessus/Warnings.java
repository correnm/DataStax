package src.com.g2.nessus;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

/**
 * @author Sara Bergman
 * @purpose All the warnings/ informative pop-ups to inform the user of application functions
 * @date 05-Jan-2018
 *  */
public class Warnings {
	 /**A warning pop-up
	  * @purpose informs the user that no data was found in their uploaded file(s)*/
	 public static void noFile(){
		 JOptionPane noNessus = new JOptionPane();
		 JOptionPane.showMessageDialog(noNessus, "<html>No Data has been found.<br>Try re-uploading your *.nessus or *.xml file(s) or folders containg those files.</html>", "NO DATA", JOptionPane.WARNING_MESSAGE);
	 }
	 
	 /**A warning pop-up 
	  * @purpose informs the user that the output file is unable to be produced
	  * @param filePath to attempted write location */
	 public static void showWarning(String filePath){
		JOptionPane fileNotFound = new JOptionPane();
		JOptionPane.showMessageDialog(fileNotFound,
			     "An output file cannot be saved because it is either currently being used by another process or the file path localtion does not enable 'WRITE' permissions. \nAttempted file access: " + filePath,
			    "CANNOT ACCESS FILE",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	 /**A warning pop-up
	  * @purpose informs users that a specific file as not been found
	  * @param fileName of specific file attempted to be used for parsing
	  * @param addMessage: message suggesting how to rectify lost or missing file */
	public static void showFileNotFound(String fileName, String addMessage){
		JOptionPane fileNotFound = new JOptionPane();
		JOptionPane.showMessageDialog(fileNotFound,
			    "<html><h3>Parser cannot find " + fileName+"</h3></html> \nPlease upload "+ fileName+" to the \"parser_resources\" folder located in the same file location as this Parser executable." + addMessage,
			    "CANNOT FIND " + fileName.toUpperCase(),
			    JOptionPane.WARNING_MESSAGE);
	}
	
	/** Informative pop-up
	 * @purpose to inform the user that all csv output files have been created to specified directory
	 * @parm directory of saved csv output files*/
	 public static void showSaveConfirmation(String type, String directory, boolean importSpreadSheet, boolean connectorEnds, boolean hostPorts, boolean hostVulns){
		 JOptionPane confirm = new JOptionPane();
//		 String ce = "connector-ends.csv";
//		 String is = "importSpreadsheet.csv";
//		 String hp = "host-ports.csv";
		 String hv = "host-vulnerabilities.csv";
		 String filesSaved = new String();
		 if (type == "nessus"){
			 filesSaved = "Saved importSpreadsheet.csv, connector-ends.csv, host-ports.csv, and host-vulnerabilities.csv";
		 }else if (type == "lightning"){
			 filesSaved = "Saved importSpreadsheet.csv, connector-ends.csv, and host-ports.csv";
		 }
		 if (importSpreadSheet && connectorEnds && hostPorts && hostVulns){
			 JOptionPane.showMessageDialog(confirm, "<html>"+filesSaved+" to: <br>" + directory+"</html>");
		 }else if(!importSpreadSheet && !connectorEnds && !hostPorts && !hostVulns){
			 noFile();
		 }
	 }
	 
	 /** A warning pop-up
	  * @purpose to inform the user that the file(s) selected is/are non-compatable*/
	 public static void showNonCompatibleFile(){
		 JFrame warn = new JFrame();
		 JOptionPane.showMessageDialog(warn,"<html> File(s) selected is not compatible. <br>Compatable files include *.nessus files or folders containing *.nessus files. </html>" , 
					"Error Found", JOptionPane.WARNING_MESSAGE);
	 }
	 /** A warning pop-up
	  * @purpose to inform the user that he/she does not have access to the database*/
	 public static void showAuthenticationError(){
		 JFrame warn = new JFrame();
		 JOptionPane.showMessageDialog(warn, "There seems to be an authentication error. \nBe sure to enter the correct keyspace, username, and password. ",
				 "AUTHENTICATION ERROR", JOptionPane.WARNING_MESSAGE);
	 }
}
