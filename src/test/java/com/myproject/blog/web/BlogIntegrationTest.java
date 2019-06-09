package com.myproject.blog.web;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.profiles.active=dev" })
public class BlogIntegrationTest {

	@Autowired
	private BlogService blogService;

	@Test
	public void findUsers() {
		List<User> userList = blogService.findUsers();
		MatcherAssert.assertThat(userList.size(), Matchers.equalTo(4));
	}
	
	@Test
	public void findUserByID() {
		User user = blogService.findUser(1L);
		MatcherAssert.assertThat(user.getFirstName(), Matchers.equalTo("Namiq"));
	}
	
	@Test
	public void findUserByUserName() {
		User user = blogService.findUserByUname("namiq14");;
		MatcherAssert.assertThat(user.getFirstName(), Matchers.equalTo("Namiq"));
	}
}
