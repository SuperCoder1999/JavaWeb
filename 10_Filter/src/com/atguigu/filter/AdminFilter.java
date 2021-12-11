package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class AdminFilter implements Filter {

    public AdminFilter(){
        System.out.println("1. 构造器方法");
    }

    public void destroy() {
        System.out.println("4. destroy方法");
    }

    /**
     * 用于拦截请求.可以做权限检查
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("3.进入doFilter()方法中");
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
        System.out.println("2.init方法");

        // 1.FilterConfig类获取 filter-name的内容
        System.out.println("filter-name: " + config.getFilterName());

        //2.获取web.xml中配置的filter的init-param初始化参数
        System.out.println("初始化参数name: " + config.getInitParameter("name"));
        //3.获取所有的filter的init-param初始化参数名
        //扩展只是 Enumeration类的使用
        Enumeration<String> enumeration= config.getInitParameterNames();
        while(enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
            //怎么还是倒叙输出呢?
        }

        //4.获取ServletContext对象 - ServletContext 不是很熟练
        System.out.println(config.getServletContext());
    }

}
