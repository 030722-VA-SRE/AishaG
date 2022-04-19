package com.revature.exceptions;

import com.revature.models.User;

public class UserFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5915039296808608025L;

	public UserFoundException(User newUser) {
		super(newUser + " is occupied. Please choose a different name.");
	}
	
}