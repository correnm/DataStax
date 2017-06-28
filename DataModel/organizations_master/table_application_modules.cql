USE keyspacename;

CREATE TABLE IF NOT EXISTS application_modules (
	industry_name_r 	text,		// references.industry_name = "private", "public"
	module_name			text,
	menu_name			text,
	role_name_r			list<text>,		// references.role_name_r = "admin", "user"
	module_description	text,	
    PRIMARY KEY ((industry_name_r, module_name, menu_name))
) 
WITH COMMENT='Manages application modules that are available to each organization based upon their industry and the user based on their role.';