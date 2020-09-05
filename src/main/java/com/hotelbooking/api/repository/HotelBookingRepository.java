package com.hotelbooking.api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotelbooking.api.model.HotelBookingDetails;

public interface HotelBookingRepository extends JpaRepository<HotelBookingDetails, Long> {

	@Query("FROM HotelBookingDetails c WHERE c.customer_id = :customer_id and c.booking_status = 'PENDING_APPROVAL'")
	List<HotelBookingDetails> findPendingApprovlBookings(Sort by, Long customer_id);

	@Query("FROM HotelBookingDetails c WHERE c.room_id = :room_id and c.booking_status = 'PENDING_APPROVAL' and c.check_in_time >=:localDateTime")
	List<HotelBookingDetails> getPendingApprovalRoomsByRoomId(Sort by, Long room_id, LocalDateTime localDateTime);
	
}
