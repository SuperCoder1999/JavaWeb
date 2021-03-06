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
    <Script type="text/javascript">
        $(function () {
            $("button.addToCart").click(function() {
                /*
                  在事件响应的function函数中,有一个this对象,这个对象,是当前响应
                  事件的dom对象
                 */
                //原本用于 回显 加入购物车 后 的lastName 和 totalCount
                //var bookId = $(this).attr("bookId");
                <%--location.href="${pageScope.basePath}cartServlet?action=addItem&id=" + bookId;--%>
            //现在 用 Ajax 局部更新 lastName和totalCount
                var bookId = $(this).attr("bookId");
                $.getJSON("${pageScope.basePath}cartServlet", "action=ajaxAddItem&id=" + bookId, function(data) {
                   $("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品");
                   $("#cartLastName").text(data.lastName);
                });
            });
        });
    </Script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <div>
        <c:if test="${not empty sessionScope.user.username}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>书城项目</span>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>
        <c:if test="${empty sessionScope.user.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    ${sessionScope.cart.items}
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
<%--                param 是访问 pageByPrice时,传入的参数.之后转发 时 又传给index.jsp,当作其参数 --%>
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${not empty sessionScope.cart.items}">
                <%--<span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                </div>--%>
                <span id="cartTotalCount"></span>
                <div>
                    您刚刚将<span id="cartLastName" style="color: red"></span>加入到了购物车中
                </div>
            </c:if>
           <c:if test="${empty sessionScope.cart.items}">
               <div>
                   <span style="color: red">当前购物车为空</span>
               </div>
           </c:if>
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
                    <button bookId="${book.id}" class="addToCart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

<%@ include file="/pages/common/page_nav.jsp" %>

</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>