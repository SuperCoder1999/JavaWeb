<%--
1.EL 表达式的格式是：${表达式}
2.EL 表达式在输出null 值的时候，输出的是空串。jsp 表达式脚本输出null 值的时候，输出的是null 字符串。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("key","值");
%>
表达式脚本输出key的值是：<%=request.getAttribute("key1")==null?"":request.getAttribute("key1")%><br/>
EL表达式输出key的值是：${key1}
</body>
</html>
