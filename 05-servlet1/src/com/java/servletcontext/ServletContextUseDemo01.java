package com.java.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个不小心 创建成 注释版本的Servlet程序了
 * 测试 获取 context对象以及其中的内容 并 测试获取context中的工程在服务器中的绝对路径
 */

@WebServlet(name = "ServletContextUseDemo01")
public class ServletContextUseDemo01 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1、获取web.xml中配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext();

        String username = context.getInitParameter("username");
        System.out.println("context-param参数username的值是:" + username);
        System.out.println("context-param参数password的值是:" + context.getInitParameter("password"));
//        2、获取当前的工程路径，格式: /工程路径
        System.out.println( "当前工程路径:" + context.getContextPath() );
//        3、获取工程部署后在服务器硬盘上的绝对路径
        /**
         *  / 斜杠被服务器解析地址为:http://ip:port/工程名/  映射到IDEA代码的web目录<br/>
         */
        System.out.println("工程下css目录的绝对路径是:" + context.getRealPath("/css"));
        System.out.println("工程部署的路径是:" + context.getRealPath("/"));
        System.out.println("工程下imgs目录1.jpg的绝对路径是:" + context.getRealPath("/imgs/3.jpg"));
    }
}
