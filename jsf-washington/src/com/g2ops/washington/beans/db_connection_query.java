package com.g2ops.washington.utils;

	import com.datastax.driver.core.*;
	import javax.faces.context.FacesContext;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.ServletContext;
	import com.datastax.driver.core.ResultSet;
	import com.datastax.driver.core.Row;
	import com.g2ops.washington.utils.DatabaseConnectionManager;

	import java.io.FileReader;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.HashMap;
	import org.json.simple.JSONObject;
	import org.json.simple.JSONArray;
	import org.json.simple.parser.JSONParser;
	import org.json.simple.parser.ParseException;
	
	import java.util.Random;
	import java.util.Iterator;
	import java.util.List;
	import javax.json.*;

	public class db_connection {
	    //private static final AuthProvider INPUT_NATIVE_AUTH_PROVIDER = "cassandra.input.native.auth.provider"; 
	    private static final String USERNAME = "washington"; 
	    private static final String PASSWORD = "7579658330"; 
	    private static String chartData;
	  //
	    static String[] CONTACT_POINTS = {"54.86.18.217"}; 
	    static int PORT = 9042;
	    static AuthProvider authProvider = new PlainTextAuthProvider(USERNAME, PASSWORD);	    
		/**
		 * Connects to a Cassandra cluster and extracts basic information from it.
		 * <p/>
		 * Preconditions:
		 * - a Cassandra cluster is running and accessible through the contacts points identified by CONTACT_POINTS and PORT.
		 * <p/>
		 * Side effects: none.
		 *
		 * @see <a href="http://datastax.github.io/java-driver/manual/">Java driver online manual</a>
		 */

		    public static void main(String[] args) {
		        
		        Cluster cluster = null;
		        //conf.set(INPUT_NATIVE_AUTH_PROVIDER, PlainTextAuthProvider.class.getName()); 
	            //conf.set(USERNAME, "washington"); 
	           // conf.set(PASSWORD, "7579659330"); 
		        JSONParser parser2 = new JSONParser();
		        try {
		            // The Cluster object is the main entry point of the driver.
		            // It holds the known state of the actual Cassandra cluster (notably the Metadata).
		            // This class is thread-safe, you should create a single instance (per target Cassandra cluster), and share
		            // it throughout your application.
		            cluster = Cluster.builder()
		                    .addContactPoints(CONTACT_POINTS)
		                    .withPort(PORT)
		                    .withAuthProvider(authProvider)
		                    .build();

		            // The Session is what you use to execute queries. Likewise, it is thread-safe and should be reused.
		            Session session = cluster.connect("g2");
		            

		            // We use execute to send a query to Cassandra. This returns a ResultSet, which is essentially a collection
		            // of Row objects.
		            //ResultSet rs = session.execute("select release_version from system.local");
		            //  Extract the first row (which is the only one in this case).
		            //Row row = rs.one();
		            //ResultSet rs = session.execute("select magic_draw_id from g2.mbse_staging");
		           
		            //ResultSet rs = session.execute("SELECT JSON connected_elements FROM g2.mbse_staging");
		            try {
			            Object obj = parser2.parse(new FileReader(
			        			"/Users/DangL/desktop/PWdata/HW-SW-Data.json"));
			 
			            JSONArray jsonArr = (JSONArray) obj;
			            for (int i = 0; i < jsonArr.size(); i++) {
							JSONObject jObj = (JSONObject) jsonArr.get(i);
			            
							chartData = jObj.toString();
							//System.out.println("Char data is : ");
							System.out.println(chartData);
							session.execute("INSERT INTO g2.mbse_staging JSON 'charData'");
			            }
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		            
		            ResultSet rs = session.execute("SELECT JSON name, magic_draw_id,connected_elements FROM g2.mbse_staging");
		            
					
					//connectorSet is the main JSON set that contains EVERYTHING
					JSONObject connectorSet = new JSONObject();
					
					JSONObject chartSet = new JSONObject();
					chartSet = createChartType("Topology");
					connectorSet.put("chart", chartSet);
					
					//dataset JSON
					ArrayList<JSONObject> dataSetArray = new ArrayList<JSONObject>();
					//connectors JSON
					ArrayList<JSONObject> conArray = new ArrayList<JSONObject>();
					
					//data array containing hashes of node
					ArrayList<HashMap<String,String>> elementsArray = new ArrayList<HashMap<String,String>>();
					ArrayList<HashMap<String,String>> connectorArray = new ArrayList<HashMap<String,String>>();
					
					JSONObject data = new JSONObject();
					data.put("color", "ffffff");
					
					JSONObject connectors = new JSONObject();
					connectors.put("color", "ffffff");
					connectors.put("stdthickness", "1");
		        
		            for (Row row : rs) {
		                // process the row
		            	//String firstName = row.getString(0);
		            	//String json1 = row.getString(0);
		            	//Row row = rs.one();
		            	String json2 = row.getString("[json]");
		            	//System.out.println(json2);
		            	JSONParser parser = new JSONParser();
		            	
		            	
		            	
		            	try {
							JSONObject jsonTest = (JSONObject) parser.parse(json2);
							//jsonTest.put("names", "Danny");
							//String loudScreaming = (String) jsonTest.get("names");
							String elemID = (String) jsonTest.get("magic_draw_id");
							String elemName = (String) jsonTest.get("name");
							
							
							//connectorSet.put("dataset", connectors);
							//Create JSON node object
							JSONObject nodeObj = new JSONObject();
							nodeObj = createNode(elemID,elemName,false);
							elementsArray.add(nodeObj);
							
							data.put("data",elementsArray);
							
							
							//System.out.println(elemID);
							JSONArray connectedElem = (JSONArray) jsonTest.get("connected_elements");
							for (int i = 0; i < connectedElem.size(); i++) {
								JSONObject newObj = (JSONObject) connectedElem.get(i);
								String id = (String) newObj.get("destination_id");
								//System.out.println(id);
								JSONObject connectorObj = new JSONObject();
								//Add code to handle mission supports
								//if mission_support[].size != 0, change color of connector, param = true
								connectorObj =  createConnector(elemID,id,false);
				            	connectorArray.add(connectorObj);
				            	//System.out.println(connectorObj.toString());
								
							}
						
							//System.out.println(loudScreaming);
							//chartData = jsonTest.toJSONString();
							
							//HashMap<String, String> connectors = new HashMap<String,String>();
							
							connectors.put("connector", connectorArray);
							
							//String test = connectors.toString();
							
							//JSONArray connecterArray = new JSONArray();
							//JSONArray connecterArray = new JSONArray(Arrays.asList(where));
							//connecterArray.put(connectors);
							if (conArray.size() == 1){
								
							}else{
								conArray.add(connectors);
							}
							
							if (dataSetArray.size() == 1){
								
							}else{
								dataSetArray.add(data);
							}
							
							connectorSet.put("dataset", dataSetArray);
							connectorSet.put("connectors", conArray);
							String connectorSetString = connectorSet.toString();
							
							System.out.println(connectorSetString);
							//connectors.put("connector", );

							
							
							
							
							
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            	
		            	


		            }


		            // Extract the value of the first (and only) column from the row.
		            //System.out.println(row.getString("release_version")); 

		        } finally {
		            // Close the cluster after we�re done with it. This will also close any session that was created from this
		            // cluster.
		            // This step is important because it frees underlying resources (TCP connections, thread pools...). In a
		            // real application, you would typically do this at shutdown (for example, when undeploying your webapp).
		            if (cluster != null)
		            	cluster.close();
		        }
		    }
		    
		    
		    //Function to build node JSON
		    public static JSONObject createNode(String _elemID, String _elemName, boolean mission_support){
		    	HashMap<String, String> hash = new HashMap<String,String>();

		    	Random rand = new Random();

		    	int  x = rand.nextInt(100) + 1;
		    	int  y = rand.nextInt(100) + 1;
		    	
		    	String strX = Integer.toString(x);
		    	String strY = Integer.toString(y);


		    	hash.put("id", _elemID);
		    	hash.put("label", _elemName);
		    	hash.put("color", "#ffffff");
		    	hash.put("x", strX);
		    	hash.put("y", strY);
		    	hash.put("radius", "25");
		    	hash.put("shape", "circle");
		    	
		    	if (mission_support == true){
		    		hash.put("color", "#ff7f7c");
		    	}else {
		    		hash.put("color", "#ffffff");
		    	}

		    	JSONObject test = new JSONObject(hash);
		    	return test;
		    }
		    
		    //Function to build connector JSON
		    public static JSONObject createConnector(String _elem1 , String _elem2, boolean mission_support){
		    	HashMap<String, String> hash = new HashMap<String,String>();

		    	hash.put("strength", "1");
		    	hash.put("from", _elem1);
		    	hash.put("to", _elem2);
		    	hash.put("arrowatstart", "0");
		    	hash.put("arrowatend", "0");
		    	
		    	if (mission_support == true){
		    		hash.put("color", "ff7f7c");
		    	}else {
		    		hash.put("color", "a6aaad");
		    	}

		    	JSONObject connectorObject = new JSONObject(hash);
		    	return connectorObject;
		    }
		    
		    //Return chart JSON object
		    public static JSONObject createChartType(String _chartName ){
		    	HashMap<String, String> hash = new HashMap<String,String>();

		    	hash.put("caption", _chartName);
		    	hash.put("xaxisminvalue", "0");
		    	hash.put("xaxismaxvalue", "0");
		    	hash.put("yaxisminvalue", "0");
		    	hash.put("yaxismaxvalue", "0");
		    	hash.put("is3d", "0");
		    	hash.put("showformbtn", "1");
		    	hash.put("formaction", "dragnodeData.php");
		    	hash.put("formtarget", "_blank");
		    	hash.put("formmethod", "POST");
		    	hash.put("formbtntitle", "SAVE");
		    	hash.put("viewmode", "0");
		    	hash.put("showplotborder", "1");
		    	hash.put("plotborderthickness", "4");
		    	hash.put("theme", "fint");
		    	hash.put("showcanvasborder", "1");
		    	hash.put("canvasborderalpha", "20");
		    	hash.put("animation", "1");
		    	
		    	JSONObject chartObject = new JSONObject(hash);
		    	return chartObject;
		    }
		}