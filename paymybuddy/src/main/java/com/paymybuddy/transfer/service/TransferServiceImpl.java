package com.paymybuddy.transfer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.paymybuddy.security.service.SecurityService;
import com.paymybuddy.transfer.model.Transfer;
import com.paymybuddy.transfer.repository.TransferRepository;
import com.paymybuddy.transfer.transferDTO.CurrentUserTransferDTO;
import com.paymybuddy.transfer.transferDTO.TransferRequest;
import com.paymybuddy.transfer.transferDTO.TransferResponseDTO;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.wallet.ManageFoundsDTO;
import com.paymybuddy.wallet.service.WalletService;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private WalletService walletService;

	@Autowired
	private SecurityService securityService;

	@Override
	@Transactional
	public TransferResponseDTO createTransfer(TransferRequest transferRequest) {
		Optional<User> userSend = Optional.of(securityService.getCurrentUserByUserMailAddress());
		Optional<User> userReceive = userService.getUserByID(transferRequest.getUserReceiveID());

		if (userReceive.isEmpty() || (userSend.isEmpty())) {
			throw new IllegalStateException("One of the user doesn't exist");
		}
		if (!userReceive.get().isActive() || !userSend.get().isActive())
			throw new IllegalStateException("One of the user isn't active");

		Transfer transfer = new Transfer(userSend.get(), userReceive.get(), transferRequest.getAmount(), new Date());

		validateTransfer(transfer);
		sendMoney(transfer);
		receiveMoney(transfer);

		TransferResponseDTO transferDTO = new TransferResponseDTO(transferRepository.save(transfer));
		return transferDTO;

	}

	private void validateTransfer(Transfer transfer) {
		if (transfer.getUserSend().getWallet().getFounds() < transfer.getAmount())
			throw new IllegalStateException("Not enough founds on the account");
		if (!transfer.getUserSend().isActive() || !transfer.getUserReceive().isActive())
			throw new IllegalStateException("One of the user isn't active anymore");
	}

	private void receiveMoney(Transfer transfer) {
		ManageFoundsDTO manageFoundsReceive = new ManageFoundsDTO(transfer.getUserReceive().getId(),
				transfer.getAmount());
		walletService.addFounds(manageFoundsReceive);
	}

	private void sendMoney(Transfer transfer) {
		ManageFoundsDTO manageFoundsSend = new ManageFoundsDTO(transfer.getUserSend().getId(),
				transfer.getTaxedAmount());
		walletService.removeFounds(manageFoundsSend);
	}

	@Override
	public Page<CurrentUserTransferDTO> getTransfersPaginated(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of((pageNum - 1), pageSize, Sort.by("date").descending());
		String userName = SecurityService.getCurrentUserMailAddress();
		Optional<User> currentUser = userService.getUserByUserName(userName);
		if (currentUser.isEmpty())
			return new PageImpl<>(new ArrayList<>());

		int currentID = currentUser.get().getId();
		Page<Transfer> transferPage = transferRepository.findByUserReceive_idOrUserSend_id(currentID, currentID,
				pageable);

		return transferPage.map(t -> new CurrentUserTransferDTO(t));
	}

}
