package com.atguigu.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet02 extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("接收文件");
        //1.判断请求时上传的数据 是否是多段数据(只有多段数据,才是文件上传的.也才能应用于下面的处理方式)
        if (ServletFileUpload.isMultipartContent(request)) {
            //2.创建ServletFileUpload对象,获取每一个表单项 对应的 FileItem
            //2.1创建FileItemFactory的实现类 用于 创建ServletFileUpload对象
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //2.2利用FileItemFactory对象创建 ServletFileUpload对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);

            //3.解析上传数据,即获取每一个表单项FileItem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //4.分别 获取 每一个FileItem对象, 根据其 不同属性 采用不同处理方式
                for (FileItem fileItem : list) {//如果是 普通类型的表单项
                    if (fileItem.isFormField()) {
                        String name = fileItem.getFieldName();
                        System.out.println(name);
                        String value = fileItem.getString();
                        System.out.println(value);
                    } else { //如果是 文件类型 的表单项
                        System.out.println("211");
                        String fileName = fileItem.getName();
                        fileItem.write(new File("e:/2.jpg"));
                        System.out.println(fileName);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("不是多段数据,其他方式处理");
        }
    }
}
