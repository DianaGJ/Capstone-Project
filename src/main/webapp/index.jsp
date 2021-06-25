<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1 id="greentop">EasyPass</h1></div>
</head>

<body>

  <form action="list.jsp" method="post">
  
    <div class="container">
      <img src="username.png" alt="Username" class="avatar">
      <input class="input" type="text" placeholder="Enter Username" name="uname" required><br>

      <img src="lock.jpg" alt="Password" class="avatar">
      <input class="input" type="password" placeholder="Enter Password" name="psw" required><br><br>

      <button id ="sbutton" class="button" type="submit">Login</button><br><br>
        
    <span class="psw">No account? <a href="#">Register here.</a></span>
  </form>

</body>
</html>