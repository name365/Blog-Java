package com.jiaxuan.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: jx
 * Date: 2019-03-11 12:58
 * Description:<描述>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserVo {
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
    /*角色id*/
    private Integer roleId;
}
