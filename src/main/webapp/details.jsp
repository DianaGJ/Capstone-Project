<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Details</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<div id="top">
	<h1>Details</h1>
</div>
</head>

<body>

	<form action="UpdateController" method="post">
		<input type="hidden" id="id" name="id" value="${password.id}">
		<table>
			<tr>

				<td><label id="registerlabel">Web Site: </label></td>
				<td><input class="input" type="text" name="website"
					value="${password.website}" required></td>
			</tr>
			<tr>
				<td><label id="registerlabel">User: </label></td>
				<td><input class="input" type="text" name="websiteUser"
					value="${password.websiteUser}" required></td>
			</tr>
			<tr>
				<td><label id="registerlabel">Password: </label></td>
				<td><input id="password" class="input" type="text" name="password"
					value="${password.password}" required></td>
				
			</tr>
			<tr>
				<td colspan="2">Generate Password</td>
			</tr>
			<tr>
				<td><input id="generate-length" type="number" value="10" min="1"></td>
				<td><input id="suggested-password" type="text"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="button" id="sbutton" onclick="getPassword()">Generate</button></td>
			</tr>
		</table>
		<br> <br>
		<button id="sbutton" class="button" type="submit">Save</button>
		<a href="list"> <input type="button" class="button"
			value="Cancel" id="sbutton" />
		</a>

	</form>

	<script>
		function getPassword() {
			var length = document.getElementById("generate-length").value;
			
			fetch('/Capstone/generate_password?method=random&length=' + length)
				.then(response => response.text())
				.then(text => document.getElementById("suggested-password").value = text);
		}
	</script>
</body>
</html>