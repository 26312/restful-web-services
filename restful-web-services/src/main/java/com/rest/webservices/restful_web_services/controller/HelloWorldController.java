package com.rest.webservices.restful_web_services.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	/**
	 * It is more efficient to use @GetMapping annotation than @RequestMapping.
	 * Reduces the code length and only path of the mapping has to be passed 
	 */
	//@RequestMapping(method = RequestMethod.GET, path = "/hello-world")

	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Welcome to Hello World!!!";
	}
	
	
	/**
	 * The above mapping returns value in String format. 
	 * Now, we will return the contents in JSON format
	 */
	@GetMapping("/Hello-World-Bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello-World-Beans");
	}
	
	/**
	 *  Path Parameters
	 *   /users/{id}/todo/{id} ==> /users/2/todo/200
	 *   /hello-world/path-variable/{name}
	 *   /hello-world/path-variable/Varun
	 */
	
	@GetMapping("/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(name);
	}
	
	
}
