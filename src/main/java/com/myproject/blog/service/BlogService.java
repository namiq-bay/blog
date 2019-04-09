package com.myproject.blog.service;

import java.util.List;

import com.myproject.blog.exception.UserNotFoundException;
import com.myproject.blog.model.Article;
import com.myproject.blog.model.Authorities;
import com.myproject.blog.model.Comment;
import com.myproject.blog.model.User;

public interface BlogService {
	List<User> findUsers(); // Bütün User obyektlərini qaytarır
	List<User> findUsers(String lastName); // Sadəcə lastName parametrinə uyğun User obyektlərini qaytarır
	User findUser(Long id) throws UserNotFoundException; // Id-sinə görə User obyekti qaytarır, uyğun obyekt yoxdursa UserNotFoundException fırladır.	
	User findUserByUname(String username) throws UserNotFoundException;
	void createUser(User user); 
	void updateUser(User user);
	void deleteUser(Long id);
		
	
	List<Article> findArticles();
	Article findArticleByUrl(String url);
	Article findArticleById(Long id);
	void updateArticle(Article article);
	
	List<Comment> findComments();
	void createComment(Comment comment); 	

	void createAuthority(Authorities authorities);

	
}
