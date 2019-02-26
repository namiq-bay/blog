package com.myproject.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.blog.service.BlogService;

/*
 * Controller annotasiyası ilə Spring Container bu sinifdən bir Controller
 * bean-i yaradır və sinifin methodlarındakı Request Mapping annotasiyalarını
 * yoxlayaraq gələn web request-ləri əlaqəli methodlara yönləndirir.
 */
@Controller
public class BlogController {
	
	@Autowired // Spring Container bootstrap vaxtı BlogService tipindəki bean-i  Controller beani-nə enject edir
	private BlogService blogService;
	
	@RequestMapping("/users") // sayt.com/users tipli request gəldikdə Spring bu requestin getUsers methodu tərəfindən handle edilməsini  təmin edir
	public ModelAndView getUsers(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", blogService.findUsers() ); // mav içinə findUsers() methodundan qayıdan user listini 'users' keywordu ilə set edirik
		mav.setViewName("users");
		return mav;
	}
	
	
	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/photography")
	public ModelAndView photography() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("photography");
		return mav;
	}
	
	@RequestMapping("/travel")
	public ModelAndView travel() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("travel");
		return mov;
	}
	
	@RequestMapping("/fashion")
	public ModelAndView fashion() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("fashion");
		return mov;
	}
	
	@RequestMapping("/books")
	public ModelAndView books() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("books");
		return mov;
	}
	
	@RequestMapping("/running")
	public ModelAndView running() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("running");
		return mov;
	}
	
	@RequestMapping("/about")
	public ModelAndView about() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("about");
		return mov;
	}
	
	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("contact");
		return mov;
	}
	
	@RequestMapping("/rsa")
	public ModelAndView rsa() {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("rsa");
		return mov;
	}
	
	
	@RequestMapping("/test") // RequestMapping gələn web requestlərini uyğun handler methoda yönəldir
	@ResponseBody // responseBody annotasiyası methodunun qayıtdığı String ifadənin responsun body-si olduğunu göstərir	
	public String welcome() {
		return "Welcome to my Blog!";
	}
}
