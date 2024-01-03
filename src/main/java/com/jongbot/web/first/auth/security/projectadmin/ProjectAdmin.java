package com.jongbot.web.first.auth.security.projectadmin;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAdmin {
	
	private String id;
	private String userName;
	private Set<GrantedAuthority> role;
	

}
