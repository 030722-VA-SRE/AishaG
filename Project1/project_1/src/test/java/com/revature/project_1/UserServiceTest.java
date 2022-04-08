package com.revature.project_1;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.controllers.UserController;
import com.revature.dto.UserDto;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)

public class UserServiceTest() {
	@InjectMocks
	static UserRepository ur;
	static UserController uc;
	static UserService us;
	static User user;
	static UserDto uto;
	List<User> users = new ArrayList<>();
	
	
	@BeforeAll
	public void setup() {
		
	}
	
	
}