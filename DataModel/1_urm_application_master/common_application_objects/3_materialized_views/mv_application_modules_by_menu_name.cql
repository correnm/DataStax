CREATE MATERIALIZED VIEW application_modules_by_menu_name
AS SELECT menu_name, module_name, file_name
FROM application_modules
WHERE menu_name IS NOT NULL AND module_Name IS NOT NULL AND file_name IS NOT NULL
PRIMARY KEY ((menu_name), module_name)
WITH COMMENT = 'query alternative for the application_modules table.';