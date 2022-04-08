package com.revature.controllers;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assembler.UserModelAssembler;
import com.revature.dto.UserDto;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping
public class UserController {
	
	private UserService us;
	private LoginService ls;
	private UserModelAssembler uma;

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserController(UserService us, LoginService ls, UserModelAssembler uma) {
		super();
		this.us = us;
		this.ls = ls;
		this.uma = uma;
	}
	
	@GetMapping("/users")
	public CollectionModel<EntityModel<UserDto>> getAllUsers() {
		

		List<EntityModel<UserDto>> udto = 
				us.getUsers().stream()
				.map(uma::toModel)
			.collect(Collectors.toList());
		
		return CollectionModel.of(udto, 
		linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<EntityModel<UserDto>> findUserById(@PathVariable("id") int id) {

		UserDto ud = us.getUserById(id);
		
		EntityModel<UserDto> entitymodel =
		uma.toModel(ud);
		
		return ResponseEntity
				.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entitymodel);
				
	}

	 
	@PostMapping("/users")
	public ResponseEntity<EntityModel<User>> newUser(@RequestBody User user){
	
		EntityModel<User> entitymodel =
uma.toModel(us.createUser(user));
		LOG.trace("A new user has joined!");
		return ResponseEntity

.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		.body(entitymodel);
		
	}

	
	@PutMapping("/users/{id}")
	public ResponseEntity<EntityModel<User>> updateUser(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody User updatedUser, @PathVariable ("id") int id) throws UserNotFoundException {
		ls.verifiedAdmin(token);
		
		User user = us.updateUserById(updatedUser, id);

		
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		user.setRole(updatedUser.getRole());
		


			EntityModel<User> entitymodel =
	uma.toModel(updatedUser);
			
			ResponseEntity.ok(updatedUser);
			return ResponseEntity
	.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entitymodel);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id){
		ls.verifiedAdmin(token);
		
			us.deleteUserById(id);
			LOG.info("user has been deleted, by admin");
			return ResponseEntity.noContent().build();
			
	}

		
}
