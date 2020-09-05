package com.hotelbooking.api.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hotelbooking.api.exception.BonusPointUpdateFailureException;
import com.hotelbooking.api.exception.BookingFailureException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.exception.InsufficientAccomodationDaysException;
import com.hotelbooking.api.exception.InsufficientBonusPointsException;
import com.hotelbooking.api.exception.NoBookingFoundException;
import com.hotelbooking.api.exception.NoPendingBookingFoundException;
import com.hotelbooking.api.exception.RoomNotAvailableException;
import com.hotelbooking.api.exception.RoomNotExistException;
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
	private Environment env;

	@Autowired
	public void setBookingRepository(HotelBookingRepository bookingRepository, RoomRepository roomsRepository,
			CustomerRepository customerRepository) {

		this.bookingRepository = bookingRepository;
		this.roomRepository = roomsRepository;
		this.customerRepository = customerRepository;

	}

	@Override
	public HotelBookingDetails bookHotel(HotelBookingDetails hotelBookingDetails) throws RoomNotExistException,
			CustomerNotExistException, InsufficientAccomodationDaysException, BookingFailureException,
			RoomNotAvailableException, InsufficientBonusPointsException, NoPendingBookingFoundException {

		String bookingStatus = env.getProperty("hotel.room.booking_status.pending_approval");
		HotelBookingDetails _bookingResult = null;
		Float total_rent = null;
		Float _remaningBonusPoints = null;
		Float _pointsToRS = null;
		Integer _accomodationDays = null;

		Room _room = this.getRoom(hotelBookingDetails);
		Customer _customer = this.getCustomerById(hotelBookingDetails.getCustomer_id());

		_pointsToRS = this.bonusPointsToRupees(_customer, _room);
		_accomodationDays = this.getAccomodationDays(hotelBookingDetails);
		total_rent = _room.getRent_per_day() * _accomodationDays;

		if (_pointsToRS >= total_rent) {
			bookingStatus = env.getProperty("hotel.room.booking_status.booked");
		}

		// If room not available
		if (getAvailableRooms(_room) == 0) {
			_room = canclePendingBookingsAndUpdateRooms(_room, _pointsToRS, total_rent, bookingStatus);
		}

		// adding booking entry
		hotelBookingDetails.setBooking_status(bookingStatus);
		hotelBookingDetails.setTotal_bill(total_rent);
		_bookingResult = this.bookingRepository.save(hotelBookingDetails);

		if (_bookingResult == null) {
			throw new BookingFailureException("Booking Failure Exception");
		}

		// updating room count
		_room.setBooked_rooms(_room.getBooked_rooms() + 1);
		this.roomRepository.save(_room);

		// updating customer bonus points only Room is booked
		if (bookingStatus.equals("BOOKED")) {
			_remaningBonusPoints = total_rent / _room.getRate_per_bonus_point();
			_customer.setBonus_points(_customer.getBonus_points() - _remaningBonusPoints);
			this.customerRepository.save(_customer);
		}

		return _bookingResult;
	}

	private Room canclePendingBookingsAndUpdateRooms(Room _room, Float _pointsToRS, Float total_rent,
			String bookingStatus) throws NoPendingBookingFoundException, InsufficientBonusPointsException {

		Room _retObject = null;

		// finding Pending Approval Room by room id, ascending with check_in_time
		List<HotelBookingDetails> bookingDetailsList;
		try {
			bookingDetailsList = this.bookingRepository.getPendingApprovalRoomsByRoomId(
					Sort.by(Sort.Direction.ASC, "check_in_time"), _room.getRoom_id(), LocalDateTime.now());

			if (_pointsToRS < total_rent) {
				throw new InsufficientBonusPointsException("Insufficient Bonus Points Exception");
			}

			bookingDetailsList.get(0).setBooking_status(env.getProperty("hotel.room.booking_status.cancelled"));

			if (this.bookingRepository.save(bookingDetailsList.get(0)) != null) {
				_room.setBooked_rooms(_room.getBooked_rooms() - 1);
				_retObject = this.roomRepository.save(_room);
			}

		} catch (Exception e) {
			throw new NoPendingBookingFoundException(
					"No PENDING_APPROVAL booking found for room " + _room.getRoom_id());
		}

		return _retObject;
	}

	@Override
	public Boolean updateCustomerBonusPoints(Long customerId, Float bonusPoints)
			throws BonusPointUpdateFailureException, CustomerNotExistException, RoomNotExistException,
			NoPendingBookingFoundException {

		Customer _customer = null;
		Customer _updateStatus = null;

		_customer = this.customerRepository.findById(customerId).get();

		if (_customer == null) {
			throw new CustomerNotExistException("Customer not exist exception.");
		}

		// adding bonus points in existing
		_customer.setBonus_points(_customer.getBonus_points() + bonusPoints);

		// update to customer
		_updateStatus = this.customerRepository.save(_customer);

		if (_updateStatus == null) {
			throw new BonusPointUpdateFailureException("Failed to update bonus points.");
		}

		// calling status booking method
		updateBookingStatusForCustomer(_customer.getCustomer_id());

		return true;

	}

	@Override
	public void updateBookingStatusForCustomer(Long customerId)
			throws RoomNotExistException, CustomerNotExistException, NoPendingBookingFoundException {

		List<HotelBookingDetails> _bookingDetailsList = this.bookingRepository
				.findPendingApprovlBookings(Sort.by(Sort.Direction.ASC, "total_bill"), customerId);

		if (_bookingDetailsList == null) {
			throw new NoPendingBookingFoundException("No Pending Approval Booking found for customers");
		}

		Float _ruppees = null;
		Customer _customer = null;
		Room _room = null;

		for (HotelBookingDetails hotelBookingDetails : _bookingDetailsList) {

			_customer = getCustomerById(customerId);
			_room = getRoom(hotelBookingDetails);
			_ruppees = bonusPointsToRupees(_customer, _room);

			if (_ruppees >= hotelBookingDetails.getTotal_bill()) {

				// update booked status to booking_details
				hotelBookingDetails.setBooking_status("BOOKED");
				this.bookingRepository.save(hotelBookingDetails);

				// deduct customer bonus points
				Float _billedPoints = hotelBookingDetails.getTotal_bill()
						/ getRoom(hotelBookingDetails).getRate_per_bonus_point();

				_customer.setBonus_points(_customer.getBonus_points() - _billedPoints);
				this.customerRepository.save(_customer);

			} else {
				break;
			}
		}
	}

	private Float bonusPointsToRupees(Customer _customer, Room _room) {
		return _customer.getBonus_points() * _room.getRate_per_bonus_point();
	}

	private Integer getAccomodationDays(HotelBookingDetails hotelBookingDetails)
			throws InsufficientAccomodationDaysException {

		Integer duration = null;

		duration = (int) Duration
				.between(hotelBookingDetails.getCheck_in_time(), hotelBookingDetails.getCheck_out_time()).toDays();
		if (duration == null || duration < 1) {
			throw new InsufficientAccomodationDaysException(
					"InsufficientAccomodationDaysException - Days should be more than 1 ");
		}

		return duration;
	}

	private Customer getCustomerById(Long customer_id) throws CustomerNotExistException {
		Customer _customer = this.customerRepository.findById(customer_id).get();

		if (_customer == null) {
			throw new CustomerNotExistException("Customer Not Exist Exception");
		}
		return _customer;
	}

	public Integer getAvailableRooms(Room room) {
		return (room.getTotal_rooms() - room.getBooked_rooms());
	}

	public Room getRoom(HotelBookingDetails hotelBookingDetails) throws RoomNotExistException {
		Room _room = null;

		_room = this.roomRepository.findById(hotelBookingDetails.getRoom_id()).get();
		if (_room == null) {
			throw new RoomNotExistException("Room Not Exist Exception");
		}

		return _room;
	}

	@Override
	public List<HotelBookingDetails> getBookingDetails() throws NoBookingFoundException {
		List<HotelBookingDetails> bookingDetails = null;
		bookingDetails = this.bookingRepository.findAll();
		if (bookingDetails == null) {
			throw new NoBookingFoundException("No Booking Found Exception");
		}
		return bookingDetails;
	}

}
