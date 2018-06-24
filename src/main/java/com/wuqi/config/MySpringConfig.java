package com.wuqi.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * spring 配置类
 * @author wuqi
 * @date 2018/06/22
 */
@Configuration
@PropertySource("classpath:c3p0.properties")
public class MySpringConfig {
	@Autowired
	private Environment e;
	
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(e.getProperty("jdbc.username"));
		dataSource.setPassword(e.getProperty("jdbc.password"));
		dataSource.setJdbcUrl(e.getProperty("jdbc.jdbcUrl"));
		dataSource.setDriverClass(e.getProperty("jdbc.driverClass"));
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return template;
	}
	
	
}
