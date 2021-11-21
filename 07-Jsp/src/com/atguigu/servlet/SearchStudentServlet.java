package com.atguigu.servlet;

import com.atguigu.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取 请求参数
        //2.调用响应的 StudentService方法查询 List
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int t = i + 2;
            list.add(new Student(t, "name" + t, 20 + t, "110" + t));
        }
        //3.将 List对象保存到 request 的域数据 中
        request.setAttribute("stuList", list);
        //4.将 jsp文件 传入 requestDispatcher 对象中,创建请求转发对象
        //并调用forward()方法,传入request/response对象,进行请求转发
        request.getRequestDispatcher("/exercise/searchStudent.jsp").forward(request, response);
    }
}
