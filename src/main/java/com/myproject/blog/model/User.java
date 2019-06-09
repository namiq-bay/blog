package com.myproject.blog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity /*
		 * Verilənlər bazasında qalıcı olaraq yaradılan obyektə Entity deyilir. Obyektin
		 * entity sayıla bilməsi və verilənlər bazası tərəfindən tanınabilməsi üçün
		 * class üstündə "@Entity" annotasiyası təyin olunmalıdır. Entity sinifimiz bir
		 * Java POJO sinifidır.
		 */
@Table(name = "users") // Table annotasiyası ilə User sinifi ilə t_user table-ını əlaqəəndiririk
@XmlRootElement // User obyektlərinin xml formata dönüşdürmək üçün domain sinif üzərinə bu
				// annotasiyanı əlavə edirik.
public class User extends BaseEntity {

	@Column(name = "username", nullable = false, updatable=false)
	private String username;

	@Column(name = "first_name", nullable = false) // firstName property-sini first_name swtunu ilə əlaqələndirdik
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "password", nullable = false, updatable=false)
	private String password;

	@Column(name = "email", nullable = false, updatable=false)
	private String email;

	@OneToMany(mappedBy = "user")
	private Set<Article> articles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
//		authorities.setUsername(username);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlTransient
	@JsonIgnore
	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "User [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
