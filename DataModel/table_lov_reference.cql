CREATE TABLE IF NOT EXISTS g2.lov_references (
  org_id 			uuid,		// must be in the organizations table
  reference_key 	text,		// lowercase: should match the name of the database column in an associated table
  reference_value 	list<frozen <reference_values>>,
  PRIMARY KEY ((org_id), reference_key)
)
WITH COMMENT='Table contains the global list of values to be used for validation or drop down lists.';
