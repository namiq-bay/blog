package com.myproject.blog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "t_user") // Table annotasiyası ilə User sinifi ilə t_user table-ını əlaqəəndiririk
@XmlRootElement // User obyektlərinin xml formata dönüşdürmək üçün domain sinif üzərinə bu
				// annotasiyanı əlavə edirik.
public class User {
	@Id // id annotasiyası hansı property-nin primary key olduğunu təyin edir.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogSeqGen")
	@SequenceGenerator(name = "blogSeqGen", sequenceName = "blog_sequence")

	// id propertisini üçün sütun əlaqəsi qeyd etmədik, bu zaman JPA property adı
	// ilə sütun adını birbaşa əlaqələndrir
	private Long id;

	@Column(name = "first_name") // firstName property-sini first_name swtunu ilə əlaqələndirdik
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@OneToMany(mappedBy = "user")
	private Set<Article> articles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
