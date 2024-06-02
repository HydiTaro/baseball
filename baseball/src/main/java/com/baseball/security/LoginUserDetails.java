package com.baseball.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetails implements UserDetails{

	private final LoginUser loginUser ;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public LoginUserDetails(LoginUser loginUser) {
		this.loginUser = loginUser;
		this.authorities = loginUser.roleList()
				.stream()
				.map(role->new SimpleGrantedAuthority(role))
				.toList();
	}
	
	public LoginUser getLoginUser() {return loginUser;}
	
	@Override
	public String getPassword() { return loginUser.password();}
	
	@Override
	public String getUsername() { return loginUser.email();}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
