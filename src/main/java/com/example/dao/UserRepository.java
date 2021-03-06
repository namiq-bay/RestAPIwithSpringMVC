package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface UserRepository {
	List<User> findAll();
	User findByID(Long id);
	List<User> findByLastName(String lastName);
	
	void create(User user);
	User update(User user);
	void delete(Long id);
	
}
