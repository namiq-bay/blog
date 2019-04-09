package com.myproject.blog.dao;

import java.util.List;

import com.myproject.blog.model.User;

public interface UserRepository {
	List<User> findAll(); // Bütün userları qaytarır
	User findById(Long id); // id-yə görə User obyekti qaytarır
	User findByUname(String username); // username-ə görə User obyekti qaytarır
	List<User> findByLastName(String lastName); // LastName-ə uyğun User-ləri qaytarır
	void create(User user); // input arg olaraq User obyekti alır, heçnə qaytarmır
	User update(User user); // input arg olaraq User obyekti alır, Update olan user-ı qaytarır
 	void delete(Long id); // input arg olaraq silinəcək User obyektinin id-sini alır, heçnə qaytarmır	
}
