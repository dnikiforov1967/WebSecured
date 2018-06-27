/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service.impl;

import com.example.demo.jpa.api.UserJpaRepository;
import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author dnikiforov
 */
@Component("jpaUserApiService")
public class UserApiService implements UserApiServiceInterface {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Override
	public AppUser create(AppUser user) {
		return userJpaRepository.save(user);
	}

	@Override
	public void delete(AppUser user) {
		userJpaRepository.delete(user);
	}

	@Override
	public AppUser find(AppUser user) {
		final Optional<AppUser> found = userJpaRepository.findById(user.getUsername());
		return found.orElse(null);
	}

	@Override
	public AppUser findByName(String userName) {
		final Optional<AppUser> found = userJpaRepository.findById(userName);
		return found.orElse(null);
	}

	@Override
	public List<AppUser> getAllUsers() {
		final List<AppUser> findAll = userJpaRepository.findAll();
		return findAll;
	}

}
