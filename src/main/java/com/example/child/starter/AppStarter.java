/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.child.starter;

import com.example.child.jpa.model.AppRole;
import com.example.child.jpa.model.AppUser;
import com.example.child.jpa.model.RoleEnum;
import com.example.child.service.RoleServiceInterface;
import com.example.child.service.UserApiServiceInterface;
import com.example.child.util.CommonHelper;
import com.example.child.web.resource.AppUserResource;
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

	@Autowired
	private RoleServiceInterface roleServiceInterface;

	@PostConstruct
	private void init() {
		try {
			final AppRole[] roles = CommonHelper.transformJsonToObject("defaultRoleSet.json", AppRole[].class);
			Arrays.stream(roles).forEach(roleServiceInterface::create);

			final AppUserResource[] userResources = CommonHelper.transformJsonToObject("defaultUserSet.json", AppUserResource[].class);
			final Stream<AppUser> users = Arrays.stream(userResources).map((t) -> {
				final AppUser appUser = new AppUser(t.getUsername(), t.getPassword());
				final Set<AppRole> collect = t.getRoles().stream().map(AppRole::new).collect(Collectors.toSet());
				appUser.setAppRoles(collect);
				return appUser;
			});
			users.forEach(userServiceInterface::create);
			LOG.log(Level.WARNING, "Initial user set created");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

}
