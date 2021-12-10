package com.atguigu.book.web;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.impl.BookServiceImpl;
import com.atguigu.book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id参数
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.在Mysql中通过 queryBookById()找到对应的Book对象
        Book book = bookService.queryBookById(id);
        //2.5将Book对象,封装成CartItem对象
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        //3.采用 "懒汉模式" 在Session域中创建一个 Cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //4.调用 addItem()方法,添加book,到购物车
        cart.addItem(cartItem);
        //4.5 将刚刚添加的商品名保存到 session域中,回显到index.jsp中
        request.getSession().setAttribute("lastName", cartItem.getName());
        //5.重定向 到 referer 中
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id参数
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2.从 Session域中 获取 cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //3.调用 cart.deleteItem()方法 删除
        if (cart != null) {
            cart.deleteItem(id);
        }
        //4.重定向 到 referer
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取 cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //2.调用 cart.clear()方法清空购物车
        if (cart != null) {
            cart.clear();
        }
        //3.重定向 到 Referer
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取request中的id,count参数
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        //2.在session域中找到 cart对象,调用cart对象的updateCount()方法
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if (cart != null)
            cart.updateCount(id, count);
        //3.重定向到Referer中.
        response.sendRedirect(request.getHeader("Referer"));
    }
}