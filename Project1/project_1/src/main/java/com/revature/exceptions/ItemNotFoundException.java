package com.revature.exceptions;

public class ItemNotFoundException extends Exception {
	private int id;
	
	public static ItemNotFoundException createWith(int id) {
		return new ItemNotFoundException(id);
	}
	
	private ItemNotFoundException(int id) {
		this.id = id;
	}
//	private static final long serialVersionUID = 1L;
//
//	public ItemNotFoundException (String message) {
//		super("This item could not be acquired. Please ensure the id or name is correct)");
//	}
	@Override
	public String getMessage() {
		return "Item '" + id + "' not found";
	}
}
