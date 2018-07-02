/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnikiforov
 */
@Component("jpaUserApiService")
@Transactional
public class UserApiService implements UserApiServiceInterface {

	private static final Logger LOG = Logger.getLogger(UserApiService.class.getName());

	@PersistenceContext(unitName = "globalPU")
	private EntityManager entityManager;

	@Override
	public AppUser create(AppUser user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public void delete(AppUser user) {
		entityManager.remove(user);
	}

	@Override
	public AppUser find(AppUser user) {
		final AppUser find = entityManager.find(AppUser.class, user.getUsername());
		return find;
	}

	@Override
	public AppUser findByName(String userName) {
		final AppUser found = entityManager.find(AppUser.class, userName);
		return found;
	}

	@Override
	public List<AppUser> getAllUsers() {
		final TypedQuery<AppUser> query = entityManager.createQuery("select c from AppUser c", AppUser.class);
		final List<AppUser> findAll = query.getResultList();
		return findAll;
	}

}
