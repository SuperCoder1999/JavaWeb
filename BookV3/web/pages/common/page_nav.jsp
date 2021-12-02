<%--
  Created by IntelliJ IDEA.
  User: 33115
  Date: 2021/12/2
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">

    <c:if test="${requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
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
            <a href="${requestScope.page.url}&pageNo=${id}">${id}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
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
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });

    </script>
</div>