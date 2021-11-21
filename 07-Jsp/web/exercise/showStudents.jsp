<%@ page import="com.atguigu.pojo.Student" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="4">
    <%
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int t = i + 1;//提高输出效率
            list.add(new Student(t, "name" + t, 18 + t, "1190" + t));
        }
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
<%--//            System.out.println(list.get(i).getId() + "\t" +--%>
<%--//                    list.get(i).getName() + "\t" +--%>
<%--//                    list.get(i).getAge() + "\t" +--%>
<%--//                    list.get(i).getPhone() + "\t"--%>
<%--//            );--%>

</body>
</html>
