package com.hotelbooking.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelbooking.api.model.Customer;
import com.hotelbooking.api.service.CustomerService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private Customer customer;

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
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
	public void registerCustomerSuccess() throws Exception {
		when(this.customerService.registerCustomer(any())).thenReturn(customer);
		mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());

	}

	@Test
	public void registerCustomerFailure() throws Exception {
		when(this.customerService.registerCustomer(any())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(customer))).andExpect(MockMvcResultMatchers.status().isConflict())
				.andDo(MockMvcResultHandlers.print());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
