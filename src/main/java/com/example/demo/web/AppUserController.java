/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.web;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author dnikiforov
 */
@RestController
@RequestMapping("/users")
public class AppUserController {

	private static final Logger LOG = Logger.getLogger(AppUserController.class.getName());
	
	@Autowired
	@Qualifier("jpaUserApiService")
	private UserApiServiceInterface userApiService;
	
	@RequestMapping(value = "/getAll", method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	@Secured({"ROLE_USER"})
	public List<AppUser> getAll() {
		LOG.log(Level.WARNING, "getAll() method invocation");
		return userApiService.getAllUsers();
	}

	@RequestMapping(value = "/", method = POST
			,produces = {MediaType.APPLICATION_JSON_VALUE}
			,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public AppUser create(@RequestBody AppUser appUser) {
		return userApiService.create(appUser);
	}	
	
}
