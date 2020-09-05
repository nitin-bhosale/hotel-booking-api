package com.hotelbooking.api.service;

import java.util.List;

import com.hotelbooking.api.exception.BonusPointUpdateFailureException;
import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.exception.InsufficientAccomodationDaysException;
import com.hotelbooking.api.exception.InsufficientBonusPointsException;
import com.hotelbooking.api.exception.NoBookingFoundException;
import com.hotelbooking.api.exception.NoPendingBookingFoundException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.exception.RoomNotExistException;
import com.hotelbooking.api.model.HotelBookingDetails;

public interface HotelBookingService {

	HotelBookingDetails bookHotel(HotelBookingDetails hotelBookingDetails)
			throws BookingFailureException, RoomNotAvailableException, RoomNotExistException, CustomerNotExistException,
			InsufficientAccomodationDaysException, InsufficientBonusPointsException, NoPendingBookingFoundException;

	Boolean updateCustomerBonusPoints(Long customerId, Float bonusPoints) throws BonusPointUpdateFailureException,
			CustomerNotExistException, RoomNotExistException, NoPendingBookingFoundException;

	void updateBookingStatusForCustomer(Long customerId) throws RoomNotExistException, CustomerNotExistException,
			NoBookingFoundException, NoPendingBookingFoundException;

	List<HotelBookingDetails> getBookingDetails() throws NoBookingFoundException;

}
