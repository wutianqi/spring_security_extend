package com.wuqi.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
/**
 * 验证成功后的处理
 * @author wuqi
 * @date 2018/06/22
 */
@Component
public class MySuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//父类的处理已经很完善了，用父类的处理操作
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
