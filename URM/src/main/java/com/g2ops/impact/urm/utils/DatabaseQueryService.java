package com.g2ops.impact.urm.utils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

public class DatabaseQueryService {

	private Session dbSession;

	public DatabaseQueryService(DatabaseConnectionManager orgDBConnection) {
		
		// get the database connection session
		this.dbSession = orgDBConnection.getSession();

	}
	
	public ResultSet runQuery(String query) {

		// execute the query that was passed in
		ResultSet rs = this.dbSession.execute(query);

		// return the result set
		return rs;

	}
	
	public void runUpdateQuery(String query) {

		// execute the query that was passed in
		this.dbSession.execute(query);

	}
	
}
