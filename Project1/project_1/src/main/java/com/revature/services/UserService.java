package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.TdModelRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository ur;
	private TdModelRepository tr;
	
	@Autowired
	public UserService(UserRepository ur, TdModelRepository tr) {
		super();
		this.ur = ur;
		this.tr = tr;
	}
	
	public User getUserById(int id) throws UserNotFoundException {
		return ur.findById(id).orElseThrow(UserNotFoundException::new);
	}
	
	@Transactional
	public User createUser(User newUser) {
		
		return ur.save(newUser);
	}

	public List<User> getAll() {
		return ur.findAll();
	}
	
	@Transactional
	public User updateUser(int id, User user) {
		return ur.save(user);
}

	public void deleteUser(int id) throws UserNotFoundException{
		getUserById(id);
		
		ur.deleteById(id);		
	}
}
	
	
	
//	public User getById(int id) {
//		return ud.getUserById(id);
//	}
//	
//	public List<User> getAll() {
//		// Can add some logic here, ie: if list is empty throw an exception
//		return ud.getUsers();
//	}
//	
//	public boolean addUser(User user) {
//		// validation of user object, add business logic
//		int newId = ud.createUser(user);
//		
//		if(newId == -1) {
//			// throw an exception
//			return false;
//		}
//		
//		return true;
//	}

