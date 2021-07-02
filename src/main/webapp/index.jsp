<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>EasyPass</h1></div>
</head>

<body>

  <form action="LoginController" method="post">
  
    <div class="container">
      <img src="username.png" alt="Username" class="avatar">
      <input class="input" type="text" placeholder="Enter Username" name="uname" required><br><br>

      <img src="lock.jpg" alt="Password" class="avatar">
      <input class="input" type="password" placeholder="Enter Password" name="psw" required><br><br>

      <button id ="sbutton" class="button" type="submit">Login</button><br><br>
      <c:if test="${not empty errorMessage}">
      <h3 style="color: red;">${errorMessage}</h3>
      </c:if>
       <c:if test="${not empty activationMessage}">
      <h3 style="color: red;">${activationMessage}</h3>
      </c:if>
        
    
    <span class="msg">Forgot your password? <a href="recover.jsp">Recover it here.</a></span><br><br>
    <span class="msg">No account? <a href="register.jsp">Register here.</a></span>

  </form>

</body>
</html>