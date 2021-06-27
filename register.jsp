<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Register</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>Register</h1></div>
</head>

<body>

  <form action="list.jsp" method="post">
  
    <div class="container">
      <label id="registerlabel">First Name: </label><input class="input" type="text" name="fname" required><br>
      <label id="registerlabel">Last Name: </label><input class="input" type="text" name="lname" required><br>
      <label id="registerlabel">Username: </label><input class="input" type="text" name="uname" required><br>
      <label id="registerlabel">Email: </label><input class="input" type="email" name="email" required><br>
      <label id="registerlabel">Password: </label><input class="input" type="password" name="pword" required><br>
      <label id="registerlabel">Confirm Password: </label><input class="input" type="password" name="pword2" required><br>

      <br><button id ="sbutton" class="button" type="submit">Create</button>
      <button id="cbutton" class="button" type="reset">Cancel</button><br><br>
        
    <span class="create">Have an account? <a href="#">Click here to login.</a></span>
  </form>

</body>
</html>