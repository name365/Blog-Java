package com.github.service;

import com.github.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Hystrix服务降级
 * @author subeiLY
 * @create 2022-02-24 13:40
 */

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryId(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("该ID:" + id + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭！")
                        .setDb_source("No this database in MySQL.");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
