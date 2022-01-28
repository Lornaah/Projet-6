package com.paymybuddy.user.service;

import java.util.Optional;

import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.user.UpdateRequest;
import com.paymybuddy.user.models.User;

public interface UserService {

	public User createUser(User user);

	public boolean alreadyRegistered(String mailAddress);

	public String deleteUser(RegistrationRequest request);

	public String updateUser(UpdateRequest request);

	public Optional<User> getUser(UpdateRequest request);

	public Optional<User> getUserByID(int ID);
}