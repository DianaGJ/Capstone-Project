<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Register</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<div id="top">
	<h1>Register</h1>
</div>
</head>

<body>


	<form action="RegisterController" method="post">

		<div class="container">

			<table>
				<tr>
					<td><label id="registerlabel">Username: </label></td>
					<td><input class="reginput" type="text" name="uname" required></td>
				</tr>
				<tr>
					<td><label id="registerlabel">Email: </label></td>
					<td><input class="reginput" type="email" name="email" required>
					</td>
				</tr>
				<tr>
					<td><label id="registerlabel">Password: </label></td>
					<td><input class="reginput" type="password" name="pword"
						required></td>
				</tr>
				<tr>
					<td><label id="registerlabel">Confirm Password: </label></td>
					<td><input class="reginput" type="password" name="pword2"
						required></td>
				</tr>
			</table>

			<c:if test="${not empty erroMessage}">
				<h3 style="color: red;">${erroMessage}</h3>
			</c:if>
			<br>
			<button id="sbutton" class="button" type="submit" style="margin-bottom: 8px;">Create</button>
			<a type="reset" href=""><button id="sbutton" type="button">Reset</button></a>
			<br> <br> <span class="create">Have an account? <a
				href="index.jsp">Click here to login.</a></span>
	</form>

</body>
</html>