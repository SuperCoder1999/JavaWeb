package com.atguigu.book.service;

import com.atguigu.book.pojo.Cart;

public interface OrderService {
    //由购物车,用户Id 创建 订单
    public String createOrder(Cart cart, Integer userId);
}
