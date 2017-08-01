USE keyspacename;

CREATE TABLE IF NOT EXISTS application_modules (
	industry_name_r 	text,		// references.industry_name = "private", "public"
	menu_name			text,
	module_name			text,
	file_name			text,
	role_name_r			list<text>,		// references.role_name_r = "admin", "user"
	module_description	text,	
    PRIMARY KEY ((industry_name_r, menu_name, module_name))
) 
WITH COMMENT='Manages application modules that are available to each organization based upon their industry and the user based on their role.';

INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'Dashboards', 'Business Process Impact Analysis', 'dashboard-slide-2.jsf', 'visualizations from a business perspective', ['user', 'admin']);

INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'Dashboards', 'Operational Impact Analysis', 'dashboard-slide-1a.jsf', 'visualizations from a technology perspective', ['user', 'admin']);

INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'Administrator', 'Business Value Attribution', 'business-attribution_table.jsf', 'Table and data entry form', ['admin']);


INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'Administrator', 'Manage Users', 'users-table.jsf', 'Table and data entry form', ['admin']);


INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'Administrator', 'Organization Settings', 'organization-settings-form.jsf', 'Table and data entry form', ['admin']);


INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'User', 'Manage Settings', 'user-settings-form.jsf', 'Data entry form', ['user','admin']);

INSERT INTO application_modules
(industry_name_r, menu_name, module_name, file_name, module_description, role_name_r)
VALUES('private', 'User', 'Change Passcode', 'change-passcode-form.jsf', 'Data entry form', ['user', 'admin']);