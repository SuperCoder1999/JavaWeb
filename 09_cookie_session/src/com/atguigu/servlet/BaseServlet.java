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
        //设置 回写 的字符集
        response.setContentType("text/html; charset=utf-8");
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
