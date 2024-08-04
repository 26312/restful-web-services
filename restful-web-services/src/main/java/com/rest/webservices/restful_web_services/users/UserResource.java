package com.rest.webservices.restful_web_services.users;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restful_web_services.exception.UserNotFoundException;

import jakarta.servlet.Servlet;
import jakarta.validation.Valid;

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
	
	
	//http://localhost:8080/users --> add a link, to create a response with data and link
	
	
	// HATEOAS Concepts:
	// 1.  EntityModel --
	// 2.  WebMvcLinkBuilder
	
	
	
	// Get Specific User
	@GetMapping("/users/{id}")
	public EntityModel<Users> getUserById(@PathVariable int id) {
		Users users = service.findOne(id);
		
		if(users==null) {
			throw new UserNotFoundException("id: " + id);
		}
		
		EntityModel<Users> entityModel = EntityModel.of(users);
		WebMvcLinkBuilder webMvcLinkBuilder =  linkTo(methodOn(this.getClass()).getAllUsers());
		// add the link to entityModel
		entityModel.add(webMvcLinkBuilder.withRel("all-users"));
		
		
//		return users;
		return entityModel; // returning entityModel which also changes the return type as: EntityModel<Users> wrapping the class instead of Users 
	}
	
	// Add a User
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody Users users) {
		Users savedUser = service.addNewUser(users);
		
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest() 
					.path("/{id}") // refer to the new Id being created
					.buildAndExpand(savedUser.getId()) // call the {id} and map it here
					.toUri(); // wrap it to the URI
		
		return ResponseEntity.created(uri).build();
	}
	
	// Deleting a User
	@DeleteMapping("/delete-users/{id}")
	public void deleteUser(@PathVariable int id) {
		 service.deleteById(id);
	}
	
	
}
