package com.myproject.blog.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myproject.blog.model.Article;
import com.myproject.blog.model.Comment;
import com.myproject.blog.service.BlogService;

@Controller
public class ArticleController {

	@Autowired
	private BlogService blogService;

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

}
