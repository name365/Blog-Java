package com.github.controller;

import com.github.pojo.Dept;
import com.github.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author subeiLY
 * @create 2022-02-13 10:29
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService service;

    /**
     * DiscoveryClient 可以用来获取一些配置的信息，得到具体的微服务！
     */
    @Autowired
    private DiscoveryClient client;

    /**
     * 获取一些注册进来的微服务的信息~
     * @return
     */
    @GetMapping("/discovery")
    public Object discovery() {
        // 获取微服务列表的清单
        List<String> services = client.getServices();
        System.out.println("discovery=>services:" + services);
        // 得到一个具体的微服务信息,通过具体的微服务id，applicaioinName；
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" + // 主机名称
                            instance.getPort() + "\t" + // 端口号
                            instance.getUri() + "\t" + // uri
                            instance.getServiceId() // 服务id
            );
        }
        return this.client;
    }

    // @RequestBody
    // 如果参数是放在请求体中，传入后台的话，那么后台要用@RequestBody才能接收到
    @PostMapping("/add")
    public boolean addDept(@RequestBody Dept dept) {
        return service.addDept(dept);
    }
    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return service.queryById(id);
    }
    @GetMapping("/list")
    public List<Dept> queryAll() {
        return service.queryAll();
    }
}