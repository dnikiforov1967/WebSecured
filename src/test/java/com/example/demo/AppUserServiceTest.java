/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.jpa.model.AppRole;
import com.example.demo.jpa.model.RoleEnum;
import com.example.demo.jpa.model.AppUser;
import com.example.demo.service.UserApiServiceInterface;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dnikiforov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AppUserServiceTest {

	@Autowired
	private UserApiServiceInterface userApiService;

	public AppUserServiceTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreate() {
		final AppUser appUser = new AppUser("dima", "dima1");
		appUser.getAppRoles().add(new AppRole(RoleEnum.ROLE_USER));
		final AppUser created = userApiService.create(appUser);
		assertEquals(1,created.getAppRoles().size());
	}

	@Test(expected = EntityExistsException.class)
	public void testCreateDouble() {
		userApiService.create(new AppUser("dima", "dima1"));
		userApiService.create(new AppUser("dima", "dima2"));
	}

	@Test()
	public void testCreateAndUpdateWithRoles() {
		AppUser appUser = new AppUser("dima", "dima1");
		Set<AppRole> appRoles = appUser.getAppRoles();
		appRoles.add(new AppRole(RoleEnum.ROLE_USER));
		userApiService.create(appUser);
		appUser = userApiService.findByName(appUser.getUsername());
		assertEquals(1, appUser.getAppRoles().size());
		appUser = new AppUser("dima", "dima2");
		appRoles = appUser.getAppRoles();
		appRoles.add(new AppRole(RoleEnum.ROLE_ADMIN));
		appRoles.add(new AppRole(RoleEnum.ROLE_USER));
		appUser = userApiService.update(appUser);
		appUser = userApiService.findByName(appUser.getUsername());
		assertEquals(2, appUser.getAppRoles().size());
	}

}
