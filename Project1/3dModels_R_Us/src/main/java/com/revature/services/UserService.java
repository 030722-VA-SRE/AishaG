package com.revature.services;

import java.util.List;
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
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	public UserService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public List<UserDto> getUsers(){
		List<User> users = ur.findAll();

		return users.stream()
				.map((user) -> new UserDto(user))
				.collect(Collectors.toList());
	}
	
	@Transactional
	public UserDto getUserById(int id) throws UserNotFoundException{
		User user = ur.findById(id).orElseThrow(() -> new UserNotFoundException(id));

		
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
	
		ur.findById(id);
		user.setId(id);
		return ur.save(user);
	}
	
	@Transactional
	public void deleteUserById(int id) throws UserNotFoundException {

		getUserById(id);
		LOG.info(MDC.get("userToken"));
		ur.deleteById(id);
	}
}