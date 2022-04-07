package com.revature.exceptions;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3716268286668920746L;

		public UserNotFoundException(int id) {
			super("There is no user " + id + " to speak of...");
		}

	}

