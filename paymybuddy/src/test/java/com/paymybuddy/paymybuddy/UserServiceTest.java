package com.paymybuddy.paymybuddy;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.models.User;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.security.repository.UserRepository;
import com.paymybuddy.user.UpdateRequest;
import com.paymybuddy.user.UserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	@BeforeEach
	public void clearDB() {
		userRepository.findAll().forEach(u -> {
			userRepository.delete(u);
		});
		;
	}

	@Test
	public void deleteUser() {

		// Arrange
		User user = new User("test", "test");
		RegistrationRequest request = new RegistrationRequest(user.getMailAddress(), user.getPassword());
		userService.createUser(user);
		assertTrue(userService.alreadyRegistered(user.getMailAddress()));

		// Act
		userService.deleteUser(request);

		// Assert
		assertFalse(userService.alreadyRegistered(user.getMailAddress()));

	}

	@Test
	public void updateUser() {

		// Arrange
		User user = new User("test", "test");
		UpdateRequest request = new UpdateRequest(user.getMailAddress(), "test2", true);
		userService.createUser(user);

		// Act
		userService.updateUser(request);

		// Assert
		assertTrue(request.getPassword().equals("test2"));
	}

	@Test
	public void getUser() {

		// Arrange
		User user = new User("test", "test");
		userService.createUser(user);
		UpdateRequest request = new UpdateRequest(user.getMailAddress(), user.getPassword(), user.isActive());

		// Act
		Optional<User> userOpt = userService.getUser(request);

		// Assert
		assertTrue(userOpt.isPresent());
		assertTrue(userOpt.get().getMailAddress().equals(user.getMailAddress()));
		assertTrue(userOpt.get().getPassword().equals(user.getPassword()));

	}

}
