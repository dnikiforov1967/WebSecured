/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.security.handler;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author dnikiforov
 */
public class DaoUserDetailsService implements UserDetailsService {

	private static final Logger LOG = Logger.getLogger(DaoUserDetailsService.class.getName());

	private UserApiServiceInterface userApiServiceInterface;

	public DaoUserDetailsService(UserApiServiceInterface userApiServiceInterface) {
		this.userApiServiceInterface = userApiServiceInterface;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		LOG.log(Level.INFO, "Identification of " + userName + " is calling");
		final AppUser user = userApiServiceInterface.findByName(userName);
		UserDetails userDetails = User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.build();
		return userDetails;
	}

}
