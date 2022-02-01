package com.github.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author subeiLY
 * @create 2022-01-31 20:43
 */
// 声明为配置类
@Configuration
public class ShiroConfig {
    // 创建 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean
    getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new
                ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
        添加Shiro内置过滤器，常用的有如下过滤器：
        anon： 无需认证就可以访问
        authc： 必须认证才可以访问
        user： 如果使用了记住我功能就可以直接访问
        perms: 拥有某个资源权限才可以访问
        role： 拥有某个角色权限才可以访问
        */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
//        filterMap.put("/user/add","authc");
//        filterMap.put("/user/update","authc");
//        filterMap.put("/user/*","authc");
        // 授权过滤器
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        // 修改到要跳转的login页面；
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }

    // 创建 DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager
    getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new
                DefaultWebSecurityManager();
        // 关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 配置ShiroDialect：方言，用于 thymeleaf 和 shiro 标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    // 创建 realm 对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
