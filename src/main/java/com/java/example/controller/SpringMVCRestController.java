package com.java.example.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.example.exception.UserNotFoundException;
import com.java.example.model.User;
import com.java.example.service.RestAPIService;;

@RestController
@RequestMapping("/rest/users")
public class SpringMVCRestController {

	@Autowired
	private RestAPIService restAPIService;

// get all users
//	@RequestMapping(value = "/users", method = RequestMethod.GET)
//	public ResponseEntity<List<User>> getUsers() {
//		List<User> users = restAPIService.findUsers();
//		return ResponseEntity.ok(users);
//	}

	// get all users or by last name
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String ln) {
		List<User> users = restAPIService.findUsers(ln);
		return ResponseEntity.ok(users);
	}

	// get user by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		try {
			User user = restAPIService.findUser(id);

			return ResponseEntity.ok(user);
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// create user
	@RequestMapping(method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<URI> createUser(@RequestBody User user) {
		try {
			restAPIService.createUser(user);
			Long id = user.getId();

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update user
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			User newUser = restAPIService.findUser(id);

			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setAge(user.getAge());

			restAPIService.updateUser(newUser);
			return ResponseEntity.ok().build();

		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// delete user
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

		try {
			restAPIService.deleteUser(id);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
