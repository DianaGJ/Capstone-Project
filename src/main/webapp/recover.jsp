<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Password Recovery</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css" />
<div id="top">
	<h1>Password Recovery</h1>
</div>
</head>

<body>

	<form action="RecoveryController" method="post"
		style="min-width: 600px;">
		<table>
			<tr>
				<td><label id="registerlabel" for="recovery-email">Email
						Address: </label></td>
				<td><input id="recovery-email" class="input" type="email"
					name="email" required></td>
			</tr>
		</table>
		<br>
		<c:if test="${not empty errorMessage}">
			<h3 style="color: red;">${errorMessage}</h3>
		</c:if>
		<button id="sbutton"   name="sender" 
			onclick=" this.form.submit(); this.disabled = true; sendEmail();">Recover
			Password</button>
		<br> <br> <br> <span class="msg">Return to <a
			href="index.jsp">Login</a> page.
		</span>
	</form>

	<script type="text/javascript">
	
		function sendEmail() {
			var element = document.getElementsByName("sender");
			element[0].innerText = 'Sending Email...';
			element[0].disable = true;
		}
	</script>
</body>
</html>