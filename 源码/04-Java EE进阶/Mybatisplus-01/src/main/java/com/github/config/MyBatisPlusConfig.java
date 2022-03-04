package com.github.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author subeiLY
 * @create 2022-03-04 14:34
 */
// 扫描 mapper 文件夹
@MapperScan("com.github.mapper")
@EnableTransactionManagement
@Configuration // 配置类
public class MyBatisPlusConfig {
    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    // 分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    // 逻辑删除组件
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    // SQL执行效率插件
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启，保证我们的效率
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // ms设置sql执行的最大时间，如果超过了则不执行
        performanceInterceptor.setMaxTime(100);
        // 是否格式化代码
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

}
