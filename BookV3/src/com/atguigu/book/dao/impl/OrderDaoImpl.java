package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderDao;
import com.atguigu.book.pojo.Order;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(),
                order.getUserId());
    }
}
