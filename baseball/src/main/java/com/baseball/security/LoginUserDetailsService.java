package com.baseball.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.baseball.dao.SecurityServiceDao;

public class LoginUserDetailsService implements UserDetailsService{
	
	
	private final SecurityServiceDao securityServiceDao;
	
	
	public LoginUserDetailsService(SecurityServiceDao securityServiceDao) {
		this.securityServiceDao = securityServiceDao;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<LoginUser> user0p = securityServiceDao.getLoginUserByEmail(email);
		return user0p.map(user->new LoginUserDetails(user))
				.orElseThrow(()-> new UsernameNotFoundException("not found"));
	}
}
