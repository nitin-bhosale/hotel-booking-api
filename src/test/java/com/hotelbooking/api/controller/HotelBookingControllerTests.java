package com.hotelbooking.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.hotelbooking.api.model.HotelBookingDetails;
import com.hotelbooking.api.service.HotelBookingService;

@RunWith(SpringRunner.class)
@WebMvcTest
class HotelBookingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private HotelBookingDetails hotelBookingDetails;

	@Mock
	private HotelBookingService bookingService;

	@InjectMocks
	private HotelBookingController hotelBookingController;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(hotelBookingController).build();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime checkInDate = LocalDateTime.parse("2020-08-27 23:24:45", formatter);
		LocalDateTime checkOutDate = LocalDateTime.parse("2020-08-30 01:24:45", formatter);

		hotelBookingDetails.setBooking_id(1l);
		hotelBookingDetails.setCheck_in_time(checkInDate);
		hotelBookingDetails.setCheck_out_time(checkOutDate);
		hotelBookingDetails.setCustomer_id(1l);
		hotelBookingDetails.setRoom_id(1l);
	}

	@Test
	public void bookHotelRoomSuccess() throws Exception {

		when(this.bookingService.bookHotel(any())).thenReturn(hotelBookingDetails);

		mockMvc.perform(MockMvcRequestBuilders.post("/book").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(hotelBookingDetails))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void bookHotelRoomFaulire() throws Exception {
		when(this.bookingService.bookHotel(any())).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.post("/book").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(hotelBookingDetails))).andExpect(MockMvcResultMatchers.status().isConflict())
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
