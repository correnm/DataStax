function topologyNodeChartReset() {

	// redraw the table with all of its rows (since no node is selected at this point)
	table.columns( 0 ).search( '' ).draw();

	// get the Topology Chart object
	chartObj = FusionCharts("topologyMapID");

	// get the Topology Chart data in JSON format
	chartData = chartObj.getJSONData();

	// get the array of nodes
	dataArray = chartData['dataset'][0].data;

	// loop through array of nodes
	for (i = 0; i < dataArray.length; i++) {
		// set all to default color
		dataArray[i].color = "#ffffff";
	};

	// update the array of nodes
	chartData['dataset'][0].data = dataArray;

	// update the Topology Chart data (which redraws the chart)
	chartObj.setJSONData(chartData);

	// set the h3 tag above the table
	$("#nodeLabelID").html("All");
	
};


function topologyNodeChartNodeClicked(nodeID) {

	//console.log("nodeID: " + nodeID + " was clicked");

	// filter the first column of the details table by the node ID
	table.columns( 0 ).search( nodeID ).draw();

	// get the Topology Chart object
	chartObj = FusionCharts("topologyMapID");

	// get the Topology Chart data in JSON format
	chartData = chartObj.getJSONData();

	// get the array of nodes
	dataArray = chartData['dataset'][0].data;

	// loop through array of nodes
	for (i = 0; i < dataArray.length; i++) {
		if (dataArray[i].id == nodeID) {
			// highlight clicked node
			dataArray[i].color = "#efb73e";
			label = dataArray[i].label;
		} else {
			// set others to default color
			dataArray[i].color = "#ffffff";
		}
	};

	// update the array of nodes
	chartData['dataset'][0].data = dataArray;

	// update the Topology Chart data (which redraws the chart)
	chartObj.setJSONData(chartData);

	// set the h3 tag above the table
	$("#nodeLabelID").html(label);
	
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


// when the FusionCharts library is loaded in the browser
FusionCharts.ready(function () {
	
	// get the Topology Chart object
	chartObj = FusionCharts("topologyMapID");

	// add chartClick event listener
	chartObj.addEventListener("chartClick", topologyNodeChartReset);

});
