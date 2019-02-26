<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>ID</td>
				<td>First Name</td>
				<td>Last Name</td>
			</tr>
		</thead>
		<!-- JSP expression-ın içindəki user ifadəsi 'mav' variable-ının içinə yazdığımız user ifadəsidir-->
		<c:forEach items="${users}" var="user" varStatus="status">
			<tr bgcolor="${status.index % 2 == 0 ? 'white' : 'lightgray'}">
				<td>${user.id}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>