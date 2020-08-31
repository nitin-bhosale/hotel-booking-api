package com.hotelbooking.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbooking.api.exception.CustomerAlreadyExistException;
import com.hotelbooking.api.exception.CustomerNotFoundException;
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
	public List<Customer> getAllCustomers() throws CustomerNotFoundException {
		List<Customer> _customerList = this.customerRepository.findAll();
		if (_customerList == null) {
			throw new CustomerNotFoundException("Customer Not Found Exception");
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
	public Customer getCustomerByCustomerId(Long customer_id) throws CustomerNotFoundException {
		Optional<Customer> _customerResult = this.customerRepository.findById(customer_id);
		if (!_customerResult.isPresent()) {
			throw new CustomerNotFoundException("Customer Not Found Exception");
		}

		return _customerResult.get();
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
