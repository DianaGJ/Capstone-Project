<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Details</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>Details</h1></div>
</head>

<body>

  <form action="list.jsp" method="post">
  
    <div class="container">
      <label id="registerlabel">Web Site: </label><input class="input" type="text" name="wsite" required><br>
      <label id="registerlabel">User: </label><input class="input" type="text" name="user" required><br>
      <label id="registerlabel">Password: </label><input class="input" type="email" name="pword" required><br>

      <br><button id ="sbutton" class="button" type="submit">Save</button>
      <button id="cbutton" class="button" type="reset">Cancel</button><br><br>

  </form>

</body>
</html>