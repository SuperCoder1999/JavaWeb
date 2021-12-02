<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 33115
  Date: 2021/12/2
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上de 书城</span>
    <div>
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="" method="get">
                价格：<input id="min" type="text" name="min" value=""> 元 -
                <input id="max" type="text" name="max" value=""> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有3件商品</span>
            <div>
                您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
            </div>
        </div>

        <c:forEach items="${requestScope.page.items}" var="book">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="${book.imgPath}" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${book.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${book.author}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${book.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${book.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${book.stock}</span>
                </div>
                <div class="book_add">
                    <button>加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

    <div id="page_nav">

        <c:if test="${requestScope.page.pageNo > 1}">
            <a href="client/bookServlet?action=page&pageNo=1">首页</a>
            <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>
        <c:choose>
            <c:when test="${requestScope.page.pageTotal<=5}">
                <c:set scope="page" var="begin" value="1"/>
                <c:set scope="page" var="end" value="${requestScope.page.pageTotal}"/>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${requestScope.page.pageNo <= 3}">
                        <c:set scope="page" var="begin" value="1"/>
                        <c:set scope="page" var="end" value="5"/>
                    </c:when>
                    <c:when test="${requestScope.page.pageNo > requestScope.page.pageTotal-3}">
                        <c:set scope="page" var="begin" value="${requestScope.page.pageTotal-4}"/>
                        <c:set scope="page" var="end" value="${requestScope.page.pageTotal}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set scope="page" var="begin" value="${requestScope.page.pageNo-2}"/>
                        <c:set scope="page" var="end" value="${requestScope.page.pageNo+2}"/>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}" var="id">
            <c:if test="${id == requestScope.page.pageNo}">
                【${requestScope.page.pageNo}】
            </c:if>
            <c:if test="${id != requestScope.page.pageNo}">
                <a href="client/bookServlet?action=page&pageNo=${id}">${id}</a>
            </c:if>
        </c:forEach>
        <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
            <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="client/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>
        共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
        <input type="button" id="searchPageBtn" value="确定">
        <script type="text/javascript">

            $(function () {
                // 跳到指定的页码
                $("#searchPageBtn").click(function () {

                    var pageNo = $("#pn_input").val();

                    <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                    <%--alert(pageTotal);--%>

                    <%--javaScript语言中提供了一个location地址栏对象
                    它有一个属性叫href.它可以获取浏览器地址栏中的地址
                    href属性可读，可写--%>
                    location.href = "${pageScope.basePath}client/bookServlet?action=page&pageNo=" + pageNo;
                });
            });

        </script>
    </div>

</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>