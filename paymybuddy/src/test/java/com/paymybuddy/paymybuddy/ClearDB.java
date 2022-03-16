package com.paymybuddy.paymybuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.connection.repository.ConnectionRepository;
import com.paymybuddy.message.repository.MessageRepository;
import com.paymybuddy.transfer.repository.TransferRepository;
import com.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.wallet.repository.WalletRepository;

@Service
public class ClearDB {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TransferRepository transferRepository;
	@Autowired
	ConnectionRepository connectionRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	WalletRepository walletRepository;

	public void clearDB() {
		connectionRepository.deleteAll();
		userRepository.deleteAll();
		transferRepository.deleteAll();
		messageRepository.deleteAll();
		walletRepository.deleteAll();
	}
}
