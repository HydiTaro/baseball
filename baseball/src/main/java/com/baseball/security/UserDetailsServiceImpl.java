package com.baseball.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseball.dao.UserDao;

@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
public class UserDetailsServiceImpl{
	
    @Autowired
    private UserDao userDao;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		try{
//            UserForm entity = userDao.findByName(username);
//            // 認可があればここで設定できる
//            // org.springframework.security.core.userdetails.Userにして返却する
//            // パスワードエンコーダを利用してパスワードはエンコードをかける
//            return new User(entity.getUsername(), PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(entity.getPassword()), new ArrayList<>());
//        }catch (Exception e) {
//            throw new UsernameNotFoundException("ユーザーが見つかりません");
//        }
//	}
}
