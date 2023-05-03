package com.stackroute.roomMicroService.exceptions;

public class RoomNotFoundException extends RuntimeException {

	public RoomNotFoundException(){
		super("Not found");
	}
}
