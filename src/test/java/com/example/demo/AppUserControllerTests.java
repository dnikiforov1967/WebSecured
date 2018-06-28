package com.example.demo;

import com.example.demo.jpa.model.AppUser;
import com.example.demo.util.CommonHelper;
import com.example.demo.web.AppUserController;
import com.example.demo.web.resource.AppUserResource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "user1", password = "1111", roles = {"USER"})
public class AppUserControllerTests {

	@Autowired
	private AppUserController appUserController;

	@Test
	public void testGetAllUsers() throws IOException {
		final List<AppUserResource> all = appUserController.getAll();
		final AppUser[] array = CommonHelper.transformJsonToObject("defaultUserSet.json", AppUser[].class);
		final List<AppUserResource> fromFile = Arrays.stream(array).map(AppUserResource::new).collect(Collectors.toList());
		Collections.sort(all);
		Collections.sort(fromFile);
		assertEquals(all, fromFile);
	}

	@Test
	public void testCreateUser() throws IOException {
		AppUser appUser = new AppUser("newUser", "newPassword");
		final AppUserResource created = appUserController.create(appUser);
		assertEquals(new AppUserResource(appUser), created);
	}

}
