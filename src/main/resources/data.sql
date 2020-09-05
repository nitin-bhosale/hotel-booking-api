--Customer Data
INSERT INTO `hotel_booking`.`customer_master` (`address`, `age`, `bonus_points`, `email`, `first_name`, `gender`, `last_name`, `mobile_no`) VALUES ('pune, MH, India', '24', '4000', 'nbhosale34@gmail.com', 'Nitin', 'Male', 'Bhosale', '9764825764');
INSERT INTO `hotel_booking`.`customer_master` (`address`, `age`, `bonus_points`, `email`, `first_name`, `gender`, `last_name`, `mobile_no`) VALUES ('mumbai, MH, India', '34', '7000', 'rahul@gmail.com', 'Rahul', 'Male', 'Jain', '7770035127');
INSERT INTO `hotel_booking`.`customer_master` (`address`, `age`, `bonus_points`, `email`, `first_name`, `gender`, `last_name`, `mobile_no`) VALUES ('thane, MH,  India', '27', '5000', 'ritu@gmail.com', 'Ritu', 'Female', 'Jain', '9764825766');

--Hotel Data
INSERT INTO `hotel_booking`.`hotel_master` (`address`, `contact_no`, `hotel_name`, `hotel_star`) VALUES ('Kolhapur, MH, India', '0231-344556', 'Moody Moon', '3');
INSERT INTO `hotel_booking`.`hotel_master` (`address`, `contact_no`, `hotel_name`, `hotel_star`) VALUES ('Pune, MH, India', '020-233454', 'Knights Inn', '5');
INSERT INTO `hotel_booking`.`hotel_master` (`address`, `contact_no`, `hotel_name`, `hotel_star`) VALUES ('Mumbai', '022-894765', 'Classio Hotel', '5');

--Rooms Data
INSERT INTO `hotel_booking`.`rooms_master` (`amenities`, `booked_rooms`, `rate_per_bonus_point`, `rent_per_day`, `room_type`, `total_rooms`, `hotel_id`) VALUES ('Gym, Swimming pool, Gaming zone', '0', '2', '500', 'Single', '10', '1');
INSERT INTO `hotel_booking`.`rooms_master` (`amenities`, `booked_rooms`, `rate_per_bonus_point`, `rent_per_day`, `room_type`, `total_rooms`, `hotel_id`) VALUES ('Parking,welcome drink, free dinner', '3', '1', '700', 'Queen', '23', '1');
INSERT INTO `hotel_booking`.`rooms_master` (`amenities`, `booked_rooms`, `rate_per_bonus_point`, `rent_per_day`, `room_type`, `total_rooms`, `hotel_id`) VALUES ('Bar, Longue, Fitness Center', '2', '3', '300', 'Double', '30', '2');
INSERT INTO `hotel_booking`.`rooms_master` (`amenities`, `booked_rooms`, `rate_per_bonus_point`, `rent_per_day`, `room_type`, `total_rooms`, `hotel_id`) VALUES ('Parking, Longue, Fitness Center', '2', '2', '500', 'single', '15', '2');
INSERT INTO `hotel_booking`.`rooms_master` (`amenities`, `booked_rooms`, `rate_per_bonus_point`, `rent_per_day`, `room_type`, `total_rooms`, `hotel_id`) VALUES ('Gaming zone, Longue, Swimming pool', '2', '1', '600', 'single', '10', '3');
INSERT INTO `hotel_booking`.`rooms_master` (`amenities`, `booked_rooms`, `rate_per_bonus_point`, `rent_per_day`, `room_type`, `total_rooms`, `hotel_id`) VALUES ('Gaming zone, Bar, Swimming pool', '0', '2', '1200', 'Queen', '15', '3');

