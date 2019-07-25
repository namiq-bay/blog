package com.myproject.blog.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.myproject.blog.dao.CommentRepository;
import com.myproject.blog.model.Article;
import com.myproject.blog.model.Comment;

@Repository("commentRepository")
public class CommentRepositoryJpaImpl implements CommentRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Comment> findAll() {
		return entityManager.createQuery("from Comment", Comment.class).getResultList();

	}

	@Override
	public void create(Comment comment) {
			entityManager.persist(comment);	
	}

	@Override
	public void deleteByArticleId(Article article) {
		entityManager.createQuery("DELETE FROM Comment c WHERE c.article = :articleId")
		.setParameter("articleId", article)
		.executeUpdate();
		
	}

}
