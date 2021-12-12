package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.dao.impl.BookDaoImpl;
import com.atguigu.book.dao.impl.OrderDaoImpl;
import com.atguigu.book.dao.impl.OrderItemDaoImpl;
import com.atguigu.book.pojo.*;
import com.atguigu.book.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //1.调用 OrderDao.saveOrder() 将order对象保存到数据库
        //1.1 orderId 用时间戳+userId组成,防止重复
        String orderId = System.currentTimeMillis() + "" + userId;
        //1.2 由Cart组成 Order对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);

        //int i = 12 / 0;

        //2.将 购物车Cart中的Items中的CartItem全部取出,构成一个个OrderItem,再保存到数据库
        for (Map.Entry<Integer, CartItem> entry :
                cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            //修改 Book 的销量,库存信息
            //1.通过 BookDao.queryBookById() 和 cartItem的商品Id 返回数据库中的 Book对象
            Book book = bookDao.queryBookById(cartItem.getId());
            //2.修改返回的Book对象,将修改后的Book对象作为参数 通过BookDao.updateBook()在数据库中修改对应的Book数据
            book.setStock(book.getStock() - cartItem.getCount());
            book.setSales(book.getSales() + cartItem.getCount());
            bookDao.updateBook(book);
        }
        //将购物车清空
        cart.clear();

        //返回订单号
        return orderId;
    }
}
