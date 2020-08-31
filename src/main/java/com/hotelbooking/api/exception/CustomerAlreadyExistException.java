package com.hotelbooking.api.exception;

public class CustomerAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerAlreadyExistException(String message) {
		super(message);
	}

}
