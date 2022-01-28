package com.paymybuddy.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.user.models.User;
import com.paymybuddy.wallet.model.Wallet;
import com.paymybuddy.wallet.repository.WalletRepository;

@Service("walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepository walletRepository;

	@Override
	public Wallet createWallet(User user) {

		Wallet wallet = new Wallet();
		wallet.setUser(user);
		return walletRepository.save(wallet);
	}

	@Override
	public float getFounds(Integer userID) {

		return walletRepository.getById(userID).getFounds();
	}

}
