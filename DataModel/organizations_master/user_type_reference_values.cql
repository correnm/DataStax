USE keyspacename;

CREATE TYPE reference_values (
optgroup				text,   			// <optgroup label="Swedish Cars">
option_values			map<text, text>,	// <option value="volvo">Volvo</option>
);