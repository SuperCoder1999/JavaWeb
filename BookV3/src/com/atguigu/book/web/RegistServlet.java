package com.atguigu.book.web;

import com.atguigu.book.pojo.User;
import com.atguigu.book.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取请求的参数
        String code = request.getParameter("code");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //我觉得 确认密码 不必要再检验了.这个应该由js检验的
//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (!"abcde".equalsIgnoreCase(code)) {
            //请求转发到 regist.jsp
            System.out.println("验证码错误:"+ code);
            request.setAttribute("username", userName);
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }

        System.out.println("3、检查 用户名是否可用");
//        3、检查 用户名是否可用
        UserServiceImpl userService = new UserServiceImpl();
        if (!userService.existsUsername(userName)) {
            //请求转发到 regist.jsp
            System.out.println("用户名已经存在:" + userName);
            request.setAttribute("username", userName);
            request.setAttribute("msg", "用户名已经被使用");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }
        System.out.println("添加用户");
        userService.registerUser(new User(userName, password, email));
        //请求转发到 regist_success.jsp
        request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
