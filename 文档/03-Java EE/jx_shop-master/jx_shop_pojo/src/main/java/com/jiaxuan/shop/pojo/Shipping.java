package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Author: jx
 * Date: 2019-03-23 9:00
 * Description: 客户收货地址
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shipping {
    /*主键id*/
    private Integer id;
    /*客户id*/
    private Integer customerId;
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
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
    /*收货地址的状态：默认为0，如果设置为默认地址后则修改为1*/
    private Integer status;
}
