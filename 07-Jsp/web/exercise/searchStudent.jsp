<%@ page import="com.atguigu.pojo.Student" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--SearchStudentServlet程序 的请求转发对象--%>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            border: 1px blue solid;
            width: 600px;
            border-collapse: collapse;
        }
        td, th{
            border: 1px blue solid;
        }
    </style>
</head>
<body>
<table>
<%--  5.在jsp文件中 取出request中的域数据,打印到页面  --%>
    <%
        ArrayList<Student> list = (ArrayList<Student>)request.getAttribute("stuList");
    %>
    <%for (int i = 0; i < 10; i++) { %>
    <tr>
        <td>
            <%=list.get(i).getId()%>
        </td>
        <td>
            <%=list.get(i).getName()%>
        </td>
        <td>
            <%=list.get(i).getAge()%>
        </td>
        <td>
            <%=list.get(i).getPhone()%>
        </td>
    </tr>
    <%}%>
</table>
</body>
</html>
