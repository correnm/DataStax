package src.com.g2.utils;

import java.math.BigDecimal;

/**
 * @author Sara Bergman
 * @date 29-Jan 2018
 * 
 * @purpose This is a utility class that returns a session that connects to the Cassandra database.
 * 
 * 
 * Modification History
 * Date 				Author				Description
 *
 */

import com.datastax.driver.core.AuthProvider;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PlainTextAuthProvider;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class Connect2Db {
	/**class variables**/
	private static String USERNAME = "sara_bergman";//sara_prokop//sara_bergman//washington 
	private static String PASSWORD = "password"; //password//7579659330

	private static String[] CONTACT_POINTS = {"52.44.86.234"}; 
	private static int PORT = 9042;
	private static AuthProvider authProvider = new PlainTextAuthProvider(USERNAME, PASSWORD);
	/*connecting to database*/
	private static Cluster cluster = Cluster.builder().addContactPoints(CONTACT_POINTS).withPort(PORT).withAuthProvider(authProvider).build();
	//default keyspace is to appl_auth
	private static String keySpace = "g2"; // password for vmasc is 7575551212
	private static Session session = cluster.connect(keySpace); 

	public Connect2Db(){
//		test();
	}
	
	public static Session getSession(){
		return session;
	}
	
	public void setKeyspace(String keyspace){
		this.keySpace = keyspace;
	}
	
//	public void test(){
//		String query_cveLookup = "Select cve_description,cvss_base_score, modified_date, patch_count, patch_references from appl_auth.common_vulnerabilities "
//				+ "where cve_id = ? ";
//		PreparedStatement prepared_cveLookup= session.prepare(query_cveLookup);
//		BoundStatement bound;
//		bound = prepared_cveLookup.bind("CVE-1999-0524");
//		Row nvd_row = session.execute(bound).one();
//		BigDecimal baseScore = nvd_row.getDecimal("cvss_base_score");
//		System.out.println(baseScore);
//	}
}
