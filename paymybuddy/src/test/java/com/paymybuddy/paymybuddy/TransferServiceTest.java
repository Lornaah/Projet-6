package com.paymybuddy.paymybuddy;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.paymybuddy.transfer.repository.TransferRepository;
import com.paymybuddy.transfer.service.TransferService;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;

@SpringBootTest
public class TransferServiceTest {

	@Autowired
	TransferService transferService;

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	UserService userService;

	private User userSend;
	private User userReceive;

	@BeforeEach
	private void setUp() {
		User userSend = new User();
		this.userSend = userService.createUser(userSend);

		User userReceive = new User();
		this.userReceive = userService.createUser(userReceive);
	}

	@Test
	public void createTransfer() {

		// Arrange
		TransferRequest transferRequest = new TransferRequest(userSend.getId(), userReceive.getId());

		// Act
		TransferResponseDTO transfer = transferService.createTransfer(transferRequest);

		// Assert
		assertTrue(transfer != null);
	}

	@Test
	public void getTransfer() {

		// Arrange
		TransferRequest transferRequest = new TransferRequest(userSend.getId(), userReceive.getId());
		TransferResponseDTO transfer = transferService.createTransfer(transferRequest);

		// Act
		Optional<TransferResponseDTO> transferOpt = transferService.getTransfer(transfer.getID());

		// Assert
		assertTrue(transferOpt.isPresent());
		assertTrue(transferOpt.get().getAmount() == 100);
		assertTrue(transferOpt.get().getUserSend().getId() == (userSend.getId()));
		assertTrue(transferOpt.get().getUserReceive().getId() == (userReceive.getId()));
	}

}
