package com.paymybuddy.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.registration.RegistrationRequest;
import com.paymybuddy.security.model.UserDetailsImpl;
import com.paymybuddy.user.UpdateRequest;
import com.paymybuddy.user.models.User;
import com.paymybuddy.user.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

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
		if (userRepository.findByMailAddress(mailAddress).isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public String deleteUser(RegistrationRequest request) {

		if (alreadyRegistered(request.getMailAddress())) {
			userRepository.delete(userRepository.findByMailAddress(request.getMailAddress()).get());
			return "User deleted";
		}
		return "User doesn't exist";

	}

	@Override
	public String updateUser(UpdateRequest request) {

		if (alreadyRegistered(request.getMailAddress())) {
			User dataBaseUser = userRepository.findByMailAddress(request.getMailAddress()).get();

			dataBaseUser.setPassword(request.getPassword());
			dataBaseUser.setActive(request.isActive());
			userRepository.save(dataBaseUser);
			return "User updated";
		}
		return "User doesn't exist";
	}

	@Override
	public Optional<User> getUser(UpdateRequest request) {

		Optional<User> userOpt = userRepository.findByMailAddress(request.getMailAddress());
		if (userOpt.isPresent() && (userOpt.get().getPassword().equals(request.getPassword()))) {
			return userOpt;
		}

		return Optional.empty();
	}

	public Optional<User> getUserByID(int ID) {
		return userRepository.findById(ID);

	}
}
