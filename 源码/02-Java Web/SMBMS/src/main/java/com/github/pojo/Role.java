package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Package: com.github.pojo
 * @ClassName: Role
 * @Author: subei
 * @CreateTime: 2021/8/5 10:23
 * @Description:
 */
@Data
public class Role {
    private Integer id; // 主键ID
    private String roleCode;    // 角色编码
    private String roleName;    // 角色名称
    private Integer createdBy;  // 创建者
    private Date creationDate;  // 创建时间
    private Integer modifyBy;   // 修改者
    private Date modifyDate;    // 修改时间

}
