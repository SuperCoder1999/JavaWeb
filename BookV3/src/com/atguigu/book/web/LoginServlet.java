package com.atguigu.book.web;

import com.atguigu.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1）获取用户名密码
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
//        2）判断用户是否存在
        UserServiceImpl userService = new UserServiceImpl();
        if (userService.login(userName, password) == null) {
            System.out.println("账号或密码错误:" + userName + "\t" + password);
            //请求转移到 登陆页面
            //        3）如果登陆失败--->>>> 返回用户名或者密码错误信息
            request.setAttribute("msg", "用户名或者密码错误");
            request.setAttribute("username", userName);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        //        4）如果登录成功--->>>> 返回登陆成功信息
        System.out.println("登录成功");
        request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
