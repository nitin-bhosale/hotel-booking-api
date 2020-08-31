package com.hotelbooking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.api.model.HotelBookingDetails;

public interface HotelBookingRepository extends JpaRepository<HotelBookingDetails, Long> {


}
