package com.hotelbooking.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_Master")
public class Customer {
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customer_id;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "age")
	private String age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "email")
	private String email;
	@Column(name = "address")
	private String address;
	@Column(name = "mobile_no")
	private String mobile_no;
	@Column(name = "bonus_points")
	private Float bonus_points;

	

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(Long customer_id, String first_name, String last_name, String age, String gender, String email,
			String address, String mobile_no, Float bonus_points) {
		super();
		this.customer_id = customer_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.address = address;
		this.mobile_no = mobile_no;
		this.bonus_points = bonus_points;
		
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public Float getBonus_points() {
		return bonus_points;
	}

	public void setBonus_points(Float bonus_points) {
		this.bonus_points = bonus_points;
	}

	
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", age=" + age + ", gender=" + gender + ", email=" + email + ", address=" + address + ", mobile_no="
				+ mobile_no + ", bonus_points=" + bonus_points +  "]";
	}

}
