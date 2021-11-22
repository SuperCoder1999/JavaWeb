<%-- 练习一：在jsp页面中输出九九乘法口诀表 --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>九九乘法表
<p>

        <%for (int i = 1; i <= 9; i++) {
        for (int j = 1; j <= i; j++) {%>
    <%--        <%=j + "*" + i + "=" + i*j%>--%>
        <%=j%>
        <%="*"%>
        <%=i%>
        <%="="%>
        <%=i*j%>
    <%--            sout(i + "*" + j + "=" + i*j);--%>
        <%}%>
    <br>
        <%}%>


<p>九九乘法表-表格版
<p>
<table border="4">
    <%
        for (int i = 1; i <= 9; i++) {
    %>
    <tr>
        <%for (int j = 1; j <= i; j++) {%>
        <td><%=j + "*" + i + "=" + i*j%></td>
            <%--sout(i + "*" + j + "=" + i*j);--%>
        <%}%>
    </tr>
    <%}%>
</table>
</body>
</html>
