package com.atguigu.book.filter;

import com.atguigu.book.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void destroy() {
    }

    /**
     *  针对所有请求,都进行事务管理.
     *  (其实,可以只针对,对数据库有操作的 那几个程序及页面.)
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(req, resp);
            //如果没有事务,调用commitAndClose()因为有if 也不会出错.
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            //如果有异常,就rollback
            JdbcUtils.rollbackAndClose();
            System.out.println("Transaction异常:");
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
