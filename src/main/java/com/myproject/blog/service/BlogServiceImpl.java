package com.myproject.blog.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.blog.dao.ArticleRepository;
import com.myproject.blog.dao.AuthorityRepository;
import com.myproject.blog.dao.CommentRepository;
import com.myproject.blog.dao.UserRepository;
import com.myproject.blog.exception.UserNotFoundException;
import com.myproject.blog.model.Article;
import com.myproject.blog.model.Authorities;
import com.myproject.blog.model.Comment;
import com.myproject.blog.model.User;

@Service
//Service annotasiyası Spring Containerin əlaqəli sinifdən bean yaratmasını təmin edir
@Transactional
//Class səviyyəsində Transactional annotasiyasını istifadə etdikdə bu sinifə aid bütün public methodlar avtomatik olaraq transactiona olacaqdır.
public class BlogServiceImpl implements BlogService {

	private UserRepository userRepository; // instance value

	private ArticleRepository articleRepository; // instance value

	private CommentRepository commentRepository; // instance value

	private AuthorityRepository authorityRepository;

	@Autowired
	/*
	 * Autowired annotasiyası ilə Spring Container userRepository tipindəki bir
	 * bean-ni BlogService bean-inin içinə bu setter method vasitəsiylə injection
	 * edir
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setArticleRepository(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Autowired
	public void setCommentRepository(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Autowired
	public void setAuthorityRepository(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public List<User> findUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findUsers(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	@Override
	public User findUser(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id);
		if (user == null)
			throw new UserNotFoundException("User not found with id: " + id);
		return user;
	}

	@Override
	public void createUser(User user) {
		userRepository.create(user);

	}

	@Override
	public void updateUser(User user) {
		userRepository.update(user);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
		// if(true) throw new RuntimeException("testing rollback");
	}

	@Override
	public List<Article> findArticles() {
		return articleRepository.findAll();
	}

	@Override
	public List<Comment> findComments() {
		return commentRepository.findAll();
	}

	@Override
	public void createComment(Comment comment) {
		commentRepository.create(comment);

	}

	@Override
	public Article findArticleById(Long id) {
		Article hit = articleRepository.hitById(id);
		return hit;
	}

	@Override
	public void updateArticle(Article article) {
		articleRepository.update(article);
	}

	@Override
	public User findUserByUname(String username) throws NoResultException {
		User user = userRepository.findByUname(username);
		if (user == null)
			throw new NoResultException("User not found with id: " + username);
		return user;

	}

	@Override
	public void createAuthority(Authorities authorities) {
		authorityRepository.create(authorities);
	}

	@Override
	public Article findArticleByUrl(String url) {
		Article article = articleRepository.findByUrl(url);

		if (article == null)
			throw new NoResultException("Article not found with url: " + url);
		return article;
	}

	@Override
	public void createArticle(Article article) {
		articleRepository.crate(article);		
	}

	@Override
	public void deleteArticle(Long id) {
		articleRepository.delete(id);
		
	}

	@Override
	public void deleteCommentsByArticle(Article article) {
		commentRepository.deleteByArticleId(article);
		
	}


}
