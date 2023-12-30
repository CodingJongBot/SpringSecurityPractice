package com.jongbot.web.first.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

//	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
//	DefaultLoginPageGeneratingFilter defaultLoginPageGeneratingFilter;
//	
//	private BCryptPasswordEncoder passwordEncoder;
//		
//	@Autowired
//	public SecurityConfig(BCryptPasswordEncoder passwordEncoder) {
//		this.passwordEncoder = passwordEncoder;
//	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser(
				User.
				withUsername("User1")
				.password(passwordEncoder().encode("1111"))
				.roles("USER")
				.build()
				)
		.withUser(
				User
				.withUsername("Admin")
				.password(passwordEncoder().encode("2222"))
				.roles("ADMIN")
				.build()
				)
		;
	}
	
	@Override
	public  void configure(WebSecurity web) throws Exception{
		web.ignoring()
			.antMatchers("/assets/**")
			.antMatchers("/static/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.headers().disable()
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/user/*").hasRole("USER")
			.antMatchers("/admin/*").hasRole("ADMIN")
			.anyRequest().authenticated()
		.and()
		
		.formLogin()
//			.loginPage("/login")
//			.successForwardUrl("/loginTest")
//			.loginProcessingUrl("/user/loginSuccess")
			.defaultSuccessUrl("/user/loginSuccess",false)
			.permitAll();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	

}
