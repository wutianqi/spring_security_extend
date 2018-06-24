package com.wuqi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
/**
 * 验证失败后的处理
 * @author wuqi
 * @date 2018/06/22
 */
@Component
public class MyFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
//		super.onAuthenticationFailure(request, response, exception);
		
		request.setAttribute("error_message", "用户名或密码错误");
		request.getRequestDispatcher("/template/myLogin.jsp").forward(request, response);;
	}
	
}
