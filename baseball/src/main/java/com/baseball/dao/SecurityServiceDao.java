package com.baseball.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.security.LoginUser;

@Mapper
public interface SecurityServiceDao {
	
	Optional<LoginUser> getLoginUserByEmail(@Param("conditionEntity") String email);
}
