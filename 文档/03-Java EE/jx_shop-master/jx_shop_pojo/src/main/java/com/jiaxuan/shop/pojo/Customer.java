package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Author: jx
 * Date: 2019-03-12 17:27
 * Description: 客户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    /*主键id*/
    private Integer id;
    /*用户姓名*/
    private String name;
    /*用户登录账户名*/
    private String loginName;
    /*用户密码*/
    private String password;
    //*用户手机号*/
    private String phone;
    /*用户地址*/
    private String address;
    /*用户状态*/
    private Integer isValid;
    /*用户注册时间*/
    private Date registDate;

}
