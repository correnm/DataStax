package com.g2ops.impact.urm.beans;

/**
 * @author 		Corren McCoy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2018
 * @see 		<a href="https://examples.javacodegeeks.com/enterprise-java/jsf/jsf-standard-converters-example/">JSF Converters</a>
 * @exception
 * Purpose:		Fetch item and values needed to populate selectItems drop down
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 * 			currentUser needs to be passed in because when defining it in the class, it returns null
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 05.24.2018		corren.mccoy		created bean
 * 06.06.2018		tammy.bogart		updated bean - added DatabaseQueryService references, changed class to Serializable, 
 * 06.07.2018		tammy.bogart		updated bean - changed output to LinkedHashMap so the values for previous selections can be seen and the data is ordered
 */
 
//import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
//import javax.inject.Inject;
import javax.inject.Named;

//import javax.faces.model.SelectItem; // used to display list of values from database

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;


@Named("lovReferences")
@SessionScoped		//required for pulling in currentUser info --Client-specific data   

public class LovReferences implements Serializable {

	private static final long serialVersionUID = 1L;	

	//@Inject private UserBean currentUser;
	
	private Session dbSession;
	private ResultSet rs;
//		private List<SelectItem> selectItems = new ArrayList<SelectItem>();
	private LinkedHashMap<String, String> selectItems = new LinkedHashMap<String, String>();			
	private String optgroup_label;

	// constructor
	public LovReferences() {
		System.out.println("*** in LovReferences constructor ***");		
 	} 

	public LinkedHashMap<String, String> getSelectItems(String databaseColumn, UserBean currentUser){		
 		// all data is stored in lowercase
 		String dbColumn = databaseColumn.toLowerCase();
 
		// clear out any old content before re-populating
		selectItems.clear();
		optgroup_label = "";
		
		 dbSession = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace()); 		


 		// execute the query against the table
		populateSelectItems(dbColumn);
		
		// query should only return one row per database column
		Row row = rs.one();
		//This is the description of the data we want to display
		optgroup_label = row.getString("optgroup_label");
		
		// Set the first entry to a user prompt
		selectItems.put("", "Select " + optgroup_label);
		
		// items for drop down are stored in a map (k,v)
		Map<String, String> valueMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);

		// Add the elements to the selectItems
        Iterator<Map.Entry<String, String>> iterator = valueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
           selectItems.put(entry.getKey(), entry.getValue());
        }

        return selectItems;
 	} //getSelectItems
 	

	private void populateSelectItems (String databaseColumn) {
 		String query = "select optgroup_label, option_values from lov_references"
				+ " where database_column = ?"; 

		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(databaseColumn);
		rs = dbSession.execute(bound);
	} //populateSelectItems
	
	//public UserBean getCurrentUser() {
	//	return currentUser;
	//}

} // LovReferences

