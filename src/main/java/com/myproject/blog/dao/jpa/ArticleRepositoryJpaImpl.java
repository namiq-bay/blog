package com.myproject.blog.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.blog.dao.ArticleRepository;
import com.myproject.blog.model.Article;

@Repository("articleRepository")
public class ArticleRepositoryJpaImpl implements ArticleRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Article> findAll() {
		return entityManager.createQuery("from Article", Article.class).getResultList();
	}
}
