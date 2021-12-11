package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        //1.获取 用户名和密码 参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //判断登录是否成功
        if ("root".equals(username) && "123".equals(password)) {
            //登录成功就,返回一个 "登录成功" 的 信息
            response.getWriter().write("登陆成功");
            //将username保存到 session域中
            request.getSession().setAttribute("user", username);
        } else {
            response.getWriter().write("登录失败");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
