package com.jiaxuan.shop.service;

import com.jiaxuan.shop.pojo.OrderItem;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-24 16:00
 * Description: 订单明细业务接口
 */
public interface OrderItemService {
    Boolean saveOrderItem(OrderItem orderItem);

    List<OrderItem> findOrderItemsByOrderId(Integer id);
}
