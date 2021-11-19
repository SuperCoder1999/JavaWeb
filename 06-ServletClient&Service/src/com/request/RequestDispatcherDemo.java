package com.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试 请求转发 功能
 */
public class RequestDispatcherDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.完成当前Servlet程序需要完成的工作
        System.out.println("转接 程序 的工作");
        System.out.println(request.getParameter("name"));

        //设置域数据
        request.setAttribute("pwd", "1234");
        System.out.println(request.getAttribute("pwd"));

        //2.获取 有目的程序的 RequestDispatcher对象
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/requestDispatcherDemoHelp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/RequestDispatcherDemoHelp.html");

        //3.将 请求 响应 传入 RequestDispatcher对象中, 执行 requestDispatcher对象指向的 目的程序
        requestDispatcher.forward(request, response);
    }
}
