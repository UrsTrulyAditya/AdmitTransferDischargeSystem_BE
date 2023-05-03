package com.stackroute.roomMicroService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.stackroute.roomMicroService.exceptions.RoomNotFoundException;
import com.stackroute.roomMicroService.model.Room;
import com.stackroute.roomMicroService.repository.RoomRepository; 

@Service
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;

	public RoomServiceImpl(RoomRepository roomRepository) {

		this.roomRepository = roomRepository;

	}

	
	@Override
	public Room getRoomById(int id) {
		return roomRepository.findById(id);

	}
	
	@Override
    public List<Room> getRoomsByStatus(String status) throws RoomNotFoundException {
        List<Room> rooms = roomRepository.findByStatus(status);
        if (rooms.isEmpty()) {
            throw new RoomNotFoundException();
        }
        return rooms;
    }

	@Override
	public Boolean deleteRoomById(int id) throws RoomNotFoundException {
		 Room existingRoom = roomRepository.findById(id);
		 System.out.println(existingRoom);
	        if (existingRoom == null) {
	            return false;
	        }
	        roomRepository.deleteById(id);
	        return true;
	}

	@Override
	public Room updateRoomById(int id, Room room) {

		Room existingRoom = roomRepository.findById(id);

		 if (existingRoom != null) {
			 
			 existingRoom.setRoomNo(room.getRoomNo());
		 }
		 if (existingRoom != null) {
			 
			 existingRoom.setWardNo(room.getWardNo());
			 existingRoom.setHospitalId(room.getHospitalId());
			 
			 existingRoom.setPatientId(room.getPatientId());
			 
			 existingRoom.setStatus(room.getStatus());
		 }



		return roomRepository.save(existingRoom);

	}

	@Override
	public Room updateRoom(int id, Room room) {
	    Optional<Room> optionalRoom = roomRepository.findRoomById(id);
	    System.out.println(optionalRoom);
	    if (optionalRoom != null) {
	        Room existingRoom = optionalRoom.get();
	        existingRoom.setPatientId(room.getPatientId());
	        existingRoom.setHospitalId(room.getHospitalId());
	        existingRoom.setRoomNo(room.getRoomNo());
	        return roomRepository.save(existingRoom);
	    }
	    return null;
	}


	
	@Override
	public List<Room> viewAllRooms() {

		return roomRepository.findAll();

	}


	@Override
	public ResponseEntity<Object> addRoom(Room room) {

//	    Optional<Room> existingPatientRoom = roomRepository.findByPatientId(room.getPatientId());
//	    if(existingPatientRoom.isPresent()) {
//	        return new ResponseEntity<>("Patient ID already exists", HttpStatus.CONFLICT);
//	    }
//	    
	    Optional<Room> existingRoom = roomRepository.findByRoomNo(room.getRoomNo());
	    if(existingRoom.isPresent()) {
	        return new ResponseEntity<>("Room already allotted", HttpStatus.CONFLICT);
	    }

	    roomRepository.save(room);
	    return new ResponseEntity<>(room, HttpStatus.CREATED);
	}

	@Override
	public Room getRoomByRoomNo(int roomNo) {
	    Optional<Room> optionalRoom = roomRepository.findByRoomNo(roomNo);
	    return optionalRoom.orElse(null);
	}

	@Override
	public Room updateRoom(Room existingRoom) {
	    if (existingRoom == null) {
	        return null;
	    }
	    int roomId = existingRoom.getId();
	    Optional<Room> optionalRoom = roomRepository.findRoomById(roomId);
	    if (optionalRoom.isPresent()) {
	        Room room = optionalRoom.get();
	        if (existingRoom.getRoomNo() != 0) {
	            room.setRoomNo(existingRoom.getRoomNo());
	        }
	        if (existingRoom.getStatus() != null) {
	            room.setStatus(existingRoom.getStatus());
	        }
	        if (existingRoom.getPatientId() != 0) {
	            room.setPatientId(existingRoom.getPatientId());
	        }
	        return roomRepository.save(room);
	    } else {
	        return null;
	    }
	}


	@Override
	public Room updateRoomByPatient(int id, Room room) {
	    Room existingRoom = roomRepository.findById(id);
	    existingRoom.setStatus(room.getStatus());
	    existingRoom.setPatientId(room.getPatientId());
	    return roomRepository.save(existingRoom);
	}


	@Override
	public Optional<Room> getRoomByPatientId(int id) {
		Optional<Room> existingPatient  = roomRepository.findByPatientId(id);
		if(existingPatient.isPresent()) {
			return existingPatient;
	    }
		return null;
		    
	}


}
