<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--关系运算--%>
${ 12 == 12 } 或 ${ 12 eq 12 } <br>
${ 12 != 12 } 或 ${ 12 ne 12 } <br>
${ 12 < 12 } 或 ${ 12 lt 12 } <br>
${ 12 > 12 } 或 ${ 12 gt 12 } <br>
${ 12 <= 12 } 或 ${ 12 le 12 } <br>
${ 12 >= 12 } 或 ${ 12 ge 12 } <br>
<hr>
${ 12 == 12 && 12 > 11 } 或 ${ 12 == 12 and 12 > 11 } <br>
${ 12 == 12 || 12 > 11 } 或 ${ 12 == 12 or 12 > 11 } <br>
${ ! true } 或 ${ not true } <br>

<%--算术运算--%>
<hr>
${ 12 + 12 } <br>
${ 12 - 12 } <br>
${ 12 * 12 } <br>
${ 18 / 12 } 或 ${ 18 div 12 }<br>
${ 18 % 12 } 或 ${ 18 mod 12 } <br>

<%-- 判断 为空--%>
<%
    //        1、值为null值的时候，为空
    request.setAttribute("emptyNull", null);
//        2、值为空串的时候，为空
    request.setAttribute("emptyStr", "");
//        3、值是Object类型数组，长度为零的时候
    request.setAttribute("emptyArr", new Object[]{});
//        4、list集合，元素个数为零
    List<String> list = new ArrayList<>();
//        list.add("abc");
    request.setAttribute("emptyList", list);
//        5、map集合，元素个数为零
    Map<String,Object> map = new HashMap<String, Object>();
//        map.put("key1", "value1");
    request.setAttribute("emptyMap", map);
%>
${ empty emptyNull } <br/>
${ empty emptyStr } <br/>
${ empty emptyArr } <br/>
${ empty emptyList } <br/>
${ empty emptyMap } <br/>

<%-- 三目运算 --%>
<hr>
${ 12 != 12 ? "国哥帅呆":"国哥又骗人啦" }
</body>
</html>
