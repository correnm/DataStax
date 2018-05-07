package com.g2ops.impact.urm.beans;

/**
 * @author 		Tammy Bogart, G2 Ops, Virginia Beach, VA
 * @version 	1.00, April 2018
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 4.13.2018		tammy.bogart		created, this bean was created to combine all business attribution beans into 1 bean
 *										in order to remove the need to pass parameters via the front-end 
 */
 
//import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.g2ops.impact.urm.utils.DatabaseQueryService;
import com.g2ops.impact.urm.utils.SessionUtils;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;

import com.g2ops.impact.urm.types.BusinessAttributionTypes;
import com.g2ops.impact.urm.types.BusinessHosts;
import com.g2ops.impact.urm.types.RunsOnHost;
import com.g2ops.impact.urm.types.Audit_Upsert;
import com.g2ops.impact.urm.types.HardwareList;

@Named("businessAttribution")
@SessionScoped	//required for pulling in currentUser info --Client-specific data      



public class BusinessAttribution  implements Serializable {

	private static final long serialVersionUID = 1L;	

	@Inject private UserBean currentUser;
	
	static Audit_Upsert auditUpsert = new Audit_Upsert();

	private DatabaseQueryService databaseQueryService;
	private ResultSet rs, resultSet, resultset;
	private Iterator<Row> iterator, itSites;
	private PreparedStatement prepared; 
	private BoundStatement bound;
	private Session session;
	private Row row, rowSites;
	private Set<RunsOnHost>setROH;
	
	private String query, newQuery, selectedBusName, busName, bit, busCrit, infClass, dbType, annRevC, breachType, selectedHostName;
	private String hostName, hostIP, hostSubnet, hostAssetType, hostAssetVisibility, hostOS, hostVendor, defaultSiteName, resistanceStrengthstr;
	private UUID siteID, hostSiteID, hostID, selSiteID, selBusProcID, busProcID;

	private BigDecimal riskAppetite, annRev, recordCount, resistanceStrength;

	private int annRevYear;
	private List<String> bits;
	private List<String> bcrits;
	private List<String> iclass;
	private List<String> dbtypes;

	private Boolean loadHostAddRun = false;
	
	private BusinessAttributionTypes att;
	private List<BusinessAttributionTypes> attList = new ArrayList<BusinessAttributionTypes>();

	private List<SelectItem> siteList = new ArrayList <SelectItem> ();
	private Set<String> subnetList = new HashSet<String> ();

	private BusinessHosts host, selectedHost;	
	private List<BusinessHosts> hostList = new ArrayList<BusinessHosts>();		//.businessHostData
	
	private HardwareList hwHost;
	private List<HardwareList> addHostList = new ArrayList<HardwareList>();

	static RunsOnHost RunsOnHost = new RunsOnHost();	

	// constructor
	public BusinessAttribution() {
		System.out.println("*** in BusinessAttribution constructor ***");
	}
	
	@PostConstruct
	public void init() {
		System.out.println("*** in BusinessAttribution init ***");
		//initialize variables
		// get the Database Query Service object for this Organization
		databaseQueryService = SessionUtils.getOrgDBQueryService(currentUser.getOrgKeyspace());
		session = SessionUtils.getOrgDBSession(currentUser.getOrgKeyspace());

		//TODO: NOT USING auditUpsert class now, should we??
		MappingManager manager = new MappingManager(session);
		manager.udtCodec(Audit_Upsert.class);
		auditUpsert.setChangedbyusername(currentUser.getFirstName().toLowerCase()+"."+currentUser.getLastName().toLowerCase());
		manager.udtCodec(RunsOnHost.class);
		
		//pull data for initial table load		
		LoadBPTableData();

	} // end of init()
	
	////LOAD functions for each BP page
	private void LoadBPTableData() {
		System.out.println("in LoadBPTableData");
		// this is the code for the business attribution table 
		query = "select site_id, business_process_id, business_process_name, business_interruption_threshold, business_criticality, information_classification, "
				+ "default_breach_type, annual_revenue, annual_revenue_year, runs_on_hosts, risk_appetite, record_count, resistance_strength from business_value_attribution";
		rs = databaseQueryService.runQuery(query);
		
		// clear out any old content before retrieving new database info
		attList.clear();
		iterator = rs.iterator();

		//iterate over the results. 
		while (iterator.hasNext()) {
			row = iterator.next();
			siteID = row.getUUID("site_id");
			busProcID = row.getUUID("business_process_id");
			busName = row.getString("business_process_name");
			bit = row.getString("business_interruption_threshold");
			busCrit = row.getString("business_criticality");
			infClass = row.getString("information_classification");
			breachType = row.getString("default_breach_type");
			annRev = row.getDecimal("annual_revenue");
			int annRevYear = row.getInt("annual_revenue_year");
			recordCount = row.getDecimal("record_count");
			resistanceStrength =  row.getDecimal("resistance_strength");
			riskAppetite = row.getDecimal("risk_appetite");
				
			att = new BusinessAttributionTypes(siteID, busProcID, busName, bit, busCrit, infClass, breachType, annRev, annRevYear, recordCount, resistanceStrength, riskAppetite);
			// Save each database record to the list.
			attList.add(att);
		}	//end of while
	}	// end of LoadTableData()

