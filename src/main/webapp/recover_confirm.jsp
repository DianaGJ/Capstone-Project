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

  <form action="RecoveryController" method="get">
    
    <label id="registerclabel">You have recently requested to reset your password.</label><br><br>
    <label id="registerclabel">Please click the link below to perform the reset.</label><br><br>

	<input type="hidden" name="code" value="${code}" />

    <button id ="sbutton" class="button" type="submit">Reset Password</button><br><br><br>
    
  </form>

</body>
</html>