package com.atguigu.book.web;


import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.impl.BookServiceImpl;
import com.atguigu.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    //对数据库操作的类
    BookService bookService = new BookServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.将获取的参数 封装成Book对象
        Book book = WebUtils.copyParaToBean(request.getParameterMap(), new Book());
        //2.调用bookService.addBook()方法 - 并没有验证
        bookService.addBook(book);
        //3.请求重定向到 /pages/manager/book_manager.jsp - "刷新"图书列表
        response.sendRedirect(request.getContextPath()+ "/manager/bookServlet?action=list");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) {

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取请求中的参数 id
        int id = WebUtils.parseInt(request.getParameter("id"), -1);
        //2.调用bookServlet.deleteBook()方法
        bookService.deleteBookById(id);
        //3.请求重定向到 request.getContextPath()+"/manager/bookServlet?action=list"程序.
        //通过bookServlet中的list方法,请求转发到 图书列表页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    public void getBook(HttpServletRequest request, HttpServletResponse response) {

    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //由其他页面 访问BookServlet 的list方法
        //1.从数据库中 获取 book表所有内容的list对象
        List<Book> list =  bookService.queryBooks();
        //2.在request域中 添加list集合
        request.setAttribute("list", list);
        //3.请求转发到 /pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}
