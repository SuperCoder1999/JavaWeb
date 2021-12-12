package com.atguigu.servlet;

import com.atguigu.pojo.Person;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends BaseServlet {
    protected void javaScriptAjax(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("Ajax请求 发送来了");
        Person person = new Person(1, "张三");
        //创建 Gson对象,调用其方法,将 person转换成 jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonString);
        System.out.println("发送回去");
    }
}
