package com.github.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author subeiLY
 * @create 2022-02-13 11:24
 */
@Configuration
public class ConfigBean {

    @Bean
    public IRule myRule(){
        // 使用我们重新选择的随机算法，替代默认的轮询！
        return new RandomRule();
    }

    // @Configuration -- spring  applicationContext.xml
    // 配置负载均衡实现RestTemplate
    // IRule
    // RoundRobinRule 轮询
    // RandomRule 随机
    // AvailabilityFilteringRule ： 会先过滤掉，跳闸，访问故障的服务~，对剩下的进行轮询~
    // RetryRule ： 会先按照轮询获取服务~，如果服务获取失败，则会在指定的时间内进行，重试
    @Bean
    @LoadBalanced // Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

