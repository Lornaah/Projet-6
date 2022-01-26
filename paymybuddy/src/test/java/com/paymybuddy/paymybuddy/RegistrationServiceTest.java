package com.paymybuddy.paymybuddy;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.user.service.UserService;

@SpringBootTest
public class RegistrationServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RegistrationService registrationService;

	@BeforeEach
	public void clearDB() {
		userRepository.findAll().forEach(u -> {
			userRepository.delete(u);
		});
		;
	}

	@Test
	public void registerUser() {

		// Arrange
		RegistrationRequest request = new RegistrationRequest("test", "test");

		// Act
		registrationService.registerUser(request);

		// Assert
		assertTrue(userService.alreadyRegistered(request.getMailAddress()));
	}

	@Test
	public void alreadyRegistered() {

		// Arrange
		RegistrationRequest request = new RegistrationRequest("test", "test");
		registrationService.registerUser(request);

		// Act
		assertThrows(IllegalStateException.class, () -> {
			registrationService.registerUser(request);
		});
	}
}
