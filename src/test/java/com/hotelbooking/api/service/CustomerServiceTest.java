package com.hotelbooking.api.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hotelbooking.api.exception.CustomerAlreadyExistException;
import com.hotelbooking.api.model.Customer;
import com.hotelbooking.api.repository.CustomerRepository;

public class CustomerServiceTest {

	@MockBean
	private Customer customer;

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		customer = new Customer();
		customer.setCustomer_id(1l);
		customer.setFirst_name("John");
		customer.setLast_name("Smith");
		customer.setAge("24");
		customer.setGender("Male");
		customer.setAddress("Pune, MH, India");
		customer.setEmail("john@gmail.com");
		customer.setMobile_no("77700351274");
		customer.setBonus_points(800.00f);

	}

	@Test
	public void registerCustomerSuccess() throws CustomerAlreadyExistException {
		when(this.customerRepository.save((Customer) any())).thenReturn(customer);
		Customer _customer = this.customerServiceImpl.registerCustomer(customer);
		Assert.assertEquals(customer, _customer);
	}

	@Test
	public void registerCustomerFailure() throws CustomerAlreadyExistException {
		when(this.customerRepository.save((Customer) any())).thenReturn(customer);
		Customer _customer = this.customerServiceImpl.registerCustomer(customer);
		Assert.assertNotEquals(customer, _customer);

	}
}
