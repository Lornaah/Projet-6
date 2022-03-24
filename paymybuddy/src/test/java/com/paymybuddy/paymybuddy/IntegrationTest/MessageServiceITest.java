package com.paymybuddy.paymybuddy.IntegrationTest;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.message.model.Message;
import com.paymybuddy.message.repository.MessageRepository;
import com.paymybuddy.message.service.MessageService;
import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.security.service.SecurityService;
import com.paymybuddy.user.models.User;

@SpringBootTest
public class MessageServiceITest {

	@Autowired
	MessageService messageService;

	@Autowired
	RegistrationService registrationService;

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	ClearDB clearDB;

	private User userCurrent;

	@BeforeEach
	public void setUp() {

		clearDB.clearDB();

		RegistrationRequest newRequest = new RegistrationRequest("Test@mail.fr", "Test123Test", "TestTest", "Test");
		userCurrent = registrationService.registerUser(newRequest);
	}

	@Test
	public void addMessage() {
		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.fr");

			// Act
			int oldSize = messageRepository.findAll().size();
			Message message = new Message(userCurrent.getFirstName(), userCurrent.getMailAddress(), "MessageTest",
					new Date());
			messageService.addMessage(message);
			int newSize = messageRepository.findAll().size();

			// Assert
			assertTrue(messageRepository.getById(message.getId()) != null);
			assertTrue(oldSize == (newSize - 1));

		}
	}
}
