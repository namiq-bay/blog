package com.myproject.blog.dao;

import java.util.List;

import com.myproject.blog.model.Article;
import com.myproject.blog.model.Comment;

public interface CommentRepository {
	List<Comment> findAll();
	void create(Comment comment);
	void deleteByArticleId(Article article); 
}

