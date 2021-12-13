<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<%
		//国际化方式二:通过"显示的语言类型",让用户提交参数,服务器,按照参数设定语言
		Locale locale = null;
		//2.获取用户提交的 参数
		String lang = request.getParameter("country");
		//3.再根据这个参数,设置Local
		if ("zh".equals(lang)) {
			locale = Locale.CHINA;
		} else if ("us".equals(lang)) {
			locale = Locale.US;
		} else {
			//第一次访问不带 语言参数,这时还是按照request.getLocale();返回语言类型
			locale = request.getLocale();
		}

		//2.通过 ResourceBundle.getBundle()和 baseName+locale获取Bundle对象
		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n", locale);
		//3.jsp在上面创建了bundle对象,在下面的jsp页面中就可以调用这个对象中的属性了

		/*//通过请求头的方式 实现国际化
		//1.获取请求头中的 Locale对象
		Locale locale = request.getLocale();
		System.out.println(locale);

		//1.5 通过"显示的语言类型",让用户提交参数,服务器,按照参数设定语言
		//第一次访问不带 语言参数,这时还是按照request.getLocale();返回语言类型
		//1.获取用户提交的 参数
		String lang = request.getParameter("country");
		//2.再根据这个参数,设置Local
		if ("zh".equals(lang)) {
			locale = Locale.CHINA;
		} else if ("us".equals(lang)) {
			locale = Locale.US;
		}

		//2.通过 ResourceBundle.getBundle()和 baseName+locale获取Bundle对象
		ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n", locale);
		//3.jsp在上面创建了bundle对象,在下面的jsp页面中就可以调用这个对象中的属性了*/
	%>
</head>
<body>
	<a href="i18n.jsp?country=zh">中文</a>|
	<a href="i18n.jsp?country=us">english</a>
	<center>
		<h1><%=resourceBundle.getString("regist")%></h1>
		<table>
		<form>
			<tr>
				<td><%=resourceBundle.getString("username")%></td>
				<td><input name="username" type="text" /></td>
			</tr>
			<tr>
				<td><%=resourceBundle.getString("password")%></td>
				<td><input type="password" /></td>
			</tr>
			<tr>
				<td><%=resourceBundle.getString("sex")%></td>
				<td><input type="radio" /><%=resourceBundle.getString("boy")%>
					<input type="radio" /><%=resourceBundle.getString("girl")%></td>
			</tr>
			<tr>
				<td><%=resourceBundle.getString("email")%></td>
				<td><input type="text" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="reset" value="<%=resourceBundle.getString("reset")%>" />&nbsp;&nbsp;
				<input type="submit" value="<%=resourceBundle.getString("submit")%>" /></td>
			</tr>
			</form>
		</table>
		<br /> <br /> <br /> <br />
	</center>
	国际化测试：
	<br /> 1、访问页面，通过浏览器设置，请求头信息确定国际化语言。
	<br /> 2、通过左上角，手动切换语言
</body>
</html>