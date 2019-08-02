package com.java.example.RestAPITest;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.java.example.model.User;

public class RestControllerTest {

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testGetUsers() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/users", List.class);

		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.containsInAnyOrder("Namiq", "Azər", "Ali", "Güney"));

	}

	@Test
	public void testGetUserByID() {
		ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8080/rest/users/1", User.class);

		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Namiq"));
	}

	@Test
	public void testGetUsersByLastName() {
		ResponseEntity<List> respose = restTemplate.getForEntity("http://localhost:8080/rest/users?ln=Bayramov",
				List.class);
		MatcherAssert.assertThat(respose.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = respose.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.contains("Namiq"));
	}

	@Test
	public void testCreateUser() {
		User user = new User();

		user.setId(9L);
		user.setFirstName("NewFirstName");
		user.setLastName("NewLastName");
		user.setAge(33L);

		URI uri = restTemplate.postForLocation("http://localhost:8080/rest/users", user);

		User user2 = restTemplate.getForObject(uri, User.class);

		MatcherAssert.assertThat(user2.getFirstName(), Matchers.equalTo(user.getFirstName()));
	}

	@Test
	public void testUpdateUser() {
		User user = restTemplate.getForObject("http://localhost:8080/rest/users/2", User.class);

		MatcherAssert.assertThat(user.getFirstName(), Matchers.equalTo("Azər"));

		user.setFirstName("Elnur");
		user.setLastName("Ağayev");
		user.setAge(10L);

		restTemplate.put("http://localhost:8080/rest/users/2", user);

		user = restTemplate.getForObject("http://localhost:8080/rest/users/2", User.class);

		MatcherAssert.assertThat(user.getFirstName(), Matchers.equalTo("Elnur"));

	}

	@Test
	public void testDeleteUser() {
		restTemplate.delete("http://localhost:8080/rest/users/1");

		try {
			restTemplate.getForEntity("http://localhost:8080/rest/users/1", User.class);
			Assert.fail("The user has not been deleted!");
		} catch (RestClientException ex) {

		}

	}
}
