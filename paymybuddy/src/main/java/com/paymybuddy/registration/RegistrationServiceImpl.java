package com.paymybuddy.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;
import com.paymybuddy.wallet.service.WalletService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserService userService;

	@Autowired
	private WalletService walletService;

	@Override
	public User registerUser(RegistrationRequest request) {
		if (userService.alreadyRegistered(request.getMailAddress())) {
			throw new IllegalStateException("User already exists");
		}

		User user = new User(request.getMailAddress(), request.getPassword(), request.getFirstName(),
				request.getLastName());

		user.setWallet(walletService.createWallet(user));

		return userService.createUser(user);
	}

}
