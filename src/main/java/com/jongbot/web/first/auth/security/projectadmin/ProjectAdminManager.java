package com.jongbot.web.first.auth.security.projectadmin;

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

import com.jongbot.web.first.auth.security.projectuser.ProjectUserAuthenticationToken;


@Component
public class ProjectAdminManager implements AuthenticationProvider, InitializingBean{

	//TODO DB에서 Admin 정보 읽어오기 & 인증 //InitializeBean은 Test목적으로 Bean 초기화시 User정보를 넣기 위함. 
	private Map<String, ProjectAdmin> projectAdminDB = new HashMap<>();
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		ProjectAdminAuthenticationToken token = (ProjectAdminAuthenticationToken) authentication;
		if(projectAdminDB.containsKey(token.getCredentials())){
			ProjectAdmin user = projectAdminDB.get(token.getCredentials());
			return ProjectAdminAuthenticationToken.builder()
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
		return authentication == ProjectAdminAuthenticationToken.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set<GrantedAuthority> role = new HashSet<GrantedAuthority>();
		role.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		List<ProjectAdmin> adminList = new ArrayList<>();
		adminList.add(new ProjectAdmin("admin1", "adminName1", role));
		adminList.add(new ProjectAdmin("admin2", "adminName2", role));
		adminList.add(new ProjectAdmin("admin3", "adminName3", role));
		
		for(ProjectAdmin admin : adminList) {
			projectAdminDB.put(admin.getId(), admin);	
		}
		
	}

}
