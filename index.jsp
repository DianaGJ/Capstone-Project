<!DOCTYPE html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>EasyPass</h1></div>
</head>

<body>

  <form action="action_page.php" method="post">
  
    <div class="container">
      <img src="username.png" alt="Username" class="avatar">
      <input class="input" type="text" placeholder="Enter Username" name="uname" required><br>

      <img src="lock.jpg" alt="Password" class="avatar">
      <input class="input" type="password" placeholder="Enter Password" name="psw" required><br><br>

      <button class="button" type="submit">Login</button><br><br>
        
    <span class="psw">No account? <a href="#">Register here.</a></span>
  </form>

</body>
</html>