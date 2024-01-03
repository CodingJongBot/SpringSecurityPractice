package com.jongbot.web.first.auth.security.projectadmin;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectAdminAuthenticationToken implements Authentication {
	private ProjectAdmin principal;
	private String credentials;
	private String details;
	private boolean authenticated; 
	
	
	@Override
	public String getName() {
		return principal.getUserName()==null ? "":principal.getUserName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return principal.getRole()==null?new HashSet<GrantedAuthority>():principal.getRole();
	}

}
