package com.myproject.blog.web;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.blog.exception.SpringException;
import com.myproject.blog.exception.UserNotFoundException;
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
		
		try{ 
			authorities.setUsername(user.getUsername());
			authorities.setAuthority("ROLE_USER");
			
			blogService.createAuthority(authorities);		
			blogService.createUser(user);	 
			
			redirectAttributes.addFlashAttribute("message", "Your registration is successfuly!");
			
			return "redirect:/login";
		} catch (Exception e) {

			
			model.addAttribute("error","This user name is already existing!");
			return "registration";
		}
		
	}
}
