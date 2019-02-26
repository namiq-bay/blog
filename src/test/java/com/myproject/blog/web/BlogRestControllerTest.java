package com.myproject.blog.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.myproject.blog.model.User;

public class BlogRestControllerTest {
	private RestTemplate restTemplate;

	@Before // Before methodu hər test methodu işləmədən öncə işləyərək içindəki kodu excute
			// edir

	public void setUp() {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void testGetUser() {
		ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8080/rest/user/2", User.class);		
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Namiq"));
		
	}
	public void testGetUserByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http:/localhost:8080/rest/user?ln=Bayramov", List.class );
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();		

		List<String> firstNames = body.stream().map(e->e.get("firstName")).collect(Collectors.toList());
		
		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Namiq","Gunay","Patrick", "John"));
		
	}
	
	
}