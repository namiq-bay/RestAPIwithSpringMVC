package com.java.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.example.controller.SpringMVCRestController;
import com.java.example.dao.UserRepository;
import com.java.example.exception.UserNotFoundException;
import com.java.example.model.User;

@Service
@Transactional
public class RestAPIServiceImpl implements RestAPIService {

	private UserRepository userRepository;

	@Autowired
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
		User user = userRepository.findByID(id);

		if (user == null)
			throw new UserNotFoundException("User not found this id: " + id);
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

//	void createTest() {
//		User user = new User();
//
//		user.setId(9L);
//		user.setFirstName("Test2");
//		user.setLastName("User");
//		user.setAge(23);
//
//		userRepository.create(user);
//	}
//
//	public static void main(String[] args) {
//		RestAPIServiceImpl obj = new RestAPIServiceImpl();
//		obj.createTest();
//	}

}