package com.hotelbooking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelbooking.api.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
