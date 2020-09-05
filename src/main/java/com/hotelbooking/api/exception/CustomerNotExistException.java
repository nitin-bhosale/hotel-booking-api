package com.hotelbooking.api.exception;

public class CustomerNotExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public CustomerNotExistException(String message) {
		super(message);
	}

}
