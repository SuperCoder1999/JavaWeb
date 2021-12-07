package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    private Integer totalCount;
    private BigDecimal totalPrice;
//     因为 放商品项 的集合 不需要重复元素(重复的商品项由 商品项自己的 count属性 表明)
//     private List<CartItem> items = new ArrayList<CartItem>();
//     Integer: 是 CartItem的id属性.
//    用Map 的优点 如下:1.遍历方便(底层遍历更快).2.统计商品项数量 只要map.size()即可.
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品项
     * @return
     */
    public void addItem(CartItem cartItem) {

    }

    /**
     * 删除 商品项
     * @param id
     */
    public void deleteItem(Integer id) {

    }

    /**
     * 清空 购物车
     */
    public void clear() {

    }

    /**
     * 修改 商品项数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {

    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}
