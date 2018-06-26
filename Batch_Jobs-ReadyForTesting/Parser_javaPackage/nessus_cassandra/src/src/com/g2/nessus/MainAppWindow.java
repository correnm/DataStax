 package src.com.g2.nessus;

/**
 * @author Corren McCoy and Sara Bergman G2 Ops, Virginia Beach, VA
 * 
 * @purpose This class creates the graphic user interface for the parser. 
 * The user is able to upload nessus files, parse the information, display select information 
 * on the interface, save output csv files from Magic Draw, as well as save information to 
 * a common database. 
 * 
 * Modification History
 * June 2017		sara.prokop		enabled parsing and saving capabilities
 * 01-Jan-2018		sara.bergman	introduced a GUI interface for the database connection
 */

import src.com.g2.nessus.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuKeyListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ProgressBarUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONObject;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.opencsv.CSVReader;



import javax.swing.event.MenuKeyEvent;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import src.com.g2.types.ReportHost;
import src.com.g2.utils.Batches;
import src.com.g2.utils.UpdateCassandra;
import src.com.g2.utils.WritetoCSV;


public class MainAppWindow extends JFrame {
Container window = getContentPane();
	public static String magicDrawImportSpreadsheet = "importSpreadsheet.csv"; //public static String magicDrawImportSpreadsheet = "./export/importSpreadsheet.csv";
	public static String magicDrawConnectorEnds = "connector-ends.csv";//public static String magicDrawConnectorEnds = "./export/connector-ends.csv";
	public static String magicDrawSrvProPorts = "host-ports.csv";
	public static String magicDrawHostVulns = "host-vulnerabilities.csv";
	public static String USER_GUIDE = "parser_resources" +File.separatorChar + "Scan_Parser_User_Guide.pdf";
	private Parser parser;
//	Batches rand = new Batches();

	/**
	 * @purpose Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppWindow frame = new MainAppWindow();
					// center the initial JFrame on the screen
					frame.setLocationRelativeTo(null);
					makeFrameHalfSize(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**Determines the size of the application */ 
	private static void makeFrameHalfSize(JFrame aFrame){
	  // determine the current screen size, then sets the size of the given JFrame with the setSize method.
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  aFrame.setSize(screenSize.width/2, screenSize.height/2);
	}
	
	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainAppWindow()  { 		
		setName("mainApplicationFrame");
		setTitle("G2 Ops: Scan Parser");
		
		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setBounds(100, 100, 758, 673);

		createMenuBar();
	}
	
	/**Creates the top menu bar */
	@SuppressWarnings("deprecation")
	public void createMenuBar(){
		// Define the application menu options
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
					
		//File
		JMenu mnFile = new JMenu("File");
		// Set the menu shortcut to use with Alt-
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);
			
		
		//FILE>Open
		JMenu mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);
		
		//FILE>Open>Open Nessus
		//opens a dialog box for the user to select one or more nessus files to parse.
		JMenuItem mntmOpenN = new JMenuItem("Open Nessus");
		mntmOpenN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openFiles("nessus");
				} catch (SAXParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmOpenN.setToolTipText("Select the input Nessus file(s)");
		mntmOpenN.setMnemonic(KeyEvent.VK_O);
		mnOpen.add(mntmOpenN);
		
		//FILE>Open>Open Lightening
		JMenuItem mntmOpenL = new JMenuItem("Open Lightning");
		mntmOpenL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openFiles("lightning");
				} catch (SAXParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mntmOpenL.setToolTipText("Select the input Raytheon Lightning file(s)");
		mntmOpenL.setMnemonic(KeyEvent.VK_O);
		mnOpen.add(mntmOpenL); 
		
		
		//FILE>SAVE RESULTS
		JMenu mnSaveResults = new JMenu("Save Results");
		mnFile.add(mnSaveResults);
		
		//FILE>SAVE RESULTS>MBSE CSV IMPORT
		//Opens a dialog box for the user to select a directory to save 3 CSV files designed for import into magic draw.
		//1. Import Spreadsheet, which includes all information that is being parsed out of the file
		//2. Host-Ports, which includes a list of all hosts, its ports, and protocols
		//3. Connector-Ends, list all the sources and target connecting points
		//4. Host-Vulnerabilities, lists all the known ports on specific hosts
		JMenuItem mntmMbseCsvImport = new JMenuItem("MBSE CSV Import");
		mntmMbseCsvImport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					saveFile();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane fileNotFound = new JOptionPane();
					JOptionPane.showMessageDialog(fileNotFound,
						    "An Output spreadsheet cannot be accessed because it is currently being used by another process.",
						    "CANNOT ACCESS FILE" + magicDrawSrvProPorts,
						    JOptionPane.WARNING_MESSAGE);
				}//saving output spreadsheets
			}
		});
		mntmMbseCsvImport.setToolTipText("Select the output directory");
		mnSaveResults.add(mntmMbseCsvImport);
		
		//FILE>SAVE RESULTS>CASSANDRA DATABASE
		//saves the parsed info into the database
		JMenuItem mntmCassandraDatabase = new JMenuItem("Cassandra Database");
		mntmCassandraDatabase.setToolTipText("Select the output database");
		mntmCassandraDatabase.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				UpdateCassandra cassandra = new UpdateCassandra(nessus);
