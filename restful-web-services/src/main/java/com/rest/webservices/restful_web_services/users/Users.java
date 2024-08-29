package com.rest.webservices.restful_web_services.users;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity
public class Users {

	@Id
	private Integer id;
	
	@Size(min=2, message = "Name should have least 2 characters")
	@JsonProperty("user_name")
	private String name;
	
	@Past( message = "DOB should be in Past")
	@JsonProperty("birth_date") 
	private LocalDate birthDate;
	
	public Users() {
		
	}
	
	public Users(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	

}
