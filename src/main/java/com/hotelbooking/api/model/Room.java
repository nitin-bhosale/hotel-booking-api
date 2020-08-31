package com.hotelbooking.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Rooms_Master")
public class Room {
	@Id
	@Column(name = "room_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long room_id;
	@Column(name = "room_type")
	private String room_type;
	@Column(name = "amenities")
	private String amenities;

	@Column(name = "total_rooms")
	private Integer total_rooms;
	@Column(name = "booked_rooms")
	private Integer booked_rooms;

	@Column(name = "rent_per_day")
	private Float rent_per_day;
	@Column(name = "rate_per_bonus_point")
	private Float rate_per_bonus_point;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

	public Room() {
		// TODO Auto-generated constructor stub
	}

	public Room(Long room_id, String room_type, String amenities, Integer total_rooms, Integer booked_rooms,
			Float rent_per_day, Float rate_per_bonus_point, Hotel hotel) {
		super();
		this.room_id = room_id;
		this.room_type = room_type;
		this.amenities = amenities;
		this.total_rooms = total_rooms;
		this.booked_rooms = booked_rooms;
		this.rent_per_day = rent_per_day;
		this.rate_per_bonus_point = rate_per_bonus_point;
		this.hotel = hotel;
	}

	public Long getRoom_id() {
		return room_id;
	}

	public void setRoom_id(Long room_id) {
		this.room_id = room_id;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public Integer getTotal_rooms() {
		return total_rooms;
	}

	public void setTotal_rooms(Integer total_rooms) {
		this.total_rooms = total_rooms;
	}

	public Integer getBooked_rooms() {
		return booked_rooms;
	}

	public void setBooked_rooms(Integer booked_rooms) {
		this.booked_rooms = booked_rooms;
	}

	public Float getRent_per_day() {
		return rent_per_day;
	}

	public void setRent_per_day(Float rent_per_day) {
		this.rent_per_day = rent_per_day;
	}

	public Float getRate_per_bonus_point() {
		return rate_per_bonus_point;
	}

	public void setRate_per_bonus_point(Float rate_per_bonus_point) {
		this.rate_per_bonus_point = rate_per_bonus_point;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public String toString() {
		return "Rooms [room_id=" + room_id + ", room_type=" + room_type + ", amenities=" + amenities + ", total_rooms="
				+ total_rooms + ", booked_rooms=" + booked_rooms + ", rent_per_day=" + rent_per_day
				+ ", rate_per_bonus_point=" + rate_per_bonus_point + ", hotel=" + hotel + "]";
	}

}
