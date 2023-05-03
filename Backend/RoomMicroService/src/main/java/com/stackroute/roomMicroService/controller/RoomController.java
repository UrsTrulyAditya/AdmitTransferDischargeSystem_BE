package com.stackroute.roomMicroService.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.roomMicroService.exceptions.DuplicateRoomException;
import com.stackroute.roomMicroService.exceptions.RoomNotFoundException;
import com.stackroute.roomMicroService.model.Room;
import com.stackroute.roomMicroService.service.RoomService;

@RestController
@CrossOrigin
@RequestMapping("/rooms")
public class RoomController {

	private final RoomService roomService;

	public RoomController(RoomService roomService) {

		this.roomService = roomService;

	}

	@PostMapping("/addroom")
	public ResponseEntity<Object> addRoom(@RequestBody Room room) {
		try {
			return roomService.addRoom(room);
		} catch (DuplicateRoomException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/viewRoom/{id}")
	public ResponseEntity<Object> getRoomById(@PathVariable int id) {
		try {
			Room room = roomService.getRoomById(id);
			return new ResponseEntity<>(room, HttpStatus.OK);
		} catch (RoomNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/viewRoomByPatientId/{id}")
	public ResponseEntity<Object> getRoomByPatientId(@PathVariable int id) {
		try {
			Optional<Room> room = roomService.getRoomByPatientId(id);
			return new ResponseEntity<>(room, HttpStatus.OK);
		} catch (RoomNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/viewRoomByStatus/{status}")
	public ResponseEntity<Object> getRoomsByStatus(@PathVariable String status) {
	    try {
	        List<Room> rooms = roomService.getRoomsByStatus(status);
	        return new ResponseEntity<>(rooms, HttpStatus.OK);
	    } catch (RoomNotFoundException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("deleteRoom/{id}")

	public ResponseEntity<?> deleteRoomById(@PathVariable int id) throws RoomNotFoundException {

		try {			
			Boolean deleted = roomService.deleteRoomById(id);
			if( deleted) {
				
				return new ResponseEntity<>("deleted",HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
		} catch (RoomNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("updateroom/{id}")

	public Room updateRoomById(@PathVariable int id, @RequestBody Room room) {

		return roomService.updateRoomById(id, room);

	}
	

	@PutMapping("updateroomByPatientId/{id}")

	public Room updateRoomByPatientId(@PathVariable int id, @RequestBody Room room) {

		return roomService.updateRoomByPatient(id, room);

	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable int id, @RequestBody Room room) {

		Room roomObj = roomService.updateRoom(id, room);

		if (roomObj == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(roomObj);

	}
	
	@PutMapping("updateroombyno/{roomNo}")
	public Room updateRoomByNo(@PathVariable int roomNo, @RequestBody Room room) {
	    Room existingRoom = roomService.getRoomByRoomNo(roomNo);
	    
	    if (existingRoom == null) {
	        throw new RoomNotFoundException();
	    }
	    
	    if (room.getStatus() != null) {
	        existingRoom.setStatus(room.getStatus());
	    }
	    
//	    if (room.getPatientId() != 0) {
	        existingRoom.setPatientId(room.getPatientId());
//	    }
	    
	    // Return the updated room object
	    return roomService.updateRoom(existingRoom);
	}


	@GetMapping("/viewrooms")
	public ResponseEntity<Object> viewAdmins() {

		try {
			List<Room> rooms = roomService.viewAllRooms();
			return new ResponseEntity<>(rooms, HttpStatus.OK);
		} catch (RoomNotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
