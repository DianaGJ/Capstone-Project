<!DOCTYPE html>
<html lang="en">
<head>
<title>Easy Pass: Password Recovery</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css"/>
<div id="top"><h1>Password Recovery</h1></div>
</head>

<body>

  <form action="RecoveryController" method="post">
    <table>
      <tr>
        <td><label id="registerlabel">Email Address: </label></td>
        <td><input class="input" type="email" name="email" required></td>
      </tr>
    </table><br>

      <button id ="sbutton" class="button" type="submit">Recover Password</button><br><br><br>
    
    <span class="msg">Return to <a href="index.jsp">Login</a> page.</span>
  </form>

</body>
</html>