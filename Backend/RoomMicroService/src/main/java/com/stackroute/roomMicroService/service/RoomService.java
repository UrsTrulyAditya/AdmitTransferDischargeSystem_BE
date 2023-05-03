package com.stackroute.roomMicroService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.stackroute.roomMicroService.exceptions.RoomNotFoundException;
import com.stackroute.roomMicroService.model.Room;

public interface RoomService { 

    ResponseEntity<Object> addRoom(Room room); 

    Room getRoomById(int id); 

    Boolean deleteRoomById(int id); 

    Room updateRoomById(int id, Room room);

	List<Room> viewAllRooms();

	Room updateRoom(int id, Room room);

	List<Room> getRoomsByStatus(String status) throws RoomNotFoundException;

	Room getRoomByRoomNo(int roomNo);

	Room updateRoom(Room existingRoom);

	Optional<Room> getRoomByPatientId(int id);

	Room updateRoomByPatient(int id, Room room);
 
} 

 

  
