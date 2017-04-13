package com.g2ops.washington.utils;

	import com.datastax.driver.core.*;

	public class db_connection {
	    //private static final AuthProvider INPUT_NATIVE_AUTH_PROVIDER = "cassandra.input.native.auth.provider"; 
	    private static final String USERNAME = "washington"; 
	    private static final String PASSWORD = "7579658330"; 
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
		            Session session = cluster.connect();

		            // We use execute to send a query to Cassandra. This returns a ResultSet, which is essentially a collection
		            // of Row objects.
		            ResultSet rs = session.execute("select release_version from system.local");
		            //  Extract the first row (which is the only one in this case).
		            Row row = rs.one();

		            // Extract the value of the first (and only) column from the row.
		            System.out.println(row.getString("release_version")); 

		        } finally {
		            // Close the cluster after we’re done with it. This will also close any session that was created from this
		            // cluster.
		            // This step is important because it frees underlying resources (TCP connections, thread pools...). In a
		            // real application, you would typically do this at shutdown (for example, when undeploying your webapp).
		            if (cluster != null)
		            	cluster.close();
		        }
		    }
		}