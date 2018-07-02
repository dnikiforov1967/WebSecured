/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.config.DatabaseConfiguration;
import com.example.demo.jpa.model.AppRole;
import com.example.demo.jpa.model.AppUser;
import com.example.demo.jpa.model.AppUserRole;
import com.example.demo.jpa.model.AppUserRoleKey;
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

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	@Test(expected = EntityExistsException.class)
	public void testCreate() {
		userApiService.create(new AppUser("dima","dima1"));
		userApiService.create(new AppUser("dima","dima2"));
	}

	@Test()
	public void testCreateAndMergeWithRoles() {
		AppUser appUser = new AppUser("dima","dima1");
		Set<AppUserRole> appUserRoles = new HashSet<>();
		appUserRoles.add(new AppUserRole(new AppUserRoleKey(appUser, AppRole.ROLE_USER)));
		appUser.setAppUserRoles(appUserRoles);
		userApiService.create(appUser);
		userApiService.clear();
		appUser = userApiService.findByName(appUser.getUsername());
		assertEquals(1,appUser.getAppUserRoles().size());
		userApiService.clear();
		appUser = new AppUser("dima","dima2");
		appUserRoles.clear();
		appUserRoles.add(new AppUserRole(new AppUserRoleKey(appUser, AppRole.ROLE_ADMIN)));
		appUserRoles.add(new AppUserRole(new AppUserRoleKey(appUser, AppRole.ROLE_USER)));
		appUser.setAppUserRoles(appUserRoles);
		appUser = userApiService.update(appUser);
		userApiService.clear();
		appUser = userApiService.findByName(appUser.getUsername());
		assertEquals(2,appUser.getAppUserRoles().size());
	}

}
