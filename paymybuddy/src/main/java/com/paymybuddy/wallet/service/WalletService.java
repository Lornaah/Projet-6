package com.paymybuddy.wallet.service;

import com.paymybuddy.user.models.User;
import com.paymybuddy.wallet.model.Wallet;

public interface WalletService {

	float getFounds(Integer userID);

	Wallet createWallet(User user);

}
