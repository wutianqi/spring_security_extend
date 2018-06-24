package com.wuqi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import com.wuqi.security.MyAccessDecisionManager;
import com.wuqi.security.MyAuthenticationProvider;
import com.wuqi.security.MyFailureHandler;
import com.wuqi.security.MySuccessHandler;
/**
 * spring security配置类
 * @author wuqi
 * @date 2018/06/13
 */
@EnableWebSecurity
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MySuccessHandler mySuccessHandler;
	
	@Autowired
	private MyFailureHandler myFailureHandler;
	
	@Autowired
	private MyAuthenticationProvider myAuthenticationProvider;
	
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/index.jsp").permitAll() //首页任何人都可以访问
			.anyRequest().authenticated() //其他的路径均需要认证才能访问
			.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
				//通过spring secuirty提供的后处理bean的方式
				//往FilterSecurityInterceptor中注入自定义的AccessDecisionManager
				@Override
				public <O extends FilterSecurityInterceptor> O postProcess(O filterSecurity) {
					filterSecurity.setAccessDecisionManager(myAccessDecisionManager);
					return filterSecurity;
				}
			}) //其他的页面必须登录才可以访问
			.and()
				.formLogin() //表单登录
				.loginPage("/template/myLogin.jsp") //登录页面
				.loginProcessingUrl("/login") //处理登录的url
				.successHandler(mySuccessHandler) //认证成功后的处理
				.failureHandler(myFailureHandler)//认证失败后的处理
				.permitAll() //给登录页面的url，处理登录的url赋予permitAll的ConfigureAttribute，在AccessDecision中将会被放行
			.and()
				.authenticationProvider(myAuthenticationProvider) //自定义验证的provider
			.logout() //退出登录
			.logoutUrl("/logout") //退出登录的地址
			.logoutSuccessUrl("/template/logout.jsp") //退出登录后的跳转地址
			.permitAll();//给退出登录后跳转的地址打上perimitAll的ConfigureAttribute
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//配置不受secuirty拦截的路径
		web.ignoring()
		.antMatchers("/**/*.scss")
		.antMatchers("/**/*.jpg")
		.antMatchers("/**/*.gif")
		.antMatchers("/**/*.css")
		.antMatchers("/**/*.ico")
		.antMatchers("/**/*.js")
		.antMatchers("/**/*.ttf")
		.antMatchers("/**/*.svg")
		.antMatchers("/**/*.htm")
		.antMatchers("/**/*.html")
		.antMatchers("/**/*.eot")
		.antMatchers("/**/*.woff*")
		.antMatchers("/**/*.less")
		.antMatchers("/**/*.map")
		.antMatchers("/error")
		.antMatchers("/index")
		.antMatchers("/404")
		.antMatchers("/500")
		.antMatchers("/sessionTimeout")
		.antMatchers("/timeout")
		.antMatchers("/html/**")
		.antMatchers("/**/*.png")
		.antMatchers("/")
		.antMatchers("/jquery/**");
	}
	
	
	
	
	
}
