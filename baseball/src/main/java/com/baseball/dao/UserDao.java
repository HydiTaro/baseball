package com.baseball.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baseball.security.UserForm;

@Mapper
public interface UserDao {
	
	UserForm findByName(@Param("conditionEntity")String userId);
}
