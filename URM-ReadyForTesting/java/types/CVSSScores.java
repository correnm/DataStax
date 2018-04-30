package com.g2ops.impact.urm.types;

/**
 * @author Tammy Bogart

 * @date 04.10.2018
 * 
 * @purpose This is a utility class stores the cvss_scores column in the hardware table that is a User-Defined type
 * 
 * 
 * Modification History
 * Date 				Author				Description
 *
 */

import com.datastax.driver.mapping.annotations.UDT;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Field;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@UDT(name = "cvss_scores", keyspace="")

public class CVSSScores implements Serializable {

	private static final long serialVersionUID = 1L;

	@Field(name = "cve_id")
	private String cveID;
	
	@Field(name = "cvss_base_score")
	private BigDecimal cvssBaseScore;

	@Field(name = "cvss_temporal_score")
	private BigDecimal cvssTemporalScore;

	@Field(name = "cvss_temporal_vector")
	private String cvssTemporalVector;
	
	@Field(name = "cvss_vector")
	private String cvssVector;
	
	@Field(name = "iiv_score")
	private BigDecimal iivScore;
	
	@Field(name = "cve_description")
	private String cvssDescription;
	
	@Field(name = "vuln_modification_date")
	private LocalDate vulnModificationDate;
	
	@Field(name = "patch_references")
	private List<String> patchReferences;
	
	@Field(name = "patch_count")
	private int patchCount;
	
	@Field(name = "patched_date")
	private LocalDate patchedDate;
	
	
	
	/////////////////////Getters-Setters/////////////////////////////////////

	public String getCveID() {
		return cveID;
	}

	public void setCveID(String cveID) {
		this.cveID = cveID;
	}

	public BigDecimal getCvssBaseScore() {
		return cvssBaseScore;
	}

	public void setCvssBaseScore(BigDecimal cvssBaseScore) {
		this.cvssBaseScore = cvssBaseScore;
	}

	public BigDecimal getCvssTemporalScore() {
		return cvssTemporalScore;
	}

	public void setCvssTemporalScore(BigDecimal cvssTemporalScore) {
		this.cvssTemporalScore = cvssTemporalScore;
	}

	public String getCvssTemporalVector() {
		return cvssTemporalVector;
	}

	public void setCvssTemporalVector(String cvssTemporalVector) {
		this.cvssTemporalVector = cvssTemporalVector;
	}

	public String getCvssVector() {
		return cvssVector;
	}

	public void setCvssVector(String cvssVector) {
		this.cvssVector = cvssVector;
	}

	public BigDecimal getIivScore() {
		return iivScore;
	}

	public void setIivScore(BigDecimal iivScore) {
		this.iivScore = iivScore;
	}

	public String getCvssDescription() {
		return cvssDescription;
	}

	public void setCvssDescription(String cvssDescription) {
		this.cvssDescription = cvssDescription;
	}

	public LocalDate getVulnModificationDate() {
		return vulnModificationDate;
	}

	public void setVulnModificationDate(LocalDate vulnModificationDate) {
		this.vulnModificationDate = vulnModificationDate;
	}

	public List<String> getPatchReferences() {
		return patchReferences;
	}

	public void setPatchReferences(List<String> patchReferences) {
		this.patchReferences = patchReferences;
	}

	public int getPatchCount() {
		return patchCount;
	}

	public void setPatchCount(int patchCount) {
		this.patchCount = patchCount;
	}

	public LocalDate getPatchedDate() {
		return patchedDate;
	}

	public void setPatchedDate(LocalDate patchedDate) {
		this.patchedDate = patchedDate;
	}
	
	
}