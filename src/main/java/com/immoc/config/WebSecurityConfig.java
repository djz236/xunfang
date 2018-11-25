package com.immoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.immoc.security.AuthProvider;
import com.immoc.security.LoginAuthFailHandler;
import com.immoc.security.LoginUrlEntryPoint;

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * HTTP权限
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 资源访问权限
		http.authorizeRequests().antMatchers("/admin/login").permitAll()// 管理员登录入口
				.antMatchers("/static/**").permitAll()// 静态资源
				.antMatchers("/user/login").permitAll()// 用户登录入口
				.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/api/user/**").hasAnyRole("ADMIN", "USER")
				.and().formLogin()
				.loginProcessingUrl("/login")// 配置角色登陆处理入口
				.failureHandler(authFailHandler())
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout/page")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and().exceptionHandling()
				.authenticationEntryPoint(urlEntryPoint())
				.accessDeniedPage("/403");

		http.csrf().disable(); // 防御策略
		http.headers().frameOptions().sameOrigin();// 开启同源
		// super.configure(http);
	}

	/**
	 * 自定义认证策略
	 * 
	 * @throws Exception
	 */
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and();
		auth.authenticationProvider(authProvider()).eraseCredentials(true);
	}

	@Bean
	public AuthProvider authProvider(){
		return new AuthProvider();
	}
	
	@Bean
	public LoginUrlEntryPoint urlEntryPoint(){
		return new LoginUrlEntryPoint("/user/login");
	}
	@Bean
	public LoginAuthFailHandler authFailHandler(){
		return new LoginAuthFailHandler(urlEntryPoint());
	}
}
