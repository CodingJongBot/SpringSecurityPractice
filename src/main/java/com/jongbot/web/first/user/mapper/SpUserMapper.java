package com.jongbot.web.first.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jongbot.web.first.user.domain.SpUser;

@Mapper
public interface SpUserMapper {	
	SpUser findUserByEmail(@Param("email")String email);
	Integer save(@Param("user") SpUser user);
}
