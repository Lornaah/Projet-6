package com.paymybuddy.user;

import java.util.Optional;

import com.paymybuddy.models.User;
import com.paymybuddy.registration.RegistrationRequest;

public interface UserService {

	public String createUser(User user);

	public boolean alreadyRegistered(String mailAddress);

	public String deleteUser(RegistrationRequest request);

	public String updateUser(UpdateRequest request);

	public Optional<User> getUser(UpdateRequest request);
}