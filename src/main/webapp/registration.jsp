<%
    String text = (String)request.getAttribute("error");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<form action="registration" method="POST">
    Login: <input type="text" name="login"/>
    Password: <input type="password" name="pass"/>
    Email: <input type="email" name="email">
    <input type="submit" value="Register">


</form>

<div>
    <%
        out.println(text);

    %>
</div>
</body>
</html>