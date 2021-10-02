package com.louis.spring.oauth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
	        .antMatchers("/login")
	        .antMatchers("/oauth/authorize")
	        .and()
	        .authorizeRequests()
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().loginPage("/login").permitAll()	// 自定义登录页面，这里配置了 loginPage, 就会通过 LoginController 的 login 接口加载登录页面
	        .and().csrf().disable();
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// 配置用户名密码，这里采用内存方式，生产环境需要从数据库获取
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password(passwordEncoder().encode("123"))
            .roles("USER");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
