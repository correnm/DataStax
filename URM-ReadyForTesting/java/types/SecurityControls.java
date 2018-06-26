package com.g2ops.impact.urm.types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, June 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 25-JUne-2018		tammy.bogart		created
 * 
 */

public class SecurityControls implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String scGroup, scID, scName;
	
	private List<SecurityControlRatings>scRatings = new ArrayList<SecurityControlRatings>();


	public SecurityControls (String scGroup, String scID, String scName, List<SecurityControlRatings> scRating) {
		this.scGroup = scGroup;
		this.scID = scID;
		this.scName = scName;
		this.scRatings = scRating;
	}

 
//>>>>>>>>>>>>>>>>>>>>>>>>GETTERS/SETTERS<<<<<<<<<<<<<<<<<<<<<<<<//

	public String getScGroup() {
		return scGroup;
	}

	public void setScGroup(String scGroup) {
		this.scGroup = scGroup;
	}

	public String getScID() {
		return scID;
	}

	public void setScID(String scID) {
		this.scID = scID;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public List<SecurityControlRatings> getScRatings() {
		return scRatings;
	}

	public void setScRatings(List<SecurityControlRatings> scRatings) {
		this.scRatings = scRatings;
	}
}