package com.jongbot.web.first.auth.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jongbot.web.first.auth.security.projectadmin.ProjectAdminAuthenticationToken;
import com.jongbot.web.first.auth.security.projectuser.ProjectUserAuthenticationToken;


public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter{
	
	public CustomLoginFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
		String username = obtainUsername(request);
		username = (username != null) ? username.trim() : "";
		
		String password = obtainPassword(request);
		password = (password != null) ? password : "";
		
        String type = request.getParameter("type");
        if(type==null || !type.equals("admin")) {
        	ProjectUserAuthenticationToken token = ProjectUserAuthenticationToken.builder().credentials(username).build();
        	return this.getAuthenticationManager().authenticate(token);
//        	UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,password);
//    		return this.getAuthenticationManager().authenticate(authRequest);
        }
        else {
        	ProjectAdminAuthenticationToken token = ProjectAdminAuthenticationToken.builder().credentials(username).build();
        	return this.getAuthenticationManager().authenticate(token);
//    		UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,password);
//    		return this.getAuthenticationManager().authenticate(authRequest);
        }
		
	}
}
