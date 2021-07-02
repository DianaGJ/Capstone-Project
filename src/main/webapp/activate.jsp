<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Activate Account</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<div id="top">
	<h1>Activate Account</h1>
</div>
</head>

<body>

	<form action="ActivationController" method="post" style="">
		<table>
			<tr>
				<td><label id="registerlabel">Email Address:</label></td>
				<td><input class="input" type="email" name="email"
					value="${user.email}"  style="width: 200px; "></td>
			</tr>
			<tr>
				<td><label id="registerlabel">Please, copy this code <c:out
							value="${user.verificationCode}"></c:out> to activate your
						account:
				</label></td>
				<td><input class="input" type="text" name="verificationCode"
					required  style="width: 200px; " ></td>
			</tr>
		</table>
		<br>
		<c:if test="${not empty messageOnActivation}">
			<h3 style="color: red;">${messageOnActivation}</h3>
		</c:if>
		<button id="sbutton" class="button" type="submit">Activate
			Account</button>
		<br> <br> <br> <span class="msg">Have an
			account? <a href="index.jsp">Login here.</a>
		</span>
	</form>

</body>
</html>