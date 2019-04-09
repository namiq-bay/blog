package com.myproject.blog.web;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTests {

	@Test
	public void generateEncodedPasswords() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
		System.out.println("{bcrypt}" + passwordEncoder.encode("bruteforce"));
	}
}
