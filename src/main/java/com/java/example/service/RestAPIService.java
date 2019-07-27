package com.java.example.service;

import java.util.List;

import com.java.example.exception.UserNotFoundException;
import com.java.example.model.User;

public interface RestAPIService {
	List <User> findUsers(String lastName);
	User findUser(Long id) throws UserNotFoundException;
	
	void createUser(User user);
	void updateUser(User user);
	void deleteUser(Long id);

}
