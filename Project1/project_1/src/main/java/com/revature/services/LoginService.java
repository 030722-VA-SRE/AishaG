package com.revature.services;

import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.TdModels;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.repositories.UserRepository;

@Service
public class LoginService {
	
	private UserRepository ur;
	private static final Logger LOG = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	public LoginService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public String login(String username, String password) {
		User user = ur.findUserByUsername(username);
		
		if(user == null || !user.getPassword().equals(password)) {
			throw new AuthenticationException("Attempted to login with username: " + username);
		}
		
		LOG.info("User " + user.getUsername() + "'s credentials validated.");
		return user.getId()+":"+user.getRole().toString();
	}
	
	public void verify(String token) {
		if(token == null) {
			throw new AuthorizationException("null token");
		}
		
		String[] splitToken = token.split(":");
		
		User principal = ur.findById(Integer.valueOf(splitToken[0])).orElse(null);
		
		if(principal == null || !principal.getRole().toString().equals(splitToken[1]) || !principal.getRole().toString().equals("ADMIN")) {
				throw new AuthorizationException("Unable to verify token of value: " + splitToken[0] + ", " + splitToken[1]);
		}
		
		LOG.info("token verified successfully");
		MDC.put("userId", principal.getId());
	}
	
	public void verifyCreator(String token) {
		if(token == null) {
			throw new AuthorizationException("null token");
		}
		
		String[] splitToken = token.split(":");
		
		User principal = ur.findById(Integer.valueOf(splitToken[0])).orElse(null);
		
		if(principal == null || !principal.getRole().toString().equals(splitToken[1]) || !principal.getRole().toString().equals("CREATOR") || !principal.getRole().toString().equals("ADMIN")) {
				throw new AuthorizationException("Unable to verify token of value: " + splitToken[0] + ", " + splitToken[1]);
		}
		
		LOG.info("token verified successfully");
		MDC.put("userId", principal.getId());
	}
	
	public void verifiedCreator(String token) {
		MDC.put("requestId", UUID.randomUUID().toString());
		
		verifyCreator(token);
		
		LOG.info("users retrieved");
	}

	public void verifiedAdmin(String token) {
		MDC.put("requestId", UUID.randomUUID().toString());
		verify(token);
	}
//	
//	public void verifyModelOwnership(String token) {
//		if(token == null) {
//			throw new AuthorizationException("null token");
//		}
//		
//		String[] splitToken = token.split(":");
//		
//		User principal = ur.findById(Integer.valueOf(splitToken[0])).orElse(null);
//		
//		if(principal == null || !principal.getRole().toString().equals(splitToken[1]) || (!principal.getRole().toString().equals("CREATOR") && !principal.getId().equals(Integer.valueOf(TdModels.getCreator())) || !principal.getRole().toString().equals("ADMIN"))  {
//				throw new AuthorizationException("Unable to verify token of value: " + splitToken[0] + ", " + splitToken[1]);
//		}
//		
//		LOG.info("token verified successfully");
//		MDC.put("userId", principal.getId());
//	}
}
