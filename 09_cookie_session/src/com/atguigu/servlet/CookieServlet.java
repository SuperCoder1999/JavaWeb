package com.atguigu.servlet;

import com.atguigu.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {
    protected void createCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.在服务器中 创建cookie
        Cookie cookie = new Cookie("key1", "value1");
        //2.保存到响应头的 set-cookie中
        response.addCookie(cookie);
        //3.一次性创建多个cookie
        Cookie cookie1 = new Cookie("key2", "value2");
        response.addCookie(cookie1);

        //回写一个信息
        response.getWriter().write("创建了cookie");
    }

    protected void getCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.服务器获取浏览器发送的cookies
        Cookie[] cookies = request.getCookies();

        //2.遍历cookies(遍历结果返回到浏览器)
        for (Cookie cookie :
                cookies) {
            String cookieKey = cookie.getName();
            String cookieValue = cookie.getValue();
            response.getWriter().write("key=" + cookieKey + "  value=" + cookieValue + "<br/>");
        }

        /*//2.查找特定cookie
        for (Cookie cookie :
                cookies) {
            if ("key4".equals(cookie.getName())) {
                response.getWriter().write("找到了" + cookie.getName() + "=" + cookie.getValue());
                break;
            }
        }*/

        //3.用工具类 查找 cookie
        Cookie cookie = CookieUtils.findCookie("key4", cookies);
        if (cookie == null)
            response.getWriter().write("没找到");
        else
            response.getWriter().write("找到了" + cookie.getName() + "=" + cookie.getValue());
    }

    protected void updateCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*//一: 方法一
        //1.在服务器中 创建与要修改的cookie 同名的cookie
        //2.并在创建的同时,将value赋予 新的值
        Cookie cookie = new Cookie("key1", "newValue1");
        //2.保存到响应头的 set-cookie中
        response.addCookie(cookie);

        //回写一个信息
        response.getWriter().write("修改了key1=" + cookie.getValue());*/

        //二: 通过 cookie.setValue()方法修改
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.findCookie("key1", cookies);
        if (cookie != null) {
            cookie.setValue("newValue1_2");
        }
        //通过响应头告诉浏览器 修改情况
        response.addCookie(cookie);
        //回写一个信息
        response.getWriter().write("修改了key1=" + cookie.getValue());
    }

    protected void defaultLife(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.在服务器中 创建cookie
        Cookie cookie = new Cookie("key4", "value4");
        //1.5 设置 存活时间
        cookie.setMaxAge(-1);
        //2.保存到响应头的 set-cookie中
        response.addCookie(cookie);

        //回写一个信息
        response.getWriter().write("创建了一个MaxAge=-1的cookie: key4");
    }

    protected void deleteNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.查找浏览器中的cookie,进行删除
        Cookie[] cookies = request.getCookies();
        Cookie cookie = CookieUtils.findCookie("key4", cookies);
        if (cookie != null) {
            //1.5 修改其存活时间 为0,代表立刻删除
            cookie.setMaxAge(0);
            //2.保存到响应头的 set-cookie中
            response.addCookie(cookie);
        }
        //回写一个信息
        response.getWriter().write("删除了cookie: key4");
    }

    protected void life3600(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.在服务器中 创建cookie
        Cookie cookie = new Cookie("key3600", "value3600");
        //1.5 设置 存活时间 为 3600秒
        cookie.setMaxAge(60 * 60);
        //2.保存到响应头的 set-cookie中
        response.addCookie(cookie);

        //回写一个信息
        response.getWriter().write("创建了cookie: key3600 存活时间为 1小时");
    }

    protected void setPath(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.在服务器中 创建cookie
        Cookie cookie = new Cookie("path1", "/abc");
        //1.5 设置 path = /工程路径/abc
        cookie.setPath(request.getContextPath() + "/abc");

        //1.在服务器中 创建cookie
        Cookie cookie2 = new Cookie("path2", "/abc/");
        //1.5 设置 path = /工程路径/abc
        cookie.setPath(request.getContextPath() + "/abc/");


        //1.在服务器中 创建cookie
        Cookie cookie3 = new Cookie("path3", "/index.jsp");
        //1.5 设置 path = /工程路径/abc
        cookie.setPath(request.getContextPath() + "/index.jsp");

        //2.保存到响应头的 set-cookie中
        response.addCookie(cookie);
        response.addCookie(cookie2);
        response.addCookie(cookie3);

        //回写一个信息
        response.getWriter().write("创建了三个带有Path路径的Cookie");
    }
}
