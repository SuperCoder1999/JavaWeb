package com.atguigu.book.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    //private Integer totalCount;
    //private BigDecimal totalPrice;
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
        CartItem item = items.get(cartItem.getId());
        //添加的情况有两种:
        //1. 添加的商品项 已经存在于购物车
        if (item != null) {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        } else //2. 添加项 不在购物车
            items.put(cartItem.getId(), cartItem);
    }

    /**
     * 删除 商品项
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空 购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改 商品项数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        //更新也有两种情况
        //1.更新的 商品项 存在于购物车.更新数量/更新总金额
        if (item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));
        }
        //2.更新的 商品项 不在购物车.不在的情况,暂时不知道会不会遇到这种情况
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (CartItem item : items.values()) {
            totalCount += item.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {

    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
