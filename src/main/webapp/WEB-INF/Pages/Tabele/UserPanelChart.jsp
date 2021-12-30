<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chart Page</title>
</head>
<body>
<div id="piechart"></div>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
  
  var data = google.visualization.arrayToDataTable([
  ['Task', 'Hours per Day'],
  ['Fonduri Mastercard', <%=request.getAttribute("totalFondsMaster")%>],
  ['Fonduri Visa Card', <%=request.getAttribute("totalFondsVisa")%>],
]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Total Fonduri Banca', 'width':550, 'height':400,
		  colors: ['#A70C0C', '#0C1FA7'],
		  pieSliceTextStyle: {
			  fontSize:15
	        },
	      titleTextStyle:{
	    	     
	        	fontSize:20
	        	
	        },
	      legend:{
	        	position: 'bottom', 
	        	textStyle: {color: 'black', fontSize: 16}
	        }

		  };

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>

</body>
</html>