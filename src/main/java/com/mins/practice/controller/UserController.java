package com.mins.practice.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import com.mins.practice.Dao.UserDaoService;
import com.mins.practice.Exceptions.UserNotFoundException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.mins.practice.entities.User;
import com.mins.practice.repository.UserRepository;

@RestController
public class UserController {
	
//	@Autowired
//	private UserDaoService uds;
	
	@Autowired
	private UserRepository userrepo;
	
	//retreive all users
	@GetMapping("/users")
	public List<User> retrieveallUser() {
		return userrepo.findAll();
	}
	
	//retreive a single user
	//
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user=userrepo.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		
		
		//Hateoas user below for all user link
		//"all-users", SERVER_PATH + "/users"
				//retrieveAllUsers
				EntityModel<User> resource = EntityModel.of(user.get());
				
				WebMvcLinkBuilder linkTo = 
						linkTo(methodOn(this.getClass()).retrieveallUser());
				
				resource.add(linkTo.withRel("all-users"));
				
				//HATEOAS
				
				return resource;
	}
	
	//create a user
	//@Valid it is a java validation API 
	@PostMapping("/users")
	public ResponseEntity<HttpStatus>createUser(@Valid @RequestBody User user) {
//		User saveUser=uds.save(user);  //active when we use 2nd method of response status 
		try {
			User saveUser=userrepo.save(user);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		 
		 //If client wants where the user added link, this is seen in Header in postman by below code
//		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(saveUser.getId()).toUri();
//		
//		return ResponseEntity.created(location).build();
	}
	
	//Delete a user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userrepo.deleteById(id);
		//Automatically throws an exception so need to write below code
//		if(user==null) {
//			throw new UserNotFoundException("id:"+ id);
//		}
		
	}


}
