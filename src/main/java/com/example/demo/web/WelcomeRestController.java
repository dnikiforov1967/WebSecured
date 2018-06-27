/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.web;

import com.example.demo.security.resource.LoginResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author dnikiforov
 */
@RestController
@RequestMapping("/security/login")
public class WelcomeRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
	public LoginResource login(@RequestBody LoginResource loginResource) {
		UsernamePasswordAuthenticationToken token
				= new UsernamePasswordAuthenticationToken(loginResource.getUsername(), loginResource.getPassword());

		Authentication auth = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		return loginResource;
	}

}
