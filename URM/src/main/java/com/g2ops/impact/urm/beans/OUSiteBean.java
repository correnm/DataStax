package com.g2ops.impact.urm.beans;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, September 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 */
	
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.g2ops.impact.urm.types.OUSite;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;

@Named("OUSiteBean")
@RequestScoped
public class OUSiteBean {

	@Inject private UserBean currentUser;
	
	private String selectedOU, selectedSite, selectedOUSite;
	private Iterator<Row> iterator, iterator2;
	private OUSite[] OUSiteArray;
	private List<OUSite> OUSiteArrayList = new ArrayList<OUSite>();

	public OUSiteBean() {

		// empty constructor

	}

	@PostConstruct
	public void init() {

		// get the currently active OU and Site from the UserBean
		selectedOU = currentUser.getOrgUnitID();
		selectedSite = currentUser.getSiteID();
		selectedOUSite = selectedOU + "|" + selectedSite;

		// get the Database Query Service object for this Org
		DatabaseQueryService databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());

		// do DB query to get OUs
		ResultSet rs = databaseQueryService.runQuery("select org_unit_id, org_unit_name from organizational_units");

		// iterate over the results
		iterator = rs.iterator();
		while (iterator.hasNext()) {

			Row row = iterator.next();
			String org_unit_id = row.getUUID("org_unit_id").toString();
			String org_unit_name = row.getString("org_unit_name");

			// do DB query to get Sites for this OU
			ResultSet rs2 = databaseQueryService.runQuery("select site_id, site_name from sites where org_unit_id = " + UUID.fromString(org_unit_id));

			// iterate over the results and put each OU/Site pair into the arraylist (can't be an array because we don't know the size)
			iterator2 = rs2.iterator();
			while (iterator2.hasNext()) {

				Row row2 = iterator2.next();
				String site_id = row2.getUUID("site_id").toString();
				String site_name = row2.getString("site_name");

				String OUSiteID = org_unit_id + "|" + site_id;
				String OUSiteName = org_unit_name + " - " + site_name;
				
				OUSite OUSiteInstance = new OUSite(OUSiteID, OUSiteName);
				OUSiteArrayList.add(OUSiteInstance);
			}

		}
			
		// populate array with arraylist
		OUSiteArray = (OUSite[]) OUSiteArrayList.toArray(new OUSite[OUSiteArrayList.size()]);

		// sort the array by OU/Site names
		Arrays.sort(OUSiteArray);

		// convert the array back to an arraylist
		OUSiteArrayList = new ArrayList<OUSite>(Arrays.asList(OUSiteArray));
		
	}

	public String getSelectedOUSite() {
		return selectedOUSite;
	}
	
	public void setSelectedOUSite(String selectedOUSite) {
		this.selectedOUSite = selectedOUSite;
	}
	
	public List<OUSite> getOUSiteArray() {
		return OUSiteArrayList;
	}

	// method called when the OU/Site menu value changes
	public void OUSiteChanged(ValueChangeEvent event) {

		String newValues = (String)event.getNewValue();
		String[] newValuesArray = newValues.split("[|]{1}");
		currentUser.setOrgUnitID(newValuesArray[0]);
		currentUser.setSiteID(newValuesArray[1]);

	}
	
}
