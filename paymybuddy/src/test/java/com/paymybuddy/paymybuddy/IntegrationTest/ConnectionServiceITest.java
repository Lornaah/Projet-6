package com.paymybuddy.paymybuddy.IntegrationTest;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.connection.connectionDTO.ConnectionDTO;
import com.paymybuddy.connection.connectionDTO.CurrentConnectionDTO;
import com.paymybuddy.connection.repository.ConnectionRepository;
import com.paymybuddy.connection.service.ConnectionService;
import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.security.service.SecurityService;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;

@SpringBootTest
public class ConnectionServiceITest {

	@Autowired
	ConnectionService connectionService;

	@Autowired
	ConnectionRepository connectionRepository;

	@Autowired
	UserService userService;

	@Autowired
	RegistrationService registrationService;

	@Autowired
	ClearDB clearDB;

	private User userCurrent;
	private User userToConnectWith;

	@BeforeEach
	public void setUp() {

		clearDB.clearDB();

		RegistrationRequest request = new RegistrationRequest("Test@mail.com", "Test123Test!", "Test", "TestTest");
		userToConnectWith = registrationService.registerUser(request);
		RegistrationRequest newRequest = new RegistrationRequest("Test@mail.fr", "Test123Test", "TestTest", "Test");
		userCurrent = registrationService.registerUser(newRequest);
	}

	@Test
	public void addConnection() {
		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.fr");
			ConnectionDTO connectionDTO = new ConnectionDTO(userToConnectWith.getMailAddress());

			// Act
			connectionService.newConnection(connectionDTO);
			List<CurrentConnectionDTO> currentDTOList = connectionService.getAllConnection();
			boolean connectionExists = false;
			connectionExists = currentDTOList.stream().anyMatch(c -> c.getId() == userToConnectWith.getId());

			// Assert
			assertTrue(connectionRepository.findByUser1_idOrUser2_id(userCurrent.getId(), userCurrent.getId()) != null);
			assertTrue(connectionExists);
			assertTrue(!currentDTOList.isEmpty());

		}

	}
}
