package com.myproject.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * Controller annotasiyası ilə Spring Container bu sinifdən bir Controller
 * bean-i yaradır və sinifin methodlarındakı Request Mapping annotasiyalarını
 * yoxlayaraq gələn web request-ləri əlaqəli methodlara yönləndirir.
 */
@Controller
public class BlogController {
	
	@RequestMapping("/pcs") // RequestMapping gələn web requestlərini uyğun handler methoda yönəldir
	@ResponseBody // responseBody annotasiyası methodunun qayıtdığı String ifadənin responsun body-si olduğunu göstərir	
	public String welcome() {
		return "Welcome to my Blog!";
	}
	
	
	
	
}
