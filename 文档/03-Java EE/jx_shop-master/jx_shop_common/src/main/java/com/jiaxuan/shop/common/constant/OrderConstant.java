package com.jiaxuan.shop.common.constant;

/**
 * Author: jx
 * Date: 2019-03-24 15:09
 * Description: 订单常量
 */
public interface OrderConstant {

    /*0 表示未支付未发货*/
    int ORDER_STATUS_UNPAID_NOTSHIPPED = 0;
    /*1 表示已支付未发货*/
    int ORDER_STATUS_PAID_NOTSHIPPED = 1;
    /*2 表示已发货未收货*/
    int ORDER_STATUS_SHIPPED_UNRECEIVE = 2;
    /*3 表示完成交易*/
    int ORDER_STATUS_FINISH_DEAL = 3;
    /*5 表示取消订单*/
    int ORDER_STATUS_CANCEL_DEAL = 4;
    /*6表示删除订单*/
    int ORDER_STATUS_DELETE = 5;

}
