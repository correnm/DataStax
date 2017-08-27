function topologyNodeChartReset() {

	// redraw the table with all of its rows (since no node is selected at this point)
	table.columns( 0 ).search( '' ).draw();

	// set the h3 tag above the table
	$("#nodeLabelID").html("All");
	
};


function topologyNodeChartNodeClicked(nodeID) {

	//console.log("nodeID: " + nodeID + " was clicked");

	// filter the first column of the details table by the node ID
	table.columns( 0 ).search( nodeID ).draw();

	// set the h3 tag above the table
	$("#nodeLabelID").html(nodeID);
	
};


// when the document is loaded in the browser
$( document ).ready(function() {

	// create an object of the details table
	table = $('.businessProcessesByCyvarTableClass').DataTable( {retrieve: true} );

	// redraw the table with all of its rows (since no node is selected at this point)
	table.columns( 0 ).search( '' ).draw();

	// hide the first column of the table (so internal IDs are not displayed)
	table.columns( 0 ).visible( false );

});
