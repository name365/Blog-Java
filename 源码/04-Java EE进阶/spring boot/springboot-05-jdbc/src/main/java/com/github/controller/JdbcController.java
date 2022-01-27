package com.github.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author subeiLY
 * @create 2022-01-26 14:35
 */
@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    /**
     *  Spring Boot 默认提供了数据源，默认提供了 org.springframework.jdbc.core.JdbcTemplate
     *  JdbcTemplate 中会自己注入数据源，用于简化 JDBC操作
     *  还能避免一些常见的错误,使用起来也不用再自己来关闭数据库连接
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *     查询employee表中所有数据
     *     List 中的1个 Map 对应数据库的 1行数据
     *     Map 中的 key 对应数据库的字段名，value 对应数据库的字段值
     */
    @GetMapping("/list")
    public List<Map<String, Object>> userList(){
        String sql = "select * from employee";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    /**
     * 新增一个用户
     */
    @GetMapping("/add")
    public String addUser(){
        // 插入语句，注意时间问题
        String sql = "insert into employee(last_name, email,gender,department,birth)" +
                " values ('夸克','243357594@qq.com',1,101,'"+ new Date().toLocaleString() +"')";
        jdbcTemplate.update(sql);
        // 查询
        return "addOk";
    }

    /**
     * 修改用户信息
     */
    @GetMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id){
        // 插入语句
        String sql = "update employee set last_name=?,email=? where id="+id;
        // 数据
        Object[] objects = new Object[2];
        objects[0] = "subei";
        objects[1] = "243357594@163.com";
        jdbcTemplate.update(sql,objects);
        // 查询
        return "updateOk";
    }

    /**
     * 删除用户
     */
    @GetMapping("/delete/{id}")
    public String delUser(@PathVariable("id") int id){
        // 删除语句
        String sql = "delete from employee where id=?";
        jdbcTemplate.update(sql,id);
        // 查询
        return "deleteOk";
    }
}
