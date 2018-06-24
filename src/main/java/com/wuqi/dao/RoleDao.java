package com.wuqi.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.wuqi.model.Role;

@Repository
public class RoleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	/**
//	 * 根据用户名获取用户
//	 * @param username
//	 * @return
//	 */
//	public User getByUsername(String username) {
//		String sql = "select user_name userName, password from user where user_name = ?";
//		List<User> users = this.jdbcTemplate.query(sql, new Object[] {username}, new BeanPropertyRowMapper<User>(User.class));
//		if(users != null && users.size() != 0) {
//			return users.get(0);
//		}
//		return null;
//	}
	
	/**
	 * 获取用户的角色
	 * @param id
	 * @return
	 */
	public List<Role> listByUserId(long userId) {
		String sql = "SELECT r.id, r.role_name roleName FROM user u, user_role ur, role r WHERE u.id = ur.user_id AND r.id = ur.role_id AND u.id = ?";
		List<Role> roles = this.jdbcTemplate.query(sql, new Object[] {userId}, new BeanPropertyRowMapper<Role>(Role.class));
		if(roles != null && roles.size() != 0) {
			return roles;
		}
		return new ArrayList<Role>();
	}
	
}
