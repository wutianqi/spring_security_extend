package com.wuqi.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
@Component
public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String inputPassword = (String)authentication.getCredentials();
		String actualPassword = userDetails.getPassword();
		
		if(!actualPassword.equals(inputPassword)){
			throw new BadCredentialsException("密码错误！");
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		return userDetailsService.loadUserByUsername(username);
	}

	

}
