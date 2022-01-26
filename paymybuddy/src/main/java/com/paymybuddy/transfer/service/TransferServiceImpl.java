package com.paymybuddy.transfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.transfer.TransferRequest;
import com.paymybuddy.transfer.TransferResponseDTO;
import com.paymybuddy.transfer.model.Transfer;
import com.paymybuddy.transfer.repository.TransferRepository;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	UserService userService;

	@Override
	public String createTransfer(TransferRequest transferRequest) {

		Optional<User> userReceive = userService.getUserByID(transferRequest.getUserReveiveID());
		Optional<User> userSend = userService.getUserByID(transferRequest.getUserSendID());

		if (userReceive.isEmpty() || (userSend.isEmpty())) {
			throw new IllegalStateException("One of the user doesn't exist");
		}
		Transfer transfer = new Transfer(userReceive.get(), userSend.get(), transferRequest.getAmount());
		return transferRepository.save(transfer).toString();

	}

	@Override
	public Optional<TransferResponseDTO> getTransfer(int ID) {
		if (transferRepository.findById(ID).isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(new TransferResponseDTO(transferRepository.findById(ID).get()));
	}

}
