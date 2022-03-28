package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService us;

	@Autowired
	public UserController(UserService us) {
		super();
		this.us = us;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(us.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<User> getById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	 
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user, @RequestHeader String header){
		User u = us.createUser(user);
		return new ResponseEntity<>("User " + u.getUsername() + "has been created", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> getByID(@PathVariable("id") int id){
		try {
			us.deleteUser(id);
			return new ResponseEntity<>("User was deleted", HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
