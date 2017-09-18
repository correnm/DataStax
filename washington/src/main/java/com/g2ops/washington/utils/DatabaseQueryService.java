package com.g2ops.washington.utils;

import com.g2ops.washington.utils.SessionUtils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

import java.io.Serializable;

public class DatabaseQueryService implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session dbSession;

	public DatabaseQueryService() {
		
		System.out.println("start DatabaseQueryService constructor");
		
		// get the database connection session
		dbSession = SessionUtils.getOrgDBSession();

		System.out.println("end DatabaseQueryService constructor");

	}
	
	public ResultSet runQuery(String query) {

		// execute the query that was passed in
		ResultSet rs = dbSession.execute(query);

		// return the result set
		return rs;

	}
	
}
