package com.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 测试 请求转发 功能  (辅助程序)
 */
public class RequestDispatcherDemoHelp extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("被转接 程序 的工作");
        System.out.println(request.getParameter("name"));
        System.out.println(request.getAttribute("pwd"));
    }
}
