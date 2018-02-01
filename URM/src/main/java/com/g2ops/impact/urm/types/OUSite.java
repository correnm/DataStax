package com.g2ops.impact.urm.types;

public class OUSite implements Comparable<OUSite> {

	private String OUSiteCodes, OUSiteNames;
	
	public OUSite (String OUSiteCodes, String OUSiteNames) {
		this.OUSiteCodes = OUSiteCodes;
		this.OUSiteNames = OUSiteNames;
	}

	public String getOUSiteCodes() {
		return OUSiteCodes;
	}

	public String getOUSiteNames() {
		return OUSiteNames;
	}

	// method to compare passed in OUSite name with this object's OUSite name
	public int compareTo(OUSite compareOUSite) {

		//ascending order
		return this.OUSiteNames.compareTo(compareOUSite.getOUSiteNames());

		//descending order
		//return compareOUSiteNames.compareTo(this.OUSiteNames);

	}

}
