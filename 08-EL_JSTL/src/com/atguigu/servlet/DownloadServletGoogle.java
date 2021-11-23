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
import java.net.URLEncoder;

/**
 *  下载文件扩展: 解决 文件名为中文的情况
 */
public class DownloadServletGoogle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建下载文件 文件名 字符串
        String downloadFileName = "3.jpg";

        //2.通过 ServletContext对象 读取要下载的文件内容 (文件类型/文件的输入流)
        ServletContext servletContext = getServletContext();
        //2.1获取文件类型  并 通过响应头告知 浏览器返回数据类型
        String mimeType = servletContext.getMimeType("/file2/" + downloadFileName);
        response.setContentType(mimeType);
        //2.2 告诉服务器 收到的数据 是用于下载的
        // (Content-Disposition响应头:收到的数据怎么处理,attachment:表示附件,表示下载使用;filename表示下载的文件名)
        // 将文件名 用 url编码的形式写进请求头.(因为请求头的字符集是由http决定的,只支持英文)
        String str = "attachment; filename=" + URLEncoder.encode("妹妹.jpg","UTF-8");
        response.setHeader("Content-Disposition", str);
        //2.3 获取文件 输入流
        InputStream inputStream = servletContext.getResourceAsStream("/file2/" + downloadFileName);

        //3.获取响应的 输出流
        OutputStream outputStream = response.getOutputStream();

        //4.读取输入流数据,赋值给输出流, 以此将要下载的文件内容传回给客户端
        IOUtils.copy(inputStream, outputStream);
    }
}
