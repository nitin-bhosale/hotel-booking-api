package com.hotelbooking.api.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.model.Customer;
import com.hotelbooking.api.model.HotelBookingDetails;
import com.hotelbooking.api.model.Room;
import com.hotelbooking.api.repository.CustomerRepository;
import com.hotelbooking.api.repository.HotelBookingRepository;
import com.hotelbooking.api.repository.RoomRepository;

@Service
public class HotelBookingServiceImpl implements HotelBookingService {

	private HotelBookingRepository bookingRepository;
	private RoomRepository roomRepository;
	private CustomerRepository customerRepository;

	@Autowired
	public void setBookingRepository(HotelBookingRepository bookingRepository, RoomRepository roomsRepository,
			CustomerRepository customerRepository) {

		this.bookingRepository = bookingRepository;
		this.roomRepository = roomsRepository;
		this.customerRepository = customerRepository;

	}

	@Override
	public HotelBookingDetails bookHotel(HotelBookingDetails hotelBookingDetails)
			throws BookingFailureException, RoomNotAvailableException {

		Room _room = null;
		Customer _customer = null;
		Integer _availableRooms = null;
		Float _pointsToRS = null;
		Duration duration = null;
		String bookingStatus = "PENDING APPROVAL";
		HotelBookingDetails _bookingResult = null;
		Float total_rent = null;
		Float _remaningBonusPoints = null;

		// get room details by room id
		_room = this.roomRepository.findById(hotelBookingDetails.getRoom_id()).get();
		_availableRooms = _room.getTotal_rooms() - _room.getBooked_rooms();

		System.out.println("_availableRooms" + _availableRooms);

		if (_availableRooms == 0) {
			throw new RoomNotAvailableException("Room Booking Full Exception");
		}

		// get customer details by customer id
		_customer = this.customerRepository.findById(hotelBookingDetails.getCustomer_id()).get();
		_pointsToRS = _customer.getBonus_points() * _room.getRate_per_bonus_point();

		// calculate accommodation days and total rent
		duration = Duration.between(hotelBookingDetails.getCheck_in_time(), hotelBookingDetails.getCheck_out_time());
		total_rent = _room.getRent_per_day() * duration.toDays();

		System.out.println("_pointsToRS" + _pointsToRS);
		System.out.println("duration" + duration.toDays());
		System.out.println("total_rent" + total_rent);

		if (_pointsToRS >= total_rent) {
			// updating booked room status
			_room.setBooked_rooms(_room.getBooked_rooms() + 1);
			this.roomRepository.save(_room);

			// updating booking status
			bookingStatus = "BOOKED";
			hotelBookingDetails.setBooking_status(bookingStatus);
			_bookingResult = this.bookingRepository.save(hotelBookingDetails);

			// updating points
			_remaningBonusPoints = total_rent / _room.getRate_per_bonus_point();
			_customer.setBonus_points(_customer.getBonus_points()-_remaningBonusPoints);
			this.customerRepository.save(_customer);
		} else {

			// updating booked room status
			_room.setBooked_rooms(_room.getBooked_rooms() - 1);
			this.roomRepository.save(_room);

			// updating booking status
			hotelBookingDetails.setBooking_status(bookingStatus);
			_bookingResult = this.bookingRepository.save(hotelBookingDetails);
		}

		if (_bookingResult == null) {
			throw new BookingFailureException("Booking Failure Exception");
		}

		return _bookingResult;

	}

}
