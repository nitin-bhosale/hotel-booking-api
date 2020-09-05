package com.hotelbooking.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbooking.api.exception.CustomerAlreadyExistException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.model.Customer;
import com.hotelbooking.api.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/getCustomerById/{customer_id}", produces = { "application/json" })
	public ResponseEntity<?> getCustomerById(@PathVariable("customer_id") Long customer_id) {
		Customer _customer = null;

		try {
			_customer = this.customerService.getCustomerByCustomerId(customer_id);
			if (_customer != null) {
				return new ResponseEntity<Customer>(_customer, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Customer not found exception", HttpStatus.NOT_FOUND);
			}
		} catch (CustomerNotExistException e) {
			return new ResponseEntity<String>("Customer not found exception", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/getAllCustomers", produces = { "application/json" })
	public ResponseEntity<?> getAllCustomers() {
		System.out.println("In getAllCustomers");
		List<Customer> _customersList = null;
		try {
			_customersList = this.customerService.getAllCustomers();
			if (_customersList != null) {
				return new ResponseEntity<List<Customer>>(_customersList, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Record Not Found, Customer list is empty", HttpStatus.NOT_FOUND);
			}

		} catch (CustomerNotExistException e) {
			return new ResponseEntity<String>("Record Not Found, Customer list is empty", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(value = "/register", produces = { "application/json" })
	public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {

		Customer _customer = null;

		try {
			_customer = this.customerService.registerCustomer(customer);
			if (_customer != null) {
				return new ResponseEntity<Customer>(_customer, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Customer Already Exist Exception", HttpStatus.NOT_FOUND);
			}
		} catch (CustomerAlreadyExistException e) {
			return new ResponseEntity<String>("Customer Already Exist Exception", HttpStatus.NOT_FOUND);
		}

	}

}
