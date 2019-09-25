package com.myproject.blog;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Order(value=1)
@Configuration
public class RestSecurityConfiguration extends AbstractSecurityConfiguration {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//	super.configure(http);
		http.authorizeRequests().antMatchers("/rest/amada").permitAll();
		http.csrf().disable();
		//http.httpBasic();
	}
}