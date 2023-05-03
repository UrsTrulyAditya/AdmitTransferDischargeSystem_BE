package com.stackroute.roomMicroService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.roomMicroService.model.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> { 

    Room save(Room room); 

    Room findById(int id); 
    
    Optional<Room> findRoomById(int id);
       

    Room deleteById(int id);

	Optional<Room> findByPatientId(int patientId);

	Optional<Room> findByRoomNo(int id);

	List<Room> findByStatus(String status);


} 
