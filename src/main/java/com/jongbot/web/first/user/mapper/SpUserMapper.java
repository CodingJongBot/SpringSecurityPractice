package com.jongbot.web.first.user.mapper;

import com.jongbot.web.first.user.domain.SpUser;

//@Mapper
public interface SpUserMapper {
	
	public SpUser findUserByEmail(String email);
	public Integer save(SpUser user);

}
