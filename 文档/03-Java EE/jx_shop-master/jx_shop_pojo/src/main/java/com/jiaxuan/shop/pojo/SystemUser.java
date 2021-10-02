package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: jx
 * Date: 2019-03-11 9:48
 * Description: 系统用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUser implements Serializable {

    /*主键id*/
    private Integer id;
    /*用户名*/
    private String name;
    /*登录名*/
    private String loginName;
    /*登录密码*/
    private String password;
    /*手机号*/
    private String phone;
    /*用户邮箱*/
    private String email;
    /*是否禁用 0 表示禁用 1 表示 启用*/
    private Integer isValid;
    /*用户创建时间*/
    private Date createDate;
    /*角色*//*这里也直接可以放 角色id*/
    private Role role;
}

