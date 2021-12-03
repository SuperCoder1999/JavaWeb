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

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("前台主页");
        //1.获取参数 pageNO/pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2.调用 bookService的page()方法,获取 要显示的页面对应的page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);
        //3.将page保存到 request中
        req.setAttribute("page", page);

        //如果 采用价格区间显示,就将 页面按钮都改成 这个价格区间的对应页面
//        StringBuilder url = new StringBuilder("client/bookServlet?action=pageByPrice");
//        if (req.getParameter("min") != null)
//            url.append("&min=").append(req.getParameter("min"));
//        if (req.getParameter("max") != null)
//            url.append("&max=").append(req.getParameter("max"));
//        page.setUrl(url.toString());
        //我觉得,可以直接 将 page()删除了,直接 page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);
        //因为page显示的页面就是, min如果为空max为空,即查询[0,0xffffffff]的范围.
        //而 如果min或max单方面为空,也不会报错,和不传入min或max参数是一样的
        page.setUrl("client/bookServlet?action=pageByPrice&min="+min+"&max="+max);

        //4.请求转发到 /pages/manager/book_manager.jsp 让jsp利用page中的信息显示页面
        System.out.println("zhuanfa");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
        System.out.println("转发");
    }
}
