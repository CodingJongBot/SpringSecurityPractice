package com.jongbot.web.first.user.domain;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpUser implements UserDetails {

	private String email;
	private String password;
	
	private String nickname;
	private String tel_number;
	private String address;
	private String address_detail;
	private String profile_image;
	private String oauth_naver;
	private String oauth_kakao;
	private String oauth_google;

	//TODO if use Mybatis, check SQL Query;
	private Set<SpUserAuthority> authorities;

	private boolean enabled;

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

}
