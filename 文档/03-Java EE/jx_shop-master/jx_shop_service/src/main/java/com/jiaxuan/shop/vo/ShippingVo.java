package com.jiaxuan.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: jx
 * Date: 2019-03-23 10:39
 * Description: 收货地址vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingVo {
    /*id*/
    private Integer id;
    /*收货人姓名*/
    private String receiverName;
    /*收货人的座机号码*/
    private String receiverPhone;
    /*收货人的手机号码*/
    private String receiverMobile;
    /*省份名称*/
    private String receiverProvince;
    /*城市名称*/
    private String receiverCity;
    /*区 县*/
    private String receiverDistrict;
    /*邮政编码*/
    private String zipCode;
    /*详细地址内容*/
    private String addressDetail;
}
