CREATE TABLE IF NOT EXISTS g2.mbse_staging (
    org_id 					uuid,
    site_or_ou_name 		text,
    building 				text,
    magic_draw_id 			text,
    ip_address 				text,
    name 					text,
    part 					text, 	// from CPE. (a)pplication, (h)ardware, (o)perating system
    vendor 					text,
    product 				text,
    version 				text,
    version_update 			text,
    os_vendor 				text,
    os_product 				text,
    os_version 				text,
    os_version_update 		text,
    firmware_vendor 		text,
    firmware_product 		text,
    firmware_version 		text,
    firmware_version_update text,
    product_subsystem 		text, 		// Line of business
    connected_elements 	list <frozen<connected_elements>>, // magic_draw_id of (from, to)
    PRIMARY KEY ((org_id,site_or_ou_name,building),magic_draw_id)
) WITH COMMENT = 'Staging data from export of SysML data.';