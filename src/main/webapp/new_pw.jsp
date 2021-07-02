<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Create New Password</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<div id="top">
	<h1>Create New Password</h1>
</div>
</head>

<body>

	<form action="UpdatePassController" method="post">

		<input type="hidden" id="username" name="username" value="${user.username }">
		<table>
			<thead>
				<tr>
					<th> YOUR USERNAME IS </th>
					<th style=" color: red;"> <c:out  value=" ${user.username }"></c:out></th>
				</tr>
			</thead>
			<tr>
				<td><label id="registerlabel">New Password: </label></td>
				<td><input class="input" type="password" name="newpw" required></td>
			</tr>
			<tr>
				<td><label id="registerlabel">Confirm New Password: </label></td>
				<td><input class="input" type="password" name="newpw2" required></td>
			</tr>
		</table>
		<br>
		<c:if test="${not empty errorMessage}">
			<h3 style="color: red;">${errorMessage}</h3>
		</c:if>
		<button id="sbutton" class="button" type="submit">Update
			Password</button>
		<br> <br> <br> <span class="msg">Return to <a
			href="index.jsp">Login</a> page.
		</span>
	</form>

</body>
</html>