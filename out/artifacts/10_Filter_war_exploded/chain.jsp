<%--
  Created by IntelliJ IDEA.
  User: 33115
  Date: 2021/12/11
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        System.out.println("chain.jsp 线程:" + Thread.currentThread().getName());
        System.out.println("chain.jsp 被访问成功");
    %>
</body>
</html>