//				showDbWindow();
				
				UpdateCassandra cassandra = new UpdateCassandra();
				cassandra.save(parser);
				
//				cassandra.collectHwTable();
			}
		});
		mntmCassandraDatabase.setEnabled(false);
		mnSaveResults.add(mntmCassandraDatabase);
		
		//TOOLS
		JMenu mnTools = new JMenu("Tools");
		// Set the menu shortcut to use with Alt-
		mnTools.setMnemonic(KeyEvent.VK_T);
		menuBar.add(mnTools);
		
		//TOOLS>UPDATE MAC ADDRESSES
		//Instructions to update the oui.csv, used to identify vendors by mac address
		JMenuItem mntmParserData = new JMenuItem("Update MAC Address Data");
		mntmParserData.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				selectDataFile();
			}
		});
		mntmParserData.setToolTipText("Upload a new oui.csv for MAC Address look-up");
		mntmParserData.setMnemonic(KeyEvent.VK_O);
		mnTools.add(mntmParserData);
		
		//TOOLS>NO CVE ID VULNERABILITIES
		JMenuItem mntmNoCveId = new JMenuItem("No CVE Id");
		mntmNoCveId.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				JFileChooser saveFile = new JFileChooser();
				//Tells the user that the files will be saved as a .csv
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", ".csv");
				saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				saveFile. setAcceptAllFileFilterUsed(false);
				saveFile.setFileFilter(filter);
				
				int userSelection = saveFile.showOpenDialog(null);
				if (userSelection ==JFileChooser.APPROVE_OPTION){
					File folder = saveFile.getSelectedFile();
					String saveToDirectory = folder.getAbsolutePath();
					String delimiter = File.separator;
					String filePath = saveToDirectory + delimiter+ "No_CVE_IDs.csv";
				
				try {
					WritetoCSV.writeNoCveIds(filePath, parser.getHosts());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		mntmNoCveId.setToolTipText("create an export of vulnerabilities that do not contain CVE Id's");
		mntmNoCveId.setMnemonic(KeyEvent.VK_O);
//		mnTools.add(mntmNoCveId);
		mntmNoCveId.setEnabled(false);
		
		//TOOLS>INSERT HARDWARE_ARCHIVE DATA
		JMenuItem mntmInsert = new JMenuItem("Insert Archived Data");
		mntmInsert.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				rand = new Batches();
//				rand.insertRandomizedData("dod", 730); //730 is 2 years. Day 0: 2018-03-06 -> 2016-03-06 // dod 3-20
			}
		});
		mntmInsert.setToolTipText("generate 2 years worth of data for the hardware_archive table");
