<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	response.setHeader("Cache-Control", "no-cache");
%>


<html>
<head>
<link href="<c:url value="/resources/styles/table.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/styles/prediction.css" />"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Refugee Predictor</title>
</head>
<body bgcolor="#76B852">
	<div align="center">
		<img src="<c:url value="/resources/images/refugee_prediction.png" />"
			alt="" /> <a class = "logout" href="logout">Logout</a> <br> <br> <br>
		<table width=700 height=400 id="cssTable">
			<tr>
				<td colspan="2" style="width: 100%">TODAY'S PREDICTION:
					${predictiontot}</td>
			</tr>
			<tr>
				<td>MITHIMNA: ${prediction}</td>
				<td>SKALA: ${prediction2}</td>
			</tr>
			<tr>
				<td>EFTHALOU: ${prediction3}</td>
				<td>TSONIA: ${prediction4}</td>
			</tr>
			<tr>
				<td colspan="2" style="">FORECAST FOR ${date} (GMT +3): <br> <c:forEach
					var="forecast" items="${tomorrowString}" varStatus="status">
	   				${forecast}<br>
					</c:forEach>
					<a class = "weather" href="http://forecast.io/#/f/39.3680,26.1756">Detailed forecast from forecast.io</a>
				</td>
		</table>
		<br>
	</div>
</body>
</html>
