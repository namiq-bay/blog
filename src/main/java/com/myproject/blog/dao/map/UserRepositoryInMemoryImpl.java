/*
 * User obyektlərini apllication-nın yaddaşında idarə etmək üçün istifadə 
 * edilən Implementation snifi 
 * Test məqsədlidir!
 */

package com.myproject.blog.dao.map;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.myproject.blog.dao.UserRepository;
import com.myproject.blog.model.User;


//Repository annotasiyası ilə SpringContainer run time vaxtı bu sinifdən bir bean yaradır
@Repository
public class UserRepositoryInMemoryImpl implements UserRepository {

	private Map<Long, User> usersMap = new HashMap<>();

	public UserRepositoryInMemoryImpl() {
		// Nümunə user obyektləri

		User user1 = new User();
		user1.setFirstName("Namiq");
		user1.setLastName("Bayramov");
		user1.setId(1L);

		User user2 = new User();
		user2.setFirstName("Gunay");
		user2.setLastName("Salimov");
		user2.setId(2L);

		User user3 = new User();
		user3.setFirstName("Patrick");
		user3.setLastName("Süskind");
		user3.setId(3L);

		User user4 = new User();
		user4.setFirstName("John");
		user4.setLastName("Steinbeck");
		user4.setId(4L);

		/*
		 * Map-in put metodu ilə, key value olaraq User obyeklərinin id-lərini, value
		 * olaraqda obyektlərin özlərini verərək map-i populate edirik
		 */

		usersMap.put(user1.getId(), user1);
		usersMap.put(user2.getId(), user2);
		usersMap.put(user3.getId(), user3);
		usersMap.put(user4.getId(), user4);

		// usersMap obyektini istifadə edərək service-dəki bütün methodları implement
		// edə bilərik
	}

	@Override
	public List<User> findAll() {
		/*
		 * .values metodu usersMap-in bütün dəyərlərini qaytarır, .values collection
		 * qaytardığı üçün onu List-ə cast edirik
		 */

		return new ArrayList<>(usersMap.values());
	}

	@Override
	public User findById(Long id) {
		// usersMap-dəki key-ə (id) uyğun dəyər qayıdır
		return usersMap.get(id);
	}

	@Override
	public List<User> findByLastName(String lastName) {
		/*
		 * lastName-ə görə User-ləri tapmaq üçün usersMap-dəki bütün user value-lar
		 * üzərində iterate etməliyik. Sadəcə lastName input arg ilə uyğun dəyərləri
		 * qaytarmaq üçün filtr etməli və filtrlədiyimiz dəyərlər bir List-ə atmalıyıq
		 */

		return usersMap.values().stream().filter(o -> o.getLastName().equals(lastName)).collect(Collectors.toList());
	}

	@Override
	public void create(User user) {
		// java.util.Date ilə Yeni yaradılan user obyekti üçün unique id əldə edirik
		user.setId(new Date().getTime());
		usersMap.put(user.getId(), user);

	}

	@Override
	public User update(User user) {
		// id-si verilən user obyektini .replace methodu ilə update edirik
		usersMap.replace(user.getId(), user);
		return user;
	}

	@Override
	public void delete(Long id) {
		// id-si verilən user obyektini .remove methodu ilə delete edirik
		usersMap.remove(id);
		

	}

}
