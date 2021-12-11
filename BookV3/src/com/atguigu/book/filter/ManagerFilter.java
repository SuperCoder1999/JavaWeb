package com.atguigu.book.filter;

import com.atguigu.book.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //进行判断,是否 对拦截放行
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            //让程序继续 执行
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
