package com.mins.practice.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mins.practice.entities.User;

//This class is a combination of service and database class
// @component used to tell the spring it is also a part of spring app so it also managed
// @component have 3 things --> @Controller,@Service, @Repository 

/*The static keyword in Java is mainly used for memory management. The static keyword in Java
 *  is used to share the same variable or method of a given class. The users can apply static
 *   keywords with variables, methods, blocks, and nested classes. The static keyword belongs 
 *   to the class than an instance of the class. The static keyword is used for a constant variable
 *    or a method that is the same for every instance of a class.
 *  */
@Component
public class UserDaoService {
	
	private static List<User> users=new ArrayList<>();
	
	private static int usersCount=3;
	
	static {
		users.add(new User(1,"Tanish",new Date()));
		users.add(new User(2,"Dahiya",new Date()));
		users.add(new User(3,"TD",new Date()));
		users.add(new User(4,"TDDD",new Date()));
	}
	
	//Get all User
	public List<User> findAll(){
		return users;
	}
	
	//Add a user
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	//Get one user
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	//delete user
	public User deleteUser(int id) {
		Iterator<User> iterator=users.iterator();
		while(iterator.hasNext()) {
			User user=iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
