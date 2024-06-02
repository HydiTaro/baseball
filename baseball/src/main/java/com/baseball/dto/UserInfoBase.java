package com.baseball.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserInfoBase implements Serializable{
	
	private String userKbn;
	
	private String userID;
}
