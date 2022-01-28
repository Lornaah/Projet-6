package com.paymybuddy.transfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.transfer.model.Transfer;
import com.paymybuddy.transfer.repository.TransferRepository;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.wallet.ManageFoundsDTO;
import com.paymybuddy.wallet.service.WalletService;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

	@Autowired
	TransferRepository transferRepository;

	@Autowired
	UserService userService;

	@Autowired
	WalletService walletService;

	@Override
	public TransferResponseDTO createTransfer(TransferRequest transferRequest) {

		Optional<User> userReceive = userService.getUserByID(transferRequest.getUserReveiveID());
		Optional<User> userSend = userService.getUserByID(transferRequest.getUserSendID());

		if (userReceive.isEmpty() || (userSend.isEmpty())) {
			throw new IllegalStateException("One of the user doesn't exist");
		}
		Transfer transfer = new Transfer(userSend.get(), userReceive.get(), transferRequest.getAmount());

		validateTransfer(transfer);
		sendMoney(transfer);
		receiveMoney(transfer);

		TransferResponseDTO transferDTO = new TransferResponseDTO(transferRepository.save(transfer));
		return transferDTO;

	}

	private void validateTransfer(Transfer transfer) {
		if (transfer.getUserSend().getWallet().getFounds() < transfer.getAmount())
			throw new IllegalStateException("Not enough founds on the account");
		if (!transfer.getUserSend().isActive() || !transfer.getUserReveive().isActive())
			throw new IllegalStateException("One of the user isn't active anymore");
	}

	private void receiveMoney(Transfer transfer) {
		ManageFoundsDTO manageFoundsReceive = new ManageFoundsDTO(transfer.getUserReveive().getId(),
				transfer.getAmount());
		walletService.addFounds(manageFoundsReceive);
	}

	private void sendMoney(Transfer transfer) {
		ManageFoundsDTO manageFoundsSend = new ManageFoundsDTO(transfer.getUserSend().getId(),
				transfer.getTaxedAmount());
		walletService.removeFounds(manageFoundsSend);
	}

	@Override
	public Optional<TransferResponseDTO> getTransfer(int ID) {
		if (transferRepository.findById(ID).isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(new TransferResponseDTO(transferRepository.findById(ID).get()));
	}

}
