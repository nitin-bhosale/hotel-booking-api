package com.hotelbooking.api.exception;

public class BookingFailureException extends Exception {

	private static final long serialVersionUID = 1L;

	public BookingFailureException(String message) {
		super(message);
	}
}
