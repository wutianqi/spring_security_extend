package com.wuqi.security;

import org.springframework.security.core.GrantedAuthority;

import com.wuqi.model.Role;

public class MyGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private Role role;

	@Override
	public String getAuthority() {
		return this.role.getRoleName();
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
