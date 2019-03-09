package com.myproject.blog.web;

import java.net.URI;
import java.security.acl.Owner;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

import junit.framework.Assert;

public class BlogRestControllerTest {
	private RestTemplate restTemplate;

	@Before // Before methodu hər test methodu işləmədən öncə işləyərək içindəki kodu excute
			// edir

	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testDeleteUser() {
		restTemplate.delete("http://localhost:8080/rest/user/1");

		try {
			restTemplate.getForEntity("http://localhost:8080/rest/user/1", User.class);
			Assert.fail("hbhbh");
		} catch (HttpClientErrorException e) {
			MatcherAssert.assertThat(e.getStatusCode().value(), Matchers.equalTo(404));
		}
	}

	@Test
	public void testUpdateUser() {
		User user = restTemplate.getForObject("http://localhost:8080/rest/user/1", User.class);
		MatcherAssert.assertThat(user.getFirstName(), Matchers.equalTo("Namiq"));
		user.setFirstName("Namiq Vaqif");
		restTemplate.put("http://localhost:8080/rest/user/1", user);
		user = restTemplate.getForObject("http://localhost:8080/rest/user/1", User.class);

		MatcherAssert.assertThat(user.getFirstName(), Matchers.equalTo("Namiq Vaqif"));

	}

	@Test
	public void testCreatedUser() {
		User user = new User();
		user.setFirstName("Edgar");
		user.setLastName("Poe");

		URI location = restTemplate.postForLocation("http://localhost:8080/rest/user", user);
		/* postForLocation bizə yeni yaradılan user obyektinin adresini(URI)qaytarır. */

		User user2 = restTemplate.getForObject(location, User.class);

		MatcherAssert.assertThat(user2.getFirstName(), Matchers.equalTo(user.getFirstName()));
		MatcherAssert.assertThat(user2.getLastName(), Matchers.equalTo(user.getLastName()));

	}

	@Test
	public void testGetUser() {
		ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8080/rest/user/2", User.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Namiq"));

	}

	public void testGetUserByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http:/localhost:8080/rest/user?ln=Bayramov",
				List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Namiq", "Gunay", "Patrick", "John"));

	}

}