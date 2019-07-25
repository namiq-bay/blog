package com.myproject.blog;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.myproject.blog.web.ArticleController;

@EnableJpaAuditing(auditorAwareRef = "blogAuditorAware")
@SpringBootApplication
@EnableCaching
@ComponentScan({ "com.myproject.blog", "com.myproject.web" })
public class BlogApplication {

	public static void main(String[] args) {

		SpringApplication.run(BlogApplication.class, args);
	}

}
