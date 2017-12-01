USE keyspacename;

CREATE TABLE IF NOT EXISTS lov_references
database_column 	text,				// lowercase: should match the name of the database column in an associated table
optgroup_label		text,   			// <optgroup label="Swedish Cars">
option_values		map<text, text>,	// <option value="volvo">Volvo</option>
PRIMARY KEY (database_column)
)
WITH COMMENT='Table contains the global list of values to be used for validation or drop down lists.';
