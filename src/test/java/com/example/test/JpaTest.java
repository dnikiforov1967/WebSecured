/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.child.WebSecuredApplication;
import com.example.child.jpa.api.RoleJpaInterface;
import com.example.child.jpa.api.UserAppJpaInterface;
import com.example.child.jpa.model.AppRole;
import com.example.child.jpa.model.AppUser;
import static com.example.child.jpa.model.RoleEnum.ROLE_ADMIN;
import static com.example.child.jpa.model.RoleEnum.ROLE_USER;
import com.example.root.RootContext;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dnikiforov
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "root", classes = RootContext.class),
        @ContextConfiguration(name = "child", classes = WebSecuredApplication.class)
})
@SpringBootTest
@Transactional
public class JpaTest {
	
	@Autowired
	private UserAppJpaInterface apiUser;
	@Autowired
	private RoleJpaInterface apiRole;
	@PersistenceContext(unitName = "globalPU")
	private EntityManager em;
	
	public JpaTest() {
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
	@Test
	public void testCreateUser() {
		AppUser appUser = new AppUser("probe","probe");
		appUser.getAppRoles().add(new AppRole(ROLE_USER));
		appUser.getAppRoles().add(new AppRole(ROLE_ADMIN));
		final AppUser saved = apiUser.saveAndFlush(appUser);
		final Set<AppRole> appRoles = saved.getAppRoles();
		assertFalse(appRoles.isEmpty());
		assertEquals(2, appRoles.size());
		em.clear();
		final AppUser find = em.find(AppUser.class, "probe");
		final Set<AppRole> findAppRoles = find.getAppRoles();
		assertFalse(findAppRoles.isEmpty());
		assertEquals(2, findAppRoles.size());
	}
}
