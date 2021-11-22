<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.atguigu.pojo.Person02" %>
<%@ page import="com.atguigu.pojo.Person02" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Person02 person = new Person02();
    person.setName("国哥好帅！");
    person.setPhones(new String[]{"18610541354","18688886666","18699998888"});

    List<String> cities = new ArrayList<String>();
    cities.add("北京");
    cities.add("上海");
    cities.add("深圳");
    person.setCities(cities);

    Map<String,Object>map = new HashMap<>();
    map.put("key1","value1");
    map.put("key2","value2");
    map.put("key3","value3");
    person.setMap(map);

    pageContext.setAttribute("p", person);
%>

输出Person：${ p }<br/>
输出Person的name属性：${p.name} <br>
输出Person的pnones数组属性值：${p.phones[2]} <br>
输出Person的cities集合中的元素值：${p.cities} <br>
输出Person的List集合中个别元素值：${p.cities[2]} <br>
输出Person的Map集合: ${p.map} <br>
输出Person的Map集合中某个key的值: ${p.map.key3} <br>

<%--EL 表达式 找对象属性,找的不是bean对象属性,而是找其对应的 getXxx()方法.其中Xxx对应了 要找的名字.--%>
输出Person的age属性：${p.age} <br>


<%-- map等 key 中 含有特殊字符,必须用[] --%>
<%
    Map<String,Object> map2 = new HashMap<String, Object>();
    map2.put("a.a.a", "aaaValue");
    map2.put("b+b+b", "bbbValue");
    map2.put("c-c-c", "cccValue");

    request.setAttribute("map", map2);
%>

${ map['a.a.a'] } <br>
${ map["b+b+b"] } <br>
${ map['c-c-c'] } <br>

</body>
</html>
