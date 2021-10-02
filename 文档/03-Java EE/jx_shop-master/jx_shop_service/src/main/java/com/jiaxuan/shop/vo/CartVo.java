package com.jiaxuan.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: jx
 * Date: 2019-03-19 16:43
 * Description:<描述>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {

    /*购物车主键id*/
    private Integer id;
    /*客户id*/
    private Integer customerId;
    /*商品的id*/
    private Integer productId;
    /*商品数量*/
    private Integer productNum;
    /*商品总价*/
    private Double totalPrice;
}
