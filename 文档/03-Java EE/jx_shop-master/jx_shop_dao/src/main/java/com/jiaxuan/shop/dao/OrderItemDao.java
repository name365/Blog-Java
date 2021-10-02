package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Author: jx
 * Date: 2019-03-24 16:06
 * Description:<描述>
 */
public interface OrderItemDao {

    int insertOrderItem(OrderItem orderItem);
    int insertOrderItemByOrderItems(@Param("orderItemList") List<OrderItem> orderItemList);
    List<OrderItem> selectOrderItemsByOrder(Integer orderId);

    List<OrderItem> selectOrderItemsByOrderIds();
}
