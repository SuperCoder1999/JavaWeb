package com.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示 Base标签的使用
 * 果采用请求转发 的方式 获取一个静态页面,那么另一个静态页面 的路径就不能通过两个页面在 web中的相对路径直接获取.
 * 除非 在静态页面获取 的页面 中添加base标签,标记该静态页面在web的真实路径.
 */
public class Base extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("转换到 base.html中");
        request.getRequestDispatcher("/a/b/base.html").forward(request, response);
    }
}
