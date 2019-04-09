package com.myproject.blog.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.blog.dao.AuthorityRepository;
import com.myproject.blog.model.Authorities;

@Repository("authorityRepository")
public class AuthorityRepositoryJpaImpl implements AuthorityRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void create(Authorities authorities) {		
		entityManager.persist(authorities);
	}

}
