package com.paymybuddy.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.models.User;
import com.paymybuddy.user.UserService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserService userService;

	@Override
	public String registerUser(RegistrationRequest request) {
		User user = new User(request.getMailAddress(), request.getPassword());
		if (userService.alreadyRegistered(user)) {
			throw new IllegalStateException("Mail is already used");
		}
		return userService.createUser(user);
	}

}
