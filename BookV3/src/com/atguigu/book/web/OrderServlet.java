package com.atguigu.book.web;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;
import com.atguigu.book.service.impl.OrderServiceImpl;
import com.atguigu.book.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    OrderService orderService = new OrderServiceImpl();
    /**
     * 由 购物车 创建 订单
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.从 Session域中 获取Cart/user参数,以获取 Cart对象和UserId
        User loginUser = (User)request.getSession().getAttribute("user");
        //如果 没有登录,就请求转发 到登录页面
        if (loginUser == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Integer userId = loginUser.getId();

        String orderId = null;
        //调用 OrderService.createOrder() 生成订单,保存到数据库.
        //1.变成事务模式:
        /*首先,orderService.createOrder()调用了orderDao的方法,而orderDao调用了父类BaseDao的各种直接
        操作数据库的方法.这里每一层的方法都可能报错.先抛到orderDao中,再抛到orderService中.因为这里是数据库操作的最后一步,
        如果没有报错即可commit.现在将其异常捕获.如果有就调用rollbackAndClose().否则调用commitAndClose()*/
       /* try {
            orderId = orderService.createOrder(cart, userId);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
        }*/
        //其实这里可以捕获一次异常,orderService.createOrder()调用了orderDao的方法,而orderDao调用了父类BaseDao的各种直接
        //        操作数据库的方法.这些方法可能报错.先抛到orderDao中,再抛到orderService中.
        orderId = orderService.createOrder(cart, userId);
        //将 orderId保存到 Session域中,让接下来的网页显示 订单号
        request.getSession().setAttribute("orderId", orderId);
        //重定向到/pages/cart/checkout.jsp,显示订单号,并提示接下来操作
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

}
