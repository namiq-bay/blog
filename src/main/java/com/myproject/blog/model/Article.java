package com.myproject.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_articles")
@XmlRootElement
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogSeqGen")
	@SequenceGenerator(name = "blogSeqGen", sequenceName = "blog_sequence")
	private Long id;

	@Column(name = "article_name")
	private String articleName;

	@Column(name = "category")
	private String category;

	@Column(name = "url")
	private String url;

	@Column(name = "img_url")
	private String imageUrl;

	@Column(name = "author_img_url")
	private String authImageUrl;

	@Column(name = "hit")
	private int hit;

	@Column(name = "cre_date")
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAuthImageUrl() {
		return authImageUrl;
	}

	public void setAuthImageUrl(String authImageUrl) {
		this.authImageUrl = authImageUrl;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", articleName=" + articleName + ", category=" + category + ", url=" + url
				+ ", imageUrl=" + imageUrl + ", authImageUrl=" + authImageUrl + ", hit=" + hit + ", createDate="
				+ createDate + ", user=" + user + "]";
	}
	
	
}