package com.louis.spring.oauth.client.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
public class OAuthClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable()
			.antMatcher("/**")
	        .authorizeRequests()
	        .antMatchers("/", "/login**")
	        .permitAll()
	        .anyRequest()
	        .authenticated();
    }

}
