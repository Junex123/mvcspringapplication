<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1> Search Record </h1>
	<form:form action="search" method="post" modelAttribute="bean">
		Enter Employee code : <form:input path="empcode"/> &nbsp;
		or Employee name : <form:input path="empname"/> &nbsp;
		or Designation : <form:input path="designation"/> &nbsp;
		or Email : <form:input path="email"/> &nbsp;
		
			<input type="submit" value="Search">
	</form:form>
	<br/>
	<table border="2" width="100%">
		<tr>
			<th> Employee code </th>
			<th> Employee name </th>
			<th> Designation </th>
			<th> Email </th>
			<th> Update </th>
			<th> Delete </th>
		</tr>
		<c:forEach var="tab"  items="${li }">
		<tr>
			<td> <form action="update" method="post" modelAttribute="bean">    
			<input type="text" value="${tab.empcode }" name="empcode" readonly> </td>
			<td> <input type="text" value="${tab.empname }" name="empname"> </td>
			<td> <input type="text" value="${tab.designation }" name="designation"> </td>
			<td> <input type="text" value="${tab.email }" name="email"> </td>
			<td align="center"> <input type="submit" value="Update"></form> </td>
			
			<td align="center"> <form method="post" action="delete" modelAttribute="bean">
				<input type="hidden" value="${tab.empcode }" name="empcode">
				<input type="submit" value="Delete">
			</form></td>
		</tr>
		</c:forEach>
	</table>
				<h2> ${msg } </h2>
</center>

</body>
</html>