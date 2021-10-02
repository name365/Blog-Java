package com.jiaxuan.shop.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: jx
 * Date: 2019-03-11 9:53
 * Description:角色实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /*角色id*/
    private Integer id;
    /*角色名称*/
    private String roleName;
}
