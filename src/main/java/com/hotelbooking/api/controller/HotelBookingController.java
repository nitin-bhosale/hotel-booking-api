package com.hotelbooking.api.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.api.exception.BonusPointUpdateFailureException;
import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.CheckInDateException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.exception.InsufficientAccomodationDaysException;
import com.hotelbooking.api.exception.InsufficientBonusPointsException;
import com.hotelbooking.api.exception.NoBookingFoundException;
import com.hotelbooking.api.exception.NoPendingBookingFoundException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.exception.RoomNotExistException;
import com.hotelbooking.api.model.HotelBookingDetails;
import com.hotelbooking.api.service.HotelBookingService;

/**
 * @author nbhos
 *
 */
@RestController
public class HotelBookingController {

	private HotelBookingService bookingService;

	@Autowired
	public void setBookingService(HotelBookingService bookingService) {
		this.bookingService = bookingService;
	}

	// API : to book hotel room
	@PostMapping(value = "/book", produces = { "application/json" })
	public ResponseEntity<?> bookHotel(@RequestBody HotelBookingDetails hotelBookingDetails)
			throws RoomNotExistException, CustomerNotExistException, InsufficientAccomodationDaysException,
			InsufficientBonusPointsException, NoPendingBookingFoundException, CheckInDateException {

		HotelBookingDetails _hotelBookingDetails = null;
		Duration duration = null;

		LocalDateTime now = LocalDateTime.now();
		duration = Duration.between(now, hotelBookingDetails.getCheck_in_time());

		if (duration.toDays() < 0) {
			throw new CheckInDateException("Check-in date is past date");
		}

		duration = Duration.between(hotelBookingDetails.getCheck_out_time(), hotelBookingDetails.getCheck_in_time());

		if (duration.toDays() < 1) {
			throw new InsufficientAccomodationDaysException("Accomodation days should be one or more than one day");
		}

		try {
			_hotelBookingDetails = this.bookingService.bookHotel(hotelBookingDetails);
			return new ResponseEntity<HotelBookingDetails>(_hotelBookingDetails, HttpStatus.OK);

		} catch (BookingFailureException e) {
			return new ResponseEntity<String>("Customer Already Exist Exception", HttpStatus.NOT_FOUND);
		} catch (RoomNotAvailableException e) {
			return new ResponseEntity<String>("Room Booking Full Exception", HttpStatus.NOT_FOUND);
		}

	}

	// API : to update users bonus points
	@PostMapping(value = "/updateBonusPoints/{customerid}/{bonuspoints}", produces = { "application/json" })
	public ResponseEntity<?> updateBonusPoints(@PathVariable("customerid") Long customerId,
			@PathVariable("bonuspoints") Float bonusPoints) throws NoPendingBookingFoundException {

		try {
			this.bookingService.updateCustomerBonusPoints(customerId, bonusPoints);
			return new ResponseEntity<String>("Bonus Point updated successfully", HttpStatus.OK);

		} catch (BonusPointUpdateFailureException e) {
			return new ResponseEntity<String>("Bonus Point Update Failed Exception", HttpStatus.NOT_FOUND);
		} catch (CustomerNotExistException e) {
			return new ResponseEntity<String>("Customer Not Found Exception", HttpStatus.NOT_FOUND);
		} catch (RoomNotExistException e) {
			return new ResponseEntity<String>("Room Not Found Exception", HttpStatus.NOT_FOUND);
		}

	}

	// API : to get all bookings
	@GetMapping(value = "/bookingDetails", produces = { "application/json" })
	public ResponseEntity<?> getBookingDetails() throws NoBookingFoundException {
		List<HotelBookingDetails> _bookingDetails = null;
		try {
			_bookingDetails = this.bookingService.getBookingDetails();
			return new ResponseEntity<List<HotelBookingDetails>>(_bookingDetails, HttpStatus.OK);
		} catch (NoBookingFoundException e) {
			return new ResponseEntity<String>("Booking Not Found Exception", HttpStatus.NOT_FOUND);
		}

	}

}
