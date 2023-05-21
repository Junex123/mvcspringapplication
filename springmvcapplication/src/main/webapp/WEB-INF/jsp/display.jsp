<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	th {
		background:orange;
		color:white;
	}
	h2 {
		color:red;
	}
</style>
</head>
<body>
<center>
	<h1> Employee Records </h1> <br/>
	<table border="2" width="100%">
		<tr>
			<th> Employee code </th>
			<th> Employee name </th>
			<th> Designation </th>
			<th> Email </th>
		</tr>
		<c:forEach var="tab" items="${data }">
		<tr>
			<td> ${tab.empcode } </td>
			<td> ${tab.empname } </td>
			<td> ${tab.designation } </td>
			<td> ${tab.email } </td>
		</tr>
		</c:forEach>
	</table>
	<br/>
			<h2> ${msg } </h2>
</center>
</body>
</html>