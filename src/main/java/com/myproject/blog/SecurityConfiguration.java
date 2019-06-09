package com.myproject.blog;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
				"/favicon.ico", 
				"/css/**",
				"/js/**", 
				"/images/**", 
				"/webjars/**",
				"/scss/**",
				"/fonts/**",
				"/login/**",
				"/login",
				"/reg/**",
				"/reg",
				"/createUser",
				"/articles/**",
				"/p/**",
				"/update/**",
				"/")
				.permitAll()
			.antMatchers("/pub/**").permitAll()
			.and()
				.authorizeRequests().antMatchers("/rest/**").access("hasRole('EDITOR')")
			.and()
				.authorizeRequests().antMatchers("/actuator/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
            .and()
            	.authorizeRequests().antMatchers("/editor").access("hasRole('ADMIN')")
            .and()
            .formLogin()
            	.loginPage("/login")
            	.loginProcessingUrl("/login")
            	.successForwardUrl("/")
            .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            .and()
            .exceptionHandling();
		
//		http.authorizeRequests()
//		.anyRequest()
//		.authenticated();
		
//		http.formLogin()
//		.loginPage("/login")
//		.loginProcessingUrl("/login")
//		.failureUrl("/login?loginFailed=true");
//		
		http.rememberMe()
		.userDetailsService(userDetailsService);		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}
	
}
