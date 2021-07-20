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
	<h1>Generate Password</h1>
</div>
</head>

<body>
	<form action="GeneratePassword" method="get">
		<table>
			<tr>
				<td colspan="2" style="padding-bottom: 20px;">Need help coming up with a password? Generate one here!</td>
			</tr>
			<tr>
				<td><label for="generate-length">Length:</label></td>
				<td><input id="generate-length" type="number" name="length" value="${length}" min="1" required></td>
			</tr>
			<tr>
				<td><label for="method">Method:</label></td>
				<td>
					<select id="method" name="method">
						<option value="random" ${method == "random" ? 'selected' : ''}>Random</option>
						<option value="pin" ${method == "pin" ? 'selected' : ''}>PIN</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="suggested-password">Password:</label></td>
				<td><input id="suggested-password" type="text" value="${password}"></td>
			</tr>
			<tr>
				<td colspan="2"><button id="sbutton" class="button" type="submit">Generate Code</button></td>
			</tr>
			<tr>
				<td colspan="2"><a href="list"><input type="button" class="button" value="Back" id="sbutton" /></a></td>
			</tr>
		</table>
	</form>
</body>
</html>