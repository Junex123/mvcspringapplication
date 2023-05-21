<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1> User Login </h1>
	<form:form method="post" action="sign" modelAttribute="bean">
		Enter Employee code : <form:input path="empcode"/> <br/><br/>
		Enter Employee name : <form:input path="empname"/> <br/><br/>
							  <input type="submit" value="Login">
	</form:form>
</center>
</body>
</html>