package com.atguigu.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Filter 2 前置代码");
        System.out.println("Filter2线程:" + Thread.currentThread().getName());
        System.out.println("request域中的参数name=" + req.getAttribute("name"));
        chain.doFilter(req, resp);
        System.out.println("Filter 2 后置代码");
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
