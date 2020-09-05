package com.hotelbooking.api.service;

import java.util.List;

import com.hotelbooking.api.exception.CustomerAlreadyExistException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers() throws CustomerNotExistException;

	Customer registerCustomer(Customer customer) throws CustomerAlreadyExistException;

	Customer getCustomerByCustomerId(Long customer_id) throws CustomerNotExistException;

	boolean getCustomerByEmail(String email) throws CustomerNotExistException;

}
