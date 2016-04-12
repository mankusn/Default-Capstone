<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<link href="<c:url value="/resources/styles/login.css" />"
	rel="stylesheet">
<script src="/resources/scripts/login.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Manager Home</title>
</head>
<body>

	<div align="center"> 
	<img src="<c:url value="/resources/images/refugee_prediction.png" />" alt="" />
	
	</div>
	<div class="login-page">
		<div class="form">
			<form:form class="login-form" action="validate" method="post" modelAttribute="credentials">
				<form:input type="text" placeholder="username" path="userName" /> <form:input type="password"
					placeholder="password" path="password" />
				 <button>login</button>
				<!--  <input type="submit" value="Save">  -->
				
			</form:form>
		</div>
	</div>
</body>
</html>
