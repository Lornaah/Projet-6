package com.paymybuddy.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.user.models.User;
import com.paymybuddy.user.service.UserService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserService userService;

	@Override
	public String registerUser(RegistrationRequest request) {
		User user = new User(request.getMailAddress(), request.getPassword());
		if (userService.alreadyRegistered(request.getMailAddress())) {
			throw new IllegalStateException("Mail is already used");
		}
		return userService.createUser(user);
	}

}
