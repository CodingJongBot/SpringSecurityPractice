package com.jongbot.web.first.user.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jongbot.web.first.user.domain.SpUser;
import com.jongbot.web.first.user.domain.SpUserAuthority;
import com.jongbot.web.first.user.mapper.SpUserMapper;

@Service
//@Transactional //TODO add Dependency Mybatis or JPA 
public class SpUserService implements UserDetailsService{

	
	private SpUserMapper spUserMapper;
	
	@Autowired
	public SpUserService (SpUserMapper spUserMapper) {
		this.spUserMapper = spUserMapper;
	}	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		return spUserMapper.findUserByEmail(username);
	}	
	
	public UserDetails findUser(String email) {		
		return spUserMapper.findUserByEmail(email);
	}
	
	public void saveUser(SpUser user) {
		spUserMapper.save(user);
	}
	
	//TODO DeleteUser
	public void deleteUser(SpUser user) {
//		spUserMapper.delete(user);
	}
	
	public void addAuthority(String email, String authority) {
		SpUser user = spUserMapper.findUserByEmail(email);
		if(user ==null) return;
		SpUserAuthority newRole = new SpUserAuthority(user.getEmail(), authority);
		if(user.getAuthorities() == null) {			
			HashSet<SpUserAuthority> authorities = new HashSet<SpUserAuthority>();
			authorities.add(newRole);			
			user.setAuthorities(authorities);
			saveUser(user);			
		}
		else if(!user.getAuthorities().contains(newRole)){
			HashSet<SpUserAuthority> authorities = new HashSet<SpUserAuthority>();
			authorities.addAll(user.getAuthorities());
			authorities.add(newRole);			
			user.setAuthorities(authorities);
			saveUser(user);
		}	
	}
	
	public void removeAuthority(String email, String authority) {
		SpUser user = spUserMapper.findUserByEmail(email);
		if(user ==null) return;
		if(user.getAuthorities() == null)return; 
		
		SpUserAuthority removeRole = new SpUserAuthority(user.getEmail(), authority);
		
		 if(!user.getAuthorities().contains(removeRole)){
			HashSet<SpUserAuthority> authorities = new HashSet<SpUserAuthority>();
			authorities.addAll(user.getAuthorities());
			authorities.remove(removeRole);			
			user.setAuthorities(authorities);
			saveUser(user);
		}		
	}
	

}
