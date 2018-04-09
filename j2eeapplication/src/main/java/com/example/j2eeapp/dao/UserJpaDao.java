package com.example.j2eeapp.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.util.Assert;

import com.example.j2eeapp.commons.dao.GenericJpaDao;
import com.example.j2eeapp.domain.UserEntity;

public class UserJpaDao extends GenericJpaDao<UserEntity, Long> implements UserDao {

	public UserJpaDao() {
		super(UserEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkAvailable(String userName) {
		Assert.notNull(userName);// TODO Auto-generated method stub

		Query query = getEntityManager().createQuery(
				"select count(*) from " + getPersistentClass().getSimpleName() + " u where u.userName=:userName")
				.setParameter("userName", userName);
		Long count = (Long) query.getSingleResult();
		return count < 1;
	}

	@Override
	public UserEntity loadUserByUsername(String userName) {
		Assert.notNull(userName);
		UserEntity user =null;
		Query query=getEntityManager().createQuery("select u from "+getPersistentClass().getSimpleName()+" u where u.userName=:userName:").setParameter("userName", userName);// TODO Auto-generated method stub
		try {
			user=(UserEntity)query.getSingleResult();
		}catch(NoResultException e) {
			//donothing
		}
		return user;
	}

}
