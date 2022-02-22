package com.github.service.impl;

import com.github.dao.DeptDao;
import com.github.pojo.Dept;
import com.github.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author subeiLY
 * @create 2022-02-13 10:28
 */
@Service
public class DeptServiceImpl implements DeptService {
    // 自动注入
    @Autowired
    private DeptDao deptDao;
    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }
    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }
    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }
}
