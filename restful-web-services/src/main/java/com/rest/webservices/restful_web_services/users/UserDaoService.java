package com.rest.webservices.restful_web_services.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<Users> users = new ArrayList();
	static int userCount =0;
	static{
		users.add(new Users(++userCount,"Varun",LocalDate.now().minusYears(15)));
		users.add(new Users(++userCount,"Dev",LocalDate.now().minusYears(30)));
		users.add(new Users(++userCount,"Manish",LocalDate.now().minusYears(6)));
		users.add(new Users(++userCount,"Arpit",LocalDate.now().minusYears(10)));
	}
	
	public List<Users> findAll() {
		return users;
	}
	
	public Users findOne(int id) {
//		return users.stream()
//				.filter(users->users.getId()
//				.equals(id))
//				.findFirst()
//				.get();
		
		return users.stream()
				.filter(users->users.getId()
				.equals(id))
				.findFirst()
				.orElse(null);
	}
	
	public Users addNewUser(Users user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
}
