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
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>Password Recovery</h1></div>
</head>

<body>

  <form action="VerificationCodeController" method="post">
    
    <label id="registerclabel">You have recently requested to reset your password.</label><br><br>
    <label id="registerclabel">Please check your email <b><c:out value="${email}"></c:out></b> and enter your verification code below.</label><br><br>

	<input type="text" name="code" style="text-align: center"/>
	<input type="hidden" name="email" value="${email}" />
	<input type="hidden" name="codeGenerated" value="${codeGenerated}" />
	<br><br>
    <button id ="sbutton" class="button" type="submit" >Reset Password</button><br><br><br>
    
    <span class="msg">Return to <a
			href="index.jsp">Login</a> page.
		</span>
  </form>

</body>
</html>