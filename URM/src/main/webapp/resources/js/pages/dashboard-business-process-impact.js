function businessProcessesByCyVaRChartReset() {

	// redraw the table with all of its rows (since no business process is selected at this point)
	table.columns( 0 ).search( '' ).draw();

	// set the h3 tag above the table
	$("#businessProcessNameID").html("All");
	
};


function businessProcessesByCyVaRChartClicked(businessProcessName) {

	// create regex to use for search
	businessProcessNameRegex = "^" + businessProcessName + "$";

	// filter the first column of the details table by the business process name
	table.columns( 0 ).search( businessProcessNameRegex, true ).draw();

	// set the h3 tag above the table
	$("#businessProcessNameID").html(businessProcessName);
	
};


// when the document is loaded in the browser
$( document ).ready(function() {

	// create an object of the details table
	table = $('.businessProcessesByCyvarTableClass').DataTable( {retrieve: true} );

	// redraw the table with all of its rows (since no business process is selected at this point)
	table.columns( 0 ).search( '' ).draw();

});
