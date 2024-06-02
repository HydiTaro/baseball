package com.baseball.common.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baseball.common.Constants;
import com.baseball.common.config.JwtConfig;

@Component
public class JwtTokenService {
	
	@Autowired
	private JwtConfig jwtConfig;
	
	/**
	 * トークン認証
	 */
	public boolean validateToken(String authToken) {
		
		try{
			String secret = new String(Base64.getDecoder().decode(jwtConfig.getBase64Secret()),
				StandardCharsets.UTF_8);
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(authToken);
			return true;
		} catch (JWTDecodeException | TokenExpiredException e) {
			return false;
		}
	}
	
//	public Authentication getAuthentication(String token) {
//		// DecodewdJWT オブジェクト取得
//		DecodedJWT jwt = JWT.decode(token);
//		//権限追加
//		Collection<? extends GrantedAuthority> authorities = Arrays.stream(jwt.getClaims().toString().split(","))
//				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//		String userIdEncrypt = jwt.getClaim(Constants.JWT_ITEM_USER_ID).asString();
//		String userId = CryptUtil.decrypt(userId);
//		String principal = new User(userId,StringUtils.EMPTY,authorities);
//		return new UsernamePasswordAuthenticationToken(principal,token,authorities);
//	}
	
	/**
	 * トークン生成
	 * @param userIdEncrypt
	 * @return
	 */
	private String createToken(String userIdEncrypt) {
		String secret = new String(Base64.getDecoder().decode(jwtConfig.getBase64Secret()),
				StandardCharsets.UTF_8);
		Algorithm algorithm = Algorithm.HMAC256(secret);
		// ヘッダー部
		Map<String,Object> header = new HashMap<>();
		header.put("type", "JWT");
		header.put("alg", "HS256");
		// ペイロード部
		Map<String,Object> payload = new HashMap<>();
		payload.put(Constants.JWT_ITEM_USER_ID, userIdEncrypt);
		payload.put("alg", "HS256");
		Date expireTime=new Date(System.currentTimeMillis()+jwtConfig.getTokeValidityInMilliSeconds());
		return JWT.create().withHeader(header).withPayload(payload).withExpiresAt(expireTime).sign(algorithm);
	}
	
	public String getUserIdFromToken(String token) {
		DecodedJWT jwt=  JWT.decode(token);
		return jwt.getClaim(Constants.JWT_ITEM_USER_ID).asString();
	}
	
}
