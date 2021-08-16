package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Package: com.github.pojo
 * @ClassName: User
 * @Author: 懒羊羊
 * @CreateTime: 2021/8/5 10:00
 * @Description: User实体类
 * 使用Lombok的jar包，可以不用写get/set、toString等方法
 */
@Data
public class User {
    private Integer id; // 主键ID
    private String userCode;    // 用户编码
    private String userName;    // 用户名称
    private String userPassword;    // 用户密码
    private Integer gender; // 性别
    private Date birthday;  // 出生日期
    private String phone;   // 手机
    private String address; // 地址
    private Integer userRole;   // 用户角色
    private Integer createdBy;  // 创建者
    private Date creationDate;  // 创建时间
    private Integer modifyBy;   // 更新者
    private Date modifyDate;    // 更新时间

    private Integer age;    // 年龄
    private String userRoleName;    // 用户角色名称

    public Integer getAge() {
        Date date = new Date();
        Integer age = date.getYear()-birthday.getYear();
        return age;
    }
}