	private void LoadDropDowns() {
		// get List of all possible business process values
		query="";
		query = "select database_column, option_values from lov_references where database_column in('business_interruption_threshold', 'business_criticality', 'breach_type', 'information_classification')";
		rs = databaseQueryService.runQuery(query);
		iterator = rs.iterator();
				
		//iterate over the results. 
		while (iterator.hasNext()) {
			Row row = iterator.next();
			String dbColumn = row.getString("database_column");
			switch(dbColumn) {
			case "business_interruption_threshold" :
				Map<String, String> bitMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.bits = new ArrayList<String>(bitMap.values());
				break;
			case "business_criticality" :
				Map<String, String> critMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.bcrits = new ArrayList<String>(critMap.values());
				break;
			case "breach_type" :
				Map<String, String> typeMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.dbtypes = new ArrayList<String>(typeMap.values());
				break;				
			case "information_classification" :
				Map<String, String> iclassMap = (Map<String, String>) row.getMap("option_values", String.class, String.class);	
				this.iclass = new ArrayList<String>(iclassMap.values());							
				break;
			}
		}	//end while

	}	//end LoadDropDowns()

	public String LoadBPEditFormData(UUID selectedSiteID, UUID selectedBusinessID) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		// get the data for the business process being edited
		if (this.getBcrits()==null) {
			LoadDropDowns();
		}
		
			this.selSiteID = selectedSiteID;
			this.selBusProcID = selectedBusinessID;
			
			query = "SELECT "
				+ "business_process_name, "
				+ "business_interruption_threshold, "
				+ "business_criticality, information_classification, "  
				+ "default_breach_type, "
				+ "annual_revenue, "
				+ "annual_revenue_year, "
				+ "runs_on_hosts, "
				+ "risk_appetite, "
				+ "record_count, "
				+ "resistance_strength " 
				+ "from business_value_attribution where site_id=? and business_process_id=?";	

		prepared = session.prepare(query);
		bound = prepared.bind(selectedSiteID, selectedBusinessID);			
		resultset = session.execute(bound);
		row = resultset.one();
		
