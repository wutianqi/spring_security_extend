package com.wuqi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
/**
 * MVC配置类
 * @author wuqi
 * @date 2018/06/13
 */
@EnableWebMvc
@Configuration
@ComponentScan("com.wuqi")
public class MyWebConfig extends WebMvcConfigurerAdapter {

	//配置mvc视图解析器
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
	
	//配置静态资源映射
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		//addResourceHandler("/**") 表示暴露对外访问的路径
		//addResourceLocations("/") 表示静态资源文件存储的路径
		registry.addResourceHandler("/**").addResourceLocations("/");
	}
	
	
}
