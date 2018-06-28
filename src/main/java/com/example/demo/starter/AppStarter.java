/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.starter;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.jpa.model.AppUserRole;
import com.example.demo.jpa.model.AppUserRoleKey;
import com.example.demo.service.UserApiServiceInterface;
import com.example.demo.util.CommonHelper;
import com.example.demo.web.resource.AppUserResource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
		try {
			final AppUserResource[] userResources = CommonHelper.transformJsonToObject("defaultUserSet.json", AppUserResource[].class);
			final Stream<AppUser> users = Arrays.stream(userResources).map((t) -> {
				final AppUser appUser = new AppUser(t.getUsername(), t.getPassword());
				final Stream<AppUserRole> rolesStream = t.getRoles().stream().map((x) -> {
					return new AppUserRole(new AppUserRoleKey(appUser, x));
				});
				final Set<AppUserRole> roles = rolesStream.collect(Collectors.toSet());
				appUser.setAppUserRoles(roles);
				return appUser;
			});
			users.forEach(userServiceInterface::create);
			LOG.log(Level.WARNING, "Initial user set created");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
