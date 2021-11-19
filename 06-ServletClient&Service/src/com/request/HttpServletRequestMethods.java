package com.request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 演示 Request常用方法
 * i. getRequestURI() 获取请求的资源路径
 * ii. getRequestURL() 获取请求的统一资源定位符（绝对路径）
 * iii. getRemoteHost() 获取客户端的ip 地址
 * iv. getHeader() 获取请求头
 * v. getParameter() 获取请求的参数
 * vi. getParameterValues() 获取请求的参数（多个值的时候使用）
 * vii. getMethod() 获取请求的方式GET 或POST
 * viii. setAttribute(key, value); 设置域数据
 * ix. getAttribute(key); 获取域数据
 * x. getRequestDispatcher() 获取请求转发对象
 */

public class HttpServletRequestMethods extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        i.getRequestURI()					获取请求的资源路径
        System.out.println("URI => " + req.getRequestURI());
//        ii.getRequestURL()					获取请求的统一资源定位符（绝对路径）
        System.out.println("URL => " + req.getRequestURL());
//        iii.getRemoteHost()				获取客户端的ip地址
        /**
         * 在IDEA中，使用localhost访问时，得到的客户端 ip 地址是 ===>>> 127.0.0.1<br/>
         * 在IDEA中，使用127.0.0.1访问时，得到的客户端 ip 地址是 ===>>> 127.0.0.1<br/>
         * 在IDEA中，使用 真实ip 访问时，得到的客户端 ip 地址是 ===>>> 真实的客户端 ip 地址<br/>
         */
        System.out.println("客户端 ip地址 => " + req.getRemoteHost());
//        iv.getHeader()						获取请求头
        System.out.println("请求头User-Agent ==>> " + req.getHeader("User-Agent"));
//        vii.getMethod()					获取请求的方式GET或POST
        System.out.println( "请求的方式 ==>> " + req.getMethod() );
    }
}
