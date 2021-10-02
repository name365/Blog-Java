package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Author: jx
 * Date: 2019-03-21 14:14
 * Description: 订单实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    /*订单id*/
    private Integer id;
    /*订单编号*/
    private String orderNumber;
    /*客户对象*/
    private Customer customer;
    /*商品总价*/
    private Double price;
    /*订单的创建时间*/
    private Date createDate;
    /*商品数量*/
    private Integer productNumber;
    /**
     *功能描述:订单的状态
     * 0 表示未支付
     * 1 表示已经付未发货
     * 2 表示已支付已发货
     * 3 表示已发货未收货
     * 4 表示交易完成
     * 5 表示客户删除的订单，设置为无效
     */
    private Integer status;
    /*收货地址*/
    private String address;
}
