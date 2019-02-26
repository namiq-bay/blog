package com.myproject.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import com.myproject.blog.exception.UserNotFoundException;
import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

@RestController // Spring Container restController ilə bu sinifdən bir Controller bean-i
				// yaradır, həmdə handler method-larına ayrı-ayrı responseBody annotasiyası
				// yazmağa ehtiyac qalmır
@RequestMapping("/rest")
public class BlogRestController {
	@Autowired // Spring Container BlogServces bean-ini injection edir
	private BlogService blogService;

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public ResponseEntity<List<User>> getOwners() {
		List<User> users = blogService.findUsers();
		return ResponseEntity.ok(users);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public ResponseEntity<List<User>> getUsers(@RequestParam("ln") String lastName) {
		List<User> users = blogService.findUsers(lastName);
		return ResponseEntity.ok(users);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

		try {
			User user = blogService.findUser(id);
			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
