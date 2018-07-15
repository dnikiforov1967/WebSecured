/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.child.WebSecuredApplication;
import com.example.child.jms.api.JmsApi;
import com.example.child.jms.model.JmsResult;
import com.example.child.web.resource.AppUserResource;
import com.example.root.RootContext;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dima
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "root", classes = RootContext.class),
        @ContextConfiguration(name = "child", classes = WebSecuredApplication.class)
})
@SpringBootTest
@Transactional
//@Ignore
public class JmsGeneralTest {

	@Autowired
	@Qualifier("jmsSender")
	private JmsApi jmsApi;

	public JmsGeneralTest() {
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
	public void hello() {
		AppUserResource appUserResource = new AppUserResource();
		appUserResource.setUsername("testUser");
		appUserResource.setPassword("testPassword");
		JmsResult result = jmsApi.send(appUserResource);
		assertEquals("testPassword", result.getResource().getPassword());
		result = jmsApi.send(appUserResource);
		assertNull(result.getResource());
	}
}
