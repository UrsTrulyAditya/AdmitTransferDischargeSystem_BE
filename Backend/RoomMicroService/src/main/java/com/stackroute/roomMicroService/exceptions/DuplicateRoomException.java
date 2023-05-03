package com.stackroute.roomMicroService.exceptions;

public class DuplicateRoomException extends RuntimeException {

	public DuplicateRoomException() {
		super("this patient already admitted in a room");
	}
}
