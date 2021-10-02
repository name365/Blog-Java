package com.jiaxuan.shop.service.impl;

import com.jiaxuan.shop.common.constant.OrderConstant;
import com.jiaxuan.shop.common.constant.WxPayConfig;
import com.jiaxuan.shop.common.utils.CommonUtils;
import com.jiaxuan.shop.common.utils.HttpUtils;
import com.jiaxuan.shop.common.utils.WxPayUtils;
import com.jiaxuan.shop.dao.OrderDao;
import com.jiaxuan.shop.dao.OrderItemDao;
import com.jiaxuan.shop.pojo.Order;
import com.jiaxuan.shop.pojo.OrderItem;
import com.jiaxuan.shop.service.OrderService;
import com.jiaxuan.shop.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * Author: jx
 * Date: 2019-03-24 16:28
 * Description:<描述>
 */
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    /**
     *功能描述: 提交订单创建订单以及对应的订单明细
     * @Author jx
     * @Date 18:32 2019/03/24
     * @Param [orderVo]
     * @return java.lang.String
     */
    @Override
    public String saveOrder(OrderVo orderVo) {
        //保存订单
        Order order = new Order();
        BeanUtils.copyProperties(orderVo, order);
        //将订单写入数据库
        int row = orderDao.insertOrder(order);
        //将订单明细插入到数据库中
        List<OrderItem> orderItemList = orderVo.getOrderItemList();
        List<OrderItem> newOrderItemList = new ArrayList<>();

        if (row >= 1) {
            for (OrderItem orderItem : orderItemList) {
                //int rows = orderItemDao.insertOrderItem(orderItem);
                //都属于同一个订单
                orderItem.setOrder(order);
                newOrderItemList.add(orderItem);
            }
        }
        //插入订单明细
        int rows = orderItemDao.insertOrderItemByOrderItems(newOrderItemList);

        if (rows >= 1) {
            return order.getOrderNumber();
        }
        return null;
    }

    /**
     *功能描述: 根据客户id 和订单号查询一条订单
     * @Author jx
     * @Date 18:52 2019/03/25
     * @Param [orderNo, id]
     * @return Order
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Order findOrderIdByOrderNoAndCustomerId(String orderNo, Integer id) {
        Order order = orderDao.selectOrderIdByOrderNoAndCustomerId(orderNo, id);
        if (ObjectUtils.isEmpty(order)) {
            return null;
        }
        return order;
    }

    /**
     *功能描述: 根据微信支付服务器响应的内容，拿到订单号查询是否存在
     * @Author jx
     * @Date 11:46 2019/04/04
     * @Param [outTradeNo]
     * @return Order
     */
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Order findOrderByOutTradeNo(String outTradeNo) {
        return orderDao.selectOrderByOutTradeNo(outTradeNo);
    }

    /**
     *功能描述: 获取某个用户的所有订单以及明细信息
     * @Author jx
     * @Date 14:04 2019/03/26
     * @Param [id]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @Override
    public List<OrderVo> getCustomerAllOrders(Integer id) {
        //先获取该用户的所有订单id
        List<Order> orderList = orderDao.selectAllOrderByCustomerId(id);

        List<OrderVo> orderVoList = new ArrayList<>();
        //循环遍历出每个订单对应的明细，每个封装到OrderVo中，最终返回一个orderVo的集合
        for(Order order :orderList){
            //通过订单id 查询出该笔订单的明细
            OrderVo orderVo = new OrderVo();
            List<OrderItem> orderItemList = orderItemDao.selectOrderItemsByOrder(order.getId());
            BeanUtils.copyProperties(order,orderVo);
            orderVo.setOrderItemList(orderItemList);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }

    /**
     *功能描述: 取消订单操作
     * @Author jx
     * @Date 12:01 2019/03/29
     * @Param [id, orderId]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyOrderStatusByCustomerIdAndOrderId(Integer id, Integer orderId) {
        int rows = orderDao.updateOrderStatusByCustomerIdAndOrderId(id, orderId, OrderConstant.ORDER_STATUS_CANCEL_DEAL);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 删除订单操作
     * @Author jx
     * @Date 8:53 2019/03/30
     * @Param [id, removeOrderId]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean removeOrderByCustomerIdAndOrderId(Integer id, Integer removeOrderId) {
        int rows = orderDao.updateOrderStatusByCustomerIdAndOrderId(id, removeOrderId, OrderConstant.ORDER_STATUS_DELETE);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 确认收货
     * @Author jx
     * @Date 9:40 2019/03/30
     * @Param [id, confirmOrderId]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean confirmOrderByCustomerIdAndOrderId(Integer id, Integer confirmOrderId) {
        int rows = orderDao.updateOrderStatusByCustomerIdAndOrderId(id, confirmOrderId, OrderConstant.ORDER_STATUS_FINISH_DEAL);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 获取不同状态的订单列表
     * @Author jx
     * @Date 11:20 2019/03/30
     * @Param [id, status]
     * @return java.util.List<OrderVo>
     */
    @Override
    public List<OrderVo> getDifferenceStatusOrders(Integer id,Integer status) {
        //先获取该用户的所有订单id
        List<Order> orderList = orderDao.selectOrdersByCustomerId(id,status);

        List<OrderVo> orderVoList = new ArrayList<>();
        //循环遍历出每个订单对应的明细，每个封装到OrderVo中，最终返回一个orderVo的集合
        for(Order order :orderList){
            //通过订单id 查询出该笔订单的明细
            OrderVo orderVo = new OrderVo();
            Integer orderId = order.getId();
            List<OrderItem> orderItemList = orderItemDao.selectOrderItemsByOrder(orderId);
            BeanUtils.copyProperties(order,orderVo);
            orderVo.setOrderItemList(orderItemList);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }

    /**
     *功能描述: 支付成功，修改订单状态
     * @Author jx
     * @Date 15:07 2019/04/02
     * @Param [id, out_trade_no]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyOrderStatusByCustomerIdAndOrderNo(Integer id, String out_trade_no) {
        int rows = orderDao.updateOrderStatusByCustomerIdAndOrderNo(id, out_trade_no, OrderConstant.ORDER_STATUS_PAID_NOTSHIPPED);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 支付宝异步通知，修改订单状态，异步通知session失效
     * @Author jx
     * @Date 21:45 2019/04/02
     * @Param [out_trade_no]
     * @return java.lang.Boolean
     */
    @Override
    public Boolean modifyOrderStatusByOrderNo(String out_trade_no) {
        int rows = orderDao.updateOrderStatusByOrderNo(out_trade_no, OrderConstant.ORDER_STATUS_PAID_NOTSHIPPED);
        if (rows >= 1) {
            return true;
        }
        return false;
    }

    /**
     *功能描述: 微信支付业务处理，返回微信支付二维码
     * @Author jx
     * @Date 15:23 2019/04/03
     * @Param [order, ip]
     * @return java.lang.String
     */
    @Override
    public String getWxPayUrl(Order order,String ip) throws Exception {
        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        /*必传 微信分配的公众账号ID（企业号corpid即为此appId）*/
        params.put("appid", WxPayConfig.wxpay_appId);
        /*必传 微信支付分配的商户号*/
        params.put("mch_id",WxPayConfig.wxpay_mer_id);
        //随机字符串
        params.put("nonce_str", CommonUtils.generateUUID());
        /*商品描述，必传*/
        params.put("body","jx水果");
        /*商户订单号*/
        params.put("out_trade_no",order.getOrderNumber());

        /*总金额,单位为分,由元转化为分,*/
        int price = (int) (order.getPrice() * 100);

        params.put("total_fee", String.valueOf(price));

        /*终端IP,必传*/
        params.put("spbill_create_ip",ip);
        /*微信回调地址*/
        params.put("notify_url",WxPayConfig.wxpay_callback);
        /*trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义*/
        params.put("trade_type","NATIVE");

        //sign 签名
        String sign = WxPayUtils.createSign(params, WxPayConfig.wxpay_key);
        params.put("sign",sign);

        //map转xml
        String mapToXml = WxPayUtils.mapToXml(params);

        System.out.println("mapToxml= "+ mapToXml);
        //统一下单

        String payResult = HttpUtils.doPost(WxPayConfig.UNIFIED_ORDER_URL, mapToXml, 4000);

        if (null == payResult) {
            return null;
        }

        Map<String, String> payResultMap = WxPayUtils.xmlToMap(payResult);
        System.out.println("payResult= "+ payResultMap.toString());
        if (payResultMap != null) {
            return payResultMap.get("code_url");
        }
        return null;
    }

    public Map<String,String> getWxPayResultMap(Order order,String ip) throws Exception {
        //生成签名
        SortedMap<String,String> params = new TreeMap<>();
        /*必传 微信分配的公众账号ID（企业号corpid即为此appId）*/
        params.put("appid", WxPayConfig.wxpay_appId);
        /*必传 微信支付分配的商户号*/
        params.put("mch_id",WxPayConfig.wxpay_mer_id);
        //随机字符串
        params.put("nonce_str", CommonUtils.generateUUID());
        /*商品描述，必传*/
        params.put("body","jx水果");
        /*商户订单号*/
        params.put("out_trade_no",order.getOrderNumber());

        /*总金额,单位为分,由元转化为分,*/
        int price = (int) (order.getPrice() * 100);

        params.put("total_fee", String.valueOf(price));

        /*终端IP,必传*/
        params.put("spbill_create_ip",ip);
        /*微信回调地址*/
        params.put("notify_url",WxPayConfig.wxpay_callback);
        /*trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义*/
        params.put("trade_type","NATIVE");

        //sign 签名
        String sign = WxPayUtils.createSign(params, WxPayConfig.wxpay_key);
        params.put("sign",sign);

        //map转xml
        String mapToXml = WxPayUtils.mapToXml(params);

        System.out.println("mapToxml= "+ mapToXml);
        //统一下单

        String payResult = HttpUtils.doPost(WxPayConfig.UNIFIED_ORDER_URL, mapToXml, 4000);

        if (null == payResult) {
            return null;
        }

        Map<String, String> payResultMap = WxPayUtils.xmlToMap(payResult);
        if (payResultMap != null) {
            return payResultMap;
        }
        return null;
    }
}
