package com.jongbot.web.first.auth.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import com.jongbot.web.first.auth.security.projectadmin.ProjectAdminManager;
import com.jongbot.web.first.auth.security.projectuser.ProjectUserManager;
import com.jongbot.web.first.user.service.SpUserService;


@Order(0)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final SpUserService spUserService;
	
	
	public SecurityConfig(SpUserService spUserService) {
		this.spUserService=spUserService;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations(),PathRequest.toH2Console());
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(spUserService);
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
//			.headers().disable()
//			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/login").permitAll()
//			.antMatchers("/user/*").hasRole("USER")
			.antMatchers("/admin/*").hasRole("ADMIN")
				.anyRequest().authenticated()
		.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/", false)
				.failureUrl("/login-error")
		.and()
			.logout()
				.logoutSuccessUrl("/")
		.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
