package com.hotelbooking.api.service;

import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.model.HotelBookingDetails;

public interface HotelBookingService {

	HotelBookingDetails bookHotel(HotelBookingDetails hotelBookingDetails) throws BookingFailureException, RoomNotAvailableException;
	
	

}
