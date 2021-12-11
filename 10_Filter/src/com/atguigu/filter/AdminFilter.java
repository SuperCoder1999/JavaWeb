package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }

    /**
     * 用于拦截请求.可以做权限检查
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("进入doFilter()方法中");
        HttpServletRequest httpServletRequest = (HttpServletRequest)req;
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");
        //如果等于 null, 说明没有登陆请求转发
        if (user == null) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        } else {
            //让程序继续 执行 用户 访问的目标
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
