package com.github.service;

import com.github.pojo.Dept;

import java.util.List;

/**
 * @author subeiLY
 * @create 2022-02-13 10:28
 */
public interface DeptService {
    public boolean addDept(Dept dept); // 添加一个部门
    public Dept queryById(Long id); // 根据id查询部门
    public List<Dept> queryAll(); // 查询所有部门
}

