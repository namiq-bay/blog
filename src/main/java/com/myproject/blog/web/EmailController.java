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

	@GetMapping("/contact")
	public String contactPage(Model model) {
		model.addAttribute("email", new Email());
		return "contact";
	}

	@PostMapping("/sendEmail")
	public String emailSending(@ModelAttribute Email email) {

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo("namiqbayramov14@gmail.com");
		msg.setSubject(email.getSubject());
		msg.setText("Name: " + email.getName() + "\nEmail: " + email.getEmail() + "\nMessage: " + email.getMessage());

		mailSender.send(msg);

		return "redirect:/contact";
	}

//	@RequestMapping("/contact")
//	public ModelAndView contact() {
//		ModelAndView mov = new ModelAndView();
//		mov.setViewName("contact");
//		return mov;
//	}
//	
//	@ModelAttribute
//	public Email initModel() {
//		return new Email();
//	}
//	
//	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
//	public String emailFormHandle(@ModelAttribute Email email) {
//		
//		
//		
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setFrom("aytacmemmedova221@gmail.com");
//		msg.setTo("namiqbayramov14@gmail.com");
//		msg.setSubject(email.getSubject());
//		msg.setText(email.getMessage());
//		msg.setSubject("testSub");
//		msg.setText("Hello World: "+ email.getMessage());
//		
//		
//		mailSender.send(msg);
//		
//		return "redirect:/contact";
//		
//	}

}
