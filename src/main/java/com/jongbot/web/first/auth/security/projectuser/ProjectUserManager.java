package com.jongbot.web.first.auth.security.projectuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


@Component
public class ProjectUserManager implements AuthenticationProvider, InitializingBean{

	//TODO DB에서 User 정보 읽어오기 & 인증 //InitializeBean은 Test목적으로 Bean 초기화시 User정보를 넣기 위함. 
	private Map<String, ProjectUser> projectUserDB = new HashMap<>();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		ProjectUserAuthenticationToken token = (ProjectUserAuthenticationToken) authentication;
		if(projectUserDB.containsKey(token.getCredentials())){
			ProjectUser user = projectUserDB.get(token.getCredentials());
			return ProjectUserAuthenticationToken.builder()
					.principal(user)
					.details(user.getUserName())
					.authenticated(true)
					.build();
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
//		return authentication == UsernamePasswordAuthenticationToken.class;
		return authentication == ProjectUserAuthenticationToken.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set<GrantedAuthority> role = new HashSet<GrantedAuthority>();
		role.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		List<ProjectUser> userList = new ArrayList<>();
		userList.add(new ProjectUser("user1", "UserName1", role));
		userList.add(new ProjectUser("user2", "UserName2", role));
		userList.add(new ProjectUser("user3", "UserName3", role));
		
		for(ProjectUser user : userList) {
			projectUserDB.put(user.getId(), user);	
		}
		
	}

}
