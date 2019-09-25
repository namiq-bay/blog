package com.myproject.blog.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "comments")
@XmlRootElement
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogSeqGen")
	@SequenceGenerator(name = "blogSeqGen", sequenceName = "blog_sequence")
	private Long id;

	@Column(name = "accept")
	private byte accept;

	// @Size(min=3, max=30)
	// @NotEmpty
	@Column(name = "name")
	private String name;

	// @NotEmpty
	@Column(name = "email")
	private String email;

	@Column(name = "web_site")
	private String webSite;

	// @NotEmpty
	@Column(name = "comment")
	private String comment;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Column(name = "comment_date")
	private Date commentDate;

	// (cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE,
	// CascadeType.REFRESH, CascadeType.DETACH})

	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getAccept() {
		return accept;
	}

	public void setAccept(byte accept) {
		this.accept = accept;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", accept=" + accept + ", name=" + name + ", email=" + email + ", webSite="
				+ webSite + ", comment=" + comment + ", article=" + article + "]";
	}

}
