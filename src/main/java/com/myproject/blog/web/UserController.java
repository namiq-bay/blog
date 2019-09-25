package com.myproject.blog.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.blog.dao.UserRepository;
import com.myproject.blog.model.Article;
import com.myproject.blog.model.Authorities;
import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

@Controller
public class UserController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public ModelAndView regPage() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("registration");
		return mov;
	}

	@ModelAttribute
	public User initModel() {
		return new User();
	}

	@RequestMapping(value = "/p/registration", method = RequestMethod.POST)
	public String userRegistration(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {

		Authorities authorities = new Authorities();

		try {
			authorities.setUsername(user.getUsername());
			authorities.setAuthority("ROLE_USER");

			user.setPassword(passwordEncoder(user.getPassword()));

			blogService.createAuthority(authorities);
			blogService.createUser(user);

			redirectAttributes.addFlashAttribute("message", "Your registration is successfuly!");

			return "redirect:/login";
		} catch (Exception e) {

			model.addAttribute("error", "This user name is already existing!");
			return "registration";
		}
	}

	public String passwordEncoder(String pass) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return "{bcrypt}" + encoder.encode(pass);
	}

	// User Editing

	@RequestMapping(method = RequestMethod.GET, value = "/h/admin/users")
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView();
		List<User> users = blogService.findUsers();

		mav.addObject("users", users);
		mav.setViewName("users");

		return mav;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/h/update/user/{id}")
	public String updateUser(@PathVariable("id") Long id, @Valid User user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			User newUser = blogService.findUser(id);
			newUser.setId(user.getId());
			newUser.setUsername(user.getUsername());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setEmail(user.getEmail());

			blogService.updateUser(user);
			redirectAttributes.addFlashAttribute("message", "User successfuly updated!");
			return "redirect:/h/admin/users";

		} catch (Exception e) {
			String message = null;
			redirectAttributes.addFlashAttribute("message", "User not updated!");
			return "redirect:/h/admin/users";
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/h/delete/user/{id}")
	public String deleteUser(@PathVariable("id") Long id, RedirectAttributes attributes) {
		blogService.deleteUser(id);
		attributes.addFlashAttribute("message", "User succesfuly deleted!");
		return "redirect:/h/admin/users";

	}

	@RequestMapping(method = RequestMethod.POST, value = "/h/users/search")
	public ModelAndView userSearch(@RequestParam("searchTerm") String searchTerm) {
		ModelAndView mov = new ModelAndView();
		List<User> users = new ArrayList<User>();

		// User user = blogService.findUserByUname(username);

		for (User user : blogService.findUsers()) {
			if (user.getUsername().toLowerCase().contains(searchTerm.toLowerCase())
					|| user.getFirstName().toLowerCase().contains(searchTerm.toLowerCase())
					|| user.getLastName().toLowerCase().contains(searchTerm.toLowerCase()))
				users.add(user);
			;
		}
		mov.addObject("users", users);
		mov.setViewName("users");

		return mov;
	}

//	@RequestMapping(method = RequestMethod.POST, value = "/create/user")
//	public String createUser(@ModelAttribute User user, BindingResult result, RedirectAttributes attributes) {
//
//		
//		try {
//			
//			user.setPassword(passwordEncoder(user.getPassword()));
//			blogService.createUser(user);
//			attributes.addFlashAttribute("message", "User created successfuly!");
//				
//		} catch (Exception e) {
//			attributes.addFlashAttribute("message", "User don't created!");
//		}
//		
//
//		return "redirect:/admin/users";
//	}

//
//	@PostMapping("/update/{id}")
//	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			user.setId(id);
//			return "editUser";
//		}
//
//		blogService.updateUser(user);
//		model.addAttribute("users", blogService.findUsers());
//		return "users";
//	}

}
