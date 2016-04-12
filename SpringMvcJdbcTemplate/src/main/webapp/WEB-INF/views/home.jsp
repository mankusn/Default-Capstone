<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Team Default Boat Predictor</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Refugee Boat Predictor TEST PAGE NICHOLAS MANKS</h1>
	        <h2>Prediction for Zone 1: ${prediction}</h2>
	        <h2>Prediction for Zone 2: ${prediction2}</h2>
	        <h2>Prediction for Zone 3: ${prediction3}</h2>
	        <h2>Prediction for Zone 4: ${prediction4}</h2>
	        <h3>Total: ${predictiontot}</h3>
	   		<h2>Today's forecast:</h2>
	   		<c:forEach var = "forecast" items = "${tomorrowString}" varStatus = "status">
	   			${forecast}<br>
	   		</c:forEach>
	        <!--<h3><a href="newData">New Data</a></h3>-->
	        <!--<table border="1">
	        	<th>Zone 1</th>
	        	<th>Zone 2</th>
	        	<th>Zone 3</th>
	        	<th>Zone 4</th>
	        	<th>Total</th>	
				<c:forEach var="data" items="${listData}" varStatus="status">
	        	<tr>
					<td>${data.boatCount}</td>
					<td>${data.boatCount2}</td>
					<td>${data.boatCount3}</td>
					<td>${data.boatCount4}</td>
					<td>${data.boatTotal}</td>						
	        	</tr>
				</c:forEach>	        	
			</table>-->
			<!--<form action ="<c:url value="/insert" />" method = "POST">
				<input type="submit" name="input" value="save" />
			</form>
			<form action ="<c:url value="/generate" />" method = "POST">
				<input type="submit" name="generate" value="Generate Tomorrow's Data" />
			</form>-->
    	</div>
    </body>
</html>
