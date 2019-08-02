package com.example.service;

import java.util.List;

import com.example.exception.UserNotFoundException;
import com.example.model.User;

public interface RestAPIService {
	List <User> findUsers(String lastName);
	User findUser(Long id) throws UserNotFoundException;
	
	void createUser(User user);
	void updateUser(User user);
	void deleteUser(Long id);

}
