package com.atguigu.servlet;

import com.atguigu.pojo.Person;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

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

    protected void JQueryAjax(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("JQueryAjax 被访问");
        Person person = new Person(1, "张三");
        //创建 Gson对象,调用其方法,将 person转换成 jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonString);
        System.out.println("发送回去");
    }

    protected void jQueryGetAjax(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("jQueryGetAjax 被访问");
        Person person = new Person(1, "张三");
        //创建 Gson对象,调用其方法,将 person转换成 jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonString);
        System.out.println("发送回去");
    }

    protected void jQueryPostAjax(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("jQueryPostAjax 被访问");
        Person person = new Person(1, "张三");
        //创建 Gson对象,调用其方法,将 person转换成 jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonString);
        System.out.println("发送回去");
    }

    protected void getJSONAjax(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("getJSONAjax 被访问");
        Person person = new Person(1, "张三");
        //创建 Gson对象,调用其方法,将 person转换成 jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonString);
        System.out.println("发送回去");
    }

    protected void jQuerySerialize(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        System.out.println("jQuerySerialize 被访问");
        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map);
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() );
            for (String str :
                    (String[])entry.getValue()) {
                System.out.println("     " + str);
            }
        }
        Person person = new Person(1, "张三");
        //创建 Gson对象,调用其方法,将 person转换成 jsonString
        Gson gson = new Gson();
        String jsonString = gson.toJson(person);

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.getWriter().write(jsonString);
        System.out.println("发送回去");
    }
}
