package com.g2ops.impact.urm.beans;

/**
 * @author 		Corren McCoy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, May 2018
 * @see 		<a href="https://examples.javacodegeeks.com/enterprise-java/jsf/jsf-standard-converters-example/">JSF Converters</a>
 * @exception
 * Purpose:		Fetch item and values needed to populate selectItems drop down
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 05.24.2018		corren.mccoy		created bean
 */
 
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem; // used to display list of values from database

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.types.BusinessPractice;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;


@Named("lovReferences")
@SessionScoped
public class LovReferences {
	
	@Inject private UserBean currentUser;

	private Session dbSession;
	private ResultSet rs;
	private Iterator<Row> iterator;
	private	String optionLabel;
	private List<SelectItem> selectItems = new ArrayList<SelectItem>();
	private String databaseColumn;
 
 	public LovReferences() {

 	} 
 	
 	public List<SelectItem> getSelectItems(String databaseColumn){
 		// all data is stored in lowercase
 		this.databaseColumn = databaseColumn.toLowerCase();
 
		// clear out any old content before re-populating
		selectItems.clear();
		
		// execute the query against the table
		populateSelectItems(databaseColumn);
		
		// query should only return one row per database column
		Row row = rs.one();
		//This is the description of the data we want to display
		String optgroup_label = row.getString("optgroup_label");
		
		// Set the first entry to a user prompt
		selectItems.add(new SelectItem("", "Select " + optgroup_label));  
		
		// items for drop down are stored in a map (k,v)
		Map<String, String> valueMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);

		// Add the elements to the selectItems
        Iterator<Map.Entry<String, String>> iterator = valueMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
			selectItems.add(new SelectItem(entry.getKey(), entry.getValue())); 
        }
        System.out.println(selectItems);
 	 	return selectItems;
 	} //getSelectItems
 	

	private void populateSelectItems (String databaseColumn) {

		// get the Database Query Service object for this Organization
		dbSession = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());
		
		String query = "select optgroup_label, option_values from lov_references"
				+ " where database_column = ?"; 

		PreparedStatement prepared = dbSession.prepare(query);
		BoundStatement bound = prepared.bind(databaseColumn);
		rs = dbSession.execute(bound);
	} //populateSelectItems

} // LovReferences

