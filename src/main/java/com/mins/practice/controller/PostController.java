package com.mins.practice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mins.practice.Exceptions.UserNotFoundException;
import com.mins.practice.entities.Post;
import com.mins.practice.entities.User;
import com.mins.practice.repository.PostRepository;
import com.mins.practice.repository.UserRepository;

@RestController
public class PostController {
	
	@Autowired
	private PostRepository pr;
	@Autowired
	private UserRepository userrepo;
	//retrieve all post of a user
	//first we need to find user
	//then fetching the post
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllusers(@PathVariable int id){
		Optional<User> userOptional= userrepo.findById(id); //find a single user by id
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		return userOptional.get().getPost();
		
	}
	
	//post for a specific a user 
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPostforauser(@PathVariable int id,@RequestBody Post post){
		Optional<User> userOptional= userrepo.findById(id); //find a single user by id
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id: "+id);
		}
		User user= userOptional.get();
		post.setUser(user);
		pr.save(post);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}

}
