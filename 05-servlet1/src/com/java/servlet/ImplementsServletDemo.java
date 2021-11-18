package com.java.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet入门 通过实现接口Servlet 实现Servlet程序
 */

public class ImplementsServletDemo implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
//        1、可以获取Servlet程序的别名servlet-name的值
        System.out.println("HelloServlet程序的别名是:" + servletConfig.getServletName());
//        2、获取初始化参数init-param
        System.out.println("初始化参数username的值是;" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数url的值是;" + servletConfig.getInitParameter("url"));
//        3、获取ServletContext对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * get \ post请求分发处理
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("浏览器连接!");
        //获取 请求类型
        //1.向下转型
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //2.获取method对应的字符串
        String method = httpServletRequest.getMethod();

        if ("GET".equals(method))
            doGet();
        else if ("POST".equals(method))
            doPost();
    }

    private void doPost() {
        System.out.println("doPost()  ImplementsServletDemo");
    }

    private void doGet() {
        System.out.println("doGet()  ImplementsServletDemo");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
