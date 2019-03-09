package com.myproject.blog.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.w3c.dom.ls.LSInput;

import com.myproject.blog.exception.InternalServerException;
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

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

		try {
			blogService.deleteUser(id);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new InternalServerException(e);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User userRequest) {
		try {
			User user = blogService.findUser(id); // id-sinə görə user obyektini tapdıq
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			blogService.updateUser(user);
			return ResponseEntity.ok().build();

		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ResponseEntity<URI> createUser(@RequestBody User user) {
		try {

			blogService.createUser(user); // user yaradılır
			Long id = user.getId(); // yaradılan user obyektinin id - sini alırıq
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id)
					.toUri(); /*
								 * aldığımız id ni CurrentRrequest uri ilə birləşdirib yeni bir uri obyekti
								 * yaradırıq.
								 */
			return ResponseEntity.created(location).build(); // sonrada bu uri obyektini qaytarırıq. 201 Status Kodu
																// qayıdacaq.
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

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
