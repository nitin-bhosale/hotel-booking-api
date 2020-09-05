# hotel-booking-api
1. This is a hotel-booking-api designed in Java, Spring Boot, MySQL Database
2. Once you run application, this will run on 8080 port.
3. Data will be automatically inserted into Customer, Hotel & Room tables from data.sql file from classpath resources/data.sql file
4. Also you can find API Documentation using Swagger UI from below provided path
    Swagger Url - http://localhost:8080/swagger-ui.html

########################  API - CUSTOMER  ######################################
1. Service to get all Customers - 
  GET - http://localhost:8080/getAllCustomers

2. Service to get Customer by id -
  GET - http://localhost:8080/getCustomerById/1
  
3. Service to register Customer
  POST - http://localhost:8080/register
  
  JSON for Input -
      {
        "first_name": "Lucy",
        "last_name": "Morgan",
        "age": "24",
        "gender": "female",
        "email": "lucy@gmail.com",
        "address": "Mumbai, MH, India",
        "mobile_no": "9764825745",
        "bonus_points": 5000
    }

########################  API - HOTEL ROOM BOOKING ############################

1. Booking hotel please provide below provided JSON to service
  POST - http://localhost:8080/book
  
  JSON for Input -
  {
    "check_in_time" : "2020-08-27T23:24:45.267",
    "check_out_time" : "2020-09-01T02:24:45.267",
    "customer_id" : 2,
    "room_id" : 5
  }
  
  Note - Enhancement done in above API to add bookings if specific room is not available then book same room if find PENDING_APPROVAL Status.
  
  
  
  2. API to update Bonus point and do booking for PENDING_APPROVAL
  
  http://localhost:8080/updateBonusPoints/{customerid}/{bonuspoints}
  
