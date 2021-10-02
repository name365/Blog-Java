package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: jx
 * Date: 2019-03-21 14:33
 * Description: 订单类目表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable{
    /*订单类目id*/
    private Integer id;
    /*商品数量*/
    private Integer num;
    /*商品小计*/
    private Double price;
    /*商品对象*/
    private Product product;
    /*所属于哪个订单，订单对象*/
    private Order order;
}
