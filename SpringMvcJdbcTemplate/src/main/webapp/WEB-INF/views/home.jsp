<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Anus</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Refugee Boat Predictor</h1>
	        <h2>Prediction: ${prediction}</h2>
	        <!--<h3><a href="newData">New Data</a></h3>-->
	        <table border="1">
	        	<th>Boat Count</th>	
				<c:forEach var="data" items="${listData}" varStatus="status">
	        	<tr>
					<td>${data.boatCount}</td>		
	        	</tr>
				</c:forEach>	        	
			</table>
			<!--<form action ="<c:url value="/insert" />" method = "POST">
				<input type="submit" name="input" value="save" />
			</form>
			<form action ="<c:url value="/generate" />" method = "POST">
				<input type="submit" name="generate" value="Generate Tomorrow's Data" />
			</form>-->
    	</div>
    </body>
</html>
