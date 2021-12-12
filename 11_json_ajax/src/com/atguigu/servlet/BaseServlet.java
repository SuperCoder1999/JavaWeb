package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决status一直是 0 的问题
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("text/html; charset=UTF-8");
        String method = request.getParameter("action");
        //反射 调用方法
        try {
            Class userServletClass = this.getClass();
            Method method1 = userServletClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this, request, response);
        } catch (Exception e) {
            //这里可能接收到 method1.invoke() 抛出的关于 事务的异常.
            //不能处理掉这个异常,需要抛到 TransactionFilter中.即向上抛即可
            //因为,TransactionFilter中的chain.doFilter()调用了 这里的doPast()或doGet()
            System.out.println("BaseServlet 异常:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //普通方式 调用 各个方法
        /*if ("login".equals(method)) {
            login(request, response);
        } else {
            regist(request, response);
        }*/
    }
}
