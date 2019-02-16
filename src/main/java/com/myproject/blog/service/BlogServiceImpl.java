package com.myproject.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.blog.dao.UserRepository;
import com.myproject.blog.exception.UserNotFoundException;
import com.myproject.blog.model.User;


@Service 
//Service annotasiyası Spring Containerin əlaqəli sinifdən bean yaratmasını təmin edir
public class BlogServiceImpl implements BlogService{

	private UserRepository userRepository; // instance value
	
	@Autowired 
	/* Autowired annotasiyası ilə Spring Container userRepository tipindəki bir 
	 * bean-ni BlogService bean-inin içinə bu setter method vasitəsiylə injection edir
	 */
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public List<User> findUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findUsers(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	@Override
	public User findUser(Long id) throws UserNotFoundException {
		User user = userRepository.findById(id);
		if(user == null) throw new UserNotFoundException("User not found with id: "+ id);
		return user;
	}

	@Override
	public void createUser(User user) {
		userRepository.create(user);
		
	}

	@Override
	public void updateUser(User user) {
		userRepository.update(user);	
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);	
	}

}
