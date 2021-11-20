
<%--演示jsp的三种语法之一:jsp头部的page指令 也叫 声明脚本--%>

<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" language="java" %>
<!--
errorPage表示错误后自动跳转去的路径　<br/>
这个路径一般都是以斜杠打头，它表示请求地址为http://ip:port/工程路径/
映射到代码的Web目录
-->
<html>
<head>
    <title>Title</title>
</head>
<body>
b.jsp页面

<%-- 讲解 jsp中的 各种注释 --%>
<!-- 这是html注释  -->
<%
    // 单行java注释
    /*  多行java注释  */
%>

<%-- 这是jsp注释  --%>

</body>
</html>
