package src.com.g2.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import src.com.g2.types.Cvss_Scores;

public class VulnUtils {
	
	private final static Logger slf4jLogger = LoggerFactory.getLogger(VulnUtils.class);
	
	/**@purpose returns all the new cvss_scores that are within the results Set and not yet included in the current cvss_scores
	 * @param rs_scores: resultset of all scores for a particular cpe
	 * @param curr_cve: a list of cveids that the host already has. */
	public static List<Cvss_Scores> getNewScores_software(ResultSet rs_scores, List<String> curr_cves){
		List<Cvss_Scores> cpeScoresList = new ArrayList<Cvss_Scores>();
		Iterator<Row> it = rs_scores.iterator();
		while(it.hasNext()){
			Row row = it.next();
			if (curr_cves.contains(row.getString("cve_id")))
				continue;
			Cvss_Scores score = new Cvss_Scores();
			score.setCve_id(row.getString("cve_id"));
			score.setCve_description(row.getString("cve_description"));
			score.setCvss_base_score(row.getDecimal("cvss_base_score"));
			score.setCvss_temporal_score(row.getDecimal("cvss_temporal_score"));
			score.setCvss_vector(row.getString("cvss_vector"));
			score.setCvss_temporal_vector(row.getString("cvss_temporal_vector"));
			score.setVuln_modification_date(row.getDate("modified_date"));
			score.setPatch_count(row.getInt("patch_count"));
			score.setPatch_references(row.getList("patch_references", String.class));
			cpeScoresList.add(score);
		}
		return cpeScoresList;
	}
	
	public static Cvss_Scores updateScore(Cvss_Scores score,ResultSet rs_score){
		Iterator<Row> it = rs_score.iterator();
		while(it.hasNext()){
			Row row = rs_score.one();
			if (score.getCvss_base_score() == null){
//				System.out.println("Select * from common_vulnerabilities where cve_id = "+score.getCve_id());
				slf4jLogger.error("base score is null for cve: "+score.getCve_id());
//				score.setCvss_base_score(cvss_base_score);
				continue;
				}
			score.setCve_description(row.getString("cve_description"));
			score.setCvss_base_score(row.getDecimal("cvss_base_score"));
			score.setCvss_temporal_score(row.getDecimal("cvss_temporal_score"));
			score.setCvss_vector(row.getString("cvss_vector"));
			score.setCvss_temporal_vector(row.getString("cvss_temporal_vector"));
			score.setVuln_modification_date(row.getDate("modified_date"));
			score.setPatch_count(row.getInt("patch_count"));
			score.setPatch_references(row.getList("patch_references", String.class));
			return score;
		}
		return score;
	}
}
