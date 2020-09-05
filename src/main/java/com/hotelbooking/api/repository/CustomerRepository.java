package com.hotelbooking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotelbooking.api.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmail(String email);

//	@Modifying
//	@Query("update Customer_Master cust set cust.bonus_points = :bonus_points where cust.customer_id = :customer_id")
//	int updateBonusPoints(@Param("customer_id") Long customerId, @Param("bonus_points") Float bonusPoints);

}
