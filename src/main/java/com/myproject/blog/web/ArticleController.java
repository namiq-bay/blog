package com.myproject.blog.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.blog.model.Article;
import com.myproject.blog.model.Comment;
import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

@Controller
public class ArticleController implements ServletContextAware {

	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/uploads";

	private ServletContext servletContext;

	@Autowired
	private BlogService blogService;

//	@RequestMapping(value = "/editor", method = RequestMethod.GET)
//	public ModelAndView editor(Model model) {
//		ModelAndView mov = new ModelAndView();
//		mov.setViewName("test");
//		return mov;
//	}

	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "adminPanel";
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public String editor(Model model) {
		model.addAttribute("article", new Article());
		return "create-article";
	}

//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public String testEditor(Model model) {
//		model.addAttribute("article", new Article());
//		return "test";
//	}

//	@RequestMapping(value = "/rsa", method = RequestMethod.GET)
//	public ModelAndView rsa() {
//		ModelAndView mov = new ModelAndView();
//
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		
//		modelMap.put("articleByID", blogService.hitById(1L));
//		modelMap.put("articles", blogService.findArticles());
//		modelMap.put("comments", blogService.findComments());
//		modelMap.put("standardDate", new Date());
//
//		Article article = blogService.hitById(1L);
//		article.setHit(article.getHit()+1);
//		blogService.updateArticle(article);
//		
//		mov.addAllObjects(modelMap);
//		
//		mov.setViewName("cs/rsa");
//		return mov;
//	}

	@RequestMapping("/articles/{url}")
	public ModelAndView getArticlesByID(@PathVariable String url) {
		ModelAndView mov = new ModelAndView();
		Map<String, Object> modelMap = new HashMap<String, Object>();

		modelMap.put("article", blogService.findArticleByUrl(url));
		modelMap.put("comments", blogService.findComments());
		modelMap.put("standardDate", new Date());

		Article article = blogService.findArticleByUrl(url);
		article.setHit(article.getHit() + 1);
		blogService.updateArticle(article);

		mov.setViewName("cs/article");
		mov.addAllObjects(modelMap);
		return mov;
	}

	@ModelAttribute
	public Comment initModel() {
		return new Comment();
	}

	@RequestMapping(value = "/sendComment", method = RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes) {
		blogService.createComment(comment);
		redirectAttributes.addFlashAttribute("message", "Your comment will be added after checking. Thanks!");
		return "redirect:/articles/" + comment.getArticle().getUrl();
	}

	@RequestMapping(value = "/searchArticle", method = RequestMethod.POST)
	public ModelAndView articleSearch(@RequestParam("searchTerm") String searchTerm) {
		ModelAndView mov = new ModelAndView();
		List<Article> searchArticles = new ArrayList<Article>();

		for (Article article : blogService.findArticles())
			if (article.getTitle().toLowerCase().contains(searchTerm.toLowerCase())
					|| article.getBody().toLowerCase().contains(searchTerm.toLowerCase()))
				searchArticles.add(article);

		mov.addObject("articles", searchArticles);
		mov.setViewName("cs/results");

		return mov;
	}

	@RequestMapping(value = "/createArticle", method = RequestMethod.POST)
	public String createArticle(@RequestParam("files") MultipartFile[] files, @ModelAttribute Article article,
			RedirectAttributes redirectAttributes, Principal principal, BindingResult bindingResult) {

		User user = blogService.findUserByUname(principal.getName());

		// article header and cover images upload

		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (bindingResult.hasErrors()) {
			return "/editor";
		}

		article.setBgImage(files[0].getOriginalFilename());
		article.setImageUrl(files[1].getOriginalFilename());

		article.setUser(user);
		article.setHit(0L);

		blogService.createArticle(article);

		return "redirect:/index";
	}

	// Update article

	@RequestMapping(value = "/articles/update/{id}", method = RequestMethod.GET)
	public String loadArticleByID(@PathVariable Long id, ModelMap model) {
		Article article = blogService.findArticleById(id);
		model.put("article", article);
		return "edit-article";
	}

	@RequestMapping(value = "/articles/update/{id}", method = RequestMethod.POST)
	public String formSubmitHandler(@RequestParam("files") MultipartFile[] files, @ModelAttribute Article article,
			RedirectAttributes redirectAttributes, Principal principal) {

		User user = blogService.findUserByUname(principal.getName());

		// article header and cover images upload

		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (!files[0].isEmpty()) {
			article.setBgImage(files[0].getOriginalFilename());
			article.setImageUrl(files[1].getOriginalFilename());
		}

//		article.setAuthImageUrl(user.getImage);

		article.setUser(user);

		blogService.updateArticle(article);
		return "redirect:/articles/" + article.getUrl();
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
