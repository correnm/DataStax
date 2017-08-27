// OBSOLETE the message map is contained in the connected elements in mbse_staging (6/15/2017)
CREATE TABLE IF NOT EXISTS message_mapping_staging (
  magic_draw_id 		text,
  site 					text,
  mission_support 		text,
  connector 			text, 	// message origination component
  destination_id 		text,	// message destination component
  PRIMARY KEY ((magic_draw_id), destination_id)
) WITH COMMENT = 'Staging table for mission support messages showing the origination and destination components.';