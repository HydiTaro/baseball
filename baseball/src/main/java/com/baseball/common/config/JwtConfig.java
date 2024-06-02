package com.baseball.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@Configuration
//@PropertySource("classpath:${security.file}")
@ConfigurationProperties(prefix="jwt")
public class JwtConfig {
	
	private String base64Secret;
	
	private Long tokeValidityInMilliSeconds;
	
	private String cookieName;
	
	private String[] excludePath;
}
