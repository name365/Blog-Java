package com.jiaxuan.shop.common.constant;

/**
 * Author: jx
 * Date: 2019-04-03 14:17
 * Description: 微信支付配置类
 */
public class WxPayConfig {

    /*支付公众账号的 appid*/
    public final static String wxpay_appId = "wx60efg5ublh115180";
    /*支付公众账号的 app_secret*/
    public final static String wxpay_appsecret = "8o73am7175i476qp7z86azk9zk285y75";
    /*商户id*/
    public final static String wxpay_mer_id = "6585640546";
    /*支付秘钥*/
    public final static String wxpay_key = "33C7A237Ddn7Ck1hy9Fjw8a5jSTrG4e0";
    /*支付回调地址*/
    //public final static String wxpay_callback = "http://xiaomo.mynatapp.cc/front/wxPay/callback";

    //部署服务器上
    public final static String wxpay_callback = "https://www.xmlvhy.com/xmShopFront/front/wxPay/callback";

    /*统一下单url*/
    //public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public final static String UNIFIED_ORDER_URL = "http://api.xdclass.net/pay/unifiedorder";
}
