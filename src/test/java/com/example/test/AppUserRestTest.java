/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.child.WebSecuredApplication;
import com.example.child.jpa.model.AppRole;
import com.example.child.jpa.model.RoleEnum;
import com.example.child.jpa.model.AppUser;
import com.example.child.service.UserApiServiceInterface;
import com.example.child.util.CommonHelper;
import com.example.child.web.resource.AppUserResource;
import com.example.root.RootContext;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Mocked integration test
 *
 * @author dnikiforov
 */
@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "root", classes = RootContext.class),
        @ContextConfiguration(name = "child", classes = WebSecuredApplication.class)
})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc()
@WithMockUser(username = "user1", password = "1111", roles = {"ADMIN"})
@Ignore
public class AppUserRestTest {

	private static AppUser user;
	private static String jsonView;

	@MockBean(name = "jpaUserApiService")
	private UserApiServiceInterface userApiService;

	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MockMvc mockMvc;

	public AppUserRestTest() {
	}

	@BeforeClass
	public static void setUpClass() throws IOException {
		user = new AppUser("dmitry", "Qahjasha");
		user.setAppRoles(Collections.singleton(new AppRole(RoleEnum.ROLE_ADMIN)));
		final List<AppUserResource> singletonList = Collections.singletonList(new AppUserResource(user));
		jsonView = CommonHelper.transformObjectToJson(singletonList);
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
		Mockito.when(userApiService.getAllUsers()).thenReturn(Collections.singletonList(user));
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testGetAllUsers() throws Exception {
		mockMvc
				.perform(
						get("/users/getAll")
								.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andExpect(content().json(jsonView));
	}

}