		// set values to what was returned by the query
		selectedBusName = row.getString("business_process_name");		
		bit = row.getString("business_interruption_threshold");
		busCrit = row.getString("business_criticality");
		infClass = row.getString("information_classification");
		riskAppetite = row.getDecimal("risk_appetite");
		dbType = row.getString("default_breach_type");
		annRevC = row.getDecimal("annual_revenue").toString();
		annRevYear = row.getInt("annual_revenue_year");
		recordCount  = row.getDecimal("record_count");
		resistanceStrength = row.getDecimal("resistance_strength");
		if (resistanceStrength != null) {
			resistanceStrengthstr = resistanceStrength.toString();		//needed for regex validation
		}
		String ret = LoadHostTableData(selectedSiteID, selectedBusinessID);
		System.out.println("in LoadEditFormData, ret: " + ret);
		//goto Edit form
		return "/administrator/business-attribution-edit.jsf";
	}	//end of LoadEditFormData()
	
	public String LoadBPAddFormData()  throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		// get the data for the business process being edited
		if (this.getBcrits()==null) {
			LoadDropDowns();
		}
		this.selBusProcID= null;
		this.selectedBusName = "";
		this.bit = "";
		this.infClass="";
		this.riskAppetite = null;
		this.dbType = "";
		this.annRevC = "";
		this.annRevYear=2017;
		this.recordCount= null;
		this.resistanceStrength=null;
		this.resistanceStrengthstr = "";		//needed for regex validation
		//goto add form
		return "/administrator/business-attribution-add.jsf";

	}	//end of LoadAddFormData()
	////end of LOAD functions for each BP  page

	//LOAD functions for each Host  page
	public String LoadHostTableData(UUID selectedSiteID, UUID selectedBusinessID) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {	
		System.out.println("In LoadHostTableData");
		//set selected variables for bean
		this.selSiteID = selectedSiteID;
		this.selBusProcID = selectedBusinessID;
		// get List of all possible hosts for this business
			query = "SELECT "
					+ "business_process_name, runs_on_hosts "
					+ "from business_value_attribution where site_id=? and business_process_id=?";

			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound = prepared.bind(selectedSiteID, selectedBusinessID);			
			ResultSet resultset = session.execute(bound);
			row = resultset.one();
			selectedBusName = row.getString("business_process_name");
			setROH = row.getSet("runs_on_hosts", RunsOnHost.class);

			populateHostListData(setROH);

			//goto Host List form
			return "/administrator/business-attribution-hosts.jsf";
	} //end loadHostTableData()
	
	public String LoadHostTableDataforEdit(UUID selectedSiteID, UUID selectedBusinessID) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {	
		System.out.println("In LoadHostTableData");
		//set selected variables for bean
		this.selSiteID = selectedSiteID;
		this.selBusProcID = selectedBusinessID;
		// get List of all possible hosts for this business
			query = "SELECT "
					+ "business_process_name, runs_on_hosts "
					+ "from business_value_attribution where site_id=? and business_process_id=?";

			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound = prepared.bind(selectedSiteID, selectedBusinessID);			
			ResultSet resultset = session.execute(bound);
			row = resultset.one();
			selectedBusName = row.getString("business_process_name");
			setROH = row.getSet("runs_on_hosts", RunsOnHost.class);

			populateHostListData(setROH);

			return null;

	} //end LoadHostTableDataforEdit()

	
	public String LoadHostAddData() {
	if (loadHostAddRun) {
		System.out.println("in LoadHostAddData loadHostAddRun=true");	
		//return null;
	}
	//Initialize all areas
		addHostList.clear();
		siteID = null;
		
		//clear out host values
		this.hostName  = "";
		this.hostSiteID  = null;
		this.hostSubnet  = "";
		this.hostAssetType  = "";
		this.hostAssetVisibility  = "";
		this.hostIP  = "";
		this.hostOS  = "";
		this.hostVendor  = "";
		
		//load drop-down for site
		SelectItem siSite = new SelectItem();
		query = "select distinct site_id from hardware";
		resultSet = databaseQueryService.runQuery(query);
		itSites = resultSet.iterator();
		newQuery = "select site_name from sites where org_unit_id=703161c5-eca3-48a0-8ad9-99f2a6b8d5e7 and site_id=?";
		prepared = session.prepare(newQuery);
		while (itSites.hasNext()) {
			rowSites = itSites.next();			
			siteID = rowSites.getUUID("site_id");
			BoundStatement boundSN = prepared.bind(siteID);			
			ResultSet rsSN = session.execute(boundSN);
			Row rowSN = rsSN.one();
			String siteName = rowSN.getString("site_name");
			siSite.setLabel(siteName);
			siSite.setValue(siteID);	
			this.siteList.add(siSite);			
			if (siteID.equals(this.selSiteID)) {
				this.defaultSiteName = siteName;
			}
		}	//end while
		//populate subnet with selected siteID
		this.hostSiteID = siteID;
		populateSubnets(siteID);
		loadHostAddRun = true;

		//goto Add form
		return "/administrator/business-attribution-host-add.jsf";
	}	//end LoadHostAddData

	public String LoadHostAddDataforEdit() {
	//Initialize all areas
		addHostList.clear();
		siteID = null;
		
		//clear out host values
		this.hostName  = "";
		this.hostSiteID  = null;
		this.hostSubnet  = "";
		this.hostAssetType  = "";
		this.hostAssetVisibility  = "";
		this.hostIP  = "";
		this.hostOS  = "";
		this.hostVendor  = "";
		
		//load drop-down for site
		SelectItem siSite = new SelectItem();
		query = "select distinct site_id from hardware";
		resultSet = databaseQueryService.runQuery(query);
		itSites = resultSet.iterator();
		newQuery = "select site_name from sites where org_unit_id=703161c5-eca3-48a0-8ad9-99f2a6b8d5e7 and site_id=?";
		prepared = session.prepare(newQuery);
		while (itSites.hasNext()) {
			rowSites = itSites.next();			
			siteID = rowSites.getUUID("site_id");
			BoundStatement boundSN = prepared.bind(siteID);			
			ResultSet rsSN = session.execute(boundSN);
			Row rowSN = rsSN.one();
			String siteName = rowSN.getString("site_name");
			siSite.setLabel(siteName);
			siSite.setValue(siteID);	
			this.siteList.add(siSite);			
			if (siteID.equals(this.selSiteID)) {
				this.defaultSiteName = siteName;
			}
		}	//end while
		//populate subnet with selected siteID
		this.hostSiteID = siteID;
		populateSubnets(siteID);
		loadHostAddRun = true;
		return null;
	}	//end LoadHostAddDataforEdit
	
	public void listenSite(ValueChangeEvent event) {
		Object newValue = event.getNewValue();
		this.hostSiteID = UUID.fromString(newValue.toString());
		populateSubnets(this.hostSiteID);
	}
	
	public void populateSubnets(UUID hSiteID) {
		this.subnetList.clear();
		query = "select ip_subnet_or_building from hardware where site_id=?"; 
		prepared = session.prepare(query);
		bound = prepared.bind(hSiteID);			
		rs = session.execute(bound);
		Iterator<Row> itSubnets = rs.iterator();
		Row rowSubnets;
		String defaultSubnet = "";
		String subNet = "";
		while (itSubnets.hasNext()) {
			rowSubnets = itSubnets.next();
			subNet = rowSubnets.getString("ip_subnet_or_building");
			if (defaultSubnet.isEmpty()) {
				defaultSubnet = subNet;
			}
			this.subnetList.add(subNet);
		}	//end while
		//populate hardware list with default site and subnet
		this.hostSubnet = subNet;
		populateHardwareHosts(subNet);
	}	//end populateSubnets()
	
	public void listenHardware(ValueChangeEvent event) {
		Object newValue = event.getNewValue();
		this.hostSubnet = newValue.toString();
		populateHardwareHosts(this.hostSubnet);
	}	//loadHardwareHosts
	
	private void populateHardwareHosts(String selSubNet) {
		addHostList.clear();
		//BusinessHosts
		query = "select host_name, ip_address, asset_type, asset_visibility, operating_system, vendor, internal_system_id " + 
				"from hardware where site_id=? and ip_subnet_or_building=?"; 
		prepared = session.prepare(query);
		bound = prepared.bind(hostSiteID, selSubNet);			
		rs = session.execute(bound);
		Iterator<Row> itHW = rs.iterator();
		while (itHW.hasNext()) {
			row = itHW.next();
			if (row != null) {
				if (row.getString("host_name") != null) {
					hostName = row.getString("host_name");
				}
				if (row.getString("ip_address") != null) {
					hostIP = row.getString("ip_address");
				}
				if (row.getString("asset_type") != null) {
					hostAssetType = row.getString("asset_type");
				}
				if (row.getString("asset_visibility") != null) {
					hostAssetVisibility = row.getString("asset_visibility");
				}
				if (row.getString("operating_system") != null) {
					hostOS = row.getString("operating_system");
				}
				if (row.getString("vendor") != null) {
					hostVendor = row.getString("vendor");
				}
				if (row.getUUID("internal_system_id") != null) {
					hostID = row.getUUID("internal_system_id");
				}
				hwHost = new HardwareList(hostSiteID, hostSubnet, hostID, hostName, hostIP, hostAssetType, hostAssetVisibility, hostOS, hostVendor);				
				addHostList.add(hwHost);	// Save each database record to the list.
			}	//end if row is null		
		}	//end while itHW
		
		// remove runs_on_host items from addHostList		
		Iterator<HardwareList> itHWL = addHostList.iterator();
		while (itHWL.hasNext()) {
			hwHost = itHWL.next();
			UUID hID = hwHost.getHostID();
			UUID hSID = hwHost.getHostSiteID();
			String hSN = hwHost.getHostSubnet().trim().toLowerCase();
			Iterator<BusinessHosts> itROH = hostList.iterator();
			while(itROH.hasNext()) {
				host = itROH.next();
				UUID hostID = host.getHostID();
				UUID hostSID = host.getHostSiteID();
				String hostSN = host.getHostSubnet().trim().toLowerCase();
				if (hostID.equals(hID) && hostSID.equals(hSID) && hostSN.equals(hSN)) {
					//remove from addHostList
					itHWL.remove();
				}	// end if
			}	// end while itroh
		}	// end ithwl while
	}	// end populateHardwareHosts()

	////EDIT functions for each BP page
	
	public String editBVAControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
			//convert Annual Revenue from currency format ($ 650,005.00) to decimal
		try {
				annRev = parse(annRevC, Locale.US);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		if (this.getResistanceStrengthstr().isEmpty()) {
			this.resistanceStrength = null;
		} else {
			this.resistanceStrength = new BigDecimal(this.getResistanceStrengthstr().trim());
		}

		query = "UPDATE business_value_attribution SET "
					+ "business_process_name=?, "
					+ "business_interruption_threshold=?, "
					+ "business_criticality=?, "
					+ "information_classification=?, " 
					+ "risk_appetite=?, "
					+ "default_breach_type=?, "  
					+ "annual_revenue=?, " 
					+ "annual_revenue_year=?, " 
					+ "record_count=?, "  
					+ "resistance_strength=?, "
					+ "audit_upsert= {datechanged: toUnixTimestamp(now()), changedbyusername: '" + auditUpsert.getChangedbyusername() + "'} "
					+ "where site_id=? and business_process_id= ?";
			
			
			prepared = session.prepare(query);
			bound = prepared.bind(this.selectedBusName, this.getBit(), this.getBusCrit(),this.getInfClass(), this.getRiskAppetite(),
					this.getDbType(), this.getAnnRev(), this.getAnnRevYear(), this.getRecordCount(), this.resistanceStrength, this.selSiteID,this.selBusProcID);			
			session.execute(bound);

		//refresh table data
		LoadBPTableData();
		// go back to the Business Attribution Table
		return "business-attribution-table";
	}	//end of editBVAControllerMethod()
	

	public String addBVAControllerMethod() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		//convert Annual Revenue from currency format ($ 650,005.00) to decimal
		try {
			this.annRev = parse(this.getAnnRevC(), Locale.US);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (this.getResistanceStrengthstr().isEmpty()) {
			this.resistanceStrength = null;
		} else {
			this.resistanceStrength = new BigDecimal(this.getResistanceStrengthstr().trim());
		}

		query="";
		query = "INSERT INTO business_value_attribution"  
				+ "(site_id, "
				+ "business_process_id, "
				+ "annual_revenue, "
				+ "annual_revenue_year, "
				+ "business_criticality, "
				+ "business_interruption_threshold, "
				+ "business_process_name, "
				+ "default_breach_type, " 
				+ "information_classification, "
				+ "record_count, "
				+ "risk_appetite, "
				+ "resistance_strength, "
				+ "audit_upsert) "  
				+ "VALUES(?,uuid(),?,?,?,?,?,?,?,?,?,?,{datechanged: toUnixTimestamp(now()), changedbyusername: '" + auditUpsert.getChangedbyusername() + "'})";
		PreparedStatement prepared = session.prepare(query);
		BoundStatement bound = prepared.bind(this.getSiteID(),this.getAnnRev(), this.getAnnRevYear(), this.getBusCrit(), this.getBit(), 
				this.getSelectedBusName(), this.getDbType(), this.getInfClass(), this.getRecordCount(), this.getRiskAppetite(), this.resistanceStrength);
		session.execute(bound);
		
		//refresh table data
		LoadBPTableData();
		// go back to the Business Attribution Table
		return "business-attribution-table";
	}	//end addBVAControllerMethod()


	public String deleteBVAControllerMethod(BusinessAttributionTypes att) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		UUID selectedBusinessID = att.getbusProcID();
		UUID selectedSiteID = att.getsiteID();
		
			query = "DELETE FROM business_value_attribution "
					+ "WHERE site_id= ? and business_process_id=? ";
			
			PreparedStatement prepared = session.prepare(query);
			BoundStatement bound = prepared.bind(selectedSiteID, selectedBusinessID);
			session.execute(bound);
	
			//update table
			this.attList.remove(att);
			System.out.println("Business Process " + att.getBusName() +" was deleted.");		
	
		return null;

	}	//end deleteBVAControllerMethod
	
	////end of EDIT functions for each BP page
	
	////EDIT functions for each Host page
	public String addBVAHostControllerMethod(UUID selHostID) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		this.hostID = selHostID;
		//only adding hardware node to the Runs_On_Hosts field in the business_value_attribution table
		//grab all hosts for this business process
		query = "select runs_on_hosts from business_value_attribution "
					+ "where site_id=? and business_process_id=?";
			prepared = session.prepare(query);
			bound = prepared.bind(this.getSelSiteID(), this.getSelBusProcID());			
			rs = session.execute(bound);
			row = rs.one();
			Set<RunsOnHost> setRunsOnHost = row.getSet("runs_on_hosts", RunsOnHost.class);
			
			RunsOnHost hostNode = new RunsOnHost();
			hostNode.setInternalSystemID(selHostID);
			hostNode.setIpSubnet(this.getHostSubnet());
			hostNode.setSiteID(this.getHostSiteID());
			//add new host to set
			setRunsOnHost.add(hostNode);
			
			query = "UPDATE business_value_attribution set "
						+	"runs_on_hosts = ?, "
						+	"audit_upsert={datechanged: toUnixTimestamp(now()), changedbyusername: '" + auditUpsert.getChangedbyusername() + "'} "
						+	"where site_id= ? and business_process_id=? ";

			prepared = session.prepare(query);
			bound = prepared.bind(setRunsOnHost, this.getSelSiteID(),this.getSelBusProcID());
			session.execute(bound);
	
			// refresh host list
			LoadHostTableData(this.getSelSiteID(),this.getSelBusProcID());
			// go back to the Business Attribution page
			return "business-attribution-hosts.jsf";
	}	// end of addBVAController method
	
	public String addBVAHostControllerMethodinEdit(UUID selHostID) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		System.out.println("in addBVAHost, selhostID: " + selHostID);
		this.hostID = selHostID;
		//only adding hardware node to the Runs_On_Hosts field in the business_value_attribution table
		//grab all hosts for this business process
		query = "select runs_on_hosts from business_value_attribution "
					+ "where site_id=? and business_process_id=?";
			prepared = session.prepare(query);
			bound = prepared.bind(this.getSelSiteID(), this.getSelBusProcID());			
			rs = session.execute(bound);
			row = rs.one();
			Set<RunsOnHost> setRunsOnHost = row.getSet("runs_on_hosts", RunsOnHost.class);
			
			RunsOnHost hostNode = new RunsOnHost();
			hostNode.setInternalSystemID(selHostID);
			hostNode.setIpSubnet(this.getHostSubnet());
			hostNode.setSiteID(this.getHostSiteID());
			//add new host to set
			setRunsOnHost.add(hostNode);
			
			query = "UPDATE business_value_attribution set "
						+	"runs_on_hosts = ?, "
						+	"audit_upsert={datechanged: toUnixTimestamp(now()), changedbyusername: '" + auditUpsert.getChangedbyusername() + "'} "
						+	"where site_id= ? and business_process_id=? ";

			prepared = session.prepare(query);
			bound = prepared.bind(setRunsOnHost, this.getSelSiteID(),this.getSelBusProcID());
			session.execute(bound);
	
			// refresh host list
			LoadHostTableData(this.getSelSiteID(),this.getSelBusProcID());
			return null;
	}	// end of addBVAController method

	public String deleteBVAHostControllerMethodModal() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		if (selectedHost != null) {
			String delMsg = deleteBVAHostControllerMethod(selectedHost);
			System.out.println("delMsg: " + delMsg);
		}
		return null;
	}
		
	public String deleteBVAHostControllerMethod(BusinessHosts host) throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
		if (host == null) {			
			return "nohost_available";
		}
		
		String successMessage = null;
		UUID selhostID = host.getHostID();
		
		//grab all hosts for this business process
			String queryHosts = "SELECT runs_on_hosts FROM business_value_attribution "
					+ "WHERE site_id=? and business_process_id=?";

			prepared = session.prepare(queryHosts);
			bound = prepared.bind(this.siteID, this.selBusProcID);			
			ResultSet resultset = session.execute(bound);
			Row row = resultset.one();
			
			Set<RunsOnHost> setRunsOnHost = row.getSet("runs_on_hosts", RunsOnHost.class);
			boolean deleted = false;
		
			for (RunsOnHost roh : setRunsOnHost) {
				if (roh.getInternalSystemID().equals(selhostID)) {
					setRunsOnHost.remove(roh);
					deleted = true;
					break;	
				}
			}
			//Remove host from set
			if (deleted==true) {
				//update business_value_attribution.runsonhosts field
				String queryUpdate = "UPDATE business_value_attribution SET "
							+	"runs_on_hosts= ?, "
							+	"audit_upsert={datechanged: toUnixTimestamp(now()), changedbyusername: '" + auditUpsert.getChangedbyusername() + "'} "
							+	"where site_id=? and business_process_id=?";
				
				prepared = session.prepare(queryUpdate);
				bound = prepared.bind(setRunsOnHost, this.siteID, this.selBusProcID);
				session.execute(bound);
				// refresh host list
				this.hostList.remove(host);
				successMessage = "Host, " + this.selectedHostName + " removed from business process " + this.selectedBusName + "successfully.";
			} else {
				successMessage = "Not able to remove hostnode, " + this.selectedHostName + " from business process " + this.selectedBusName;
			}	//end if deleted			

			System.out.println(successMessage);
			return successMessage;
	}	//end delete

	public String defineSelectedHost(BusinessHosts selHost) {
		this.selectedHost = selHost;
		this.selectedHostName = selHost.getHostName();
		System.out.println("in defineSelectedHost, selHost: " + selectedHostName); 
		return null;
	}

	public void	populateHostListData(Set<RunsOnHost> setRunsOnHost) {
		// clear list data
		this.hostList.clear();
		//run hosts through hardware table to get details for list data
		Iterator<RunsOnHost> itROH = setRunsOnHost.iterator();
		RunsOnHost hostNode = new RunsOnHost();
		query = "SELECT "
				+ "asset_type, "
				+ "asset_visibility, "
				+ "host_name, "
				+ "ip_address, "
				+ "operating_system, "
				+ "vendor "  
				+ "from hardware where site_id=? and ip_subnet_or_building=? and internal_system_id=?";  
		prepared = session.prepare(query);

		while(itROH.hasNext()) {
			hostNode = itROH.next();
			hostID = hostNode.getInternalSystemID();
			hostSubnet = hostNode.getIpSubnet();
			hostSiteID = hostNode.getSiteID();
			bound = prepared.bind(hostSiteID, hostSubnet, hostID);			
			rs = session.execute(bound);
			row = rs.one();
			if (row != null) {
				if (row.getString("asset_type") != null) {
					hostAssetType = row.getString("asset_type");
				}
				if (row.getString("asset_visibility") != null) {
					hostAssetVisibility = row.getString("asset_visibility");
				}
				if (row.getString("host_name") != null) {
					hostName = row.getString("host_name");
				}
				if (row.getString("ip_address") != null) {
					hostIP = row.getString("ip_address");
				}
				if (row.getString("operating_system") != null) {
					hostOS = row.getString("operating_system");
				}
				if (row.getString("vendor") != null) {
					hostVendor = row.getString("vendor");
				}
				host = new BusinessHosts(hostSiteID, hostSubnet, hostID, selBusProcID, hostName, hostIP, hostAssetType, hostAssetVisibility, hostOS, hostVendor);
				
				hostList.add(host);	// Save each database record to the list.
			}	//end if
		}   //end while		
	}  //end poulateHostList
	
	////end of EDIT functions for each BP page

	// function for logging error messages
	public void updateLogTable(String payload, String severity)
	{
		try {
			String eventMask = 	"Stored format is: yyy-mm-dd hh:mm:ss GMT";
			ZonedDateTime now = ZonedDateTime.now( ZoneOffset.UTC );


			String qs = "insert into application_logging (app_module_id, event_ts_mask, event_datetime, payload, severity_r) VALUES (uuid(), '" + eventMask + "', '" + now + 
					"', '" + payload + "', '" + severity +"')";
			prepared = session.prepare(qs);
			bound = prepared.bind();			
			session.execute(bound);
		} catch (Exception e)
		{
			System.out.println("error updating application_logging: " + e.toString());
		}

	    /*  ex. code to call this function
	     * } catch (Exception e) {
			//e.printStackTrace();			
			String dbMsg = "error updating busines_value_attribution: " + e.toString();
			String dbMsg2 = updateLogTable(dbMsg,"Error");
	     */

	}	//end updateLogTable()
	
	public static BigDecimal parse(final String amount, final Locale locale) throws ParseException {
	    final NumberFormat format = NumberFormat.getNumberInstance(locale);
	    if (format instanceof DecimalFormat) {
	        ((DecimalFormat) format).setParseBigDecimal(true);
	    }
	    return (BigDecimal) format.parse(amount.replaceAll("[^\\d.,]",""));    
	}


	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Getters/Setters<<<<<<<<<<<<<<<<<<<<<<<<//
	public List<BusinessAttributionTypes> getBusinessAttData() {
		return attList;
	}
	
	public BusinessAttributionTypes getAtt() {
		return att;
	}

	public void setAtt(BusinessAttributionTypes att) {
		this.att = att;
	}

	public List<BusinessHosts> getBusinessHostData() {
		return hostList;
	}
	
	public BusinessHosts getHost() {
		return host;
	}

	public void setHost(BusinessHosts host) {
		this.host = host;
	}

	public HardwareList getHwHost() {
		return hwHost;
	}

	public void setHwHost(HardwareList hwHost) {
		this.hwHost = hwHost;
	}

	public List<HardwareList> getAddHostList() {
		return addHostList;
	}

	public void setAddHostList(List<HardwareList> addHostList) {
		this.addHostList = addHostList;
	}

	public UserBean getCurrentUser() {
		return currentUser;
	}

	public UUID getSiteID() {
		return siteID;
	}

	public void setSiteID(UUID siteID) {
		this.siteID = siteID;
	}

	public UUID getSelSiteID() {
		return selSiteID;
	}

	public void setSelSiteID(UUID selSiteID) {
		this.selSiteID = selSiteID;
	}

	public UUID getBusProcID() {
		return busProcID;
	}

	public void setBusProcID(UUID busProcID) {
		this.busProcID = busProcID;
	}

	public UUID getSelBusProcID() {
		return selBusProcID;
	}

	public void setSelBusProcID(UUID selectedBusProcID) {
		this.selBusProcID = selectedBusProcID;
	}

	public String getSelectedBusName() {
		return selectedBusName;
	}

	public void setSelectedBusName(String selectedBusName) {
		this.selectedBusName = selectedBusName;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBit() {
		return bit;
	}

	public void setBit(String bit) {
		this.bit = bit;
	}

	public String getBusCrit() {
		return busCrit;
	}

	public void setBusCrit(String busCrit) {
		this.busCrit = busCrit;
	}

	public String getInfClass() {
		return infClass;
	}

	public void setInfClass(String infClass) {
		this.infClass = infClass;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getAnnRevC() {
		return annRevC;
	}

	public void setAnnRevC(String annRevC) {
		this.annRevC = annRevC;
	}

	public BigDecimal getRiskAppetite() {
		return riskAppetite;
	}

	public void setRiskAppetite(BigDecimal riskAppetite) {
		this.riskAppetite = riskAppetite;
	}

	public BigDecimal getAnnRev() {
		return annRev;
	}

	public void setAnnRev(BigDecimal annRev) {
		this.annRev = annRev;
	}

	public BigDecimal getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(BigDecimal recordCount) {
		this.recordCount = recordCount;
	}

	public int getAnnRevYear() {
		return annRevYear;
	}

	public void setAnnRevYear(int annRevYear) {
		this.annRevYear = annRevYear;
	}

	public List<String> getBits() {
		return bits;
	}

	public void setBits(List<String> bits) {
		this.bits = bits;
	}

	public List<String> getBcrits() {
		return bcrits;
	}

	public void setBcrits(List<String> bcrits) {
		this.bcrits = bcrits;
	}

	public List<String> getIclass() {
		return iclass;
	}

	public void setIclass(List<String> iclass) {
		this.iclass = iclass;
	}

	public List<String> getDbtypes() {
		return dbtypes;
	}

	public void setDbtypes(List<String> dbtypes) {
		this.dbtypes = dbtypes;
	}


	public String getBreachType() {
		return breachType;
	}

	public void setBreachType(String breachType) {
		this.breachType = breachType;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getHostSubnet() {
		return hostSubnet;
	}

	public void setHostSubnet(String hostSubnet) {
		this.hostSubnet = hostSubnet;
	}

	
	public String getResistanceStrengthstr() {
		return resistanceStrengthstr;
	}

	public void setResistanceStrengthstr(String resistanceStrengthstr) {
		this.resistanceStrengthstr = resistanceStrengthstr;
	}

	public String getHostAssetType() {
		return hostAssetType;
	}

	public void setHostAssetType(String hostAssetType) {
		this.hostAssetType = hostAssetType;
	}

	public String getHostAssetVisibility() {
		return hostAssetVisibility;
	}

	public void setHostAssetVisibility(String hostAssetVisibility) {
		this.hostAssetVisibility = hostAssetVisibility;
	}

	public String getHostOS() {
		return hostOS;
	}

	public void setHostOS(String hostOS) {
		this.hostOS = hostOS;
	}

	public String getHostVendor() {
		return hostVendor;
	}

	public void setHostVendor(String hostVendor) {
		this.hostVendor = hostVendor;
	}

	public UUID getHostSiteID() {
		return hostSiteID;
	}

	public void setHostSiteID(UUID hostSiteID) {
		this.hostSiteID = hostSiteID;
	}

	public UUID getHostID() {
		return hostID;
	}

	public void setHostID(UUID hostID) {
		this.hostID = hostID;
	}

	public List<SelectItem> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SelectItem> siteList) {
		this.siteList = siteList;
	}

	public Set<String> getSubnetList() {
		return subnetList;
	}

	public void setSubnetList(Set<String> subnetList) {
		this.subnetList = subnetList;
	}

	public String getDefaultSiteName() {
		return defaultSiteName;
	}

	public void setDefaultSiteName(String defaultSiteName) {
		this.defaultSiteName = defaultSiteName;
	}

	public BigDecimal getResistanceStrength() {
		return resistanceStrength;
	}

	public void setResistanceStrength(BigDecimal resistanceStrength) {
		this.resistanceStrength = resistanceStrength;
	}
	
	public String getSelectedHostName() {
		return selectedHostName;
	}
	
	public void setSelectedHostName(String selectedHostName) {
		this.selectedHostName = selectedHostName;
	}
	
	public BusinessHosts getSelectedHost() {
		return selectedHost;
	}

	public void setSelectedHost(BusinessHosts selectedHost) {
		System.out.println("in setSelectedHost");
		this.selectedHost = selectedHost;
	}	
}
                                                                                      
 
