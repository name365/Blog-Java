package com.jiaxuan.shop.common.constant;

/**
 * Author: jx
 * Date: 2019-03-20 19:10
 * Description:<描述>
 */
public interface CartConstant {

    /*表示购物车的商品是禁用状态*/
    int CART_PRODUCT_STATUS_ISVALID = 0;

    /*表示购物车的商品是启用状态*/
    int CART_PRODUCT_STATUS_VALID = 1;

    /*表示直接购买，临时放入购物车中*/
    int CART_PRODUCT_REDIRECT_TO_CART = 2;

}
