package com.paymybuddy.paymybuddy.UnitTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.user.service.UserService;

@SpringBootTest
public class RegistrationServiceUTest {

	@Autowired
	private UserService userService;

	@Autowired
	private RegistrationService registrationService;

	@Autowired
	ClearDB clearDB;

	@BeforeEach
	public void clearDB() {
		clearDB.clearDB();
	}

	@Test
	public void registerUser() {

		// Arrange
		RegistrationRequest request = new RegistrationRequest("Test@mail.com", "TestTest123!", "Test", "Test");

		// Act
		registrationService.registerUser(request);

		// Assert
		assertTrue(userService.alreadyRegistered(request.getMailAddress()));
	}

	@Test
	public void alreadyRegistered() {

		// Arrange
		RegistrationRequest request = new RegistrationRequest("Test@mail.com", "TestTest123!", "Test", "Test");
		registrationService.registerUser(request);

		// Act
		assertThrows(IllegalStateException.class, () -> {
			registrationService.registerUser(request);
		});
	}
}
