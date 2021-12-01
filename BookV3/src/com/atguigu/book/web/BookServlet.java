package com.atguigu.book.web;


import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Page;
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
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取参数 pageNO/pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用 bookService的page()方法,获取 要显示的页面对应的page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //3.将page保存到 request中
        req.setAttribute("page", page);
        //4.请求转发到 /pages/manager/book_manager.jsp 让jsp利用page中的信息显示页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.将获取的参数 封装成Book对象
        Book book = WebUtils.copyParaToBean(request.getParameterMap(), new Book());
        //2.调用bookService.addBook()方法 - 并没有验证
        bookService.addBook(book);
        //3.请求重定向到 /pages/manager/book_manager.jsp - "刷新"图书列表
        response.sendRedirect(request.getContextPath()+ "/manager/bookServlet?action=list");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.将传来的参数封装为Book对象
        Book book = WebUtils.copyParaToBean(request.getParameterMap(), new Book());
        //2.调用 bookService.updateBook(book)
        System.out.println(book);
        bookService.updateBook(book);
        //3.重定向到 request.getContextPath() + "/manager/bookServlet?action=list"
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.获取请求中的参数 id
        int id = WebUtils.parseInt(request.getParameter("id"), -1);
        //2.调用bookServlet.deleteBook()方法
        bookService.deleteBookById(id);
        //3.请求重定向到 request.getContextPath()+"/manager/bookServlet?action=list"程序.
        //通过bookServlet中的list方法,请求转发到 图书列表页面
        response.sendRedirect(request.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取 上传的参数 id
        int id = WebUtils.parseInt(request.getParameter("id"), -1);
        //2.调用bookService.queryBookById()方法 获取对应的 book对象
        Book book = bookService.queryBookById(id);
        //3.将book对象 存到 request域中
        request.setAttribute("book", book);
        //4.请求转发 到 /pages/manager/book_edit.jsp 页面,显示book信息
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //由其他页面 访问BookServlet 的list方法
        //1.从数据库中 获取 book表所有内容的list对象
        List<Book> list =  bookService.queryBooks();
        //2.在request域中 添加list集合
        request.setAttribute("list", list);
        //3.请求转发到 /pages/manager/book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
    }
}
