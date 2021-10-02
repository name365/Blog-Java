package com.louis.mango.admin.security;
import org.springframework.security.core.GrantedAuthority;

/**
 * 权限封装
 * @author Louis
 * @date Jan 14, 2019
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}