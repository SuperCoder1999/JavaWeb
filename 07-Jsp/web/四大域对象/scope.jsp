<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/23
  Time: 21:23
  To change this template use File | Settings | File Templates.
  四大域对象  与  请求转发标签
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>scope.jsp页面</h1>
    <%
        // 往四个域中都分别保存了数据
        pageContext.setAttribute("key", "pageContext");
        request.setAttribute("key", "request");
        session.setAttribute("key", "session");
        application.setAttribute("key", "application");
    %>
    pageContext域是否有值：<%=pageContext.getAttribute("key")%> <br>
    request域是否有值：<%=request.getAttribute("key")%> <br>
    session域是否有值：<%=session.getAttribute("key")%> <br>
    application域是否有值：<%=application.getAttribute("key")%> <br>
<%--    <%--%>
<%--        request.getRequestDispatcher("/四大域对象/scope2.jsp").forward(request,response);--%>
<%--    %>--%>

    <%--  讲解 请求转发标签  --%>
    <%--
        <jsp:forward page=""></jsp:forward> 是请求转发标签，它的功能就是请求转发
            page 属性设置请求转发的路径
    --%>
    <jsp:forward page="/四大域对象/scope2.jsp"></jsp:forward>
</body>
</html>
