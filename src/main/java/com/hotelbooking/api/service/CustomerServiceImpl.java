package com.hotelbooking.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.api.exception.CustomerAlreadyExistException;
import com.hotelbooking.api.exception.CustomerNotExistException;
import com.hotelbooking.api.model.Customer;
import com.hotelbooking.api.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() throws CustomerNotExistException {
		List<Customer> _customerList = this.customerRepository.findAll();
		if (_customerList == null) {
			throw new CustomerNotExistException("Customer Not Found Exception");
		}
		return _customerList;

	}

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerAlreadyExistException {

		if (getCustomerByEmail(customer.getEmail())) {
			throw new CustomerAlreadyExistException("Customer Already Exist Exception");
		}
		return this.customerRepository.save(customer);

	}

	@Override
	public Customer getCustomerByCustomerId(Long customer_id) throws CustomerNotExistException {
		Customer _customerResult = null;

		try {
			_customerResult = this.customerRepository.findById(customer_id).get();
		} catch (NoSuchElementException e) {
			throw new CustomerNotExistException("Customer Not Found Exception");
		}

		return _customerResult;
	}

	@Override
	public boolean getCustomerByEmail(String email) {
		Customer _customer = this.customerRepository.findByEmail(email);
		if (_customer == null) {
			return false;
		}
		return true;
	}

}