//		mnTools.add(mntmInsert); //This is commented so that it does not show in the executable jar 
		mntmInsert.setEnabled(false);
		
		//TOOLS>ARCHIVE TODAY'S DATA
		JMenuItem mntmArchiveToday = new JMenuItem("Archive Today's Data");
		mntmArchiveToday.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				rand.insertHardwareIntoArchive("vmasc"); // does not randomize data before archiving
//				rand.insertRandomizedData("vmasc", 3);//creates another day of pho data. 
			}
		});
		mntmArchiveToday.setToolTipText("Archive randomized data of the current hardware table");
//		mnTools.add(mntmArchiveToday); //This is commented so that it does not show in the executable jar 
		mntmArchiveToday.setEnabled(false);
		
		//TOOLS>DAILY ROLLUP FROM HARDWARE_ARCHIVE
		JMenuItem mntmRollUpDaily_arch = new JMenuItem("Rollup Daily (hardware_archive)");
		mntmRollUpDaily_arch.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				rand.rollupHardwareDaily_fromHardwareArchive("dod");
			}
		});
		mntmRollUpDaily_arch.setToolTipText("Rollup data from hardware_archive into hardware_daily_summary");
//		mnTools.add(mntmRollUpDaily_arch); //This is commented so that it does not show in the executable jar 
		mntmRollUpDaily_arch.setEnabled(false);
		
		//TOOLS>WEEKLY ROLLUP
		JMenuItem mntmRollUpWeekly = new JMenuItem("Rollup Weekly");
		mntmRollUpWeekly.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				rand.rollupHardwareWeekly("dod");
			}
		});
		mntmRollUpWeekly.setToolTipText("Rollup data from hardware_dialy_summary into hardware_weekly_summary");
//		mnTools.add(mntmRollUpWeekly); //This is commented so that it does not show in the executable jar 
		mntmRollUpWeekly.setEnabled(false);
		
		//TOOLS>MONTHLY ROLLUP
		JMenuItem mntmRollUpMonthly = new JMenuItem("Rollup Monthly");
		mntmRollUpMonthly.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				rand.rollupHardwareMonthly("dod");
			}
		});
		mntmRollUpMonthly.setToolTipText("Rollup data from hardware_weekly_summary into hardware_monthly_summary");
//		mnTools.add(mntmRollUpMonthly); //This is commented so that it does not show in the executable jar 
		mntmRollUpMonthly.setEnabled(false);
		
		//TOOLS>TEST COLLECT HARDWARE
		//Instructions to update the oui.csv, used to identify vendors by mac address
		JMenuItem mntmRandomize = new JMenuItem("TEST Collect Hardware");
		mntmRandomize.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
//				rand.collectHwTable();
			}
		});
		mntmRandomize.setToolTipText("gathers all records from hardware table into a list");
//		mnTools.add(mntmRandomize); //This is commented so that it does not show in the executable jar 
		mntmRandomize.setEnabled(false);
		
		
		//TOOLS>UPDATE CVSS SCORES AND TABLE
		//Instructions to update the oui.csv, used to identify vendors by mac address
		JMenuItem mntmCVSSUpdate = new JMenuItem("Update CVSS Scores");
		mntmCVSSUpdate.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				UpdateCassandra update = new UpdateCassandra();
				update.save(parser);
			}
		});
		mntmCVSSUpdate.setToolTipText("SET iiv_score to 0 and set only scores with a base score");
//		mnTools.add(mntmCVSSUpdate); //This is commented so that it does not show in the executable jar 
		mntmCVSSUpdate.setEnabled(false); 
		
		//TOOLS>UPDATE SOFTWARE
		JMenuItem mntmSoftware = new JMenuItem("Software Inserts");
		mntmSoftware.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Batches update = new Batches();
				update.updateSoftware("dod");
			}
		});
		mntmSoftware.setToolTipText("SET iiv_score to 0 and set only scores with a base score");
