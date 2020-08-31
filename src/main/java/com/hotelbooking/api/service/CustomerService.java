package com.hotelbooking.api.service;

import java.util.List;

import com.hotelbooking.api.exception.CustomerAlreadyExistException;
import com.hotelbooking.api.exception.CustomerNotFoundException;
import com.hotelbooking.api.model.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers() throws CustomerNotFoundException;

	Customer registerCustomer(Customer customer) throws CustomerAlreadyExistException;

	Customer getCustomerByCustomerId(Long customer_id) throws CustomerNotFoundException;

	boolean getCustomerByEmail(String email) throws CustomerNotFoundException;

}
