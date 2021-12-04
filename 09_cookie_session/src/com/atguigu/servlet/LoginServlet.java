package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取提交的 用户名与密码,进行验证 登录
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if ("username_mysql".equals(username) && "password_mysql".equals(password)) {
            System.out.println("登陆成功");
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
        } else {
            System.out.println("登陆失败");
        }
    }
}
