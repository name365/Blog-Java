package com.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author subeiLY
 * @create 2022-01-15 14:46
 */
@Configuration
/**
 * EnableConfigurationProperties的作用
 * 1、开启HelloProperties配置绑定功能
 * 2、把这个HelloProperties这个组件自动注册到容器中
 */
@ConditionalOnWebApplication // web应用生效
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    HelloProperties helloProperties;

    @Bean // @Bean 用在方法上，告诉Spring容器，你可以从下面这个方法中拿到一个Bean
    public HelloService helloService(){
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }

}
