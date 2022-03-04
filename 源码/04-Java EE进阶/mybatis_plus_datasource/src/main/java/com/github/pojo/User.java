package com.github.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author subeiLY
 * @create 2022-03-04 21:39
 */
@Data //lombok注解
@TableName("user")
public class User {
    @TableField
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
