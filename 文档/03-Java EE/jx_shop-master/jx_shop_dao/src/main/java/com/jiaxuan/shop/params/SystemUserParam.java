package com.jiaxuan.shop.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Author: jx
 * Date: 2019-03-11 13:45
 * Description: 查询的系统用户的条件参数
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserParam implements Serializable{

    /*姓名*/
    private String name;
    /*用户名*/
    private String loginName;
    /*电话*/
    private String phone;
    /*角色id*/
    private Integer roleId;
    /*状态*/
    private Integer isValid;

}
