package com.jiaxuan.shop.service;

import com.jiaxuan.shop.vo.OrderVo;
import com.jiaxuan.shop.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * Author: jx
 * Date: 2019-03-24 15:57
 * Description: 订单业务类接口
 */
public interface OrderService {

    String saveOrder(OrderVo orderVo);

    Order findOrderIdByOrderNoAndCustomerId(String orderNo,Integer id);

    Order findOrderByOutTradeNo(String orderNo);

    List<OrderVo> getCustomerAllOrders(Integer id);

    Boolean modifyOrderStatusByCustomerIdAndOrderId(Integer id, Integer orderId);

    Boolean removeOrderByCustomerIdAndOrderId(Integer id, Integer removeOrderId);

    Boolean confirmOrderByCustomerIdAndOrderId(Integer id, Integer confirmOrderId);

    List<OrderVo> getDifferenceStatusOrders(Integer id,Integer status);

    Boolean modifyOrderStatusByCustomerIdAndOrderNo(Integer id, String out_trade_no);

    Boolean modifyOrderStatusByOrderNo(String out_trade_no);

    String getWxPayUrl(Order order,String ip) throws Exception;

    Map<String,String> getWxPayResultMap(Order order, String ip) throws Exception;
}
