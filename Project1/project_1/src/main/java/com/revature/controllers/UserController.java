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
		
//		MDC.put("requestId", UUID.randomUUID().toString());
//		
//		ls.verify(token);
//		
//		LOG.info("users retrieved");
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
		//		try {
//			return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
//		} catch (UserNotFoundException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
	 
	@PostMapping("/users")
	public ResponseEntity<EntityModel<User>> newUser(@RequestBody User user){
	
		EntityModel<User> entitymodel =
uma.toModel(us.createUser(user));
		
		return ResponseEntity

.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
		.body(entitymodel);
		
//		User u = us.createUser(user);
//		return new ResponseEntity<>("User " + u.getUsername() + "has been created", HttpStatus.CREATED);
	}

	
	@PutMapping("/users/{id}")
	public ResponseEntity<EntityModel<User>> updateUser(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody User updatedUser, @PathVariable ("id") int id) throws UserNotFoundException {
		ls.verifiedAdmin(token);
		
		User user = us.updateUserById(updatedUser, id);
//		.orElseThrow(() -> new UserNotFoundException("User not found for this id :: " + user.getId()));
		
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		user.setRole(updatedUser.getRole());
		

//			.map(User -> {
//				user.setName(newuser.getName());
//				user.setRole(newuser.getRole());
//				return ur.save(user);
//			})
//			.orElseGet(() -> {
//				newuser.setId(id);
//				return us.updateUserById(newuser);
//			});
			EntityModel<User> entitymodel =
	uma.toModel(updatedUser);
			
			ResponseEntity.ok(updatedUser);
			return ResponseEntity
	.created(entitymodel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(entitymodel);
//		try {
//			User u = us.updateUserById(id, newuser);
//			return new ResponseEntity<>("User " + u.getUsername() + "has been updated", HttpStatus.ACCEPTED);
//		} catch (UserNotFoundException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUserById(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable("id") int id){
		ls.verifiedAdmin(token);
		
			us.deleteUserById(id);
			
			return ResponseEntity.noContent().build();
	}
			//("User was deleted", HttpStatus.OK);
			
		 
			//return new ResponseEntity<>("User of id " + id + "was not found", HttpStatus.NOT_FOUND);
		
}
