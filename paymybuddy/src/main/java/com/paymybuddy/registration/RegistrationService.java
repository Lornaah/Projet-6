package com.paymybuddy.registration;

import com.paymybuddy.user.models.User;

public interface RegistrationService {

	public User registerUser(RegistrationRequest request);
}
