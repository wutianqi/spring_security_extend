package com.wuqi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wuqi.dao.RoleDao;
import com.wuqi.dao.UserDao;
import com.wuqi.model.Role;
import com.wuqi.model.User;
import com.wuqi.security.MyGrantedAuthority;
import com.wuqi.security.MyUserDetails;
@Service
@Primary
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//获取用户
		User user = userDao.getByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("不存在此用户");
		}
		
		//获取用户对应的角色
		List<Role> roles = roleDao.listByUserId(user.getId());
		//将角色转成权限
		List<MyGrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role -> {
			MyGrantedAuthority authority = new MyGrantedAuthority();
			authority.setRole(role);
			authorities.add(authority);
		});
		
		MyUserDetails userDetails = new MyUserDetails();
		userDetails.setUser(user);
		userDetails.setAuthorities(authorities);
		
		return userDetails;
	}

}
