USE dod;

CREATE TYPE IF NOT EXISTS connected_elements(
    destination_id 		text,		// ip address or magic_draw_id associated with the connected hardware (destination)
    mission_support		list<text>	// name of the mission support element
);