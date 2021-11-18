package com.java.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过继承HttpServlet 重写其中的方法实现Servlet程序
 */
public class ExtendsHttpServletDemo extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("重写了init()初始化方法,做了一些事情");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 也可以获取到servletConfig对象.
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);

        //2、获取这个Servlet程序自己的 初始化参数init-param
        System.out.println("初始化参数username的值是;" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是;" + servletConfig.getInitParameter("url"));

        System.out.println("doGet  ExtendsHttpServletDemo");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost  ExtendsHttpServletDemo");

    }
}
