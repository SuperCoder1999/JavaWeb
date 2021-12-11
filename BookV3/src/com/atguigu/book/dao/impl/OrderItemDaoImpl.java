package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderItemDao;
import com.atguigu.book.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    //需要先创建 order数据,因为在t_order_item中有外键
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_money`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }
}
