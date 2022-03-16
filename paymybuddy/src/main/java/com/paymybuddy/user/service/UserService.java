package com.paymybuddy.user.service;

import java.util.Optional;

import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.updateDTO.UpdatePasswordDTO;
import com.paymybuddy.user.updateDTO.UpdateProfileDTO;
import com.paymybuddy.user.updateDTO.UpdateRequest;

public interface UserService {

	public User createUser(User user);

	public boolean alreadyRegistered(String mailAddress);

	public String deleteUser(RegistrationRequest request);

	public Optional<User> updateProfileNamesByUserMailAddress(String userName, UpdateProfileDTO dtoRequest);

	public Optional<User> getUser(UpdateRequest request);

	public Optional<User> getUserByID(int ID);

	public Optional<User> getUserByUserName(String name);

	public Optional<User> updatePasswordByUserMailAddress(String currentUserName, UpdatePasswordDTO updatePasswordDTO);

	public void disableAccountByUserName(String currentUserName);
}