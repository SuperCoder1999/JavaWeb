package com.atguigu.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建下载文件 文件名 字符串
        String downloadFileName = "3.jpg";
        //2.通过 ServletContext对象 读取要下载的文件内容 (文件类型/文件的输入流)
        ServletContext servletContext = getServletContext();
        System.out.println(servletContext);
        //2.1获取文件类型  并 通过响应头告知 浏览器返回数据类型
        String mimeType = servletContext.getMimeType("/file2/" + downloadFileName);
        response.setContentType(mimeType);
        System.out.println(mimeType);
        //2.2 获取文件 输入流
        InputStream inputStream = servletContext.getResourceAsStream("/file2/" + downloadFileName);

        //3.获取响应的 输出流
        OutputStream outputStream = response.getOutputStream();
        System.out.println(inputStream);
        System.out.println(outputStream);
        //4.读取输入流数据,赋值给输出流, 以此将要下载的文件内容传回给客户端
        IOUtils.copy(inputStream, outputStream);
    }
}
