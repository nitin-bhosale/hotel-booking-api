package com.hotelbooking.api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.exception.InsufficientAccomodationDaysException;
import com.hotelbooking.api.exception.InsufficientBonusPointsException;
import com.hotelbooking.api.exception.NoPendingBookingFoundException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.exception.RoomNotExistException;
import com.hotelbooking.api.model.HotelBookingDetails;
import com.hotelbooking.api.repository.HotelBookingRepository;

public class HotelBookingServiceTest {

	@MockBean
	private HotelBookingDetails hotelBookingDetails;

	@Mock
	private HotelBookingRepository hotelBookingRepository;

	@InjectMocks
	private HotelBookingServiceImpl hotelBookingServiceImpl;

	@Before
	public void setUp() throws Exception {
		// HotelBookingDetails
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime checkInDate = LocalDateTime.parse("2020-08-27 23:24:45", formatter);
		LocalDateTime checkOutDate = LocalDateTime.parse("2020-08-30 01:24:45", formatter);

		hotelBookingDetails.setBooking_id((long) 10904);
		hotelBookingDetails.setCheck_in_time(checkInDate);
		hotelBookingDetails.setCheck_out_time(checkOutDate);
		hotelBookingDetails.setCustomer_id(1l);
		hotelBookingDetails.setRoom_id(1l);
	}

	@Test
	public void hotelRoomBookingSuccess()
			throws BookingFailureException, RoomNotAvailableException, RoomNotExistException, CustomerNotExistException,
			InsufficientAccomodationDaysException, InsufficientBonusPointsException, NoPendingBookingFoundException {
		when(this.hotelBookingRepository.save((HotelBookingDetails) any())).thenReturn(hotelBookingDetails);
		HotelBookingDetails _bookingDetails = this.hotelBookingServiceImpl.bookHotel(hotelBookingDetails);
		Assert.assertEquals(hotelBookingDetails, _bookingDetails);
	}

	@Test
	public void hotelRoomBookingFailure()
			throws BookingFailureException, RoomNotAvailableException, RoomNotExistException, CustomerNotExistException,
			InsufficientAccomodationDaysException, InsufficientBonusPointsException, NoPendingBookingFoundException {
		when(this.hotelBookingRepository.save((HotelBookingDetails) any())).thenReturn(null);
		HotelBookingDetails _bookingDetails = this.hotelBookingServiceImpl.bookHotel(hotelBookingDetails);
		Assert.assertNotEquals(hotelBookingDetails, _bookingDetails);
	}
}
