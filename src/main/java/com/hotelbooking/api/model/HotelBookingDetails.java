package com.hotelbooking.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nbhos
 *
 */
@Entity
@Table(name = "Hotel_Booking_Details")
public class HotelBookingDetails {
	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long booking_id;
	@Column(name = "check_in_time")
	private LocalDateTime check_in_time;
	@Column(name = "check_out_time")
	private LocalDateTime check_out_time;
	@Column(name = "booking_status")
	private String booking_status;
	@Column(name = "customer_id")
	private Long customer_id;
	@Column(name = "room_id")
	private Long room_id;

	public HotelBookingDetails() {
		// TODO Auto-generated constructor stub
	}

	public HotelBookingDetails(Long booking_id, LocalDateTime check_in_time, LocalDateTime check_out_time,
			String booking_status, Long customer_id, Long room_id) {
		super();
		this.booking_id = booking_id;
		this.check_in_time = check_in_time;
		this.check_out_time = check_out_time;
		this.booking_status = booking_status;
		this.customer_id = customer_id;
		this.room_id = room_id;
	}

	public Long getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(Long booking_id) {
		this.booking_id = booking_id;
	}

	public LocalDateTime getCheck_in_time() {
		return check_in_time;
	}

	public void setCheck_in_time(LocalDateTime check_in_time) {
		this.check_in_time = check_in_time;
	}

	public LocalDateTime getCheck_out_time() {
		return check_out_time;
	}

	public void setCheck_out_time(LocalDateTime check_out_time) {
		this.check_out_time = check_out_time;
	}

	public String getBooking_status() {
		return booking_status;
	}

	public void setBooking_status(String booking_status) {
		this.booking_status = booking_status;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}

	@Override
	public String toString() {
		return "HotelBookingDetails [booking_id=" + booking_id + ", check_in_time=" + check_in_time
				+ ", check_out_time=" + check_out_time + ", booking_status=" + booking_status + ", customer_id="
				+ customer_id + ", room_id=" + room_id + "]";
	}

}
