package com.myproject.blog.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.swing.JOptionPane;

import org.hibernate.validator.internal.util.privilegedactions.GetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.blog.BlogApplication;
import com.myproject.blog.model.Article;
import com.myproject.blog.model.Comment;
import com.myproject.blog.model.User;
import com.myproject.blog.service.BlogService;

@Controller
public class ArticleController implements ServletContextAware {

	private ServletContext servletContext;

	@Autowired
	private BlogService blogService;

	// delete
	List<Comment> list;
	static Comment com ;

	@RequestMapping(value = "/h/editor", method = RequestMethod.GET)
	public String editor(Model model) {
		model.addAttribute("article", new Article());
		return "create-article";
	}

	@RequestMapping("/articles/{url}")
	public ModelAndView getArticlesByUrl(@PathVariable String url) {

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

	@RequestMapping(value = "/p/sendComment", method = RequestMethod.POST)
	public String handleFormSubmit(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes) {
		blogService.createComment(comment);
		redirectAttributes.addFlashAttribute("message", "Your comment will be added after checking. Thanks!");
		return "redirect:/articles/" + comment.getArticle().getUrl() + "#success";
	}

	@RequestMapping(value = "/p/searchArticle", method = RequestMethod.POST)
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

	@RequestMapping(value = "/h/articles/create", method = RequestMethod.POST)
	public String createArticle(@ModelAttribute Article article, RedirectAttributes redirectAttributes,
			Principal principal, BindingResult bindingResult) {

		User user = blogService.findUserByUname(principal.getName());

		// article cover image upload

//		article.setImageUrl(file.getOriginalFilename());

		article.setUser(user);
		article.setHit(0L);

		blogService.createArticle(article);

		return "redirect:/index";
	}

	// Update article

	@RequestMapping(value = "/h/articles/update/{id}", method = RequestMethod.GET)
	public String loadArticleByID(@PathVariable Long id, ModelMap model) {
		Article article = blogService.findArticleById(id);
		model.put("article", article);
		return "edit-article";
	}

	@RequestMapping(value = "/h/articles/update/{id}", method = RequestMethod.POST)
	public String editArticle(@ModelAttribute Article article, RedirectAttributes redirectAttributes,
			Principal principal) {

		User user = blogService.findUserByUname(principal.getName());

		article.setUser(user);

		blogService.updateArticle(article);
		return "redirect:/articles/" + article.getUrl();
	}

	@RequestMapping(value = "/h/articles/delete/{id}", method = RequestMethod.POST)
	public String deleteArticle(@PathVariable Long id) {

		Article article = blogService.findArticleById(id);

		// delete article's comments
//		blogService.deleteCommentsByArticle(article);

		// delete article
		blogService.deleteArticle(id);

		return "redirect:/";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/p/amada", method = RequestMethod.GET)
	public ModelAndView amadaGPScoordination() {
		// List<Comment> comment = blogService.findComments();

		ModelAndView mav = new ModelAndView();

		List<Comment> list = blogService.findComments();

		Comment com = list.get(list.size() - 1);
		
		String data[] = com.getCoordinate().split(" ");
		
		mav.addObject("tem", data[2]);
		mav.addObject("lat", data[1]);
		mav.addObject("lng", data[0]);
		
		
		mav.setViewName("amada");

		return mav;

	}

	public void test() {
		list = blogService.findComments();
		
		com = list.get(4);
		
		System.out.println(com.getCoordinate());
		
		
		

	}

	public static void main(String[] args) {
		ArticleController obj = new ArticleController();
		obj.test();
	}

}
