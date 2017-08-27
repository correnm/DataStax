USE dod;

CREATE TABLE IF NOT EXISTS lov_references (
  reference_key 	text,		// lowercase: should match the name of the database column in an associated table
  reference_value 	list<frozen <reference_values>>,
  PRIMARY KEY (reference_key)
)
WITH COMMENT='Table contains the global list of values to be used for validation or drop down lists.';
