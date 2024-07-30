package com.rest.webservices.restful_web_services.users;

import java.net.URI;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restful_web_services.exception.UserNotFoundException;

import jakarta.servlet.Servlet;

@RestController 
public class UserResource {

	@Autowired
	private UserDaoService service;

	public UserResource(UserDaoService service) {
		this.service = service;
	}
	
	// Get all users
	@GetMapping("/users")
	public List<Users> getAllUsers(){
		return service.findAll();
	}
	
	// Get Specific User
	@GetMapping("/users/{id}")
	public Users getUserById(@PathVariable int id) {
		Users users = service.findOne(id);
		
		if(users==null) {
			throw new UserNotFoundException("id: " + id);
		}
		return users;
	}
	
	// Add a User
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@RequestBody Users users) {
		Users savedUser = service.addNewUser(users);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest() 
					.path("/{id}") // refer to the new Id being created
					.buildAndExpand(savedUser.getId()) // call the {id} and map it here
					.toUri(); // wrap it to the URI
		
		return ResponseEntity.created(uri).build();
	}
	
	
}
