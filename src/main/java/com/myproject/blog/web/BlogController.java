package com.myproject.blog.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.blog.model.Comment;
import com.myproject.blog.model.Email;
import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

/*
 * Controller annotasiyası ilə Spring Container bu sinifdən bir Controller
 * bean-i yaradır və sinifin methodlarındakı Request Mapping annotasiyalarını
 * yoxlayaraq gələn web request-ləri əlaqəli methodlara yönləndirir.
 */
@Controller
public class BlogController {

	@Autowired // Spring Container bootstrap vaxtı BlogService tipindəki bean-i Controller
				// beani-nə enject edir
	private BlogService blogService;

	@RequestMapping("/users") // sayt.com/users tipli request gəldikdə Spring bu requestin getUsers methodu
								// tərəfindən handle edilməsini təmin edir
	public ModelAndView getUsers() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", blogService.findUsers()); // mav içinə findUsers() methodundan qayıdan user listini
															// 'users' keywordu ilə set edirik
		mav.setViewName("users");
		return mav;
	}

	@RequestMapping("/articles")
	public ModelAndView getArticles() {
		ModelAndView mav = new ModelAndView();

		Map<String, Object> model = new HashMap<String, Object>();

		model.put("articles", blogService.findArticles());
		model.put("comments", blogService.findComments());

		// mav.addObject("articles", blogService.findArticles());
		// mav.addObject("comments",blogService.findComments());

		mav.addAllObjects(model);
		mav.setViewName("articles");
		return mav;

	}

	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView();

		boolean isAuthenticated;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		isAuthenticated = authentication instanceof AnonymousAuthenticationToken ? false
				: authentication.isAuthenticated();


		
		if (isAuthenticated) {
			String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
			User currentUser = blogService.findUserByUname(currentUserName);
			mav.addObject("user", currentUser);
			
		}

		mav.addObject("articles", blogService.findArticles());
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


	
	
	
	

	@RequestMapping("/rsa")
	public ModelAndView rsa() {
		ModelAndView mov = new ModelAndView();
		mov.addObject("comments", blogService.findComments());
		mov.setViewName("cs/rsa");
		return mov;
	}

	@RequestMapping("/test") // RequestMapping gələn web requestlərini uyğun handler methoda yönəldir
	@ResponseBody // responseBody annotasiyası methodunun qayıtdığı String ifadənin responsun
					// body-si olduğunu göstərir
	public String welcome() {
		return "Welcome to my Blog!";
	}

//	public String register(final ModelMap model,
//	        @ModelAttribute("userForm") @Valid final UserarioForm userForm,
//	        final BindingResult result) {
//		
//	}

//	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
//    public String checkLogin(@RequestParam String roll, @RequestParam String pass, Model model) {
//           if (blogService.existsByRollAndPass(roll, pass))
//           {
//            return "Welcome";
//           }
//            else
//           {
//               model.addAttribute("logError","logError");
//               return "Login";
//           }
//        }
//	

//	@RequestMapping(value = "/loginAdminFailed", method = RequestMethod.GET)
//    public String loginError(HttpSession session, HttpServletRequest request) {
//        session.setAttribute("message", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//        return "admin/login";    
//	
//	}
//	

	
	
	
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		
		ModelAndView mov = new ModelAndView();
		mov.setViewName("login");
		return mov;
	}
	
	
	
	
//	@GetMapping("/employees/{id}")
//	Employee one(@PathVariable Long id) {
//
//		return repository.findById(id)
//			.orElseThrow(() -> new EmployeeNotFoundException(id));
//	}


}
