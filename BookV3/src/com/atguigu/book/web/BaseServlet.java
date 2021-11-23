package com.atguigu.book.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("action");
        //反射 调用方法
        try {
            Class userServletClass = this.getClass();
            Method method1 = userServletClass.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            method1.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //普通方式 调用 各个方法
        /*if ("login".equals(method)) {
            login(request, response);
        } else {
            regist(request, response);
        }*/
    }
}
