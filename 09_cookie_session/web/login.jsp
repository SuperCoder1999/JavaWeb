<%--
  Created by IntelliJ IDEA.
  User: 33115
  Date: 2021/12/4
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="loginServlet" method="get">
        用户名: <input type="text" name="username" value="${cookie.username.value}"><br/>
        密码: <input type="password" name="password" value=""> <br/>
        <input type="submit" value="登录">登录
    </form>
</body>
</html>
