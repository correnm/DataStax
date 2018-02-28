USE keyspacename;

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

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('Dashboards', 'Mission Process Impact Analysis', 'dashboard-slide-2.jsf', 'visualizations from a mission perspective', ['user', 'admin']);

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('Dashboards', 'Operational Impact Analysis', 'dashboard-slide-1a.jsf', 'visualizations from a technology perspective', ['user', 'admin']);

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('Administrator', 'Business Value Attribution', 'business-attribution_table.jsf', 'Table and data entry form', ['admin']);

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('Administrator', 'Manage Users', 'users-table.jsf', 'Table and data entry form', ['admin']);

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('Administrator', 'Organization Settings', 'organization-settings-form.jsf', 'Table and data entry form', ['admin']);

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('User', 'Manage Settings', 'user-settings-form.jsf', 'Data entry form', ['user','admin']);

INSERT INTO application_modules
(menu_name, module_name, file_name, module_description, role_name_r)
VALUES('User', 'Change Passcode', 'change-passcode-form.jsf', 'Data entry form', ['user', 'admin']);