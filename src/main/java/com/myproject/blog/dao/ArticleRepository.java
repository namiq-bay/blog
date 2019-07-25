package com.myproject.blog.dao;

import java.util.List;

import com.myproject.blog.model.Article;
import com.myproject.blog.model.User;

public interface ArticleRepository {
	List<Article> findAll();
	Article findByUrl(String url);	
	Article hitById(Long id);
	Article update(Article article);	
	
	void crate(Article article);
	void delete(Long id);
	
}
