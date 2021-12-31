<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chart Page</title>

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
    
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        var data = google.visualization.arrayToDataTable([
          ['Month', 'Fonduri Totale', 'Fonduri Mastercard', 'Fonduri Visa Card'],
          ['Aug',  10105,              5380,                4725,            ],
          ['Sept', 13500,              8000,               5500,            ],
          ['Oct',  17850,              7850,             10000,            ],
          ['Nov',  22765,              11705,            11060,            ],
          ['Dec',  10570,              3570,              7000,            ]
        ]);

        var options = {
          colors:['#FA7805','#A70C0C','#0C1FA7'],
          title : 'Fondurile bancii/ luna',
          vAxis: {title: 'Euro'},
          hAxis: {title: 'Ultimele 5'},
          seriesType: 'bars',
          series: {5: {type: 'line'}}
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
</head>

<body>
<div class="charts">
<div id="piechart" style="width: 40%; height: 500px;"></div>

<div id="chart_div" style="width:70%;height: 463px;float: right;margin-top: -28%;">
</div>
</div>
</body>
</html>