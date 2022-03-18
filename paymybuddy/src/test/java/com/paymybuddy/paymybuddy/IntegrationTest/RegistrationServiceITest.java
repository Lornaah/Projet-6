package com.paymybuddy.paymybuddy.IntegrationTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.security.SecurityService;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.user.service.UserService;

@SpringBootTest
public class RegistrationServiceITest {
	@Autowired
	RegistrationService registrationService;

	@Autowired
	UserService userService;

	@Autowired
	ClearDB clearDB;

	@Autowired
	UserRepository userRepository;

	private User user;

	@BeforeEach
	public void setUp() {
		clearDB.clearDB();
	}

	@Test
	public void newUser() {
		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.com");
			RegistrationRequest request = new RegistrationRequest("Test@mail.com", "Test123Test!", "Test", "TestTest");

			// Act
			user = registrationService.registerUser(request);

			// Assert
			assertTrue(userService.alreadyRegistered(user.getMailAddress()));
			assertTrue(userRepository.getById(user.getId()) != null);

		}
	}
}
