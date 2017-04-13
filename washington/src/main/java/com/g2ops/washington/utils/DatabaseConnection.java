package com.g2ops.washington.utils;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;

public class DatabaseConnection {

    private static final String USERNAME = "washington"; 
    private static final String PASSWORD = "7579658330"; 

    private static final String[] CONTACT_POINTS = {"54.86.18.217"}; 
    private static final int PORT = 9042;
    private static AuthProvider authProvider = new PlainTextAuthProvider(USERNAME, PASSWORD);

    private static Cluster cluster;
    private static Session session;

    public void connect() {
        
        //try { 
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

    public void close() {
       cluster.close();
    }    

}
