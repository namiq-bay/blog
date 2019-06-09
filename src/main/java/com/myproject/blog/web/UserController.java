package com.myproject.blog.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.blog.dao.UserRepository;
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

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {

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

	@RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
	public String loadUser(@PathVariable Long id, ModelMap model) {

		User user = blogService.findUser(id);
		model.put("user", user);

		return "editUser";
	}

	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = blogService.findUser(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "editUser";
		}

		blogService.updateUser(user);
		model.addAttribute("users", blogService.findUsers());
		return "users";
	}

}
