package com.immoc.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;;

public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {
	private PathMatcher pathMatcher = new AntPathMatcher();
	private final Map<String, String> authEntryPointMap;

	public LoginUrlEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
		authEntryPointMap = new HashMap<>();
		// 普通用户登录入口映射
		authEntryPointMap.put("/user/**", "/user/login");
		// 管理员登陆入口映射
		authEntryPointMap.put("/admin/**", "/admin/login");

	}

	/*
	 * 根据请求跳转到制定的页面，父类是默认使用loginFormUrl
	 * 
	 * @see org.springframework.security.web.authentication.
	 * LoginUrlAuthenticationEntryPoint#determineUrlToUseForThisRequest(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {

		String uri = request.getRequestURI().replace(request.getContextPath(), "");
		for(Map.Entry<String,String> authEntry:this.authEntryPointMap.entrySet()){
			if(this.pathMatcher.match(authEntry.getKey(), uri)){
				return authEntry.getValue();
			}
		}
		
		return super.determineUrlToUseForThisRequest(request, response, exception);
	}

}
