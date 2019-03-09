package com.myproject.blog.dao;

import java.util.List;

import com.myproject.blog.model.Article;

public interface ArticleRepository {
	List<Article> findAll();
}
