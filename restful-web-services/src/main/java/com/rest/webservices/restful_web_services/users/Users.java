package com.rest.webservices.restful_web_services.users;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class Users {
	
	private Integer id;
	private String name;
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
