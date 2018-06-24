package com.wuqi.model;

import org.springframework.security.core.GrantedAuthority;

import com.wuqi.model.Role;

public class MyGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private Role role;

	@Override
	public String getAuthority() {
		return this.role.getRoleName();
	}

}
