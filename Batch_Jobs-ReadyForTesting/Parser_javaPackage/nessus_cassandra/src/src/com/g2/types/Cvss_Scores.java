package src.com.g2.types;
/**
 * @purpose to be mapped to UDT cvss_scores
 * CREATE TYPE cvss_scores (
	cve_id 							text,   // vulnerability list <cve>CVE-2016-7212</cve>
	cvss_base_score 				decimal,	// <cvss_base_score>9.3</cvss_base_score>
	cvss_temporal_score 			decimal,	// <cvss_temporal_score>6.9</cvss_temporal_score>
	cvss_temporal_vector			text,	// <cvss_temporal_vector>CVSS2#E:ND/RL:OF/RC:C</cvss_temporal_vector>
	cvss_vector						text, 	// <cvss_vector>CVSS2#AV:N/AC:H/Au:N/C:P/I:P/A:N</cvss_vector>
	iiv_score						decimal,	// output of VMASC algorithm
	cve_description                 text,
	vuln_modification_date			date,
	patch_references	            list<text>, 
	patch_count						int,
	patched_date					date
	);
 * 
 * */
import java.util.List;
import com.datastax.driver.core.LocalDate;
import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;
import java.math.BigDecimal;
import java.math.MathContext;

@UDT(name = "cvss_scores", keyspace = "")
public class Cvss_Scores {
	
	@Field(name = "cve_id")
	private String cve_id;
	
	@Field(name = "cvss_base_score")
	private BigDecimal cvss_base_score = new BigDecimal(0);
	
	@Field(name = "cvss_temporal_score")
	private BigDecimal cvss_temporal_score = new BigDecimal(0);
	
	@Field(name = "iiv_score")
	private BigDecimal iiv_score = new BigDecimal(0);
	
	@Field(name = "cve_description")
	private String cve_description;
	
	@Field(name = "vuln_modification_date")
	private LocalDate vuln_modification_date;
	
	@Field(name = "cvss_temporal_vector")
	private String cvss_temporal_vector;
	
	@Field(name = "cvss_vector")
	private String cvss_vector;
	
	@Field(name = "patch_references")
	private List<String> patch_references;
	
	@Field(name = "patch_count")
	private int patch_count;
	
	@Field(name = "patched_date")
	private LocalDate patched_date;
	
//	MathContext mc = new MathContext(3);// 3 percision
	
	//>>>>>>>>>>>>>> cve_id <<<<<<<<<<<<<<<<
	public void setCve_id(String cve_id){
		this.cve_id = cve_id;
	}
	public String getCve_id(){
		return cve_id;
	}
	//>>>>>>>>>>>>>> cvss_base_score <<<<<<<<<<<<<<<<
	public void setCvss_base_score(BigDecimal cvss_base_score){
		this.cvss_base_score = cvss_base_score;
	}
	public BigDecimal getCvss_base_score(){
		MathContext mc = new MathContext(3);// 3 percision
		cvss_base_score = cvss_base_score.round(mc);
		return cvss_base_score;
	}
	//>>>>>>>>>>>>>> cvss_temporal_score <<<<<<<<<<<<<<<<
	public void setCvss_temporal_score(BigDecimal cvss_temporal_score){
		this.cvss_temporal_score = cvss_temporal_score;
	}
	public BigDecimal getCvss_temporal_score(){
		MathContext mc = new MathContext(3);// 3 percision
		cvss_temporal_score = cvss_temporal_score.round(mc);
		return cvss_temporal_score;
	}
	//>>>>>>>>>>>>>> iiv_score <<<<<<<<<<<<<<<<
	public void setIiv_score(BigDecimal iiv_score){
		this.iiv_score = iiv_score;
	}
	public BigDecimal getIiv_score(){
		MathContext mc = new MathContext(3);// 3 percision
		iiv_score = iiv_score.round(mc);
		return iiv_score;
	}
	//>>>>>>>>>>>>>> cve_description <<<<<<<<<<<<<<<<
	public void setCve_description(String cve_description){
		this.cve_description = cve_description;
	}
	public String getCve_description(){
		return cve_description;
	}
	//>>>>>>>>>>>>>> vuln_modification_date <<<<<<<<<<<<<<<<
	public void setVuln_modification_date(LocalDate vuln_publication_date){
		this.vuln_modification_date = vuln_publication_date;
	}
	public LocalDate getVuln_modification_date(){
		return vuln_modification_date;
	}
	//>>>>>>>>>>>>>> cvss_temporal_vector <<<<<<<<<<<<<<<<
	public void setCvss_temporal_vector(String cvss_temproal_vector){
		this.cvss_temporal_vector = cvss_temporal_vector;
	}
	public String getCvss_temporal_vector(){
		return cvss_temporal_vector;
	}
	//>>>>>>>>>>>>>> cvss_vector <<<<<<<<<<<<<<<<
	public void setCvss_vector(String cvss_vector){
		this.cvss_vector = cvss_vector;
	}
	public String getCvss_vector(){
		return cvss_vector;
	}
	//>>>>>>>>>>>>>> patch_references <<<<<<<<<<<<<<<<
	public void setPatch_references(List<String> patch_references){
		this.patch_references = patch_references;
	}
	public List<String> getPatch_references(){
		return patch_references;
	}
	//>>>>>>>>>>>>>> patch_count <<<<<<<<<<<<<<<<
	public void setPatch_count(int patch_count){
		this.patch_count = patch_count;
	}
	public int getPatch_count(){
		return patch_count;
	}
	//>>>>>>>>>>>>>> patched_date <<<<<<<<<<<<<<<<
	public void setPatched_date(LocalDate patched_date){
		this.patched_date = patched_date;
	}
	public LocalDate getPatched_date(){
		return patched_date;
	}
	
	public static String replaceNull(String input) {
		return input == null ? "" : input;
	}
	
	public String toString(){
		StringBuffer cvssBuff = new StringBuffer();
		cvssBuff.append("cve_id: " + getCve_id());
		cvssBuff.append(", cvss_base_score: " + getCvss_base_score());
		cvssBuff.append(", cvss_temporal_score: " + getCvss_temporal_score());
		cvssBuff.append(", iiv_score: " + getIiv_score());
		cvssBuff.append(", cve_description: " + getCve_description());
		cvssBuff.append(", vuln_modification_date: " + getVuln_modification_date());
		cvssBuff.append(", cvss_temporal_vector: " + getCvss_temporal_vector());
		cvssBuff.append(", cvss_vector: " + getCvss_vector());
		if ((getPatch_count() >0))
			cvssBuff.append(", patch_references: " + patch_references.get(0));
		cvssBuff.append(", patch_count: " + getPatch_count());
		cvssBuff.append(", patched_date: " + getPatched_date());
		
		return cvssBuff.toString();
	}
}
