package com.paymybuddy.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.security.SecurityService;
import com.paymybuddy.security.model.UserDetailsImpl;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;
import com.paymybuddy.user.updateDTO.UpdatePasswordDTO;
import com.paymybuddy.user.updateDTO.UpdateProfileDTO;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SecurityService securityService;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByMailAddress(userName);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return user.map(UserDetailsImpl::new).get();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean alreadyRegistered(String mailAddress) {
		return userRepository.findByMailAddress(mailAddress).isPresent();
	}

	@Override
	public String deleteUser(RegistrationRequest request) {

		if (alreadyRegistered(request.getMailAddress())) {
			userRepository.delete(userRepository.findByMailAddress(request.getMailAddress()).get());
			return "User deleted";
		}
		return "User doesn't exist";

	}

	public Optional<User> getUserByID(int ID) {
		return userRepository.findById(ID);

	}

	@Override
	public Optional<User> getUserByUserName(String name) {
		return userRepository.findByMailAddress(name);
	}

	@Override
	public Optional<User> updateProfileNamesByUserMailAddress(String mailAddress, UpdateProfileDTO dtoRequest) {
		Optional<User> actualUser = userRepository.findByMailAddress(mailAddress);

		if (dtoRequest.getNewFirstName() == null || dtoRequest.getNewLastName() == null) {
			return Optional.empty();
		}
		if (!actualUser.get().getFirstName().equals(dtoRequest.getNewFirstName())) {
			actualUser.get().setFirstName(dtoRequest.getNewFirstName());
		}

		if (!actualUser.get().getLastName().equals(dtoRequest.getNewLastName())) {
			actualUser.get().setLastName(dtoRequest.getNewLastName());
		}

		return Optional.of(userRepository.save(actualUser.get()));
	}

	@Override
	public Optional<User> updatePasswordByUserMailAddress(String currentUserMailAddress,
			UpdatePasswordDTO updatePasswordDTO) {
		if (updatePasswordDTO.getOldPassword() == null || updatePasswordDTO.getNewPassword() == null) {
			return Optional.empty();
		}
		Optional<User> currentUser = userRepository.findByMailAddress(currentUserMailAddress);

		if (securityService.matches(updatePasswordDTO.getOldPassword(), currentUser.get().getPassword())) {
			currentUser.get().setPassword(securityService.getEncryptedPassword(updatePasswordDTO.getNewPassword()));
			return Optional.of(userRepository.save(currentUser.get()));
		}
		return Optional.empty();
	}

	@Override
	public User disableAccountByUserName(String currentUserName) {
		User currentUser = getUserByUserName(currentUserName).get();
		currentUser.setActive(false);
		return userRepository.save(currentUser);
	}

}
