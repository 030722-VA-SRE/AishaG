package com.revature.exceptions;

public class ItemNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public ItemNotFoundException (int id) {
		super("Item of " + id +" could not be acquired. Please ensure the id or name is correct)");
	}

}
