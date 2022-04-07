package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.UserRole;
import com.revature.dto.UserDto;
import com.revature.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findUserByUsername(String username);

	List<User> findUsersByRole(UserRole userRole);

	User save(UserRole userRole);
	
	User save(UserDto newUser);
	
}
