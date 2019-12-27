<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="login.html" method="post">
    用户名:<input type="text" name="userName"><br>
    密码:<input type="password" name="password"><br>
    <input type="submit" value="登录">
    <br>
    ${error}
</form>
</body>
</html>
