package com.atguigu.book.test;

import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.dao.impl.OrderDaoImpl;
import com.atguigu.book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void savaOrder() {
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0, 1));
    }
}