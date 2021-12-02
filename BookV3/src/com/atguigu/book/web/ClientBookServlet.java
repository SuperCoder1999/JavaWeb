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

public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("前台主页");
        //1.获取参数 pageNO/pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用 bookService的page()方法,获取 要显示的页面对应的page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //3.将page保存到 request中
        req.setAttribute("page", page);
        page.setUrl("client/bookServlet?action=page");
        //4.请求转发到 /pages/manager/book_manager.jsp 让jsp利用page中的信息显示页面
        System.out.println("zhuanfa");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        System.out.println("转发");
    }
}
