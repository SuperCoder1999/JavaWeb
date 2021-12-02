<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@ include file="/pages/common/head.jsp" %>
<%--    <script type="text/javascript">--%>
<%--        $(function() {--%>
<%--            $("a.deleteClass").click(function () {--%>
<%--                return confirm("你确定要删除:[" + $(this).parent().parent().find("td:first").text() + "]");--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>
    <script type="text/javascript">
        $(function () {
            // 给删除的a标签绑定单击事件，用于删除的确认提示操作
            $("a.deleteClass").click(function () {
                // 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的dom对象。
                /**
                 * confirm是确认提示框函数
                 * 参数是它的提示内容
                 * 它有两个按钮，一个确认，一个是取消。
                 * 返回true表示点击了，确认，返回false表示点击取消。
                 */
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
                // return false// 阻止元素的默认行为===不提交请求
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.author}</td>
                <td>${item.sales}</td>
                <td>${item.stock}</td>
                <td><a class="deleteClass2" href="manager/bookServlet?action=getBook&id=${item.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a id="delete" class="deleteClass" href="manager/bookServlet?action=delete&id=${item.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <div id="page_nav">

        <c:if test="${requestScope.page.pageNo > 1}">
            <a href="manager/bookServlet?action=page&pageNo=1">首页</a>
            <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
                <a href="manager/bookServlet?action=page&pageNo=${id}">${id}</a>
            </c:if>
        </c:forEach>
       <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
           <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageNo+1}">下一页</a>
           <a href="manager/bookServlet?action=page&pageNo=${requestScope.page.pageTotal}">末页</a>
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
                    location.href = "${pageScope.basePath}manager/bookServlet?action=page&pageNo=" + pageNo;
                });
            });
        </script>
    </div>
</div>

<%@ include file="/pages/common/footer.jsp" %>
</body>
</html>