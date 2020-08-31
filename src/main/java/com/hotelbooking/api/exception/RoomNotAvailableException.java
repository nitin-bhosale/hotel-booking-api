package com.hotelbooking.api.exception;

public class RoomNotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;

	public RoomNotAvailableException(String message) {
		super(message);
	}
}
