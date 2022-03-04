package com.github.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author subeiLY
 * @create 2022-03-03 21:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    @Version // 乐观锁Version注解
    private Integer version;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;   // 驼峰命名
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic // 逻辑删除
    private Integer deleted;
}