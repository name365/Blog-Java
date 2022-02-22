package com.github.dao;

import com.github.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author subeiLY
 * @create 2022-02-13 10:25
 */
@Mapper
public interface DeptDao {
    public boolean addDept(Dept dept); // 添加一个部门
    public Dept queryById(Long id); // 根据id查询部门
    public List<Dept> queryAll(); // 查询所有部门
}
