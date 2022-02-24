package com.github.service;

import com.github.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author subeiLY
 * @create 2022-02-23 15:08
 */

@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id); //根据id查询部门

    @GetMapping("/dept/list")
    public List<Dept> queryAll(); //查询所有部门

    @PostMapping(value = "/dept/add")
    public boolean addDept(Dept dept); //添加一个部门

}
