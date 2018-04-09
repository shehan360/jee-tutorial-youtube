package com.example.j2eeapp.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.j2eeapp.dao.UserDao;
import com.example.j2eeapp.domain.UserEntity;
import com.example.j2eeapp.services.UserService;

public class UserServiceImpl implements UserService, UserDetailsService {

	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean createUser(UserEntity userEntity) {
		userDao.save(userEntity);// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user= userDao.loadUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No such user with name provided '%s'", username));
		}
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		User userDetails = new User(user.getUserName(),user.getPassword(),authorities);
		return userDetails;
	}

}
