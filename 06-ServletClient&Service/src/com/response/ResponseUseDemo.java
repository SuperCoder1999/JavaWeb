package com.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 演示 服务器响应客户端
 */
public class ResponseUseDemo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置 服务器编码 以及 告诉浏览器 响应编码
        response.setContentType("text/html; charset=utf-8");
        //或者采用:
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-type", "text/heml; charset=UTF-8");

        //获取字符流
        Writer writer = response.getWriter();
        writer.write("我最刷");
    }
}
