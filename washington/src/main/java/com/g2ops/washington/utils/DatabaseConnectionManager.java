package com.g2ops.washington.utils;

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.Session;

public class DatabaseConnectionManager {

	private String USERNAME; 
	private String PASSWORD; 
	private String[] CONTACT_POINTS;
	private int PORT;

	private Cluster cluster;
	private Session session;

	public DatabaseConnectionManager(String uName, String pWord, String[] cPoints, String port) {

		this.USERNAME = uName;
		this.PASSWORD = pWord;
		this.CONTACT_POINTS = cPoints;
		this.PORT = Integer.parseInt(port);

		AuthProvider authProvider = new PlainTextAuthProvider(this.USERNAME, this.PASSWORD);

		// Connect and authenticate to the Cassandra cluster.
		cluster = Cluster.builder()
				.addContactPoints(this.CONTACT_POINTS)
				.withPort(this.PORT)
				.withAuthProvider(authProvider)
				.build();

		// The Session is what you use to execute queries. Likewise, it is thread-safe and should be reused.
		session = cluster.connect();

	}

	public Session getSession() {
		return session;    	
	}

	public void closeConnection() {
		cluster.close();
	}

}
