package com.stackroute.tpaMicroService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stackroute.roomMicroService.RoomMicroServiceApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoomMicroServiceApplicationTests {

	@Test
	void contextLoads() {
		// Ensure that the application context is being loaded correctly
		assertNotNull(RoomMicroServiceApplication.class);
	}

}
