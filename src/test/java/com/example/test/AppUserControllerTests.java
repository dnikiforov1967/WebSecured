package com.example.test;

import com.example.child.WebSecuredApplication;
import com.example.child.jpa.model.AppUser;
import com.example.child.util.CommonHelper;
import com.example.child.web.AppUserController;
import com.example.child.web.resource.AppUserResource;
import com.example.root.RootContext;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextHierarchy({
        @ContextConfiguration(name = "root", classes = RootContext.class),
        @ContextConfiguration(name = "child", classes = WebSecuredApplication.class)
})
@SpringBootTest
@WithMockUser(username = "user1", password = "1111", roles = {"ADMIN"})
//@Ignore
public class AppUserControllerTests {

	@Autowired
	private AppUserController appUserController;

	@Test
	public void testGetAllUsers() throws IOException {
		final List<AppUserResource> all = appUserController.getAll();
		final AppUserResource[] resources = CommonHelper.transformJsonToObject("defaultUserSet.json", AppUserResource[].class);
		final List<AppUserResource> fromFile = Arrays.stream(resources).collect(Collectors.toList());
		Collections.sort(all);
		Collections.sort(fromFile);
		assertEquals(all, fromFile);
	}

	@Test
	public void testCreateUser() throws IOException {
		AppUser appUser = new AppUser("newUser", "newPassword");
		final AppUserResource appUserResource = new AppUserResource(appUser);
		final AppUserResource created = appUserController.create(appUserResource);
		assertEquals(appUserResource, created);
	}

}
