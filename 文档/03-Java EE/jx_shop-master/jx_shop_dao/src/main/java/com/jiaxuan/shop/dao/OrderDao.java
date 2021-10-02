package com.jiaxuan.shop.dao;

import com.jiaxuan.shop.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author: jx
 * Date: 2019-03-24 16:29
 * Description:<描述>
 */
public interface OrderDao {

    int insertOrder(Order order);
    Order selectOrderIdByOrderNoAndCustomerId(@Param("orderNo") String orderNo, @Param("customerId") Integer id);
    List<Order> selectAllOrderByCustomerId(@Param("customerId") Integer id);
    int updateOrderStatusByCustomerIdAndOrderId(@Param("customerId") Integer id,
                                                @Param("orderId") Integer orderId,
                                                @Param("status") Integer status);

    List<Order> selectOrdersByCustomerId(@Param("customerId") Integer id,@Param("status") Integer status);

    int updateOrderStatusByCustomerIdAndOrderNo(@Param("customerId") Integer id,
                                                @Param("orderNumber") String out_trade_no,
                                                @Param("status") Integer status);

    int updateOrderStatusByOrderNo(@Param("orderNumber") String out_trade_no,
                                   @Param("status") Integer status);

    Order selectOrderByOutTradeNo(String outTradeNo);
}
