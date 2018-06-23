/**
Application menus are data-driven and created based on data in this table.

Modifications
06-22-2018		corren.mccoy	Insert statements as placeholders for modules in development	
*/

CREATE TABLE IF NOT EXISTS application_modules (
	menu_name			text,
	module_name			text,
	file_name			text,
	role_name_r			list<text>,		// references.role_name_r = "admin", "user"
	module_description	text,	
	menu_module_order	int,
    PRIMARY KEY ((menu_name), module_name)
) 
WITH COMMENT='Manages application modules that are available to each organization based upon their industry and the user based on their role.';

//
INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Administrator','Business Processes','business-attribution-table.jsf',1,'table of business processes',['admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Administrator','Node Metrics','node-metrics-table.jsf',2,'Table and data entry form',['admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Administrator','Users','users-table.jsf',3,'table of users',['admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'User','Change Passcode','passcode-edit.jsf',1,'Data entry form',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'User','Manage Settings','user-settings-form.jsf',2,'Data entry form',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Analytics','Business Process Impact Analysis','dashboard-business-process-impact.jsf',1,'visualizations from a business perspective',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Analytics','Operational Impact Analysis','dashboard-operational-impact.jsf',2,'visualizations from a technology perspective',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Analytics','Vulnerability Impact Analysis','vulnerability-impact-analysis.jsf',3,'Impact of CVE on the organization',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Analytics','Risk Buydown Scenarios','risk_buydown-scenarios.jsf',4,'Predefined what-if scenarios',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Analytics','Organization Security Controls','organization-security-controls.jsf',4,'Worksheet to determine resistance',['user', 'admin']);

INSERT INTO application_modules (menu_name,module_name,file_name,menu_module_order,module_description,role_name_r) VALUES (
'Analytics','Business Process Security Controls','business-process-security-controls.jsf',4,'Worksheet to determine resistance strength',['user', 'admin']);
