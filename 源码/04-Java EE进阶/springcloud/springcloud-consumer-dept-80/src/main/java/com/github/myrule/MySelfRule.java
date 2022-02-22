package com.github.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author subeiLY
 * @create 2022-02-22 22:53
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        // Ribbon默认是轮询，可以自定义为随机算法
        return new MyRondomRule();
    }
}
