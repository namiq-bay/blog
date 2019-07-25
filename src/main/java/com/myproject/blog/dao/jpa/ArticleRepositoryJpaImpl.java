package com.myproject.blog.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.blog.dao.ArticleRepository;
import com.myproject.blog.model.Article;
import com.myproject.blog.model.User;

@Repository("articleRepository")
public class ArticleRepositoryJpaImpl implements ArticleRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Article> findAll() {
		return entityManager.createQuery("from Article ORDER BY id DESC", Article.class).getResultList();
	}

	@Override
	public Article hitById(Long id) {
		return entityManager.find(Article.class, id);
	}

	@Override
	public Article update(Article article) {
		return entityManager.merge(article);
	}

	@Override
	public Article findByUrl(String url) {
		return entityManager
				.createQuery("from Article where url = :url", Article.class)
				.setParameter("url", url)
				.getSingleResult();
				
	}

	@Override
	public void crate(Article article) {
		entityManager.persist(article);		
	}

	@Override
	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Article.class, id));
		
	}
}
