package com.example.j2eeapp.dao;

import com.example.j2eeapp.commons.dao.GenericDao;
import com.example.j2eeapp.domain.UserEntity;

public interface UserDao extends GenericDao<UserEntity, Long>{
	
	boolean checkAvailable(String userName);
	
	UserEntity loadUserByUsername(String userName);
}
