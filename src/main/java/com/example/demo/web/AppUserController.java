/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.web;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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

	@Autowired
	@Qualifier("jpaUserApiService")
	private UserApiServiceInterface userApiService;
	
	@RequestMapping(value = "/getAll", method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<AppUser> getAll() {
		return userApiService.getAllUsers();
	}

	@RequestMapping(value = "/", method = POST
			,produces = {MediaType.APPLICATION_JSON_VALUE}
			,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public AppUser create(@RequestBody AppUser appUser) {
		return userApiService.create(appUser);
	}	
	
}
