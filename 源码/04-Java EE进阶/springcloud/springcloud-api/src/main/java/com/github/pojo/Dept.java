package com.github.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author subeiLY
 * @create 2022-02-13 9:49
 */
@NoArgsConstructor
@Data
@Accessors(chain = true) // 链式写法
public class Dept implements Serializable {
    // Dept(实体类) orm mysql->Dept(表) 类表关系映射
    private Long deptno; // 主键
    private String dname; // 部门名称
    // 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存到多个不同的数据库
    private String db_source;
    public Dept(String dname) {
        this.dname = dname;
    }

    /*
    链式写法：
        Dept dept = new Dept()
        dept.setDeptno(11L).setDname("school").setDb_source("DB01");
     */
}
