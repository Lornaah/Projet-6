package com.paymybuddy.paymybuddy.IntegrationTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.paymybuddy.ClearDB;
import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.registration.RegistrationService;
import com.paymybuddy.security.service.SecurityService;
import com.paymybuddy.transfer.repository.TransferRepository;
import com.paymybuddy.transfer.service.TransferService;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.wallet.ManageFoundsDTO;
import com.paymybuddy.wallet.service.WalletService;

@Transactional
@SpringBootTest
public class TransferServiceITest {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	TransferService transferService;

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	UserService userService;

	@Autowired
	WalletService walletService;

	@Autowired
	ClearDB clearDB;

	private User userReceive;
	private User userSend;

	@BeforeEach
	public void setUp() {
		clearDB.clearDB();
		RegistrationRequest request = new RegistrationRequest("Test@mail.com", "Test123Test!", "Test", "TestTest");
		userSend = registrationService.registerUser(request);
		RegistrationRequest newRequest = new RegistrationRequest("Test@mail.fr", "Test123Test", "TestTest", "Test");
		userReceive = registrationService.registerUser(newRequest);

	}

	@Test
	public void createTransferWhenUserSendHasEnoughFounds() {

		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.com");
			ManageFoundsDTO foundRequest = new ManageFoundsDTO(userSend.getId(), 500);
			walletService.addFounds(foundRequest);

			// Act
			TransferRequest transferRequest = new TransferRequest(userReceive.getId(), 500);
			TransferResponseDTO transfer = transferService.createTransfer(transferRequest);

			// Assert
			assertTrue(transfer != null);
		}

	}

	@Test
	public void createTransferWhenUserSendHasNotEnoughFounds() {

		// Arrange
		try (MockedStatic<SecurityService> security = Mockito.mockStatic(SecurityService.class)) {
			security.when(SecurityService::getCurrentUserMailAddress).thenReturn("Test@mail.com");
			ManageFoundsDTO foundRequest = new ManageFoundsDTO(userSend.getId(), 100);
			walletService.addFounds(foundRequest);

			// Act
			TransferRequest transferRequest = new TransferRequest(userReceive.getId(), 500);

			// Assert
			assertThrows(IllegalStateException.class, () -> {
				transferService.createTransfer(transferRequest);
			});
		}
	}
}
