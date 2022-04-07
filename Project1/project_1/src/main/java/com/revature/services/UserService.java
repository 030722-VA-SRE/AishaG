package com.revature.services;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserDto;
import com.revature.exceptions.UserFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository ur;
	private UserDto ud;
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public List<UserDto> getUsers(){
		List<User> users = ur.findAll();
		
		/*-
		 *  converts the list into a stream in which a map function is applied
		 *  The map function applies some logic to each object within the List and returns that object
		 *  the newly UserDto objects are then returned
		 */
		return users.stream()
				.map((user) -> new UserDto(user))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public UserDto getUserById(int id) throws UserNotFoundException {
		User user = ur.findById(id).orElseThrow();
		// log.info("user x retrieved ...");
		
		return new UserDto(user);
	}
	
	@Transactional
	public User createUser(User newUser) throws UserFoundException{	

		String username = newUser.toString();
		User returningUser = ur.findUserByUsername(username);
		if(returningUser != null) {
		throw new UserFoundException(newUser);
	}
		newUser.setRole(UserRole.USER);
		return ur.save(newUser);
}
	
			
	
	@Transactional
	public User updateUserById(User user, int id) throws UserNotFoundException {
		/*-
		 *  Logic for update user, ie:
		 *  	- check that user exists
		 *  	- partial updates
		 *  	- etc...
		 */
		ur.findById(id);
		user.setId(id);
		return ur.save(user);
	}
	
	@Transactional
	public void deleteUserById(int id) throws UserNotFoundException {
		// this tries to retrieve a user by id, if it doesn't exist, throws an exception
		getUserById(id);
		LOG.info(MDC.get("userToken"));
		ur.deleteById(id);
	}
}