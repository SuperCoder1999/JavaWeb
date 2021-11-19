package com.response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求 重定向
 */
public class ResponseRedirect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //方式一:
        System.out.println("这个程序退休,重定向 到下一个程序");
//        response.setStatus(302);
//        response.addHeader("Location",
//                "http://localhost:8080//06_ServletClient_Service_war_exploded/responseRedirectHelp");

        //方式二
        response.sendRedirect("http://localhost:8080//06_ServletClient_Service_war_exploded/responseRedirectHelp");
    }
}