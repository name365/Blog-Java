package com.louis.springboot.spring.security.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 身份验证提供者
 * @author Louis
 * @date Nov 20, 2018
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

    public JwtAuthenticationProvider(UserDetailsService userDetailsService) {
        setUserDetailsService(userDetailsService);
        setPasswordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	// 可以在此处覆写整个登录认证逻辑
    	return super.authenticate(authentication);
    }
    
    @Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
    	// 可以在此处覆写密码验证逻辑
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

}