package com.atguigu.filter;


import javax.servlet.*;
import java.io.IOException;

public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter 1 前置代码");
        System.out.println("Filter1线程:" + Thread.currentThread().getName());
        request.setAttribute("name", "root");
        chain.doFilter(request, response);
        System.out.println("Filter 1 后置代码");
    }

    @Override
    public void destroy() {

    }
}
