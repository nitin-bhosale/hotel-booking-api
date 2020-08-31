package com.hotelbooking.api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Hotel_Master")
public class Hotel {
	@Id
	@Column(name = "hotel_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hotel_id;
	@Column(name = "hotel_name")
	private String hotel_name;
	@Column(name = "hotel_star")
	private String hotel_star;
	@Column(name = "contact_no")
	private String contact_no;
	@Column(name = "address")
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel")
	private Set<Room> rooms = new HashSet<Room>();

	public Hotel() {
		// TODO Auto-generated constructor stub
	}

	public Hotel(Long hotel_id, String hotel_name, String hotel_star, String contact_no, String address,
			Set<Room> rooms) {
		super();
		this.hotel_id = hotel_id;
		this.hotel_name = hotel_name;
		this.hotel_star = hotel_star;
		this.contact_no = contact_no;
		this.address = address;
		this.rooms = rooms;
	}

	public Long getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Long hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getHotel_star() {
		return hotel_star;
	}

	public void setHotel_star(String hotel_star) {
		this.hotel_star = hotel_star;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Hotel [hotel_id=" + hotel_id + ", hotel_name=" + hotel_name + ", hotel_star=" + hotel_star
				+ ", contact_no=" + contact_no + ", address=" + address + ", rooms=" + rooms + "]";
	}


}
