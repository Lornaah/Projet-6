package com.paymybuddy.paymybuddy.IntegrationTest;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.security.SecurityService;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.user.updateDTO.UpdatePasswordDTO;
import com.paymybuddy.user.updateDTO.UpdateProfileDTO;

@SpringBootTest
public class UserServiceITest {
	private static final String USER_PASSWORD = "Test123Test";

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RegistrationService registrationService;

	private static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	ClearDB clearDB;

	private User userCurrent;

	@BeforeEach
	public void setUp() {

		clearDB.clearDB();

		RegistrationRequest newRequest = new RegistrationRequest("Test@mail.fr",
				passwordEncoder().encode(USER_PASSWORD), "TestTest", "Test");
		userCurrent = registrationService.registerUser(newRequest);
	}

	@Test
	public void updateProfileNamesByUserNameTest() {
		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.fr");
			UpdateProfileDTO updateProfileDTO = new UpdateProfileDTO("NewFirstName", "NewLastName");

			// Act
			Optional<User> currentUser = userService.updateProfileNamesByUserMailAddress(userCurrent.getMailAddress(),
					updateProfileDTO);

			// Assert
			assertTrue(currentUser.isPresent());
			assertTrue(currentUser.get().getFirstName().equals("NewFirstName"));
			assertTrue(currentUser.get().getLastName().equals("NewLastName"));
			assertTrue(userService.getUserByUserName(currentUser.get().getMailAddress()) != null);

		}
	}

	@Test
	public void updatePasswordByUserMailAddressTest() {
		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.fr");
			UpdatePasswordDTO updatePasswordDTO = new UpdatePasswordDTO(USER_PASSWORD, "NewPassword123!");

			// Act
			Optional<User> currentUser = userService.updatePasswordByUserMailAddress(userCurrent.getMailAddress(),
					updatePasswordDTO);

			// Assert
			assertTrue(currentUser.isPresent());
			assertTrue(passwordEncoder().matches("NewPassword123!", currentUser.get().getPassword()));
		}
	}

	@Test
	public void disableAccount() {
		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.fr");

			// Act
			userCurrent = userService.disableAccountByUserName(userCurrent.getMailAddress());

			// Assert
			assertTrue(!userCurrent.isActive());
		}
	}

}
