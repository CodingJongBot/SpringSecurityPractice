package com.jongbot.web.first.auth.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jongbot.web.first.auth.security.projectadmin.ProjectAdminManager;
import com.jongbot.web.first.auth.security.projectuser.ProjectUserManager;


@Order(1)
@Configuration
public class MobileSecurityConfig extends WebSecurityConfigurerAdapter{


	private final ProjectUserManager userManager;
	private final ProjectAdminManager adminManager;
	
	public MobileSecurityConfig(ProjectUserManager userManager,ProjectAdminManager adminManager) {
		this.userManager = userManager;
		this.adminManager = adminManager;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userManager);
		auth.authenticationProvider(adminManager);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CustomLoginFilter filter = new CustomLoginFilter(authenticationManager());
		
		http
			.antMatcher("/api/**")
			.csrf().disable()
			.authorizeRequests()			
			.anyRequest().authenticated()
		.and()
			.httpBasic();
	}
	
}
