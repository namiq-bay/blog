package com.myproject.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.blog.model.Email;
import com.myproject.blog.service.BlogService;

@Controller
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping("/sendEmail")
	public String emailSending(@ModelAttribute Email email) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo("namiqbayramov14@gmail.com");
		msg.setSubject(email.getSubject());
		msg.setText("Name: " + email.getName() + "\nEmail: " + email.getEmail() + "\nMessage: " + email.getMessage());

		mailSender.send(msg);

		return "redirect:/p/contact";
	}
}
