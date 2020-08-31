package com.hotelbooking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.model.HotelBookingDetails;
import com.hotelbooking.api.service.HotelBookingService;

@RestController
public class HotelBookingController {

	private HotelBookingService bookingService;

	@Autowired
	public void setBookingService(HotelBookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping(value = "/book", produces = { "application/json" })
	public ResponseEntity<?> bookHotel(@RequestBody HotelBookingDetails hotelBookingDetails) {

		HotelBookingDetails _hotelBookingDetails = null;
		try {
			_hotelBookingDetails = this.bookingService.bookHotel(hotelBookingDetails);
			return new ResponseEntity<HotelBookingDetails>(_hotelBookingDetails, HttpStatus.OK);

		} catch (BookingFailureException e) {
			return new ResponseEntity<String>("Customer Already Exist Exception", HttpStatus.NOT_FOUND);
		} catch (RoomNotAvailableException e) {
			return new ResponseEntity<String>("Room Booking Full Exception", HttpStatus.NOT_FOUND);
		}

	}

}
