<%--
  Created by IntelliJ IDEA.
  User: 33115
  Date: 2021/12/11
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>低配版 filter</title>
</head>
<body>
    <%
        Object user = session.getAttribute("user");
        //如果等于 null, 说明没有登陆请求转发
        if (user == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    %>
    否则,向用户展示 当前myFilter页面
</body>
</html>
