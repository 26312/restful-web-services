package com.rest.webservices.restful_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	
	// Below URI Versioning has been used
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionPerson() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	// RequestParameter Versioning
	@GetMapping(path = "/person",params = "version1")
	public PersonV1 getFirstVersionPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "person", params = "version2")
	public PersonV2 getSecondVersionPersonRequestParameter() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	
	// Request Header
	@GetMapping(path = "/person/header", headers =  "X-API-Version=1")
	public PersonV1 getFirstVersionPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-Version=2")
	public PersonV2 getSecondVersionPersonRequestHeader() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	
	// Media Type Versioning
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
		
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionPersonAcceptHeader() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	

}
