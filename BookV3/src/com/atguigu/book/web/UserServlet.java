package com.atguigu.book.web;

import com.atguigu.book.pojo.User;
import com.atguigu.book.service.impl.UserServiceImpl;
import com.atguigu.book.utils.WebUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

    //这个对数据库操作的类,几乎在每个方法中都要创建,所以提起出来
    UserServiceImpl userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1）获取用户名密码
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
//        2）判断用户是否存在
        //UserServiceImpl userService = new UserServiceImpl();
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


    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取请求的参数
        String code = request.getParameter("code");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //优化:利用BeanUtils 将多个请求参数,注入到JavaBean中
        User user = WebUtils.copyParaToBean(request.getParameterMap(), new User());
        System.out.println(user);

        //我觉得 确认密码 不必要再检验了.这个应该由js检验的
//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (!"abcde".equalsIgnoreCase(code)) {
            //请求转发到 regist.jsp
            System.out.println("验证码错误:"+ code);
            request.setAttribute("username", userName);
            request.setAttribute("email", email);
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }

        System.out.println("3、检查 用户名是否可用");
//        3、检查 用户名是否可用
        //UserServiceImpl userService = new UserServiceImpl();
        if (!userService.existsUsername(userName)) {
            //请求转发到 regist.jsp
            System.out.println("用户名已经存在:" + userName);
            request.setAttribute("username", userName);
            request.setAttribute("email", email);
            request.setAttribute("msg", "用户名已经被使用");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            return;
        }
        System.out.println("添加用户");
        userService.registerUser(new User(userName, password, email));
        //请求转发到 regist_success.jsp
        request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
    }
}
