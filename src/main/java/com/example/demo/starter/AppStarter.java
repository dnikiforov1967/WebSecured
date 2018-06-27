/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.starter;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author dnikiforov
 */
@Service
@Lazy(false)
public class AppStarter {

	private static final Logger LOG = Logger.getLogger(AppStarter.class.getName());

	@Autowired
	private UserApiServiceInterface userServiceInterface;

	@PostConstruct
	private void init() {
		ObjectMapper mapper = new ObjectMapper();
		final InputStream inputStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("defaultUserSet.json");
		try {
			final AppUser[] users = mapper.readValue(inputStream, AppUser[].class);
			Arrays.stream(users).forEach(userServiceInterface::create);
			LOG.log(Level.INFO, "Initial user set created");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
