package com.myproject.blog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_articles")
@XmlRootElement
public class Article {

	// article instances
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogSeqGen")
	@SequenceGenerator(name = "blogSeqGen", sequenceName = "blog_sequence")
	private Long id;

	@Column(name = "article_name")
	private String title;

	@Column(name = "category")
	private String category;

	@Column(name = "about")
	private String about;

	@Column(name = "url")
	private String url;

	@Column(name = "img_url")
	private String imageUrl;

	@Column(name = "author_img_url")
	private String authImageUrl;
	
	@Column(name="bg_img")
	private String bgImage;

	@Column(name = "hit")
	private Long hit;

	@Column(name = "article_body")
	private String body;

	@Temporal(TemporalType.DATE)
	@Column(name = "cre_date")
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "article")
	private Set<Comment> comments = new HashSet<>();

	// Comment instances
	
	public void setBgImage(String bgImage) {
		this.bgImage = bgImage;
	}
	
	public String getBgImage() {
		return bgImage;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getHit() {
		return hit;
	}

	public void setHit(Long hit) {
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
		return "Article [id=" + id + ", title=" + title + ", category=" + category + ", url=" + url
				+ ", imageUrl=" + imageUrl + ", authImageUrl=" + authImageUrl + ", hit=" + hit + ", createDate="
				+ createDate + ", user=" + user + "]";
	}

}