package com.jongbot.web.first.user.domain;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpUserAuthority  implements GrantedAuthority{
	private String userEmail;
	private String authority;
}
