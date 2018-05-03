/**
Modifications
04-05-2018			sara.bergman		Modified/Renamed installed_software to software_installed list<text>
										Modified/renamed run_date to run_datetime timestamp
04-27-2018			sara.bergman		altered installed_software from list<text> to list<software>
										removed patch_availability text
*/

CREATE TABLE IF NOT EXISTS hardware_staging (
    site_id			 		uuid,
    ip_subnet_or_building 	text,
    internal_system_id 		uuid,
    connected_elements 		list<frozen<connected_elements>>,
    cpe_id 					text,
    cvss_scores 			list<frozen<cvss_scores>>,
    host_name 				text,
    run_datetime 			timestamp,
    installed_software 		list<software>,
    ip_address 				text,
    mac_address 			text,
    operating_system 		text,
    os_general 				text,
    system_type 			text,
    vendor 					text,
    vulnerability_count 	int,
	reportable_flag			boolean,
    PRIMARY KEY (site_id, ip_subnet_or_building, internal_system_id)
)  WITH COMMENT = 'Data from Nessus scans or SysML models are loaded here for review before porting to the hardware table for permanent storage';