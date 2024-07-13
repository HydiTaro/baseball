package com.baseball.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class WebSecurityConfig {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.formLogin(login->login
//				.loginProcessingUrl("/api/login")
////				.loginPage("/login")
////				.defaultSuccessUrl("/")// ログイン成功後のリダイレクト先URL
////				.failureUrl("/login?error")// ログイン失敗後のリダイレクト先URL
//				.permitAll()		// ログイン画面は未ログインでもアクセス可能
//			).logout(logout->logout	//ログアウト成功後のリダイレクト先URL
//					.logoutSuccessUrl("/")
//			).authorizeHttpRequests(authz->authz// URLごとの認可設定記述
//					.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//						.permitAll()
//					.requestMatchers("/")
//						.permitAll()
//					.requestMatchers("/general")
//						.hasRole("GENERAL")
//					.requestMatchers("/admin")
//						.hasRole("ADMIN")
//					.anyRequest().authenticated()
//			);
//		// 独自フィルターの利用
//        // デフォルトのAuthenticationManagerを利用する
//		var authManager = authenticationManager(http.getSharedObject(AuthenticationConfiguration.class));
//        http.addFilter(new JsonAuthenticationFilter(authManager));
////		http.csrf().ignoringAntMatchers("/sample");
//		return http.build();
//	}
//	
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
}
