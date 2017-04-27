package com.g2ops.washington.utils;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;

public class DatabaseConnectionManager {

    private String USERNAME; 
    private String PASSWORD; 
    //private String[] CONTACT_POINTS = {"127.0.0.1", "127.0.0.1", "127.0.0.1", "127.0.0.1"};
    private String[] CONTACT_POINTS;
    private int PORT;

    private Cluster cluster;
    private Session session;

    //public DatabaseConnectionManager(String uName, String pWord, String cPoint1, String cPoint2, String cPoint3, String cPoint4, String port) {
    public DatabaseConnectionManager(String uName, String pWord, String[] cPoints, String port) {

    	this.USERNAME = uName;
    	this.PASSWORD = pWord;
        //this.CONTACT_POINTS[0] = cPoint1;
        //this.CONTACT_POINTS[1] = cPoint2;
        //this.CONTACT_POINTS[2] = cPoint3;
        //this.CONTACT_POINTS[3] = cPoint4;
    	this.CONTACT_POINTS = cPoints;
    	this.PORT = Integer.parseInt(port);
    	
        AuthProvider authProvider = new PlainTextAuthProvider(this.USERNAME, this.PASSWORD);

    	
        //try { 
            // The Cluster object is the main entry point of the driver.
            // It holds the known state of the actual Cassandra cluster (notably the Metadata).
            // This class is thread-safe, you should create a single instance (per target Cassandra cluster), and share
            // it throughout your application.
            cluster = Cluster.builder()
                    .addContactPoints(this.CONTACT_POINTS)
                    .withPort(this.PORT)
                    .withAuthProvider(authProvider)
                    .build();

            // The Session is what you use to execute queries. Likewise, it is thread-safe and should be reused.
            session = cluster.connect();

        //} finally {
            // Close the cluster after we’re done with it. This will also close any session that was created from this
            // cluster.
            // This step is important because it frees underlying resources (TCP connections, thread pools...). In a
            // real application, you would typically do this at shutdown (for example, when undeploying your webapp).
            //if (cluster != null)
            	//cluster.close();
        //}

    }

    public Session getSession() {
    	return session;    	
    }

    public void closeConnection() {
       cluster.close();
    }    

}