//		mnTools.add(mntmSoftware); //This is commented so that it does not show in the executable jar 
		mntmSoftware.setEnabled(false); 
		
		//HELP
		JMenu mnHelp = new JMenu("Help");
		// Set the menu shortcut to use with Alt-
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);
		
		//HELP>USER'S GUIDE
		//displays the user guide (that should probably be updated)
		JMenuItem mntmUsersGuide = new JMenuItem("User's Guide");
		mntmUsersGuide.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				openUserGuide();
			}
		});
		mnHelp.add(mntmUsersGuide);
		
		//HELP>ABOUT
		//displays general information about the application
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});
	}
	
	/**Opens a dialog box for a user to select one or multiple nessus files to parse */ 
	private void openFiles(String fileType) throws SAXParseException{

		JFileChooser fileChooser = new JFileChooser(); 
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setDialogTitle("Open "+fileType.toUpperCase());
		int result = fileChooser.showOpenDialog(null); //(contentPane)
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects (a) file(s)
			 File[] selectedFiles = fileChooser.getSelectedFiles();
	
			 JFrame frame = new JFrame("Loading");
			 frame.setSize(500,10);
			 frame.setVisible(true);
			 frame.setLocationRelativeTo(null);

			 window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			 
			 //parses the selected files 
				try {
					if (fileType == "nessus"){
						parser = new Parser("nessus");	
					}else if(fileType == "lightning"){
						parser = new Parser("lightning");
					}
				parser.startParser(selectedFiles);
				window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				} catch (SAXException e) {
						e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					e.printStackTrace();
				} 
			//displays all parsed data 
			getData("");
			frame.dispose(); // not sure what this is for
		}	
	}
	
	/**Top-level method for displaying the parsed data onto a table within the application */
	public void getData(String text){
		//removes the last table
		window.removeAll();
		String[] colNames = {"host","vendor","mac_address","qualified_name","ip_address","o_s","operating_system","system_type","fqdn","scan_date","installed_software","trace_route_hops", "cvss_base_score", "cvss_temporal_score"};
		List<ReportHost> dataList = parser.getHosts();
		
		//creates a 2-dimensional array of data to display
		Object [][] data = new String [dataList.size()][];
		Iterator<ReportHost> it = dataList.iterator();
	    ReportHost curHost = new ReportHost();
	    int rowIndex =0;
	    int index = 0;
	    
	    //places searched data in the 2-dimensional array to prep for display. 
	    //If there is no search filter, text="" and returns all data.
	    while (it.hasNext()) {
	    	curHost = it.next();
	    	String[] entries = curHost.toString().split("><");
	    	for (String entry: entries){
	    		if (entry.toLowerCase().contains(text)){
	    			data[rowIndex]= entries;
	    			rowIndex++;
	    			break;
	    		}
	    	}
	    } 
	    //rebuilds the interface
	    createMenuBar();
	    //ensures that the menu bar is always enabled. 
	    window.validate();
	    buildSearchArea(parser);
	    window.revalidate();
	    buildTable(data, colNames);
	    
	    //ensures everything is enabled after the search	    
	    window.revalidate();
	}
	
	/**creates a search area which displays search area with a button to revert to viewing all parsed data. */
	public void buildSearchArea(Parser nessus){
		Box searchArea= Box.createHorizontalBox();
		JLabel searchlabel = new JLabel("Search: ", JLabel.RIGHT);
		JTextField searchField = new JTextField(20);
		JButton revert = new JButton("All Results");
		JButton clear = new JButton("Clear");
		JLabel dataInfo = new JLabel("Parse "+parser.getType().toUpperCase() + "            Rows: " + parser.getHostList().size()+"            ", JLabel.LEFT);
		//searches the whole datatable for substring
		searchField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				String text = searchField.getText().toLowerCase();
				getData(text);
			}
		});
		//displays all parsed data within the file
		revert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				getData("");
			}
		});
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				if (parser.getType() == "lightning")
					parser.setLightningHosts(new HashMap<String, ReportHost>());
				else
					parser.getHosts().clear();
				getData("");
				buildSearchArea(parser);
			}
		});
		
		searchArea.add(dataInfo);
		searchArea.add(searchlabel);
		searchArea.add(searchField);
		searchArea.add(revert);
		searchArea.add(clear);
			
		Box singlelineFields = Box.createVerticalBox();
		singlelineFields.add(searchArea);
		JPanel searchPanel = new JPanel();
		searchPanel.add(singlelineFields);
			
		window.add(searchPanel, BorderLayout.PAGE_START);
	}
	
	/**creates a table with the designated data and column headers */
	public void buildTable(Object[][] data, String[] colNames){
	    DefaultTableModel model = new DefaultTableModel(data, colNames);
		JTable table = new JTable(model);
		resizeColumnWidth(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		window.add(scrollPane, BorderLayout.CENTER);	
	}
	
	/**regulates the width of the columns in the nessus display table */
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 60; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 200)
	            width=200;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    	}
	} 
	
	/**An attempt to show a loading bar.. not currently in use. */
	private void showBar(JFrame frame){
		//Create and set up the window.
        

        //Create and set up the content pane.
        JComponent newContentPane = new JPanel();
        JProgressBar progressBar = new JProgressBar();
	     progressBar.setIndeterminate(true);
	     progressBar.setStringPainted(true);
	    frame.add(progressBar,BorderLayout.PAGE_START );
	    frame.setLocationRelativeTo(null);

        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setVisible(true);
	}
	
	/**Saves the parsed data as multiple csv files in a user-selected directory meant for a magic draw import.
	 * - all inclusive data - I may need to change the header in the display to look like the csv
	 * - a list all hosts, ports, and protocols
	 * - a connector-ends list
	 * (does not currently save searched data only)  */
	private void saveFile() throws FileNotFoundException{

		String cEfileName;
		String iSfileName;
		String hpFileName;
		String hvFileName;
		String delimiter;
		
		String site = selectSite();
		
		String saveToDirectory;
		JFileChooser saveFiles = new JFileChooser();
		try{
		saveFiles.setDialogTitle("Save "+parser.getType()+": Select a Directory");
		}catch(NullPointerException e){
			e.printStackTrace();
			Warnings.noFile();
		}
		saveFiles.setApproveButtonText("Save");
		
		//Tells the user that the files will be saved as a .csv
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", ".csv");
		saveFiles.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		saveFiles. setAcceptAllFileFilterUsed(false);
		saveFiles.setFileFilter(filter);
		
		int userSelection = saveFiles.showOpenDialog(null);
		if (userSelection ==JFileChooser.APPROVE_OPTION){
			File folder = saveFiles.getSelectedFile();
			saveToDirectory = folder.getAbsolutePath();
			delimiter = File.separator;
			cEfileName = saveToDirectory + delimiter+ magicDrawConnectorEnds;
			iSfileName = saveToDirectory + delimiter+magicDrawImportSpreadsheet;
			hpFileName = saveToDirectory + delimiter+magicDrawSrvProPorts;
			hvFileName = saveToDirectory + delimiter+magicDrawHostVulns;

				WritetoCSV.writeMagicDrawCsvFile(iSfileName, Parser.getHosts());
				WritetoCSV.writeConnectorEndsCsvFile(cEfileName, Parser.getConnectionsList(), site);
				WritetoCSV.writeMagicDrawHostPorts(hpFileName, Parser.getPortsInfo());
				if (parser.getType()=="nessus")
					WritetoCSV.writeHostVulnerabilities(hvFileName, Parser.getHosts());
			//get save status
			boolean isSaveStatus = WritetoCSV.getIsSaveStatus();
			boolean ceSaveStatus = WritetoCSV.getCeSaveStatus();
			boolean hpSaveStatus = WritetoCSV.getHpSaveStatus();
			boolean hvSaveStatus = WritetoCSV.getHvSaveStatus();
			if (parser.getType()=="lightning")
				hvSaveStatus = true;
			Warnings.showSaveConfirmation(parser.getType(), saveToDirectory,isSaveStatus, ceSaveStatus, hpSaveStatus,hvSaveStatus);
			System.out.println("Save as file: " + folder.getAbsolutePath());
		}
	}
	
	public String selectSite(){
		 
		 JFrame frame = new JFrame("Select Site");
		String site = JOptionPane.showInputDialog(frame, "Enter a site for MBSE import");
		
		return site;
	}
	
	
	/**Creates a GUI interface for the user to connect to the database and save information */
	public void showDbWindow(){
		final JFrame frame = new JFrame();
		frame.setSize(new Dimension(window.getWidth()/2,window.getHeight()/2 + 100));
		frame.setLocationRelativeTo(window);
		frame.setTitle("Save to DataBase");
		
		Box keyspaceArea = Box.createHorizontalBox();
		JLabel lblKeyspace = new JLabel("Keyspace: ");
		JTextField txtKeyspace = new JTextField(10);
		keyspaceArea.add(lblKeyspace);
		keyspaceArea.createRigidArea(new Dimension(5,5));
		keyspaceArea.add(txtKeyspace);
		
		Box userArea = Box.createHorizontalBox();
		JLabel lblUser = new JLabel("User: ");
		JTextField txtUser = new JTextField(10);
		userArea.add(lblUser);
		userArea.createRigidArea(new Dimension(5,5));
		userArea.add(txtUser);
		
		Box pswdArea = Box.createHorizontalBox();
		JLabel lblPswd = new JLabel("Password: ");
		JPasswordField txtPswd = new JPasswordField(10);
		txtPswd.setEchoChar('*');
		pswdArea.add(lblPswd);
		pswdArea.createRigidArea(new Dimension(5,5));
		pswdArea.add(txtPswd);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent evt) {
				String keyspace = txtKeyspace.getText();
				String user = txtUser.getText();
				String pswd = txtPswd.getText();
				try{
				UpdateCassandra cassandra = new UpdateCassandra();//keyspace, user, pswd);
				cassandra.save(parser);
				}catch(ExceptionInInitializerError e){
					Warnings.showAuthenticationError();
					e.printStackTrace();
				}
			}
		});
		
		Box vert1 = Box.createVerticalBox();
		vert1.add(new JLabel("Enter Datbase Details"));
		vert1.createRigidArea(new Dimension(50,50));
		vert1.add(keyspaceArea);
		vert1.createRigidArea(new Dimension(5,5));
		vert1.add(userArea);
		vert1.createRigidArea(new Dimension(500,500));
		vert1.add(pswdArea);
		vert1.createRigidArea(new Dimension(5,20));
		vert1.add(btnSubmit);
		
		JPanel panel = new JPanel();
		panel.add(vert1);

		frame.add(panel);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	
	/**Instructs users on how to update a dependent datafile (oui.csv). Provides a link to the dat file source*/
	public void selectDataFile(){
		String instructions = "<html><h3><b>Update oui.csv MAC Address look-up:</b></h3><br>1. Click on the link below.<br>2. Download (as CSV) MAC Address Block Large(MA-L) to the \"parser_resources\" folder <br>located in the same file location as this Parser executable.<br>"
				+ "<h4>This may take a couple of minutes due to the size of the file.</h4></html>";
		String url = "https://regauth.standards.ieee.org/standards-ra-web/pub/view.html#registries";
		String link = "<html><FONT color = \"#000099\"> <U>IEEE STANDARDS ASSOCIATION: Registration Authority</U></FONT></html>";

		JLabel inst = new JLabel (instructions);
		JLabel hyperlink = new JLabel(link);
		
		GridBagConstraints c = new GridBagConstraints();
		JFrame frame = new JFrame("Update oui.csv");
		
		frame.setLayout(new GridBagLayout());
		//set to fixed size because it becomes deformed if disproportionate. 
		frame.setSize(750, 300); //good for both mac and windows
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		hyperlink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hyperlink.setToolTipText(url);
		hyperlink.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					Desktop.getDesktop().browse(new URI(url));
				}catch(URISyntaxException| IOException ex){
					ex.printStackTrace();
				}
			}
		});
		
		//arranging Layout of the frame
		c.gridx = 0;
	    c.gridy = 0;
		c.ipadx = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
	    frame.add(inst,c);

	    c.anchor = GridBagConstraints.CENTER;
	    c.gridy= 1;
	    frame.add(hyperlink, c);

	    frame.setVisible(true);

	}
	/** Displays the about information about this parser  */
	public void showAbout(){
		JFrame frame = new JFrame("About");
		String url = "http://www.g2-ops.com/home/";
		frame.setLayout(new GridBagLayout());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(500, 300);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel logo = new JLabel(new ImageIcon("./images/logo.png"));
		JLabel appInfo = new JLabel ("<html><h3><span style =\"background-color:= #FFFFFF\">Scan Parser 4.0</b><BR>@ 2018 G2 Ops</span></h3></html>");
		JLabel moreInfo = new JLabel("<html><h4>For more information about G2 Ops' products and services, visit our website: <h4></html>");
		JLabel site = new JLabel ("<html><FONT color = \"#000099\"> <U>www.g2-ops.com</U></FONT></html>");

		JPanel about = new JPanel();
	    about.setSize(800, 250); //511,298
	    about.setBorder(BorderFactory.createLoweredBevelBorder());
	    about.setLayout(new GridBagLayout());
	    
		GridBagConstraints c = new GridBagConstraints();
		
		site.setCursor( new Cursor(Cursor.HAND_CURSOR));
		site.setToolTipText(url);
		//makes the link to the website clickable
		site.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				try{
					Desktop.getDesktop().browse(new URI(url));
				}catch(URISyntaxException| IOException ex){
					//something should probably go here
				}
			}
		});
		
		c.gridx = 0;
		c.gridy = 0;

		c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.FIRST_LINE_START;
	    frame.add(logo,c);
	   
	    c.gridy = 1;
	    frame.add(appInfo, c);
		
	    c.gridx = 0;
	    c.gridy =0;
	    c.ipadx = 2;
	    c.anchor = GridBagConstraints.FIRST_LINE_START;
		about.add(moreInfo, c);

		c.ipady = 70;
		c.anchor = GridBagConstraints.PAGE_START;
		about.add(site,c);
		
		c.gridy = 2;
		c.ipady = 0;
		c.gridwidth = 3;
		c.gridheight = 2;
		frame.add(about,c);
		
		frame.setVisible(true);
	}
	
	/**Displays the user guide for the application
	 * The user guide should probably be updated */ 
	public void openUserGuide(){
		String userGuideFileName;
		try{
			File locate = new File(Parser.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
			System.out.println("UserGuide path: " +locate.getAbsolutePath());
			
			String path = locate.getAbsolutePath();
			String file = locate.getName();
			System.out.println("File: " + file);
			
			userGuideFileName = path.replace(file, "") + USER_GUIDE ;
//			System.out.println("userGuideFileName: "+ userGuideFileName);
			//opening file
			File userGuide = new File(userGuideFileName);
			
		    Desktop desktop = Desktop.getDesktop();
		    desktop.open(userGuide);		 
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			e.printStackTrace();
			Warnings.showFileNotFound(USER_GUIDE, "");
		}catch (UnsupportedOperationException e){
			e.printStackTrace();
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame,"Platform is not supported to open User Guide file" , 
					"UnsupportedOperationException Found", JOptionPane.WARNING_MESSAGE);
		}
	}
}



