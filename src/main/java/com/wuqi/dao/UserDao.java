package com.wuqi.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wuqi.model.User;
@Repository()
public class UserDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据用户名获取用户
	 * @param username
	 * @return
	 */
	public User getByUsername(String username) {
		String sql = "select id, user_name userName, password from user where user_name = ?";
		List<User> users = this.jdbcTemplate.query(sql, new Object[] {username}, new BeanPropertyRowMapper<User>(User.class));
		if(users != null && users.size() != 0) {
			return users.get(0);
		}
		return null;
	}


}
