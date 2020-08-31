package com.hotelbooking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);

}
