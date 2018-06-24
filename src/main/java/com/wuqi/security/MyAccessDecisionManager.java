package com.wuqi.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		//获取访问的uri
		FilterInvocation secureObject = (FilterInvocation)object;
		String requestURI = secureObject.getHttpRequest().getRequestURI();
		
		//获取访问用户的权限集，并将其转成以,拼接的字符串形式
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		StringBuilder authorityBuilder = new StringBuilder();
		StringBuilder attributeBuilder = new StringBuilder();
		if(authorities != null && authorities.size() != 0) {
			for(GrantedAuthority authority : authorities) {
				authorityBuilder.append(authority.getAuthority() + ",");
			}
			authorityBuilder.deleteCharAt(authorityBuilder.length() - 1);
		}
		String authorityString = authorityBuilder.toString();
		
		if(configAttributes != null && configAttributes.size() != 0) {
			for(ConfigAttribute attribute : configAttributes) {
				attributeBuilder.append(attribute.toString() + ",");
			}
			attributeBuilder.deleteCharAt(attributeBuilder.length() - 1);
		}
		String attributeString = attributeBuilder.toString();
		
		//权限判断
		//打上permitAll标记的uri全部可以访问
		if(attributeString.contains("permitAll")) {
			return;
		}
		
		if(authorityString.contains("ROLE_ANONYMOUS")) {
			//未登录
			throw new AccessDeniedException("未登录");
		}
		
		if(authorityString.contains("admin")) {
			//admin用户可以访问任何页面
			return;
		}
		
		if(authorityString.contains("user")) {
			//user用户可以访问除了admin页面以外的其他页面
			if(!"/template/admin.jsp".equals(requestURI)) {
				return;
			}
			throw new AccessDeniedException("无权限");
		}
		
		if(!"/template/other.jsp".equals(requestURI)) {
			//没有任何角色的用户只能访问
			throw new AccessDeniedException("无权限");
		}
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		//支持所有的ConfigAttribute
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		//只支持FilterInvocation（spring security对安全对象的封装）或其子类
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
